const apiBaseUrl = 'http://localhost:8081'; // Base URL for API requests

// Elements
const registerForm = document.getElementById('registerForm'); // Registration form element
const loginForm = document.getElementById('loginForm'); // Login form element
const menuContainer = document.getElementById('menu-container'); // Menu container element
const menuTableBody = document.querySelector('#menuTable tbody'); // Table body for menu items
const submitOrderButton = document.getElementById('submitOrder'); // Submit order button element
const orderSummaryContainer = document.getElementById('order-summary-container'); // Order summary container element
const orderSummaryTableBody = document.querySelector('#orderSummaryTable tbody'); // Table body for order summary
const totalAmountContainer = document.getElementById('totalAmountContainer'); // Container for total order amount
const confirmOrderButton = document.getElementById('confirmOrder'); // Confirm order button element

// Global variables
let currentUser = null; // Current user object
let currentOrder = []; // Array to store current order items

// Event listeners for form submissions and button clicks
if (registerForm) {
    registerForm.addEventListener('submit', handleRegister); // Register form submission
}

if (loginForm) {
    loginForm.addEventListener('submit', handleLogin); // Login form submission
}

if (submitOrderButton) {
    submitOrderButton.addEventListener('click', handleSubmitOrder); // Submit order button click
}

if (confirmOrderButton) {
    confirmOrderButton.addEventListener('click', handleConfirmOrder); // Confirm order button click
}

// Function to handle user registration
function handleRegister(event) {
    event.preventDefault(); // Prevent default form submission behavior
    const name = document.getElementById('registerName').value; // Get username input value
    const password = document.getElementById('registerPassword').value; // Get password input value
    const role = document.getElementById('registerRole').value; // Get role input value

    // Send registration data to the server
    fetch(`${apiBaseUrl}/users`, {
        method: 'POST', // HTTP POST method
        headers: {
            'Content-Type': 'application/json' // Specify JSON content type
        },
        body: JSON.stringify({ userName: name, password, role }) // Convert data to JSON string and send in the request body
    })
    .then(response => response.json()) // Parse response as JSON
    .then(data => {
        alert('Registration successful. Please login.'); // Show success message
        window.location.href = 'login.html'; // Redirect to login page
    })
    .catch(error => console.error('Error registering user:', error)); // Log any errors
}

// Function to handle user login
function handleLogin(event) {
    event.preventDefault(); // Prevent default form submission behavior
    const name = document.getElementById('loginName').value; // Get username input value
    const password = document.getElementById('loginPassword').value; // Get password input value

    // Send login credentials to the server
    fetch(`${apiBaseUrl}/users/login`, {
        method: 'POST', // HTTP POST method
        headers: {
            'Content-Type': 'application/json' // Specify JSON content type
        },
        body: JSON.stringify({ userName: name, password, role: '' }) // Convert data to JSON string and send in the request body
    })
    .then(response => response.json()) // Parse response as JSON
    .then(data => {
        currentUser = data; // Set current user object
        // Redirect user based on role
        if (currentUser.role === 'admin') {
            window.location.href = 'adminMenu.html'; // Redirect to admin menu page
        } else if (currentUser.role === 'customer') {
            window.location.href = 'customerMenu.html'; // Redirect to customer menu page
        }
    })
    .catch(error => {
        alert('Login failed. Please check your credentials.'); // Show error message
        console.error('Error logging in:', error); // Log any errors
    });
}

// Function to fetch and display menu items
function displayMenu(isAdmin) {
    fetch(`${apiBaseUrl}/menu`) // Fetch menu items from the server
    .then(response => response.json()) // Parse response as JSON
    .then(data => {
        // Clear existing menu table rows
        menuTableBody.innerHTML = '';
        // Iterate over each menu item and create table rows
        data.forEach(item => {
            const row = document.createElement('tr'); // Create table row element
            // Populate table row with item details and action buttons
            row.innerHTML = `
                <td>${item.itemName}</td>
                <td>${item.price}</td>
                <td>
                    ${isAdmin ? `<button onclick="deleteItem(${item.menuId})">Delete</button>` : `<button onclick="addItemToOrder(${item.menuId}, '${item.itemName}', ${item.price})">Add to Order</button>`}
                </td>
            `;
            menuTableBody.appendChild(row); // Append row to menu table body
        });
    })
    .catch(error => console.error('Error fetching menu:', error)); // Log any errors
}

