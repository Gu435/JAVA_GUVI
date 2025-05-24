package model.dao.Controller;

import java.util.List;

import model.Customer;

public class CustomerController {
    private final CustomerDao customerDao;

    public CustomerController() {
        this.customerDao = new CustomerDao();
    }

    // Add new customer
    public boolean addCustomer(String name, String email, String phone, String licenseNumber) {
        Customer customer = new Customer(name, email, phone, licenseNumber);
        return customerDao.addCustomer(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    // Get customer by ID
    public Customer getCustomerById(int id) {
        return customerDao.getCustomerById(id);
    }

    // Update customer details
    public boolean updateCustomer(int id, String name, String email,
            String phone, String licenseNumber) {
        Customer customer = new Customer(name, email, phone, licenseNumber);
        customer.setId(id);
        return customerDao.updateCustomer(customer);
    }

    // Delete customer
    public boolean deleteCustomer(int id) {
        return customerDao.deleteCustomer(id);
    }

    // Check if email exists
    public boolean emailExists(String email) {
        return customerDao.getAllCustomers().stream()
                .anyMatch(c -> c.getEmail().equalsIgnoreCase(email));
    }

    // Check if license number exists
    public boolean licenseExists(String licenseNumber) {
        return customerDao.getAllCustomers().stream()
                .anyMatch(c -> c.getLicenseNumber().equalsIgnoreCase(licenseNumber));
    }
}