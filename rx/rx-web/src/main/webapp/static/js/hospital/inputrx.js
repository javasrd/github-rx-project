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
	// console.log("ok!");
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
	var callbackFunc = null;
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
	var callbackFunc = null;
	var containerId = ".dropdown-table";
	loadPage(containerId, url, parms, callbackFunc);
}

/*******************************************************************************
 * 加载剂量单位
 ******************************************************************************/
function loadDrugDoseUnit(abc) {
	var url = "/drug/doseunit";
	var parms = {
		abc : abc
	};
	var callbackFunc = null;
	var containerId = ".dropdown-unit";
	loadPage(containerId, url, parms, callbackFunc);
}

/*******************************************************************************
 * 加载打印模板,加载成功后,触发打印预览
 ******************************************************************************/

/**
 * 加载打印模板(for preview)
 * @param type 表示处方类型,  1:正方   2:副方
 * @returns
 */
function loadPrintTemplate() {
	
	var url = "/presc/printtemplate";
	
	var parmObj=new Object();
	parmObj.patientId=$("#patient").attr("bind-id");
	parmObj.doctorId=$("#doctor").attr("bind-id");
	parmObj.departmentId=$("#department").attr("bind-id");
	parmObj.type=1;  //1:正方   2:副方
	parmObj.prescNo=$("#presc-no").val();  //处方号
	parmObj.prescDrugs=g_prescDrugList;
	
	var parms = {jsonPresc:JSON.stringify(parmObj)}; //参数jsonPresc的格式为json	
	var callbackFunc = loadPrintTemplate_copy;
	var containerId = "#printarea1";
	loadPage(containerId, url, parms, callbackFunc);
}

/**
 * 加载处方副本 (for preview)
 * @returns
 */
function loadPrintTemplate_copy() {
	
	var url = "/presc/printtemplate";
	
	var parmObj=new Object();
	parmObj.patientId=$("#patient").attr("bind-id");
	parmObj.doctorId=$("#doctor").attr("bind-id");
	parmObj.departmentId=$("#department").attr("bind-id");
	parmObj.type=2;  //1:正方   2:副方
	parmObj.prescNo=$("#presc-no").val();  //处方号
	parmObj.prescDrugs=g_prescDrugList;
	
	var parms = {jsonPresc:JSON.stringify(parmObj)}; //参数jsonPresc的格式为json	
	var callbackFunc = printPreview;
	var containerId = "#printarea2";
	loadPage(containerId, url, parms, callbackFunc);
}

// 加载打印模板成功后-回调函数
function printPreview(){
	// (1)生成条形码.
	var crNo=$("#patient-crno").text();
	var prescNo=$("#presc-no").val();
	
	generateBarcode(crNo,"code128",".barcode-crno");  //生成病历条码
	generateBarcode(prescNo,"code128",".barcode-pn");  //生成处方条码
	
	// (2)触发打印预览.
	$("#btn-print-preview").trigger("click");
}

/**
 * 加载打印模板(for print)
 * @param type 表示处方类型,  1:正方   2:副方
 * @returns
 */
function loadPrintTemplate_print() {
	
	var url = "/presc/printtemplate";
	
	var parmObj=new Object();
	parmObj.patientId=$("#patient").attr("bind-id");
	parmObj.doctorId=$("#doctor").attr("bind-id");
	parmObj.departmentId=$("#department").attr("bind-id");
	parmObj.type=1;  //1:正方   2:副方
	parmObj.prescNo=$("#presc-no").val();  //处方号
	parmObj.prescDrugs=g_prescDrugList;
	
	var parms = {jsonPresc:JSON.stringify(parmObj)}; //参数jsonPresc的格式为json	
	var callbackFunc = loadPrintTemplate_print_copy;
	var containerId = "#printarea1";
	loadPage(containerId, url, parms, callbackFunc);
}

/**
 * 为打印加载副本
 * @returns
 */
