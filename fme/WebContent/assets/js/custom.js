

!function($) {
    "use strict";
	// Minimalize menu
    $('.navbar-minimalize').on('click', function () {
		if($(window).width() > 991)
		{
			 $("body").toggleClass("mini-navbar");
		}
		else
		{
        $("body").toggleClass("mini-navbar-show");
		}


    });
   $('[data-toggle="tooltip"]').tooltip();
    $('[data-toggle="popover"]').popover()
	// $.material.init();
	
$(function(){
    $('.scroller').slimScroll({
        height: '250px'
    });
	//$('#input-date-added').datepicker();
	//$('#input-date-modified').datepicker();
	$('#menu').metisMenu();
});
	
	
	$(function() {
	
		$( "#datepicker1,#datepicker2,#datepicker3" ).datepicker({ dateFormat: 'dd/mm/yy',minDate:0 });

		$('#datepicker2').change(function() {
			var start = $('#datepicker1').datepicker('getDate');
			var end   = $('#datepicker2').datepicker('getDate');

		if (start<end) {
			var days   = (end - start)/1000/60/60/24;
		$('#days').val(days);
		}
		else {
		  alert ("To Date must be Greater than From Date!");
		  $('#datepicker1').val("");
		  $('#datepicker2').val("");
			$('#days').val("");
		}
		}); //end change function

	}); //end ready
			
											
	
	}(window.jQuery);	