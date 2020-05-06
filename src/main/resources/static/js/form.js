function sendForm(form, url, success, error) {
    let form_data = new FormData(form);
    $.ajax({
        type: 'POST',
        enctype: 'multipart/form-data',
        url: url,
        data: form_data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 800000,
        success: success,
        error: error
    });
}