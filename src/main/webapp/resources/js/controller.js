var app = angular.module('app', []);

app.directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]);

app.controller('RootCtrl', function ($scope, $http) {

    pageSize = 5;
    $scope.currentPage = 1;

});

function activeMenu(menu) {
    $('#' + menu).removeClass('alert-warning');
    $('#' + menu).addClass('alert-success');
    $('#' + menu).addClass('active');
}