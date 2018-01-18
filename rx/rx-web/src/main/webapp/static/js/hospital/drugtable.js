
/***************************************************************************
	全局变量
***************************************************************************/
var g_drugList=new Array();  //用户选择的药品列表.
var g_currDrug=new Object(); //用户选择的当前药品

$(function(){
	
	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	//当用户双击某药品条目时,选择此药品
	$(".drug-item").on("dblclick", function() {		
		
		//用于根据ID查询当前药品的属性值.
		var drugId=$(this).attr("drug-id");
		
		/*<td th:text="${drug.wareid}" th:attr="id='wareid-'+${drug.id}"></td>
		<td th:text="${drug.warename}" th:attr="id='warename-'+${drug.id}"></td>
		<td th:text="${drug.warespec}" th:attr="id='warespec-'+${drug.id}"></td>
		<td th:text="${drug.saleprice}" th:attr="id='saleprice-'+${drug.id}"></td>						
		<td th:text="${drug.wareunit}" th:attr="id='wareunit-'+${drug.id}"></td>
		<td th:text="${drug.abc}" ></td>*/
		
		
		g_currDrug.id=drugId;
		g_currDrug.wareid=$("#wareid-"+drugId).text();
		g_currDrug.warename=$("#warename-"+drugId).text();
		g_currDrug.warespec=$("#warespec-"+drugId).text();
		g_currDrug.saleprice=$("#saleprice-"+drugId).text();
		g_currDrug.wareunit=$("#wareunit-"+drugId).text();
		
		
		$("#warename").val($("#warename-"+drugId).text());
		alert($("#warename-"+drugId).text());
		
		
		
	});
});