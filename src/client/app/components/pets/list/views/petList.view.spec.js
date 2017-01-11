var Backbone = require('backbone');
var PetListView = require('./petList.view');
var PetItemView = require('./petItem.view');
var PetModel = require('../models/pet.model');
var template = require('../templates/pet-list.hbs');

describe('Pet List View', function() {
  describe('type', function() {
    it('should be of view', function() {
      expect(PetListView.prototype).toEqual(jasmine.any(Backbone.View));
    });
  });

  describe('properties', function() {
    it('class name should be defined', function() {
      expect(PetListView.prototype.className).toEqual('pet-list');
    });

    it('template should be defined', function() {
      expect(PetListView.prototype.template).toEqual(template);
    });
  });

  describe('creation', function() {
    it('should have pet collection defined', function() {
      var view = new PetListView;

      expect(view.collection).toEqual(jasmine.any(Backbone.Collection));
    });
  });

  describe('api', function() {
    describe('.update()', function() {
      it('should be defined', function() {
        expect(PetListView.prototype.update).toEqual(jasmine.any(Function));
      });

      it('should reset collection', function() {
        spyOn(Backbone.Collection.prototype, 'reset');

        var view = new PetListView,
          fakeData = {};

        view.update(fakeData);

        expect(view.collection.reset).toHaveBeenCalledWith(fakeData);
      });
    });

    describe('.didRequestItemDelete()', function() {
      it('should be defined', function() {
        expect(PetListView.prototype.didRequestItemDelete).toEqual(jasmine.any(Function));
      });

      it('should trigger view event', function() {
        spyOn(PetListView.prototype, 'trigger');

        var view = new PetListView,
          fakeData = {};

        view.didRequestItemDelete(fakeData);

        expect(view.trigger).toHaveBeenCalledWith('petList:delete', fakeData);
      });
    });
  });

  describe('rendering', function() {
    describe('.render()', function() {
      beforeEach(function() {
        spyOn(PetListView.prototype, 'createPetItemViews').and.returnValue([
          new PetItemView({
            model: new PetModel({
              name: 'advisory key',
              status: 'cool company'
            })
          })
        ]);

        this.view = new PetListView;
        this.view.render();
      });

      it('should return view itself', function() {
        expect(this.view.render()).toBe(this.view);
      });

      it('should render table', function() {
        expect(this.view.$el).toContainElement('table');
      });

      it('should render two table rows with head and data', function() {
        expect(this.view.$el.find('tr')).toHaveLength(2);
        expect(this.view.$el.find('tr').last()).toContainText('advisory key');
        expect(this.view.$el.find('tr').last()).toContainText('cool company');
      });
    });
  });

  describe('event', function() {
    describe('custom', function() {
      it('should rerender on collection reset', function() {
        spyOn(PetListView.prototype, 'render');

        var view = new PetListView;

        view.collection.trigger('reset');

        expect(view.render).toHaveBeenCalled();
      });
    });
  });
});