'use strict';

angular.module('oldCountryApp')
    .controller('CurrencyDetailController', function ($scope, $rootScope, $stateParams, entity, Currency) {
        $scope.currency = entity;
        $scope.load = function (id) {
            Currency.get({id: id}, function(result) {
                $scope.currency = result;
            });
        };
        $rootScope.$on('oldCountryApp:currencyUpdate', function(event, result) {
            $scope.currency = result;
        });
    });
