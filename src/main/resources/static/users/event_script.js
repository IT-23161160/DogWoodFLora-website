document.addEventListener("DOMContentLoaded", function () {
    const radioButtons = document.querySelectorAll('input[name="Photos"]');
    const galleries = document.querySelectorAll('.photo-gallery .pic');

    // Show all images by default
    galleries.forEach(gallery => {
        gallery.style.display = 'block';
    });

    radioButtons.forEach(radio => {
        radio.addEventListener('change', function () {
            const filter = this.id; // Get the ID of the checked radio button

            galleries.forEach(gallery => {
                // Get the alt attribute of the image for filtering
                const imgAlt = gallery.querySelector('img').alt.toLowerCase();

                // Show/hide gallery items based on the selected radio button
                if (filter === 'check1') {
                    gallery.style.display = 'block'; // Show all
                } else if (filter === 'check2' && imgAlt.includes('weddings')) {
                    gallery.style.display = 'block'; // Show weddings
                } else if (filter === 'check3' && imgAlt.includes('special event')) {
                    gallery.style.display = 'block'; // Show special events
                } else if (filter === 'check4' && imgAlt.includes('parties')) {
                    gallery.style.display = 'block'; // Show parties
                } else if (filter === 'check5' && imgAlt.includes('decorations')) {
                    gallery.style.display = 'block'; // Show decorations
                } else if (filter === 'check6' && imgAlt.includes('buddhist occasions')) {
                    gallery.style.display = 'block'; // Show Buddhist Occasions
                } else if (filter === 'check7' && imgAlt.includes('bridal')) {
                    gallery.style.display = 'block'; // Show bridal
                } else if (filter === 'check8' && imgAlt.includes('funerals')) {
                    gallery.style.display = 'block'; // Show funerals
                } else {
                    gallery.style.display = 'none'; // Hide non-matched category
                }
            });
        });
    });
});
