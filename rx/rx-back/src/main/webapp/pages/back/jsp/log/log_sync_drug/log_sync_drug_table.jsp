<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<table class="table table-striped table-bordered table-hover " id="log-sync-drug-table"
	style="width: 100%;"center">
	<thead style="width: 98%; padding-top: 80px;">
		<tr role="row">
			<th><input type="checkbox" name="checkbox" id="checkbox" onclick="javascript:checkAll(this);">
				<label for="checkbox">ALL</label>
			</th>
			<th>ID</th>
			<th>创建时间</th>
			<th>数据源地址</th>
			<th>同步状态</th>
			<th>错误信息</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="log">
			<tr>
				<td>
					<input type="checkbox" name="checkbox2" id="log-sync-drug-td-${log.id}" onclick="javascript:checkOne();" value="${log.id}">
				</td>
				<td>${log.id}</td>
				<td>${log.createdDateStr}</td>
				<td>${log.url}</td>
				<td>${log.statusStr}</td>
				<td>${log.errormsg}</td>
				<td class="center ">
					<div style="text-align: center;; height: auto;"
						class="datagrid-cell datagrid-cell-c1-action">
						
						<button class="btn btn-default delet_btn"
							onclick="javascript:selectDetails(${log.id});">详情</button>	
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<%@include file="../../../../common/pagehelper.jsp"%><!-- 分页页面 -->
