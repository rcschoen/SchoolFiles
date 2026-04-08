const dotenv = require('dotenv').config();
const port = process.env.PORT || 1717;

//code
//ser up express
const express = require('express');

const app = express();
// rest of logic

//import router
app.use('/api/notes' , require('./routers/noteRoutes'))













app.listen(port, ()=> console.log(`Server startd on port ${port}`));

