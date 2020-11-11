const mongoose = require("mongoose");
const config = require("../config/database");

const ProductSchema = mongoose.Schema({
  _id: mongoose.Schema.Types.ObjectId,
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
  productImage: {
    type: String,
  },
});

const Product = (module.exports = mongoose.model("Product", ProductSchema));

module.exports.getProductByID = function (id, callback) {
  User.findById(id, callback);
};
