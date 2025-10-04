# 🌸 DogWood Flora - Flower Shop E-Commerce Website

A comprehensive full-stack e-commerce web application for a flower shop, built with Spring Boot and modern web technologies. This project demonstrates professional software development practices and object-oriented programming principles.

## 📋 Project Overview

DogWood Flora is a feature-rich online flower shop that enables customers to browse, search, and purchase beautiful flowers and floral arrangements. The platform includes both customer-facing features and a robust administrative dashboard for managing products, orders, events, and customers.

**Key Highlights:**
- 🌺 Browse and search flower products by category
- 🛒 Place orders with secure payment processing
- 📅 View and book special floral events
- 👨‍💼 Admin panel for complete inventory and order management
- 🔐 Secure authentication and role-based access control
- 📱 Responsive design for all devices

## 🛠️ Tools and Frameworks

### Backend Technologies
- **Java 17** - Core programming language
- **Spring Boot 3.3.4** - Application framework
- **Spring MVC** - Web application architecture
- **Spring Data JPA** - Data persistence layer
- **Spring Security** - Authentication and authorization
- **Thymeleaf** - Server-side template engine
- **Lombok** - Reduce boilerplate code with annotations
- **Maven** - Build automation and dependency management
- **Hibernate** - ORM framework (JPA implementation)

### Frontend Technologies
- **HTML5** - Semantic markup
- **CSS3** - Styling with custom properties and animations
- **JavaScript (ES6+)** - Client-side interactivity
- **Font Awesome 5.15.3** - Icon library
- **Swiper.js** - Touch slider/carousel
- **Google Fonts (Nunito, Abril Fatface)** - Typography

### Database
- **MySQL** - Production database
- **H2** - In-memory database (for testing)
- **JPA/Hibernate** - Object-relational mapping

### Additional Libraries
- **JWT (io.jsonwebtoken)** - Token-based authentication
- **Commons Codec** - Encoding utilities
- **JAXB API** - XML binding support

## 📁 Project Structure

```
DogWoodFLora-website/
├── src/
│   ├── main/
│   │   ├── java/com/dogWoodFlora/
│   │   │   ├── OoadAssignmentApplication.java    # Main application entry point
│   │   │   ├── config/                            # Configuration classes
│   │   │   ├── controller/                        # MVC Controllers
│   │   │   │   ├── AdminController.java
│   │   │   │   ├── CustomerController.java
│   │   │   │   ├── OrderController.java
│   │   │   │   ├── ProductController.java
│   │   │   │   ├── ReviewController.java
│   │   │   │   └── UserController.java
│   │   │   ├── dto/                               # Data Transfer Objects
│   │   │   │   ├── CartDTO.java
│   │   │   │   ├── EventDTO.java
│   │   │   │   ├── OrderDTO.java
│   │   │   │   ├── ProductDTO.java
│   │   │   │   └── ReviewDTO.java
│   │   │   ├── entity/                            # JPA Entities
│   │   │   │   ├── ProductEntity.java
│   │   │   │   ├── OrderEntity.java
│   │   │   │   ├── UserEntity.java
│   │   │   │   ├── CartEntity.java
│   │   │   │   └── EventEntity.java
│   │   │   ├── mapper/                            # Entity-DTO Mappers
│   │   │   │   ├── ProductMapper.java
│   │   │   │   ├── OrderMapper.java
│   │   │   │   └── UserMapper.java
│   │   │   ├── repository/                        # JPA Repositories
│   │   │   │   ├── ProductRepository.java
│   │   │   │   ├── OrderRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   ├── security/                          # Security Configuration
│   │   │   │   └── CustomUserDetails.java
│   │   │   └── service/                           # Business Logic Layer
│   │   │       ├── ProductService.java
│   │   │       ├── OrderService.java
│   │   │       └── UserService.java
│   │   └── resources/
│   │       ├── application.properties             # Application configuration
│   │       ├── static/                            # Static resources
│   │       │   ├── css/                           # Stylesheets
│   │       │   │   ├── style.css
│   │       │   │   ├── Cindex1.css
│   │       │   │   └── pCSS/
│   │       │   ├── js/                            # JavaScript files
│   │       │   │   ├── app.js
│   │       │   │   └── product.js
│   │       │   └── img/                           # Images
│   │       └── templates/                         # Thymeleaf templates
│   │           ├── customerIndex.html
│   │           ├── login.html
│   │           ├── register.html
│   │           ├── show.html
│   │           └── details.html
│   └── test/                                      # Test files
│       └── java/com/dogWoodFlora/
├── pom.xml                                        # Maven configuration
└── README.md                                      # This file
```

## 🚀 Setup and Installation

### Prerequisites

