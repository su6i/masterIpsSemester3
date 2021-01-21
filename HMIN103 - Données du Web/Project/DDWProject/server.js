// const express       = require('express')                     ;
// const app           = express()                              ;
const http = require("http");
const app = require("./app");
const port = process.env.PORT || 8888;
const server = http.createServer(app);

const config = require("./config/database");
const mongoose = require("mongoose");
const cors = require("cors");

// Connect to Database
mongoose
  .connect(config.database)
  .then(() => console.log("Connected to database " + config.database))
  .catch((err) => console.log(err));

// Database error
mongoose.connection.on("Error", (err) => {
  console.log("Database error " + err);
});

// Resolving Cross-origin resource sharing errors
app.use(cors());



// Some unimportant routes

app.get("/category", (req, res) => {
  console.log("This is my route: /category");
  db.collection("annonces").distinct("Category", (err, documents) => {
    res.json(documents);
  });
});

app.get("/category/:type", (req, res) => {
  console.log("This is my route: /category/:type");
  db.collection("annonces")
    .find({ Category: req.params.type })
    .toArray((err, documents) => {
      res.end(JSON.stringify(documents));
    });
});

app.get("/comment", (req, res) => {
  console.log("This is my route: /comment");
  db.collection("annonces").distinct("comment", (err, documents) => {
    res.json(documents);
  });
});

app.post("/users/connection", (req, res) => {
  console.log("route: /users/connection/ avec " + JSON.stringify(req.body));
  db.collection("users")
    .findOne(req.body)
    .then((result) => {
      res.json(result);
      console.log(result);
    })
    .catch((error) => console.error(error));
});


server.listen(port, () => {
  console.log("P2P Lend server is running on port: " + port);
});