function loadPrintTemplate_print_copy() {
	
	var url = "/presc/printtemplate";
	
	var parmObj=new Object();
	parmObj.patientId=$("#patient").attr("bind-id");
	parmObj.doctorId=$("#doctor").attr("bind-id");
	parmObj.departmentId=$("#department").attr("bind-id");
	parmObj.type=2;  //1:正方   2:副方
	parmObj.prescNo=$("#presc-no").val();  //处方号
	parmObj.prescDrugs=g_prescDrugList;
	
	var parms = {jsonPresc:JSON.stringify(parmObj)}; //参数jsonPresc的格式为json	
	var callbackFunc = print_prescription;
	var containerId = "#printarea2";
	loadPage(containerId, url, parms, callbackFunc);
}

// 加载打印模板成功后-回调函数
function print_prescription(){
	// (1)生成条形码.
	var crNo=$("#patient-crno").text();
	var prescNo=$("#presc-no").val();
	
	generateBarcode(crNo,"code128",".barcode-crno");  //生成病历条码
	generateBarcode(prescNo,"code128",".barcode-pn");  //生成处方条码
	// (2)打印.
	$.print("#printarea");
}

/*******************************************************************************
 * 将选择的药品加入列表中
 ******************************************************************************/
function addDrugIntoTable() {
	// 需要加入的内容如下所示:
	/*
	 * 
	 * <th>商品码</th> <th>商品名</th> <th>规格</th> <th>用法</th> <th>频次</th>
	 * <th class="input_width">单次用量</th> <th class="input_width">剂量单位</th>
	 * <th class="input_width">用药天数</th> <th class="input_width">单位</th>
	 * <th class="small_width">单价</th> <th class="small_width">数量</th>
	 * <th class="small_width">金额</th>
	 * 
	 * <tr th:remove="all"> <td>此处存放药品名称</td> <td></td> <td></td> <td></td>
	 * <td></td> <td class="input_width input_td"><input class="form-control
	 * input-sm"></td> <td class="input_width input_td"><input
	 * class="form-control input-sm" onfocus="Common.showDropdownUnit(this)"
	 * onblur="Common.hideDropdownUnit()"></td>
	 * <td class="input_width input_td"><input class="form-control input-sm"></td>
	 * <td class="input_width"></td> <td class="small_width"></td>
	 * <td class="small_width"></td> <td class="small_width"></td> </tr>
	 * 
	 * g_currDrug.wareid=$("#wareid-"+drugId).text();
	 * g_currDrug.warename=$("#warename-"+drugId).text();
	 * g_currDrug.warespec=$("#warespec-"+drugId).text();
	 * g_currDrug.saleprice=$("#saleprice-"+drugId).text();
	 * g_currDrug.wareunit=$("#wareunit-"+drugId).text();
	 */

	//getInputDoseUnitId(this)
	var drugItem = '<tr ondblclick="delSelectedDrug(this)" bind-id=' + '"'
			+ g_currDrug.id
			+ '"'
			+ '>'
			+ '<td>'
			+ g_currDrug.wareid
			+ '</td>'
			+ '<td>'
			+ g_currDrug.warename
			+ '</td>'
			+ '<td>'
			+ g_currDrug.warespec
			+ '</td>'
			+ '<td>'
			+ $("#drugmode").val()
			+ '</td>'
			+ '<td>'
			+ $("#drugtimes").val()
			+ '</td>'
			+ '<td class="input_width input_td"><input class="form-control input-sm dosage" id="drug-dosage-'
			+ g_currDrug.id
			+ '"'
			+ ' bind-id="'
			+ g_currDrug.id
			+ '"'
			+ '></td>'
			+ '<td class="input_width input_td"><input class="form-control input-sm dose-unit" onfocus="getInputDoseUnitId(this)" id="drug-doseunit-'
			+ g_currDrug.id
			+ '"'
			+ ' bind-id="'
			+ g_currDrug.id
			+ '"'
			+ '></td>'
			+ '<td class="input_width input_td"><input class="form-control input-sm days" id="drug-days-'
			+ g_currDrug.id + '"' + ' bind-id="' + g_currDrug.id + '"'
			+ '></td>' + '<td class="input_width">' + g_currDrug.wareunit
			+ '</td>' + '<td class="small_width">' + g_currDrug.saleprice
			+ '</td>' + '<td class="small_width">' + $("#quantity").val()
			+ '</td>' + '<td class="small_width">' + $("#quantity").val()
			* g_currDrug.saleprice + '</td>' + '</tr>';
	
	var drugItem_test='<tr>'+
        '<td>此处存放药品名称12313123</td>'+
        '<td></td>'+
        '<td></td>'+
        '<td></td>'+
        '<td></td>'+
        '<td class="input_width input_td"><input class="form-control input-sm"></td>'+
        '<td class="input_width input_td"><input class="form-control input-sm" onfocus="Common.showDropdownUnit(this)" onblur="Common.hideDropdownUnit()"></td>'+
        '<td class="input_width input_td"><input class="form-control input-sm"></td>'+
        '<td class="input_width"></td>'+
        '<td class="small_width"></td>'+
        '<td class="small_width"></td>'+
        '<td class="small_width"></td>'+
      '</tr> ';

	$("#drug-items").append(drugItem); // 加入显示列表中.	
	
	// 收集用药书指导及数量 加入到全局列表中
	g_currDrug.drugmode = $("#drugmode").val(); // 给药方式
	g_currDrug.drugtimes = $("#drugtimes").val(); // 给药次数
	g_currDrug.quantity = $("#quantity").val(); // 数量
	g_currDrug.dosage = $("#drug-dosage-" + g_currDrug.id).val(); // 单次剂量
	g_currDrug.doseunit = $("#drug-doseunit-" + g_currDrug.id).val(); // 剂量单位
	g_currDrug.days = $("#drug-days-" + g_currDrug.id).val(); // 用药天数
	g_currDrug.patientid = $("#patient").attr("bind-id"); // 患者ID
	g_currDrug.doctorid = $("#doctor").attr("bind-id"); // 医生ID

	// 将药品加入到药品列表
	/*
	 * var newDrug=new Object(); jQuery.each(g_currDrug, function(i, val) {
	 * newDrug.i=val; });
	 */
	var newDrug = g_currDrug;
	g_prescDrugList.push(newDrug); // 加入列表中

	// 动态绑定input事件
	bindEventForDosage("#drug-dosage-" + g_currDrug.id);
	bindEvent("keydown", "#drug-dosage-" + g_currDrug.id,handler_keydown_dosage);

	bindEventForDoseunit("#drug-doseunit-" + g_currDrug.id);
	bindEvent("keydown", "#drug-doseunit-" + g_currDrug.id,	handler_keydown_doseunit);
	//bindEvent("blur", "#drug-doseunit-" + g_currDrug.id, handler_blur_doseunit);

	bindEventForDays("#drug-days-" + g_currDrug.id);
	bindEvent("keydown", "#drug-days-" + g_currDrug.id, handler_keydown_days);

	clearInputValue(); // 清除输入框
	setFocus("#drug-dosage-" + g_currDrug.id); //下一个输入框获取焦点

	g_currDrug = null; // 加入后置当前药品为空

	Common.addStripedStyle();
}

