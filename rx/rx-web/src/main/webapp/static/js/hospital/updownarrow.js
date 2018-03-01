
var DOWN_ARROW_CODE=40; //下箭头CODE
var UP_ARROW_CODE=38;	//上箭头CODE
var CONTAINER_ID_TABLE="dropdown-table-1";  //输入面板下拉框容器
var CONTAINER_ID_UNIT="dropdown-unit-1";	//处方列表下拉框容器

//-----------------------输入面板--------------------

//药品列表
var CURR_ROW_CLASS_NAME_DRUG="curr-row-drug";	//药品下拉框列表当前项class name
var CURR_ROW_ATTR_NAME_DRUG="index-drug";		//药品下拉框列表记录索引名称

//剂量单位
var CURR_ROW_CLASS_NAME_DOSE_UNIT="curr-row-dose-unit";	
var CURR_ROW_ATTR_NAME_DOSE_UNIT="index-dose-unit";

//times
var CURR_ROW_CLASS_NAME_TIMES="curr-row-times";	
var CURR_ROW_ATTR_NAME_TIMES="index-times";

//mode
var CURR_ROW_CLASS_NAME_MODE="curr-row-mode";	
var CURR_ROW_ATTR_NAME_MODE="index-mode";

//days
var CURR_ROW_CLASS_NAME_DAYS="curr-row-days";	
var CURR_ROW_ATTR_NAME_DAYS="index-days";

//-----------------------处方列表--------------------

//剂量单位
var CURR_ROW_CLASS_NAME_DOSE_UNIT_TABLE="curr-row-dose-unit_table";	
var CURR_ROW_ATTR_NAME_DOSE_UNIT_TABLE="index-dose-unit_table";

//times
var CURR_ROW_CLASS_NAME_TIMES_TABLE="curr-row-times_table";	
var CURR_ROW_ATTR_NAME_TIMES_TABLE="index-times_table";

//mode
var CURR_ROW_CLASS_NAME_MODE_TABLE="curr-row-mode_table";	
var CURR_ROW_ATTR_NAME_MODE_TABLE="index-mode_table";

//days
var CURR_ROW_CLASS_NAME_DAYS_TABLE="curr-row-days_table";	
var CURR_ROW_ATTR_NAME_DAYS_TABLE="index-days_table";



/**
 * 标定下一个药品
 * @returns
 */
function selectTheNextRow(className,attrName,containerx){
	var listx=getRowsByAttr("class",className);
	if(listx.size()>0){
		var curr=listx[0];
		var index=parseInt($(curr).attr(attrName));
		var tempList=getRowsByAttr(attrName,index+1);
		if (tempList.size()>0){
			removeCurrRowClass(attrName,index,className);
			addCurrRowClass(attrName,index+1,className);
			
			//移动滚动条
			var container = $('#'+containerx);
		    //var scrollTo = getRowsByAttr(attrName,index+1).eq(0);
			var scrollTo = $(tempList[0]);
			container.scrollTop(scrollTo.offset().top - container.offset().top + container.scrollTop());
			// Or you can animate the scrolling:
			//container.animate({scrollTop: scrollTo.offset().top - container.offset().top + container.scrollTop()});​
			
			
		}
	}
}

/**
 * 标定上一个药品
 * @returns
 */
function selectThePreviousRow(className,attrName,containerx,tableClass){
	var listx=getRowsByAttr("class",className);
	if(listx.size()>0){
		var curr=listx[0];
		var index=parseInt($(curr).attr(attrName));
		var tempList=getRowsByAttr(attrName,index-1);
		if (tempList.size()>0){
			removeCurrRowClass(attrName,index,className);
			addCurrRowClass(attrName,index-1,className);
			
			//移动滚动条
			var container = $('#'+containerx);
		    //var scrollTo = getRowsByAttr(attrName,index+1).eq(0);
			var scrollTo = $(tempList[0]);
			container.scrollTop(scrollTo.offset().top - container.offset().top + container.scrollTop());
			
		}
		else{
			var container = $('#'+containerx);
			var scrollTo = $('[class~='+tableClass+'] ' + 'thead').eq(0);	
			container.scrollTop(scrollTo.offset().top - container.offset().top + container.scrollTop());
		}
	}
}






//以下三个函数为通用函数
function addCurrRowClass(attrName,attrValue,className){	
	//$('['+attrName+'='+'"'+  attrValue + '"'+']').addClass(className);
	//alert('['+attrName+'='+attrValue +']');
	$('['+attrName+'='+attrValue +']').addClass(className);
}
function removeCurrRowClass(attrName,attrValue,className){
	$('['+attrName+'='+attrValue +']').removeClass(className);
}
function removeAllCurrRowClass(attrName,className){
	$('['+attrName+']').removeClass(className);
}

function getRowsByAttrName(attrName){
	return $('['+attrName+']');
}

function getRowsByAttr(attrName,attrValue){
	return $('['+attrName+'~='+attrValue +']');
}