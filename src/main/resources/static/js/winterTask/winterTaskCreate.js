function sendAndOpenEditForm55() {

    // Отправляем POST-запрос на адрес для создания сущности
    fetch('/winter', {
        method: 'POST',
    }).then(data => {
        const entityId = data.id;
        window.location.href = `/winter`;
    })

}
