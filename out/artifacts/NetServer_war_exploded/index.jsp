<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/5/8
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$DHCPServer$</title>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/websocket.js"></script>
    <script>
      $(function () {
          initWS();
          connect();
      })
    </script>
  </head>
  <body>
  <div class="wrapper">
    <div class="header">
      <h1 class="logo">地址分配</h1>

    <div class="clock" data-duration="0">00:00:00</div>
  </div>
  <div class="content">
    <div class="paster">
      <a class="clear">清空</a>
      <div class="list" id="recvDiv">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
          <thead>
          <tr>
            <th width="40px">序号</th>
            <th width="120px">服务器</th>
            <th width="150px">客户MAC</th>
            <th width="120px">分配IP地址</th>
            <th width="120px">子网掩码</th>
            <th width="120px">默认网关</th>
            <th width="120px">域名服务器</th>
            <th width="100px">租用时间</th>
            <th width="100px">Offer时间</th>
            <th width="80px">Offer次数</th>
            <th width="80px">Reply时间</th>
            <th width="80px">Reply次数</th>
            <th width="80px">Inform次数</th>
            <th width="95px">Release次数</th>
            <th width="95px">Decline次数</th>
            <th width="80px">更新次数</th>
         </tr>
          </thead>
         <tbody>
          <tr id="mac"><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>

         </tbody>
       </table>
      </div>
  </div>
  </div>
  <div class="footer">
    <p>陆军工程大学指挥控制工程学院</p>
    <p>地址：江苏省南京市海福巷1号，电话：025-80824376，邮编：210007</p>
  </div>

  </body>
</html>
