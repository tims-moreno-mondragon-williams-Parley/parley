document.getElementById('shareOpinionBtn').addEventListener('click', function() {
    var form = document.querySelector('.post-creation-form');
    if (form.style.display === "none" || form.style.display === "") {
        form.style.display = "block";
    } else {
        form.style.display = "none";
    }
});
