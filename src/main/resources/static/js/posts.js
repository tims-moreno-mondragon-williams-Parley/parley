"use strict";

$(document).ready(function () {


    /*******************
     * Event Listeners *
     ******************/

    // event listeners for showing and hiding category creation form for admins
    $("#show-create-category-form-button").on("click", function (event){
        event.preventDefault();
        $(this).toggleClass("hide-button")
        $("#hide-create-category-form-button").toggleClass("hide-button");
        $("#create-category-form-container").slideToggle("slow");
    });

    $("#hide-create-category-form-button").on("click", function (event){
        event.preventDefault();
        $(this).toggleClass("hide-button")
        $("#show-create-category-form-button").toggleClass("hide-button");
        $("#create-category-form-container").slideToggle("slow");
    });

    // event listeners for showing and hiding topic creation forms for admins
    $(".show-create-topic-form-button").on("click", function(event){
        event.preventDefault();
        $(this).toggleClass("hide-button");
        const index = $(".show-create-topic-form-button").index(this);
        $(".hide-create-topic-form-button").eq(index).toggleClass("hide-button");
        $(".hide-topic-form").eq(index).slideToggle("slow");
    });

    $(".hide-create-topic-form-button").on("click", function(event){
        event.preventDefault();
        $(this).toggleClass("hide-button");
        const index = $(".hide-create-topic-form-button").index(this);
        $(".show-create-topic-form-button").eq(index).toggleClass("hide-button");
        $(".hide-topic-form").eq(index).slideToggle("slow");
    });

    // event listeners for showing and hiding post creation form
    $("#show-create-post-form-button").on("click", function (event){
        event.preventDefault();
        $(this).toggleClass("hide-button")
        $("#hide-create-post-form-button").toggleClass("hide-button");
        $("#post-form").slideToggle("slow");
    });

    $("#hide-create-post-form-button").on("click", function (event){
        event.preventDefault();
        $(this).toggleClass("hide-button")
        $("#show-create-post-form-button").toggleClass("hide-button");
        $("#post-form").slideToggle("slow");
    });

    // event listeners for showing and hiding comment creation form
    $(".show-comment-form").on("click", function (event){
       event.preventDefault();
       $(this).toggleClass("hide-button");
       const index = $(".show-comment-form").index(this);
       $(".hide-comment-form").eq(index).toggleClass("hide-button");
       $(".comment-form-section").eq(index).slideToggle("slow");
    });

    $(".hide-comment-form").on("click", function (event){
       event.preventDefault();
       $(this).toggleClass("hide-button");
       const index = $(".hide-comment-form").index(this);
       $(".show-comment-form").eq(index).toggleClass("hide-button");
       $(".comment-form-section").eq(index).slideToggle("slow");
    });

});