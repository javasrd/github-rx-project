/**
 * 单击事件处理.
 * @returns
 */
function handler_click_days(){	
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
	
	autoCalcQuantity_days(daysId);  //自动计算数量
	
	//当选择一个用药次数后
	Common.hideDropdownTable();  //关闭选择下拉框
	$("#quantity").focus();     //下一个输入框获取焦点
	setDaysWindowStatus(WINDOW_CLOSED);	
}

/**
 * 功能: 在返回的疗程列表按ID进行查询 
 * 
 * @param id
 *          疗程ID
 * @returns 如果找到则返回相应的索引.否则返回-1
 */
function searchDaysById(id) {
	for (var i = 0; i < ret_daysList.length; i++) {
		var days = ret_daysList[i];
		if (id == days.id)
			return i;
	}
	return -1;
}


/**
 * 自动计算数量,如果可以计算的话.
 * @returns
 */
function autoCalcQuantity_days(daysId){
	console.log("debug");
	if (g_currDrug!=null){
		var idx=searchDaysById(daysId);
		var days=ret_daysList[idx];
		var days_value=days.value;
		
		//置当前药品的疗程
		if (days_value!=null && days_value>0){
			g_currDrug.days_value=days_value;
		}
		else{
			g_currDrug.day_values=0;
		}
		
		if(g_currDrug.dosage_value>0 && g_currDrug.drugtimes_value>0 && g_currDrug.days_value>0 && g_currDrug.minspec_value>0){
			console.log("dosage_value:"+g_currDrug.dosage_value);
			console.log("drugtimes_value:"+g_currDrug.drugtimes_value);
			console.log("days_value:"+g_currDrug.days_value);			
			console.log("minspec_value:"+g_currDrug.minspec_value);
			
			var quantity=Math.ceil(g_currDrug.dosage_value*g_currDrug.drugtimes_value*g_currDrug.days_value/g_currDrug.minspec_value);
			setInputBoxVal("#quantity", quantity); // 自动计算数量成功
			$("#quantity").focus();
			console.log("quantity:"+quantity);
			
		}
	}
}

/**
 * 设置输入框的值
 * 
 * @param inputId
 *            文本输入框ID
 * @param val
 *            值
 * @returns
 */
function setInputBoxVal(inputId, val) {
	$(inputId).attr("disabled", true);
	$(inputId).val(val);
	$(inputId).attr("disabled", false);
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
	$(".days-item").on("click", handler_click_days);
	addCurrRowClass(CURR_ROW_ATTR_NAME_DAYS,1,CURR_ROW_CLASS_NAME_DAYS);
});