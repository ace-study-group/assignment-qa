var Backbone = require('backbone');
var PetModel = require('../models/pet.model');
var template = require('../templates/pet-item.hbs');

module.exports = Backbone.View.extend({
    tagName: 'tr',
    template: template,
    className: 'pet-item',

    events: {
        'click button.delete': 'didClickDeleteButton',
        'click .pet.lbl.pet-name, .pet.lbl.pet-status': 'didClickPet',
        'keypress .usr-input.pet': 'updateOnEnter',
        'keydown .usr-input.pet': 'revertOnEscape',
        'blur .usr-input.pet': 'close'

    },

    didClickDeleteButton: function () {
        this.trigger('petItem:delete', this.model);
    },

    didClickPet: function (e) {
        console.log('petItem.view.js - didDClickPet');
        this.toggle();
        $(e.currentTarget).parent().find('input').focus();
    },

    toggle : function(){
        this.$el.toggleClass('edit');
    },

    revertOnEscape: function (e) {
        // 27 == ESC_KEY
        if (e.which === 27) {
            this.toggle();
        }
    },

    updateOnEnter: function (e)
    {
        if (e.which === 13) {
            this.close();
        }
    },

    close: function (e) {
        if (!this.$el.hasClass('edit')) {
            return;
        }

        this.trigger('petItem:edit', this.formData());
        this.toggle();
    },

    formData: function() {
        return {
            id: this.id,
            name: this.$el.find('.pet.usr-input.pet-name').val().trim(),
            status: this.$el.find('.pet.usr-input.pet-status').val().trim()
        }
    },

    render: function () {
        console.log('petItem.view.js - render');
        var html = this.template(this.model.toJSON());
        this.$el.html(html);
        this.id = this.model.get('id');
        return this;
    }
});