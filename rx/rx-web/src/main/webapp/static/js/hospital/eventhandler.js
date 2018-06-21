/**************************************
 * 事件处理函数
 *************************************/
/**-----------------------------------
 * keydown event
 -----------------------------------*/
function handler_keydown_hospital(ev){
	var oEvent = ev || event;// 获取事件对象(IE和其他浏览器不一样，这里要处理一下浏览器的兼容性event是IE；ev是chrome等)
	if (oEvent.keyCode == 27) // Esc键的keyCode是27
	{
		if (getDrugWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownTable(); // 关闭选择下拉框			
			setDrugWindowStatus(WINDOW_CLOSED);
		}
		
		if (getModeWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownTable(); // 关闭选择下拉框
			setModeWindowStatus(WINDOW_CLOSED);
		}
		
		if (getTimesWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownTable(); // 关闭选择下拉框
			setTimesWindowStatus(WINDOW_CLOSED);
		}
		
		if (getDoseUnitInputWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownTable(); // 关闭选择下拉框
			setDoseUnitInputWindowStatus(WINDOW_CLOSED);
		}
		
		if (getDaysWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownTable(); // 关闭选择下拉框
			setDaysWindowStatus(WINDOW_CLOSED);
		}
		//-----------dropdown in table---------
		
		if (getTableModeWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownUnit(); // 关闭选择下拉框
			setTableModeWindowStatus(WINDOW_CLOSED);
		}
		
		if (getTableTimesWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownUnit(); // 关闭选择下拉框
			setTableTimesWindowStatus(WINDOW_CLOSED);
		}
		
		if (getDoseUnitWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownUnit(); // 关闭选择下拉框
			setDoseUnitWindowStatus(WINDOW_CLOSED);
		}
		
		if (getTableDaysWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownUnit(); // 关闭选择下拉框
			setTableDaysWindowStatus(WINDOW_CLOSED);
		}
		
		return false;

	}
}

/**
 * 用于处理药品助记码框中ESC按键
 * 
 * @param ev
 *            事件对象
 * @returns
 */
function handler_keydown_abc(ev) {
	var oEvent = ev || event;// 获取事件对象(IE和其他浏览器不一样，这里要处理一下浏览器的兼容性event是IE；ev是chrome等)
	if (oEvent.keyCode == 27) // Esc键的keyCode是27
	{
		if (getDrugWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownTable(); // 关闭选择下拉框
			$(this).focus(); // 给药方式文本框获取焦点
			setDrugWindowStatus(WINDOW_CLOSED);
		}
		return false;

	}
	
	if (oEvent.keyCode == DOWN_ARROW_CODE) {  //向下箭头
		if (getDrugWindowStatus() == WINDOW_OPENED) {
			selectTheNextRow(CURR_ROW_CLASS_NAME_DRUG,CURR_ROW_ATTR_NAME_DRUG,CONTAINER_ID_TABLE);
		}
		else{
			$("#btn-abc").trigger("click");
		}
		return false;
	}
	
	if (oEvent.keyCode == UP_ARROW_CODE) {  //向上箭头
		if (getDrugWindowStatus() == WINDOW_OPENED) {
			selectThePreviousRow(CURR_ROW_CLASS_NAME_DRUG,CURR_ROW_ATTR_NAME_DRUG,CONTAINER_ID_TABLE, 'drug-category');			
		}
		return false;
	}
	
	
	// 用户按下了回车键
	// 当药品列表中不为空,选择第一个.如果为空时,不做任何动作.
	if (oEvent.keyCode == 13) {
		if (getDrugWindowStatus() == WINDOW_OPENED) {
			choiceTheCurrRow_drug();  //TODO 修改此代码为 choiceCurrRowDrug();			
		}
		else{
			if(g_currDrug==null){
				alert("未选择药品",500);
			}
				
		}
		return false;
	}
	
	

}

/**
 * 表格:频次-keydown事件处理程序
 * @param ev
 * @returns
 */
function handler_keydown_times_table(ev){
	var oEvent = ev || event;// 获取事件对象(IE和其他浏览器不一样，这里要处理一下浏览器的兼容性event是IE；ev是chrome等)
	if (oEvent.keyCode == 27) // Esc键的keyCode是27
	{
		//alert("esc:"+getDoseUnitWindowStatus());
		if (getTableTimesWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownUnit(); // 关闭选择下拉框
			$(this).focus();
			setTableTimesWindowStatus(WINDOW_CLOSED);
		}
		return false;
	}
	
	if (oEvent.keyCode == DOWN_ARROW_CODE) {  //向下箭头
		if (getTableTimesWindowStatus() == WINDOW_OPENED) {
			selectTheNextRow(CURR_ROW_CLASS_NAME_TIMES_TABLE,CURR_ROW_ATTR_NAME_TIMES_TABLE,CONTAINER_ID_UNIT);
		}
		else{
			var bindid=$(this).attr("bind-id");
			$("#btn-times-"+bindid).trigger("click");	
		}
		return false;
	}
	
	if (oEvent.keyCode == UP_ARROW_CODE) {  //向上箭头
		if (getTableTimesWindowStatus() == WINDOW_OPENED) {
			selectThePreviousRow(CURR_ROW_CLASS_NAME_TIMES_TABLE,CURR_ROW_ATTR_NAME_TIMES_TABLE,CONTAINER_ID_UNIT, 'dict-drug-times');			
		}
		return false;
	}
	
	
	// 用户按下了回车键
	// 当times列表中不为空,选择第一个.如果为空时,不做任何动作.
	if (oEvent.keyCode == 13) {
		if (getTableTimesWindowStatus() == WINDOW_OPENED) {
			choiceTableTheFirstTimes();			
		} else {
			var bindid=$(this).attr("bind-id");
			if($.trim($(this).val())==""){
				alert("未录入次数!",500);
				//setFocus("#drugtimes");
			}
			else{
				$("#drug-mode-"+bindid).focus(); 	//"天数"文本框获取焦点
			}
			
		}
		return false;
	}
}

