<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="myform" method ="post" action="/myapp1/board/save">
	번호 : <input type="text" name="seq"> <br/>
	제목 : <input type="text" name="title"> <br/>
	작성자 : <input type="text" name="writer"> <br/>
	내용 : <input type="text" name="contents"> <br/>
	작성일 : <input type="text" name="wdate"> <br/>
	조회수 : <input type="text" name="hit"> <br/>
	<button>등록</button>
	<!-- button tag: submit 호출해서 form 태그에 지정된 action과 method를 참고로 해서 서버로 보낸다 -->
</form>
</body>
</html>