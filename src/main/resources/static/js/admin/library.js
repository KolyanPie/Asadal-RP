let domain;

function selectDomain() {
    $.each($('input[name="domain"]'), function (i, item) {
        if (item.checked) {
            domain = item.value;
            $.each($('.domain-input'), function (i, element) {
                element.style.display = 'none';
            })
            $.each($('.' + domain), function (i, element) {
                element.style.display = 'unset';
            })
        }
    })
}

function clearData() {
    let $form = $('#form');

    $form.find('input[type="text"], textarea').val('');
    $form.find('input[type="number"]').val('0');
}

$(document).ready(function () {
    selectDomain();
})
