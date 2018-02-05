// TODO, 后续采用面象对象的方式开发JS
/*******************************************
 * 药品列表处理方法
 *******************************************/
var g_prescDrugList = new Array(); // 当前处方药品列表.
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

/**
 * getter;
 */
function getDrugList(){
	return g_prescDrugList;
}
/**
 * 获得当前药品列表的长度
 */
function getDrugListLength(){
	return g_prescDrugList.length;
}

/**
 * 计算现在处方中药品的总金额
 * @returns 总金额
 */
function calcPrescDrugAmount(){
	var sum=0;
	var drugList=getDrugList();
	for(i=0;i<drugList.length;i++){
		sum=sum+drugList[i].saleprice*drugList[i].quantity;
	}
	return sum;
}