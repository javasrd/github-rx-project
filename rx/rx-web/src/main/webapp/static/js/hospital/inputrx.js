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


/*******************************************************************************
 * 加载剂量单位
 ******************************************************************************/
function loadDrugDoseUnit(abc) {
	var url = "/drug/doseunit";
	var parms = {
		abc : abc
	};
	var callbackFunc =null;
	var containerId = ".dropdown-unit";
	loadPage(containerId, url, parms, callbackFunc);
}

/*******************************************************************************
 * 加载打印模板,加载成功后,触发打印预览
 ******************************************************************************/
function loadPrintTemplate() {
	/*$("#btn-print-preview").trigger("click");
	return;*/
	//loadCss();
	
	var url = "/presc/printtemplate";
	var parms = {};
	var callbackFunc =printPreview;
	var containerId = "#printarea";
	loadPage(containerId, url, parms, callbackFunc);
}
//加载打印模板成功后-回调函数
function printPreview(){
	//(1)生成条形码.
	generateBarcode();	
	//(2)触发打印预览.
	$("#btn-print-preview").trigger("click");	
	//(3)加载原来的css
	//loadCss();
	
}

function loadCss(){
	var url = "/presc/loadcss";
	var parms = null;
	var callbackFunc =null;
	var containerId = "#loadcss";	
	loadPage(containerId, url, parms, callbackFunc);
}



/*******************************************************************************
 * 将选择的药品加入列表中
 ******************************************************************************/
function addDrugIntoTable(){
	//需要加入的内容如下所示:
	/*
	 
	  			<th>商品码</th>
                <th>商品名</th>
                <th>规格</th>
                <th>用法</th>
                <th>频次</th>
                <th class="input_width">单次用量</th>
                <th class="input_width">剂量单位</th>
                <th class="input_width">用药天数</th>
                <th class="input_width">单位</th>
                <th class="small_width">单价</th>
                <th class="small_width">数量</th>
                <th class="small_width">金额</th>
	  
	 			<tr th:remove="all">
                  <td>此处存放药品名称</td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td class="input_width input_td"><input class="form-control input-sm"></td>
                  <td class="input_width input_td"><input class="form-control input-sm" onfocus="Common.showDropdownUnit(this)" onblur="Common.hideDropdownUnit()"></td>
                  <td class="input_width input_td"><input class="form-control input-sm"></td>
                  <td class="input_width"></td>
                  <td class="small_width"></td>
                  <td class="small_width"></td>
                  <td class="small_width"></td>
                </tr> 
                
                g_currDrug.wareid=$("#wareid-"+drugId).text();
				g_currDrug.warename=$("#warename-"+drugId).text();
				g_currDrug.warespec=$("#warespec-"+drugId).text();
				g_currDrug.saleprice=$("#saleprice-"+drugId).text();
				g_currDrug.wareunit=$("#wareunit-"+drugId).text();
	 */
	
	if(g_currDrug==null) return false;
	
	var drugItem=
		'<tr ondblclick="delSelectedDrug(this)" bind-id='+ '"'+g_currDrug.id+'"'+ '>'+
			'<td>'+g_currDrug.wareid+'</td>'+
	        '<td>'+g_currDrug.warename+'</td>'+
	        '<td>'+g_currDrug.warespec+'</td>'+
	        '<td>' +$("#drugmode").val()+'</td>'+
	        '<td>' +$("#drugtimes").val()+'</td>'+
	        '<td class="input_width input_td"><input class="form-control input-sm dosage" id="drug-dosage-' +g_currDrug.id+'"'+ ' bind-id="'+g_currDrug.id+'"'+ '></td>'+
	        '<td class="input_width input_td"><input class="form-control input-sm dose-unit" onfocus="resetCounter1($(this))" id="drug-doseunit-'+g_currDrug.id+'"' +' bind-id="'+g_currDrug.id+'"'+ '></td>'+        
	        '<td class="input_width input_td"><input class="form-control input-sm days" id="drug-days-' +g_currDrug.id+ '"'+' bind-id="'+g_currDrug.id+'"'+'></td>'+
	        '<td class="input_width">'+g_currDrug.wareunit+'</td>'+
	        '<td class="small_width">'+g_currDrug.saleprice+'</td>'+
	        '<td class="small_width">'+$("#quantity").val()+'</td>'+
	        '<td class="small_width">'+$("#quantity").val()*g_currDrug.saleprice+ '</td>'+
        '</tr>';
	
	$("#drug-items").append(drugItem);  //加入显示列表中.
	
	//收集用药书指导及数量  加入到全局列表中
	g_currDrug.drugmode=$("#drugmode").val();    //给药方式
	g_currDrug.drugtimes=$("#drugtimes").val();  //给药次数
	g_currDrug.quantity=$("#quantity").val();    //数量
	g_currDrug.dosage=$("#drug-dosage-"+g_currDrug.id).val();  		//单次剂量
	g_currDrug.doseunit=$("#drug-doseunit-"+g_currDrug.id).val();  	//剂量单位
	g_currDrug.days=$("#drug-days-"+g_currDrug.id).val();  			//用药天数
	g_currDrug.patientid=$("#patient").attr("bind-id");			    //患者ID
	g_currDrug.doctorid=$("#doctor").attr("bind-id");			    //医生ID
	
	
	//将药品加入到药品列表
	/*var newDrug=new Object();
	jQuery.each(g_currDrug, function(i, val) {
	    newDrug.i=val;
	});	*/
	var newDrug=g_currDrug;
	g_prescDrugList.push(newDrug);  //加入列表中	
	
	/*isIE1("#drug-dosage-"+g_currDrug.id);
	isIE1("#drug-doseunit-"+g_currDrug.id);
	isIE1("#drug-days-"+g_currDrug.id);
	
	bindEventForDosage("#drug-dosage-"+g_currDrug.id);
	bindEventForDoseunit("#drug-doseunit-"+g_currDrug.id);	
	bindEventForDays("#drug-days-"+g_currDrug.id);*/	
	
	
	clearInputValue();  //清除输入框
	setFocus("#drug-dosage-"+g_currDrug.id);
	
	
	g_currDrug=null;  //加入后置当前药品为空
}

