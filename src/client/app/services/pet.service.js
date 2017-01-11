var $ = require('jquery');
var _ = require('underscore');
var RSVP = require('rsvp');

var allPets = function() {
    return new RSVP.Promise(function(resolve, reject) {
      $.ajax({
        url: '/api/pets',
        dataType: 'json',
        method: 'GET'
      }).done(function(pets) {
        var mapped = _.map(pets, function(pet) {
          return {
            id: pet.id,
            name: pet.name,
            status: pet.status
          }
        });

        resolve(mapped);
      }).fail(function(jqXHR, textStatus) {
        reject(textStatus);
      });
    });
  },

  petById = function(id) {
    return new RSVP.Promise(function(resolve, reject) {
      $.ajax({
        url: '/api/pets/' + id,
        dataType: 'json',
        method: 'GET'
      }).done(function(pet) {
        resolve({
          id: pet._id,
          name: pet.name,
          status: pet.status
        });
      }).fail(function(jqXHR, textStatus) {
        reject(textStatus);
      });
    });
  },

  createPet = function(props) {
    return new RSVP.Promise(function(resolve, reject) {
      $.ajax({
        url: '/api/pets',
        dataType: 'json',
        method: 'POST',
        data: props
      }).done(function(pet) {
        resolve({
          id: pet._id,
          name: pet.name,
          status: pet.status
        });
      }).fail(function(jqXHR, textStatus) {
        reject(textStatus);
      });
    });
  },

    updatePet = function(props) {
      return new RSVP.Promise(function(resolve, reject) {
        $.ajax({
          url: '/api/pets/'+props.id ,
          dataType: 'json',
          method: 'PUT',
          data: props
        }).done(function(pet) {
          resolve({
            id: pet._id,
            name: pet.name,
            status: pet.status
          });
        }).fail(function(jqXHR, textStatus) {
          reject(textStatus);
        });
      });
    },

  deletePet = function(id) {
    return new RSVP.Promise(function(resolve, reject) {
      $.ajax({
        url: '/api/pets/' + id,
        method: 'DELETE'
      }).done(function() {
        resolve();
      }).fail(function(jqXHR, textStatus) {
        reject(textStatus);
      });
    });
  };

module.exports = {
  allPets: allPets,
  petById: petById,
  updatePet: updatePet,
  createPet: createPet,
  deletePet: deletePet
}