/* globals $ */
'use strict';

angular.module('oldCountryApp')
    .directive('oldCountryAppPager', function() {
        return {
            templateUrl: 'scripts/components/form/pager.html'
        };
    });
