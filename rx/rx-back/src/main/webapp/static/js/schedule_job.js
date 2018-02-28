
//$(function(){
	
	bootstrapValidateFun();//启用验证
	
//});

/*
 * bootstrap验证
 */
function bootstrapValidateFun(){
	/*
	 * bootstrapValidator验证
	 */
	$("#save-form").bootstrapValidator({
	    message: "This value is not valid",
	    feedbackIcons: {
	        valid: "glyphicon glyphicon-ok",
	        invalid: "glyphicon glyphicon-remove",
	        validating: "glyphicon glyphicon-refresh"
	    },
	    
	    fields: {
	    	jobName: {
	            validators: {
	                notEmpty: {
	                    message: "任务名称不能为空"
	                },
	                stringLength: {
                        max: 30,
                        message: '长度不能超过30个字符'
                    },
	            }
	        },
	        jobGroup: {
	            validators: {
	                notEmpty: {
	                    message: "任务分组不能为空"
	                },
	                stringLength: {
                        max: 50,
                        message: '长度不能超过50个字符'
                    },
	            }
	        },
	        cronExpression: {
	            validators: {
	                notEmpty: {
	                    message: "任务运行时间表达式不能为空"
	                },
	                stringLength: {
                        max: 30,
                        message: '长度不能超过30个字符'
                    },
	            }
	        },
	        jobDesc: {
	            validators: {
	                notEmpty: {
	                    message: "任务描述不能为空"
	                },
	                /*regexp: {
		                regexp: "^[\u4e00-\u9fa5A-Za-z0-9_\\s+\\\\\/]+$",
		                message: "请勿输入特殊符号"
	                },*/
	                stringLength: {
                        max: 100,
                        message: '长度不能超过100个字符'
                    },
                    /*remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}  
                        url: "back/user/checkUsernameValid",//验证地址
                        message: "用户名已存在",//提示消息
                        delay :  1000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST',//请求方式
                        //自定义提交数据，默认值提交当前input value
                           //data: {
                    			//username: $("[name='username']").val(),
                    			//userid: $("[name='id']").val(),
                           //}
                        data: function(validator) {
                        	return {
                            	//password: $('[name="passwordNameAttributeInYourForm"]').val(),
                            	//whatever: $('[name="whateverNameAttributeInYourForm"]').val()
                        		username: $("[name='username']").val(),
                    			userid: $("[name='id']").val(),
                        	};
                        }
                    },*/
	            }   
	        },
	    }
	}).on('success.form.bv',function(e){
	    e.preventDefault();
	    saveFun();//验证通过保存内容
	});
}
	
/*
 * 查看详细信息
 */
function selectDetailsFun(id){
	resetFun();
	var url = "back/quartz/selectUpdateById";
	var params = {"id":id};
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null){
			var resp = $.parseJSON(res);
			if(resp.result_code=="success"){
				$("#edit-schedule-job-li").removeClass("hide");
				var job =resp.scheduleJob;
				$("#job-id").val(job.jobId);//ID
				$("#job-name").val(job.jobName);//任务名称
				//任务状态 1：任务中；2：暂停；3：停止
				if(job.jobStatus!=3){
					$("#job-name").attr("disabled", true);
				}else{
					$("#job-name").attr("disabled", false);
				}
				//$("#job-group").val(job.jobGroup);//任务分组
				$("#job-cron-expression-name").val(job.cronExpression);//任务运行时间表达式名称
				$("#job-cron-expression").val(job.cronExpression);//任务运行时间表达式
				$("#job-desc").val(job.jobDesc);//任务描述
				//任务状态 1：任务中；2：暂停；3：停止
				if(job.jobStatus!=3){
					$("#job-desc").attr("disabled", true);
				}else{
					$("#job-desc").attr("disabled", false);
				}
				
				$('#tabs-243687 a[href="#tab-2"]').tab('show');
				return;
			}
		}
		util.message("查询异常");
		
	});
}

/*
 * 保存内容提交
 */
$("#save-submit-btn").click(function(){
	$("#save-form").submit();
});

/*
 * 保存内容
 */
function saveFun(){
	var url = url = "back/quartz/update";
	var id = $("#job-id").val();
	if(id==null || id==""){
		url = "back/quartz/insert";
	}else{
		url = "back/quartz/update";
	}
	
	//util.loading();
	$("#save-form").ajaxSubmit({
		type:"post",
		url:url,
		data:{
			
		},
		success : function(res) {
			console.log(res);
			if(res!=null){
				var obj = $.parseJSON(res);
				if(obj.result_code=="success"){
					//操作成功后重新加载当前菜单内容
					reloadInfoFun();
				}else{
					util.message(obj.result_err_msg);
				}
			}
		},
	});
}

/*
 * 停止任务
 */
function deleteJobFun(id){
	util.delConfirm("确认停止任务？", id, "deleteJobAjaxRequest");
}
/*
 * 停止任务信息AJAX请求
 */
