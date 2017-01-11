var Backbone = require('backbone');
var PetItemView = require('./petItem.view');
var PetModel = require('../models/pet.model');
var template = require('../templates/pet-item.hbs');

describe('Pet Item View', function() {
  describe('type', function() {
    it('should be of view', function() {
      expect(PetItemView.prototype).toEqual(jasmine.any(Backbone.View));
    });
  });

  describe('properties', function() {
    it('tag name should be tr', function() {
      expect(PetItemView.prototype.tagName).toEqual('tr');
    });

    it('template should be defined', function() {
      expect(PetItemView.prototype.template).toEqual(template);
    });
  });

  describe('api', function() {
    describe('.didClickDeleteButton()', function() {
      it('should be defined', function() {
        expect(PetItemView.prototype.didClickDeleteButton).toEqual(jasmine.any(Function));
      });

      it('should trigger view event', function() {
        var fakeJSON = {};

        spyOn(PetItemView.prototype, 'trigger');
        spyOn(PetModel.prototype, 'toJSON').and.returnValue(fakeJSON);

        var view = new PetItemView({
          model: new PetModel
        });

        view.didClickDeleteButton();

        expect(view.trigger).toHaveBeenCalledWith('petItem:delete', fakeJSON);
      });
    });
  });

  describe('rendering', function() {
    describe('.render()', function() {
      beforeEach(function() {
        this.view = new PetItemView({
          model: new PetModel
        });
        this.view.render();
      });

      it('should return view itself', function() {
        expect(this.view.render()).toBe(this.view);
      });

      it('should render template', function() {
        expect(this.view.render().$el).toContainHtml(template);
      });
    });
  });

  describe('events', function() {
    describe('dom', function() {
      it('should be properly defined', function() {
        expect(PetItemView.prototype.events).toEqual({
          'click button.delete': 'didClickDeleteButton'
        })
      });
    });
  });
});