
/***************************************************************************
	全局变量
***************************************************************************/
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
	//设置用法文本框
	$("#drugmode").val($("#modename-"+modeId).text());
	
	//当选择一个给药方式后
	Common.hideDropdownTable();  //关闭选择下拉框		
	$("#drugtimes").focus();     //"给药次数"文本框获取焦点
	setModeWindowStatus(WINDOW_CLOSED);
}

/**
 * 选择第一条记录
 * @returns
 */
function choiceTheFirstMode(){
	var selector=".mode-item";
	var listx=$(selector);
	if(listx.size()>0){		
		var currNode=$(selector).eq(0);
		var modeId=currNode.attr("mode-id");
		process_curr_mode(modeId);
	}
}

$(function(){	
	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	//当用户双击某药品条目时,选择此药品
	$(".mode-item").on("dblclick", handler_dblclick_mode);
});