'use strict';

angular.module('oldCountryApp').controller('CountryDialogController',
    ['$scope', '$stateParams', '$modalInstance', '$q', 'entity', 'Country', 'Currency',
        function($scope, $stateParams, $modalInstance, $q, entity, Country, Currency) {

        $scope.country = entity;
        $scope.country_currencys = Currency.query({filter: 'country-is-null'});
        $q.all([$scope.country.$promise, $scope.country_currencys.$promise]).then(function() {
            if (!$scope.country.country_currency.id) {
                return $q.reject();
            }
            return Currency.get({id : $scope.country.country_currency.id}).$promise;
        }).then(function(country_currency) {
            $scope.country_currencys.push(country_currency);
        });
        $scope.load = function(id) {
            Country.get({id : id}, function(result) {
                $scope.country = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('oldCountryApp:countryUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.country.id != null) {
                Country.update($scope.country, onSaveFinished);
            } else {
                Country.save($scope.country, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
