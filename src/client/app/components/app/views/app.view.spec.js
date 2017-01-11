var _ = require('underscore');
var Backbone = require('backbone');
var jasmineJquery = require('jasmine-jquery');
var RSVP = require('rsvp');
var AppView = require('./app.view');
var PetFormView = require('../../pets/form/views/petForm.view');
var PetListView = require('../../pets/list/views/petList.view');
var petService = require('../../../services/pet.service');

describe('App View', function() {
  describe('type', function() {
    it('should be of view', function() {
      expect(AppView.prototype).toEqual(jasmine.any(Backbone.View));
    });
  });

  describe('creation', function() {
    beforeEach(function() {
      spyOn(_, 'bindAll');
      spyOn(AppView.prototype, 'loadPets');
      this.view = new AppView;
    });

    it('should have pet form view defined', function() {
      expect(this.view.petFormView).toEqual(jasmine.any(PetFormView))
    });

    it('should have pet list view defined', function() {
      expect(this.view.petListView).toEqual(jasmine.any(PetListView))
    });

    it('should load pets', function() {
      expect(this.view.loadPets).toHaveBeenCalled();
    });
  });

  describe('api', function() {
    describe('.loadPets()', function() {
      beforeEach(function() {
        this.view = new AppView;
      });

      it('should be defined', function() {
        expect(AppView.prototype.loadPets).toEqual(jasmine.any(Function));
      });

      it('should call pet service', function() {
        spyOn(petService, 'allPets').and.returnValue(RSVP.Promise.resolve());

        this.view.loadPets();

        expect(petService.allPets).toHaveBeenCalled();
      });

      it('should call success method after service is done', function(done) {
        spyOn(petService, 'allPets').and.returnValue(RSVP.Promise.resolve('success'));

        this.view.didLoadPets = function(data) {
          expect(data).toEqual('success');
          done();
        }

        this.view.loadPets({});
      });
    });

    describe('.didRequestItemCreation()', function() {
      beforeEach(function() {
        this.view = new AppView;
      });

      it('should be defined', function() {
        expect(AppView.prototype.didRequestItemCreation).toEqual(jasmine.any(Function));
      });

      it('should call pet service', function() {
        spyOn(petService, 'createPet').and.returnValue(RSVP.Promise.resolve());

        var fakeData = {};

        this.view.didRequestItemCreation(fakeData);

        expect(petService.createPet).toHaveBeenCalledWith(fakeData);
      });

      it('should call success method after service is done', function(done) {
        spyOn(petService, 'createPet').and.returnValue(RSVP.Promise.resolve('success'));

        var fakeData = {};

        this.view.loadPets = function() {
          expect(true).toEqual(true);
          done();
        }

        this.view.didRequestItemCreation(fakeData);
      });
    });

    describe('.didRequestItemDeletion()', function() {
      beforeEach(function() {
        this.view = new AppView;
      });

      it('should be defined', function() {
        expect(AppView.prototype.didRequestItemDeletion).toEqual(jasmine.any(Function));
      });

      it('should call pet service', function() {
        spyOn(petService, 'deletePet').and.returnValue(RSVP.Promise.resolve());

        var fakeData = {
          id: 1
        };

        this.view.didRequestItemDeletion(fakeData);

        expect(petService.deletePet).toHaveBeenCalledWith(fakeData.id);
      });

      it('should call success method after service is done', function(done) {
        spyOn(petService, 'deletePet').and.returnValue(RSVP.Promise.resolve('success'));

        var fakeData = {
          id: 1
        };

        this.view.loadPets = function() {
          expect(true).toEqual(true);
          done();
        }

        this.view.didRequestItemDeletion(fakeData);
      });
    });

    describe('.didLoadPets()', function() {
      it('should be defined', function() {
        expect(AppView.prototype.didLoadPets).toEqual(jasmine.any(Function));
      });

      it('should update pet list view', function() {
        var view = new AppView,
          fakeData = {};

        spyOn(view.petListView, 'update');

        view.didLoadPets(fakeData);

        expect(view.petListView.update).toHaveBeenCalledWith(fakeData);
      });
    });
  });

  describe('rendering', function() {
    describe('.render()', function() {
      beforeEach(function() {
        spyOn(AppView.prototype, 'loadPets');
        this.view = new AppView;
        this.view.render();
      });

      it('should return view itself', function() {
        expect(this.view.render()).toBe(this.view);
      });

      it('should render pet form view', function() {
        expect(this.view.$el).toContainHtml(this.view.petFormView.render().$el.html());
      });

      it('should render pet list view', function() {
        expect(this.view.$el).toContainHtml(this.view.petListView.render().$el.html());
      });
    });
  });

  describe('events', function() {
    describe('custom', function() {
      beforeEach(function() {
        spyOn(_, 'bindAll');
        spyOn(AppView.prototype, 'loadPets');
        spyOn(AppView.prototype, 'didRequestItemCreation');
        spyOn(AppView.prototype, 'didRequestItemDeletion');

        this.view = new AppView;
      });

      it('should listen to pet form create event', function() {
        var fakeData = {};
        this.view.petFormView.trigger('petForm:create', fakeData);

        expect(this.view.didRequestItemCreation).toHaveBeenCalledWith(fakeData);
      });

      it('should listen to pet list delete event', function() {
        var fakeData = {};
        this.view.petListView.trigger('petList:delete', fakeData);

        expect(this.view.didRequestItemDeletion).toHaveBeenCalledWith(fakeData);
      });
    });
  });
});