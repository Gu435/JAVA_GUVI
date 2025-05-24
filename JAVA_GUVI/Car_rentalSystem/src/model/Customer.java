package Car_rentalSystem.src.model;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String licenseNumber;

    public Customer() {
    }

    public Customer(String name, String email, String phone, String licenseNumber) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return String.format("Customer [ID: %d, Name: %s, Email: %s, Phone: %s, License: %s]",
                id, name, email, phone, licenseNumber);
    }

    public boolean isValid() {
        return name != null && !name.isEmpty() &&
                email != null && !email.isEmpty() &&
                phone != null && !phone.isEmpty() &&
                licenseNumber != null && !licenseNumber.isEmpty();
    }
}