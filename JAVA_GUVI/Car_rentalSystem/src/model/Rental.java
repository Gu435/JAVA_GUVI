package Car_rentalSystem.src.model;

import java.util.Date;

public class Rental {
    private int id;
    private int carId;
    private int customerId;
    private Date rentalDate;
    private Date returnDate;
    private double totalCost;
    private String status;

    public Rental() {
    }

    public Rental(int carId, int customerId, Date rentalDate,
            Date returnDate, double totalCost, String status) {
        this.carId = carId;
        this.customerId = customerId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.totalCost = totalCost;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(status);
    }

    public boolean isCompleted() {
        return "COMPLETED".equalsIgnoreCase(status);
    }

    public boolean isCancelled() {
        return "CANCELLED".equalsIgnoreCase(status);
    }

    @Override
    public String toString() {
        return String.format(
                "Rental [ID: %d, CarID: %d, CustomerID: %d, RentalDate: %s, ReturnDate: %s, Cost: %.2f, Status: %s]",
                id, carId, customerId, rentalDate, returnDate, totalCost, status);
    }

    public boolean isValid() {
        return carId > 0 &&
                customerId > 0 &&
                rentalDate != null &&
                status != null && !status.isEmpty();
    }
}