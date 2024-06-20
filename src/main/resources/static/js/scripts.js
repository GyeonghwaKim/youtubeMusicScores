
$("#recommend").on("click",function() {
    if (confirm("정말로 추천하시겠습니까?")) {
        alert("추천이 완료되었습니다.");
         var uri = $(this).data("uri");
         window.location.href = uri;
    } else {
        alert("추천이 취소되었습니다.");
    }
});

$("li>div>#nav-modifyBtn").on("click",function(){
                      var index=$("li>div>#nav-modifyBtn").index(this);

                      $("li>#nav-modifyDiv").eq(index).toggle();

            });

