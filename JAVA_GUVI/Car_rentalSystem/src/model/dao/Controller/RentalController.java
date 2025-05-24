package model.dao.Controller;

import java.util.Date;
import java.util.List;

import dao.CustomerDao;
import model.Car;
import model.Rental;
import model.dao.CarDao;
import model.dao.RentalDao;

public class RentalController {
    private final RentalDao rentalDao;
    private final CarDao carDao;
    private final CustomerDao customerDao;

    public RentalController() {
        this.rentalDao = new RentalDao();
        this.carDao = new CarDao();
        this.customerDao = new CustomerDao();
    }

    // Create new rental
    public boolean createRental(int carId, int customerId, Date rentalDate,
            Date returnDate, double totalCost) {
        // Check if car is available
        if (!carDao.getCarById(carId).getStatus().equals("AVAILABLE")) {
            return false;
        }

        Rental rental = new Rental(carId, customerId, rentalDate, returnDate, totalCost, "ACTIVE");
        boolean success = rentalDao.addRental(rental);

        if (success) {
            // Update car status to RENTED
            carDao.updateCarStatus(carId, "RENTED");
        }
        return success;
    }

    // Complete rental
    public boolean completeRental(int rentalId) {
        Rental rental = rentalDao.getRentalById(rentalId);
        if (rental != null && rental.getStatus().equals("ACTIVE")) {
            rental.setStatus("COMPLETED");
            rental.setReturnDate(new Date());

            boolean success = rentalDao.updateRental(rental);
            if (success) {
                // Update car status back to AVAILABLE
                carDao.updateCarStatus(rental.getCarId(), "AVAILABLE");
            }
            return success;
        }
        return false;
    }

    // Get all rentals
    public List<Rental> getAllRentals() {
        return rentalDao.getAllRentals();
    }

    // Get active rentals
    public List<Rental> getActiveRentals() {
        return rentalDao.getActiveRentals();
    }

    // Get rentals by customer
    public List<Rental> getRentalsByCustomer(int customerId) {
        return rentalDao.getAllRentals().stream()
                .filter(r -> r.getCustomerId() == customerId)
                .toList();
    }

    // Calculate rental cost
    public double calculateRentalCost(int carId, long days) {
        Car car = carDao.getCarById(carId);
        if (car != null) {
            return days * car.getDailyRate();
        }
        return 0;
    }
}