const express = require("express");
const app = express();

const MongoClient = require("mongodb").MongoClient;
const url = "mongodb://localhost:27017";

MongoClient.connect(url, { useNewUrlParser: true }, (err, client) => {
  let db = client.db("donnees_web");
  var cors = require("cors");

  app.use(express.json());
  app.use(express.urlencoded({ extended: true }));

  // use it before all route definitions
  //app.use(cors({origin: 'http://localhost:8888'}));
  app.use(cors());

  const port = 8888;

  // Add headers
  app.use(function (req, res, next) {
    // Website you wish to allow to connect
    res.setHeader("Access-Control-Allow-Origin", "*");

    // Request methods you wish to allow
    res.setHeader(
      "Access-Control-Allow-Methods",
      "GET, POST, OPTIONS, PUT, PATCH, DELETE"
    );

    // Request headers you wish to allow
    res.setHeader("Access-Control-Allow-Headers", "*");

    // Set to true if you need the website to include cookies in the requests sent
    // to the API (e.g. in case you use sessions)
    res.setHeader("Access-Control-Allow-Credentials", true);

    // Pass to next layer of middleware
    next();
  });

  // app.get("/annonces", (req, res) => {
  //     console.log("route: /annonces");
  //     db.collection("annonces").find().toArray((err, documents) => {
  //         res.json((documents));
  //     })
  //   })

  //   app.get("/annonces/nom/:nom", (req, res) => {
  //     console.log("This is my route: /nom");
  //     db.collection("annonces").find({"Name":req.params.nom}).toArray((err, documents) => {
  //         res.end(JSON.stringify(documents));
  //     })
  //   })

  //   app.get("/annonces/price/:prix", (req, res) => {
  //     console.log("This is my route: /nom");
  //     db.collection("annonces").find({"price":parseInt(req.params.prix)}).toArray((err, documents) => {
  //         res.end(JSON.stringify(documents));
  //     })
  //   })

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
});

console.log("Node is connected on port: 8888");
app.listen(8888);
