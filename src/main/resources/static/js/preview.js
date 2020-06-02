$(document).ready(function () {
    $('.preview').children('input[type="file"]').change(function () {
        const reader = new FileReader();
        reader.onload = function (e) {
            $('.preview').find('img').attr('src', e.target.result);
        }
        reader.readAsDataURL(this.files[0]);
    });
});
