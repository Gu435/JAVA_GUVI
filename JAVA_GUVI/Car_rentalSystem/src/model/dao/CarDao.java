package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Car;

public class CarDao {

    // Add a new car to the database
    public boolean addCar(Car car) {
        String sql = "INSERT INTO cars (make, model, license_plate, daily_rate, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, car.getMake());
            stmt.setString(2, car.getModel());
            stmt.setString(3, car.getLicensePlate());
            stmt.setDouble(4, car.getDailyRate());
            stmt.setString(5, car.getStatus());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        car.setId(rs.getInt(1)); // Set the generated ID
                    }
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Error adding car: " + e.getMessage());
            return false;
        }
    }

    // Get all cars from database
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";

        try (Connection conn = Database.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Car car = new Car(
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("license_plate"),
                        rs.getDouble("daily_rate"));
                car.setId(rs.getInt("id"));
                car.setStatus(rs.getString("status"));
                cars.add(car);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching cars: " + e.getMessage());
        }
        return cars;
    }

    // Get car by ID
    public Car getCarById(int id) {
        String sql = "SELECT * FROM cars WHERE id = ?";
        Car car = null;

        try (Connection conn = Database.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    car = new Car(
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getString("license_plate"),
                            rs.getDouble("daily_rate"));
                    car.setId(rs.getInt("id"));
                    car.setStatus(rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching car by ID: " + e.getMessage());
        }
        return car;
    }

    // Update car information
    public boolean updateCar(Car car) {
        String sql = "UPDATE cars SET make = ?, model = ?, license_plate = ?, "
                + "daily_rate = ?, status = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getMake());
            stmt.setString(2, car.getModel());
            stmt.setString(3, car.getLicensePlate());
            stmt.setDouble(4, car.getDailyRate());
            stmt.setString(5, car.getStatus());
            stmt.setInt(6, car.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating car: " + e.getMessage());
            return false;
        }
    }

    // Delete a car
    public boolean deleteCar(int id) {
        String sql = "DELETE FROM cars WHERE id = ?";

        try (Connection conn = Database.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting car: " + e.getMessage());
            return false;
        }
    }

    // Get available cars
    public List<Car> getAvailableCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE status = 'AVAILABLE'";

        try (Connection conn = Database.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Car car = new Car(
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("license_plate"),
                        rs.getDouble("daily_rate"));
                car.setId(rs.getInt("id"));
                car.setStatus(rs.getString("status"));
                cars.add(car);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching available cars: " + e.getMessage());
        }
        return cars;
    }
}