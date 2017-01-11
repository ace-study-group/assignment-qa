var _ = require('underscore');
var Backbone = require('backbone');
var PetCollection = require('../collections/pet.collection');
var PetItemView = require('./petItem.view');
var template = require('../templates/pet-list.hbs');

module.exports = Backbone.View.extend({
  className: 'pet-list',

  template: template,

  initialize: function() {
    this.collection = new PetCollection;
    this.listenTo(this.collection, 'reset', this.render);
  },

  update: function(data) {
    this.collection.reset(data);
  },

  createPetItemViews: function() {
    var self = this;
    return this.collection.map(function(petModel) {
      var view = new PetItemView({
        model: petModel
      });

      self.listenTo(view, 'petItem:delete', self.didRequestItemDelete);
      self.listenTo(view, 'petItem:edit', self.didRequestItemEdit);

      return view;
    });
  },

  didRequestItemDelete: function(itemData) {
    this.trigger('petList:delete', itemData);
  },

  didRequestItemEdit: function(itemData) {
    this.trigger('petList:edit', itemData);
  },

  getTableBodyContainer: function() {
    return this.$el.find('tbody.pet-list');
  },

  render: function() {
    this.$el.html(this.template());

    var tableBody = this.getTableBodyContainer(),
      petItemViews = this.createPetItemViews();

    _.each(petItemViews, function(petItemView) {
      tableBody.append(petItemView.render().el);
    });

    return this;
  }
});