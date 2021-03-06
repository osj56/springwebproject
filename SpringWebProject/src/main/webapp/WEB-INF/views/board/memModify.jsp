<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="resources/vendor/bootstrap/login_css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="sources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="resources/vendor/login_font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<form:form action="${cp}/memModify" method="post" commandName="member">
	<c:forEach items="${modify}" var="row">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">회원정보 수정</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" >
                            <fieldset>
                                <div class="form-group">
                                	
                                   <td>ID <input class="form-control" placeholder="ID" name="memId" type="ID" value="${row.memId}"></td>
                                </div>
                                
                        		<div class="form-group">
                                   <td>Password <input class="form-control" placeholder="Password" name="memPw" type="password" ></td>
                                </div>
                                
                                 <div class="form-group">
                                   <td>Email</td> <input class="form-control" placeholder="Email" name="memMail" type="Email"">
                                </div>
                                
                                <!-- Change this to a button or input when using this as a form -->
                                
                                <button type="submit" class="btn btn-lg btn-success btn-block"  >확인</button>
         						<a class="btn btn-lg btn-success btn-block"  href="${cp}/">메인</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
</form:form>
    <!-- jQuery -->
    <script src="resources/vendor/login_jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="resources/vendor/bootstrap/login_js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="resources/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="resources/dist/js/sb-admin-2.js"></script>

</body>

</html>