/**
 * 表格: 用法
 * @param ev
 * @returns
 */
function handler_keydown_mode_table(ev){
	var oEvent = ev || event;// 获取事件对象(IE和其他浏览器不一样，这里要处理一下浏览器的兼容性event是IE；ev是chrome等)
	if (oEvent.keyCode == 27) // Esc键的keyCode是27
	{
		//alert("esc:"+getDoseUnitWindowStatus());
		if (getTableModeWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownUnit(); // 关闭选择下拉框
			$(this).focus();
			setTableModeWindowStatus(WINDOW_CLOSED);
		}
		return false;
	}
	
	
	if (oEvent.keyCode == DOWN_ARROW_CODE) {  //向下箭头
		if (getTableModeWindowStatus() == WINDOW_OPENED) {
			selectTheNextRow(CURR_ROW_CLASS_NAME_MODE_TABLE,CURR_ROW_ATTR_NAME_MODE_TABLE,CONTAINER_ID_UNIT);
		}
		else{
			var bindid=$(this).attr("bind-id");
			$("#btn-mode-"+bindid).trigger("click");
		}
		return false;
	}
	
	if (oEvent.keyCode == UP_ARROW_CODE) {  //向上箭头
		if (getTableModeWindowStatus() == WINDOW_OPENED) {
			selectThePreviousRow(CURR_ROW_CLASS_NAME_MODE_TABLE,CURR_ROW_ATTR_NAME_MODE_TABLE,CONTAINER_ID_UNIT, 'dict-drug-mode');			
		}
		return false;
	}
	
	
	// 用户按下了回车键
	// 当times列表中不为空,选择第一个.如果为空时,不做任何动作.
	if (oEvent.keyCode == 13) {
		if (getTableModeWindowStatus() == WINDOW_OPENED) {
			choiceTableTheFirstMode();			
		} else {
			var bindid=$(this).attr("bind-id");
			if($.trim($(this).val())==""){
				alert("未录入用法!",500);
				//setFocus("#drugtimes");
			}
			else{
				$("#drug-days-"+bindid).focus(); // "天数"文本框获取焦点
			}
			
		}
		return false;
	}
}

function handler_keydown_quantity_table(ev){
	var oEvent = ev || event;// 获取事件对象(IE和其他浏览器不一样，这里要处理一下浏览器的兼容性event是IE；ev是chrome等)
	if (oEvent.keyCode == "13") {// keyCode=13是回车键
		
		if($.trim($(this).val())==""){
			alert("数量为空",500);
		}
		else{
			//判定是否为正数
			if(!isPositiveNumber($(this).val())){
				alert("数量不是正数",500);
			}
			else{
				var sum=calcPrescDrugAmount();			
				if(sum>300){
					alert("每个处方中金额不可以超过300元!",500);
				}
				else{
					setFocus("#abc");
				}
			}
			
		}
		return false;	
	}
	
}

/**
 * 给药方式
 * 
 * @param ev
 * @returns
 */
function handler_keydown_mode(ev) {
	var oEvent = ev || event;// 获取事件对象(IE和其他浏览器不一样，这里要处理一下浏览器的兼容性event是IE；ev是chrome等)
	if (oEvent.keyCode == 27) // Esc键的keyCode是27
	{
		if (getModeWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownTable(); // 关闭选择下拉框
			$(this).focus();
			setModeWindowStatus(WINDOW_CLOSED);
		}
		return false;

	}
	
	if (oEvent.keyCode == DOWN_ARROW_CODE) {  //向下箭头
		if (getModeWindowStatus() == WINDOW_OPENED) {
			selectTheNextRow(CURR_ROW_CLASS_NAME_MODE,CURR_ROW_ATTR_NAME_MODE,CONTAINER_ID_TABLE);
		}
		else{
			$("#btn-drugmode").trigger("click");
		}
		return false;
	}
	
	if (oEvent.keyCode == UP_ARROW_CODE) {  //向上箭头
		if (getModeWindowStatus() == WINDOW_OPENED) {
			selectThePreviousRow(CURR_ROW_CLASS_NAME_MODE,CURR_ROW_ATTR_NAME_MODE,CONTAINER_ID_TABLE, 'dict-drug-mode');			
		}
		return false;
	}
	
	
	
	// 用户按下了回车键
	// 当药品列表中不为空,选择第一个.如果为空时,不做任何动作.
	if (oEvent.keyCode == 13) {
		if (getModeWindowStatus() == WINDOW_OPENED) {
			choiceTheFirstMode();
		} else {
			if($.trim($("#drugmode").val())==""){
				alert("未录入药品用法",500);
			}
			else{
				$("#treatment-days").focus(); // "给药次数"文本框获取焦点
			}
			
			
		}
		return false;
	}
}

