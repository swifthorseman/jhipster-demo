/* globals $ */
'use strict';

angular.module('oldCountryApp')
    .directive('oldCountryAppPagination', function() {
        return {
            templateUrl: 'scripts/components/form/pagination.html'
        };
    });
