// Отправка значения чекбокса и идентификатора комментария при клике на чекбокс
var checkboxes = document.querySelectorAll('.comment-checkbox');
checkboxes.forEach(function (checkbox) {
    checkbox.addEventListener('change', function () {
        var commentId = this.getAttribute('data-id');
        var isChecked = this.checked;

        // Создание объекта с данными для отправки на сервер
        var postData = {
            commentId: commentId,
            isChecked: isChecked
        };

        // Отправка POST-запроса на сервер
        fetch('/comment/check?id=' + commentId + '&isChecked=' + isChecked, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postData)
        })

    });

    // Восстановление состояния чекбоксов при загрузке страницы
    var commentId = checkbox.getAttribute('data-id');
    var savedValue = localStorage.getItem('comment_' + commentId);
    if (savedValue !== null) {
        checkbox.checked = savedValue === 'true';
    }
});