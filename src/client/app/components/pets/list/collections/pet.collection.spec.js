var Backbone = require('backbone');
var PetModel = require('../models/pet.model');
var PetCollection = require('./pet.collection');

describe('Pet Collection', function() {
  it('should be type of collection', function() {
    expect(PetCollection.prototype).toEqual(jasmine.any(Backbone.Collection));
  });

  it('should have Pet model defined', function() {
    expect(PetCollection.prototype.model).toBe(PetModel);
  });
});