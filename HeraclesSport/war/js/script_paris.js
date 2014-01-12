var loaded =false;

function loadPage(){ 
	$(".paris_form").css('display','none');
	$(".active .paris_form").css('display','block');
    loaded = true;
};
$(document).ready(function(){    
    	if(!loaded)
        	loadPage();
    	$(".paris_type_scor").css('display','none');
    	$(".paris_type").change(function() {
    		if($(this).children('option:selected').val()==="vict") {
    			$(this).parent().parent().children(".paris_type_scor").hide("slow");
    			$(this).parent().parent().children(".paris_type_vict").show( "slow");
    		}
    		else if($(this).children('option:selected').val()==="scor") {
    			$(this).parent().parent().children(".paris_type_vict").hide("slow");
    			$(this).parent().parent().children(".paris_type_scor").show( "slow");
    		}
    	});
    	$(".paris_scor_eqp").change(function() {
    		if($(this).children('option:selected').val()==="all") {
    			$(this).parent().parent().children(".scor_eqp_home").show("slow");
    			$(this).parent().parent().children(".scor_eqp_away").show( "slow");
    		}
    		else if($(this).children('option:selected').val()==="home") {
    			$(this).parent().parent().children(".scor_eqp_away").hide( "slow");
    			$(this).parent().parent().children(".scor_eqp_home").show("slow");
    		}
    		else if($(this).children('option:selected').val()==="away") {
    			$(this).parent().parent().children(".scor_eqp_home").hide( "slow");
    			$(this).parent().parent().children(".scor_eqp_away").show("slow");
    		}    		
    	});
    	$(".paris_show").click(function() {
    		if(!$(this).parent().hasClass("active")) {
	    		// Hide active
	    		$(".active").children(".paris_form").hide("slow");
	    		$(".active").removeClass("active");
	    		// Show new
	    		$(this).parent().addClass("active");
	    		$(".active").children(".paris_form").show( "slow");
    		}
    		return false;
    	});
    	var $active = $('.active');

    	    if($active.length) {
		        $('html, body').animate({  
		            scrollTop:$active.offset().top-200
		        }, 'slow');
    	    }
});