// Function to delete a menu item
function deleteItem(itemId) {
    fetch(`${apiBaseUrl}/menu/${itemId}`, {
        method: 'DELETE' // Send DELETE request to server
    })
    .then(response => {
        if (response.ok) {
            displayMenu(true); // Refresh menu display after successful deletion
        } else {
            console.error('Error deleting item'); // Log error if deletion fails
        }
    })
    .catch(error => console.error('Error deleting item:', error)); // Log any errors
}

// Function to add an item to the current order
function addItemToOrder(menuId, itemName, price) {
    const quantity = parseInt(prompt(`Enter quantity for ${itemName}:`), 10); // Prompt user for quantity
    if (!isNaN(quantity) && quantity > 0) { // Validate quantity
        const existingItem = currentOrder.find(item => item.menuId === menuId); // Check if item already exists in order
        if (existingItem) {
            existingItem.quantity += quantity; // Increment quantity if item already exists
        } else {
            currentOrder.push({ menuId, itemName, price, quantity }); // Add new item to order
        }
        updateOrderSummary(); // Update order summary display
    } else {
        alert('Invalid quantity.'); // Show error message for invalid quantity
    }
}

// Function to update the order summary display
function updateOrderSummary() {
    orderSummaryTableBody.innerHTML = ''; // Clear existing order summary table rows
    let totalAmount = 0; // Initialize total order amount

    // Iterate over each item in the current order
    currentOrder.forEach((item, index) => {
        const itemTotalPrice = item.quantity * item.price; // Calculate total price for the item
        totalAmount += itemTotalPrice; // Add item price to total amount

        const orderRow = document.createElement('tr'); // Create table row element for order summary
        // Populate table row with item details and action buttons
        orderRow.innerHTML = `
            <td>${item.itemName}</td>
            <td>${item.quantity}</td>
            <td>$${item.price}</td>
            <td>$${itemTotalPrice}</td>
            <td>
                <button onclick="editOrderItem(${index})">Edit</button>
                <button onclick="removeOrderItem(${index})">Remove</button>
            </td>
        `;
        orderSummaryTableBody.appendChild(orderRow); // Append row to order summary table body
    });

    totalAmountContainer.innerHTML = `Total Amount: $${totalAmount}`; // Update total amount display
    orderSummaryContainer.classList.remove('hidden'); // Show order summary container
}

// Function to edit the quantity of an item in the order
function editOrderItem(index) {
    const newQuantity = prompt('Enter new quantity:', currentOrder[index].quantity); // Prompt user for new quantity
    if (newQuantity !== null && !isNaN(newQuantity) && parseInt(newQuantity) > 0) { // Validate new quantity
        currentOrder[index].quantity = parseInt(newQuantity, 10); // Update quantity in the order
        updateOrderSummary(); // Update order summary display
    } else {
        alert('Invalid quantity.'); // Show error message for invalid quantity
    }
}

// Function to remove an item from the order
function removeOrderItem(index) {
    currentOrder.splice(index, 1); // Remove item from the order array
    updateOrderSummary(); // Update order summary display
}

// Function to handle submission of order
function handleSubmitOrder() {
    menuContainer.classList.add('hidden'); // Hide menu container
    updateOrderSummary(); // Update order summary display
}

// Function to handle confirmation of order
function handleConfirmOrder() {
    const orderData = {
        userId: currentUser.userId, // User ID associated with the order
        items: currentOrder.map(item => ({
            menuId: item.menuId, // Menu item ID
            quantity: item.quantity, // Quantity of the item
            price: item.price // Price per item
        }))
    };

    fetch(`${apiBaseUrl}/orders`, {
        method: 'POST', // HTTP POST method
        headers: {
            'Content-Type': 'application/json' // Specify JSON content type
        },
        body: JSON.stringify(orderData) // Convert order data to JSON string and send in the request body
    })
    .then(response => response.json()) // Parse response as JSON
    .then(data => {
        alert('Order submitted successfully!'); // Show success message
        currentOrder = []; // Clear current order array
        updateOrderSummary(); // Update order summary display
        menuContainer.classList.remove('hidden'); // Show menu container
        orderSummaryContainer.classList.add('hidden'); // Hide order summary container
    })
    .catch(error => console.error('Error submitting order:', error)); // Log any errors
}
