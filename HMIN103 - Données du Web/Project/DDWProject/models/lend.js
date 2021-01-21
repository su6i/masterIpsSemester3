const mongoose = require("mongoose");
const config   = require("../config/database");

const LendSchema = mongoose.Schema({
  _id: mongoose.Schema.Types.ObjectId,
  annonce: {
    type: mongoose.Schema.Types.ObjectId,
    ref : "Annonce",
    required: true,
  },
  quantity: { type: Number, default: 1 },
  uid: mongoose.Schema.Types.ObjectId,
  dateReturn  : {
    type: mongoose.Schema.Types.Date,
  },
});

const Lend = (module.exports = mongoose.model("Lend", LendSchema));

module.exports.getLendByID = function (id, callback) {
  User.findById(id, callback);
};
