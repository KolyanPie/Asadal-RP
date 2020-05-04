let xhr = new XMLHttpRequest();

document.addEventListener("DOMContentLoaded", function () {
    selectDomain();
})

function selectDomain() {
    document.getElementsByName('domain').forEach(
        function (item) {
            if (item.checked) {
                let elements = document.getElementsByClassName("domain-input");
                for (let i = 0; i < elements.length; i++) {
                    elements[i].style.display = 'none';
                }
                elements = document.getElementsByClassName(item.value);
                for (let i = 0; i < elements.length; i++) {
                    elements[i].style.display = 'unset';
                }
            }
        }
    )
}
