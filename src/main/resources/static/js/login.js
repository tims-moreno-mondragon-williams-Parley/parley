function validateForm() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if (!username || !password) {
        alert("Both fields are required!");
        return false;
    }

    var hasUppercase = /[A-Z]/.test(password);
    var hasLowercase = /[a-z]/.test(password);
    var hasNumber = /\d/.test(password);

    if (!hasUppercase || !hasLowercase || !hasNumber) {
        alert("Password must contain at least one uppercase letter, one number, and one lowercase letter.");
        return false;
    }

    return true;
}