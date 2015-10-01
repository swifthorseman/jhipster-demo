'use strict';

angular.module('oldCountryApp')
    .controller('CardProductTypeController', function ($scope, CardProductType) {
        $scope.cardProductTypes = [];
        $scope.loadAll = function() {
            CardProductType.query(function(result) {
               $scope.cardProductTypes = result;
            });
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            CardProductType.get({id: id}, function(result) {
                $scope.cardProductType = result;
                $('#deleteCardProductTypeConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            CardProductType.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteCardProductTypeConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.cardProductType = {card_band: null, card_product_type_code: null, card_product_type_name: null, card_scheme_id: null, card_scheme_name: null, loading_date: null, scheme: null, debit_credit: null, display_flag: null, id: null};
        };
    });
