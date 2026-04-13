const express = require('express');
const {
    getNote,
    setNote,
    updateNote,
    deleteNote
} = require('../controllers/noteController')
const router = express.Router();

//router.get('/', getNote)
//router.post('/', setNote)
//router.put('/:id', updateNote)
//router.delete('/:id', deleteNote)

router.route('/').get(getNote).post(setNote)
router.route('/:id').put(updateNote).delete(deleteNote)



module.exports = router;