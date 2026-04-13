

const asyncHandler = require('express-async-handler')
const Note = require('../model/noteModel')

const getNote = asyncHandler(async (req, res) => {
    const notes = await Note.find()
    res.status(200).json({notes})
})

const setNote = asyncHandler(async (req, res) => {

    if(!req.body.text){
        res.status(400)
        throw new Error("Please Enter Text")
    }

    const note_created = await Note.create(
        {
            text:req.body.text
        }
    )

    res.status(200).json({message:"Set Notes"})
})

const updateNote = asyncHandler(async (req, res) => {
  res.status(200).json({message:`Update Note ${req.params.id}`})
})

const deleteNote = asyncHandler(async (req, res) => {
   res.status(200).json({message:`Delete Note ${req.params.id}`})
})

module.exports = {
    getNote,
    setNote,
    updateNote,
    deleteNote
}
