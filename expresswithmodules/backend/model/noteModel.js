//Shaping Data

const mongoose = require('mongoose')

const noteSchema = mongoose.Schema(

    {
        text: {
                type: String,
                required: [true, "please add text for note"]
              }

    },
    {
        Timestamp: true
    }

)
module.exports = mongoose.model('note', noteSchema)