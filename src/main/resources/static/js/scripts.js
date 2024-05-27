
$(".menuBtn").on("click",function(){
    $(".menubar").css("display","block");
    $(".menuBtn").css("display","none")
});

$(".returnBtn").on("click",function(){
    $(".menubar").css("display","none");
    $(".menuBtn").css("display","block");
});


$("li>.row>.writeBtn").on("click",function(){
    var index=$("li>.row>.writeBtn").index(this);

    $("li>.row>.modify").eq(index).css("display","block");
});


