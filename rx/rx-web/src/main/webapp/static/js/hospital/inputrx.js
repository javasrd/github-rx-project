/*******************************************************************************
 * 加载药品列表
 ******************************************************************************/
function loadDrugTable(abc) {
	var url = "/drug/category";
	var parms = {
		abc : abc
	};
	var callbackFunc = loadDrugTableSuccess;
	var containerId = ".dropdown-table";
	//console.log("ok!");
	loadPage(containerId, url, parms, callbackFunc);
}

// 加载成功后的回调函数
function loadDrugTableSuccess() {
}

/*******************************************************************************
 * 加载给药方式
 ******************************************************************************/
function loadDrugMode(abc) {
	var url = "/drug/mode";
	var parms = {
		abc : abc
	};
	var callbackFunc =null;
	var containerId = ".dropdown-table";	
	loadPage(containerId, url, parms, callbackFunc);
}

/*******************************************************************************
 * 加载给药次数
 ******************************************************************************/
function loadDrugTimes(abc) {
	var url = "/drug/times";
	var parms = {
		abc : abc
	};
	var callbackFunc =null;
	var containerId = ".dropdown-table";	
	loadPage(containerId, url, parms, callbackFunc);
}

/*************************************
  	IE浏览器兼容性
 ************************************/
function isIE() {
	// for ie
	if (document.all) {
		$('input[type="text"]').each(function() {
			var that = this;

			if (this.attachEvent) {
				this.attachEvent('onpropertychange', function(e) {
					if (e.propertyName != 'value')
						return;
					$(that).trigger('input');
				});
			}
		})
	}
}


function resetCounter(that){
	counter=0;
}

/*******************************************************************************
 * 页面加载时自动执行此函数
 ******************************************************************************/
var counter=0; //标志,用于防止多次打开关闭(闪烁)
$(function() {

	isIE();

	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	$("#abc").on("input", function() {
		var abc = $("#abc").val(); // 助记码		
		if(counter==0){ //如果是首次调用时.
			Common.showDropdownTable($(this))
			counter=1;
		}
		
		loadDrugTable(abc);
	});
	
	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	$("#drugmode").on("input", function() {
		var abc = $("#drugmode").val(); // 助记码		
		if(counter==0){ //如果是首次调用时.
			Common.showDropdownTable($(this))
			counter=1;
		}		
		loadDrugMode(abc);
	});
	
	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	$("#drugtimes").on("input", function() {
		var abc = $("#drugtimes").val(); // 助记码		
		if(counter==0){ //如果是首次调用时.
			Common.showDropdownTable($(this))
			counter=1;
		}		
		loadDrugTimes(abc);
	});

});