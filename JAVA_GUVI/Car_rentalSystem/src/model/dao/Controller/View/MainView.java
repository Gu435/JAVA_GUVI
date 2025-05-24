package model.dao.Controller.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class MainView extends JFrame {
    private JButton carButton;
    private JButton customerButton;
    private JButton rentalButton;
    private JButton exitButton;

    public MainView() {
        setTitle("Car Rental System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        styleComponents();
    }

    private void initComponents() {
        carButton = new JButton("Manage Cars");
        customerButton = new JButton("Manage Customers");
        rentalButton = new JButton("Manage Rentals");
        exitButton = new JButton("Exit");

        carButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CarView().setVisible(true);
            }
        });

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerView().setVisible(true);
            }
        });

        rentalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RentalView().setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void layoutComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(carButton, gbc);

        gbc.gridy = 1;
        panel.add(customerButton, gbc);

        gbc.gridy = 2;
        panel.add(rentalButton, gbc);

        gbc.gridy = 3;
        panel.add(exitButton, gbc);

        add(panel, BorderLayout.CENTER);
    }

    private void styleComponents() {
        // Set a modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Style buttons
        Font buttonFont = new Font("Segoe UI", Font.PLAIN, 14);
        carButton.setFont(buttonFont);
        customerButton.setFont(buttonFont);
        rentalButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        carButton.setBackground(new Color(70, 130, 180));
        customerButton.setBackground(new Color(70, 130, 180));
        rentalButton.setBackground(new Color(70, 130, 180));
        exitButton.setBackground(new Color(220, 20, 60));

        carButton.setForeground(Color.WHITE);
        customerButton.setForeground(Color.WHITE);
        rentalButton.setForeground(Color.WHITE);
        exitButton.setForeground(Color.WHITE);

        carButton.setFocusPainted(false);
        customerButton.setFocusPainted(false);
        rentalButton.setFocusPainted(false);
        exitButton.setFocusPainted(false);

        // Set window icon
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/car_icon.png"));
        if (icon.getImage() != null) {
            setIconImage(icon.getImage());
        }
    }
}