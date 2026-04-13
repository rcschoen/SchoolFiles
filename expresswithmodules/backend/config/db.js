//connect to database

const mongoose = require('mongoose')
const connectDB = async()=>{

    try{
        const conn = await mongoose.connect(process.env.MONGO_URI);
        console.log("Database Online", conn.Connection.host)
    }catch(error){
        console.log(error)
        process.exit(1)
    };
}
module.export = connectDB;