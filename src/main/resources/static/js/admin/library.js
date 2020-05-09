let domain;
let isNew = true;

function selectDomain() {
    domain = $('input[type="radio"][name="domain"]:checked').val();
    $('#select-domain').select2({
        templateSelection: fillForm,
        ajax: {
            url: '/admin/' + domain + '/list',
            type: 'GET'
        }
    });
    $('.domain-input').css('display', 'none');
    $('.' + domain).css('display', 'unset');
}

function clearData() {
    let $form = $('#form');

    $form.find('input[type="text"], textarea').val('');
    $form.find('input[type="number"]').val('0');
}

let fillForm = function (data) {
    console.log(data);
}

let alertError = function (jqXHR) {
    alert("Error code: " + jqXHR.status + "\n" + jqXHR.responseText);
}

$(document).ready(function () {
    selectDomain();
    $('#create-button').click(function () {
        $('#save').val("Создать");
        isNew = true;
        $('.change').css('display', 'none');
        clearData();
    });
    $('#change-button').click(function () {
        $('#save').val("Изменить");
        isNew = false;
        $('.change').css('display', 'unset');
        clearData();
    });
    $('#delete-button').click(function () {
        $.ajax({
            type: 'DELETE',
            url: '/admin/' + domain + '/remove?' + 'id=' + $('#select-domain').val(),
            processData: false,
            contentType: false,
            cache: false,
            timeout: 800000,
            error: alertError
        });
    });
    $('#save').click(function () {
        sendForm(
            $('#form')[0],
            '/admin/' + domain + '/save',
            clearData,
            alertError
        );
    });
})
