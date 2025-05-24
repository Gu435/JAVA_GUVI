-->Project Overview:-
This is a comprehensive Car Rental System built with Java, JDBC, and MySQL, featuring a Swing-based user interface. The application allows users to manage cars, customers, and rental transactions through an intuitive graphical interface.
-->Features:-
Car Management: Add, edit, delete, and view car details
Customer Management: Maintain customer records with contact information
Rental Management: Process car rentals, returns, and track rental history
Database Integration: MySQL backend with JDBC connectivity
User-Friendly UI: Swing-based graphical interface with responsive design
-->Technologies Used:-
Programming Language: Java 8+
Database: MySQL
Database Connectivity: JDBC
User Interface: Java Swing
Build Tool: Maven (recommended)
-->Prerequisites:-
Before running the application, ensure you have the following installed:
Java Development Kit (JDK) 8 or later
MySQL Server 5.7 or later
MySQL Connector/J (included in project lib folder)
IDE (Eclipse, IntelliJ IDEA, or NetBeans recommended)
-->Installation Instructions:-
Database Setup
Create a MySQL database:
sql
CREATE DATABASE car_rental_system;
Run the SQL schema script provided in the project documentation to create tables.
Update the database.properties file with your MySQL credentials:
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/car_rental_system?useSSL=false&serverTimezone=UTC
username=your_username
password=your_password
-->Project Setup:-
Clone or download the project repository.
Import the project into your preferred IDE as a Maven or Java project.
Add the MySQL Connector/J JAR file to your project's build path:
Right-click project > Build Path > Configure Build Path
Add External JARs > Select mysql-connector-java-8.0.23.jar
Build the project to resolve dependencies.
-->Running the Application:-
Run the App.java class or MainView.java as a Java application.
The main menu will appear with options to manage cars, customers, and rentals.
-->Application Structure:-
car-rental-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── carrental/
│   │   │   │   │   ├── controller/      # Business logic controllers
│   │   │   │   │   ├── dao/            # Data Access Objects
│   │   │   │   │   ├── model/          # Data models
│   │   │   │   │   ├── view/           # UI components
│   │   │   │   │   └── App.java        # Main application class
│   │   ├── resources/
│   │   │   └── database.properties     # Database configuration
├── lib/
│   └── mysql-connector-java-8.0.23.jar # MySQL JDBC driver
-->Usage Guide
->Managing Cars
Click "Manage Cars" from the main menu
Add new cars with details like make, model, year, and daily rate
Edit existing car information
Remove cars from the system
View all available cars
->Managing Customers
Click "Manage Customers" from the main menu
Add new customers with personal details
Update customer information
Delete customer records
View all registered customers
->Managing Rentals
Click "Manage Rentals" from the main menu
Create new rental transactions
Process car returns
View active and completed rentals
Calculate rental costs
->Troubleshooting
1.Database Connection Issues:
->Verify MySQL server is running
->Check credentials in database.properties
->Ensure the MySQL Connector JAR is properly added
2.UI Rendering Problems
Try setting the system look and feel:
->java
UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
3.Class Not Found Errors:-
->Rebuild the project to ensure all dependencies are resolved
->Verify the MySQL Connector JAR is in the build path
4.Contributing
->Contributions are welcome! Please follow these steps:
->Fork the repository
->Create a new branch for your feature/fix
->Commit your changes
->Push to the branch
->Create a Pull Request