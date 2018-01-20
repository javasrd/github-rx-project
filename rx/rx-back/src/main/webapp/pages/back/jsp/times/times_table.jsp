<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<table class="table table-striped table-bordered table-hover " id="dict-times-table"
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
		<c:forEach items="${pagehelper.list}" var="times">
			<tr>
				<td>
					<input type="checkbox" name="checkbox2" id="user-td-${times.id}" onclick="javascript:checkOne();" value="${times.id}">
				</td>
				<td>${times.id}</td>
				<td>${times.code}</td>
				<td>${times.name}</td>
				<td class="center ">
					<div style="text-align: center;; height: auto;"
						class="datagrid-cell datagrid-cell-c1-action">
						
						<button class="btn btn-default delet_btn"
							onclick="javascript:selectDetails(${times.id});">详情</button>	
						<button class="btn btn-default delet_btn"
							onclick="javascript:deleteInfoFun(${times.id});">删除</button>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<%@include file="../../../common/pagehelper.jsp"%><!-- 分页页面 -->
