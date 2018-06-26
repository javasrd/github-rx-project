/**************************************
	全局变量
***************************************/
/**
 * 单次剂量单位列表 单击事件处理.
 * @returns
 */
function handler_click_doseunit(){	
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
	$("#"+g_edit_doseunit_id).attr("disabled",true);
	$("#"+g_edit_doseunit_id).val(doseunit);	
	$("#"+g_edit_doseunit_id).attr("disabled",false);
	
	setPrescriptionDoseunit(doseunit);
	
}

function setPrescriptionDoseunit(doseunit){
	//(2)当选择一个剂量单位后
	Common.hideDropdownUnit();  //关闭选择下拉框
	setDoseUnitWindowStatus(WINDOW_CLOSED);	
	
	//（3）将选择的剂量单位值保存到处方药品列表相应的对象中
	var drugId=$("#"+g_edit_doseunit_id).attr("bind-id");
	//自g_prescDrugList查询,并置doseunit
	for(var i=0;i<g_prescDrugList.length;i++){
		if(g_prescDrugList[i].id==drugId){
			g_prescDrugList[i].doseunit=doseunit;				
			break;
			
		}
	}
	
	//用药天数获取焦点
	var arr = g_edit_doseunit_id.split("-");
	$("#drug-times-"+arr[2])[0].focus();
}

/**
 * 选择第一条记录
 * @returns
 */
function choiceTheFirstDoseUnit(){
	var listx=getRowsByAttr("class",CURR_ROW_CLASS_NAME_DOSE_UNIT_TABLE);
	if(listx.size()>0){
		curr=listx[0];
		var doseunitId=$(curr).attr("doseunit-id");  //列表中元素的ID
		process_curr_doseunit(doseunitId);
	}
	else{
		if($.trim($("#"+g_edit_doseunit_id).val())!=""){
			//当输入了剂量单位后
			var doseunit=$.trim($("#"+g_edit_doseunit_id).val());
			setPrescriptionDoseunit(doseunit);
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
	$(".doseunit-item").on("click", handler_click_doseunit);
	addCurrRowClass(CURR_ROW_ATTR_NAME_DOSE_UNIT_TABLE,1,CURR_ROW_CLASS_NAME_DOSE_UNIT_TABLE);
});