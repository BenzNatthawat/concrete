import express from 'express'

const router = express.Router()

const checkedapi = async (req, res, next) => {
  res.json({ checkedapi: '200' })
}

router.get('/', checkedapi)
module.exports = router
