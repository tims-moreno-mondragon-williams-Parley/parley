document.getElementById('shareOpinionBtn').addEventListener('click', function() {
    var form = document.querySelector('.post-creation-form');
    var button = document.getElementById('shareOpinionBtn');

    if (form.style.display === "none" || form.style.display === "") {
        form.style.display = "block";
        button.style.display = "none";  // Hide the button when the form is shown
    } else {
        form.style.display = "none";
    }
});

document.getElementById('cancelPostBtn').addEventListener('click', function() {
    var form = document.querySelector('.post-creation-form');
    var button = document.getElementById('shareOpinionBtn');

    form.style.display = "none";  // Hide the form when the cancel button is clicked
    button.style.display = "block";  // Show the "Share your opinion" button again
});

// Assuming your submit button has an id of "submitPostBtn"
document.getElementById('submitPostBtn').addEventListener('click', function() {
    var form = document.querySelector('.post-creation-form');
    var button = document.getElementById('shareOpinionBtn');

    form.style.display = "none";  // Hide the form when the post is submitted
    button.style.display = "block";  // Show the button again
});

document.addEventListener('DOMContentLoaded', function() {
    // Get elements
    const viewCommentsBtn = document.querySelector('.show-comment-form');
    const commentBtn = document.querySelector('.comment-btn');
    const cancelBtn = document.querySelector('.hide-comment-form');
    const commentForm = document.querySelector('.comment-form-section');
    const commentsSection = document.querySelector('.comment-cards-section');

    viewCommentsBtn.addEventListener('click', function() {
        if (commentsSection.style.display === "none" || commentsSection.style.display === "") {
            commentsSection.style.display = "block";
            viewCommentsBtn.style.display = 'none';
            commentBtn.style.display = 'block';
        } else {
            commentsSection.style.display = "none";
        }
    });

    // Show comment form when comment button is clicked
    commentBtn.addEventListener('click', function() {
        commentForm.style.display = 'block';
        commentBtn.style.display = 'none';
    });

    // Hide comment form when cancel is clicked and show comment button
    cancelBtn.addEventListener('click', function() {
        commentForm.style.display = 'none';
        commentBtn.style.display = 'block';
    });
});
document.querySelector('.like-post-sect .like-dislike').addEventListener('click', function(e) {
    e.preventDefault(); // prevent form submission for demonstration
    this.classList.add('selected'); // Add selected class to the clicked button
    document.querySelector('.dislike-post-sect .like-dislike').classList.remove('selected'); // Remove selected class from the other button
});

document.querySelector('.dislike-post-sect .like-dislike').addEventListener('click', function(e) {
    e.preventDefault(); // prevent form submission for demonstration
    this.classList.add('selected'); // Add selected class to the clicked button
    document.querySelector('.like-post-sect .like-dislike').classList.remove('selected'); // Remove selected class from the other button
});




