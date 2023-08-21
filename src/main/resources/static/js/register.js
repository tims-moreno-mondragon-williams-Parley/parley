function validatePassword(password) {
    const minLength = 8;
    const maxLength = 20;
    const hasUppercase = /[A-Z]/.test(password);
    const hasLowercase = /[a-z]/.test(password);
    const hasDigits = /\d/.test(password);

    return(
        password.length >= minLength &&
            password.length <= maxLength &&
            hasUppercase &&
            hasLowercase &&
            hasDigits
    );

}
function doPasswordsMatch(password, confirmPassword) {
    return password === confirmPassword;
}
document.addEventListener("DOMContentLoaded", function () {
    const passwordInput = document.getElementById("password");
    const confirmPasswordInput = document.getElementById("confirmPassword");
    const passwordMatch = document.getElementById("passwordMatch");

    confirmPasswordInput.addEventListener("input", function () {
        const password = passwordInput.valueOf();
        const confirmPassword = confirmPasswordInput.valueOf();

        if(passwordMatch(password, confirmPassword)){
            passwordMatch.textContent = "";
        } else {
            passwordMatch.textContent = "Passwords do not Match";
        }
    })
})

validatePassword();
doPasswordsMatch();
