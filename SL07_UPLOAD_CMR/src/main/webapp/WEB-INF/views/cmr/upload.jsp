<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" type="image/x-icon" href="images/SiSt.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<style>
 span.material-symbols-outlined{
    vertical-align: text-bottom;
 }  
</style>
</head>
<body>
<header>
  <h1 class="main"><a href="#" style="position: absolute;top:30px;">KenIk HOme</a></h1>
  <ul>  
    <li><a href="#">로그인</a></li>
    <li><a href="#">회원가입</a></li>
  </ul>
</header>
<div>
  <xmp class="code"> 
  	upload.jsp
  </xmp>
  
  <!-- 인코딩 multipart/form-data 이거 없으면 400번 에러 뜸 -->
   <form action="" method="post" enctype="multipart/form-data">
    <div><input type="text" name="output" value="hello world!"></div>
    <div><input type="file" name="attach"></div>
    <div><input type="submit"></div>
    <!-- 밑은 필요 없지만(필터 주석처리 해놨기 때문) 넣어두는 토큰  -->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">      
  </form>
  
</div> 
</body>
</html>







 

