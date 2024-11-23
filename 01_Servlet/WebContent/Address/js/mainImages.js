document.addEventListener('DOMContentLoaded', function () {
    const carousel = document.querySelector('.carousel');
    const prevButton = document.querySelector('.prev-button');
    const nextButton = document.querySelector('.next-button');

    let currentIndex = 0;

    function showSlide(index) {
        const slideWidth = carousel.querySelector('.cell').offsetWidth;
        carousel.style.transform = `translateX(-${slideWidth * index}px)`;
        currentIndex = index;
    }

    prevButton.addEventListener('click', function () {
        currentIndex = (currentIndex === 0) ? carousel.childElementCount - 1 : currentIndex - 1;
        showSlide(currentIndex);
    });

    nextButton.addEventListener('click', function () {
        currentIndex = (currentIndex === carousel.childElementCount - 1) ? 0 : currentIndex + 1;
        showSlide(currentIndex);
    });
});