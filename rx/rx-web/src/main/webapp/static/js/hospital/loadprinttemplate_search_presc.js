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
	parmObj.patientId=$("#patient-id").val();
	parmObj.doctorId=$("#doctor-id").val();
	parmObj.departmentId=$("#department").attr("bind-id");
	parmObj.type=1;  //1:正方   2:副方
	parmObj.prescNo=$("#presc-no").val();  //处方号
	parmObj.prescDrugs=g_drug_list;
	
	var parms = {jsonPresc:JSON.stringify(parmObj)}; //参数jsonPresc的格式为json	
	var callbackFunc = printPreview;
	var containerId = "#print-preview-area";
	loadPage(containerId, url, parms, callbackFunc);
	//$("#btn-print-preview").trigger("click");
}

/**
 * 加载处方副本 (for preview)
 * @returns
 */
function loadPrintTemplate_copy() {
	
	var url = "/presc/printtemplate";
	
	var parmObj=new Object();
	parmObj.patientId=$("#patient-id").val();
	parmObj.doctorId=$("#doctor-id").val();
	parmObj.departmentId=$("#department").attr("bind-id");
	parmObj.type=2;  //1:正方   2:副方
	parmObj.prescNo=$("#presc-no").val();  //处方号
	parmObj.prescDrugs=g_drug_list;
	
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
	parmObj.patientId=$("#patient-id").val();
	parmObj.doctorId=$("#doctor-id").val();
	parmObj.departmentId=$("#department").attr("bind-id");
	parmObj.type=1;  //1:正方   2:副方
	parmObj.prescNo=$("#presc-no").val();  //处方号
	parmObj.prescDrugs=g_drug_list;
	console.log("print:"+JSON.stringify(parmObj));
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
	parmObj.patientId=$("#patient-id").val();
	parmObj.doctorId=$("#doctor-id").val();
	parmObj.departmentId=$("#department").attr("bind-id");
	parmObj.type=2;  //1:正方   2:副方
	parmObj.prescNo=$("#presc-no").val();  //处方号
	parmObj.prescDrugs=g_drug_list;
	console.log("print:"+JSON.stringify(parmObj));
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