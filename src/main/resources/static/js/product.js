function placeOrder(button) {
    // Get the product ID from the button's data attribute
    const productId = button.getAttribute('data-product-id');

    // Get CSRF token if enabled (update this if you have CSRF enabled)
    const csrfToken = document.querySelector('meta[name="_csrf"]')?.getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]')?.getAttribute('content');

    // Send the POST request to the backend
    fetch('/placeOrder', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            ...(csrfToken && { [csrfHeader]: csrfToken }) // Include CSRF token if available
        },
        body: JSON.stringify({ productId: productId })
    })
        .then(response => {
            if (response.ok) {
                // Redirect to confirmation page or show a success message
                window.location.href = '/confirmation';
            } else {
                // Handle errors
                console.error('Failed to place order');
                alert('Failed to place order. Please try again.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An unexpected error occurred.');
        });
}

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