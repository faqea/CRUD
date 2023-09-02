function sendAndOpenEditForm3() {
    fetch('/buyer/maxId')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const taskId = document.getElementById("value").value;
            data = {id: data.id};
            fetch('/buyer/taskBuyer?taskId=' + taskId, {
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
                    window.location.href = `/buyer/task/${entityId}/editBuyer`;
                })
                .catch(error => {
                    console.error('There was a problem creating the entity:', error);
                });
        })
        .catch(error => {
            console.error('There was a problem getting the max ID:', error);
        });
}

