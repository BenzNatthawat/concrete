import express from 'express'
import loadDB from '../../config/db'

const router = express.Router()

const index = async (req, res, next) => {
  const db = await loadDB()
  await db.query(`SELECT * FROM items`, (err, results) => {
    if (!err) {
      return res.json({ method: 'index', results: results })
    }
  })
}
const store = async (req, res, next) => {
  console.log('store')
  return res.json({ method: 'store' })
}
const show = async (req, res, next) => {
  console.log('show')
  return res.json({ method: 'show' })
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
