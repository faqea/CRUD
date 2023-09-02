function sendAndOpenEditForm2() {
    fetch('/winter/maxId')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const createdEntityId = data.id;
            fetch('/winter', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({id: createdEntityId})
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    const robotId = document.getElementById("open-button").getAttribute("data-id");
                    fetch(`/winter/${createdEntityId}/editTask?id=${robotId}`, {
                        method: 'GET'
                    })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                            console.log('GET request successful');
                            // Редирект на страницу редактирования робота
                            window.location.href = `/robot/task/${robotId}/editRobot`;
                        })
                        .catch(error => {
                            console.error('There was a problem with the GET request:', error);
                        });
                })
                .catch(error => {
                    console.error('There was a problem creating the entity:', error);
                });
        })
        .catch(error => {
            console.error('There was a problem getting the max ID:', error);
        });
}