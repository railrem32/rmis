<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html ng-app="app" ng-controller="ImageCtrl">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Изображения</title>

        <%@include file="/WEB-INF/pages/jspf/head.jspf" %>

    </head>
    <body>
        <div class="container" >

            <div class="row">
                <div class="col-sm-3">
                    <%@include file="/WEB-INF/pages/jspf/menu.jspf" %>
                </div>
                <div class="col-sm-9">
                    <button type="button" class="btn btn-success data" data-toggle="modal" data-target="#editForm" ng-click="modelFormHeader = 'Создание';newImage()">Добавить</button>

                    <div class="loader"></div>
                    <table class="table table-hover data" >
                        <thead>
                            <tr>
                                <th>Наименование изображения</th>
                                <th>Тип функции</th>
                                <th>Действия</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr ng-repeat="image in images.images">
                                <td>{{image.description}}</td>
                                <td>{{image.typeOf}}</td>
                                <td>
                                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#editForm" ng-click="setCurrentImage('Редактирование', image)">
                                        <span class="glyphicon glyphicon-pencil"></span>
                                    </button>
                                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm" ng-click="setCurrentImage('Удаление', image)">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <ul class="pagination data">
                        <li ng-repeat="i in getPages() track by $index"><a href="#" ng-click="loadImages($index + 1)">{{$index + 1}}</a></li>
                    </ul>
                    

                    <div id="deleteForm" class="modal fade" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Удаление</h4>
                                </div>
                                <div class="modal-body">
                                    <p>Подтвердите удаление изображения <b>{{currentImage.description}}</b></p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-info" data-dismiss="modal" ng-click="deleteScript(currentImage)">Удалить</button>
                                    <button type="button" class="active btn btn-default" data-dismiss="modal">Отмена</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="editForm" class="modal fade " role="dialog">
                        <div class="modal-dialog">

                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">{{modelFormHeader}}</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="scrName">Наименование </label>
                                        <input type="text" class="form-control" ng-model="currentImage.description" id="scrName" required/>
                                    </div>

                                    <div class="form-group">
                                        <label for="scrName">Тип</label>
                                        <select class="form-control" ng-model="currentImage.typeOf" ng-options="typeOf for typeOf in typeOfs"></select>
                                    </div>

                                    <div class="form-group">
                                        <label for="scrFile">Файл:</label>
                                        <input type="file" class="form-control" file-model="scrFile" id="scrFile"/>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="active btn btn-success" data-dismiss="modal" ng-click="saveImage(currentImage)">Cохранить</button>
                                    <button type="button" class="btn btn-warningdefault" data-dismiss="modal">Закрыть</button>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

        </div>
    </body>
    <script>
                $(document).ready(function () {
                    activeMenu('images');
                });
    </script>
</html>
