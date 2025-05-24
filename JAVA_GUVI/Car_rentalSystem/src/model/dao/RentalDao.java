package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Rental;

public class RentalDao {
    // CRUD operations for Rental

    private static final Statement DBConnection = null;

    public boolean addRental(Rental rental) {
        String sql = "INSERT INTO rentals (car_id, customer_id, rental_date, return_date, total_cost, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rental.getCarId());
            stmt.setInt(2, rental.getCustomerId());
            stmt.setTimestamp(3, new java.sql.Timestamp(rental.getRentalDate().getTime()));

            if (rental.getReturnDate() != null) {
                stmt.setTimestamp(4, new java.sql.Timestamp(rental.getReturnDate().getTime()));
            } else {
                stmt.setNull(4, java.sql.Types.TIMESTAMP);
            }

            stmt.setDouble(5, rental.getTotalCost());
            stmt.setString(6, rental.getStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals";

        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Rental rental = new Rental();
                rental.setRentalId(rs.getInt("rental_id"));
                rental.setCarId(rs.getInt("car_id"));
                rental.setCustomerId(rs.getInt("customer_id"));
                rental.setRentalDate(rs.getTimestamp("rental_date"));
                rental.setReturnDate(rs.getTimestamp("return_date"));
                rental.setTotalCost(rs.getDouble("total_cost"));
                rental.setStatus(rs.getString("status"));

                rentals.add(rental);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentals;
    }

    public Rental getRentalById(int rentalId) {
        String sql = "SELECT * FROM rentals WHERE rental_id = ?";
        Rental rental = null;

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rentalId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                rental = new Rental();
                rental.setRentalId(rs.getInt("rental_id"));
                rental.setCarId(rs.getInt("car_id"));
                rental.setCustomerId(rs.getInt("customer_id"));
                rental.setRentalDate(rs.getTimestamp("rental_date"));
                rental.setReturnDate(rs.getTimestamp("return_date"));
                rental.setTotalCost(rs.getDouble("total_cost"));
                rental.setStatus(rs.getString("status"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rental;
    }

    public boolean updateRental(Rental rental) {
        String sql = "UPDATE rentals SET car_id = ?, customer_id = ?, rental_date = ?, " +
                "return_date = ?, total_cost = ?, status = ? WHERE rental_id = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rental.getCarId());
            stmt.setInt(2, rental.getCustomerId());
            stmt.setTimestamp(3, new java.sql.Timestamp(rental.getRentalDate().getTime()));

            if (rental.getReturnDate() != null) {
                stmt.setTimestamp(4, new java.sql.Timestamp(rental.getReturnDate().getTime()));
            } else {
                stmt.setNull(4, java.sql.Types.TIMESTAMP);
            }

            stmt.setDouble(5, rental.getTotalCost());
            stmt.setString(6, rental.getStatus());
            stmt.setInt(7, rental.getRentalId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRental(int rentalId) {
        String sql = "DELETE FROM rentals WHERE rental_id = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rentalId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Rental> getActiveRentals() {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals WHERE status = 'ACTIVE'";

        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Rental rental = new Rental();
                rental.setRentalId(rs.getInt("rental_id"));
                rental.setCarId(rs.getInt("car_id"));
                rental.setCustomerId(rs.getInt("customer_id"));
                rental.setRentalDate(rs.getTimestamp("rental_date"));
                rental.setReturnDate(rs.getTimestamp("return_date"));
                rental.setTotalCost(rs.getDouble("total_cost"));
                rental.setStatus(rs.getString("status"));

                rentals.add(rental);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentals;
    }
}