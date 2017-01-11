var $ = require('jquery');
var AppView = require('./components/app/views/app.view');
var BannerView = require('./components/app/views/banner.view');

$('.pet-app').append(new AppView().render().el);
$('.banner-date').append(new BannerView().render().el);