function deleteJobAjaxRequest(id){
	var url = "back/quartz/delete";
	var params = {"id":id};
	//util.loading();
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null && res!=""){
			var obj = $.parseJSON(res);
			if(obj.result_code=="success"){
				reloadInfoFun();
			}else{
				util.message(obj.result_err_msg);
			}
		}
		
	});
}

/*
 * 删除任务和记录
 */
function deleteJobAndRecordFun(id){
	util.delConfirm("确认删除任务和记录？", id, "deleteJobAndRecordAjaxRequest");
}
/*
 * 删除任务和记录信息AJAX请求
 */
function deleteJobAndRecordAjaxRequest(id){
	var url = "back/quartz/deleteRecord";
	var params = {"id":id};
	//util.loading();
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null && res!=""){
			var obj = $.parseJSON(res);
			if(obj.result_code=="success"){
				reloadInfoFun();
			}else{
				util.message(obj.result_err_msg);
			}
		}
		
	});
}

/*
 * 启动任务信息AJAX请求
 */
function startJobFun(id){
	var url = "back/quartz/start";
	var params = {"id":id};
	//util.loading();
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null && res!=""){
			var obj = $.parseJSON(res);
			if(obj.result_code=="success"){
				reloadInfoFun();
			}else{
				util.message(obj.result_err_msg);
			}
		}
		
	});
}

/*
 * 暂停任务信息AJAX请求
 */
function pauseJobFun(id){
	var url = "back/quartz/pause";
	var params = {"id":id};
	//util.loading();
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null && res!=""){
			var obj = $.parseJSON(res);
			if(obj.result_code=="success"){
				reloadInfoFun();
			}else{
				util.message(obj.result_err_msg);
			}
		}
		
	});
}

/*
 * 继续任务（恢复任务）信息AJAX请求
 */
function resumeJobFun(id){
	var url = "back/quartz/resume";
	var params = {"id":id};
	//util.loading();
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null && res!=""){
			var obj = $.parseJSON(res);
			if(obj.result_code=="success"){
				reloadInfoFun();
			}else{
				util.message(obj.result_err_msg);
			}
		}
		
	});
}

/*
 * 重新加载当前菜单内容
 */
function reloadInfoFun(){
	//操作成功后重新加载
	var href = "back/quartz/selectItems?pagehelperFun=clickPageBtnRequestFun";
	parent.window.iframeLoading(href);//调用主页面中的在iframe中加载内容的方法
}

/*
 * 点击页面中的页码执行此函数
 * 		函数功能：根据页码数请求当前页内容
 */
function clickPageBtnRequestFun(params){
	var action = "back/quartz/selectItems";
	params.clickPageBtn = true;
	//util.loading();
	$("#item-div").load(action, params, function(){
	});
}

/*
 * 点击列表中某个复选框时，全选或反选
 */
function checkOne(){
    
    var flag = true;
    $("#schedule-job-table tbody input[type='checkbox']").each(function(){
    	if(!$(this).prop("checked")){
    		flag = false;
    	}
    });
    if(flag){
    	$("#schedule-job-table thead input[type='checkbox']").prop('checked', true);
    }else{
    	$("#schedule-job-table thead input[type='checkbox']").prop('checked', false);
    }
}
/*
 * 点击列表中All复选框时，列表全选或反选
 */
function checkAll(obj){
	$("#schedule-job-table tbody input[type='checkbox']").prop('checked', $(obj).prop('checked'));
}

/**
 * 点击添加按钮显示添加编辑选项卡
 */
$("#add-schedule-job-btn").click(function(){
	$("#edit-schedule-job-li").removeClass("hide");
	$('#tabs-243687 a[href="#tab-2"]').tab('show');
});

/**
 * 任务运行时间表达式 onchange 事件
 * 		函数功能：选择任务运行时间表达式后获取任务运行时间表达式名称并设置到任务运行时间表达式名称的隐藏域中，提交时做为参数传到后台
 * @returns
 */
function setJobCronExpressionName(){
	var expressionName = $("#job-cron-expression option:selected").text();
	console.log("expressionName:"+expressionName);
	$("#job-cron-expression-name").val(expressionName);
}

/*
 * 重置form表单
 */
function resetFun(){
	$("#save-form").data('bootstrapValidator').destroy();//销毁bootstrapValidator验证
	bootstrapValidateFun();//启用验证
	//$('#save-form')[0].reset();
	$(":input","#save-form")  
	 .not(":button, :submit, :reset, :checkbox, :radio")  
	 .val("")
	 .removeAttr("checked")  
	 .removeAttr("selected");
	$("#edit-schedule-job-li").addClass("hide");
}

/*
 * 日期转字符串格式函数
 * 调用方法：date.format('yyyy-MM-dd HH:mm:ss');
 */
Date.prototype.format = function(format) {
    var date = {
           "M+": this.getMonth() + 1,
           "d+": this.getDate(),
           "h+": this.getHours(),
           "m+": this.getMinutes(),
           "s+": this.getSeconds(),
           "q+": Math.floor((this.getMonth() + 3) / 3),
           "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
           format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
           if (new RegExp("(" + k + ")").test(format)) {
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1
                         ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
           }
    }
    return format;
}