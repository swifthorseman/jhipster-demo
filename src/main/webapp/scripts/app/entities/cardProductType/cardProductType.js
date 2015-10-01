'use strict';

angular.module('oldCountryApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('cardProductType', {
                parent: 'entity',
                url: '/cardProductTypes',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CardProductTypes'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/cardProductType/cardProductTypes.html',
                        controller: 'CardProductTypeController'
                    }
                },
                resolve: {
                }
            })
            .state('cardProductType.detail', {
                parent: 'entity',
                url: '/cardProductType/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'CardProductType'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/cardProductType/cardProductType-detail.html',
                        controller: 'CardProductTypeDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'CardProductType', function($stateParams, CardProductType) {
                        return CardProductType.get({id : $stateParams.id});
                    }]
                }
            })
            .state('cardProductType.new', {
                parent: 'cardProductType',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/cardProductType/cardProductType-dialog.html',
                        controller: 'CardProductTypeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {card_band: null, card_product_type_code: null, card_product_type_name: null, card_scheme_id: null, card_scheme_name: null, loading_date: null, scheme: null, debit_credit: null, display_flag: null, id: null};
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('cardProductType', null, { reload: true });
                    }, function() {
                        $state.go('cardProductType');
                    })
                }]
            })
            .state('cardProductType.edit', {
                parent: 'cardProductType',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/cardProductType/cardProductType-dialog.html',
                        controller: 'CardProductTypeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['CardProductType', function(CardProductType) {
                                return CardProductType.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('cardProductType', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
