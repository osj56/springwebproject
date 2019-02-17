<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


                                   <h1>채팅방</h1>
</head>
<body>
<div class="container">
    <form id="chatForm" name="chatForm" method="post">
     
     <table class="table table-striped">
  <thead>
    <tr>
     
      <th scope="col">사용자</th>
      <th scope="col">방이름</th>
      
    </tr>
  </thead>
  <tbody>
 
   
      <c:forEach items="${plist}" var="row" varStatus="status"  >
     
       <input type="hidden" name="roomname" value="${row.roomname}">
    <tr>

      <td scope="row">${row.chatuser}</td>

      
   	  
    
      <td><a href="${cp}/chat/chat?roomname=${row.roomname}" >${row.roomname}</a></td>
   	
    </tr>
    </c:forEach>
    
    
  </tbody>
  
</table>


<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
   
   <c:if test="${pageMaker.prev}">
    <li class="page-item disabled">
      <a class="page-link" href="chatRoom${pageMaker.makeQuery(pageMaker.startPage - 1)}" tabindex="-1" aria-disabled="true">Previous</a>
    </li>
   </c:if> 
    
    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    <li class="page-item"><a class="page-link" href="chatRoom${pageMaker.makeQuery(idx)}">${idx}</a></li>
    </c:forEach>
    
      <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    <li class="page-item">
      <a class="page-link" href="chatRoom${pageMaker.makeQuery(pageMaker.endPage + 1)}">Next</a>
    </li>
     </c:if> 
  </ul>
</nav>

            <a href='#' onClick='fn_write()' class="btn btn-success">방만들기</a>   
            <a class="btn btn-success"  href="${cp}/">메인</a>       
        </div>
        
   
    </form>
</div>


<script>
//글쓰기
function fn_write(){
    
    var form = document.getElementById("chatForm");
    
    form.action = "<c:url value='/chat/create'/>";
    form.submit();
    
}



</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>
