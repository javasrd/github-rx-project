/**
 * 用药方式 双击事件处理.
 * @returns
 */
function handler_dblclick_mode(){	
	//获取用户双击当前项id
	var id=$(this).attr("mode-id");	
	process_curr_mode(id);		
}

/**
 * 处理当前所选定的mode
 * @param modeId
 * @returns
 */
function process_curr_mode(modeId){
	
	var mode=$("#modename-"+modeId).text(); //自times列表选择
	$("#"+g_edit_mode_id).attr("disabled",true);
	$("#"+g_edit_mode_id).val(mode);	
	$("#"+g_edit_mode_id).attr("disabled",false);
	
	setPrescriptionMode(mode);
}

function setPrescriptionMode(mode){
	//(2)当选择一个字典项后
	Common.hideDropdownUnit();  //关闭选择下拉框
	setTableModeWindowStatus(WINDOW_CLOSED);	
	
	//（3）将选择的次数值保存到处方药品列表相应的对象中
	var drugId=$("#"+g_edit_mode_id).attr("bind-id");
	//自g_prescDrugList查询,并置mode
	for(var i=0;i<g_prescDrugList.length;i++){
		if(g_prescDrugList[i].id==drugId){
			g_prescDrugList[i].drugmode=mode;				
			break;
			
		}
	}
	
	//用法获取焦点
	var arr = g_edit_mode_id.split("-");
	$("#drug-days-"+arr[2])[0].focus();
}


/**
 * 选择第一条记录
 * @returns
 */
function choiceTableTheFirstMode(){
	var selector=".mode-item";
	var listx=$(selector);
	if(listx.size()>0){		
		var currNode=$(selector).eq(0);
		var modeId=currNode.attr("mode-id");
		process_curr_mode(modeId);
	}
	else{
		
		if($.trim($("#"+g_edit_mode_id).val())!=""){
			//当输入了次数后
			var mode=$.trim($("#"+g_edit_mode_id).val());
			setPrescriptionMode(mode);
		}
		else{
			alert("未输入用法!",500);			
		}
			
	}
}

$(function(){	
	/*************************
	 * 绑定事件
	 *************************/
	//当用户双击某药品条目时,选择此药品
	$(".mode-item").on("dblclick", handler_dblclick_mode);
});