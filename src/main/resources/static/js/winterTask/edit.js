document.querySelectorAll('.user-row').forEach(row => {
    const checkbox = row.querySelector('input[type="checkbox"]');
    const emptyField = row.querySelector('input[type=""]');

    row.addEventListener('click', async (event) => {
        if (event.target !== checkbox && event.target !== emptyField) {
            const userId = row.dataset.id;
            const editUrl = `/winter/${userId}/editTask`;

            // Перенаправление на ссылку в том же окне
            window.location.href = editUrl;
        }
    });
});