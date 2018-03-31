'use strict';

productsApp.controller('UpdateProductController',
    function UpdateProductController($scope, $http) {

    $scope.product = {
        id:angular.element('#productId').val(),
        category:angular.element('#Category').val(),
        name:angular.element('#name').val(),
        description:angular.element('#description').val(),
        price:angular.element('#price').val()
    }

        $scope.saveProduct = function saveProduct(product, button) {

            $http({
                method : 'POST',
                url    : '/api/v1/product/update',
                data   : product
            }).then(function (result) {
                window.location = "/"
            })
        }


    });