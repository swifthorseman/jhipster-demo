'use strict';

angular.module('oldCountryApp')
    .factory('CardProductType', function ($resource, DateUtils) {
        return $resource('api/cardProductTypes/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.loading_date = DateUtils.convertLocaleDateFromServer(data.loading_date);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.loading_date = DateUtils.convertLocaleDateToServer(data.loading_date);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.loading_date = DateUtils.convertLocaleDateToServer(data.loading_date);
                    return angular.toJson(data);
                }
            }
        });
    });
