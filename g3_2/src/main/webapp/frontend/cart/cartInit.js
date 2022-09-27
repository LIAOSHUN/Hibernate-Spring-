function init() {
   $.ajax({
			url: "cart.do",
			type: "POST",
			data: {
				action: "init"
			},
			success: function(){
				
			}
		})
}

window.onload = init;