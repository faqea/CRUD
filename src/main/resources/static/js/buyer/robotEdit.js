// Получаем ссылку на элемент списка роботов
const robotList = document.getElementById('list');

// Обрабатываем клик на ссылке
robotList.addEventListener('click', (event) => {
    // Проверяем, что клик был сделан на ссылке для редактирования робота
    if (event.target.classList.contains('edit-robot-link')) {
        // Отменяем стандартное действие ссылки
        event.preventDefault();

        // Получаем идентификатор редактируемого робота из data-атрибута
        const robotId = event.target.dataset.id;

        // Формируем URL для редактирования робота
        const editRobotUrl = `/robot/${robotId}/editRobot`;

        // Открываем модальное окно
        openModal(editRobotUrl);
    }
});

// Функция для открытия модального окна
function openModal(editRobotUrl) {
    // Получаем ссылку на iframe в модальном окне
    const editRobotIframe = document.getElementById('edit-robot-iframe');

    // Устанавливаем URL формы редактирования в iframe
    editRobotIframe.setAttribute('src', editRobotUrl);

    // Открываем модальное окно
    const editRobotModal = document.getElementById('myModal4');
    editRobotModal.style.display = "block";
}

// Функция для закрытия модального окна
function closeModal() {
    // Получаем ссылку на iframe в модальном окне
    const editRobotIframe = document.getElementById('edit-robot-iframe');

    // Очищаем URL формы редактирования в iframe
    editRobotIframe.setAttribute('src', '');

    // Закрываем модальное окно
    const editRobotModal = document.getElementById('myModal4');
    editRobotModal.style.display = "none";
}

// Обрабатываем клик на крестике закрытия модального окна
const closeButton = document.querySelector('.close');
closeButton.addEventListener('click', () => {
    closeModal();
});

// Закрываем модальное окно при клике на область вне модального окна
window.addEventListener('click', (event) => {
    if (event.target == document.getElementById('myModal4')) {
        closeModal();
    }
});