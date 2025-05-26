document.addEventListener('DOMContentLoaded', function () {
    // image preview
    const imageInput = document.getElementById('image');
    const previewImg = document.getElementById('preview');

    if (imageInput && previewImg) {
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
    }

    // character countdown
    const textarea = document.getElementById('content');
    const countDisplay = document.getElementById('count');
    const maxLength = 200;

    if (textarea && countDisplay) {
        textarea.addEventListener('input', () => {
            const remaining = maxLength - textarea.value.length;
            countDisplay.textContent = `残り${remaining}文字`;
        });
    }

    // エラーメッセージ
    const logoutMsg = document.getElementById("logoutMsg");
    if (logoutMsg) {
        setTimeout(() => {
            logoutMsg.style.transition = "opacity 1s";
            logoutMsg.style.opacity = "0";
            setTimeout(() => logoutMsg.remove(), 1000);
        }, 3000);
    }
});

// comment area
function showExtraFields() {
    const extraFields = document.getElementById("extraFields");
    const commentInput = document.getElementById("commentInput");

    if (!extraFields.classList.contains("show")) {
        extraFields.classList.add("show");
        commentInput.classList.add("expanded");
    }
}

function hideExtraFields() {
    const extraFields = document.getElementById("extraFields");
    const commentInput = document.getElementById("commentInput");

    if (extraFields.classList.contains("show")) {
        extraFields.classList.remove("show");
        commentInput.classList.remove("expanded");
    }
}
