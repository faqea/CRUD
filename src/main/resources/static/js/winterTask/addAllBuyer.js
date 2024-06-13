function sendAndOpenEditForm() {
    const winterTaskId = document.getElementById("value").value;
    fetch('/buyer/winterBuyer?winterTaskId=' + winterTaskId, {
        method: 'POST',
    })
        .then(data => {
            const entityId = data.id;
            window.location.href = `/winter/${winterTaskId}/editTask`;
        })

}
