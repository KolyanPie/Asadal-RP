let domain;
let isNew = true;

function selectDomain() {
    clearData();
    domain = $('input[type="radio"][name="domain"]:checked').val();
    $('textarea[name="description"]').attr('maxlength', domain === 'place' ? 1024 : 255);
    $('#select-domain').select2({
        ajax: {
            url: '/admin/' + domain + '/list',
            type: 'GET'
        }
    }).on('select2:select', function (event) {
        fillForm(event.params.data);
    });
    $('.domain-input').css('display', 'none');
    $('.' + domain).css('display', 'unset');
}

function clearData() {
    $('#save').val("Создать");
    $('#select-domain').val(null).trigger("change");
    isNew = true;
    let $form = $('#form');

    $form.find('input[type="hidden"], input[type="text"], textarea').val('');
    $form.find('input[type="number"]').val('0');
}

let fillForm = function (data) {
    isNew = false;
    $('#save').val("Изменить");
    $.ajax({
        type: 'GET',
        url: '/admin/' + domain + '/get?' + 'id=' + data.id,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 800000,
        error: alertError,
        success: function (data) {
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
        $('.change').css('display', 'none');
        clearData();
    });
    $('#change-button').click(function () {
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
        let formData = new FormData($('#form')[0]);
        formData.append("moodlets", $('#moodlets').val());
        formData.append("actions", $('#actions').val());
        formData.append("persons", $('#persons').val());
        formData.append("items", $('#items').val());
        sendForm(
            formData,
            '/admin/' + domain + (isNew ? '/save' : '/update?id=' + $('input[name="id"]').val()),
            clearData,
            alertError
        );
    });
    $('.select-set').select2({
        ajax: {
            url: '/admin/' + 'moodlet' + '/list',
            type: 'GET'
        }
    });
})
