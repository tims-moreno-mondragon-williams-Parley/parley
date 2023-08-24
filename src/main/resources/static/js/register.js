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
    const form = document.querySelector("form");
    const passwordInput = document.getElementById("password");
    const confirmPasswordInput = document.getElementById("confirmPassword");
    const passwordMatchError = document.querySelector(".error-message");
    const passwordQualificationsError = document.querySelector(".error-invalidquals");

    form.addEventListener("submit", function (event) {
        const password = passwordInput.value;
        const confirmPassword = confirmPasswordInput.value;

        if (!doPasswordsMatch(password, confirmPassword)) {
            passwordMatchError.textContent = "Passwords do not match.";
            event.preventDefault(); // prevent form submission
        } else {
            passwordMatchError.textContent = "";
        }

        if (!validatePassword(password)) {
            passwordQualificationsError.textContent = "Password does not meet the qualifications stated below.";
            event.preventDefault(); // prevent form submission
        } else {
            passwordQualificationsError.textContent = "";
        }
    });
});

validatePassword();
doPasswordsMatch();
