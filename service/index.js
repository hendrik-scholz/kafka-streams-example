const express = require('express');
const app = express();
const port = 3000;

const isbnToDetails = {};
isbnToDetails['9780132350884'] = {
    publisher: "Prentice Hall",
    language: "English",
    numberOfPages: 464,
    price: "30.00 EUR",
};

app.get('/:isbn', (req, res) => {
    const isbn = req.params.isbn;
    const response = isbnToDetails[isbn];

    if (response) {
        res.send(isbnToDetails[isbn]);
    } else {
        res.status(404).send();
    }
});

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`);
});