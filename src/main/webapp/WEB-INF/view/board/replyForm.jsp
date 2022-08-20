<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<main>
<!-- '게시글 id 님의 글에 댓글쓰기'로 수정 -->
<h2 id="headertitle" style="margin-bottom:20px">댓글 작성하기</h2>

<div class="d-flex justify-content-center mb-3">

	<form action="/board/replyFormProc" method="post" enctype="multipart/form-data">
 
	  <div class="p-2 input-group mb-3">
	    <div class="input-group-prepend">
	      <span class="input-group-text">작성자</span>
	    </div>
	    <input type="text" id="id" name="id"  class="form-control" readonly value="${id}">
	  </div>
	 
	  <div class="p-2 input-group mb-3">
	     <div class="input-group-prepend">
	      <span class="input-group-text">제목</span>
	    </div>
	    <input type="text" id="title" name="title" class="form-control" placeholder="re:${board.title}">
	  </div>
	  
	   <div class="p-2 input-group mb-3">
	    <div class="input-group-prepend">
	      <span class="input-group-text">글내용</span>
	    </div>
	    <input type="text" id="content" name="content" class="form-control">
	  </div>
	  
	   <div class="p-2 input-group mb-3">
	    <div class="input-group-prepend">
	      <span class="input-group-text">첨부파일</span>
	    </div>
		<input type="file" id="filename" name="filename" class="form-control">
	  </div>
	  
	 <input type="submit" class="btn btn-primary" value="댓글쓰기"/>
	 <input type="button" class="btn btn-primary" onclick="location.href='/board/list'" value="취소"/>
	 
	 <c:choose>
	 <c:when test="${board.groupid eq 0}"><input hidden type="text" name="groupid" value="${board.idx}"></c:when>
	 </c:choose>
	 
	 <input hidden type="text" name="depth" value="${board.depth+1}">
	 
	</form>
	
</div>
</main>
</html>