/**
 * 文本框 给药次数 keydown_times
 * 
 * @param ev
 * @returns
 */
function handler_keydown_times(ev) {
	var oEvent = ev || event;// 获取事件对象(IE和其他浏览器不一样，这里要处理一下浏览器的兼容性event是IE；ev是chrome等)
	if (oEvent.keyCode == 27) // Esc键的keyCode是27
	{
		if (getTimesWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownTable(); // 关闭选择下拉框
			$(this).focus();
			setTimesWindowStatus(WINDOW_CLOSED);
		}
		return false

	}
	
	
	if (oEvent.keyCode == DOWN_ARROW_CODE) {  //向下箭头
		if (getTimesWindowStatus() == WINDOW_OPENED) {
			selectTheNextRow(CURR_ROW_CLASS_NAME_TIMES,CURR_ROW_ATTR_NAME_TIMES,CONTAINER_ID_TABLE);
			//alert("debug");
		}
		else{
			$("#btn-drugtimes").trigger("click");
		}
		return false;
	}
	
	if (oEvent.keyCode == UP_ARROW_CODE) {  //向上箭头
		if (getTimesWindowStatus() == WINDOW_OPENED) {
			selectThePreviousRow(CURR_ROW_CLASS_NAME_TIMES,CURR_ROW_ATTR_NAME_TIMES,CONTAINER_ID_TABLE, 'dict-drug-times');
			//alert("debug");
		}
		return false;
	}
	
	
	
	// 用户按下了回车键
	// 当MODE下拉列表中不为空,选择第一个.如果为空时,不做任何动作.
	if (oEvent.keyCode == 13) {
		//alert("TimesWindowStatus"+getTimesWindowStatus());
		if (getTimesWindowStatus() == WINDOW_OPENED) {
			choiceTheFirstTimes();
		} else {
			if($.trim($(this).val())==""){
				alert("未录入频次",500);
			}
			else{
				$("#drugmode").focus(); 
			}
			
		}
		return false;
	}
}



/**
 * 表格中:剂量单位 keydown 处理器
 * 
 * @param event
 * @returns
 */
function handler_keydown_doseunit_table(ev) {
	var oEvent = ev || event;// 获取事件对象(IE和其他浏览器不一样，这里要处理一下浏览器的兼容性event是IE；ev是chrome等)
	if (oEvent.keyCode == 27) // Esc键的keyCode是27
	{
		//alert("esc:"+getDoseUnitWindowStatus());
		if (getDoseUnitWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownUnit(); // 关闭选择下拉框
			$(this).focus();
			setDoseUnitWindowStatus(WINDOW_CLOSED);
		}
		return false;
	}
	
	if (oEvent.keyCode == DOWN_ARROW_CODE) {  //向下箭头
		if (getDoseUnitWindowStatus() == WINDOW_OPENED) {
			selectTheNextRow(CURR_ROW_CLASS_NAME_DOSE_UNIT_TABLE,CURR_ROW_ATTR_NAME_DOSE_UNIT_TABLE,CONTAINER_ID_UNIT);
		}
		else{
			var bindid=$(this).attr("bind-id");
			$("#btn-doseunit-"+bindid).trigger("click");
		}
		return false;
	}
	
	if (oEvent.keyCode == UP_ARROW_CODE) {  //向上箭头
		if (getDoseUnitWindowStatus() == WINDOW_OPENED) {
			selectThePreviousRow(CURR_ROW_CLASS_NAME_DOSE_UNIT_TABLE,CURR_ROW_ATTR_NAME_DOSE_UNIT_TABLE,CONTAINER_ID_UNIT, 'dict-drug-doseunit');			
		}
		return false;
	}
		
	// 用户按下了回车键
	// 当times列表中不为空,选择第一个.如果为空时,不做任何动作.
	if (oEvent.keyCode == 13) {
		if (getDoseUnitWindowStatus() == WINDOW_OPENED) {
			choiceTheFirstDoseUnit();			
		} else {
			var bindid=$(this).attr("bind-id");
			if($.trim($(this).val())==""){
				alert("未录入剂量单位!",500);
				//setFocus("#drugtimes");
			}
			else{
				$("#drug-times-"+bindid).focus(); // "用药次数"文本框获取焦点
			}
			
		}
		return false;
	}
	
}

/**
 * 输入框:剂量单位  keydown 处理
 * @param ev
 * @returns
 */
