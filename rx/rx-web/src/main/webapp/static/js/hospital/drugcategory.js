
/***************************************************************************
	全局变量
***************************************************************************/
/**
 * 药品目录双击事件处理.
 * @returns
 */
function handler_dblclick_drugcategory(){
	//用于根据ID查询当前药品的属性值.
	var drugId=$(this).attr("drug-id");	
	process_curr_drug(drugId);	
}

/**
 * 处理当前选择的药品
 * @param drugId 药品id
 * @returns void
 */
function process_curr_drug(drugId){
	g_currDrug=new Object();
	g_currDrug.id=drugId;
	g_currDrug.wareid=$("#wareid-"+drugId).text();
	g_currDrug.warename=$("#warename-"+drugId).text();
	g_currDrug.warespec=$("#warespec-"+drugId).text();
	g_currDrug.saleprice=$("#saleprice-"+drugId).text();
	g_currDrug.wareunit=$("#wareunit-"+drugId).text();
	
	//设置药品名称文本框
	$("#warename").val($("#warename-"+drugId).text());
	
	//当选择一个药品后	
	Common.hideDropdownTable();  //关闭选择下拉框
	$("#drugmode").focus();      //给药方式文本框获取焦点
	setDrugWindowStatus(WINDOW_CLOSED);
}

/**
 * 选择第一条记录
 * @returns
 */
function choiceTheFirstDrug(){	
	var listx=$(".drug-item");	
	if(listx.size()>0){
		var first=$(".drug-item").eq(0);
		var drugId=first.attr("drug-id");
		process_curr_drug(drugId);
	}
}


$(function(){
	
	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	//当用户双击某药品条目时,选择此药品
	$(".drug-item").on("dblclick", handler_dblclick_drugcategory);
});