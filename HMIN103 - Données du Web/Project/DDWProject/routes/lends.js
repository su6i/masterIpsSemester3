const express = require("express");
const router = express.Router();

const mongoose = require("mongoose");
const config = require("../config/database");

const mongodb = require('mongodb');

const Lend = require("../models/lend");
const Annonce = require("../models/annonce");

const cors = require("cors");
const bodyParser = require("body-parser");
const { resolveSoa } = require("dns");
const { populate } = require("../models/annonce");

const passport = require("passport");
const jwt = require("jsonwebtoken");
const annonce = require("../models/annonce");

// Passport middleware:
// Middleware is computer software that provides services to software applications
//beyond those available from the operating system.

require("../config/passport")(passport);
router.use(passport.initialize());
router.use(passport.session());

router.use(bodyParser.json()); // to support JSON-encoded bodies
router.use(bodyParser.urlencoded({
  extended: true
})); // to support URL-encoded bodies

// Resolving Cross-origin resource sharing errors
router.use(cors());


// Connect to Database
mongoose
  .connect(config.database, {useUnifiedTopology: true, useNewUrlParser: true, useCreateIndex: true })
  .then(() => console.log("Connected to database " + config.database))
  .catch((err) => console.log(err));


// Database error
mongoose.connection.on("Error", (err) => {
  console.log("Database error " + err);
});

// AnnonceComponent
// All annonces.
router.get(
  "/",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    // console.log("req.user:::", req.user._id);
    Lend.find({ lender: req.user._id })
      .select("annonce _id quantity lender days")
      .populate("annonce", "price name image owner")    //  category type description
      .exec()
      .then((docs) => {
        res.status(200).json({
          // lends: docs.length,
          lends: docs.map((doc) => {
            return {
              _id: doc._id,
              annonce: doc.annonce,
              quantity: doc.quantity,
              days: doc.days,
              lender: doc.lender,
              owner: doc.owner,
              image: doc.image,
              price: doc.price,
              name: doc.name,
              request: {
                type: "GET",
                url: "http://localhost:8888/lends/" + doc._id,
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
  "/:lid",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    console.log("req.params.lid::::",req.params.lid)
    console.log("req.user._id::::",req.user._id);
    Lend.findById(req.params.lid)
      .populate("annonce")
      .exec()
      .then((lend) => {
        if (!lend) {
          return res.status(404).json({
            message: "Lend not found",
          });
        }
        res.status(200).json({
          lend: lend,
          annonce: annonce,
          request: {
            type: "GET",
            url: "http://localhost:8888/lends",
          },
        });
      })
      .catch((err) => {
        res.status(500).json({ error: err });
      });
    }
);

router.patch(
  "/:lid",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    res.status(200).json({
      message: "Lend updated",
      lid: req.params.lid,
    });
  }
);




// my-ads.
router.get(
  "/my/ads",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    console.log("localStorage.getItem('user'): ",localStorage.getItem('user'));
    console.log("req.params::::",req.params);
    console.log("------------req.user._id::::",req.user._id);
    // console.log("ObjectId(req.user._id)::::",mongodb.ObjectId(req.user._id));
    // console.log("localStorage::::",mongodb.ObjectId(localStorage.getItem('user')['id']));

    // let ownerid = ;
    Annonce.find({ owner: mongoose.Types.ObjectId(req.user._id)  })
      // .select("annonce _id quantity lender days")
      .populate("annonce")
      .exec()
      .then((docs) => {
        res.status(200).json({
          // lends: docs.length,
          lends: docs.map((doc) => {
            return {
              _id: doc._id,
              annonce: doc.annonce,
              quantity: doc.quantity,
              days: doc.days,
              lender: doc.lender,
              owner: doc.owner,
              image: doc.image,
              price: doc.price,
              name: doc.name,
              request: {
                type: "GET",
                url: "http://localhost:8888/lends/myads/",
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











router.delete(
  "/:lid",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    Lend.deleteOne({ _id: req.params.lid, lender: req.user._id })
      .exec()
      .then((result) => {
        res.status(200).json({
          message: "Lend deleted",
          request: {
            type: "POST",
            url: "http://localhost:8888/lends",
            body: {
              lid: req.params.lid,
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

// /lends
router.post(
  "/",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    Annonce.findById(req.body.aid)
      .then((annonce) => {
        const lend = new Lend({
          _id: mongoose.Types.ObjectId(),
          lender: req.user._id,
          quantity: req.body.quantity,
          annonce: req.body.aid,
          days: req.body.days,
        });
        return lend.save();
      })
      .then((result) => {
        res.status(200).json({
          message: "Lend stored",
          createdlend: {
            annonce: result.annonce,
            lender: req.user._id,
            quantity: result.quantity,
            days: result.days,
          },
          request: {
            type: "GET",
            url: "http://localhost:8888/lends/" + result._id,
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
