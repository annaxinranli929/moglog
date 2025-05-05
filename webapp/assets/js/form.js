document.addEventListener('DOMContentLoaded', function () {
    // image preview
    const imageInput = document.getElementById('image');
    const previewImg = document.getElementById('preview');

    imageInput.addEventListener('change', function () {
        const file = this.files[0];

        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewImg.src = e.target.result;
                previewImg.style.display = 'block';
            }
            reader.readAsDataURL(file);
        } else {
            previewImg.style.display = 'none';
        }
    });

    // character countdowm
    const textarea = document.getElementById('content');
    const countDisplay = document.getElementById('count');
    const maxLength = 200;

    textarea.addEventListener('input', () => {
        const remaining = maxLength - textarea.value.length;
        countDisplay.textContent = `残り${remaining}文字`;
    });
});