function handler_keydown_doseunit(ev){
	var oEvent = ev || event;// 获取事件对象(IE和其他浏览器不一样，这里要处理一下浏览器的兼容性event是IE；ev是chrome等)
	if (oEvent.keyCode == 27) // Esc键的keyCode是27
	{
		//alert("esc:"+getDoseUnitWindowStatus());
		if (getDoseUnitInputWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownTable(); // 关闭选择下拉框
			$(this).focus();
			setDoseUnitInputWindowStatus(WINDOW_CLOSED);
		}
		return false;
	}
	
	if (oEvent.keyCode == DOWN_ARROW_CODE) {  //向下箭头
		if (getDoseUnitInputWindowStatus() == WINDOW_OPENED) {
			selectTheNextRow(CURR_ROW_CLASS_NAME_DOSE_UNIT,CURR_ROW_ATTR_NAME_DOSE_UNIT,CONTAINER_ID_TABLE);
		}
		else{
			$( "#btn-single-dose-unit" ).trigger( "click" );
		}
		return false;
	}
	
	if (oEvent.keyCode == UP_ARROW_CODE) {  //向上箭头
		if (getDoseUnitInputWindowStatus() == WINDOW_OPENED) {
			selectThePreviousRow(CURR_ROW_CLASS_NAME_DOSE_UNIT,CURR_ROW_ATTR_NAME_DOSE_UNIT,CONTAINER_ID_TABLE, 'dict-drug-doseunit');			
		}
		return false;
	}
	
	
	
	
	// 用户按下了回车键
	// 当times列表中不为空,选择第一个.如果为空时,不做任何动作.
	if (oEvent.keyCode == 13) {
		if (getDoseUnitInputWindowStatus() == WINDOW_OPENED) {
			choiceTheFirstDoseUnitInput();			
		} else {
			if($.trim($(this).val())==""){
				alert("未录入剂量单位!",500);
			}
			else{
				$("#drugtimes").focus();
			}
			
		}
		
		return false;
	}
}


/**
 * 疗程(输入文本框) 按钮处理程序
 * @param ev
 * @returns
 */
function handler_keydown_days(ev){
	var oEvent = ev || event;// 获取事件对象(IE和其他浏览器不一样，这里要处理一下浏览器的兼容性event是IE；ev是chrome等)
	if (oEvent.keyCode == 27) // Esc键的keyCode是27
	{
		if (getDaysWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownTable(); // 关闭选择下拉框
			$(this).focus();
			setDaysWindowStatus(WINDOW_CLOSED);
		}
		return false;
	}
	
	
	if (oEvent.keyCode == DOWN_ARROW_CODE) {  //向下箭头
		if (getDaysWindowStatus() == WINDOW_OPENED) {
			selectTheNextRow(CURR_ROW_CLASS_NAME_DAYS,CURR_ROW_ATTR_NAME_DAYS,CONTAINER_ID_TABLE);
		}
		else{
			$("#btn-treatment-days").trigger("click");
		}
		return false;
	}
	
	if (oEvent.keyCode == UP_ARROW_CODE) {  //向上箭头
		if (getDaysWindowStatus() == WINDOW_OPENED) {
			selectThePreviousRow(CURR_ROW_CLASS_NAME_DAYS,CURR_ROW_ATTR_NAME_DAYS,CONTAINER_ID_TABLE, 'dict-drug-days');			
		}
		return false;
	}
	
	
	// 用户按下了回车键
	// 当下拉列表中不为空,选择第一个.如果为空时,不做任何动作.
	if (oEvent.keyCode == 13) {
		if (getDaysWindowStatus() == WINDOW_OPENED) {
			choiceTheFirstDays();			
		} else {
			if($.trim($(this).val())==""){
				alert("未录入疗程!",500);
			}
			else{
				$("#quantity").focus();
			}
			
		}
		
		return false;
	}
}

/**
 * 单次用量 keydown 事件处理，下一个输入框获取焦点
 * 
 * @param event
 * @returns
 */
function handler_keydown_dosage_table(event) {
	if (event.keyCode == "13") {// keyCode=13是回车键 模拟按tab键
		var id = $(this).attr("bind-id");
		var val=$.trim($(this).val());
		if(val==""){
			alert("单次用量为空!",500);
		}
		else if(!isPositiveNumber(val)){
			alert("单次用量需为正数!",500);
		}			
		else{
			$("#drug-doseunit-" + id).focus();
		}
		
		return false;
	}
}

/**
 * 单次剂量输入框  keydown 事件处理程序
 * @param event
 * @returns
 */
function handler_keydown_dosage(event){
	if (event.keyCode == "13") {// keyCode=13是回车键 模拟按tab键		
		var val=$.trim($(this).val());
		if(val==""){
			alert("单次用量为空!",500);
		}
		else if(!isPositiveNumber(val)){
			alert("单次用量需为正数!",500);
		}			
		else{
			autoCalcQuantity_dosage(val);  //自动计算
			$("#single-dose-unit").focus();			
		}
		
		return false;
	}
}

/**
 * 自动计算数量,如果可以计算的话.
 * @returns
 */
