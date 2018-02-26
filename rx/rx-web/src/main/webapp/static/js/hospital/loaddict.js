/********************************************
 * 加载药品列表
 ********************************************/
function loadDrugTable(abc) {
	var url = "/drug/category";
	var parms = {
		abc : abc
	};
	var callbackFunc = loadDrugTableSuccess;
	var containerId = ".dropdown-table";
	// console.log("ok!");
	loadPage(containerId, url, parms, callbackFunc);
}

// 加载成功后的回调函数
function loadDrugTableSuccess() {
	
}

/****************************************
 * 加载剂量单位(input box)
 ****************************************/
function loadDrugDoseUnit(abc) {
	var url = "/drug/doseunit";
	var parms = {
		abc : abc
	};
	var callbackFunc = null;
	var containerId = ".dropdown-table";
	loadPage(containerId, url, parms, callbackFunc);
}

/*******************************************
 * 加载给药次数
 *******************************************/
function loadDrugTimes(abc) {
	var url = "/drug/times";
	var parms = {
		abc : abc
	};
	var callbackFunc = null;
	var containerId = ".dropdown-table";
	loadPage(containerId, url, parms, callbackFunc);
}

/******************************************
 * 加载给药方式
 ******************************************/
function loadDrugMode(abc) {
	var url = "/drug/mode";
	var parms = {
		abc : abc
	};
	var callbackFunc = null;
	var containerId = ".dropdown-table";
	loadPage(containerId, url, parms, callbackFunc);
}


/*******************************************
 * 加载用药疗程
 *******************************************/
function loadDrugDays(abc) {
	var url = "/drug/days";
	var parms = {
		abc : abc
	};
	var callbackFunc = null;
	var containerId = ".dropdown-table";
	loadPage(containerId, url, parms, callbackFunc);
}

//--------------------表格中下拉列表---------------------

/***************************************
 * 加载剂量单位(表格)
 ***************************************/
function loadTableDrugDoseUnit(abc) {
	var url = "/drug/doseunit_table";
	var parms = {
		abc : abc
	};
	var callbackFunc = null;
	var containerId = ".dropdown-unit";
	loadPage(containerId, url, parms, callbackFunc);
}

/*******************************************
 * 加载给药次数
 *******************************************/
function loadTableDrugTimes(abc) {
	var url = "/drug/times_table";
	var parms = {
		abc : abc
	};
	var callbackFunc = null;
	var containerId = ".dropdown-unit";
	loadPage(containerId, url, parms, callbackFunc);
}

/******************************************
 * 加载给药方式
 ******************************************/
function loadTableDrugMode(abc) {
	var url = "/drug/mode_table";
	var parms = {
		abc : abc
	};
	var callbackFunc = null;
	var containerId = ".dropdown-unit";
	loadPage(containerId, url, parms, callbackFunc);
}


/*******************************************
 * 加载用药疗程
 *******************************************/
function loadTableDrugDays(abc) {
	var url = "/drug/days_table";
	var parms = {
		abc : abc
	};
	var callbackFunc = null;
	var containerId = ".dropdown-unit";
	loadPage(containerId, url, parms, callbackFunc);
}


