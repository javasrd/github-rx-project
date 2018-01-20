
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
	    	code: {
	            validators: {
	                notEmpty: {
	                    message: "用药剂量单位编码不能为空"
	                },
	                stringLength: {
                        max: 30,
                        message: '长度不能超过30个字符'
                    },
	            }
	        },
	        name: {
	            validators: {
	                notEmpty: {
	                    message: "用药剂量单位名称不能为空"
	                },
	                regexp: {
		                regexp: "^[\u4e00-\u9fa5A-Za-z0-9_\\s+\\\\\/]+$",
		                message: "请勿输入特殊符号"
	                },
	                stringLength: {
                        max: 50,
                        message: '长度不能超过50个字符'
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
function selectDetails(id){
	resetFun();
	var url = "back/dict-dose-unit/selectUpdateById";
	var params = {"id":id};
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null){
			var resp = $.parseJSON(res);
			if(resp.result_code=="success"){
				$("#edit-dict-dose-unit-li").removeClass("hide");
				var doseUnit =resp.dictDoseUnit;
				$("#dose-unit-id").val(doseUnit.id);//ID
				$("#dose-unit-code").val(doseUnit.code);//编码
				$("#dose-unit-name").val(doseUnit.name);//名称
				
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
 * 验证用户名是否存在
 */
function remoteValid(){
	var valid = false;
	var url = "back/dict-dose-unit/checkValid";
	var id = $("#dose-unit-id").val();
	var code = $("#dose-unit-code").val();
	var name = $("#dose-unit-name").val();
	var params = {"id":id, "name":name};
	//util.loading();
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null && res!=""){
			var obj = $.parseJSON(res);
			valid = obj.valid;
		}
	});
	return valid;
}
/*
 * 保存内容
 */
function saveFun(){
	var url = null;
	var id = $("#dose-unit-id").val();
	if(id==null || id==""){
		/*var valid = remoteValid();
		if(!valid){
			util.message("用户名已存在！");
			return false;
		}*/
		url = "back/dict-dose-unit/insert";
	}else{
		url = "back/dict-dose-unit/updateById";
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
 * 删除信息AJAX请求（逻辑删除）
 */
function deleteInfoAjaxRequest(id){
	var url = "back/dict-dose-unit/deleteById";
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
 * 单个删除
 */
function deleteInfoFun(id){
	util.delConfirm("确认删除？", id, "deleteInfoAjaxRequest");
}

/*
 * 重新加载当前菜单内容
 */
function reloadInfoFun(){
	//操作成功后重新加载
	var href = "back/dict-dose-unit/selectItems?pagehelperFun=clickPageBtnRequestFun";
	parent.window.iframeLoading(href);//调用主页面中的在iframe中加载内容的方法
}

/*
 * 点击页面中的页码执行此函数
 * 		函数功能：根据页码数请求当前页内容
 */
function clickPageBtnRequestFun(params){
	var action = "back/dict-dose-unit/selectItems";
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
    $("#dict-dose-unit-table tbody input[type='checkbox']").each(function(){
    	if(!$(this).prop("checked")){
    		flag = false;
    	}
    });
    if(flag){
    	$("#dict-dose-unit-table thead input[type='checkbox']").prop('checked', true);
    }else{
    	$("#dict-dose-unit-table thead input[type='checkbox']").prop('checked', false);
    }
}
/*
 * 点击列表中All复选框时，列表全选或反选
 */
function checkAll(obj){
	$("#dict-dose-unit-table tbody input[type='checkbox']").prop('checked', $(obj).prop('checked'));
}

/**
 * 点击添加按钮显示添加编辑选项卡
 */
$("#add-dict-dose-unit-btn").click(function(){
	$("#edit-dict-dose-unit-li").removeClass("hide");
	$('#tabs-243687 a[href="#tab-2"]').tab('show');
});

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
	$("#edit-dict-dose-unit-li").addClass("hide");
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