function autoCalcQuantity_dosage(dosage_value){
	console.log("debug");
	if (g_currDrug!=null){
		g_currDrug.dosage_value=dosage_value;
		
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




function handler_keydown_days_table(ev) {
	var oEvent = ev || event;// 获取事件对象(IE和其他浏览器不一样，这里要处理一下浏览器的兼容性event是IE；ev是chrome等)
	if (oEvent.keyCode == 27) // Esc键的keyCode是27
	{
		if (getTableDaysWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownUnit(); // 关闭选择下拉框
			$(this).focus();
			setTableDaysWindowStatus(WINDOW_CLOSED);
		}
		return false;
	}
	
	if (oEvent.keyCode == DOWN_ARROW_CODE) {  //向下箭头
		if (getTableDaysWindowStatus() == WINDOW_OPENED) {
			selectTheNextRow(CURR_ROW_CLASS_NAME_DAYS_TABLE,CURR_ROW_ATTR_NAME_DAYS_TABLE,CONTAINER_ID_UNIT);
		}
		else{
			var bindid=$(this).attr("bind-id");
			$("#btn-days-"+bindid).trigger("click");
		}
		return false;
	}
	
	if (oEvent.keyCode == UP_ARROW_CODE) {  //向上箭头
		if (getTableDaysWindowStatus() == WINDOW_OPENED) {
			selectThePreviousRow(CURR_ROW_CLASS_NAME_DAYS_TABLE,CURR_ROW_ATTR_NAME_DAYS_TABLE,CONTAINER_ID_UNIT, 'dict-drug-days');			
		}
		return false;
	}
	
	
	// 用户按下了回车键
	// 当列表中不为空,选择第一个.如果为空时,不做任何动作.
	if (oEvent.keyCode == 13) {
		if (getTableDaysWindowStatus() == WINDOW_OPENED) {
			choiceTableTheFirstDays();
		} else {
			var bindid=$(this).attr("bind-id");
			var val=$.trim($(this).val());
			if (val==""){
				alert("用药天数为空!",500);
			}
			else{
				$("#drug-quantity-" + bindid).focus();
			}
			
		}
		return false;
	}
	
}

/**
 * 数量 keydown 事件处理函数
 */
function handler_keydown_quantity(event) {
	if (event.keyCode == "13") {// keyCode=13是回车键
		
		var validObj=validAddDrug();
		if(validObj.valid==true){
			addDrugIntoTable(); // 将选择的药品加入列表中.
		}
		else{
			alert(validObj.errorMsg,2000);
		}
				
		return false;
	}
}

/**
 * 在加入药品时的有效性验证(输入面板)
 * @returns
 *  返回有效性验证对象
 *  	var err=new Object;
			errorMsg string;
			valid  boolean; 
 * 	如果验证通过返回true;否则返回false;
 */
function validAddDrug(){
	
	//初始化校验对象
	var err=new Object;
	err.errorMsg="";
	err.valid=true;
	
	var prescNo=$("#presc-no").val();
	if(prescNo!=""){
		err.valid=false;
		err.errorMsg=err.errorMsg+"处方已经确定,不可再增加药品"+ ";";
		return err;
	}
	
	var len=getDrugListLength();  //获得药品列表的长度.
	//alert("list length:"+len);
	if(len>=DRUG_MAXNUM_PER_PRESCRIPTION){	
		err.valid=false;
		err.errorMsg=err.errorMsg+"每个处方中药品数不可超过5种!"+ ";";		
	}	
	if(g_currDrug==null){
		err.valid=false;
		err.errorMsg=err.errorMsg+"未选择药品"+ ";";		
	}

	var val="";
	//剂量
	val=$.trim($("#single-dosage").val());
	if(val==""){
		err.valid=false;
		err.errorMsg=err.errorMsg+"单次剂量为空!"+";";
	}
	else if(!isPositiveNumber(val)){
		err.valid=false;
		err.errorMsg=err.errorMsg+"单次剂量需为正数!"+";";
	}
	
	//剂量单位
	val=$.trim($("#single-dose-unit").val());
	if(val==""){
		err.valid=false;
		err.errorMsg=err.errorMsg+"剂量单位为空!"+";";
	}
	//用药次数
	if($.trim($("#drugtimes").val())==""){
		err.valid=false;
		err.errorMsg=err.errorMsg+"未选择频次"+ ";";
	}
	//用药方式
	if($.trim($("#drugmode").val())==""){
		err.valid=false;
		err.errorMsg=err.errorMsg+"未选择用法"+ ";";
	}
	//疗程(用药天数)
	val=$.trim($("#treatment-days").val());
	if(val==""){
		err.valid=false;
		err.errorMsg=err.errorMsg+"疗程为空!"+";";
	}
	
	//数量
	if($.trim($("#quantity").val())==""){
		err.valid=false;
		err.errorMsg=err.errorMsg+"数量为空"+ ";";
	}
	else{
		//判定是否为正整数
		if(!isPositiveNumber($("#quantity").val())){
			err.valid=false;
			err.errorMsg=err.errorMsg+"数量不是正数"+ ";";
		}
		else{
			var sum=calcPrescDrugAmount();
			var subSum=0;
			if(g_currDrug!=null)
				subSum=g_currDrug.saleprice*$("#quantity").val();
			if((sum+subSum)>300){
				err.valid=false;
				err.errorMsg=err.errorMsg+"每个处方中金额不可以超过300元!"+ ";";
			}
		}
	}	
	
	return err;
}


/**--------------------------
 * input event
 --------------------------*/
/**
 * 药品输入框 input event processor
 * @returns
 */
function handler_input_abc() {
	var abc = $(this).val(); // 助记码	
	searchDrugDropdown(abc,"#abc");
}

function searchDrugDropdown(abc,inputBoxId){	
	var inputObj=$(inputBoxId);
	if (getDrugWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.
		Common.showDropdownTable(inputObj);
		Common.setDropdownTalbeWidth(800);  //设置下拉框的宽度
		//Common.setDropdownTalbeHeight(500);  //设置下拉框的高度
		
		setDrugWindowStatus(WINDOW_OPENED);
	}
	//每次查询时均采用默认的页号(第一页)及默认页大小进行查询
	var pageNum=0;
	var pageSize=0;
	loadDrugTable(abc,pageNum,pageSize);
	
}

/**
 * 给药方式输入框 input事件处理函数 
 * 
 * @returns
 */
function handler_input_mode() {
	var abc = $(this).val(); // 助记码
	searchModeDropdown(abc,"#drugmode");
}

function searchModeDropdown(abc,inputBoxId){
	var inputObj=$(inputBoxId);
	
	if (getModeWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.	
		Common.showDropdownTable(inputObj);
		Common.setDropdownTalbeWidth(300);
		setModeWindowStatus(WINDOW_OPENED);
	}
	loadDrugMode(abc);
}

/**
 * 给药次数输入框 input事件处理函数
 * 
 * @returns
 */
function handler_input_times() {
	var abc = $(this).val(); // 助记码
	searchTimesDropdown(abc,"#drugtimes")
}

function searchTimesDropdown(abc,inputBoxId){
	var inputObj=$(inputBoxId);
	if (getTimesWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.
		Common.showDropdownTable(inputObj);
		Common.setDropdownTalbeWidth(300);
		setTimesWindowStatus(WINDOW_OPENED);
	}
	loadDrugTimes(abc);
}

/**
 * 剂量单位(输入框) input事件处理函数
 * 
 * @returns
 */
function handler_input_doseunit(){
	// (1)输入是助记码情况
	var abc = $(this).val(); // 助记码
	searchDoseunitDropdown(abc,"#single-dose-unit")
}

function searchDoseunitDropdown(abc,inputBoxId){
	var inputObj=$(inputBoxId);
	if (getDoseUnitInputWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.
		Common.showDropdownTable(inputObj);
		Common.setDropdownTalbeWidth(300);
		setDoseUnitInputWindowStatus(WINDOW_OPENED);
	}
	loadDrugDoseUnit(abc);
}

/**
 * 疗程(输入框) input事件处理函数
 * 
 * @returns
 */
function handler_input_days(){
	// (1)输入是助记码情况
	var abc = $(this).val(); // 助记码
	searchDaysDropdown(abc,"#treatment-days")
}

function searchDaysDropdown(abc,inputBoxId){
	var inputObj=$(inputBoxId);
	if (getDaysWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.
		Common.showDropdownTable(inputObj);
		Common.setDropdownTalbeWidth(270);
		setDaysWindowStatus(WINDOW_OPENED);
	}
	loadDrugDays(abc);
}

/**
 * 表格中:用药频次	input事件	处理程序
 * @returns
 */
function handler_input_times_table(){
	/*--------------------------
	用户输入
		(1)可能是助记码
		(2)实际的值
	--------------------------*/
	// (1)输入是实际值情况
	var drugId = $(this).attr("bind-id"); // 取得当前编辑的药品ID
	var index = searchDrugById(drugId); // 自g_prescDrugList查询,并置相应属性
	if (index >= 0) {
		var prescDrugList=getDrugList();
		prescDrugList[index].drugtimes = $(this).val();
	}

	// (2)输入是助记码情况
	var abc = $(this).val(); // 助记码
	if (getTableTimesWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.
		Common.showDropdownUnit($(this));
		setTableTimesWindowStatus(WINDOW_OPENED);
	}
	loadTableDrugTimes(abc);
}
/**
 * 表格中:药品用法	input事件	处理程序
 * @returns
 */
function handler_input_mode_table(){
	/*--------------------------
	用户输入
		(1)可能是助记码
		(2)实际的值
	--------------------------*/
	// (1)输入是实际值情况
	var drugId = $(this).attr("bind-id"); // 取得当前编辑的药品ID
	var index = searchDrugById(drugId); // 自g_prescDrugList查询,并置相应属性
	if (index >= 0) {
		var prescDrugList=getDrugList();
		prescDrugList[index].drugmode = $(this).val();
	}

	// (2)输入是助记码情况
	var abc = $(this).val(); // 助记码
	if (getTableModeWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.
		Common.showDropdownUnit($(this));
		setTableModeWindowStatus(WINDOW_OPENED);
	}
	loadTableDrugMode(abc);
}

function handler_input_quantity_table(){
	var drugId = $(this).attr("bind-id"); 		// 取得当前编辑的药品ID
	var index = searchDrugById(drugId); 	// 自g_prescDrugList查询,并置dosage
	if (index >= 0) {
		var prescDrugList=getDrugList();
		prescDrugList[index].quantity = $(this).val();
	}
	
	if($.trim($(this).val())==""){
		alert("数量为空",500);
	}
	else{
		//判定是否为正数
		if(!isPositiveNumber($(this).val())){
			alert("数量不是正数",500);
		}
		else{
			var sum=calcPrescDrugAmount();
			var subsum=prescDrugList[index].quantity*prescDrugList[index].saleprice;
			//显示药品金额及总金额
			$("#presc-drug-sum").text(toDecimal2(sum));  //总金额
			$("#drug-sum-"+drugId).text(toDecimal2(subsum));  //药品金额
			if(sum>300){
				alert("每个处方中金额不可以超过300元!",500);
			}
		}
	}
	
	
}


/**
 * 剂量单位输入框 input事件处理函数
 * 
 * @returns
 */
function handler_input_doseunit_table() {

	/*--------------------------
	用户输入
		(1)可能是助记码
		(2)真正的doseunit
	--------------------------*/
	// alert("doseunit");
	// (1)输入是doseunit情况
	var drugId = $(this).attr("bind-id"); // 取得当前编辑的药品ID
	var index = searchDrugById(drugId); // 自g_prescDrugList查询,并置doseunit
	if (index >= 0) {
		var prescDrugList=getDrugList();
		prescDrugList[index].doseunit = $(this).val();
	}

	// (2)输入是助记码情况
	var abc = $(this).val(); // 助记码
	if (getDoseUnitWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.
		Common.showDropdownUnit($(this));
		setDoseUnitWindowStatus(WINDOW_OPENED);
	}
	loadTableDrugDoseUnit(abc);
}

/**
 * 表格:单次剂量输入框 input 事件处理函数
 * 
 * @returns
 */
function handler_input_dosage_table() {
	var drugId = $(this).attr("bind-id"); // 取得当前编辑的药品ID
	var index = searchDrugById(drugId); // 自g_prescDrugList查询,并置dosage
	if (index >= 0) {
		var prescDrugList=getDrugList();
		prescDrugList[index].dosage = $(this).val();
	}
}

/**
 * 录入文本框单次剂量  input 事件处理函数
 * @returns
 */
function handler_input_dosage(){
	//TODO 暂时不用处理相应的业务.
	return false;
}

/**
 * 用药天数 input 事件处理函数
 * 
 * @returns
 */
function handler_input_days_table() {
	/*--------------------------
	用户输入
		(1)可能是助记码
		(2)真正的doseunit
	--------------------------*/
	// alert("doseunit");
	// (1)输入是doseunit情况
	var drugId = $(this).attr("bind-id"); // 取得当前编辑的药品ID
	var index = searchDrugById(drugId); // 自g_prescDrugList查询,并置doseunit
	if (index >= 0) {
		var prescDrugList=getDrugList();
		prescDrugList[index].days = $(this).val();
	}

	// (2)输入是助记码情况
	var abc = $(this).val(); // 助记码
	if (getTableDaysWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.
		Common.showDropdownUnit($(this));
		setTableDaysWindowStatus(WINDOW_OPENED);
	}
	loadTableDrugDays(abc);
}

/**-----------------------------------
 * click event handler
 -----------------------------------*/
/**
 * 清屏处理函数
 * @returns
 */
function handler_click_btn_cleartable(){
	//(1)显示确认对话框
	showConfirmWindow_cleartable();
}


function handler_click_btn_abc(){
	searchDrugDropdown("","#abc");
	setFocus("#abc");
}

function handler_click_btn_singledoseunit(){
	searchDoseunitDropdown("","#single-dose-unit");
	setFocus("#single-dose-unit");
}
function handler_click_btn_drugtimes(){
	searchTimesDropdown("","#drugtimes");
	setFocus("#drugtimes");
}
function handler_click_btn_drugmode(){
	searchModeDropdown("","#drugmode");
	setFocus("#drugmode");
}
function handler_click_btn_treatmentdays(){
	searchDaysDropdown("","#treatment-days");
	setFocus("#treatment-days");
}
function handler_click_btn_add_drug(){
	var e = jQuery.Event("keydown");//模拟一个键盘事件 
	e.keyCode = 13;//keyCode=13是回车 
	$("#quantity").trigger(e);
}

function handler_click_select_all(){
	var checkStatus=$(this).is(':checked');
	var drugList=getDrugList();
	for(var i=0;i<drugList.length;i++){
		var checkId="check-drug-"+drugList[i].id;
		//$("#"+checkId).attr("checked",checkStatus);
		$("#"+checkId).prop("checked",checkStatus);
	}
}

function handler_click_delete_row(){
	var prescNo=$("#presc-no").val();
	if(prescNo!=""){
		alert("处方已经确定,不可删除!",2000);
		return false;
	}
	showConfirmWindow_delete_selected_row();
}

function handler_click_btn_save_template(){
	//alert("click btn_save_template!");
	// 判断是否已存在，如果已存在则直接显示
	if (M.dialog1) {
		M.dialog1.destroy();
	}
	M.dialog1 = jqueryAlert({
		'title' : '提示',
		'content' : '<img src="'+BASE_CONTEXT_PATH+'/static/images/warning.png">'+ '    请输入模板名称:<input type="text" name="template-name" id="template-name" value="" >  ',
		'modal' : true,
		'buttons' : {
			'确定' : function() {
				//先做有效性验证
				if($.trim($('#template-name').val())==''){
					alert('模板名称为空!',2000);
				}
				else{
					//TODO 业务处理
					var templateName=$.trim($('#template-name').val());  //模板名称
					//异步发送请求
					saveTemplate(templateName);  //保存模板
					
					M.dialog1.close();
					M.dialog1.destroy();
					M.dialog1 = null;						
				}
			},
			'取消' : function() {
				M.dialog1.close();
				M.dialog1.destroy();
				M.dialog1 = null;
			}
		}
	});
	
	$('#template-name').focus();  //模板名称获取焦点.
}

function saveTemplate(templateName) {
	url = BASE_CONTEXT_PATH + "/template/save";

	var parmObj=new Object();
	parmObj.patientId=$("#patient").attr("bind-id");
	parmObj.doctorId=$("#doctor").attr("bind-id");
	parmObj.departmentId=$("#department").attr("bind-id");
	parmObj.templateName=templateName;  //模板名称
	parmObj.prescDrugs=getDrugList();
	
	//var parms = g_prescDrugList;
	// alert("array length:"+parms.length);
	// 采用AJAX方式发送POST请求
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json", // 指定发送到服务器时参数的格式
		dataType : "json", // 指定自服务器接收到的数据格式
		data : JSON.stringify(parmObj), // 传递的参数,JSON格式。
		success : function(res) { // 请求正确之后的操作
			if (res != null) {
				// console.log(res);
				// var obj = $.parseJSON(res);
				if (res.result_code == "success") {
					// util.message(obj.result_msg,"","info");
					var msg = res.result_msg; //保存成功信息
					alert("保存模板成功!"+msg, 2000,'right');
				} else {
					alert(obj.result_err_msg,5000,'error');
				}
			}
		},
		error : function(result) { // 请求失败之后的操作
			alert("保存失败,请联系系统管理员!", 5000,'error');
		}
	});
}


