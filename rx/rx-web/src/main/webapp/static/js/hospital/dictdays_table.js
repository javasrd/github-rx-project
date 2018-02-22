/**
 * 双击事件处理.
 * @returns
 */
function handler_dblclick_days(){	
	//获取用户双击当前项id
	var id=$(this).attr("days-id");	
	process_curr_days(id);		
}

/**
 * 处理当前所选定的记录
 * @param daysId
 * @returns
 */
function process_curr_days(daysId){
	
	var days=$("#daysname-"+daysId).text(); //自times列表选择
	$("#"+g_edit_days_id).attr("disabled",true);
	$("#"+g_edit_days_id).val(days);	
	$("#"+g_edit_days_id).attr("disabled",false);
	
	setPrescriptionDays(days);
}

function setPrescriptionDays(days){
	//(2)当选择一个字典项后
	Common.hideDropdownUnit();  //关闭选择下拉框
	setTableDaysWindowStatus(WINDOW_CLOSED);	
	
	//（3）将选择的次数值保存到处方药品列表相应的对象中
	var drugId=$("#"+g_edit_days_id).attr("bind-id");
	//自g_prescDrugList查询,并置days
	for(var i=0;i<g_prescDrugList.length;i++){
		if(g_prescDrugList[i].id==drugId){
			g_prescDrugList[i].days=days;				
			break;
			
		}
	}
	
	//数量获取焦点
	var arr = g_edit_days_id.split("-");
	$("#drug-quantity-"+arr[2])[0].focus();
}

/**
 * 选择第一条记录
 * @returns
 */
function choiceTableTheFirstDays(){
	var selector=".days-item";
	var listx=$(selector);
	if(listx.size()>0){
		currNode=$(selector).eq(0);
		var daysId=currNode.attr("days-id");
		process_curr_days(daysId);
	}
	else{
		if($.trim($("#"+g_edit_days_id).val())!=""){
			//当输入了疗程后
			var days=$.trim($("#"+g_edit_days_id).val());
			setPrescriptionDays(days);
		}
		else{
			alert("未输入天数!",500);			
		}
	}
}

$(function(){
	
	/************************************
	 * 绑定事件
	 ***********************************/
	//当用户双击某药品条目时,选择此药品
	$(".days-item").on("dblclick", handler_dblclick_days);
});