function clearInputValue(){
	clearInputBox("#abc","");
	
	/*clearInputBox("#warename","");
	clearInputBox("#drugmode","");
	clearInputBox("#drugtimes","");
	clearInputBox("#quantity","");*/
}	

/**
 * 清除最上面的输入框
 * @returns
 */
function clearInputBox(selector,value){
	$(selector).attr("disabled",true);  //避免触发propertychanged事件
	$(selector).val("");
	$(selector).attr("disabled",false);
	//isIE1(selector);
}

/**
 * 设置焦点
 * @param id  DOM对象ID
 * @returns
 */
function setFocus(id){
	$(id).focus();
}

/**
 * 删除当前药品,用于处理在药品上双击的事件.
 * @param that 在处方药品列表所选药品
 * @returns
 */
function delSelectedDrug(that){	
	showConfirmWindow(that);
	/*deleteDrug(that);
	deleteDrugRow(that);*/
}

//自g_prescDrugList列表中删除
function deleteDrug(that){
	var drugId=$(that).attr("bind-id");
	//根据药品ID在列表中查询.
	var index=searchDrugById(drugId);
	if(index>=0){
		removeFromDrugList(index);
	}
}

//删除table中的相应row.
function deleteDrugRow(that){
	$(that).remove();
}

var M1=new Object();
function showConfirmWindow(that){
	// 判断是否已存在，如果已存在则直接显示
	if(M1.dialog3){
	    return M1.dialog3.show();
	}
	M1.dialog3 = jqueryAlert({
	    'title'   : '提示',
	    'content' : '    确认删除当前选定的药品?    ',
	    'modal'   : true,
	    'buttons' :{
	        '确定' : function(){
	        	deleteDrug(that);
	        	deleteDrugRow(that);
	        	M1.dialog3.close();
	        	M1.dialog3=null;
	        },
	        '取消' : function(){
	        	M1.dialog3.close();
	        	M1.dialog3=null;
	        }
	    }
	});
	
}


//TODO, 后续采用面象对象的方式开发JS
/***********************************************
  药品列表处理方法
 **********************************************/

/**
 * 删除指定索引处的对象
 * @param index  索引号
 * @returns void
 */
function removeFromDrugList(index){
	if(index<0  || index>=g_prescDrugList.length) {return false;}
	g_prescDrugList.splice(index, 1);
}

/**
 * 获取指定索引的对象
 * @param index 索引号
 * @returns 如果索引处的对象存在,则返回;  否则返回null;
 */
function getDrugFromDrugList(index){
	if(index<0  || index>=g_prescDrugList.length) {return null;}
	else return  g_prescDrugList[index];
}

/**
 * 将对象加入到列表中
 * @param drug  Drug对象  
 * @returns void
 */
