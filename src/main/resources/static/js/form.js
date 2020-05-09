function sendForm(formData, url, success, error) {
    $.ajax({
        type: 'POST',
        enctype: 'multipart/form-data',
        url: url,
        data: formData,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 800000,
        success: success,
        error: error
    });
}