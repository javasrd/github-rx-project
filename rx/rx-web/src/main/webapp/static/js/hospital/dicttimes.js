
/***************************************************************************
	全局变量
***************************************************************************/
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
	//设置文本框内容
	$("#drugtimes").attr("disabled",true);
	$("#drugtimes").val($("#timesname-"+timesId).text());
	$("#drugtimes").attr("disabled",false);
	
	autoCalcQuantity_times(timesId);  //自动计算数量
	
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
	var listx=getRowsByAttr("class",CURR_ROW_CLASS_NAME_TIMES);	
	if(listx.size()>0){
		curr=listx[0]
		var timesId=$(curr).attr("times-id");
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

/**
 * 功能: 在返回的频次列表按ID进行查询 
 * 
 * @param id
 *          频次ID
 * @returns 如果找到则返回相应的索引.否则返回-1
 */
function searchTimesById(id) {
	for (var i = 0; i < ret_timesList.length; i++) {
		var times = ret_timesList[i];
		if (id == times.id)
			return i;
	}
	return -1;
}


/**
 * 自动计算数量,如果可以计算的话.
 * @returns
 */
function autoCalcQuantity_times(timesId){
	console.log("debug");
	if (g_currDrug!=null){
		var idx=searchTimesById(timesId);
		var times=ret_timesList[idx];
		var times_value=times.value;
		
		//置当前药品的频次
		if (times_value!=null && times_value!=""){
			try{
				g_currDrug.drugtimes_value=eval(times_value);
			}
			catch(err){
				g_currDrug.drugtimes_value=0;
			}
		}
		else{
			g_currDrug.drugtimes_values=0;
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


$(function(){
	
	/************************************
	 * 绑定事件
	 ***********************************/
	//当用户双击某药品条目时,选择此药品
	$(".times-item").on("click", handler_click_times);
	//alert("loaded");
	addCurrRowClass(CURR_ROW_ATTR_NAME_TIMES,1,CURR_ROW_CLASS_NAME_TIMES);
});

