$(function () {
    function typeEffect(element, speed) {
        let text = $(element).text();
        $(element).html('');
        let i = 0;

        function typing() {
            if (i < text.length) {
                $(element).append(text.charAt(i));
                i++;
                setTimeout(typing, speed);
            }
        }

        typing();
    }

    $('.container, .additional-content').find('h2, h3, li, p').each(function () {
        typeEffect(this, 75);
    });
});