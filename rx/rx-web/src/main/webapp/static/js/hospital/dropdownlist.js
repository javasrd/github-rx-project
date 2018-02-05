// 常量定义
var WINDOW_CLOSED = 0;
var WINDOW_OPENED = 1;

// 四个下拉窗口打开状态
var g_dict_drug_window_status = WINDOW_CLOSED;
var g_dict_mode_window_status = WINDOW_CLOSED;
var g_dict_times_window_status = WINDOW_CLOSED;
var g_dict_doseunit_input_window_status=WINDOW_CLOSED; //输入框
var g_dict_days_window_status=WINDOW_CLOSED; 			//疗程

var g_dict_doseunit_window_status = WINDOW_CLOSED;  //表格内

//药品
// getter and setter
function getDrugWindowStatus() {
	return g_dict_drug_window_status;
}
function setDrugWindowStatus(status) {
	g_dict_drug_window_status = status;
	if(status==WINDOW_OPENED){
		setModeWindowStatus(WINDOW_CLOSED);
		setTimesWindowStatus(WINDOW_CLOSED);
		setDoseUnitInputWindowStatus(WINDOW_CLOSED);
		setDaysWindowStatus(WINDOW_CLOSED);
	}
}

//用法
function getModeWindowStatus() {
	return g_dict_mode_window_status;
}
function setModeWindowStatus(status) {
	g_dict_mode_window_status = status;
	if(status==WINDOW_OPENED){
		setDrugWindowStatus(WINDOW_CLOSED);
		setTimesWindowStatus(WINDOW_CLOSED);
		setDoseUnitInputWindowStatus(WINDOW_CLOSED);
		setDaysWindowStatus(WINDOW_CLOSED);
	}
}

//频次
function getTimesWindowStatus() {
	return g_dict_times_window_status;
}
function setTimesWindowStatus(status) {
	g_dict_times_window_status = status;
	if(status==WINDOW_OPENED){
		setDrugWindowStatus(WINDOW_CLOSED);
		setModeWindowStatus(WINDOW_CLOSED);
		setDoseUnitInputWindowStatus(WINDOW_CLOSED);
		setDaysWindowStatus(WINDOW_CLOSED);
	}
}

//剂量单位(文本输入框)
function getDoseUnitInputWindowStatus() {
	return g_dict_doseunit_input_window_status;
}
function setDoseUnitInputWindowStatus(status) {
	g_dict_doseunit_input_window_status = status;
	if(status==WINDOW_OPENED){
		setDrugWindowStatus(WINDOW_CLOSED);
		setModeWindowStatus(WINDOW_CLOSED);
		setTimesWindowStatus(WINDOW_CLOSED);
		setDaysWindowStatus(WINDOW_CLOSED);
	}
}

//疗程
function getDaysWindowStatus() {
	return g_dict_days_window_status;
}

function setDaysWindowStatus(status) {
	g_dict_days_window_status = status;
	if(status==WINDOW_OPENED){
		setDrugWindowStatus(WINDOW_CLOSED);
		setModeWindowStatus(WINDOW_CLOSED);
		setTimesWindowStatus(WINDOW_CLOSED);
		setDoseUnitInputWindowStatus(WINDOW_CLOSED);
	}
}

//剂量单位(表格)
function getDoseUnitWindowStatus() {
	return g_dict_doseunit_window_status;
}

function setDoseUnitWindowStatus(status) {
	g_dict_doseunit_window_status = status;
}