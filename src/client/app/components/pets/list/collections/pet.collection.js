var Backbone = require('backbone');
var PetModel = require('../models/pet.model');

module.exports = Backbone.Collection.extend({
  model: PetModel
});