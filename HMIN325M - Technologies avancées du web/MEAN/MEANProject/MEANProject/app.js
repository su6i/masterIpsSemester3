const express = require("express");
const app = express();
const usersRoutes = require("./routes/users");
const productsRoutes = require("./routes/products");
const ordersRoutes = require("./routes/orders");
const morgan = require("morgan");
const bodyParser = require("body-parser");

const cors = require("cors");
const { toArray } = require("rxjs-compat/operator/toArray");

// Passport middleware:
// Middleware is computer software that provides services to software applications
//beyond those available from the operating system.
const passport = require("passport");
require("./config/passport")(passport);
app.use(passport.initialize());
app.use(passport.session());

const config = require("./config/database");
const mongoose = require("mongoose");
mongoose.Promise = global.Promise;

app.use(morgan("dev"));
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// Routes

app.use("/users", usersRoutes);
app.use("/orders", ordersRoutes);
app.use("/products", productsRoutes);

// Resolving Cross-origin resource sharing errors
app.use(cors());

app.get("/", (req, res) => {
  res.send("Welcome to root / folder.");
});

app.use("/uploads", express.static("uploads"));
app.use("/assets/image", express.static("src/assets/image"));

// app.use('/a', express.static(path.join(__dirname, 'b')));
// app.use(express.static(path.join(__dirname, 'public')));

// app.get('*', (req, res) => {
//   res.sendFile(path.join(__dirname, 'public/index.html'));
// })

// CategoryComponent
// All companies. like: Apple, Samsung, Huawei, etc.
app.get("/categories", (req, res) => {
  console.log("route: /categories");
  mongoose.connection.db
    .collection("products")
    .distinct("category", (err, documents) => {
      res.json(documents);
    });
});

// Types of products of all companies. like: Smartphones, Tablets, Smartwatches, etc.
app.get("/categories/:category/", (req, res) => {
  console.log("route: /categories/:category/:type");
  mongoose.connection.db
    .collection("products")
    .distinct("type", { category: req.params.category }, (err, documents) => {
      res.json(documents);
    });
});

// Ex. All Apple's Smartphone
app.get("/categories/:category/:types", (req, res) => {
  console.log("route: /categories/:category/:types");
  mongoose.connection.db
    .collection("products")
    .find({
      $and: [{ type: req.params.types }, { category: req.params.category }],
    })
    .toArray((err, documents) => {
      res.json(documents);
    });
});

app.use((req, res, next) => {
  const error = new Error("Data that you requested not found!");
  error.status = 404;
  next(error);
});

app.use((error, req, res, next) => {
  res.status(error.status || 500);
  res.json({
    error: {
      message: error.message,
    },
  });
});

module.exports = app;
