import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ui.MainMenu;

public class Main {
    public static void main(String[] args) {

        setLookAndFeel();

        startApplication();
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error setting look and feel:");
            e.printStackTrace();
        }
    }

    private static void startApplication() {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);

            testDatabaseConnection();
        });
    }

    private static void testDatabaseConnection() {
        try {
            model.dao.Database.getConnection();
            System.out.println("Database connection successful!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Failed to connect to database.\nPlease check your configuration.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            System.err.println("Database connection failed:");
            e.printStackTrace();
        }
    }
}