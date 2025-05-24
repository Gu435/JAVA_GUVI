package model.dao.Controller;

import java.util.List;

import model.Car;
import model.dao.CarDao;

public class CarController {
    private final CarDao carDao;

    public CarController() {
        this.carDao = new CarDao();
    }

    // Add a new car
    public boolean addCar(String make, String model, String licensePlate, double dailyRate) {
        Car car = new Car(make, model, licensePlate, dailyRate);
        return carDao.addCar(car);
    }

    // Get all cars
    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    // Get car by ID
    public Car getCarById(int id) {
        return carDao.getCarById(id);
    }

    // Update car details
    public boolean updateCar(int id, String make, String model,
            String licensePlate, double dailyRate, String status) {
        Car car = new Car(make, model, licensePlate, dailyRate);
        car.setId(id);
        car.setStatus(status);
        return carDao.updateCar(car);
    }

    // Delete a car
    public boolean deleteCar(int id) {
        return carDao.deleteCar(id);
    }

    // Get available cars
    public List<Car> getAvailableCars() {
        return carDao.getAvailableCars();
    }

    // Update car status
    public boolean updateCarStatus(int carId, String status) {
        Car car = carDao.getCarById(carId);
        if (car != null) {
            car.setStatus(status);
            return carDao.updateCar(car);
        }
        return false;
    }
}
