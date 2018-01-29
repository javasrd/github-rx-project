/*
 * 2018-1-15
 */

var yScroll = 0;

$(function(){

	Common.setBasicWidth();
	Common.setFormHeight();
	Common.addStripedStyle();

	$(window).resize(function() {
	  Common.setBasicWidth();
	  Common.setFormHeight();
	});
	
	
	$(window).scroll(function getPageScroll() {
	    if (self.pageYOffset) {
	        yScroll = self.pageYOffset;
	        //xScroll = self.pageXOffset; 
	    } else if (document.documentElement && document.documentElement.scrollTop) {
	        yScroll = document.documentElement.scrollTop;
	    } else if (document.body) {
	        yScroll = document.body.scrollTop;
	    }
	    //arrayPageScroll = new Array('',yScroll)        
	    return yScroll;
	});
	
	
});



var Common = {
	setBasicWidth : function () {
		var bodyWidth = $("body").width() - 232;
		$("#basicForm").css("width",bodyWidth);
	},

	setFormHeight : function () {
		var bodyheight = $("body").height() - 420;
		$("#drugForm").css("height",bodyheight);
	},
	
	showDropdownTable : function (that) {
		$(".dropdown-table").remove();

		var addNode = '<div class="dropdown-table"><table class="table table-condensed table-hover">'+
                      '<thead><tr></tr></thead><tbody><tr></tr></tbody></table></div>';
		
		/*var addNode = '<div class="dropdown-table"></div>';*/	
		
        that.parent().append(addNode);
	},
	
	hideDropdownTable : function () {
		$(".dropdown-table").remove();
	},

	addStripedStyle : function () {
		$("#drugForm tr").filter(":odd").addClass("odd");
		$("#drugForm tr").filter(":even").addClass("even");
	},

	//modified by jch 2018/01/25
	//解决浏览器兼容性问题.
	showDropdownUnit : function (that) {
		var top = $(that).offset().top+55;
		if ($.browser.msie) {
			top = $(that).offset().top+55+yScroll;
		}
		var left = $(that).offset().left;
		$('.dropdown-unit').css({"top":top,"left":left}).show();
	},

	hideDropdownUnit : function () {
		$('.dropdown-unit').hide();
	}
}