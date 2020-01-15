import express from 'express'
import loadDB from '../../config/db'

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
  if (deliveryDateTime && items.length) {
    const db = await loadDB()
    await db.query(`INSERT INTO orders (users_id, deliveryDateTime, status, tel, description, name) VALUES ('${id}', '${deliveryDateTime}', 'confirm', '${tel}', '${note}', '${name}')`, async (err, results) => {
      if (!err) {
        items.forEach(async element => {
          await db.query(`INSERT INTO item (orders_id, items_id, deliverycharges_id, price, list, discount, description, quantity) VALUES (${results.insertId}, ${element.items_id || null}, ${element.deliverycharges_id || null}, ${element.price || 0}, '${element.list || null}', ${element.discount || 0}, '${element.desc || ''}', '${element.quantity || 0}')`, (err, results) => {
            if (err) {
              return res.json({ err })
            }
          })
        })
        return res.json({ status: 200, succ: 'succ' })
      } else {
        return res.json({ err })
      }
    })
    return res
  } else {
    return res.json({ error: 'required', deliveryDateTime })
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
          return res.json({ method: 'index', order: order })
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
router.get('/', index)
  .post('/', store)
  .get('/:id', show)
  .post('/:id', update)
  .delete('/:id', destroy)
module.exports = router
