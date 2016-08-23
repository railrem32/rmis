<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html ng-app="app" ng-controller="RootCtrl">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Какая-то страница</title>

        <%@include file="/WEB-INF/pages/jspf/head.jspf" %>

    </head>
    <body>
        <div class="container" >

            <div class="row">
                <div class="col-sm-3">
                    <%@include file="/WEB-INF/pages/jspf/menu.jspf" %>
                </div>
                <div class="col-sm-9">

                    Контент
                </div>
            </div>

        </div>
    </body>
    <script>
                $(document).ready(function () {
                    activeMenu('pageone');
                });
    </script>
</html>
