/*******************************************************************************
 * IE浏览器兼容性
 ******************************************************************************/

function isIE1(selector) {
	// for ie
	if ($.browser.msie) {
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