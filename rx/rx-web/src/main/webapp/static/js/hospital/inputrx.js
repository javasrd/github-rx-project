/********************************
 * 将选择的药品加入列表中
 ********************************/
function addDrugIntoTable() {

	//getInputDoseUnitId(this)
	
	var drugItem = '<tr ondblclick="delSelectedDrug(this)" bind-id=' + '"'+ g_currDrug.id+ '"'+ ' id=drug-row-'  + g_currDrug.id+  ' title="双击删除药品"'+ '>'
			+ '<td class="small_width"><input type="checkbox" class="check-drug" '+'id="check-drug-'+g_currDrug.id+'"'+  ' bind-id="'+ g_currDrug.id	+ '"' + '></td>'
			+ '<td class="small_width">'+ g_currDrug.wareid	+ '</td>'
			+ '<td class="input_width">'+ g_currDrug.warename + '</td>'
			+ '<td class="small_width">'+ g_currDrug.warespec + '</td>'
			
			+ '<td class="small_width "><input class="form-control  dosage" id="drug-dosage-'+ g_currDrug.id+ '"'+ ' bind-id="'	+ g_currDrug.id	+ '"'+   ' value='+$("#single-dosage").val()  +   '></td>'
			+ '<td class="small_width "><input class="form-control  dose-unit" id="drug-doseunit-'+ g_currDrug.id+ '"'+ ' bind-id="'	+ g_currDrug.id	+ '"'+ ' onfocus="getInputDoseUnitId(this)" '+ ' value='+$("#single-dose-unit").val()  + '>'+'</td>'
			+ '<td class="input_width "><input class="form-control  times" id="drug-times-'+ g_currDrug.id+ '"'+ ' bind-id="'	+ g_currDrug.id	+ '"'+' value='+$("#drugtimes").val()+     '></td>'
			+ '<td class="input_width "><input class="form-control  mode" id="drug-mode-'+ g_currDrug.id+ '"'+ ' bind-id="'	+ g_currDrug.id	+ '"'+' value='+$("#drugmode").val()+'></td>'			
			+ '<td class="small_width "><input class="form-control  days" id="drug-days-'+ g_currDrug.id + '"' + ' bind-id="' + g_currDrug.id + '"'	+' value='+$("#treatment-days").val()+'></td>'
			+ '<td class="small_width "><input class="form-control  quantity" id="drug-quantity-'+ g_currDrug.id + '"' + ' bind-id="' + g_currDrug.id + '"'	+ 'value='+$("#quantity").val()+'></td>' 
			
			+ '<td class="small_width">' + g_currDrug.wareunit+ '</td>' 
			+ '<td class="small_width">' + g_currDrug.saleprice	+ '</td>'
			+ '<td class="small_width">' + toDecimal2($("#quantity").val()*g_currDrug.saleprice) + '</td>' 
			+ '</tr>';

	$("#drug-items").append(drugItem); // 加入显示列表中.	
	
	// 收集用药书指导及数量 加入到全局列表中	
	g_currDrug.dosage = $("#single-dosage").val(); // 单次剂量
	g_currDrug.doseunit = $("#single-dose-unit").val(); // 剂量单位
	g_currDrug.drugtimes = $("#drugtimes").val(); 	// 频次
	g_currDrug.drugmode = $("#drugmode").val(); 	// 给药方式(用法)
	g_currDrug.days = $("#treatment-days").val(); //疗程
	g_currDrug.quantity = $("#quantity").val(); 	//数量	
	g_currDrug.patientid = $("#patient").attr("bind-id"); // 患者ID
	g_currDrug.doctorid = $("#doctor").attr("bind-id"); // 医生ID

	// 将药品加入到药品列表
	/*
	 * var newDrug=new Object(); jQuery.each(g_currDrug, function(i, val) {
	 * newDrug.i=val; });
	 */
	var newDrug = g_currDrug;
	addDrugToDrugList(newDrug);  // 加入列表中
	


	// 表格中可编辑字段   动态绑定input,keydown事件
	//(1)单次用量
	bindIEEvent("input", "#drug-dosage-" + g_currDrug.id, handler_input_dosage_table);    
	bindEvent("keydown", "#drug-dosage-" + g_currDrug.id,handler_keydown_dosage);
	
	//(2)剂量单位
	bindIEEvent("input", "#drug-doseunit-" + g_currDrug.id, handler_input_doseunit_table);  
	bindEvent("keydown", "#drug-doseunit-" + g_currDrug.id,	handler_keydown_doseunit_table);
	
	//(3)频次
	bindIEEvent("input", "#drug-times-" + g_currDrug.id, handler_input_times_table);
	bindEvent("keydown", "#drug-times-" + g_currDrug.id, handler_keydown_times_table);
	
	//(4)用法
	bindIEEvent("input", "#drug-mode-" + g_currDrug.id, handler_input_mode_table);
	bindEvent("keydown", "#drug-mode-" + g_currDrug.id,	handler_keydown_mode_table);
	
	//(5)疗程(天数)
	bindIEEvent("input", "#drug-days-" + g_currDrug.id, handler_input_days_table);     
	bindEvent("keydown", "#drug-days-" + g_currDrug.id, handler_keydown_days_table);
	
	//(6)数量
	bindIEEvent("input", "#drug-quantity-" + g_currDrug.id, handler_input_quantity_table);
	bindEvent("keydown", "#drug-quantity-" + g_currDrug.id, handler_keydown_quantity_table);
	
	
	

	clearInputValue(); // 清除输入框
	setFocus("#abc"); //药品助词码输入框获取焦点

	g_currDrug = null; // 加入后置当前药品为空

	displayNumberAndSum();  
	
	$("#drugForm .drug-list tr").attr("class","");
	Common.addStripedStyle();
}

