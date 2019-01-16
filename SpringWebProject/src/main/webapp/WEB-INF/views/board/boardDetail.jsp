<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Write something else you want</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
 
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
 
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<h1>상세보기</h1>
</head>
<body>
 
<div class="container">
<table class="table table-bordered">
   
    <tbody>
         <c:forEach items="${detail}" var="row"  >
      <table class="table">
        <tr>
            <th class="success">글번호</th>
             <td>${row.cnt}</td>
            <th class="success">조회수</th>
            <td>></td>
        </tr>
           
         
        <tr>
            <th class="success">작성자</th>
            <td>${row.writer}</td>
            
        </tr>
         
       
         
        <tr>
            <th class="success">제목</th>
            <td colspan="3">${row.title }</td>
        </tr>
         
        <tr>
            <th class="success">글 내용</th>
            <td colspan="3">${row.content}</td>
        </tr>
        <div class="card-body">
            <a href="${cp}/board/boardModify?cnt=${row.cnt}" class="btn btn-outline-secondary btn-sm " role="button">수정</a>
            <a href="${cp}/board/boardDelete?cnt=${row.cnt}" class="btn btn-outline-secondary btn-sm " role="button">삭제</a>
        </div>
        
    </div>
</div>



     </c:forEach>
    



     
    </tbody>
</table>
</div>

</body>
</html>


