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

//
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
	
	var drugItem=
		'<tr th:remove="all">'+
			'<td>'+g_currDrug.wareid+'</td>'+
	        '<td>'+g_currDrug.warename+'</td>'+
	        '<td>'+g_currDrug.warespec+'</td>'+
	        '<td>' +$("#drugmode").val()+'</td>'+
	        '<td>' +$("#drugtimes").val()+'</td>'+
	        '<td class="input_width input_td"><input class="form-control input-sm"></td>'+
	        '<td class="input_width input_td"><input class="form-control input-sm dose-unit" onfocus="resetCounter1($(this))" id=' +'drug-doseunit-'+g_currDrug.id+   '></td>'+        
	        '<td class="input_width input_td"><input class="form-control input-sm"></td>'+
	        '<td class="input_width">'+g_currDrug.wareunit+'</td>'+
	        '<td class="input_width">'+g_currDrug.saleprice+'</td>'+
	        '<td class="input_width">'+$("#quantity").val()+'</td>'+
	        '<td class="small_width">'+$("#quantity").val()*g_currDrug.saleprice+ '</td>'+
        '</tr>';
	
	$("#drug-items").append(drugItem);
	
	isIE();
	
	//绑定事input事件.
	$(".dose-unit").on("input", function() {
		var abc = $(this).val(); // 助记码		
		if(counter==0){ //如果是首次调用时.
			Common.showDropdownUnit($(this));
			counter=1;			
		}		
		loadDrugDoseUnit(abc);
	});
	
	
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

var g_edit_doseunit_id;
function resetCounter1(that){
	g_edit_doseunit_id=$(that).attr("id");	
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
		var abc = $(this).val(); // 助记码		
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
		var abc = $(this).val(); // 助记码		
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
		var abc = $(this).val(); // 助记码		
		if(counter==0){ //如果是首次调用时.
			Common.showDropdownTable($(this))
			counter=1;
		}		
		loadDrugTimes(abc);
	});
	
	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	$(".dose-unit").on("input", function() {
		var abc = $(this).val(); // 助记码		
		if(counter==0){ //如果是首次调用时.
			Common.showDropdownUnit($(this))
			counter=1;
		}		
		loadDrugDoseUnit(abc);
	});
	
	
	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	$("#quantity").on("keydown", function(event) {
		if (event.keyCode == "13") {//keyCode=13是回车键			
            addDrugIntoTable();  //将选择的药品加入列表中.
        }
	});
	

});