function addDrugToDrugList(drug){
	g_prescDrugList.push(drug);
}

/**
 * 根据药品ID在处方药品列表中查找
 * @param drugId  药品ID
 * @returns 如果查询到则返回索引(自0开始);否则返回-1
 */

function searchDrugById(drugId){
	for(var i=0;i<g_prescDrugList.length;i++){
		if(g_prescDrugList[i].id==drugId){
			return i;
		}
	}
	return -1;
}



/*******************************************
 * 定义数组的索引与删除
 ******************************************/
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


/****************************************
 * 生成二维码
 * TODO 此函数需要参数化.
 * @returns void
 ***************************************/
function generateBarcode(){
    var value = "1234567890";
    var btype = "ean8";
    var renderer = "css";      
	
    var settings = {
      output:renderer,
      bgColor: "#FFFFFF",
      color: "#000000",
      barWidth: "2",
      barHeight: "30",          
      moduleSize: "0",
      posX: "0",
      posY: "0",
      addQuietZone: "0"
    };
    
     $("#barcodeTarget").html("").show();
     $("#barcodeTarget").barcode(value, btype, settings);
    
  }

/**
 * 动态绑定input事件(剂量单位). 
 * @returns
 */
function bindEventForDoseunit(selector){
	$(selector).on("input", function() {
		
		/*--------------------------
		用户输入
			(1)可能是助记码
			(2)真正的doseunit
		--------------------------*/
		
		//(1)输入是doseunit情况		
		var drugId=$(this).attr("bind-id"); //取得当前编辑的药品ID		
		var index=searchDrugById(drugId);  //自g_prescDrugList查询,并置doseunit
		if(index>=0){
			g_prescDrugList[index].doseunit=$(this).val();			
		}		
		
		//(2)输入是助记码情况
		var abc = $(this).val(); // 助记码		
		if(counter==0){ //如果是首次调用时.
			Common.showDropdownUnit($(this));
			counter=1;			
		}		
		loadDrugDoseUnit(abc);
	});
}

/**
 * 动态绑定单次用量的input事件 
 * @returns
 */
function bindEventForDosage(selector){
	$(selector).on("input", function() {
		
		var drugId=$(this).attr("bind-id");  //取得当前编辑的药品ID	
		//alert("drugId:"+drugId);
		var index=searchDrugById(drugId); //自g_prescDrugList查询,并置dosage
		if(index>=0){
			//alert("dosage:"+$(this).val());
			g_prescDrugList[index].dosage=$(this).val();			
		}		
		
	});
}

/**
 * 动态绑定用药天数的input事件 
 * @returns
 */
function bindEventForDays(selector){
	$(selector).on("input", function() {
		
		var drugId=$(this).attr("bind-id"); //取得当前编辑的药品ID
		//自g_prescDrugList查询,并置days属性
		var index=searchDrugById(drugId);
		if(index>=0){
			g_prescDrugList[index].days=$(this).val();			
		}
				
	});
}




/*************************************
  	IE浏览器兼容性
 ************************************/
function isIE1(selector) {
	// for ie
	if (document.all) {
		$(selector).each(function() {
			var that = this;

			if (this.attachEvent) {				
				this.attachEvent(
						'onpropertychange', 
						function(e) {							
							if (e.propertyName != 'value')	return;
							alert("triggered!");
							$(that).trigger('input');
						});
			}
		})
	}
}

/*var TriggerInputFlag={
		
}*/


//获取触发标志
function getTriggerInputFlag(){
	return g_trigger_input_flag;
}
//设置触发标志
function setTriggerInputFlag(trigger_flag){
	g_trigger_input_flag=trigger_flag;
}


function isIE() {
	// for ie
	if (document.all) {
		$('input[type="text"]').each(function() {
			var that = this;

			if (this.attachEvent) {				
				this.attachEvent(
						'onpropertychange', 
						function(e) {							
							if (e.propertyName != 'value')	return;							
							$(that).trigger('input');
						});
			}
		})
	}
}



/**
 * 重置标志为未打开状态
 * @param that
 * @returns void
 */

function resetCounter(that){
	counter=0;
}
function setCounter(){
	counter=1;
}
function getCounter(){
	return counter;
}

/****************************************
 * 保存处方
 * @returns
 ***************************************/
