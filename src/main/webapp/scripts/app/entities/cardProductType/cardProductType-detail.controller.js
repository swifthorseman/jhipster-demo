'use strict';

angular.module('oldCountryApp')
    .controller('CardProductTypeDetailController', function ($scope, $rootScope, $stateParams, entity, CardProductType) {
        $scope.cardProductType = entity;
        $scope.load = function (id) {
            CardProductType.get({id: id}, function(result) {
                $scope.cardProductType = result;
            });
        };
        $rootScope.$on('oldCountryApp:cardProductTypeUpdate', function(event, result) {
            $scope.cardProductType = result;
        });
    });
