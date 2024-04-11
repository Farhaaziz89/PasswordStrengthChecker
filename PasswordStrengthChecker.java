import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

public class PasswordStrengthChecker extends JFrame {

    private JPasswordField passwordField;
    private JLabel strengthLabel, lengthLabel;
    private JButton copyButton;
    private static final Clipboard CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();

    public PasswordStrengthChecker() {
        createUI();
        setTitle("Password Strength Checker");
        setSize(420, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createUI() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter Password: "));

        passwordField = new JPasswordField(20);
        inputPanel.add(passwordField);

        copyButton = new JButton("Copy to Clipboard");
        inputPanel.add(copyButton);

        add(inputPanel);

        strengthLabel = new JLabel("Password Strength: ");
        add(strengthLabel);

        lengthLabel = new JLabel("Password Length: 0");
        add(lengthLabel);

        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                check();
            }

            public void removeUpdate(DocumentEvent e) {
                check();
            }

            public void insertUpdate(DocumentEvent e) {
                check();
            }

            private void check() {
                String password = new String(passwordField.getPassword());
                updateStrengthLabel(password);
                updateLengthLabel(password);
            }
        });

        copyButton.addActionListener(e -> copyPasswordToClipboard());
    }

    private void updateStrengthLabel(String password) {
        int strength = getPasswordStrength(password);
        String strengthText = "Weak";
        Color color = Color.RED;

        if (strength >= 10) {
            strengthText = "Very Strong";
            color = new Color(0, 100, 0); // Dark green
        } else if (strength >= 7) {
            strengthText = "Strong";
            color = Color.GREEN;
        } else if (strength >= 4) {
            strengthText = "Moderate";
            color = Color.ORANGE;
        }

        strengthLabel.setText("Password Strength: " + strengthText);
        strengthLabel.setForeground(color);
    }

    private void updateLengthLabel(String password) {
        lengthLabel.setText("Password Length: " + password.length());
    }

    private int getPasswordStrength(String password) {
        int strengthPoints = 0;

        if (password.length() >= 10) strengthPoints += 2;
        else if (password.length() >= 8) strengthPoints += 1;

        if (password.matches("(?=.*[0-9]).*")) strengthPoints += 2;
        if (password.matches("(?=.*[a-z]).*")) strengthPoints += 2;
        if (password.matches("(?=.*[A-Z]).*")) strengthPoints += 2;
        if (password.matches("(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).*")) strengthPoints += 2;

        return strengthPoints;
    }

    private void copyPasswordToClipboard() {
        String password = new String(passwordField.getPassword());
        StringSelection stringSelection = new StringSelection(password);
        CLIPBOARD.setContents(stringSelection, null);
        JOptionPane.showMessageDialog(this, "Password copied to clipboard!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordStrengthChecker().setVisible(true));
    }
}
