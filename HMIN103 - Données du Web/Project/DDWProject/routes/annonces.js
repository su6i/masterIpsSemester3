const express = require("express");
const router = express.Router();

const config = require("../config/database");
const mongoose = require("mongoose");
const cors = require("cors");
const bodyParser = require("body-parser");
const Annonce = require("../models/annonce");
const multer = require("multer");

const fileFilter = (req, file, callback) => {
  // Reject a file
  if (file.mimetype === "image/jpeg" || file.mimetype === "image/png") {
    callback(null, true);
  } else {
    callback(null, false);
  }
};
const storage = multer.diskStorage({
  destination: function (req, file, callback) {
    callback(null, "./public/images/annonces");
  },
  filename: function (req, file, callback) {
    callback(null, new Date().toISOString() + "--" + file.originalname);
  },
});
const upload = multer({
  storage: storage,
  limits: {
    fileSize: 1024 * 1024 * 5,
  },
  fileFilter: fileFilter,
});

router.use(bodyParser.json()); // to support JSON-encoded bodies
router.use(bodyParser.urlencoded()); // to support URL-encoded bodies

// Resolving Cross-origin resource sharing errors
router.use(cors());

// Connect to Database
mongoose
  .connect(config.database)
  .then(() => console.log("Connected to database " + config.database))
  .catch((err) => {
    console.log(err);
    console.log("Database error " + err);
  });

// AnnonceComponent
// All annonces of all companies.
router.get("/", (req, res, next) => {
  Annonce.find()
    .collation({ locale: "fr" })
    .sort({ name: 1 })
    .select("_id name price image category type description nameLowerCase")
    .exec()
    .then((docs) => {
      const response = {
        count: docs.length,
        annonces: docs.map((doc) => {
          return {
            _id: doc._id,
            name: doc.name,
            nameLowerCase: doc.nameLowerCase,
            price: doc.price,
            image: doc.image,
            category: doc.category,
            type: doc.type,
            description: doc.description,
            request: {
              type: "GET",
              url: "http://localhost:8888/annonces/" + doc._id,
            },
          };
        }),
      };
      if (docs.length >= 0) {
        res.status(200).json(response);
      } else {
        res.status(404).json({ message: "No entry found" });
      }
    })
    .catch((err) => {
      console.log(err);
      res.status(500).json({ error: err });
    });
});

router.get("/:aid", (req, res, next) => {
  const id = req.params.aid;
  Annonce.findById(id)
    .select("_id name price image category type description")
    .exec()
    .then((doc) => {
      if (doc) {
        res.status(200).json({
          _id: doc._id,
          name: doc.name,
          price: doc.price,
          image: {
            name: doc.image,
            url: "http://localhost:8888" + doc.image,
          },
          category: doc.category,
          type: doc.type,
          description: doc.description,
          request: {
            type: "GET",
            url: "http://localhost:8888/annonces/",
          },
        });
      } else {
        res.status(404).json({
          message: "No valid entry found for provided ID",
          url: "http://localhost:8888/annonces",
        });
      }
    })
    .catch((err) => {
      console.log(err);
      res.status(500).json({ error: err });
    });
});

router.patch("/:aid", (req, res, next) => {
  const id = req.params.aid;
  const updateOps = {};
  for (const ops of req.body) {
    updateOps[ops.propName] = ops.value;
  }
  Annonce.update({ _id: id }, { $set: updateOps })
    .exec()
    .then((result) => {
      res.status(200).json({
        message: "Annonce Updated",
        request: {
          type: "GET",
          url: "http://localhost:8888/annonces/" + id,
        },
      });
    });
});

router.delete("/:aid", (req, res, next) => {
  const id = req.params.aid;
  Annonce.remove({ _id: id })
    .exec()
    .then((result) => {
      res.status(200).json({
        message: "Annonce deleted",
        request: {
          type: "POST",
          url: "http://localhost:8888/annonces",
          body: { name: "String", price: "Number" },
        },
      });
    })
    .catch((err) => {
      console.log(err);
      res.status(500).json({ error: err });
    });
});

router.post("/", upload.single("image"), (req, res, next) => {
  const annonce = new Annonce({
    _id: new mongoose.Types.ObjectId(),
    name: req.body.name,
    price: req.body.price,
    image: "/" + req.file.path,
    category: req.body.category,
    type: req.body.type,
    description: req.body.description,
  });
  annonce
    .save()
    .then((result) => {
      res.status(201).json({
        message: "Created annonce successfully",
        createdAnnonce: {
          name: result.name,
          price: result.price,
          _id: result._id,
          image: result.image,
          category: result.category,
          type: result.type,
          description: result.description,
          request: {
            type: "GET",
            url: "http://localhost:8888/annonces/" + result._id,
          },
        },
      });
    })
    .catch((err) => {
      console.log(err);
      res.status(500).json({
        error: err,
      });
    });
});

module.exports = router;
