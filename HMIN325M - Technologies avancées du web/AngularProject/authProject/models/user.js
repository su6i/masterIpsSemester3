const mongoose = require("mongoose");
const bcrypt = require("bcryptjs");
const config = require("../config/database");

const UserSchema = mongoose.Schema({
  _id: mongoose.Schema.Types.ObjectId,
  name: {
    type: String,
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
