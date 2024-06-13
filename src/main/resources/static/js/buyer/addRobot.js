function sendAndOpenEditForm5() {

    const personId = document.getElementById("value").value;
    fetch('/robot?personId=' + personId, {
        method: 'POST',
    }).then(data => {
            window.location.href = `/buyer/${personId}/editBuyer`;
        })

}

