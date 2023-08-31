"use strick"

$(function(){

    let text = "";

    $(".checkProfanityInCommentBtn").on("click", function () {
        const index = $(".checkProfanityInCommentBtn").index(this);
        text = $(".comment-body").eq(index).val();
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
            $(".submitCommentBtn").eq(index).click();
        });
    });
});