'use strict';

angular.module('oldCountryApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('currency', {
                parent: 'entity',
                url: '/currencys',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Currencys'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/currency/currencys.html',
                        controller: 'CurrencyController'
                    }
                },
                resolve: {
                }
            })
            .state('currency.detail', {
                parent: 'entity',
                url: '/currency/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Currency'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/currency/currency-detail.html',
                        controller: 'CurrencyDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Currency', function($stateParams, Currency) {
                        return Currency.get({id : $stateParams.id});
                    }]
                }
            })
            .state('currency.new', {
                parent: 'currency',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/currency/currency-dialog.html',
                        controller: 'CurrencyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {code: null, description: null, country: null, id: null};
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('currency', null, { reload: true });
                    }, function() {
                        $state.go('currency');
                    })
                }]
            })
            .state('currency.edit', {
                parent: 'currency',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/currency/currency-dialog.html',
                        controller: 'CurrencyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Currency', function(Currency) {
                                return Currency.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('currency', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
