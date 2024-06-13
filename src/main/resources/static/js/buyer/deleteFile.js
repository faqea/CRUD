// Получаем ссылки на кнопки "Удалить файл"
const deleteFileButtons = document.querySelectorAll('.delete-file-button');

// Обрабатываем клик на кнопке "Удалить файл"
deleteFileButtons.forEach((button) => {
    button.addEventListener('click', (event) => {
        // Отменяем стандартное действие кнопки
        event.preventDefault();

        // Получаем идентификатор удаляемого файла из data-атрибута кнопки
        const fileId = button.dataset.id;

        const clientId = button.dataset.clientId;

        const fileName = button.dataset.fileName;

        // Формируем URL для удаления файла
        const deleteFileUrl = `/files/${fileId}/deleteFile?clientId=${clientId}&fileName=${fileName}`;

        // Отправляем запрос на удаление файла
        fetch(deleteFileUrl, {
            method: 'POST'
        }).then((response) => {
            // Если запрос выполнен успешно, удаляем элемент списка файлов
            if (response.ok) {
                window.location.href = `/buyer/${clientId}/editBuyer`;
            }
        }).catch((error) => {
            console.error(error);
        });
    });
});