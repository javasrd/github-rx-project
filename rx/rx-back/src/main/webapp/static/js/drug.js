
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
	    	wareid: {
	            validators: {
	                notEmpty: {
	                    message: "药品ID（来自海典ERP）"
	                },
	                stringLength: {
                        max: 20,
                        message: '长度不能超过20个字符'
                    },
	            }
	        },
	        barcode: {
	            validators: {
	                notEmpty: {
	                    message: "条形码不能为空"
	                },
	                regexp: {
		                regexp: "^[\u4e00-\u9fa5A-Za-z0-9_\\s+\\\\\/]+$",
		                message: "请勿输入特殊符号"
	                },
	                /*stringLength: {
                        max: 20,
                        message: '长度不能超过20个字符'
                    },*/
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
	        abc: {
	            validators: {
	                notEmpty: {
	                    message: "助记码不能为空"
	                },
	                stringLength: {
                        max: 20,
                        message: '长度不能超过20个字符'
                    }
	            }
	        },
	        warename: {
	            validators: {
	                notEmpty: {
	                    message: "药品名称不能为空"
	                },
	                stringLength: {
                        max: 20,
                        message: '长度不能超过20个字符'
                    },
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
	var url = "back/drug/selectUpdateById";
	var params = {"id":id};
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null){
			var resp = $.parseJSON(res);
			if(resp.result_code=="success"){
				$("#edit-drug-li").removeClass("hide");
				var drug =resp.drug;
				$("#drug-id").val(drug.id);//ID
				$("#wareid").val(drug.wareid);//药品ID（来自海典ERP）
				$("#barcode").val(drug.barcode);//条形码
				$("#abc").val(drug.abc);//助记码
				$("#warename").val(drug.warename);//药品名称
				$("#waresimname").val(drug.waresimname);//通用名
				$("#warespec").val(drug.id);//规格
				$("#prod-addr").val(drug.prodAddr);//产地
				$("#producer").val(drug.producer);//生产厂家
				$("#wareunit").val(drug.wareunit);//单位
				//$("#status").val(drug.status);//在售状态
				$("#drug-status input:radio[name='status'][value='"+drug.status+"']").prop("checked",true);
				$("#saleprice").val(drug.saleprice);//售价
				$("#jl").val(drug.jl);//剂量
				$("#lc").val(drug.lc);//疗程
				$("#pc").val(drug.pc);//频次
				$("#yfyl").val(drug.yfyl);//用法
				$("#xuanx").val(drug.xuanx);//最小可出售包装单价
				$("#saleminspec").val(drug.saleminspec);//最小可出售包装规格
				$("#saleminunit").val(drug.saleminunit);//最小可出售包装剂量单位
				
				$('#tabs-243687 a[href="#tab-2"]').tab('show');
				return;
			}
		}
		util.message("查询异常");
		
	});
}

/*
 * 上传药品信息EXCEL文件提交
 * 		未用，改为TXT文本文件传输
 */
/*$("#upload-drug-btn").click(function(){
	
	var file = $("#drug-file").val();
	if(file==null || file==""){
		util.message("请选择需要导入的EXCEL文件");
		return false;
	}
	
	var url = "back/drug/upload-drug-file";
	$("#upload-file").ajaxSubmit({
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
});*/

/**
 * 初始化同步药品信息
 */
$("#init-sync-drug-btn").click(function(){
	var url = "back/drug/init-sync-drug";
	$.post(url, function(res){
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
	});
});

/*
 * 保存内容提交
 */
$("#save-submit-btn").click(function(){
	$("#save-form").submit();
});

/*
 * 验证用户名是否存在
 * 		未用
 */
function remoteValidUsername(){
	var valid = false;
	var url = "back/drug/checkUsernameValid";
	var userId = $("#user-id").val();
	var userName = $("#username").val();
	var params = {"id":userId, "username":userName};
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
	var id = $("#drug-id").val();
	if(id==null || id==""){
		url = "back/drug/insert";
	}else{
		url = "back/drug/updateById";
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
	var url = "back/drug/logicDelById";
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
	var href = "back/drug/selectItems?pagehelperFun=clickPageBtnRequestFun";
	parent.window.iframeLoading(href);//调用主页面中的在iframe中加载内容的方法
}

/*
 * 点击页面中的页码执行此函数
 * 		函数功能：根据页码数请求当前页内容
 */
function clickPageBtnRequestFun(params){
	var action = "back/drug/selectItems";
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
    $("#drug-table tbody input[type='checkbox']").each(function(){
    	if(!$(this).prop("checked")){
    		flag = false;
    	}
    });
    if(flag){
    	$("#drug-table thead input[type='checkbox']").prop('checked', true);
    }else{
    	$("#drug-table thead input[type='checkbox']").prop('checked', false);
    }
}

/*
 * 点击列表中All复选框时，列表全选或反选
 */
function checkAll(obj){
	$("#drug-table tbody input[type='checkbox']").prop('checked', $(obj).prop('checked'));
}

/**
 * 点击添加按钮显示添加编辑选项卡
 */
$("#add-drug-btn").click(function(){
	$("#edit-drug-li").removeClass("hide");
	$('#tabs-243687 a[href="#tab-2"]').tab('show');
});

/**
 * 验证文件有效性
 * 		未用
 * @param that
 * @returns
 */
/*function fileValid(that){
	var file = that.files[0];
	var fileSize = 0;
	// 大小 字节
	if (file == null) {
		// alert("请选择上传文件");
		// return;
	} else {
		console.log(file);
		console.log(file.name);
		var file_name = file.name;
		if(file_name.indexOf("xls")<0 || file_name.indexOf("xlsx")<0){
			util.message("请选择格式为.xls或.xlsx的文件！");
		    $("#upload-drug-btn").attr("disabled", true);
		    return false;
	    }
		
		fileSize = file.size;
		fileSize = Math.round(fileSize / 1024 * 100) / 100; // 单位为KB
		if (fileSize >= 10240) {
			util.message("EXCEL文件最大为10MB，请重新选择!");
			$("#upload-drug-btn").attr("disabled", true);
			return false;
		}
		$("#upload-drug-btn").attr("disabled", false);
	}
}*/

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
	$("#edit-drug-li").addClass("hide");
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