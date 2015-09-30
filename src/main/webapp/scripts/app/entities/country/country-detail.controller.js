'use strict';

angular.module('oldCountryApp')
    .controller('CountryDetailController', function ($scope, $rootScope, $stateParams, entity, Country, Currency) {
        $scope.country = entity;
        $scope.load = function (id) {
            Country.get({id: id}, function(result) {
                $scope.country = result;
            });
        };
        $rootScope.$on('oldCountryApp:countryUpdate', function(event, result) {
            $scope.country = result;
        });
    });
