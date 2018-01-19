
/***************************************************************************
	全局变量
***************************************************************************/
$(function(){
	
	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	//当用户双击某药品条目时,选择此药品
	$(".drug-item").on("dblclick", function() {		
		
		//用于根据ID查询当前药品的属性值.
		var drugId=$(this).attr("drug-id");		
		
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
		
		
	});
});