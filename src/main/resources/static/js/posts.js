"use strict";

$(document).ready(function () {


    /*******************
     * Event Listeners *
     ******************/

    // event listeners for showing and hiding category creation form for admins
    // $("#show-create-category-form-button").on("click", function (event){
    //     event.preventDefault();
    //     $(this).toggleClass("hide-button")
    //     $("#hide-create-category-form-button").toggleClass("hide-button");
    //     $("#create-category-form-container").slideToggle("slow");
    // });
    //
    // $("#hide-create-category-form-button").on("click", function (event){
    //     event.preventDefault();
    //     $(this).toggleClass("hide-button")
    //     $("#show-create-category-form-button").toggleClass("hide-button");
    //     $("#create-category-form-container").slideToggle("slow");
    // });

    // event listeners for showing and hiding topic creation forms for admins
    // $(".show-create-topic-form-button").on("click", function(event){
    //     event.preventDefault();
    //     $(this).toggleClass("hide-button");
    //     const index = $(".show-create-topic-form-button").index(this);
    //     $(".hide-create-topic-form-button").eq(index).toggleClass("hide-button");
    //     $(".hide-topic-form").eq(index).slideToggle("slow");
    // });
    //
    // $(".hide-create-topic-form-button").on("click", function(event){
    //     event.preventDefault();
    //     $(this).toggleClass("hide-button");
    //     const index = $(".hide-create-topic-form-button").index(this);
    //     $(".show-create-topic-form-button").eq(index).toggleClass("hide-button");
    //     $(".hide-topic-form").eq(index).slideToggle("slow");
    // });

    // For the category form Mondragon
    $("#show-create-category-form-button").click(function() {
        $("#create-category-form-container").removeClass("hide-form");
    });
    $("#hide-create-category-form-button").click(function() {
        $("#create-category-form-container").addClass("hide-form");
    });

    // For the topic form
    $(".show-create-topic-form-button").click(function() {
        $(this).closest("li").find(".create-topic-form-container .topic-form-section").removeClass("hide-topic-form");
    });
    $(".hide-create-topic-form-button").click(function() {
        $(this).closest("li").find(".create-topic-form-container .topic-form-section").addClass("hide-topic-form");
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
    $(document).ready(function() {
        // When the "show-comment-form" button is clicked
        $('.show-comment-form').click(function() {
            $('.comment-form').show(); // Show the comment form
        });

        // When the "hide-comment-form" button is clicked
        $('.hide-comment-form').click(function() {
            $('.comment-form').hide(); // Hide the comment form
        });
    });


});