/**
 * 显示处方中药品数量及总金额
 */
function displayNumberAndSum(){
	var drugList=getDrugList();
	$("#presc-drug-number").text(drugList.length);
	var sum=calcPrescDrugAmount();	
	$("#presc-drug-sum").text(toDecimal2(sum));
}

/**
 * 清除输入框
 * 
 * @returns
 */
function clearInputValue() {
	clearInputBox_abc();
	clearInputBox_singledoseunit();  	
	clearInputBox_drugmode();
	clearInputBox_drugtimes();
	clearInputBox_days();  

	$("#warename").val("");
	$("#single-dosage").val("");
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
					'<input id="abc" type="text" class="form-control" style="width:80px;"  value="" />');// 替换input
	bindIEEvent("input", "#abc", handler_input_abc);
	$("#abc").attr("disabled", false);
	bindEvent("keydown", "#abc", handler_keydown_abc);
}


function clearInputBox_singledoseunit() {
	var id="#single-dose-unit";
	$(id).attr("disabled", true); // 屏蔽在对输入赋值时触发input event;
	$(id)
			.val("HelloWorld")
			.replaceWith(
					'<input id="single-dose-unit" type="text" class="form-control" style="width:60px;"  value="" />');// 替换input
	bindIEEvent("input", id, handler_input_doseunit);
	$(id).attr("disabled", false);
	bindEvent("keydown", id, handler_keydown_doseunit);
}

function clearInputBox_days(){
	var id="#treatment-days";
	$(id).attr("disabled", true); // 屏蔽在对输入赋值时触发input event;
	$(id)
			.val("HelloWorld")
			.replaceWith(
					'<input id="treatment-days" type="text" class="form-control" style="width:60px;"  value="" />');// 替换input
	bindIEEvent("input", id, handler_input_days);
	$(id).attr("disabled", false);
	bindEvent("keydown", id, handler_keydown_days);
}


/**
 * 清除给药方式输入框
 * 
 * @returns
 */
function clearInputBox_drugmode() {
	var id="#drugmode";
	$(id).attr("disabled", true); // 屏蔽在对输入赋值时触发input event;
	$(id)
			.val("HelloWorld")
			.replaceWith(
					'<input id="drugmode" type="text" class="form-control" style="width:80px;"  value="" />');// 替换input
	bindIEEvent("input", id, handler_input_mode);
	$(id).attr("disabled", false);
	bindEvent("keydown", id, handler_keydown_mode);
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
					'<input id="drugtimes" type="text" class="form-control" style="width:100px;"  value="" />');// 替换input
	bindIEEvent("input", "#drugtimes", handler_input_times);
	$("drugtimes").attr("disabled", false);
	bindEvent("keydown", "#drugtimes", handler_keydown_times);
}

/**
 * 删除当前药品,用于处理在药品上双击的事件.
 * 
 * @param that
 *            在处方药品列表所选药品
 * @returns
 */
