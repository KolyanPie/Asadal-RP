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

$(document).ready(function () {
    selectDomain();
})
