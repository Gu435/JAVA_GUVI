package model.dao.Controller.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import model.Customer;
import model.dao.Controller.CustomerController;

public class CustomerView extends JFrame {
    private final CustomerController controller;
    private JTable customerTable;
    private DefaultTableModel tableModel;

    public CustomerView() {
        this.controller = new CustomerController();
        initializeUI();
        loadCustomerData();
    }

    private void initializeUI() {
        setTitle("Customer Management");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table setup
        tableModel = new DefaultTableModel(
                new Object[] { "ID", "Name", "Email", "Phone", "License Number" },
                0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        customerTable = new JTable(tableModel);
        customizeTable();
        JScrollPane scrollPane = new JScrollPane(customerTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Customer List"));

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JButton addButton = createActionButton("Add Customer", new Color(46, 125, 50));
        JButton editButton = createActionButton("Edit Customer", new Color(30, 136, 229));
        JButton deleteButton = createActionButton("Delete Customer", new Color(198, 40, 40));
        JButton refreshButton = createActionButton("Refresh", new Color(255, 179, 0));
        JButton backButton = createActionButton("Back", new Color(120, 144, 156));

        // Button actions
        addButton.addActionListener(this::showAddCustomerDialog);
        editButton.addActionListener(this::showEditCustomerDialog);
        deleteButton.addActionListener(this::deleteCustomer);
        refreshButton.addActionListener(e -> loadCustomerData());
        backButton.addActionListener(e -> dispose());

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);

        // Add components to main panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Status bar
        JLabel statusBar = new JLabel(" Total customers: " + tableModel.getRowCount());
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        mainPanel.add(statusBar, BorderLayout.NORTH);

        add(mainPanel);
        applyModernStyle();
    }

    private void customizeTable() {
        customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customerTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        customerTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        customerTable.setRowHeight(25);
        customerTable.setAutoCreateRowSorter(true);
        customerTable.setGridColor(new Color(240, 240, 240));
    }

    private JButton createActionButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(150, 35));
        return button;
    }

    private void loadCustomerData() {
        tableModel.setRowCount(0);
        List<Customer> customers = controller.getAllCustomers();
        for (Customer customer : customers) {
            tableModel.addRow(new Object[] {
                    customer.getId(),
                    customer.getName(),
                    customer.getEmail(),
                    customer.getPhone(),
                    customer.getLicenseNumber()
            });
        }
        updateStatusBar();
    }

    private void showAddCustomerDialog(ActionEvent e) {
        JDialog dialog = new JDialog(this, "Add New Customer", true);
        dialog.setSize(450, 350);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Full Name:");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();
        JLabel licenseLabel = new JLabel("License Number:");
        JTextField licenseField = new JTextField();

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(licenseLabel);
        panel.add(licenseField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(evt -> {
            if (validateCustomerFields(nameField, emailField, phoneField, licenseField)) {
                boolean success = controller.addCustomer(
                        nameField.getText(),
                        emailField.getText(),
                        phoneField.getText(),
                        licenseField.getText());
                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Customer added successfully!");
                    loadCustomerData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Error adding customer. Email or license may already exist.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(evt -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void showEditCustomerDialog(ActionEvent e) {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a customer to edit",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int customerId = (int) tableModel.getValueAt(selectedRow, 0);
        Customer customer = controller.getCustomerById(customerId);

        JDialog dialog = new JDialog(this, "Edit Customer", true);
        dialog.setSize(450, 350);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Full Name:");
        JTextField nameField = new JTextField(customer.getName());
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(customer.getEmail());
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField(customer.getPhone());
        JLabel licenseLabel = new JLabel("License Number:");
        JTextField licenseField = new JTextField(customer.getLicenseNumber());

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(licenseLabel);
        panel.add(licenseField);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(evt -> {
            if (validateCustomerFields(nameField, emailField, phoneField, licenseField)) {
                boolean success = controller.updateCustomer(
                        customerId,
                        nameField.getText(),
                        emailField.getText(),
                        phoneField.getText(),
                        licenseField.getText());
                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Customer updated successfully!");
                    loadCustomerData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Error updating customer.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(evt -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private boolean validateCustomerFields(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!",
                        "Validation Error", JOptionPane.WARNING_MESSAGE);
                field.requestFocus();
                return false;
            }
        }
        return true;
    }

    private void deleteCustomer(ActionEvent e) {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a customer to delete",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int customerId = (int) tableModel.getValueAt(selectedRow, 0);
        String customerName = (String) tableModel.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete customer: " + customerName + "?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (controller.deleteCustomer(customerId)) {
                JOptionPane.showMessageDialog(this, "Customer deleted successfully");
                loadCustomerData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete customer. They may have active rentals.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateStatusBar() {
        ((JLabel) ((JPanel) getContentPane().getComponent(0)).getComponent(0))
                .setText(" Total customers: " + tableModel.getRowCount());
    }

    private void applyModernStyle() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}