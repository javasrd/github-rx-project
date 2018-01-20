<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<table class="table table-striped table-bordered table-hover " id="dict-dose-unit-table"
	style="width: 100%;"center">
	<thead style="width: 98%; padding-top: 80px;">
		<tr role="row">
			<th><input type="checkbox" name="checkbox" id="checkbox" onclick="javascript:checkAll(this);">
				<label for="checkbox">ALL</label>
			</th>
			<th>ID</th>
			<th>编码</th>
			<th>名称</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="doseUnit">
			<tr>
				<td>
					<input type="checkbox" name="checkbox2" id="user-td-${doseUnit.id}" onclick="javascript:checkOne();" value="${doseUnit.id}">
				</td>
				<td>${doseUnit.id}</td>
				<td>${doseUnit.code}</td>
				<td>${doseUnit.name}</td>
				<td class="center ">
					<div style="text-align: center;; height: auto;"
						class="datagrid-cell datagrid-cell-c1-action">
						
						<button class="btn btn-default delet_btn"
							onclick="javascript:selectDetails(${doseUnit.id});">详情</button>	
						<button class="btn btn-default delet_btn"
							onclick="javascript:deleteInfoFun(${doseUnit.id});">删除</button>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<%@include file="../../../common/pagehelper.jsp"%><!-- 分页页面 -->
