const express = require('express');

const router = express.Router();

router.get('/', (req,res)=>{
    res.status(200).json({message:"200, ok"})}
)


router.post('/', (req,res)=>{
    res.status(200).json({message:"200, sent"})}
)

router.put('/:id', (req,res)=>{
    res.status(200).json({message:`200, update ${req.params.id}`})}
)

router.delete('/:id', (req,res)=>{
    res.status(200).json({message:`200, delete ${req.params.id}`})}
)

module.exports = router;