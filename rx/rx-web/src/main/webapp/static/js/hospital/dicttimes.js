
/***************************************************************************
	全局变量
***************************************************************************/
/**
 * 用药次数 双击事件处理.
 * @returns
 */
function handler_dblclick_times(){	
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
	//设置文本框内容
	$("#drugtimes").attr("disabled",true);
	$("#drugtimes").val($("#timesname-"+timesId).text());
	$("#drugtimes").attr("disabled",false);
	
	//当选择一个用药次数后
	Common.hideDropdownTable();  //关闭选择下拉框
	$("#drugmode").focus();     //下一个输入框获取焦点
	setTimesWindowStatus(WINDOW_CLOSED);
	
}

/**
 * 选择第一条记录
 * @returns
 */
function choiceTheFirstTimes(){
	var selector=".times-item";
	var listx=$(selector);
	if(listx.size()>0){
		currNode=$(selector).eq(0);
		var timesId=currNode.attr("times-id");
		process_curr_times(timesId);
	}
	else{
		if($.trim($("#drugtimes").val())!=""){
			//当输入一个用药次数后
			Common.hideDropdownTable();  //关闭选择下拉框
			$("#drugmode").focus();     //下一个文本框获取焦点
			setTimesWindowStatus(WINDOW_CLOSED);			
		}
		else{
			alert("未录入频次!",500);
		}
			
	}
}

$(function(){
	
	/************************************
	 * 绑定事件
	 ***********************************/
	//当用户双击某药品条目时,选择此药品
	$(".times-item").on("dblclick", handler_dblclick_times);
});