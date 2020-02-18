import express from 'express'
import loadDB from '../../config/db'
import request from 'request'
const router = express.Router()

const index = async (req, res, next) => {
  const db = await loadDB()
  await db.query(`SELECT orders.*, users.name, SUM(item.price * item.quantity) as sumPrice FROM orders INNER JOIN users ON  orders.users_id = users.id INNER JOIN item ON orders.id = item.orders_id GROUP BY item.orders_id`, (err, results) => {
    if (!err) {
      return res.json({ method: 'index', results: results })
    } else { return res.json(err) }
  })
}
const store = async (req, res, next) => {
  const { name, deliveryDateTime, tel, note, item } = req.body
  const { id } = req.decoded
  const items = JSON.parse(item)
  console.log(items)
  try {
    if (deliveryDateTime && items.length) {
      console.log(deliveryDateTime, items.length)
      const db = await loadDB()
      await db.query(`INSERT INTO orders (users_id, deliveryDateTime, status, tel, note, name) VALUES ('${id}', '${deliveryDateTime}', 'confirm', '${tel}', '${note || ''}', '${name}')`, async (err, results) => {
        if (!err) {
          items.forEach(async element => {
            await db.query(`INSERT INTO item (orders_id, items_id, deliverycharges_id, price, list, discount, description, quantity) VALUES (${results.insertId}, ${element.items_id || null}, ${element.deliverycharges_id || null}, ${element.price || 0}, '${element.list || null}', ${element.discount || 0}, '${element.desc || ''}', '${element.quantity || 0}')`, (err, results) => {
              if (err) {
                console.log(err)
                return res.json({ err })
              }
            })
          })

          let mess = {
            'ชื่อ': name,
            'วันที่ส่ง': deliveryDateTime,
            'เบอร์โทร': tel,
            'สถานที่จัดส่ง': note,
          }

          notify(JSON.stringify(mess), next)

          return res.json({ status: 200, succ: 'succ' })
        } else {
          console.log(err)
          return res.json({ err })
        }
      })
      return res
    } else {
      return res.json({ error: 'required', deliveryDateTime })
    }
  } catch (err) {
    console.log(err)
  }
}
const show = async (req, res, next) => {
  const { id } = req.params
  const db = await loadDB()
  let order
  await db.query(`SELECT * FROM orders WHERE id = ${id}`, async (err, results) => {
    if (!err) {
      order = { ...results[0] }
      await db.query(`SELECT * FROM item WHERE orders_id = ${id}`, (err, results) => {
        if (!err) {
          order.item = [...results]
          return res.json({ method: 'index', results: order })
        } else { return res.json(err) }
      })
    } else { return res.json(err) }
  })
}
const update = async (req, res, next) => {
  console.log('update')
  return res.json({ method: 'update' })
}
const destroy = async (req, res, next) => {
  console.log('destroy')
  return res.json({ method: 'destroy' })
}

const notify = (message, next) => {
  var token = 'BlHysak8GlBHVUFEaIv07azPTmQeIchJNqBrStITUlO'
  var message = message

  request({
    method: 'POST',
    uri: 'https://notify-api.line.me/api/notify',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    auth: {
      'bearer': token
    },
    form: {
      message: message
    }
  }, (err, httpResponse, body) => {
    if (err) {
      console.log(err);
    } else {
      next()
    }
  })
}

router.get('/', index)
  .post('/', store)
  .get('/:id', show)
  .post('/:id', update)
  .delete('/:id', destroy)
module.exports = router
