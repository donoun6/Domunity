let index = {
    init:function () {
        $(".sum-btn").on("click", ()=> { //function(){} => {} this 바인딩 하기 위해서
            this.save();
        });
    },

    save:function(){
        //alert(save 함수 호출)
        let data = {
            boardCategory : $("#boardCategory").val(),
            boardTitle: $("#boardTitle").val(),
            boardContent: $("#boardContent").val()

        }
        console.log(data);
        $.ajax({
            type:'post',
            url:'/api/board',
            data: JSON.stringify(data), //http body 데이터
            contentType: "application/json; charset=utf-8", //MIME *데이터가 어떤 타입인지
            dataType: "json", //요청을 서버로 해서 응답이 왔을 때 기본적으로 모든것이 문자열 (생긴게 json이면) => javascript 오브젝트로 변경
        }).done(function (resp){
            alert("글쓰기가 완료되었습니다.")
            window.location.replace("/");
        }).fail(function (error){
           alert("글쓰기에 실패하였습니다.");
        });

    },
}

index.init();




