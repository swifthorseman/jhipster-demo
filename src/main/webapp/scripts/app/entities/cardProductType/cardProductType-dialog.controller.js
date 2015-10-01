'use strict';

angular.module('oldCountryApp').controller('CardProductTypeDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'CardProductType',
        function($scope, $stateParams, $modalInstance, entity, CardProductType) {

        $scope.cardProductType = entity;
        $scope.load = function(id) {
            CardProductType.get({id : id}, function(result) {
                $scope.cardProductType = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('oldCountryApp:cardProductTypeUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.cardProductType.id != null) {
                CardProductType.update($scope.cardProductType, onSaveFinished);
            } else {
                CardProductType.save($scope.cardProductType, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
