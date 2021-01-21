const mongoose = require("mongoose");
var mongooseTypePhone = require('mongoose-type-phone');
const bcrypt = require("bcryptjs");
const config = require("../config/database");


const UserSchema = new mongoose.Schema({
  _id: mongoose.Schema.Types.ObjectId,
  firstName: {
    type: String,
  },
  lastName: {
    type: String,
  },
  phone: {
    type: mongoose.SchemaTypes.Phone,
    required: 'Phone number should be set correctly',
    allowBlank: false,
    allowedNumberTypes: [mongooseTypePhone.PhoneNumberType.MOBILE, mongooseTypePhone.PhoneNumberType.FIXED_LINE_OR_MOBILE],
    phoneNumberFormat: mongooseTypePhone.PhoneNumberFormat.INTERNATIONAL, // can be omitted to keep raw input
    defaultRegion: 'FR',
    parseOnGet: false
},
address:{
    number: Number,
    street: String,
    city: String,
    postal: Number,
  },
  email: {
    type: String,
    unique: true,
    required: true,
  },
  username: {
    type: String,
    required: true,
  },
  password: {
    type: String,
    required: true,
  },
  role: {
    type: String,
    default: "user",
    required: false,
  },
  photo:{
    id: String,
    url: String,
    title: String,
    uploaded: { type: Date, default: Date.now},
  }
});

const User = (module.exports = mongoose.model("User", UserSchema));

module.exports.getUserByID = function (id, callback) {
  User.findById(id, callback);
};

module.exports.getUser = function (value, callback) {
  let field = "username";
  if (value.indexOf("@") !== -1) {
    field = "email";
  }
  const query = { [field]: value };
  User.findOne(query, callback);
};

module.exports.addUser = (newUser, callback) => {
  bcrypt
    .genSalt(10)
    .then((salt) => bcrypt.hash(newUser.password, salt))
    .then((hash) => {
      newUser.password = hash;
      newUser.save(callback);
    })
    .catch((err) => console.log("There was an error adding a user."));
};

module.exports.comparePassword = function (candidatePassword, hash, callback) {
  bcrypt.compare(candidatePassword, hash, (err, isMatch) => {
    if (err) {
      console.log(err);
    }
    callback(null, isMatch);
  });
};
