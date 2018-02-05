/*******************************************************************************
 * 事件处理函数
 ******************************************************************************/
/**-----------------------------------
 * keydown event
 -----------------------------------*/
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
			$("#abc").focus(); // 给药方式文本框获取焦点
			setDrugWindowStatus(WINDOW_CLOSED);
		}
		return false;

	}
	// 用户按下了回车键
	// 当药品列表中不为空,选择第一个.如果为空时,不做任何动作.
	if (oEvent.keyCode == 13) {
		if (getDrugWindowStatus() == WINDOW_OPENED) {
			choiceTheFirstDrug();			
		}
		else{
			if(g_currDrug==null){
				alert("未选择药品",500);
				//setFocus("#abc");
			}
				
		}
		return false;
	}

}

/**
 * 表格中频次-keydown事件处理程序
 * @param ev
 * @returns
 */
function handler_keydown_drugtimes_table(ev){
	alert("增加业务逻辑!-drugtimes_table  keydown!");
	return false;
}

function handler_keydown_drugmode_table(ev){
	alert("增加业务逻辑!-drugtmode_table  keydown!");
	return false;
}

function handler_keydown_quantity_table(ev){
	alert("增加业务逻辑!-quantity_table  keydown!");
	return false;
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
	// 用户按下了回车键
	// 当药品列表中不为空,选择第一个.如果为空时,不做任何动作.
	if (oEvent.keyCode == 13) {
		if (getModeWindowStatus() == WINDOW_OPENED) {
			choiceTheFirstMode();
		} else {
			if($.trim($("#drugmode").val())==""){
				alert("未录入药品用法",500);
				//setFocus("#drugmode");
			}								
			//$("#drugtimes").focus(); // "给药次数"文本框获取焦点
			
		}
		return false;
	}
}

/**
 * 给药次数
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
	// 用户按下了回车键
	// 当MODE下拉列表中不为空,选择第一个.如果为空时,不做任何动作.
	if (oEvent.keyCode == 13) {
		//alert("TimesWindowStatus"+getTimesWindowStatus());
		if (getTimesWindowStatus() == WINDOW_OPENED) {
			choiceTheFirstTimes();
		} else {
			if($.trim($("#drugtimes").val())==""){
				alert("未录入频次",500);
				//setFocus("#drugtimes");
			}
			//$("#quantity").focus(); // "数量"文本框获取焦点
		}
		return false;
	}
}



/**
 * 剂量单位 keydown 处理器
 * 
 * @param event
 * @returns
 */
function handler_keydown_doseunit(ev) {
	var oEvent = ev || event;// 获取事件对象(IE和其他浏览器不一样，这里要处理一下浏览器的兼容性event是IE；ev是chrome等)
	if (oEvent.keyCode == 27) // Esc键的keyCode是27
	{
		alert("esc:"+getDoseUnitWindowStatus());
		if (getDoseUnitWindowStatus() == WINDOW_OPENED) {
			Common.hideDropdownUnit(); // 关闭选择下拉框
			$(this).focus();
			setDoseUnitWindowStatus(WINDOW_CLOSED);
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
			//$("#drug-days-"+bindid).focus(); // "天数"文本框获取焦点
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
function handler_keydown_dosage(event) {
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

function handler_keydown_days(event) {
	if (event.keyCode == "13") {// keyCode=13是回车键
		var val=$.trim($(this).val());
		if (val==""){
			alert("用药天数为空!",500);
		}
		else if(!isUnsignedInteger(val)){
			alert("用药天数不是正整数!!",500);
		}
		else{
			setFocus("#abc");
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
 * 在加入药品时的有效性验证
 * @returns
 *  返回有效性验证对象
 *  	var err=new Object;
			errorMsg string;
			valid  boolean; 
 * 	如果验证通过返回true;否则返回false;
 */
function validAddDrug(){
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
	if(len>=5){	
		err.valid=false;
		err.errorMsg=err.errorMsg+"每个处方中药品数不可超过5种!"+ ";";		
	}	
	if(g_currDrug==null){
		err.valid=false;
		err.errorMsg=err.errorMsg+"未选择药品"+ ";";		
	}
	if($.trim($("#drugmode").val())==""){
		err.valid=false;
		err.errorMsg=err.errorMsg+"未选择用法"+ ";";
	}
	if($.trim($("#drugtimes").val())==""){
		err.valid=false;
		err.errorMsg=err.errorMsg+"未选择频次"+ ";";
	}
	if($.trim($("#quantity").val())==""){
		err.valid=false;
		err.errorMsg=err.errorMsg+"数量为空"+ ";";
	}
	else{
		//判定是否为正整数
		if(!isUnsignedInteger($("#quantity").val())){
			err.valid=false;
			err.errorMsg=err.errorMsg+"数量不是正整数"+ ";";
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


/**-----------------------------------
 * click event handler
 -----------------------------------*/
/**
 * 清屏处理函数
 * @returns
 */
function handler_click_btn_cleartable(){
	/*var len=getDrugListLength();  //获得药品列表的长度.
	if(len<=0){
		alert("尚未录入药品,不必清屏!",1000);
		return false;
	}*/
	
	//(1)显示确认对话框
	showConfirmWindow_cleartable();
}

/**--------------------------
 * input event
 --------------------------*/
function handler_input_abc() {
	var abc = $(this).val(); // 助记码
	if (getDrugWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.
		Common.showDropdownTable($(this))
		setDrugWindowStatus(WINDOW_OPENED);
	}
	loadDrugTable(abc);
}

/**
 * 给药方式输入框 input事件处理函数 
 * 
 * @returns
 */
function handler_input_mode() {
	var abc = $(this).val(); // 助记码
	if (getModeWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.	
		Common.showDropdownTable($(this))
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
	if (getTimesWindowStatus() == WINDOW_CLOSED) { // 如果是首次调用时.
		Common.showDropdownTable($(this))
		setTimesWindowStatus(WINDOW_OPENED);
	}
	loadDrugTimes(abc);
}

/**
 * 表格中:用药频次	input事件	处理程序
 * @returns
 */
function handler_input_drugtimes_table(){
	alert("增加频次INPUT业务逻辑");
}

/**
 * 表格中:药品用法	input事件	处理程序
 * @returns
 */
function handler_input_drugmode_table(){
	alert("增加用法mode INPUT业务逻辑");
}

function handler_input_quantity_table(){
	alert("增加quantity INPUT业务逻辑");
}


/**
 * 剂量单位输入框 input事件处理函数
 * 
 * @returns
 */
function handler_input_doseunit() {

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
		Common.showDropdownUnit(this);
		setDoseUnitWindowStatus(WINDOW_OPENED);
	}
	loadDrugDoseUnit(abc);
}

/**
 * 剂量输入框 input 事件处理函数
 * 
 * @returns
 */
function handler_input_dosage() {
	var drugId = $(this).attr("bind-id"); // 取得当前编辑的药品ID
	var index = searchDrugById(drugId); // 自g_prescDrugList查询,并置dosage
	if (index >= 0) {
		var prescDrugList=getDrugList();
		prescDrugList[index].dosage = $(this).val();
	}
}

/**
 * 用药天数 input 事件处理函数
 * 
 * @returns
 */
function handler_input_days() {
	var drugId = $(this).attr("bind-id"); // 取得当前编辑的药品ID
	// 自g_prescDrugList查询,并置days属性
	var index = searchDrugById(drugId);
	if (index >= 0) {
		var prescDrugList=getDrugList();
		prescDrugList[index].days = $(this).val();
	}
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