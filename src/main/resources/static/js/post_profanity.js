"use strick"

$(function(){

    let text = "";

    $("#checkProfanityInPostBtn").on("click", function () {
        text = $("#body").val();
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
            $("#body").val(response.result);
            $("#submitPostBtn").click();
        });
    });
});