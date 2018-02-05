// 常量定义
var WINDOW_CLOSED = 0;
var WINDOW_OPENED = 1;

// 四个下拉窗口打开状态
var g_dict_drug_window_status = WINDOW_CLOSED;
var g_dict_mode_window_status = WINDOW_CLOSED;
var g_dict_times_window_status = WINDOW_CLOSED;
var g_dict_doseunit_window_status = WINDOW_CLOSED;

// getter and setter
function getDrugWindowStatus() {
	return g_dict_drug_window_status;
}
function setDrugWindowStatus(status) {
	g_dict_drug_window_status = status;
	if(status==WINDOW_OPENED){
		setModeWindowStatus(WINDOW_CLOSED);
		setTimesWindowStatus(WINDOW_CLOSED);
	}
}

function getModeWindowStatus() {
	return g_dict_mode_window_status;
}
function setModeWindowStatus(status) {
	g_dict_mode_window_status = status;
	if(status==WINDOW_OPENED){
		setDrugWindowStatus(WINDOW_CLOSED);
		setTimesWindowStatus(WINDOW_CLOSED);
	}
}

function getTimesWindowStatus() {
	return g_dict_times_window_status;
}
function setTimesWindowStatus(status) {
	g_dict_times_window_status = status;
	if(status==WINDOW_OPENED){
		setDrugWindowStatus(WINDOW_CLOSED);
		setModeWindowStatus(WINDOW_CLOSED);
	}
}

function getDoseUnitWindowStatus() {
	return g_dict_doseunit_window_status;
}

function setDoseUnitWindowStatus(status) {
	g_dict_doseunit_window_status = status;
}