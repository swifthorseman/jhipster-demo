 'use strict';

angular.module('oldCountryApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-oldCountryApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-oldCountryApp-params')});
                }
                return response;
            },
        };
    });