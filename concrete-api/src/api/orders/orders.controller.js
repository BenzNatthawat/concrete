import express from 'express'
import loadDB from '../../config/db'

const router = express.Router()

const index = async (req, res, next) => {
  console.log('index')
  console.log(req, res, next)
}
const store = async (req, res, next) => {
  console.log('store')
  console.log(req, res, next)
}
const show = async (req, res, next) => {
  console.log('show')
  console.log(req, res, next)
}
const update = async (req, res, next) => {
  console.log('update')
  console.log(req, res, next)
}
const destroy = async (req, res, next) => {
  console.log('destroy')
  console.log(req, res, next)
}
router.get('/', index)
  .post('/', store)
  .get('/:id', show)
  .post('/:id', update)
  .delete('/:id', destroy)
module.exports = router
