document.getElementById('openLinkButton2').addEventListener('click', function() {
    const element = document.getElementById('openLinkButton2');
    const clientId = element.getAttribute('data-id');

    const editUrl = `/buyer/task/${clientId}/editBuyer`;

    window.location.href = editUrl;

});