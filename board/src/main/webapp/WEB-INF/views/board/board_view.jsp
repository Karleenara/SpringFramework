<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kosa.myapp3.common.*" %>
<%@page import="com.kosa.myapp3.board.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>  
    
</head>
<body>
<%
String pg=StringUtil.nullToValue(request.getParameter("pg"),"0");
String seq=StringUtil.nullToValue(request.getParameter("seq"),"");
BoardDto boardDto = (BoardDto)request.getAttribute("boardDto");
%>
<form name="myform" id="myform">
	<input type="hidden" name="pg"       id="pg"       value="<%=pg%>">
	<input type="hidden" name="seq"      id="seq"      value="<%=seq%>">
	<input type="hidden" name="mode"     id="mode" >
	<input type="hidden" name="group_id" id="group_id" value="<%=boardDto.getGroup_id()%>">
	<input type="hidden" name="depth"    id="depth"    value="<%=boardDto.getDepth()%>">
	<input type="hidden" name="g_level"  id="g_level"  value="<%=boardDto.getG_level()%>">
	
	
<%@include file="../include/nav.jsp" %>


<div class="container mt-3">
  <h3>Navbar With Dropdown</h3>
  <p>This example adds a dropdown menu in the navbar.</p>
  

   <table class="table">
   	<colgroup>
   		<col width="30%">
   		<col width="*">
   	</colgroup>
    <tbody>
      <tr>
        <th>제목</td>
        <td><%=boardDto.getTitle()%></td>
      </tr>
      <tr>
        <th>작성자</td>
        <td>
        	<%=boardDto.getUserid()%>
        </td>
      </tr>
      <tr>
        <th>내용</td>
        <td style="word-break:break-all">
        	 <%=boardDto.getContents().replaceAll("\n", "<br/>") %>
        </td>
      </tr>
      <tr>
        <th>첨부파일</td>
        <td>
        	<a href="<%=request.getContextPath()%>/down?path=board&filename=<%=boardDto.getFilename1()%>" class="btn"><%=boardDto.getFilename1()%></a>
        </td>
      </tr>
      <tr>
        <th>첨부파일</td>
        <td>
        	<a href="<%=request.getContextPath()%>/down?path=board&filename=<%=boardDto.getFilename2()%>" class="btn"><%=boardDto.getFilename2()%></a>
        </td>
      </tr>
      <tr>
        <th>첨부파일</td>
        <td>
        	<a href="<%=request.getContextPath()%>/down?path=board&filename=<%=boardDto.getFilename3()%>" class="btn"><%=boardDto.getFilename3()%></a>
        </td>
      </tr>
      
    </tbody>
  </table>
 </div>


 <div class="container" style="text-align:right">
 	<button type="button" class="btn btn-primary" id="btnReply">답글</button>&nbsp;&nbsp;
	<button type="button" class="btn btn-primary" id="btnModify">수정</button>&nbsp;&nbsp;
	<button type="button" class="btn btn-primary" id="btnDelete">삭제</button>&nbsp;&nbsp;
	<button type="button" class="btn btn-primary" id="btnList">목록</button>
 </div>

  
</body>
</html>
<script>
$(()=>{
	$("#btnReply").click(()=>{
		$("#mode").val("reply");
		$("#myform").prop("action", 
				"<%=request.getContextPath()%>/board/reply");
		$("#myform").submit();
	});
	
	$("#btnModify").click(()=>{
		$("#mode").val("modify");
		$("#myform").prop("action", 
				"<%=request.getContextPath()%>/board/modify");
		$("#myform").submit();
	});
	

	$("#btnDelete").click(()=>{
		
		let param = $("#myform").serialize();
		//직렬화  -    multipart  아닐때 
		//FormData  - multipart  일때
		
		// seq=4&group_id=3 ............
		$.ajax({
			url:"<%=request.getContextPath()%>/board/delete",
			data:param,   
			dataType:"json"
		})
		.done((res)=>{
			if(res.result=="success")
			{
				alert("글이 삭제되었습니다.");
				location.href="<%=request.getContextPath()%>/board/list";
			}
			else
			{
				alert("실패하였습니다");
			}
		})
		.fail((res, status, error)=>{
			console.log( status );
			console.log( error );
		});
		
	});
	

})
</script>






