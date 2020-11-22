const mongoose = require("mongoose");
const config = require("../config/database");

const OrderSchema = mongoose.Schema({
  _id: mongoose.Schema.Types.ObjectId,
  product: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "Product",
    required: true,
  },
  quantity: { type: Number, default: 1 },
  userId: mongoose.Schema.Types.ObjectId,
});

const Order = (module.exports = mongoose.model("Order", OrderSchema));

module.exports.getOrderByID = function (id, callback) {
  User.findById(id, callback);
};
