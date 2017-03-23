<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
   <title>秒杀详情</title>
   <%@include file="common/head.jsp" %>
</head>
<body>
	<div class="container">
		<div class="panel panel-default text-center">
			<div class="panel-heading">
				<h2>${seckill.name }</h2>
			</div>
			<div class="panel-body">
				<h2 class="text-danger">
					<span class="glyphicon glyphicon-time"></span>
					<span class="glyphicon" id="seckill-box"></span>
				</h2>
			</div>
		</div>
		<div class="panel-body"></div>
	</div>
	
	<!-- 登录弹出 -->
	<div id="killPhoneModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone"></span>秒杀电话：
					</h3>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" name="killPhone" id="killPhoneKey" placeholder="输入手机号码" class="form-control">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<span id="killPhoneMessage" class="glyphicon"></span>
					<button type="button" id="killPhoneBtn" class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span>
						提交
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<script type="text/javascript" src="/seckill/resources/script/seckill.js"></script>
<script type="text/javascript">
	$(function(){
		seckill.detail.init({
			seckillId:'${seckill.seckillId}',
			startTime:'${seckill.startTime.time}',
			endTime:'${seckill.endTime.time}'
		});
	});
	
</script>
</html>
