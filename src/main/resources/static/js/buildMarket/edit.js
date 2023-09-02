document.querySelectorAll('.user-row').forEach(row => {
    const checkbox = row.querySelector('input[type="checkbox"]');

    row.addEventListener('click', (event) => {
        if (event.target !== checkbox) {
            const userId = row.dataset.id;
            const editUrl = `buildMarket/${userId}/editMarket`;

            // Перенаправление на ссылку в том же окне
            window.location.href = editUrl;
        }
    });
});