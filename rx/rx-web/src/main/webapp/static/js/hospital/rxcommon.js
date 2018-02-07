/*******************************************************************************
 * IE浏览器兼容性
 ******************************************************************************/

function isIE1(selector) {
	// for ie
	if (!IEVersion()==-1) {
		/* if (document.all) { */
		$(selector).each(
				function() {
					var that = this;

					if (this.attachEvent) {
						this.attachEvent('onpropertychange', function(e) {
							if (e.propertyName != 'value') {
								return;
							}
							if (e.propertyName == 'value'
									&& !$(that).attr("disabled")) {
								$(that).trigger('input');
							}
						});
					}
				})
	}
}

function IEVersion() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串 
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器 
    var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器 
    var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
    if(isIE) {
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        if(fIEVersion == 7) {
            return 7;
        } else if(fIEVersion == 8) {
            return 8;
        } else if(fIEVersion == 9) {
            return 9;
        } else if(fIEVersion == 10) {
            return 10;
        } else {
            return 6;//IE版本<=7
        }  
    } else if(isEdge) {
        return 'edge';//edge
    } else if(isIE11) {
        return 11; //IE11 
    }else{
        return -1;//不是ie浏览器
    }
}

function iFrameHeight() {   
	var ifm= document.getElementById("print-modal-content");   
	var subWeb = document.frames ? document.frames["print-modal-content"].document : ifm.contentDocument;   
	if(ifm != null && subWeb != null) {
	   ifm.height = subWeb.body.scrollHeight;
	   ifm.width = subWeb.body.scrollWidth;
	}   
	} 

/*******************************************************************************
 * 采用onpropertychange事件来模拟input事件 用于IE兼容性的函数
 * 
 * @returns
 ******************************************************************************/
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
/*********************************************
 * 数字转换通用函数
 ********************************************/
//功能：将浮点数四舍五入，取小数点后2位    
function toDecimal(x) {    
    var f = parseFloat(x);    
    if (isNaN(f)) {    
        return;    
    }    
    f = Math.round(x*100)/100;    
    return f;    
}    


//制保留2位小数，如：2，会在2后面补上00.即2.00    
function toDecimal2(x) {    
    var f = parseFloat(x);    
    if (isNaN(f)) {    
        return false;    
    }    
    var f = Math.round(x*100)/100;    
    var s = f.toString();    
    var rs = s.indexOf('.');    
    if (rs < 0) {    
        rs = s.length;    
        s += '.';    
    }    
    while (s.length <= rs + 2) {    
        s += '0';    
    }    
    return s;    
}    
    
function fomatFloat(src,pos){       
     return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);       
} 

/*******************************************************************************
 * 定义数组的索引与删除
 ******************************************************************************/
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

/**
 * 检查是否为正整数
 * @param a
 * @returns
 */
function  isUnsignedInteger(a)
 {
     var reg=/^[1-9]\d*$/;
     return reg.test(a);
 }

/**
 * 检查是否为正数
 * @param a
 * @returns
 */
function isPositiveNumber(a){
	var reg=/^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
	return reg.test(a);
}

/**********************************************************
 * 事件绑定
 **********************************************************/
/**
 * 绑定IE propertychange event，用于模拟input事件。
 * 
 * @param eventName
 * @param selector
 * @param handler
 * @returns
 */
function bindIEEvent(eventName, selector, handler) {
	isIE1(selector);
	$(selector).on(eventName, handler);
}

/**
 * 绑定其它非input事件
 * 
 * @param eventName
 * @param selector
 * @param handler
 * @returns
 */
function bindEvent(eventName, selector, handler) {
	$(selector).on(eventName, handler);
}

/**
 * 设置焦点
 * 
 * @param id
 *            DOM对象ID
 * @returns
 */
function setFocus(id) {
	$(id)[0].focus();
}