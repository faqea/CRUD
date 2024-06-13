// Получаем ссылки на кнопки "Удалить робота"
const deleteRobotButtons = document.querySelectorAll('.delete-robot-button');


// Обрабатываем клик на кнопке "Удалить робота"
deleteRobotButtons.forEach((button) => {
    button.addEventListener('click', (event) => {
        // Отменяем стандартное действие кнопки
        event.preventDefault();

        // Получаем идентификатор удаляемого робота из data-атрибута кнопки
        const robotId = button.dataset.id;

        // Получаем идентификатор клиента из data-атрибута кнопки
        const clientId = button.dataset.clientId;

        // Формируем URL для удаления робота с добавлением идентификатора клиента в параметры запроса
        const deleteRobotUrl = `/robot/${robotId}/deleteRobot?clientId=${clientId}`;

        // Отправляем запрос на удаление робота
        fetch(deleteRobotUrl, {
            method: 'POST'
        }).then((response) => {
            // Если запрос выполнен успешно, удаляем элемент списка роботов
            if (response.ok) {
                window.location.href = `/buyer/${clientId}/editBuyer`;
            }
        }).catch((error) => {
            console.error(error);
        });
    });
});