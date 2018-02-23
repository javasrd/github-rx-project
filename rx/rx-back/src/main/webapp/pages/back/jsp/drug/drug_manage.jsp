<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>药品信息维护</title>
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
								aria-expanded="true">药品信息列表</a></li>
							<li class="hide" id="edit-drug-li"><a
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
														药品信息列表
													</h3>
												</div>
												<div class="panel-body">
												<div class="panel panel-default">
													<div class="panel-body">
														<div class="row clearfix">
															<div class="col-md-2 column">
																<button type="button" class="btn btn-default btn-primary" id="add-drug-btn" style="height:35px;">添加药品信息</button>
															</div>
															<!-- <form id="upload-file">
																<div class="col-md-3 column">
																	<input type="file" class="btn btn-default btn-primary" id="drug-file" name="drugFile" accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" onchange="javascript:fileValid(this);" />
																</div>
																<div class="col-md-7 column">
																	<button type="button" class="btn btn-default btn-primary" id="upload-drug-btn" style="height:35px;">导入药品信息</button>
																</div>
															</form> -->
															<div class="col-md-2 column">
																<button type="button" class="btn btn-default btn-primary" id="init-sync-drug-btn" style="height:35px;">同步药品信息</button>
															</div>
														</div>
													</div>
												</div>
												<div class="panel panel-default">
													<div class="panel-body">
														<div id="item-div" style="margin: 20px">
															<%@ include file="drug_table.jsp"%>
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
														药品信息
													</h3>
												</div>
												<div class="panel-body">
													<form class="form-horizontal" id="save-form">
														<input type="hidden" id="drug-id" name="id" value="" />
														<div class="form-group">
															<label class="col-sm-2 control-label">药品ID（来自海典ERP）</label>
															<div class="col-sm-10">
																<input type="text" id="wareid" name="wareid"
																	class="form-control" placeholder="药品ID（来自海典ERP）" />
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">条形码</label>
															<div class="col-md-10">
																<input type="text" id="barcode" name="barcode"
																	class="form-control" placeholder="条形码" />
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">助记码</label>
															<div class="col-md-10">
																<input type="text" id="abc" name="abc"
																	class="form-control" placeholder="助记码" />
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">药品名称</label>
															<div class="col-md-10">
																<input type="text" id="warename" name="warename"
																	class="form-control" placeholder="药品名称" />
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">通用名</label>
															<div class="col-md-10">
																<input type="text" id="waresimname" name="waresimname"
																	class="form-control" placeholder="通用名" />
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">规格</label>
															<div class="col-md-10">
																<input type="text" id="warespec" name="warespec"
																	class="form-control" placeholder="规格" />
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">产地</label>
															<div class="col-md-10">
																<input type="text" id="prod-addr" name="prodAddr"
																	class="form-control" placeholder="产地" />
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">生产厂家</label>
															<div class="col-md-10">
																<input type="text" id="producer" name="producer"
																	class="form-control" placeholder="生产厂家" />
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">单位</label>
															<div class="col-md-10">
																<input type="text" id="wareunit" name="wareunit"
																	class="form-control" placeholder="单位" />
															</div>
														</div>
														<div class="form-group" id="drug-status">
															<!-- 在售/停售状态  1：在售； 2：停售 -->
															<label class="col-md-2 control-label">在售状态</label>
															<div class="col-md-2">
																<input type="radio" id="" name="status" value="1" checked="checked" />&nbsp;&nbsp;在售
															</div>
															<div class="col-md-8">
																<input type="radio" id="" name="status" value="2" />&nbsp;&nbsp;停售
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-2 control-label">售价</label>
															<div class="col-md-10">
																<input type="text" id="saleprice" name="saleprice"
																	class="form-control" placeholder="售价" />
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
		<script type="text/javascript" src="static/js/drug.js"></script>
</body>
</html>
