const JwtStrategy = require("passport-jwt").Strategy;
const ExtractJwt = require("passport-jwt").ExtractJwt;
const User = require("../models/user");
const config = require("./database");

module.exports = function (passport) {
  //Options
  let opts = {};
  opts.jwtFromRequest = ExtractJwt.fromAuthHeaderAsBearerToken();
  opts.secretOrKey = config.secret;
  passport.use(
    new JwtStrategy(opts, (jwt_payload, done) => {
      console.log("jwt_payload:", jwt_payload);
      User.getUserByID(jwt_payload.data._id, (err, user) => {
        if (err) {
          console.log("Error on jwt_payload");
          return done(err, false);
        }
        if (user) {
          return done(null, user);
        } else {
          return done(null, false);
        }
      });
    })
  );
};