/**
 * 清除输入框
 * 
 * @returns
 */
function clearInputValue() {
	clearInputBox_abc();
	clearInputBox_drugmode();
	clearInputBox_drugtimes();

	$("#warename").val("");
	$("#quantity").val("");
}

/**
 * 清除药品助词码输入框
 * 
 * @returns
 */
function clearInputBox_abc() {
	$("#abc").attr("disabled", true); // 屏蔽在对输入赋值时触发input event;
	$('#abc')
			.val("HelloWorld")
			.replaceWith(
					'<input id="abc" type="text" class="form-control"  value="" />');// 替换input
	bindIEEvent("input", "#abc", handler_input_abc);
	$("abc").attr("disabled", false);
	bindEvent("keydown", "#abc", handler_keydown_abc);
	//bindEvent("blur", "#abc", handler_blur_abc);
	
}

/**
 * 清除给药方式输入框
 * 
 * @returns
 */
function clearInputBox_drugmode() {
	$("#drugmode").attr("disabled", true); // 屏蔽在对输入赋值时触发input event;
	$('#drugmode')
			.val("HelloWorld")
			.replaceWith(
					'<input id="drugmode" type="text" class="form-control"  value="" />');// 替换input
	bindIEEvent("input", "#drugmode", handler_input_mode);
	$("drugmode").attr("disabled", false);
	bindEvent("keydown", "#drugmode", handler_keydown_mode);
	//bindEvent("blur", "#drugmode", handler_blur_mode);
	
}

