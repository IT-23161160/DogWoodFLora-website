
document.addEventListener("DOMContentLoaded", function () {
    const radioButtons = document.querySelectorAll('input[name="Photos"]');
    const galleries = document.querySelectorAll('.product-samples-section .product');

    // Show all images by default
    galleries.forEach(gallery => {
        gallery.style.display = 'block';
    });

    radioButtons.forEach(radio => {
        radio.addEventListener('change', function () {
            const filter = this.id;

            galleries.forEach(gallery => {
                const imgAlt = gallery.querySelector('img').alt.toLowerCase();

                if (filter === 'check1') {
                    gallery.style.display = 'block'; // Show all
                } else if (filter === 'check2' && imgAlt.includes('bytype')) {
                    gallery.style.display = 'block'; // Show Flower By Type
                } else if (filter === 'check3' && imgAlt.includes('flowerb')) {
                    gallery.style.display = 'block'; // Show Flower Bouquets
                } else if (filter === 'check4' && imgAlt.includes('parties')) {
                    gallery.style.display = 'block'; // Show Flower Baskets
                } else if (filter === 'check5' && imgAlt.includes('vases')) {
                    gallery.style.display = 'block'; // Show Flower Vase
                } else {
                    gallery.style.display = 'none'; // Hide non-matched
                }
            });
        });
    });
});