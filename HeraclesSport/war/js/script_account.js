var loaded =false;

function loadPage(){ 
    loaded = true;
};
$(document).ready(function(){    
    	if(!loaded)
        	loadPage();
    	$('#generate').click(function(event) {
	        $.get('/generate',function(responseText) {
	        	$('#pseudo').val(responseText);        
	        });
	        return false;
    	});
});

