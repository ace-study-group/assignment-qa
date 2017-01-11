var _ = require('underscore');
var Backbone = require('backbone');
var PetFormView = require('./petForm.view');
var template = require('../templates/pet-form.hbs');

describe('Pet Form View', function() {
  describe('type', function() {
    it('should be of view', function() {
      expect(PetFormView.prototype).toEqual(jasmine.any(Backbone.View));
    });
  });

  describe('properties', function() {
    it('class name should be defined', function() {
      expect(PetFormView.prototype.className).toEqual('pet-form');
    });

    it('template should be defined', function() {
      expect(PetFormView.prototype.template).toEqual(template);
    });
  });

  describe('api', function() {
    describe('.didClickCreateButton()', function() {
      it('should be defined', function() {
        expect(PetFormView.prototype.didClickCreateButton).toEqual(jasmine.any(Function));
      });

      it('should request creation of pet', function() {
        spyOn(_, 'bindAll');
        spyOn(PetFormView.prototype, 'requestCreate');

        var view = new PetFormView,
          fakeEvent = {
            preventDefault: function() {}
          };

        view.didClickCreateButton(fakeEvent);

        expect(view.requestCreate).toHaveBeenCalled();
      });
    });

    describe('.didPressKey()', function() {
      it('should be defined', function() {
        expect(PetFormView.prototype.didPressKey).toEqual(jasmine.any(Function));
      });

      it('should request creation of pet if ENTER key was pressed', function() {
        spyOn(_, 'bindAll');
        spyOn(PetFormView.prototype, 'requestCreate');

        var view = new PetFormView,
          fakeEvent = {
            preventDefault: function() {},
            which: 13
          };

        view.didPressKey(fakeEvent);

        expect(view.requestCreate).toHaveBeenCalled();
      });
    });

    describe('.requestCreate()', function() {
      it('should be defined', function() {
        expect(PetFormView.prototype.requestCreate).toEqual(jasmine.any(Function));
      });

      it('should trigger view event', function() {
        var fakeFormData = {};
        spyOn(_, 'bindAll');
        spyOn(PetFormView.prototype, 'trigger');
        spyOn(PetFormView.prototype, 'formData').and.returnValue(fakeFormData);

        var view = new PetFormView;

        view.requestCreate();

        expect(view.trigger).toHaveBeenCalledWith('petForm:create', fakeFormData);
      });

      it('should trigger view event', function() {
        var fakeFormData = {};
        spyOn(_, 'bindAll');
        spyOn(PetFormView.prototype, 'clearForm');
        spyOn(PetFormView.prototype, 'formData').and.returnValue(fakeFormData);

        var view = new PetFormView;

        view.requestCreate();

        expect(view.clearForm).toHaveBeenCalled();
      });
    });
  });

  describe('rendering', function() {
    describe('.render()', function() {
      beforeEach(function() {
        this.view = new PetFormView;
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
        expect(PetFormView.prototype.events).toEqual({
          'click button.create': 'didClickCreateButton',
          'keypress .pet-name': 'didPressKey',
          'keypress .pet-status': 'didPressKey'
        });
      });
    });
  });
});