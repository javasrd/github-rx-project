/*!
 * jQuery Print Previw Plugin v1.0.1
 *
 * Copyright 2011, Tim Connell
 * Licensed under the GPL Version 2 license
 * http://www.gnu.org/licenses/gpl-2.0.html
 *
 * Date: Wed Jan 25 00:00:00 2012 -000
 */
 
(function($) { 
    
	// Initialization
	$.fn.printPreview = function() {
		this.each(function() {
			$(this).bind('click', function(e) {
			    e.preventDefault();
			    if (!$('#print-modal').length) {
			        $.printPreview.loadPrintPreview();
			    }
			});
		});
		return this;
	};
    
    // Private functions
    var mask, size, print_modal, print_controls;
    $.printPreview = {
        loadPrintPreview: function() {
            // Declare DOM objects
            print_modal = $('<div id="print-modal"></div>');
            print_controls = $('<div id="print-modal-controls">' + 
                                    '<a href="#" class="print" title="打印"></a>' +
                                    '<a href="#" class="closex" title="关闭打印预览"></a>').hide();
            var print_frame = $('<iframe id="print-modal-content" scrolling="no" border="0" frameborder="0" name="print-frame"  />');

            // Raise print preview window from the dead, zooooooombies
            print_modal
                .hide()
                .append(print_controls)
                .append(print_frame)
                .appendTo('body');

            // The frame lives
            for (var i=0; i < window.frames.length; i++) {
                if (window.frames[i].name == "print-frame") {    
                    var print_frame_ref = window.frames[i].document;
                    break;
                }
            }
            
            print_frame_ref.open();
            /*print_frame_ref.write('<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">' +
                '<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">' + 
                '<head><title>' + document.title + '</title>'+
                ' </head>' +
                '<body></body>' +
                '</html>');*/
            print_frame_ref.write('<!DOCTYPE html>' +
                    '<html xml:lang="en" lang="en">' + 
                    '<head><title>' + document.title + '</title>'+
                    ' </head>' +
                    '<body></body>' +
                    '</html>');
            print_frame_ref.close();
            
            // Grab contents and apply stylesheet
            var previewAreaContainer="#print-preview-area";  //位于hospital.html
            var $iframe_head = $('head link[media*=print], head link[media=all]').clone(),
                /*$iframe_body = $('body > *:not(#print-modal):not(script)').clone();*/
            	$iframe_body = $(previewAreaContainer+' > *:not(script)').clone();
            $iframe_head.each(function() {
                $(this).attr('media', 'all');
            });
            if ((IEVersion()==-1)) {  //非IE浏览器
            	//alert("the browser is not ie....");
                $('head', print_frame_ref).append($iframe_head);
                $('body', print_frame_ref).append($iframe_body);
            }
            else {
            	//alert("the browser is ie");
                /*$('body > *:not(#print-modal):not(script)').clone().each(function() {
                    $('body', print_frame_ref).append(this.outerHTML);
                });
                $('head link[media*=print], head link[media=all]').each(function() {
                    $('head', print_frame_ref).append($(this).clone().attr('media', 'all')[0].outerHTML);
                });*/
            	
            	$(previewAreaContainer+' > *:not(#print-modal):not(script)').clone().each(function() {
                    $('body', print_frame_ref).append(this.outerHTML);
                });
            	
            	$('head link[media*=print], head link[media=all]').each(function() {
                    $('head', print_frame_ref).append($(this).clone().attr('media', 'all')[0].outerHTML);
                });
            	
            }
            
            // Disable all links
            $('a', print_frame_ref).bind('click.printPreview', function(e) {
                e.preventDefault();
            });
            
            // Introduce print styles
            $('head').append('<style type="text/css">' +
                '@media print {' +
                    '/* -- Print Preview --*/' +
                    '#print-modal-mask,' +
                    '#print-modal {' +
                        'display: none !important;' +
                    '}' +
                '}' +
                '</style>'
            );

            // Load mask
            $.printPreview.loadMask();

            // Disable scrolling
            $('body').css({overflow: 'hidden', height: '100%'});
            $('img', print_frame_ref).load(function() {
                print_frame.height($('body', print_frame.contents())[0].scrollHeight);
            });
            
            // Position modal
            starting_position = $(window).height() + $(window).scrollTop();            
            var css = {
                    top:         starting_position,
                    height:      '100%',
                    overflow:   'auto',
                    zIndex:      999,
                    display:     'block'
                }
            
            //added by jch 
            //var topx = $.printPreview.sizeTop();
            var topx=0;
            print_modal
                .css(css)
                .animate({ top:$(window).scrollTop()+topx}, 400, 'linear', function() {
                    print_controls.fadeIn('slow').focus();
                });
            /*print_modal.css(css);*/
            print_frame.height($('body', print_frame.contents())[0].scrollHeight);
            
            // Bind closure
            $('a', print_controls).bind('click', function(e) {
                e.preventDefault();
                if ($(this).hasClass('print')) {
                	//window.print();
                	//$.print("#printarea");
                	//$(window.frames["print-frame"].document).find("body").print();
                	
                	$.printPreview.distroyPrintPreview();
                	$("#btn-print").trigger("click");
                	 
                }
                else { $.printPreview.distroyPrintPreview(); }
            });
    	},
    	
    	distroyPrintPreview: function() {
    	    print_controls.fadeOut(100);
    	    print_modal.animate({ top: $(window).scrollTop() - $(window).height(), opacity: 1}, 400, 'linear', function(){
    	        print_modal.remove();
    	        /*$('body').css({overflow: 'auto', height: 'auto'});*/
    	        $('body').css({overflow: 'auto', height: '100%'});
    	    });
    	    mask.fadeOut('slow', function()  {
    			mask.remove();
    		});				

    		$(document).unbind("keydown.printPreview.mask");
    		mask.unbind("click.printPreview.mask");
    		$(window).unbind("resize.printPreview.mask");
    		
	    },
	    
    	/* -- Mask Functions --*/
	    loadMask: function() {
	    	//alert("test");
	        size = $.printPreview.sizeUpMask();
            mask = $('<div id="print-modal-mask" />').appendTo($('body'));
    	    mask.css({				
    			position:           'fixed', 
    			top:                 0, 
    			left:                0,
    			width:               size[0],
    			height:              size[1],
    			display:             'none',
    			opacity:             0,					 		
    			zIndex:              998,
    			backgroundColor:     '#000'
    		});
	
    		mask.css({display: 'block'}).fadeTo('400', 0.75);
    		
            $(window).bind("resize.printPreview.mask", function() {
				$.printPreview.updateMaskSize();
			});
			
			mask.bind("click.printPreview.mask", function(e)  {
				$.printPreview.distroyPrintPreview();
			});
			
			$(document).bind("keydown.printPreview.mask", function(e) {
			    if (e.keyCode == 27) {  $.printPreview.distroyPrintPreview(); }
			});
        },
    
        sizeUpMask: function() {
            if (!(IEVersion()==-1)) {  //IE browser
            	// if there are no scrollbars then use window.height
            	//alert("document height:"+$(document).height());
            	//alert("window height:"+$(window).height());
            	var d = $(document).height(), w = $(window).height();
            	return [
            		window.innerWidth || 						// ie7+
            		document.documentElement.clientWidth || 	// ie6  
            		document.body.clientWidth, 					// ie6 quirks mode
            		d - w < 20 ? w : d
            	];
            	
            } else {            	
            	return [$(document).width(), $(document).height()]; }
        },
        
        //added by jch
        /*sizeTop: function() {
        	var d = $(document).height(), w = $(window).height();
            if (!(IEVersion()==-1)) {
            	return d-w;
            } else {return 0; }
        },*/
    
        updateMaskSize: function() {
    		var size = $.printPreview.sizeUpMask();
    		mask.css({width: size[0], height: size[1]});
        },
        
        /*getWindowWidth: function () {
    		var windowWidth = 0;
    		if (typeof(window.innerWidth) == 'number') {
    			windowWidth = window.innerWidth;
    		}
    		else {
    			if (document.documentElement && document.documentElement.clientWidth) {
    				windowWidth = document.documentElement.clientWidth;
    			}
    			else {
    				if (document.body && document.body.clientWidth) {
    					windowWidth = document.body.clientWidth;
    				}
    			}
    		}
    		return windowWidth;
    	}*/
    }
})(jQuery);