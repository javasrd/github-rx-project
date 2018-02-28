<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<table class="table table-striped table-bordered table-hover " id="schedule-job-table"
	style="width: 100%;"center">
	<thead style="width: 98%; padding-top: 80px;">
		<tr role="row">
			<th><input type="checkbox" name="checkbox" id="checkbox" onclick="javascript:checkAll(this);">
				<label for="checkbox">ALL</label>
			</th>
			<th>ID</th>
			<!-- <th>任务分组</th> -->
			<th>任务名称</th>
			<th>任务描述</th>
			<th>任务运行时间</th>
			<th>任务状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="job">
			<tr>
				<td>
					<input type="checkbox" name="checkbox2" id="schedule-job-td-${job.jobId}" onclick="javascript:checkOne();" value="${job.jobId}">
				</td>
				<td>${job.jobId}</td>
				<%-- <td>${job.jobGroup}</td> --%>
				<td>${job.jobName}</td>
				<td>${job.jobDesc}</td>
				<td>${job.cronExpressionName}</td>
				<td>${job.jobStatusStr}</td>
				<td class="center ">
					<div style="text-align: center;; height: auto;"
						class="datagrid-cell datagrid-cell-c1-action">
						<!-- 任务状态 1：任务中；2：暂停；3：停止 -->
						<c:if test="${job.jobStatus!=1 && job.jobStatus!=2 && job.jobStatus!=3}">
							<button class="btn btn-default btn-info" title="启动任务"
								onclick="javascript:startJobFun(${job.jobId});">启动</button>
							<button class="btn btn-default btn-info" title="暂停任务"
								onclick="javascript:pauseJobFun(${job.jobId});" disabled="disabled">暂停</button>
							<button class="btn btn-default btn-info" title="继续任务"
								onclick="javascript:resumeJobFun(${job.jobId});" disabled="disabled">继续</button>
							<button class="btn btn-default btn-danger" title="停止任务"
								onclick="javascript:deleteJobFun(${job.jobId});">停止</button>
							<button class="btn btn-default btn-warning" title="编辑任务"
								onclick="javascript:selectDetailsFun(${job.jobId});">编辑</button>
							<button class="btn btn-default btn-danger" title="删除任务"
								onclick="javascript:deleteJobAndRecordFun(${job.jobId});">删除任务</button>
						</c:if>
						<c:if test="${job.jobStatus==1}">
							<button class="btn btn-default btn-info" title="启动任务"
								onclick="javascript:startJobFun(${job.jobId});" disabled="disabled">启动</button>
							<button class="btn btn-default btn-info" title="暂停任务"
								onclick="javascript:pauseJobFun(${job.jobId});">暂停</button>
							<button class="btn btn-default btn-info" title="继续任务"
								onclick="javascript:resumeJobFun(${job.jobId});" disabled="disabled">继续</button>
							<button class="btn btn-default btn-danger" title="停止任务"
								onclick="javascript:deleteJobFun(${job.jobId});">停止</button>
							<button class="btn btn-default btn-warning" title="编辑任务"
								onclick="javascript:selectDetailsFun(${job.jobId});">编辑</button>
							<button class="btn btn-default btn-danger" title="删除任务"
								onclick="javascript:deleteJobAndRecordFun(${job.jobId});">删除任务</button>
						</c:if>
						<c:if test="${job.jobStatus==2}">
							<button class="btn btn-default btn-info" title="启动任务"
								onclick="javascript:startJobFun(${job.jobId});" disabled="disabled">启动</button>
							<button class="btn btn-default btn-info" title="暂停任务"
								onclick="javascript:pauseJobFun(${job.jobId});" disabled="disabled">暂停</button>
							<button class="btn btn-default btn-info" title="继续任务"
								onclick="javascript:resumeJobFun(${job.jobId});">继续</button>
							<button class="btn btn-default btn-danger" title="停止任务"
								onclick="javascript:deleteJobFun(${job.jobId});">停止</button>
							<button class="btn btn-default btn-warning" title="编辑任务"
								onclick="javascript:selectDetailsFun(${job.jobId});">编辑</button>
							<button class="btn btn-default btn-danger" title="删除任务"
								onclick="javascript:deleteJobAndRecordFun(${job.jobId});">删除任务</button>
						</c:if>
						<c:if test="${job.jobStatus==3}">
							<button class="btn btn-default btn-info" title="启动任务"
								onclick="javascript:startJobFun(${job.jobId});">启动</button>
							<button class="btn btn-default btn-info" title="暂停任务"
								onclick="javascript:pauseJobFun(${job.jobId});" disabled="disabled">暂停</button>
							<button class="btn btn-default btn-info" title="继续任务"
								onclick="javascript:resumeJobFun(${job.jobId});" disabled="disabled">继续</button>
							<button class="btn btn-default btn-danger" title="停止任务"
								onclick="javascript:deleteJobFun(${job.jobId});" disabled="disabled">停止</button>
							<button class="btn btn-default btn-warning" title="编辑任务"
								onclick="javascript:selectDetailsFun(${job.jobId});">编辑</button>
							<button class="btn btn-default btn-danger" title="删除任务"
								onclick="javascript:deleteJobAndRecordFun(${job.jobId});">删除任务</button>
						</c:if>
						
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<%@include file="../../../common/pagehelper.jsp"%><!-- 分页页面 -->
