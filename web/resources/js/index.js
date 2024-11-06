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

    function rainbowText(element) {
        let colors = ['#ff0000', '#ff7f00', '#ffff00', '#00ff00', '#0000ff',];
        let colorIndex = Math.floor(Math.random() * colors.length);
        setInterval(function () {
            $(element).css('color', colors[colorIndex]);
            colorIndex = (colorIndex + 1) % colors.length;
        }, 500);
    }

    $('.container, .additional-content').find('h2, h3, li, p').each(function () {
        typeEffect(this, 75);
        rainbowText(this);
    });
});