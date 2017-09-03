//------------- forms-wizard.js -------------//
!function($) {
    "use strict";



	//------------- Form wizard -------------//

	//add validation to wizard
	var $validator = $("#wizard form").validate({
		errorPlacement: function( error, element ) {
			var place = element.closest('.input-group');
			if (!place.get(0)) {
				place = element;
			}
			if (place.get(0).type === 'checkbox') {
				place = element.parent();
			}
			if (error.text() !== '') {
				place.after(error);
			}
		},
		errorClass: 'help-block',
		rules: {
			firstname: {
 				required: true
 			},
 			email: {
 				required: true,
 				email: true
 			},
 			username: {
 				required: true
 			},
 			password: {
 				required: true,
				pwcheck: true,
 				minlength: 8
				
 			},
 			password_2: {
 				required: true,
				minlength: 8,
				equalTo: "#password"
 			}
	 	},
 		messages: {
 			firstname: {
 				required: "Required"
 			},
 			email: {
 				required: "Required"
 			}
 		},
 		highlight: function( label ) {
			$(label).closest('.form-group').removeClass('has-success').addClass('has-error');
		},
		success: function( label ) {
			$(label).closest('.form-group').removeClass('has-error');
			label.remove();
		}
	});
	
	//init first wizard
	$('#wizard').bootstrapWizard({
		tabClass: 'bwizard-steps',
		nextSelector: 'ul.pager li.next',
		previousSelector: 'ul.pager li.previous',
		firstSelector: null,
		lastSelector: null,
		onNext: function( tab, navigation, index, newindex ) {
			var validated = $('#wizard form').valid();
			if( !validated ) {
				$validator.focusInvalid();
				return false;
			}


	// Laxman Model
		else if ($('#dontWant').is(':checked')) {

		$('#myModal').modal('hide');
		}
		else{
			$("#myModal").modal('show');         // For Displaying Model when click on Nav buttons
		}
	// Laxman model Ends


		},
		onTabClick: function( tab, navigation, index, newindex ) {
			if ( newindex == index + 1 ) {

		//		$("#myModal").modal('show');
				return this.onNext( tab, navigation, index, newindex);
			} else if ( newindex > index + 1 ) {
				return false;
			} else {
				return true;
			}
		},
		onTabShow: function( tab, navigation, index ) {
			tab.prevAll().addClass('completed');
			tab.nextAll().removeClass('completed');
			var $total = navigation.find('li').length;
			var $current = index+1;
			//Add these 2 lines for progress bar
			var $percent = ($current/$total) * 100;
			$('#wizard').find('.progress-bar').css({width:$percent+'%'});
			// If it's the last tab then hide the last button and show the finish instead
			if($current >= $total) {
				$('#wizard').find('.pager .next').hide();
				$('#wizard').find('.pager .finish').show();
				$('#wizard').find('.pager .finish').removeClass('disabled');
			} else {
				$('#wizard').find('.pager .next').show();
				$('#wizard').find('.pager .finish').hide();
			}
		}
	});

	

	//------------- Wizard with progressbar -------------//
	//init first wizard
	$('#wizard1').bootstrapWizard({
		tabClass: 'bwizard-steps',
		nextSelector: 'ul.pager li.next',
		previousSelector: 'ul.pager li.previous',
		firstSelector: null,
		lastSelector: null,
		onTabShow: function( tab, navigation, index ) {
			tab.prevAll().addClass('completed');
			tab.nextAll().removeClass('completed');
			var $total = navigation.find('li').length;
			var $current = index+1;
			var $percent = ($current/$total) * 100;
			$('#wizard1').find('.progress-bar').css({width:$percent+'%'});
			// If it's the last tab then hide the last button and show the finish instead
			if($current >= $total) {
				$('#wizard1').find('.pager .next').hide();
				$('#wizard1').find('.pager .finish').show();
				$('#wizard1').find('.pager .finish').removeClass('disabled');
			} else {
				$('#wizard1').find('.pager .next').show();
				$('#wizard1').find('.pager .finish').hide();
			}
		}
	});

	//wizard is finish
	$('#wizard1 .finish').click(function() {
		//show message
	});
	
}(window.jQuery);