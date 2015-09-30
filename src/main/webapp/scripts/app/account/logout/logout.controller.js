'use strict';

angular.module('oldCountryApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
