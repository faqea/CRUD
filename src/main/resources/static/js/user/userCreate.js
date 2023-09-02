function sendAndOpenEditForm() {
    // Получаем максимальный ID из базы данных
    fetch('user/maxId')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Создаем новый объект данных с ID, равным максимальному ID плюс один
            data = {id: data.id};
            // Отправляем POST-запрос на адрес для создания сущности
            fetch('/user', {
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
                    // Получаем идентификатор созданной сущности из тела ответа
                    return response.json();
                })
                .then(data => {
                    const entityId = data.id;
                    // Открываем форму редактирования для созданной сущности
                    window.location.href = `user/${entityId}/edit`;
                })
                .catch(error => {
                    console.error('There was a problem creating the entity:', error);
                });
        })
        .catch(error => {
            console.error('There was a problem getting the max ID:', error);
        });
}
