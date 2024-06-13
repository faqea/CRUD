function sendAndOpenEditForm() {
    // Отправляем POST-запрос на адрес для создания сущности
    fetch('/producer', {
        method: 'POST',
    }).then(data => {
            // Открываем форму редактирования для созданной сущности
            window.location.href = `/producer`;
        })


}