function handler_click_btn_edit_template(){
	alert("click btn_edit_template!");
}

function handler_click_btn_use_template(){
	alert("click btn_use_template");
}

/**
 * 表格:剂量单位下拉列表按钮-click handler
 * @returns
 */
function handler_click_btn_doseunit_table(){
	var id=$(this).attr('bind-id');
	var input="drug-doseunit-"+id;
	var inputObj=$("#"+input);
  
	inputObj.trigger("focus"); //置全局变量	
	//打开下拉框
	var abc = ""; // 助记码
	if (getDoseUnitWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.
		Common.showDropdownUnit(inputObj);
		setDoseUnitWindowStatus(WINDOW_OPENED);
	}
	loadTableDrugDoseUnit(abc);
}

/**
 * 表格:频次下拉列表按钮-click handler
 * @returns
 */
function handler_click_btn_times_table(){
	var id=$(this).attr('bind-id');
	var input="drug-times-"+id;
	var inputObj=$("#"+input);
  
	inputObj.trigger("focus"); //置全局变量	
	//打开下拉框
	var abc = ""; //助记码
	if (getTableTimesWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.
		//alert("display");
		Common.showDropdownUnit(inputObj);
		setTableTimesWindowStatus(WINDOW_OPENED);
	}
	loadTableDrugTimes(abc);
}

