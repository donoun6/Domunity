let index = {
    init:function () {
        $(".sum-btn").on("click", ()=> { //function(){} => {} this 바인딩 하기 위해서
            this.save();
        });

        $(".delete-btn").on("click", ()=> {
            this.deleteById();
        });

        $(".comment-btn").on("click", ()=> {
            this.commentSave();
        });
    },

    save:function(){
        let data = {
            boardCategory : $("#boardCategory").val(),
            boardTitle: $("#boardTitle").val(),
            boardContent: $("#boardContent").val()
        };

        $.ajax({
            type:'POST',
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

    deleteById:function(){
        var bid = $(".bid").val();
        $.ajax({
            type:'DELETE',
            url:'/api/board/'+bid,
            dataType: "json",
        }).done(function (resp){
            alert("삭제가 완료되었습니다.")
            window.location.replace("/");
        }).fail(function (error){
            alert("삭제에 실패하였습니다.");
        });

    },

    commentSave:function(){
        let data = {
            commentDef : $("#commentContent").val()
        };
        let bid = $("#bid").val();

        $.ajax({
            type:'POST',
            url:`/api/board/${bid}/comment`,
            data: JSON.stringify(data), //http body 데이터
            contentType: "application/json; charset=utf-8", //MIME *데이터가 어떤 타입인지
            dataType: "json", //요청을 서버로 해서 응답이 왔을 때 기본적으로 모든것이 문자열 (생긴게 json이면) => javascript 오브젝트로 변경
        }).done(function (resp){
            alert("댓글작성이 완료되었습니다.")
            window.location.replace(`/board/view/${bid}`);
        }).fail(function (error){
            alert("댓글작성에 실패하였습니다.");
        });
    },

    commentDelete:function(bid, cid){

        $.ajax({
            type:'DELETE',
            url:`/api/board/${bid}/comment/${cid}`,
            dataType: "json",
        }).done(function (resp){
            alert("댓글삭제 완료")
            window.location.replace(`/board/view/${bid}`);
        }).fail(function (error){
            alert("댓글삭제에 실패하였습니다.");
        });
    }
}
index.init();