Before you begin, ensure you have the following installed:
- **Java 17** or higher ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.6+** ([Download](https://maven.apache.org/download.cgi))
- **MySQL 8.0+** ([Download](https://dev.mysql.com/downloads/))
- **Git** ([Download](https://git-scm.com/downloads))

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/IT-23161160/DogWoodFLora-website.git
   cd DogWoodFLora-website
   ```

2. **Configure the Database**
   
   Create a MySQL database:
   ```sql
   CREATE DATABASE dogwoodflora;
   ```

3. **Update Application Properties**
   
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/dogwoodflora
   spring.datasource.username=YOUR_MYSQL_USERNAME
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

4. **Build the Application**
   ```bash
   mvn clean install
   ```

5. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```
   
   Or run the JAR file:
   ```bash
   java -jar target/OOAD-assignment-0.0.1-SNAPSHOT.jar
   ```

6. **Access the Application**
   - **Customer Portal**: http://localhost:8080/user
   - **Admin Dashboard**: http://localhost:8080/admin
   - **Login Page**: http://localhost:8080/login

### Default Credentials

- **Admin Access**: Use the special key `SECRET123` during registration
- Database will be automatically initialized with schema on first run

## 🎯 OOP Concepts Used

This project extensively demonstrates object-oriented programming principles:

### 1. Encapsulation 🔒

**DTOs with Lombok Annotations:**
Data Transfer Objects encapsulate data and use Lombok annotations to reduce boilerplate code.

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CartDTO {
    private Long cartId;
    private LocalDateTime addedDate;
    private Long customerId;
    private List<Long> productIds;
}
```

**Entity Classes:**
```java
@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    
    // Private fields with controlled access through getters/setters
}
```

### 2. Inheritance 🌳

**Entity Hierarchy:**
JPA entities extend common base functionality, and Spring Security's authentication system uses inheritance.

**Service Layer Abstraction:**
Services can extend base service classes for common CRUD operations.

**Enum Inheritance:**
```java
public class ProductEntity {
    @Enumerated(EnumType.STRING)
    private Status productStatus;
    
    public enum Status {
        IN_STOCK, OUT_OF_STOCK, COMING_SOON
    }
}
```

### 3. Polymorphism 🎭

**Interface Implementations:**
Repository pattern demonstrates polymorphism through JPA interfaces:

```java
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    // Inherits methods like save(), findAll(), findById(), etc.
    // JpaRepository provides multiple implementations based on database type
}
```

**Method Overloading:**
Services provide multiple versions of methods with different parameters:

```java
@Service
public class ProductService {
    public List<ProductDTO> getAllProducts() { /* ... */ }
    
    public List<ProductDTO> getLimitedProducts(int limit) { /* ... */ }
    
    public ProductDTO getProductById(Long id) { /* ... */ }
}
```

**Controller Method Mapping:**
Different HTTP methods (GET, POST, PUT, DELETE) mapped to same endpoint:

```java
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) { /* ... */ }
    
    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) { /* ... */ }
    
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) { /* ... */ }
}
```

### 4. Abstraction 🎨

**Repository Pattern:**
Abstract data access through JPA repositories:

```java
// Abstract interface - implementation provided by Spring Data JPA
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    // No implementation needed - Spring Data JPA provides it
}
```

**Service Layer:**
Business logic abstracted from controllers:

```java
@Service
public class OrderService {
    // Abstract business operations
    public OrderDTO placeOrder(Long userId, Long productId) { /* ... */ }
    public List<OrderDTO> getAllOrders() { /* ... */ }
    public OrderDTO updateOrder(Long orderId, String newStatus) { /* ... */ }
}
```

**Security Abstraction:**
```java
@PreAuthorize("hasRole('ROLE_ADMIN')")
@GetMapping
public String viewOrders(Model model) {
    // Access control abstracted through annotations
}
```

### 5. Composition 📦

**Entity Relationships:**
Entities use composition to establish relationships:

```java
@Entity
public class OrderEntity {
    @ManyToOne
    private UserEntity user;        // Order "has-a" User
    
    @ManyToOne
    private ProductEntity product;  // Order "has-a" Product
    
