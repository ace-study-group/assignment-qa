var Backbone = require('backbone');

module.exports = Backbone.Model.extend({

        defaults: {
            id: '',
            name: '',
            status: ''
        },
        initialize: function () {
        },
        validate: function (attrs) {
            console.log('Validating');
        }
    }
);