let domain;
let isNew = true;

function selectDomain() {
    domain = $('input[type="radio"][name="domain"]:checked').val();
    $('.domain-input').css('display', 'none');
    $('.' + domain).css('display', 'unset');
}

function clearData() {
    let $form = $('#form');

    $form.find('input[type="text"], textarea').val('');
    $form.find('input[type="number"]').val('0');
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
            '/admin/save/' + domain,
            clearData,
            function (jqXHR) {
                alert("Error code: " + jqXHR.status + "\n" + jqXHR.responseText);
            }
        );
    });
})
