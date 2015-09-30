'use strict';

angular.module('oldCountryApp')
    .controller('CurrencyController', function ($scope, Currency) {
        $scope.currencys = [];
        $scope.loadAll = function() {
            Currency.query(function(result) {
               $scope.currencys = result;
            });
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Currency.get({id: id}, function(result) {
                $scope.currency = result;
                $('#deleteCurrencyConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Currency.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteCurrencyConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.currency = {code: null, description: null, country: null, id: null};
        };
    });
