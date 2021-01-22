const mongoose = require("mongoose");
const config = require("../config/database");

const AnnonceSchema = mongoose.Schema({
  _id: mongoose.Schema.Types.ObjectId,
  annonce: {
    type: mongoose.Schema.Types.ObjectId,
    ref : "Annonce",
    required: true,
  },
  keywords: {
    type: [String],
    required: true,

  },
});

const DescAds = (module.exports = mongoose.model("DescAds", DescAdsSchema));