/**
 * 清除给药次数输入框
 * 
 * @returns
 */
function clearInputBox_drugtimes() {
	$("#drugtimes").attr("disabled", true); // 屏蔽在对输入赋值时触发input event;
	$('#drugtimes')
			.val("HelloWorld")
			.replaceWith(
					'<input id="drugtimes" type="text" class="form-control"  value="" />');// 替换input
	bindIEEvent("input", "#drugtimes", handler_input_times);
	$("drugtimes").attr("disabled", false);
	bindEvent("keydown", "#drugtimes", handler_keydown_times);
	//bindEvent("blur", "#drugtimes", handler_blur_times);
}

/**
 * 设置焦点
 * 
 * @param id
 *            DOM对象ID
 * @returns
 */
function setFocus(id) {
	$(id)[0].focus();
}

/**
 * 删除当前药品,用于处理在药品上双击的事件.
 * 
 * @param that
 *            在处方药品列表所选药品
 * @returns
 */
function delSelectedDrug(that) {
	showConfirmWindow(that);
	/*
	 * deleteDrug(that); deleteDrugRow(that);
	 */
}

// 自g_prescDrugList列表中删除
function deleteDrug(that) {
	var drugId = $(that).attr("bind-id");
	// 根据药品ID在列表中查询.
	var index = searchDrugById(drugId);
	if (index >= 0) {
		removeFromDrugList(index);
	}
}

// 删除table中的相应row.
function deleteDrugRow(that) {
	$(that).remove();
}

var M1 = new Object();
/**
 * 双击删除行 确认对话框
 * @param that
 * @returns
 */
function showConfirmWindow(that) {
	// 判断是否已存在，如果已存在则直接显示
	if (M1.dialog3) {
		return M1.dialog3.show();
	}
	M1.dialog3 = jqueryAlert({
		'title' : '提示',
		'content' : '    确认删除当前选定的药品?    ',
		'modal' : true,
		'buttons' : {
			'确定' : function() {
				deleteDrug(that);
				deleteDrugRow(that);
				M1.dialog3.close();
				M1.dialog3 = null;
			},
			'取消' : function() {
				M1.dialog3.close();
				M1.dialog3 = null;
			}
		}
	});
}

function showConfirmWindow_cleartable(that) {
	// 判断是否已存在，如果已存在则直接显示
	if (M1.dialog3) {
		return M1.dialog3.show();
	}
	M1.dialog3 = jqueryAlert({
		'title' : '提示',
		'content' : '    确认清除处方药品列表?    ',
		'modal' : true,
		'buttons' : {
			'确定' : function() {
				removeAllDrugList();				
				clearDrugTable(that);				
				M1.dialog3.close();
				M1.dialog3 = null;
			},
			'取消' : function() {
				M1.dialog3.close();
				M1.dialog3 = null;
			}
		}
	});
}

/**
 * 清除药品 table中的 row
 * @returns
 */
function clearDrugTable(){
	$("#drug-items").empty();
}


// TODO, 后续采用面象对象的方式开发JS
/*******************************************
 * 药品列表处理方法
 *******************************************/

/**
 * 删除指定索引处的对象
 * 
 * @param index
 *            索引号
 * @returns void
 */
function removeFromDrugList(index) {
	if (index < 0 || index >= g_prescDrugList.length) {
		return false;
	}
	g_prescDrugList.splice(index, 1);
}

/**
 * 清除药品列表 List
 * @returns
 */
function removeAllDrugList(){
	g_prescDrugList=ary = []; // 赋值为一个空数组以达到清空原数组
}

/**
 * 获取指定索引的对象
 * 
 * @param index
 *            索引号
 * @returns 如果索引处的对象存在,则返回; 否则返回null;
 */
function getDrugFromDrugList(index) {
	if (index < 0 || index >= g_prescDrugList.length) {
		return null;
	} else
		return g_prescDrugList[index];
}

/**
 * 将对象加入到列表中
 * 
 * @param drug
 *            Drug对象
 * @returns void
 */
function addDrugToDrugList(drug) {
	g_prescDrugList.push(drug);
}

/**
 * 根据药品ID在处方药品列表中查找
 * 
 * @param drugId
 *            药品ID
 * @returns 如果查询到则返回索引(自0开始);否则返回-1
 */

