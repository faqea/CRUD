const redirectButton = document.getElementById("redirectButton");

redirectButton.addEventListener("click", function () {
   const buyerId = redirectButton.getAttribute('data-id');
    window.location.href = `/buyer/${buyerId}/editBuyer`;
});