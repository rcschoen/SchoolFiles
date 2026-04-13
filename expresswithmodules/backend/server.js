// server.js
const express = require('express')
const dotenv = require('dotenv').config()
const connectDB = require('./config/db')
const port = process.env.PORT || 5000
const {errorHandler} = require('./errorMiddleware/errorMiddleware')
const app = express()

connectDB();



// middleware
app.use(express.json())
// to understand form data
app.use(express.urlencoded(
    {
        extended: false
    }
))
app.use('/api/notes', require('./routes/noteRoutes'))

app.use(errorHandler)
app.listen(port, () => console.log(`Server started on port ${port}`))