global.jQuery = require('jquery');
global.Tether = require('tether');
global.bootstrap = require('bootstrap');

var AppView = require('./components/app/views/app.view');
var BannerView = require('./components/app/views/banner.view');

jQuery('.pet-app').append(new AppView().render().el);
jQuery('.banner-date').append(new BannerView().render().el);