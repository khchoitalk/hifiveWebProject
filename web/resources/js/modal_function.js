$(function() {
	
	$(document).ready(function() {
		
		$(".dd").click(function() {
			$("#myModal1").css({
				"display" : "block"
			});
		});

		$(".close").click(function() {
			$("#myModal1").css({
				"display" : "none"
			});
		});

		$("html").click(function(event) {
			if (event.target.id === "myModal1") {
				$("#myModal1").css({
					"display" : "none"
				});
			}
		});
		
	});
})