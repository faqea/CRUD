document.getElementById('openLinkButton').addEventListener('click', function() {
    const element = document.getElementById('openLinkButton');
    const robotId = element.getAttribute('data-id');

    const editUrl = `/robot/winterTask/${robotId}/editRobot`;

    window.location.href = editUrl;
});
