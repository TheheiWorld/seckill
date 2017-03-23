var seckill={
	url :{
		now : function (){
			return "/seckill/time/now";
		},
		exposer:function(seckillId){
			return "/seckill/"+seckillId+"/exposer";
		},
		execution:function(seckillId,md5){
			return "/seckill/"+seckillId + "/" + md5 + "/execution";
		}
		
	},
	//执行秒杀
	handleSeckill:function(seckillId,node){
		var button = '<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>';
		node.hide().html(button);
		$.post(seckill.url.exposer(seckillId),{},function(result){
			if(result && result['success']){
				var exposer = result['data'];
				//是否开启
				if(exposer['exposed']){
					//执行秒杀
					var MD5 = exposer['md5'];
					var killUrl = seckill.url.execution(seckillId, MD5);
					
					//绑定一次点击事件，可以防止重复点击
					$("#killBtn").one("click",function(){
						//执行秒杀请求
						//禁用按钮
						$(this).addClass('disabled');
						//发送post请求
						$.post(killUrl,{},function(result){
							if(result && result['success']){
								var killResult = result['data'];
								var state = killResult['state'];
								var stateInfo = killResult['stateInfo'];
								//显示秒杀结果
								var resultMessage = '<span class="label label-success">'+stateInfo+'</span>';
								node.html(resultMessage);
								
							}
						});
					});
					node.show();
				}else{
					var now = exposer['now'];
					var start = exposer['start'];
					var end = exposer['end'];
					seckill.countdown(seckillId, now, start, end);
				}
			}
		});
	},
	validatePhone:function(phone){
		if(phone && phone.length == 11 && !isNaN(phone)){
			return true;
		}else {
			return false;
		}
	},
	//秒杀倒计时
	countdown:function (seckillId,nowTime,startTime,endTime){
		var seckillBox = $("#seckill-box");
		if(nowTime > endTime){
			seckillBox.html("秒杀结束");
		} else if (nowTime < startTime){
			//秒杀未开始
			var killTime = new Date(startTime + 1000);
			seckillBox.countdown(killTime,function(event){
				var format = event.strftime('秒杀倒计时： %D天 %H时 %M分 %s秒');
				seckillBox.html(format);
			}).on('finish.countdown',function(){
				seckill.handleSeckill(seckillId,seckillBox);
			});
		} else {
			seckill.handleSeckill(seckillId,seckillBox);
		}
	},
	detail:{
		init:function(params){
			var killPhone = $.cookie('killPhone');
			
			if(!seckill.validatePhone(killPhone)){
				var killPhoneModal = $("#killPhoneModal");
				killPhoneModal.modal({
					show:true,
					backdrop:'static',
					keyboard:false
				});
				$("#killPhoneBtn").click(function(){
					var inputPhone = $("#killPhoneKey").val();
					if(seckill.validatePhone(inputPhone)){
						$.cookie('killPhone',inputPhone,{expires:7,path:'/seckill'});
						window.location.reload();
					}else{
						var message = '<label class="label label-danger">号码不正确</label>';
						$("#killPhoneMessage").hide().html(message).show(300);
					}
				});
			}
			
			var startTime = params['startTime'];
			var endTime = params['endTime'];
			var seckillId = params['seckillId'];
			//秒杀倒计时
			$.get(seckill.url.now(),{},function (result){
				if(result && result['success']){
					var nowTime = result['data'];
					seckill.countdown(seckillId, nowTime, startTime, endTime);
				}else {
				}
			});
		}
	}
}