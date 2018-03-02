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
	//设置文本框内容
	$("#treatment-days").attr("disabled",true);
	$("#treatment-days").val($("#daysname-"+daysId).text());
	$("#treatment-days").attr("disabled",false);
	
	//当选择一个用药次数后
	Common.hideDropdownTable();  //关闭选择下拉框
	$("#quantity").focus();     //下一个输入框获取焦点
	setDaysWindowStatus(WINDOW_CLOSED);
	
}

/**
 * 选择第一条记录
 * @returns
 */
function choiceTheFirstDays(){
	var listx=getRowsByAttr("class",CURR_ROW_CLASS_NAME_DAYS);	
	if(listx.size()>0){
		curr=listx[0]
		var daysId=$(curr).attr("days-id");
		process_curr_days(daysId);
	}
	else{
		if($.trim($("#treatment-days").val())!=""){
			//当输入一个用药天数后
			Common.hideDropdownTable();  //关闭选择下拉框
			$("#quantity").focus();     //下一个文本框获取焦点
			setDaysWindowStatus(WINDOW_CLOSED);			
		}
		else{
			alert("未录入疗程!",500);
		}
			
	}
}

$(function(){
	
	/************************************
	 * 绑定事件
	 ***********************************/
	//当用户双击某药品条目时,选择此药品
	$(".days-item").on("dblclick", handler_dblclick_days);
	addCurrRowClass(CURR_ROW_ATTR_NAME_DAYS,1,CURR_ROW_CLASS_NAME_DAYS);
});