const express = require("express");
const router = express.Router();

const mongoose = require("mongoose");
const config = require("../config/database");

const Order = require("../models/order");
const Product = require("../models/product");

const cors = require("cors");
const bodyParser = require("body-parser");
const { resolveSoa } = require("dns");
const { populate } = require("../models/product");

const passport = require("passport");
const jwt = require("jsonwebtoken");

// Passport middleware:
// Middleware is computer software that provides services to software applications
//beyond those available from the operating system.

require("../config/passport")(passport);
router.use(passport.initialize());
router.use(passport.session());

router.use(bodyParser.json()); // to support JSON-encoded bodies
router.use(bodyParser.urlencoded()); // to support URL-encoded bodies

// Resolving Cross-origin resource sharing errors
router.use(cors());

// Connect to Database
mongoose
  .connect(config.database)
  .then(() => console.log("Connected to database " + config.database))
  .catch((err) => console.log(err));

// Database error
mongoose.connection.on("Error", (err) => {
  console.log("Database error " + err);
});

// ProductComponent
// All products of all companies.
router.get(
  "/",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    Order.find({ userId: req.user._id })
      .select("product _id quantity")
      .populate("product", "price name")
      .exec()
      .then((docs) => {
        res.status(200).json({
          orders: docs.length,
          orders: docs.map((doc) => {
            return {
              _id: doc._id,
              product: doc.product,
              quantity: doc.quantity,
              request: {
                type: "GET",
                url: "http://localhost:8888/orders/" + doc._id,
              },
            };
          }),
        });
      })
      .catch((err) => {
        res.status(500).json({ error: err });
      });
  }
);

router.get(
  "/:oid",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    Order.findById(req.params.oid)
      .populate("product")
      .exec()
      .then((order) => {
        if (!order) {
          return res.status(404).json({
            message: "Order not found",
          });
        }
        res.status(200).json({
          order: order,
          request: {
            type: "GET",
            url: "http://localhost:8888/orders",
          },
        });
      })
      .catch((err) => {
        res.status(500).json({ error: err });
      });
  }
);

router.patch(
  "/:oid",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    res.status(200).json({
      message: "Order updated",
      oid: req.params.oid,
    });
  }
);

router.delete(
  "/:oid",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    Order.remove({ _id: req.params.oid, userId: req.user._id })
      .exec()
      .then((result) => {
        res.status(200).json({
          message: "Order deleted",
          request: {
            type: "POST",
            url: "http://localhost:8888/orders",
            body: {
              oid: req.params.oid,
            },
          },
        });
      })
      .catch((err) => {
        res.status(500).json({
          error: err,
        });
      });
  }
);

// /orders
router.post(
  "/",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    Product.findById(req.body.pid)
      .then((product) => {
        const order = new Order({
          _id: mongoose.Types.ObjectId(),
          quantity: req.body.quantity,
          product: req.body.pid,
          userId: req.user._id,
        });
        return order.save();
      })
      .then((result) => {
        res.status(200).json({
          message: "Order stored",
          createdOrder: {
            product: result.product,
            quantity: result.quantity,
          },
          request: {
            type: "GET",
            url: "http://localhost:8888/orders/" + result._id,
          },
        });
      })
      .catch((err) => {
        res.status(500).json({
          error: err,
        });
      });
  }
);
module.exports = router;
