let domain = "";
let isNew = true;

function selectUrl() {
    return '/admin/' + domain + '/list';
}

function selectDomain() {
    clearData();
    domain = $('input[type="radio"][name="domain"]:checked').val();
    $('textarea[name="description"]').attr('maxlength', domain === 'place' ? 1024 : 255);
    $('.domain-input').css('display', 'none');
    $('.' + domain).css('display', 'unset');
    ajaxToSelectSet('moodlet');
}

function ajaxToSelectSet(domain) {
    $.ajax({
        url: '/admin/' + domain + '/list',
        type: 'GET',
        success: function (data) {
            let $selectSet = $('#' + domain + 's');

            $selectSet.empty();
            $selectSet.select2({
                data: data.results
            });
        }
    });
}

function clearData() {
    $('#save').val("Создать");
    $('#select-domain').val(null).trigger("change");
    isNew = true;
    let $form = $('#form');

    $form.find('input[type="hidden"], input[type="text"], textarea, input[type="file"]').val('');
    $form.find('input[type="number"]').val('0');
    $form.find('input[type="checkbox"]').removeAttr('checked');
    $form.find('img').attr('src', '#');
    $form.find('input[name="type"][value="OTHER"]').attr("checked", "checked");
    $('.select-set').val(null).trigger("change");
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
            $('input[name="type"]:checked').removeAttr("checked");
            $('input[name="type"][value="' + data.type + '"]').attr("checked", "checked");
            $('textarea[name="description"]').val(data.description);
            $('input[name="durability"]').val(data.durability);
            $('input[name="value"]').val(data.value);
            if (data.playable) {
                $('input[name="playable"]').attr("checked", "checked");
            } else {
                $('input[name="playable"]:checked').removeAttr("checked");
            }
            if (data.picturePath) {
                $('.preview').find('img').attr('src', '/img/' + data.picturePath);
            } else {
                $('.preview').find('img').attr('src', '#');
            }
            if (data.moodlets) {
                $('#moodlets').val(data.moodlets.map(function (data) {
                    return data.id.toString();
                })).trigger("change");
            }
        }
    });
}

let alertError = function (jqXHR) {
    alert("Error code: " + jqXHR.status + "\n" + jqXHR.responseText);
}

$(document).ready(function () {
    selectDomain();
    $('#create-button').click(clearData);
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
        sendForm(
            formData,
            '/admin/' + domain + (isNew ? '/save' : '/update?id=' + $('input[name="id"]').val()),
            clearData,
            alertError
        );
    });
    $('#select-domain').select2({
        ajax: {
            url: selectUrl,
            type: 'GET'
        }
    }).on('select2:select', function (event) {
        fillForm(event.params.data);
    });
})
