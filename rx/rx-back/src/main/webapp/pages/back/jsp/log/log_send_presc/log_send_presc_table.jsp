<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<table class="table table-striped table-bordered table-hover " id="log-send-presc-table"
	style="width: 100%;"center">
	<thead style="width: 98%; padding-top: 80px;">
		<tr role="row">
			<th><input type="checkbox" name="checkbox" id="checkbox" onclick="javascript:checkAll(this);">
				<label for="checkbox">ALL</label>
			</th>
			<th>日志ID</th>
			<th>创建时间</th>
			<th>发送地址</th>
			<th>发送数据</th>
			<th>发送状态</th>
			<th>处方ID</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="log">
			<tr>
				<td>
					<input type="checkbox" name="checkbox2" id="log-send-presc-td-${log.id}" onclick="javascript:checkOne();" value="${log.id}">
				</td>
				<td>${log.id}</td>
				<td>${log.createdTimeStr}</td>
				<td>${log.url}</td>
				<td>${log.data}</td>
				<td>${log.statusStr}</td>
				<td>${log.prescId}</td>
				<td class="center">
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
