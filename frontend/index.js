document.addEventListener('DOMContentLoaded', () => {
    const radio1 = document.getElementById('radio-1');
    const radio2 = document.getElementById('radio-2');
    const panel1 = document.getElementById('panel-1');
    const panel2 = document.getElementById('panel-2');

    radio1.addEventListener('change', () => {
        panel1.classList.remove('-translate-x-[500px]');
        panel2.classList.add('translate-x-[500px]');
    });

    radio2.addEventListener('change', () => {
        panel1.classList.add('-translate-x-[500px]');
        panel2.classList.remove('translate-x-[500px]');
    });
});
