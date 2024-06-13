function sendAndOpenEditForm12() {

    const winterTaskId = document.getElementById("value").value;
    fetch('/robot/winterTaskRobot?winterId=' + winterTaskId, {
        method: 'POST',
    })
        .then(data => {
            const entityId = data.id;
            window.location.href = `/winter/${winterTaskId}/editTask`;
        })

}

