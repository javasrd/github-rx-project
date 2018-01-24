<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<table class="table table-striped table-bordered table-hover " id="drug-table"
	style="width: 100%;"center">
	<thead style="width: 98%; padding-top: 80px;">
		<tr role="row">
			<th><input type="checkbox" name="checkbox" id="checkbox" onclick="javascript:checkAll(this);">
				<label for="checkbox">ALL</label>
			</th>
			<th>药品ID（来自海典ERP）</th>
			<th>条形码</th>
			<th>助记码</th>
			<th>药品名称</th>
			<th>通用名</th>
			<th>在售状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="drug">
			<tr>
				<td>
					<input type="checkbox" name="checkbox2" id="drug-td-${drug.id}" onclick="javascript:checkOne();" value="${drug.id}">
				</td>
				<td>${drug.wareid}</td>
				<td>${drug.barcode}</td>
				<td>${drug.abc}</td>
				<td>${drug.warename}</td>
				<td>${drug.waresimname}</td>
				<td>
					<c:if test="${drug.status==null || drug.status==''}">未知</c:if>
					<c:if test="${drug.status!=null && drug.status!='' && drug.status==1}">在售</c:if>
					<c:if test="${drug.status!=null && drug.status!='' && drug.status==2}">停售</c:if>
				</td>
				<td class="center ">
					<div style="text-align: center;; height: auto;"
						class="datagrid-cell datagrid-cell-c1-action">
						
						<button class="btn btn-default delet_btn"
							onclick="javascript:selectDetails(${drug.id});">详情</button>	
						<button class="btn btn-default delet_btn"
							onclick="javascript:deleteInfoFun(${drug.id});">删除</button>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<%@include file="../../../common/pagehelper.jsp"%><!-- 分页页面 -->