function delSelectedDrug(that) {
	var prescNo=$("#presc-no").val();
	if(prescNo!=""){
		alert("处方已经确定,不可删除!",2000);
		return false;
	}
	showConfirmWindow(that);
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


var M = new Object();
/**
 * @param message
 * @param closeTime
 * @param type  right error warning
 * @returns
 */
function alert(message, closeTime,type) {
	// 判断是否已存在，如果已存在则直接显示
	if (M.dialog1) {
		M.dialog1.destroy();
	}
	//BASE_CONTEXT_PATH+'/static/images/warning.png"
	
	var icon=BASE_CONTEXT_PATH+'/static/images/warning.png';
	if(type=='right')
		icon=BASE_CONTEXT_PATH+'/static/images/right.png';
	else if(type=='error')
		icon=BASE_CONTEXT_PATH+'/static/images/error.png';
	else
		icon=BASE_CONTEXT_PATH+'/static/images/warning.png';	
		
		
	M.dialog1 = jqueryAlert({
		'content' : message,
		'icon':icon,
		'closeTime' : closeTime
	});	
}

//var M1 = new Object();
/**
 * 双击删除行 确认对话框
 * @param that
 * @returns
 */
function showConfirmWindow(that) {
	// 判断是否已存在，如果已存在则直接显示
	if (M.dialog1) {
		M.dialog1.destroy();
	}
	M.dialog1 = jqueryAlert({
		'title' : '提示',
		'content' : '    确认删除当前选定的药品?    ',
		'modal' : true,
		'buttons' : {
			'确定' : function() {
				deleteDrug(that);
				deleteDrugRow(that);
				displayNumberAndSum();	
				$("#drugForm .drug-list tr").attr("class","");
				Common.addStripedStyle();
				M.dialog1.close();
				M.dialog1.destroy();
				M.dialog1 = null;
				displayNumberAndSum();
			},
			'取消' : function() {
				M.dialog1.close();
				M.dialog1.destroy();
				M.dialog1 = null;
			}
		}
	});
}


function showConfirmWindow_delete_selected_row() {
	// 判断是否已存在，如果已存在则直接显示
	if (M.dialog1) {
		M.dialog1.destroy();
	}
	M.dialog1 = jqueryAlert({
		'title' : '提示',
		'content' : '<img src="'+BASE_CONTEXT_PATH+'/static/images/warning.png">'+ '    确认删除所选定的药品?    ',
		'modal' : true,
		'buttons' : {
			'确定' : function() {
				deleteSelectedDrug();  			
				displayNumberAndSum();	
				$("#drugForm .drug-list tr").attr("class","");
				Common.addStripedStyle();
				M.dialog1.close();
				M.dialog1.destroy();
				M.dialog1 = null;
				displayNumberAndSum();
			},
			'取消' : function() {
				M.dialog1.close();
				M.dialog1.destroy();
				M.dialog1 = null;
			}
		}
	});
}

function deleteSelectedDrug(){
	$(".check-drug").each(function(index){
		var drugId=$(this).attr("bind-id");
		//alert("drugId:"+drugId);
		var checkStatus=$(this).is(':checked');
		if(checkStatus==true){
			//alert("checked!");
			$("#drug-row-"+drugId).remove();  //自显示列表中删除
			// 根据药品ID在列表中查询.
			var index = searchDrugById(drugId);  //自药品列表数组中删除
			if (index >= 0) {
				removeFromDrugList(index);
			}			
		}
	});
	
	$("#check-select-all").attr("checked",false);
	
}



function showConfirmWindow_cleartable(that) {
	// 判断是否已存在，如果已存在则直接显示
	if (M.dialog1) {
		M.dialog1.destroy();
	}
	M.dialog1 = jqueryAlert({
		'title' : '提示',
		'content' : '    确认清除处方药品列表?    ',
		'modal' : true,
		'buttons' : {
			'确定' : function() {
				removeAllDrugList();  //清除药品列表 array				
				clearDrugTable(that); //清除药品table
				clearPrescNo();		  //清除处方编号
				clearNumberAndSum();  //清除处方中药品个数及总金额
				M.dialog1.close();
				M.dialog1.destroy();
				M.dialog1 = null;
			},
			'取消' : function() {
				M.dialog1.close();
				M.dialog1.destroy();
				M.dialog1 = null;
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

/**
 * 清除处方编号
 * @returns
 */
function clearPrescNo(){
	$("#presc-no").val("");
	$("#presc-no-display").text("新处方");
}

/**
 * 清除处方个数及金额
 * @returns
 */
function clearNumberAndSum(){
	$("#presc-drug-number").text("0");
	$("#presc-drug-sum").text("0");
}



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

/*********************************
 * 保存处方
 * 
 * @returns
 *********************************/
function savePrescription() {
	url = BASE_CONTEXT_PATH + "/prescription/save";

	var parmObj=new Object();
	parmObj.patientId=$("#patient").attr("bind-id");
	parmObj.doctorId=$("#doctor").attr("bind-id");
	parmObj.departmentId=$("#department").attr("bind-id");
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
					
					var prescNo = res.result_msg; // 处方编号
					// TODO 后续业务处理
					alert("保存成功,生成处方:"+prescNo, 2000);
					
					//置处方号
					$("#presc-no").val(prescNo);
					$("#presc-no-display").text(prescNo);
					
					//列表中编辑无效
					disableEditDrug();

				} else {
					alert(obj.result_err_msg,5000);
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

/**
 * 保存后,不可以再进行编辑 剂量,剂量单位,天数
 * @returns
 */
function disableEditDrug(){
	$(".dosage").attr("disabled",true);
	$(".dose-unit").attr("disabled",true);
	$(".days").attr("disabled",true);
	
	$(".times").attr("disabled",true);
	$(".mode").attr("disabled",true);
	$(".quantity").attr("disabled",true);
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
 * 全局变量
 ******************************************************************************/
var g_edit_doseunit_id; // 当前正在编辑的"剂量单位" id
var g_currDrug = null; // 医生选择的当前药品
/*******************************************************************************
 * 页面加载时自动执行此函数
 ******************************************************************************/
$(function() {

	bindIEEvent("input", "#abc", handler_input_abc);
	bindEvent("keydown", "#abc", handler_keydown_abc);
	
	//bindIEEvent("input", "#single-dosage", handler_input_dosage);
	bindEvent("keydown", "#single-dosage", handler_keydown_dosage);
	
	bindIEEvent("input", "#single-dose-unit", handler_input_doseunit);
	bindEvent("keydown", "#single-dose-unit", handler_keydown_doseunit);
	
	bindIEEvent("input", "#drugtimes", handler_input_times);
	bindEvent("keydown", "#drugtimes", handler_keydown_times);
	
	bindIEEvent("input", "#drugmode", handler_input_mode);
	bindEvent("keydown", "#drugmode", handler_keydown_mode);
	
	bindIEEvent("input", "#treatment-days", handler_input_days);
	bindEvent("keydown", "#treatment-days", handler_keydown_days);

	bindEvent("keydown", "#quantity", handler_keydown_quantity);
	
	bindEvent("keydown", ".hospital", handler_keydown_hospital);
	
	//界面面板中数字字典按钮
	bindEvent("click", "#btn-clear-presc-table", handler_click_btn_cleartable);
	bindEvent("click","#btn-abc",handler_click_btn_abc);
	bindEvent("click","#btn-single-dose-unit",handler_click_btn_singledoseunit);
	bindEvent("click","#btn-drugtimes",handler_click_btn_drugtimes);
	bindEvent("click","#btn-drugmode",handler_click_btn_drugmode);
	bindEvent("click","#btn-treatment-days",handler_click_btn_treatmentdays);
	
	//模板功能面板
	bindEvent("click","#btn-save-template",handler_click_btn_save_template);
	bindEvent("click","#btn-edit-template",handler_click_btn_edit_template);
	bindEvent("click","#btn-use-template",handler_click_btn_use_template);
	
	
	//药品增加按钮CLICK事件处理
	bindEvent("click","#btn-add-drug",handler_click_btn_add_drug);
	
	bindEvent("click", "#check-select-all", handler_click_select_all);  //选择所有
	bindEvent("click", "#btn-delete-row", handler_click_delete_row);  //选择所有
	
	
	//bindEvent("blur", "#abc", handler_blur_abc);
	//bindEvent("blur", "#drugmode", handler_blur_mode);
	//bindEvent("blur", "#drugtimes", handler_blur_times);

	//
	/******************************
	 * 绑定事件-保存按钮
	 ******************************/
	$("#btn-save-prescription").on("click", function(event) {
		//(1)所输入的药品数量判定.
		var len=getDrugListLength();  //获得药品列表的长度.
		if(len<=0){
			alert("尚未录入药品!",1000);
			return false;
		}
		if(len>=5){
			alert("每个处方中药品数不可超过5种!");
			return false;
		}
		var sum=calcPrescDrugAmount();
		if(sum>300){
			alert("每个处方的金额不可以超过300元!");
			return;
		}
		var prescNo=$("#presc-no").val();
		if(prescNo!=""){
			alert("此处方已经生成,不可重复保存!");
			return;
		}
	
		//(2)TODO 其它的根据业务逻辑进行的有效性判定
		
		
		$(this).attr("disabled",true);  //防止重复提交
		savePrescription(); // 保存处方
		  
	});

	/******************************************
	 * 绑定事件-预览按钮
	 *****************************************/
	$('#btn-print-preview').printPreview(); // 处方预览与btn-print-preview click绑定
	$("#btn-preview").on("click", function(event) {
		loadPrintTemplate(); // 加载打印模板
		//$("#btn-print-preview").trigger("click");
	});

	/***********************************************
	 * 绑定事件-预览按钮
	 ***********************************************/
	$("#btn-print").on("click", function(event) {
		// $("#printarea").print(); //功能同下.
		
		var prescNo=$("#presc-no").val();
		if(prescNo!="")
			loadPrintTemplate_print();
		else{
			alert("此处方尚未保存,请先保存后再打印处方.",2000);
		}
	});
	
	$("#abc").focus();  //助词码为默认输入框

	// Add keybinding (not recommended for production use)
	/*
	 * $(document).bind('keydown', function(e) { var code = (e.keyCode ?
	 * e.keyCode : e.which); if (code == 80 && !$('#print-modal').length) {
	 * $.printPreview.loadPrintPreview(); return false; } });
	 */

});