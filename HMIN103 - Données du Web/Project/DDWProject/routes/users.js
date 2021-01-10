const express = require("express");
const router = express.Router();
const config = require("../config/database");
const mongoose = require("mongoose");
const passport = require("passport");
const jwt = require("jsonwebtoken");
const cors = require("cors");

// Passport middleware:
// Middleware is computer software that provides services to software applications
//beyond those available from the operating system.

require("../config/passport")(passport);
router.use(passport.initialize());
router.use(passport.session());

// Resolving Cross-origin resource sharing errors
router.use(cors());

// Import Mongoose Model
const User = require("../models/user");

// // Connect to Database
// mongoose
//   .connect(config.database)
//   .then(() => console.log("Connected to database " + config.database))
//   .catch((err) => {
//     console.log(err);
//     console.log("Database error " + err);
//   });

// Just for developement porposes
router.get("/", (req, res) => {
  mongoose.connection.db
    .collection("users")
    .find()
    .toArray((err, documents) => {
      res.json(documents);
    });
});

// Register new user
router.post("/register", (req, res, next) => {
  User.findOne({ email: req.body.email })
    .exec()
    .then((user) => {
      if (user != null) {
        console.log("Mail exists!");
        return res.status(409).json({
          message: "Mail exists!",
        });
      } else {
        let newUser = new User({
          _id: new mongoose.Types.ObjectId(),
          firstName: req.body.firstName,
          lastName: req.body.lastName,
          phone: req.body.phone,
          email: req.body.email,
          username: req.body.username,
          password: req.body.password,
          address: {
            number: req.body.number,
            street: req.body.street,
            city: req.body.city,
          }
        });
        User.addUser(newUser, (err, user) => {
          if (err) {
            res.json({ success: false, message: "Faild to register user" });
          } else {
            res.json({
              success: true,
              message: "User registered successfully",
            });
          }
        });
      }
    });
});

// Profile
router.get(
  "/profile",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    res.json({ user: req.user });
  }
);

// test
router.get("/test", (req, res, next) => {
  res.send("Hassina, elle est pareseuse!!!!!");
});

// Authenticate
router.post("/authenticate", (req, res, next) => {
  const value = req.body.email;
  const password = req.body.password;

  User.getUser(value, (err, user) => {
    if (err) throw err;

    if (!user) {
      return res.json({ success: false, msg: "User not found" });
    }
    User.comparePassword(password, user.password, (err, isMath) => {
      if (err) throw err;
      if (isMath) {
        const token = jwt.sign({ data: user }, config.secret, {
          expiresIn: 604800, // One week
        });
        res.json({
          success: true,
          token: `${token}`,
          user: {
            id: user._id,
            name: user.name,
            username: user.username,
            email: user.email,
          },
        });
      } else {
        return res.json({ success: false, msg: "Wrong password" });
      }
    });
  });
});

// Delete account
router.delete(
  "/:userId",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    User.remove({ _id: req.params.userId })
      .exec()
      .then((result) => {
        result.status(200).json({
          message: "User deleted",
        });
      })
      .catch((err) => {
        console.log(err);
        result.status(500).json({
          error: err,
        });
      });
  }
);

module.exports = router;