/**
 * 表格:用药方式下拉列表按钮-click handler
 * @returns
 */
function handler_click_btn_mode_table(){
	var id=$(this).attr('bind-id');
	var input="drug-mode-"+id;
	var inputObj=$("#"+input);
  
	inputObj.trigger("focus"); //置全局变量	
	//打开下拉框
	var abc = ""; //助记码
	if (getTableModeWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.
		Common.showDropdownUnit(inputObj);
		setTableModeWindowStatus(WINDOW_OPENED);
	}
	loadTableDrugMode(abc);
}

function handler_click_btn_days_table(){
	var id=$(this).attr('bind-id');
	var input="drug-days-"+id;
	var inputObj=$("#"+input);
  
	inputObj.trigger("focus"); //置全局变量	
	//打开下拉框
	var abc = ""; //助记码
	if (getTableDaysWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.
		Common.showDropdownUnit(inputObj);
		setTableDaysWindowStatus(WINDOW_OPENED);
	}
	loadTableDrugDays(abc);
}


/**---------------------------------
 * blur event
 ---------------------------------*/
/**
 * 药品助词码  blur event
 * @returns
 */

function handler_blur_abc(){
	if(getDrugWindowStatus()==WINDOW_OPENED){
		Common.hideDropdownTable();  //关闭选择下拉框		
		setDrugWindowStatus(WINDOW_CLOSED);
	}
}

function handler_blur_mode(){
	if(getModeWindowStatus()==WINDOW_OPENED){
		Common.hideDropdownTable();  //关闭选择下拉框		
		setModeWindowStatus(WINDOW_CLOSED);
	}
}

function handler_blur_times(){
	if(getTimesWindowStatus()==WINDOW_OPENED){
		Common.hideDropdownTable();  //关闭选择下拉框		
		setTimesWindowStatus(WINDOW_CLOSED);
	}
}

function handler_blur_doseunit(){
	if(getDoseUnitWindowStatus()==WINDOW_OPENED){
		Common.hideDropdownUnit();  //关闭选择下拉框		
		setDoseUnitWindowStatus(WINDOW_CLOSED);
	}
}