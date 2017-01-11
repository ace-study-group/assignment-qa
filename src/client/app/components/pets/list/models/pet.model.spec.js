var Backbone = require('backbone');
var PetModel = require('./pet.model');

describe('Pet Model', function() {
  it('should be type of model', function() {
    expect(PetModel.prototype).toEqual(jasmine.any(Backbone.Model));
  });

  it('should have proper defaults defined', function() {
    expect(PetModel.prototype.defaults).toEqual({
      name: '',
      status: ''
    });
  });
});