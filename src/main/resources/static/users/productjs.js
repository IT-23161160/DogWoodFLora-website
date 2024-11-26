document.querySelectorAll('.delivery-radio').forEach((radio) => {
    radio.addEventListener('change', function () {
      const selectedOption = document.querySelector('.delivery-radio:checked').id;
      console.log('Selected delivery option:', selectedOption);
      // Example: Update the UI
      document.querySelector('.delivery span').textContent = `Delivery: ${selectedOption}`;
    });
  });
// Function to update the price and the border style based on selected size
function updatePriceAndBorder(sizeElement, size) {
    var priceElement = document.querySelector('.new-price');
    var newPrice = 0;
  
    // Remove the 'selected' class from all size options to reset the border
    var allSizeOptions = document.querySelectorAll('.option-size');
    allSizeOptions.forEach(function(option) {
      option.classList.remove('selected');
    });
  
    // Add 'selected' class to the clicked size to highlight it with a green border
    sizeElement.classList.add('selected');
  
    // Price calculation based on size selected
    switch(size) {
      case '9 unt.':
        newPrice = 9000;  // Price for 9 units
        break;
      case '15 unt.':
        newPrice = 15000;  // Price for 15 units
        break;
      case '21 unt.':
        newPrice = 21000;  // Price for 21 units
        break;
      case '32 unt.':
        newPrice = 32000;  // Price for 32 units
        break;
    }
  
    // Update the price on the page
    priceElement.textContent = 'Rs.' + newPrice;
  }
  
  // Event listener to handle clicks on size options
  window.onload = function() {
    var sizeOptions = document.querySelectorAll('.option-size');
  
    sizeOptions.forEach(function(option) {
      option.addEventListener('click', function() {
        // Call updatePriceAndBorder function when a size is clicked
        var selectedSize = option.textContent.trim();
        updatePriceAndBorder(option, selectedSize);
      });
    });
  };
// Listen for clicks on heart icons
document.querySelectorAll('.heart-icon').forEach(function(heart) {
    heart.addEventListener('click', function(event) {
      event.preventDefault(); // Prevent the default anchor behavior
      
      // Toggle between unfilled (fa-heart-o) and filled (fa-heart) hearts
      const icon = this.querySelector('i');
      
      if (icon.classList.contains('fa-heart-o')) {
        // Change to filled heart (red)
        icon.classList.remove('fa-heart-o');
        icon.classList.add('fa-heart');
        console.log('Added to favorites');
      } else {
        // Change to unfilled heart (outline)
        icon.classList.remove('fa-heart');
        icon.classList.add('fa-heart-o');
        console.log('Removed from favorites');
      }
    });
  });
  
  