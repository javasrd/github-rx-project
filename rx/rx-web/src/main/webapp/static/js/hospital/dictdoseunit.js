/***************************************************************************
	全局变量
***************************************************************************/


$(function(){
	
	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	//当用户双击某药品条目时,选择此药品
	$(".doseunit-item").on("dblclick", function() {		
		
		//用于根据ID查询当前药品的属性值.
		var doseUnitId=$(this).attr("doseunit-id");		
		//设置用法文本框
		var dosename=$("#doseunitname-"+doseUnitId).text();
		$("#"+g_edit_doseunit_id).val(dosename);
		//当选择一个剂量单位后
		Common.hideDropdownUnit();  //关闭选择下拉框
		
		
		//将选择的剂量单位值保存到处方药品列表相应的对象中
		var drugId=$("#"+g_edit_doseunit_id).attr("data-id");
		//自g_prescDrugList查询,并置doseunit
		for(var i=0;i<g_prescDrugList.length;i++){
			if(g_prescDrugList[i].id==drugId){
				g_prescDrugList[i].doseunit=$("#"+g_edit_doseunit_id).val();
				//alert("after double click:"+$("#"+g_edit_doseunit_id).val());
			}
		}
		
				
				
		
	});
});