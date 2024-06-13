function sendAndOpenEditForm() {

    // Отправляем POST-запрос на адрес для создания сущности
    fetch('/buyer', {
        method: 'POST',
    })
        .then(data => {
            const entityId = data.id;
            const editUrl = `/buyer`;
            window.location.href = editUrl;
        })
}
