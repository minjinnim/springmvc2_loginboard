<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<main>
<div class="d-flex justify-content-center mb-3">
	<form action="/board/writeFormProc" method="post" enctype="multipart/form-data">
	 
	  <div class="p-2 input-group mb-3">
	    <div class="input-group-prepend">
	      <span class="input-group-text">작성자</span>
	    </div>
	     <input type="text" id="writeName" name="writeName"  class="form-control" placeholder="작성자" readonly value="${id}">
	  </div>
	 
	  <div class="p-2 input-group mb-3">
	     <div class="input-group-prepend">
	      <span class="input-group-text">글제목</span>
	    </div>
	    <input type="text" id="title" name="title" class="form-control" placeholder="글제목">
	  </div>
	  
	   <div class="p-2 input-group mb-3">
	    <div class="input-group-prepend">
	      <span class="input-group-text">글내용</span>
	    </div>
	    <textarea class="form-control" id="content" name="content" wrap="virtual" placeholder="내용을 입력하세요"></textarea>
	  </div>
	  
	   <div class="p-2 input-group mb-3">
	    <div class="input-group-prepend">
	      <span class="input-group-text">첨부파일</span>
	    </div>
	    <input type="file" id="filename" name="filename" class="form-control">
	  </div>
	  
	<input type="submit" class="btn btn-primary" value="글쓰기"/>
	<input type="button" class="btn btn-primary" onclick="location.href='/board/list'" value="취소"/>
	
	</form>
</div>
</div>
</main>
