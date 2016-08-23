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

app.controller('ImageCtrl', function ($scope, $http) {

    $scope.modelFormHeader = '';

    $scope.typeOfs = ['VOID', 'FUNCTION', 'ACTION'];

    $scope.currentImage = {
        description: '',
        typeOf:''
    };
    
    $scope.images = {};

    pageSize = 10;
    $scope.currentPage = 1;

    $scope.newImage = function () {
        $scope.currentImage = {
            description: 'Наименование',
            scrFile: '',
            typeOf: 'VOID'
        };
        $('#scrFile').val('');
    };

    $scope.deleteImage = function (image) {
        $http({
            method: "DELETE",
            url: "/api/image/" + image.id
        }).then(function (response) {
            $scope.newImage();
            $scope.loadImages($scope.currentPage);
        }), function (error) {
            var a = 1;
        };
    };

    $scope.saveImage = function (image) {
        var fd = new FormData();
        fd.append('file', $scope.scrFile);
        fd.append('description', image.description);
        fd.append('imageId', image.id);
        fd.append('typeOf', image.typeOf);

        $http.post("/api/image/create", fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).success(function (data) {
            $scope.newImage();
            $scope.loadImages($scope.currentPage);
            $('#scrFile').val('');
        }).error(function (data) {
            var a = 1;
        });
    };

    

    $scope.setCurrentImage= function (title, image) {
        $scope.currentImage = angular.copy(image);
        $scope.modelFormHeader = title;
    };

    $scope.getPages = function () {
        if ($scope.scripts && $scope.images.size) {
            var pages = Math.floor($scope.images.size / pageSize);
            pages = pages + ($scope.images.size % pageSize == 0 ? 0 : 1);
            pages = pages <= 1 ? 0 : pages;
            return new Array(pages);
        } else {
            return new Array(0);
        }
    };

    $scope.loadImagess = function (page) {
        if (page) {
            $scope.currentPage = page;
        } else {
            page = 1;
        }
        $http({
            method: "GET",
            url: "/api/image/list/" + page + "?pageSize=" + pageSize
        }).then(function (response) {
            $scope.images = response.data;
            $('.loader').hide();
            $('.data').show();
            $scope.getPages();
        }), function (error) {
            var a = 1;
        };
    };

    $scope.loadImagess(1);


});

function activeMenu(menu) {
    $('#' + menu).removeClass('alert-warning');
    $('#' + menu).addClass('alert-success');
    $('#' + menu).addClass('active');
}