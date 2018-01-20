<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用药方式维护</title>
<%@ include file="../../../common/headCss.jsp"%>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12">
				<div class="card">
					<div class="tabs-container" id="tabs-243687">
						<ul class="nav nav-tabs" id="top_tab">
							<li class="active" onclick="javascript:resetFun();"><a data-toggle="tab" href="#tab-1"
								aria-expanded="true">用药方式列表</a></li>
							<li class="hide" id="edit-dict-mode-li"><a
								data-toggle="tab" href="#tab-2" aria-expanded="false">添加/编辑</a></li>
						</ul>
						<div class="tab-content">
							<div id="tab-1" class="tab-pane active">
								<div class="container-fluid" style="margin-top: 20px;">
									<div class="row clearfix">
										<div class="col-md-12 column">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h3 class="panel-title">
														用药方式列表
													</h3>
												</div>
												<div class="panel-body">
												<div class="panel panel-default">
													<div class="panel-body">
														<button type="button" class="btn btn-default btn-primary" id="add-dict-mode-btn">添加用药方式</button>
													</div>
												</div>
												<div class="panel panel-default">
													<div class="panel-body">
														<div id="item-div" style="margin: 20px">
															<%@ include file="mode_table.jsp"%>
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
														用药方式信息
													</h3>
												</div>
												<div class="panel-body">
													<form class="form-horizontal" id="save-form">
														<input type="hidden" id="mode-id" name="id" value="" />
														<div class="form-group">
															<label class="col-sm-2 control-label">用药方式编码</label>
															<div class="col-sm-10">
																<input type="text" id="mode-code" name="code"
																	class="form-control" placeholder="用药方式编码" />
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">用药方式名称</label>
															<div class="col-md-10">
																<input type="text" id="mode-name" name="name"
																	class="form-control" placeholder="用药方式名称" />
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">&nbsp;</label>
															<div class="col-sm-10">
																<button type="button" class="btn btn-primary"
																	id="save-submit-btn">保存</button>
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

		<%@ include file="../../../common/headJs.jsp"%>
		<script type="text/javascript" src="static/js/dict_mode.js"></script>
</body>
</html>
