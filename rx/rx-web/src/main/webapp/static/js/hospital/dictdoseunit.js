/**************************************
	全局变量
***************************************/
/**
 * 单次剂量单位列表 双击事件处理.
 * @returns
 */
function handler_dblclick_doseunit(){	
	//获取用户双击当前项id
	var id=$(this).attr("doseunit-id");	
	process_curr_doseunit(id);		
}

/**
 * 处理当前所选定的doseunit (在列表中选定)
 * @param doseunitId
 * @returns
 */
function process_curr_doseunit(doseUnitId){	
	//var doseUnitId=$(this).attr("doseunit-id");
	
	//（1）设置剂量单位文本框,关闭下拉窗口
	var doseunit=$("#doseunitname-"+doseUnitId).text(); //自doseunit列表选择
	$("#single-dose-unit").attr("disabled",true);
	$("#single-dose-unit").val(doseunit);	
	$("#single-dose-unit").attr("disabled",false);
	
	Common.hideDropdownTable();  //关闭选择下拉框
	$("#drugtimes").focus();
	setDoseUnitInputWindowStatus(WINDOW_CLOSED)
}
/**
 * 选择第一条记录
 * @returns
 */
function choiceTheFirstDoseUnitInput(){
	var selector=".doseunit-item";
	var listx=$(selector);
	if(listx.size()>0){
		currNode=$(selector).eq(0);
		var doseunitId=currNode.attr("doseunit-id");  //列表中元素的ID
		process_curr_doseunit(doseunitId);
	}
	else{
		if($.trim($("#single-dose-unit").val())!=""){
			//当输入了剂量单位后
			Common.hideDropdownTable();  //关闭选择下拉框
			$("#drugtimes").focus();
			setDoseUnitInputWindowStatus(WINDOW_CLOSED)
		}
		else{
			alert("未输入单次剂量单位!",500);			
		}
	}

}


$(function(){
	
	/****************************************
	 * 绑定事件
	 ****************************************/
	//当用户双击某药品条目时,选择此药品
	$(".doseunit-item").on("dblclick", handler_dblclick_doseunit);
});