function searchDrugById(drugId) {
	for (var i = 0; i < g_prescDrugList.length; i++) {
		if (g_prescDrugList[i].id == drugId) {
			return i;
		}
	}
	return -1;
}

function getDrugList(){
	return g_rescDrugList;
}

/*******************************************************************************
 * 定义数组的索引与删除
 ******************************************************************************/
Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val)
			return i;
	}
	return -1;
};

Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};


/**
 * 生成二维码 此函数需要参数化.
 * @param value  值  
 * @param btype  条码类型
 * @param container 条码容器
 * @returns void
 */
function generateBarcode(value,btype,container) {
	/*var value = "1234567890";
	var btype = "ean8";*/
	var renderer = "css";

	var settings = {
		output : renderer,
		bgColor : "#FFFFFF",
		color : "#000000",
		barWidth : "1",
		barHeight : "30",
		moduleSize : "0",
		posX : "0",
		posY : "0",
		addQuietZone : "0"
	};

	/*$(".barcode-crno").html("").show();
	$(".barcode-crno").barcode(value, btype, settings);
	
	$(".barcode-pn").html("").show();
	$(".barcode-pn").barcode(value, btype, settings);*/
	
	$(container).html("").show();
	$(container).barcode(value, btype, settings);

}

/**
 * 动态绑定input事件(剂量单位).
 * 
 * @returns
 */
function bindEventForDoseunit(selector) {
	bindIEEvent("input", selector, handler_input_doseunit);
}

/**
 * 动态绑定单次用量的input事件
 * 
 * @returns
 */
function bindEventForDosage(selector) {
	bindIEEvent("input", selector, handler_input_dosage);
}

/**
 * 动态绑定用药天数的input事件
 * 
 * @returns
 */
function bindEventForDays(selector) {
	bindIEEvent("input", selector, handler_input_days);
}

/*******************************************************************************
 * IE浏览器兼容性
 ******************************************************************************/

function isIE1(selector) {
	// for ie
	if ($.browser.msie) {
		/* if (document.all) { */
		$(selector).each(
				function() {
					var that = this;

					if (this.attachEvent) {
						this.attachEvent('onpropertychange', function(e) {
							if (e.propertyName != 'value') {
								return;
							}
							if (e.propertyName == 'value'
									&& !$(that).attr("disabled")) {
								$(that).trigger('input');
							}
						});
					}
				})
	}
}

/*******************************************************************************
 * 采用onpropertychange事件来模拟input事件 用于IE兼容性的函数
 * 
 * @returns
 ******************************************************************************/
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

/*******************************************************************************
 * 保存处方
 * 
 * @returns
 ******************************************************************************/
function savePrescription() {
	url = BASE_CONTEXT_PATH + "/prescription/save";

	var parmObj=new Object();
	parmObj.patientId=$("#patient").attr("bind-id");
	parmObj.doctorId=$("#doctor").attr("bind-id");
	parmObj.departmentId=$("#department").attr("bind-id");
	parmObj.prescDrugs=g_prescDrugList;
	
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
					
					var prescNo = res.result_msg; // 处方编号
					// TODO 后续业务处理
					alert("保存成功,生成处方:"+prescNo, 2000);
					$("#presc-no").val(prescNo);

				} else {
					util.message(obj.result_err_msg);
				}
				$("#btn-save-prescription").attr("disabled",false);
				
			}
		},
		error : function(result) { // 请求失败之后的操作
			alert("保存失败,请联系系统管理员!", 5000);
			$("#btn-save-prescription").attr("disabled",false);
		}
	});
}

var M = new Object();
function alert(message, closeTime) {
	// 判断是否已存在，如果已存在则直接显示
	if (M.dialog1) {
		return M.dialog1.show();
	}
	M.dialog1 = jqueryAlert({
		'content' : message,
		'closeTime' : closeTime,
	});
	M.dialog1=null;

}

/**
 * 取得当前所编辑的"剂量单位"文本框id.
 * @param that
 * @returns void
 */
function getInputDoseUnitId(that){
	g_edit_doseunit_id=$(that).attr("id");	
}



