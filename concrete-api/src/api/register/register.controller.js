import express from 'express'
import bcrypt from 'bcrypt'
import loadDB from '../../config/db'
import signin from '../../config/signin'

const router = express.Router()

const register = async (req, res, next) => {
  const { username, password, name } = req.body
  console.log('register')
  if (username && password && name) {
    const db = await loadDB()
    const saltRounds = 10;
    const hash = await bcrypt.hash(password, saltRounds).then(hash => hash)
    await db.query(`INSERT INTO users (username, password, name, status) VALUES ('${username}', '${hash}', '${name}', 1)`, async (err, results) => {
      if (!err) {
        await db.query(`SELECT * FROM users WHERE id = '${results.insertId}'`, async (err, results) => {
          if (!err) {
            const token = signin({ id: results[0].id, username })
            return res.json({ success: 'register success', token, name })
          }
        })
      }
      return res.json({ error: 'register failed', username, password, name })
    })
  } else {
    return res.json({ error: 'required', username, password, name })
  }
}

router.post('/', register)
module.exports = router
