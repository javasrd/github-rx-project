/********************************************
 全局变量
 ********************************************/

/**
 * 药品目录双击事件处理.
 * 
 * @returns
 */
function handler_dblclick_drugcategory() {
	// 用于根据ID查询当前药品的属性值.
	var drugId = $(this).attr("drug-id");
	process_curr_drug(drugId);
}

/**
 * 处理当前选择的药品 (1)
 * 
 * @param drugId
 *            药品id
 * @returns void
 */
function process_curr_drug(drugId) {

	// 有效性验证
	// 在处方列表中查询此药品是否已经存在
	var idx = searchDrugById(drugId);
	if (idx >= 0) {
		// （1）提示而后让用户重新输入
		alert("此药品已经存在！", 500);
		$("#abc")[0].focus(); // 药品助记框获得焦点
		return false;
	}

	g_currDrug = new Object();

	// 在返回的药品列表中查询医生所选择的那种药品.
	var choiceDrugIndex = searchByDrugId(drugId);	
	var drug = ret_drugCategory[choiceDrugIndex];

	/*
	 * g_currDrug.wareid=$("#wareid-"+drugId).text(); //药品ID(海典方)
	 * g_currDrug.warename=$("#warename-"+drugId).text(); //药品名称
	 * g_currDrug.warespec=$("#warespec-"+drugId).text(); //药品规格
	 * g_currDrug.saleprice=$("#saleprice-"+drugId).text();//药品售价
	 * g_currDrug.wareunit=$("#wareunit-"+drugId).text(); //药品单位
	 */

	g_currDrug.id = drugId; // 药品ID,处方流转系统中自定义的ID
	g_currDrug.wareid = drug.wareid; // 商品ID,海典方自定义的ID
	g_currDrug.warename = drug.warename; // 药品名称
	g_currDrug.warespec = drug.warespec; // 药品规格
	
	//保存默认值到当前药品中
	g_currDrug.jl=drug.jl;  //剂量  
	g_currDrug.lc=drug.lc;	//疗程
	g_currDrug.pc=drug.pc;	//批次	

	// 将售价修改为最小包装售价,单位改为最小包装单位(有可能与原来的售价及包装单位相同)
	// (1)采用原来的售价及单位做为默认值
	g_currDrug.saleprice = drug.saleprice;// 售价
	g_currDrug.wareunit = drug.wareunit; // 单位.
	// (2)采用最小包装售价(xuanx)及单位(saleminunit)
	if (drug.xuanx != null && drug.xuanx != 0) {
		g_currDrug.saleprice = drug.xuanx;
	}
	if (drug.saleminunit != null && drug.saleminunit != "") {
		g_currDrug.wareunit = drug.saleminunit;
	}

	// 默认值也保存在相应的记录中.
	// g_currDrug.

	// 设置药品名称文本框
	$("#warename").val($("#warename-" + drugId).text());

	// 当选择一个药品后
	Common.hideDropdownTable(); // 关闭选择下拉框
	$("#single-dosage").focus(); // 单次剂量文本框获取焦点
	setDrugWindowStatus(WINDOW_CLOSED);

	// added by jch 2018/06/18
	// 尝试自动填充默认值,并自动计算数量.
	fillFieldByDefault(choiceDrugIndex,g_currDrug);
}

/**
 * 功能: 在返回的药品列表按药品ID进行查询 *
 * 
 * @param drugId
 *            药品ID
 * @returns 如果找到则返回相应的索引.否则返回-1
 */
function searchByDrugId(drugId) {
	for (var i = 0; i < ret_drugCategory.length; i++) {
		var drug = ret_drugCategory[i];
		if (drugId == drug.id)
			return i;
	}
	return -1;
}

/**
 * 功能: (1)采用默认值填充如下输入框 单次剂量 单次剂量单位 频次 用法 疗程 (2)自动计算数量
 * 
 * 
 * @param drugIndex
 *            使用医生所选择的药品自动填充字段.
 * @returns
 */