/*******************************************************************************
 * 事件处理函数
 ******************************************************************************/

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
		g_prescDrugList[index].doseunit = $(this).val();
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
		g_prescDrugList[index].dosage = $(this).val();
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
		g_prescDrugList[index].days = $(this).val();
	}
}

/**--------------------------
 * keydown event
 --------------------------*/
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
	}	
	
	return err;
}

/**
 * 检查是否为正整数
 * @param a
 * @returns
 */
function  isUnsignedInteger(a)
 {
     var reg=/^[1-9]\d*$/;
     return reg.test(a);
 }

/**
 * 检查是否为正数
 * @param a
 * @returns
 */
function isPositiveNumber(a){
	var reg=/^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
	return reg.test(a);
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


/**********************************************************
 * 事件绑定
 **********************************************************/
/**
 * 绑定IE propertychange event，用于模拟input事件。
 * 
 * @param eventName
 * @param selector
 * @param handler
 * @returns
 */
function bindIEEvent(eventName, selector, handler) {
	isIE1(selector);
	$(selector).on(eventName, handler);
}

/**
 * 绑定其它非input事件
 * 
 * @param eventName
 * @param selector
 * @param handler
 * @returns
 */
function bindEvent(eventName, selector, handler) {
	$(selector).on(eventName, handler);
}

/*******************************************************************************
 * 全局变量
 ******************************************************************************/

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
}

function getModeWindowStatus() {
	return g_dict_mode_window_status;
}
function setModeWindowStatus(status) {
	g_dict_mode_window_status = status;
}

function getTimesWindowStatus() {
	return g_dict_times_window_status;
}
function setTimesWindowStatus(status) {
	g_dict_times_window_status = status;
}

function getDoseUnitWindowStatus() {
	return g_dict_doseunit_window_status;
}

function setDoseUnitWindowStatus(status) {
	g_dict_doseunit_window_status = status;
}


var g_edit_doseunit_id; // 当前正在编辑的"剂量单位" id
var g_prescDrugList = new Array(); // 当前处方药品列表.
var g_currDrug = null; // 医生选择的当前药品
/*******************************************************************************
 * 页面加载时自动执行此函数
 ******************************************************************************/
$(function() {

	bindIEEvent("input", "#abc", handler_input_abc);
	bindIEEvent("input", "#drugmode", handler_input_mode);
	bindIEEvent("input", "#drugtimes", handler_input_times);

	bindEvent("keydown", "#abc", handler_keydown_abc);
	bindEvent("keydown", "#drugmode", handler_keydown_mode);
	bindEvent("keydown", "#drugtimes", handler_keydown_times);

	bindEvent("keydown", "#quantity", handler_keydown_quantity);
	
	bindEvent("click", "#btn-clear-presc-table", handler_click_btn_cleartable);
	
	//bindEvent("blur", "#abc", handler_blur_abc);
	//bindEvent("blur", "#drugmode", handler_blur_mode);
	//bindEvent("blur", "#drugtimes", handler_blur_times);

	//
	/***************************************************************************
	 * 绑定事件-保存按钮
	 **************************************************************************/
	$("#btn-save-prescription").on("click", function(event) {		
		$(this).attr("disabled",true);  //防止重复提交
		savePrescription(); // 保存处方
		  
	});

	/***************************************************************************
	 * 绑定事件-预览按钮
	 **************************************************************************/
	$('#btn-print-preview').printPreview(); // 处方预览与btn-print-preview click绑定
	$("#btn-preview").on("click", function(event) {
		loadPrintTemplate(); // 加载打印模板
		//$("#btn-print-preview").trigger("click");
	});

	/***************************************************************************
	 * 绑定事件-预览按钮
	 **************************************************************************/
	$("#btn-print").on("click", function(event) {
		// $("#printarea").print(); //功能同下.
		
		var prescNo=$("#presc-no").val();
		if(prescNo!="")
			loadPrintTemplate_print();
		else{
			alert("此处方尚未保存,请先保存后再打印处方.",2000);
		}
	});

	// Add keybinding (not recommended for production use)
	/*
	 * $(document).bind('keydown', function(e) { var code = (e.keyCode ?
	 * e.keyCode : e.which); if (code == 80 && !$('#print-modal').length) {
	 * $.printPreview.loadPrintPreview(); return false; } });
	 */

});