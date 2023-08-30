"use strick"

$(function(){

    let text = "";
    console.log("Hello")

    $(".checkProfanityInCommentBtn").on("click", function () {
        const index = $(".checkProfanityInCommentBtn").index(this);
        console.log("index = " + index);
        text = $(".comment-body").eq(index).val();
        console.log("comment = " + text);
        const url = "https://" + XRapidHost + "/json?text=" + text;
        const settings = {
            async: true,
            crossDomain: true,
            url: url,
            method: 'GET',
            headers: {
                'X-RapidAPI-Key': XRapidKey,
                'X-RapidAPI-Host': XRapidHost
            }
        };

        $.ajax(settings).done(function (response) {
            $(".comment-body").eq(index).val(response.result);
            console.log("resp = " + response.result);
            console.log("comment body = " + $(".comment-body").eq(index).val());
            $(".submitCommentBtn").eq(index).click();
        });
    });
});