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
    $.ajax({
        type: 'GET',
        url: '/admin/' + domain + '/get?' + 'id=' + data.id,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 800000,
        error: alertError,
        success: function (data) {
            console.log(data);
            $('input[name="id"]').val(data.id);
            $('input[name="name"]').val(data.name);
            $('textarea[name="adminHint"]').val(data.adminHint);
            $('input[name="characterType"]').val(data.characterType);
            $('textarea[name="description"]').val(data.description);
            $('input[name="durability"]').val(data.durability);
            $('input[name="value"]').val(data.value);
            $('input[name="playable"]').val(data.playable);
            $('input[name="picture"]').val(data.picture);
        }
    });
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
            error: alertError,
            success: clearData
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
