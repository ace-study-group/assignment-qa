var _ = require('underscore');
var Backbone = require('backbone');
var template = require('../templates/pet-form.hbs');

module.exports = Backbone.View.extend({
  className: 'pet-form',

  template: template,

  initialize: function() {
    _.bindAll(this, 'requestCreate', 'clearForm', 'formData');
  },

  events: {
    'click button.create': 'didClickCreateButton',
    'keypress .pet-name': 'didPressKey',
    'keypress .pet-status': 'didPressKey'
  },

  didClickCreateButton: function(e) {
    e.preventDefault();
    this.requestCreate();
  },

  didPressKey: function(e) {
    if (e.which === 13) {
      e.preventDefault();
      this.requestCreate();
    }
  },

  requestCreate: function() {
    this.trigger('petForm:create', this.formData());
    this.clearForm();
  },

  clearForm: function() {
    this.$el.find('.pet-name').val('').focus();
    this.$el.find('.pet-status').val('');
  },

  formData: function() {
    return {
      name: this.$el.find('.pet-name').val(),
      status: this.$el.find('.pet-status').val(),
    }
  },

  render: function() {
    var html = this.template();
    this.$el.html(html);

    return this;
  }
});