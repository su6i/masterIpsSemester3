const mongoose = require("mongoose");
const config = require("../config/database");

const AnnonceSchema = mongoose.Schema({
  _id   : mongoose.Schema.Types.ObjectId,
  owner : mongoose.Schema.Types.ObjectId,
  name: {
    type: String,
    required: true,
  },
  price: {
    type: String,
    required: true,
  },
  image: {
    type: String,
    required: true,
  },
  category: {
    type: String,
    required: true,
  },
  type: {
    type: String,
    required: true,
  },
  description: [String],
});

const Annonce = (module.exports = mongoose.model("Annonce", AnnonceSchema));

module.exports.getAnnonceByID = function (id, callback) {
  User.findById(id, callback);
};
