var $ = require('jquery');
var _ = require('underscore');
var Backbone = require('backbone');
var PetListView = require('../../pets/list/views/petList.view');
var PetFormView = require('../../pets/form/views/petForm.view');
var petService = require('../../../services/pet.service');

module.exports = Backbone.View.extend({
  initialize: function() {
    _.bindAll(this, 'didLoadPets', 'loadPets');
    this.petFormView = new PetFormView;
    this.petListView = new PetListView;
    this.listenTo(this.petFormView, 'petForm:create', this.didRequestItemCreation);
    this.listenTo(this.petListView, 'petList:delete', this.didRequestItemDeletion);
    this.listenTo(this.petListView, 'petList:edit', this.didRequestItemEdit);
    this.loadPets();
  },

  loadPets: function() {
    petService.allPets().then(this.didLoadPets);
  },

  didRequestItemCreation: function(itemData) {
    petService.createPet(itemData).then(this.loadPets);
  },

  didRequestItemEdit: function(itemData) {
    console.log('app.view.js - didRequestItemEdit');
    petService.updatePet(itemData).then(this.loadPets);
  },
  didRequestItemDeletion: function(itemData) {
    console.log('app.view.js - didRequestItemDeletion');
    petService.deletePet(itemData.id).then(this.loadPets);
  },

  didLoadPets: function(pets) {
    console.log('app.view.js - didLoadPets');
    this.petListView.update(pets);
  },

  render: function() {
    console.log('app.view.js - render');
    this.$el.append(this.petFormView.render().el);
    this.$el.append(this.petListView.render().el);

    return this;
  }
});