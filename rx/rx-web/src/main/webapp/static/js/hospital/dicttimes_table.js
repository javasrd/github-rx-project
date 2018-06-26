
/**
 * 用药次数 单击事件处理.
 * @returns
 */
function handler_click_times(){	
	//获取用户双击当前项id
	var id=$(this).attr("times-id");	
	process_curr_times(id);		
}

/**
 * 处理当前所选定的times
 * @param timesId
 * @returns
 */
function process_curr_times(timesId){
	var times=$("#timesname-"+timesId).text(); //自times列表选择
	$("#"+g_edit_times_id).attr("disabled",true);
	$("#"+g_edit_times_id).val(times);	
	$("#"+g_edit_times_id).attr("disabled",false);
	
	setPrescriptionTimes(times);
}

function setPrescriptionTimes(times){
	//(2)当选择一个字典项后
	Common.hideDropdownUnit();  //关闭选择下拉框
	setTableTimesWindowStatus(WINDOW_CLOSED);	
	
	//（3）将选择的次数值保存到处方药品列表相应的对象中
	var drugId=$("#"+g_edit_times_id).attr("bind-id");
	//自g_prescDrugList查询,并置times
	for(var i=0;i<g_prescDrugList.length;i++){
		if(g_prescDrugList[i].id==drugId){
			g_prescDrugList[i].drugtimes=times;				
			break;
			
		}
	}
	
	//用法获取焦点
	var arr = g_edit_times_id.split("-");
	$("#drug-mode-"+arr[2])[0].focus();
}

/**
 * 选择第一条记录
 * @returns
 */
function choiceTableTheFirstTimes(){
	var listx=getRowsByAttr("class",CURR_ROW_CLASS_NAME_TIMES_TABLE);
	if(listx.size()>0){
		curr=listx[0];
		var timesId=$(curr).attr("times-id");
		process_curr_times(timesId);
	}
	else{
		if($.trim($("#"+g_edit_times_id).val())!=""){
			//当输入了次数后
			var times=$.trim($("#"+g_edit_times_id).val());
			setPrescriptionTimes(times);
		}
		else{
			alert("未输入频次!",500);			
		}
	}
}

$(function(){
	
	/************************************
	 * 绑定事件
	 ***********************************/
	//当用户双击某药品条目时,选择此药品
	$(".times-item").on("click", handler_click_times);
	addCurrRowClass(CURR_ROW_ATTR_NAME_TIMES_TABLE,1,CURR_ROW_CLASS_NAME_TIMES_TABLE);
});