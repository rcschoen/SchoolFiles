// by default express sends back hard to understand html error 
// we want that error in clean json format 
// we are building error middleware
const errorHandler = (err, req, res, next) =>{
    const statusCode  = res.statusCode ? res.statusCode : 500 ; 
    res.status(statusCode)
    res.json(
        {
            message: err.message,
            stack: process.env.NODE_ENV === 'production' ? null : err.stack
        }
    )
}
module.exports={
    errorHandler
}