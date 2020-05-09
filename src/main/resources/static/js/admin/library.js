let domain;
let isNew = true;

function selectDomain() {
    domain = $('input[type="radio"][name="domain"]:checked').val();
    $('#change').select2({
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
    $('#save').click(function () {
        sendForm(
            $('#form')[0],
            '/admin/' + domain + '/save',
            clearData,
            function (jqXHR) {
                alert("Error code: " + jqXHR.status + "\n" + jqXHR.responseText);
            }
        );
    });
})
