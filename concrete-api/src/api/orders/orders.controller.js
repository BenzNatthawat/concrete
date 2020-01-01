import express from 'express'
import loadDB from '../../config/db'

const router = express.Router()

const index = async (req, res, next) => {
  const db = await loadDB()
  await db.query(`SELECT orders.*, users.name FROM orders INNER JOIN users ON  orders.users_id = users.id`, (err, results) => {
    if (!err) {
      return res.json({ method: 'index', results: results })
    } else { return res.json(err) }
  })
}
const store = async (req, res, next) => {
  const { name, deliveryDateTime, tel, note, item } = req.body
  
  console.log(name, deliveryDateTime, tel, note)
  console.log(item)
  item.forEach(async element => {
    console.log(element)
  })
  return res.json("XXXX")
  const { id } = req.decoded
  // if (deliveryDateTime && item) {
  //   const db = await loadDB()
  //   await db.query(`INSERT INTO orders (users_id, deliveryDateTime, status, tel, description, name) VALUES ('${id}', '${deliveryDateTime}', 'confirm', '${tel}', '${note}', '${name}')`, async (err, results) => {
  //     if (!err) {
  //       item.forEach(async element => {
  //         await db.query(`INSERT INTO item (orders_id, items_id, deliverycharges_id, price, list, discount, description, quantity) VALUES (${results.insertId}, ${element.items_id || null}, ${element.deliverycharges_id || null}, ${element.price || 0}, '${element.list}', ${element.discount}, '${element.desc}', '${element.quantity}')`, () => {
  //           if (err) { return res.json({ err }) }
  //         })
  //       })
  //       return res.json({ succ: 'succ' })
  //     } else {
  //       return res.json({ err })
  //     }
  //   })
  // } else {
  //   return res.json({ error: 'required', deliveryDateTime })
  // }
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
