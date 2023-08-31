"use strict"

$(function(){

    let text = "";

    $("#update-profile-profanity-check").on("click", function () {
        text = $("#bio").val();
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
            $("#bio").val(response.result);
            $("#update-profile-button").click();
        });
    });
});