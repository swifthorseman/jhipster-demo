'use strict';

angular.module('oldCountryApp')
    .factory('Currency', function ($resource, DateUtils) {
        return $resource('api/currencys/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
