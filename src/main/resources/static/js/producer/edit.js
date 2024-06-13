document.querySelectorAll('.user-row').forEach(row => {
    const checkbox = row.querySelector('input[type="checkbox"]');
    const emptyField = row.querySelector('input[type=""]');

    row.addEventListener('click', (event) => {
        if (event.target !== checkbox) {
            if (event.target !== emptyField) {
                const userId = row.dataset.id;
                const editUrl = `producer/${userId}/editProducer`;
                window.location.href = editUrl;
            }
        }
    });
});