
$(function(){
	$(".parent").click(function(){
		$(this)
		.toggleClass("selected")
		.siblings(".child_"+this.id).toggle();
		var src=$(this).find("img").attr("src");
		if(src=='image/down.png'){
			$(this).find("img").attr("src","image/up.png");
		}else{
			$(this).find("img").attr("src","image/down.png");
		}
	});
});