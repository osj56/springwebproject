<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Write something else you want</title>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
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
         	<td>${row.viewCnt}</td>
           
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
        
          <c:if test="${row.writer eq login}">
        <div class="card-body">
            <a href="${cp}/board/boardModify?cnt=${row.cnt}" class="btn btn-outline-secondary btn-sm " role="button">수정</a>
            <a href="${cp}/board/boardDelete?cnt=${row.cnt}" class="btn btn-outline-secondary btn-sm " role="button">삭제</a>
        </div>
        </c:if>
    </div>
</div>
 
    </tbody>
</table>
</div>

<div class="container">
    <form:form action="${cp}/comment/insert" method="post">
    <br><br>
        <div>
            <div>
                <span><strong>Comments</strong></span> <span id="cCnt"></span>
            </div>
            <div>
                <table class="table">                    
                    <tr>
                        <td>
                            <textarea style="width: 1100px" rows="3" cols="30" id="content" name="content" placeholder="댓글을 입력하세요"></textarea>
                            <br>
                            <div>
                               <input type="hidden" name="cnt" value="${row.cnt}">
                                <button type="submit"  class="btn pull-right btn-success">등록</button>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
             
    </form:form>
</div>
</c:forEach>
<c:forEach items="${comment}" var="comment" varStatus="status">
<div class="container">
    <form id="commentListForm" name="commentListForm" method="post">
        <div id="commentList">
        
        <table class="table">
         <tr>
         <td>
       <span><strong>[${comment.name}]</strong></span> 
 
      <p> ${comment.content}</p>
      <c:if test="${comment.name eq login}">
        <a  class="btn btn-outline-secondary btn-sm " data-toggle="modal" data-target="#exampleModal" role="button">수정</a>
        <a href="${cp}/comment/delete?commentcnt=${comment.commentcnt}" class="btn btn-outline-secondary btn-sm " role="button" >삭제</a>
        </c:if>
        </td>
        </tr>
        </table>
        </div>
        
   
    </form>

</div>
<c:forEach items="${detail}" var="row"  >
 <form:form action="${cp}/comment/modify" method="post" >
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">댓글수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">수정내용</label>
            <input type="text" class="form-control" name="content" value="${comment.content }">
          </div>
         
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
           <input type="hidden" name="commentcnt" value="${row.cnt}">
        <button type="submit" class="btn btn-primary">수정</button>
      </div>
    </div>
  </div>
</div>
</form:form>
</c:forEach>
</c:forEach>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>


