/*******************************************************************************
 * 加载药品列表
 ******************************************************************************/
function loadDrugTable(abc) {
	var url = "/drug/drugtable";
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
var counter=0; //计数器
$(function() {

	isIE();

	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	$("#abc").on("input", function() {
		var abc = $("#abc").val(); // 助记码		
		if(counter==0){ //如果是首次调用时.
			Common.showDropdownTable($(this))
		}
		counter=counter+1;
		loadDrugTable(abc);
	});

});