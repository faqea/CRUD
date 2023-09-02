function saveCheckboxValue(checkboxId) {
    const checkbox = document.getElementById('checkbox' + checkboxId);
    localStorage.setItem('checkboxValue' + checkboxId, checkbox.checked);
}

window.onload = function () {
    // Восстановление значений чекбоксов при загрузке страницы
    for (let i = 42; i <= 68; i++) {
        const checkbox = document.getElementById('checkbox' + i);
        const savedValue = localStorage.getItem('checkboxValue' + i);
        if (savedValue === 'true') {
            checkbox.checked = true;
        } else {
            checkbox.checked = false;
        }
    }
};