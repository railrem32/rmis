<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html ng-app="app" ng-controller="FilterCtrl">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Фильтры</title>
    <%@include file="/WEB-INF/pages/jspf/head.jspf" %>
</head>
<body>
<div class="container" >

    <div class="row">
        <div class="col-sm-3">
            <%@include file="/WEB-INF/pages/jspf/menu.jspf" %>
        </div>
        <div class="col-sm-9">
            <button type="button" class="btn btn-success data" data-toggle="modal" data-target="#editForm" ng-click="modelFormHeader = 'Создание';newFilter()">Добавить</button>
            <div class="loader"></div>
            <table class="table table-hover data" >
                <thead>
                <tr>
                    <th>Наименование фильтра</th>
                    <th>Класс реализации</th>
                    <th>Действия</th>
                </tr>
                </thead>
                <tbody>

                <tr ng-repeat="filter in filters.filters">
                    <td>{{filter.name}}</td>
                    <td>{{filter.className}}</td>
                    <td>
                        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#editForm" ng-click="setCurrentFilter('Редактирование', script)">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </button>
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm" ng-click="setCurrentFilter('Удаление', script)">
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>

            <div id="deleteForm" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Удаление</h4>
                        </div>
                        <div class="modal-body">
                            <p>Подтвердите удаление фильтра <b>{{currentFilter.name}}</b></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-info" data-dismiss="modal" ng-click="deleteScript(currentFilter)">Удалить</button>
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
                                <label for="scrName">Наименование фильтра</label>
                                <input type="text" class="form-control" ng-model="currentFilter.name" id="scrName" required/>
                            </div>

                            <div class="form-group">
                                <label for="scrName">Класс реализации</label>
                                <select class="form-control" ng-model="currentFilter.className" ng-options="className for className in classNames"></select>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="active btn btn-success" data-dismiss="modal" ng-click="saveFilter(currentFilter)">Cохранить</button>
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
        activeMenu('filters');
    });
</script>
