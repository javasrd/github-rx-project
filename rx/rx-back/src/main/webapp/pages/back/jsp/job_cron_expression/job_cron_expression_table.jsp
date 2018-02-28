<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<table class="table table-striped table-bordered table-hover " id="job-cron-expression-table"
	style="width: 100%;"center">
	<thead style="width: 98%; padding-top: 80px;">
		<tr role="row">
			<th><input type="checkbox" name="checkbox" id="checkbox" onclick="javascript:checkAll(this);">
				<label for="checkbox">ALL</label>
			</th>
			<th>ID</th>
			<th>任务运行时间表达式名称</th>
			<th>任务运行时间表达式</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="expression">
			<tr>
				<td>
					<input type="checkbox" name="checkbox2" id="job-cron-expression-td-${expression.id}" onclick="javascript:checkOne();" value="${expression.id}">
				</td>
				<td>${expression.id}</td>
				<td>${expression.name}</td>
				<td>${expression.cronExpression}</td>
				<td class="center ">
					<div style="text-align: center;; height: auto;"
						class="datagrid-cell datagrid-cell-c1-action">
						
						<button class="btn btn-default btn-info"
							onclick="javascript:selectDetails(${expression.id});">详情</button>	
						<button class="btn btn-default btn-danger"
							onclick="javascript:deleteInfoFun(${expression.id});">删除</button>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<%@include file="../../../common/pagehelper.jsp"%><!-- 分页页面 -->
