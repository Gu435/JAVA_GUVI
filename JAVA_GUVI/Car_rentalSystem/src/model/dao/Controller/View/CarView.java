package model.dao.Controller.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.carrental.dao.CarDAO;

import model.Car;

public class CarView extends JFrame {
    private CarDAO carDAO;
    private JTable carTable;
    private DefaultTableModel tableModel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton backButton;

    public CarView() {
        carDAO = new CarDAO();
        setTitle("Manage Cars");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        styleComponents();
        loadCarData();
    }

    private void initComponents() {
        // Table model
        tableModel = new DefaultTableModel(new Object[]{"ID", "Make", "Model", "Year", "Color", "License Plate", "Daily Rate", "Status"}, 0);
        carTable = new JTable(tableModel);
        carTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Buttons
        addButton = new JButton("Add Car");
        editButton = new JButton("Edit Car");
        deleteButton = new JButton("Delete Car");
        refreshButton = new JButton("Refresh");
        backButton = new JButton("Back to Main");
        
        // Button actions
        addButton.addActionListener(new ActionListener()); {

    @Override
            public void actionPerformed(ActionEvent e) {
                showCarForm(null);
            }
        }
    }
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = carTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int carId = (int) tableModel.getValueAt(selectedRow, 0);
                    Car car = carDAO.getCarById(carId);
                    showCarForm(car);
                } else {
                    JOptionPane.showMessageDialog(CarView.this, "Please select a car to edit", "No Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = carTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int carId = (int) tableModel.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(CarView.this, 
                            "Are you sure you want to delete this car?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (carDAO.deleteCar(carId)) {
                            loadCarData();
                            JOptionPane.showMessageDialog(CarView.this, "Car deleted successfully");
                        } else {
                            JOptionPane.showMessageDialog(CarView.this, "Failed to delete car", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(CarView.this, "Please select a car to delete", "No Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCarData();
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void layoutComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);

        mainPanel.add(new JScrollPane(carTable), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void styleComponents() {
        // Set a modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Style buttons
        Font buttonFont = new Font("Segoe UI", Font.PLAIN, 12);
        addButton.setFont(buttonFont);
        editButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        refreshButton.setFont(buttonFont);
        backButton.setFont(buttonFont);

        addButton.setBackground(new Color(50, 205, 50));
        editButton.setBackground(new Color(65, 105, 225));
        deleteButton.setBackground(new Color(220, 20, 60));
        refreshButton.setBackground(new Color(255, 215, 0));
        backButton.setBackground(new Color(169, 169, 169));

        addButton.setForeground(Color.WHITE);
        editButton.setForeground(Color.WHITE);
        deleteButton.setForeground(Color.WHITE);
        refreshButton.setForeground(Color.BLACK);
        backButton.setForeground(Color.WHITE);

        addButton.setFocusPainted(false);
        editButton.setFocusPainted(false);
        deleteButton.setFocusPainted(false);
        refreshButton.setFocusPainted(false);
        backButton.setFocusPainted(false);

        // Style table
        carTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        carTable.setRowHeight(25);
        carTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        carTable.setAutoCreateRowSorter(true);
    }

    private void loadCarData() {
        tableModel.setRowCount(0);
        List<Car> cars = carDAO.getAllCars();
        for (Car car : cars) {
            tableModel.addRow(new Object[] {
                    car.getCarId(),
                    car.getMake(),
                    car.getModel(),
                    car.getYear(),
                    car.getColor(),
                    car.getLicensePlate(),
                    car.getDailyRate(),
                    car.getStatus()
            });
        }
    }

    private void showCarForm(Car car) {
        JDialog dialog = new JDialog(this, car == null ? "Add New Car" : "Edit Car", true);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(this);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Form fields
        JTextField makeField = new JTextField(20);
        JTextField modelField = new JTextField(20);
        JTextField yearField = new JTextField(20);
        JTextField colorField = new JTextField(20);
        JTextField licenseField = new JTextField(20);
    }