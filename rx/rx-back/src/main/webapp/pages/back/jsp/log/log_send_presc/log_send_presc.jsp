<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>发送患者信息日志</title>
<%@ include file="../../../../common/headCss.jsp"%>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12">
				<div class="card">
					<div class="tabs-container" id="tabs-243687">
						<ul class="nav nav-tabs" id="top_tab">
							<li class="active" onclick="javascript:resetFun();"><a data-toggle="tab" href="#tab-1"
								aria-expanded="true">发送患者信息日志列表</a></li>
							<li class="hide" id="edit-log-send-presc-li"><a
								data-toggle="tab" href="#tab-2" aria-expanded="false">详情</a></li>
						</ul>
						<div class="tab-content">
							<div id="tab-1" class="tab-pane active">
								<div class="container-fluid" style="margin-top: 20px;">
									<div class="row clearfix">
										<div class="col-md-12 column">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h3 class="panel-title">
														发送患者信息日志列表
													</h3>
												</div>
												<div class="panel-body">
												<!-- <div class="panel panel-default">
													<div class="panel-body">
														<div class="row clearfix">
															<div class="col-md-2 column">
																<button type="button" class="btn btn-default btn-primary" id="add-drug-btn" style="height:35px;">添加药品信息</button>
															</div>
															<form id="upload-file">
																<div class="col-md-3 column">
																	<input type="file" class="btn btn-default btn-primary" id="drug-file" name="drugFile" accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" onchange="javascript:fileValid(this);" />
																</div>
																<div class="col-md-7 column">
																	<button type="button" class="btn btn-default btn-primary" id="upload-drug-btn" style="height:35px;">导入药品信息</button>
																</div>
															</form>
														</div>
													</div>
												</div> -->
												<div class="panel panel-default">
													<div class="panel-body">
														<div id="item-div" style="margin: 20px">
															<%@ include file="log_send_presc_table.jsp"%>
														</div>
													</div>
												</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div id="tab-2" class="tab-pane">
								<div class="container-fluid" style="margin-top: 20px;">
									<div class="row clearfix">
										<div class="col-md-12 column">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h3 class="panel-title">
														发送患者信息日志详情
													</h3>
												</div>
												<div class="panel-body">
													<form class="form-horizontal" id="">
														<div class="form-group">
															<label class="col-sm-2 control-label">日志ID</label>
															<div class="col-sm-10">
																<span id="log-send-presc-id"></span>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">日志创建时间</label>
															<div class="col-md-10">
																<span id="log-send-presc-created-time"></span>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">发送地址</label>
															<div class="col-md-10">
																<span id="log-send-presc-url"></span>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">发送数据</label>
															<div class="col-md-10">
																<span id="log-send-presc-data"></span>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">发送状态</label>
															<div class="col-md-10">
																<span id="log-send-presc-status"></span>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">处方ID</label>
															<div class="col-md-10">
																<span id="log-send-presc-presc-id"></span>
															</div>
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../../../../common/headJs.jsp"%>
	<script type="text/javascript">
		/*
		 * 查看详细信息
		 */
		function selectDetails(id){
			resetFun();
			var url = "back/log-send-presc/select-update-by-id";
			var params = {"id":id};
			$.post(url, params, function(res){
				console.log(res);
				if(res!=null){
					var resp = $.parseJSON(res);
					if(resp.result_code=="success"){
						$("#edit-log-send-presc-li").removeClass("hide");
						var log =resp.logSendPresc;
						$("#log-send-presc-id").text(log.id);//ID
						$("#log-send-presc-created-time").text(log.createdTimeStr);//创建时间
						$("#log-send-presc-url").text(log.url);//发送地址
						$("#log-send-presc-url").text(log.data);//发送数据
						$("#log-send-presc-status").text(log.statusStr);//发送状态
						$("#log-send-presc-presc-id").text(log.prescId==null ? "" : log.prescId);//处方ID
						
						$('#tabs-243687 a[href="#tab-2"]').tab('show');
						return;
					}
				}
				util.message("查询异常");
				
			});
		}
		/*
		 * 重新加载当前菜单内容
		 */
		function reloadInfoFun(){
			//操作成功后重新加载
			var href = "back/log-send-presc/select-items?pagehelperFun=clickPageBtnRequestFun";
			parent.window.iframeLoading(href);//调用主页面中的在iframe中加载内容的方法
		}
	
		/*
		 * 点击页面中的页码执行此函数
		 * 		函数功能：根据页码数请求当前页内容
		 */
		function clickPageBtnRequestFun(params){
			var action = "back/log-send-presc/select-items";
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
		    $("#log-send-presc-table tbody input[type='checkbox']").each(function(){
		    	if(!$(this).prop("checked")){
		    		flag = false;
		    	}
		    });
		    if(flag){
		    	$("#log-send-presc-table thead input[type='checkbox']").prop('checked', true);
		    }else{
		    	$("#log-send-presc-table thead input[type='checkbox']").prop('checked', false);
		    }
		}
		/*
		 * 点击列表中All复选框时，列表全选或反选
		 */
		function checkAll(obj){
			$("#log-send-presc-table tbody input[type='checkbox']").prop('checked', $(obj).prop('checked'));
		}
		/*
		 * 重置form表单
		 */
		function resetFun(){
			$("#edit-log-send-presc-li").addClass("hide");
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
	</script>
</body>
</html>
