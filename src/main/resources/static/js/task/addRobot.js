function sendAndOpenEditForm4() {
    fetch('/robot/maxId')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const winterTaskId = document.getElementById("value").value;
            data = {id: data.id};
            fetch('/robot/taskRobot?taskId=' + winterTaskId, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    const entityId = data.id;
                    const editUrl = `/robot/task/${entityId}/editRobot`;
                    window.location.href = editUrl;
                })
                .catch(error => {
                    console.error('There was a problem creating the entity:', error);
                });
        })
        .catch(error => {
            console.error('There was a problem getting the max ID:', error);
        });
}

