// Получаем ссылки на кнопки "Удалить файл"
const deleteCommentButtons = document.querySelectorAll('.delete-comment-button');

// Обрабатываем клик на кнопке "Удалить файл"
deleteCommentButtons.forEach((button) => {
    button.addEventListener('click', (event) => {
        // Отменяем стандартное действие кнопки
        event.preventDefault();

        // Получаем идентификатор удаляемого файла из data-атрибута кнопки
        const commentId = button.dataset.id;

        const clientId = button.dataset.clientId;

        // Формируем URL для удаления файла
        const deleteFileUrl = `/comment/task/${commentId}/deleteComment?clientId=${clientId}`;

        // Отправляем запрос на удаление файла
        fetch(deleteFileUrl, {
            method: 'POST'
        }).then((response) => {
            // Если запрос выполнен успешно, удаляем элемент списка файлов
            if (response.ok) {
                window.location.href = `/task/${clientId}/editTask`;
            }
        }).catch((error) => {
            console.error(error);
        });
    });
});