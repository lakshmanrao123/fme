$(function() {
		
	$.get('allExecutives', function(response) {
         
        var list = $('#elist');
    
         $.each(response, function(index, v ) {
          	 
        	 list.append('<option value="' + v.userid + '">' + v.name + '</option>');
        	  
            });
     	 });
	
	 
	  
	}); 