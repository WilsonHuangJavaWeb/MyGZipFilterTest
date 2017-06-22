<%@ page import="java.net.URL" %>
<%@ page import="java.net.URLConnection" %>
<%@ page import="java.text.NumberFormat" %><%--
  Created by IntelliJ IDEA.
  User: ki264
  Date: 2017/6/21
  Time: 下午 04:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    public void test(JspWriter out, String url) throws Exception {

        // 模拟支持 GZIP 的浏览器
        URLConnection connGzip = new URL(url).openConnection();
        connGzip.setRequestProperty("Accept-Encoding", "gzip");
        int lengthGzip = connGzip.getContentLength();

        // 模拟不支持 GZIP 的浏览器
        URLConnection connCommon = new URL(url).openConnection();
        int lengthCommon = connCommon.getContentLength();

        double rate = new Double(lengthGzip) / lengthCommon;

        out.println("<table>");
        out.println("	<tr>");
        out.println("		<td colspan=3>网址: " + url + "</td>");
        out.println("	</tr>");
        out.println("	<tr>");
        out.println("		<td>压缩后:" + lengthGzip + " byte</td>");
        out.println("		<td>压缩前:" + lengthCommon + " byte</td>");
        out.println("		<td>比率:" + NumberFormat.getPercentInstance().format(rate) + "</td>");
        out.println("	</tr>");
        out.println("</table>");
    }
%>
<%
    String[] urls = {"http://localhost:8080/MyGZipFilterTest/dojo/dojo.js", "http://localhost:8080/MyGZipFilterTest/winter.jpg", "http://www.baidu.com", "http://www.google.cn",};
    for (String url : urls) {
        test(out, url);
    }
%>

</body>
</html>
