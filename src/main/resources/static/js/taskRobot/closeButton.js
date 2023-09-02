const redirectButton = document.getElementById("redirectButton");

redirectButton.addEventListener("click", function () {
   const taskId = redirectButton.getAttribute('data-id');
    window.location.href = `/task/${taskId}/editTask`;
});