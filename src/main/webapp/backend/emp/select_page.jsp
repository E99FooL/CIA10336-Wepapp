<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>文章管理: 首頁</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>文章管理: 首頁</h3><h4>( MVC )</h4></td></tr>
</table>

<p>這是文章管理的首頁</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllArticle.jsp'>列出</a> 所有文章。<br><br></li>

  <li>
      <FORM METHOD="post" ACTION="article.do">
          <b>輸入文章編號 (如 1):</b>
          <input type="text" name="acId">
          <input type="hidden" name="action" value="getOne_For_Display">
          <input type="submit" value="送出">
      </FORM>
  </li>

  <li>
      <FORM METHOD="post" ACTION="article.do">
          <b>選擇文章標題:</b>
          <select size="1" name="acId">
              <%-- 這裡將填入所有文章標題的選項 --%>
              <c:forEach var="articleVO" items="${articleSvc.all}" >
                  <option value="${articleVO.acId}">${articleVO.acTitle}</option>
              </c:forEach>
          </select>
          <input type="hidden" name="action" value="getOne_For_Display">
          <input type="submit" value="送出">
      </FORM>
  </li>
</ul>

<h3>文章管理</h3>

<ul>
  <li><a href='addArticle.jsp'>新增</a> 一篇新文章。</li>
</ul>

</body>
</html>
