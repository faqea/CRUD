const redirectButton = document.getElementById("redirectButton");

redirectButton.addEventListener("click", function () {
    const winterTaskId = redirectButton.getAttribute('data-id');
    window.location.href = `/winter/${winterTaskId}/editTask`;
});