/*
 * 2018-1-15
 */

$(function(){

	Common.setBasicWidth();
	Common.setFormHeight();
	Common.addStripedStyle();

	$(window).resize(function() {
	  Common.setBasicWidth();
	  Common.setFormHeight();
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

	showDropdownUnit : function (that) {
		var top = $(that).offset().top + 55;
		var left = $(that).offset().left;		
		$('.dropdown-unit').css({"top":top,"left":left}).show();
	},

	hideDropdownUnit : function () {
		$('.dropdown-unit').hide();
	}
}