    @OneToOne
    private AddressEntity address;  // Order "has-a" Address
}
```

**DTO Composition:**
```java
@Data
public class OrderDTO {
    private Long orderId;
    private Long userId;        // Composed reference to User
    private String status;
    private String paymentSlip;
}
```

## ✨ Features

### Customer Features 🛍️
- **Browse Products**: View all available flowers and floral arrangements
- **Search & Filter**: Search products by name, category, or description
- **Product Details**: View detailed information, images, and pricing
- **Shopping Cart**: Add products to cart for later purchase
- **Place Orders**: Secure order placement with payment slip upload
- **Event Booking**: Browse and register for special floral events
- **User Registration**: Create account with email and password
- **Order History**: View past orders and their status
- **Responsive Design**: Seamless experience on desktop and mobile devices

### Admin Features 👨‍💼
- **Product Management**: Add, edit, delete, and manage product inventory
- **Order Management**: View all orders, update status, and process orders
- **Customer Management**: View customer list and details
- **Event Management**: Create and manage special events
- **Category Management**: Organize products by categories
- **Image Upload**: Support for product images up to 4MB
- **Dashboard Analytics**: Overview of business metrics
- **Secure Access**: Role-based authentication with special admin key

## 🔌 API Endpoints

### Product Endpoints
```
GET    /products              - Get all products
GET    /products/{id}         - Get product by ID
GET    /products/search/{searchText} - Search products
GET    /products/categories   - Get all categories
GET    /products/categories/{category} - Get products by category
POST   /products              - Add new product (Admin only)
DELETE /products/{id}         - Delete product (Admin only)
```

### Order Endpoints
```
GET    /orders                - Get all orders (Admin only)
POST   /orders/placeOrder     - Place new order (User)
GET    /orders/{orderId}      - Get order details (Admin only)
POST   /orders/update/{orderId} - Update order status (Admin only)
POST   /orders/delete/{orderId} - Delete order (Admin only)
```

### User Endpoints
```
GET    /register              - Show registration form
POST   /register              - Process registration
GET    /login                 - Show login form
GET    /users                 - Get all customers (Admin only)
GET    /{id}                  - Get customer by ID
POST   /users/{id}/delete     - Delete customer (Admin only)
```

### Review Endpoints (REST API)
```
POST   /api/reviews           - Create new review
GET    /api/reviews/{id}      - Get review by ID
GET    /api/reviews           - Get all reviews
PUT    /api/reviews/{id}      - Update review
DELETE /api/reviews/{id}      - Delete review
```

### Customer Portal Endpoints
```
GET    /user                  - Customer home page
GET    /users/index           - Customer index page
GET    /users/view-event      - View events page
GET    /users/buy?productId={id} - Product purchase page
```

## 🎨 CSS Features

### Custom Properties (CSS Variables)
The application uses CSS custom properties for consistent theming:

```css
:root {
    --main-color: #510646;
    --pink: #e84393;
    --bg: #fdd4e8;
    --border: .1rem solid rgba(0, 0, 0, 0.3);
    --black: #333;
    --white: #fff;
    --light-color: #666;
    --box-shadow: 0 .5rem 1rem rgba(0,0,0,.1);
}
```

### Responsive Design 📱
- Mobile-first approach using media queries
- Breakpoints for tablets (768px), desktops (1024px)
- Flexible grid layouts with CSS Flexbox and Grid
- Responsive images and typography
- Touch-friendly interface elements

### Modern CSS Features
- **CSS Transitions**: Smooth animations on hover and interactions
- **CSS Transforms**: Scale, rotate, and translate effects
- **Flexbox Layout**: Modern layout system for components
- **Custom Scrollbar**: Styled scrollbar for better UX
- **CSS Animations**: Keyframe animations for dynamic elements
- **Box Shadow**: Depth and elevation effects

### Key CSS Animations
```css
/* Smooth transitions */
* {
    transition: .2s linear;
}

/* Scroll behavior */
html {
    scroll-behavior: smooth;
    scroll-padding-top: 9rem;
}

/* Hover effects */
.btn:hover {
    transform: scale(1.05);
}
```

### Swiper.js Integration
Product and review sliders with responsive breakpoints:
```javascript
var swiper = new Swiper(".product-slider", {
    slidesPerView: 3,
    loop: true,
    spaceBetween: 10,
    autoplay: { delay: 4000 },
    breakpoints: {
        0: { slidesPerView: 1 },
        550: { slidesPerView: 2 },
        800: { slidesPerView: 3 }
    }
});
```

## 🔐 Security Features

- **Spring Security** integration for authentication and authorization
- **Role-based access control** (ROLE_USER, ROLE_ADMIN)
- **JWT token** support for stateless authentication
- **Password encryption** using BCrypt
- **CSRF protection** enabled by default
- **Special admin key** for admin registration
- **Method-level security** with `@PreAuthorize` annotations

## 🧪 Testing

Run the test suite:
```bash
mvn test
```

Run specific test class:
```bash
mvn test -Dtest=OoadAssignmentApplicationTests
```

## 📦 Building for Production

Create a production-ready JAR:
```bash
mvn clean package -DskipTests
```

The JAR file will be created in the `target/` directory.

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is developed as an academic assignment for Object-Oriented Analysis and Design course.

## 👥 Authors

- **IT-23161160** - *Initial work* - [GitHub Profile](https://github.com/IT-23161160)

## 🙏 Acknowledgments

- Spring Boot documentation and community
- Font Awesome for icons
- Google Fonts for typography
- Swiper.js for carousel functionality
- MySQL for database management
- Lombok for reducing boilerplate code

---

**Made with ❤️ and 🌸 by DogWood Flora Team**