function fillFieldByDefault(drugIndex,choicedDrug) {
	var drug = ret_drugCategory[drugIndex]; // 医生所选择的药品
	var flagArr=new Array();  //是否已经填充的标志. 其中的对象有两个属性.其中的对象为未填充的输入框的ID
	var flag=null;

	// 一共有5个字段.
	// 单次剂量采用如 0.1mg的方式录入.需要进行析取
	// 单次剂量只析取数字部分,单次剂量单位只析取非数字部分.
	// 采用正则表达式进行析取.

	// 析取剂量
	var regExtractDosage = /(-?\d+)(\.\d+)?/;
	var dosageArr = regExtractDosage.exec(drug.jl);
	if (dosageArr != null && dosageArr.length > 0) {
		setInputBoxVal("#single-dosage", dosageArr[0]);// 单次剂量
		choicedDrug.dosage_value=dosageArr[0];
	} else {		
		setFlagArr(flagArr,"#single-dosage");
		choicedDrug.dosage_value=0;
	}

	// 析取剂量单位
	var doseUnit = (drug.jl).replace(regExtractDosage, "");
	if (doseUnit != "" && doseUnit != null) {
		setInputBoxVal("#single-dose-unit", doseUnit); // 单次剂量单位		
	} else {
		setFlagArr(flagArr,"#single-dose-unit");		
	}

	// 频次(用药次数)
	var regExtractDrugTimes = /(-?\d+)(\.\d+)?/;
	var drugTimesArr = regExtractDrugTimes.exec(drug.pc);
	if (drugTimesArr != null && drugTimesArr.length > 0) {
		setInputBoxVal("#drugtimes", drug.pc); //频次
		choicedDrug.drugtimes_value=drugTimesArr[0];    //保存频次
	} else {
		setFlagArr(flagArr,"#drugtimes");
		choicedDrug.drugtimes_value=0;    //保存频次
	}

	// 给药方式默认值,不影响数量的自动计算.
	if (drug.yfyl != null && drug.yfyl != "") {
		setInputBoxVal("#drugmode", drug.yfyl); // 给药方式
	}
	else{
		setFlagArr(flagArr,"#drugmode");
	}

	// 疗程
	// 析取疗程 将疗程中的数字析取出来.
	var regExtractTreatmentDays = /(-?\d+)(\.\d+)?/;
	var treatmentDaysArr = regExtractTreatmentDays.exec(drug.lc);
	if (treatmentDaysArr != null && treatmentDaysArr.length > 0) {
		setInputBoxVal("#treatment-days", drug.lc); // 疗程
		choicedDrug.days_value=treatmentDaysArr[0];    //保存疗程天数
	}
	else{
		setFlagArr(flagArr,"#treatment-days");
		choicedDrug.days_value=0;    //保存疗程天数
	}

	// 最小包装规格,试着解析saleminspec
	// 将所有的非数字,非*,非. 字符全部替换成"" 而后计算表达式的值
	var regMinSpec = /[^0-9^\*^\.]/g;
	// alert(drug.saleminspec);
	var minSpec = (drug.saleminspec).replace(regMinSpec, "");
	// alert(minSpec);
	var minSpecVal = -1;
	if (minSpec != null && minSpec != "") {
		// alert(minSpecArr[0]);
		try {
			minSpecVal = eval(minSpec); // 在这里运行代码
			choicedDrug.minspec_value=minSpecVal;
		} catch (err) {
			// 在这里处理错误
			setFlagArr(flagArr,"#quantity");
			choicedDrug.minspec_value=0;	
		}
	} else {
		setFlagArr(flagArr,"#quantity");
		choicedDrug.minSpecVal=0;
	}

	// 尝试自动计算数量.
	var quantity = -1;
	if (dosageArr != null && drugTimesArr != null && treatmentDaysArr){
		if (dosageArr.length >= 0 && drugTimesArr.length > 0
				&& treatmentDaysArr.length > 0 && minSpecVal > 0) {
			quantity = dosageArr[0] * drugTimesArr[0] * treatmentDaysArr[0]
					/ minSpecVal;
			// alert(quantity);
			// 如果计算出相应的数值,如果有小数部分则向上取整.
			quantity = Math.ceil(quantity);

			setInputBoxVal("#quantity", quantity); // 自动计算数量成功
			setFlagArr(flagArr,"#quantity");

		} else {
			setFlagArr(flagArr,"#quantity");
		}
	}
	
	//将光标定位到第一个未自动填充的输入框
	if(flagArr.length>0){
		$(flagArr[0].inputId).focus();
	}

}

/**
 * 向标记数组中增加未填充的输入框ID
 * @param flagArr  标记数组
 * @param inputId  输入id  包括 "#" 号
 * @returns
 */
function setFlagArr(flagArr,inputId){
	var flag=new Object;
	flag.inputId=inputId;
	flagArr.push(flag);
} 


/**
 * 设置输入框的值
 * 
 * @param inputId
 *            文本输入框ID
 * @param val
 *            值
 * @returns
 */
function setInputBoxVal(inputId, val) {
	$(inputId).attr("disabled", true);
	$(inputId).val(val);
	$(inputId).attr("disabled", false);
}

/**
 * 选择第一条记录
 * 
 * @returns
 */
function choiceTheCurrRow_drug() {
	var listx = getRowsByAttr("class", CURR_ROW_CLASS_NAME_DRUG);
	if (listx.size() > 0) {
		var curr = listx[0];
		var drugId = $(curr).attr("drug-id");
		process_curr_drug(drugId);
	} else {
		alert("未选择药品!");
	}
}

/**
 * 功能:分页查询
 * 
 * @returns
 * 
 */
function search_page() {
	var abc = ret_abc; // 采用返回的条件值做为参数条件
	// 分页数据
	var pageNum = $("#pageNum_drug").val();
	var pageSize = $("#pageSize_drug").val();

	// alert("debug!");
	loadDrugTable(abc, pageNum, pageSize);

}

$(function() {

	/***************************************************************************
	 * 绑定事件
	 **************************************************************************/
	// 当用户双击某药品条目时,选择此药品
	$(".drug-item").on("dblclick", handler_dblclick_drugcategory);
	addCurrRowClass(CURR_ROW_ATTR_NAME_DRUG, 1, CURR_ROW_CLASS_NAME_DRUG);

	// -------------分页（页码导航）--------------------------------
	/*
	 * 【分页】导航： 当点击页号时读取需要导航到的页码及每页记录数（pageNum,pageSize） data-bind:保存页号及页大小
	 * 数据格式:pageNum-pageSize 如果data-bind为空字符串，则不做动作 否则当用户点击某页时，则发送与筛选相同的请求
	 * -----------------------------------------------------
	 */

	$(".pagination li a").on('click', function(e) {
		// alert($(this).attr("data-bind"));
		// console.log("debug1");
		var dataBind = $(this).attr("data-bind");
		// 当dataBind有数据时处理
		if (dataBind != "") {
			var pageArr = new Array();
			pageArr = dataBind.split("-");
			// 置隐藏表单数据
			$("#pageNum_drug").val(pageArr[0]);
			$("#pageSize_drug").val(pageArr[1]);

			search_page(); // 发送请求
		}

	});

});