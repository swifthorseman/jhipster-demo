'use strict';

angular.module('oldCountryApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


