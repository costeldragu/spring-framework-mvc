'use strict';

productsApp.controller('ProductListController',
    function ProductListController($scope, $http) {

        $http({
            method: 'GET',
            url: '/api/v1/products/all'
        }).then(function successCallback(response) {
            $scope.products = response.data;
        });


    });