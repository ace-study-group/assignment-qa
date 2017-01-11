var $ = require('jquery');
var _ = require('underscore');
var dateFormat = require ('dateformat')
var Backbone = require('backbone');

module.exports = Backbone.View.extend({

  render: function() {
    var day=dateFormat(new Date(), "dd-mm-yyyy");
    this.$el.append(day);
    return this;
  }
});