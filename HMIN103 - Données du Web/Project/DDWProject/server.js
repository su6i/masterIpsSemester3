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

server.listen(port, () => {
  console.log("Electronic shop server is running on port: " + port);
});