function savePrescription(){
	url=BASE_CONTEXT_PATH+"/prescription/save";	
	
	var parms=g_prescDrugList;
	//alert("array length:"+parms.length);
	//采用AJAX方式发送POST请求
	$.ajax({
		type: "POST",
		url: url,
		contentType: "application/json", //指定发送到服务器时参数的格式				
		dataType: "json",  //指定自服务器接收到的数据格式
		data: JSON.stringify(parms), //传递的参数,JSON格式。		
		success: function(res) {  //请求正确之后的操作
			if (res != null) {
				//console.log(res);
				//var obj = $.parseJSON(res);
				if (res.result_code == "success") {					
					//util.message(obj.result_msg,"","info");					
					// 判断是否已存在，如果已存在则直接显示
					alert("保存成功",2000);
					
					var prescNo=res.result_msg; //处方编号
					//TODO 后续业务处理
					
				} else {
					util.message(obj.result_err_msg);
				}
			}
		},
		error: function(result) {  //请求失败之后的操作  
			
		}
	});
}

var M=new Object();
function alert(message,closeTime){	
	// 判断是否已存在，如果已存在则直接显示
	if(M.dialog1){
		return M.dialog1.show();
	}
	M.dialog1 = jqueryAlert({
		'content' : message,
		'closeTime' : closeTime,
	});
	
}


/**
 * 重置标志为未打开状态,并取得当前所编辑的"剂量单位"文本框id.
 * @param that
 * @returns void
 */
function resetCounter1(that){
	g_edit_doseunit_id=$(that).attr("id");	
	counter=0;
}

/***************************************************
 * 						全局变量
 **************************************************/
var counter=0; 					  //下拉框是否已经打开标志,用于防止多次打开关闭(闪烁)  0:尚未打开; 1:已经打开.
var g_edit_doseunit_id;  		  //当前正在编辑的"剂量单位" id
var g_prescDrugList=new Array();  //当前处方药品列表.
var g_currDrug=null; 			  //医生选择的当前药品
var g_trigger_input_flag=false;   //是否触发input事件标志
/*******************************************************************************
 * 页面加载时自动执行此函数
 ******************************************************************************/
$(function() {
	
	//setTriggerInputFlag(true);
	
	isIE1("#abc");
	//isIE1("#drugmode");
	//isIE1("#drugtimes");	

	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	$("#abc").on("input", function() {
		var abc = $(this).val(); // 助记码		
		if(getCounter()==0){ //如果是首次调用时.
			Common.showDropdownTable($(this))			
			setCounter();
		}
		loadDrugTable(abc);
		
	});
	
	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	$("#drugmode").on("input", function() {
		var abc = $(this).val(); // 助记码		
		if(getCounter()==0){ //如果是首次调用时.
			Common.showDropdownTable($(this))
			setCounter();			
		}		
		loadDrugMode(abc);
	});
	
	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	$("#drugtimes").on("input", function() {
		var abc = $(this).val(); // 助记码		
		if(getCounter()==0){ //如果是首次调用时.
			Common.showDropdownTable($(this))			
			setCounter();
		}		
		loadDrugTimes(abc);
	});
	
	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	/*$(".dose-unit").on("input", function() {
		var abc = $(this).val(); // 助记码		
		if(getCounter()==0){ //如果是首次调用时.
			Common.showDropdownUnit($(this))			
			setCounter();
		}		
		loadDrugDoseUnit(abc);
	});*/
	
	
	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	$("#quantity").on("keydown", function(event) {
		if (event.keyCode == "13") {//keyCode=13是回车键			
            addDrugIntoTable();  //将选择的药品加入列表中.
            return false;
        }
	});
	
	//
	/***************************************************************************
	 * 绑定事件-保存按钮
	 **************************************************************************/
	$("#btn-save-prescription").on("click", function(event) {
		generateBarcode();   //生成条形码
		savePrescription();  //保存处方
	});
	
	
	
	/*************************************************************************
	 * 绑定事件-预览按钮
	 *************************************************************************/
	$('#btn-print-preview').printPreview();  //处方预览与btn-print-preview click绑定
	$("#btn-preview").on("click", function(event) {
		loadPrintTemplate();  //加载打印模板
	});
	

	/***************************************************************************
	 * 绑定事件-预览按钮
	 **************************************************************************/	
	$("#btn-print").on("click", function(event) {
		//$("#printarea").print();  //功能同下.
		$.print("#printarea");
	});

	
	
	// Add keybinding (not recommended for production use)
    $(document).bind('keydown', function(e) {
        var code = (e.keyCode ? e.keyCode : e.which);
        if (code == 80 && !$('#print-modal').length) {
            $.printPreview.loadPrintPreview();
            return false;
        }            
    });
	

});