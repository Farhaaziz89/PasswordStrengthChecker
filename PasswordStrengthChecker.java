import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.security.SecureRandom;

public class PasswordStrengthChecker extends JFrame {

    private JPasswordField passwordField;
    private JLabel strengthLabel, lengthLabel;
    private JButton copyButton, suggestButton;
    private JCheckBox showPassword;
    private JProgressBar strengthMeter;
    private static final Clipboard CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#&()–[{}]:;',?/*~$^+=<>";
    private static final int PASSWORD_LENGTH = 12;

    public PasswordStrengthChecker() {
        createUI();
        setTitle("Advanced Password Strength Checker");
        setSize(480, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createUI() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter Password: "));

        passwordField = new JPasswordField(20);
        inputPanel.add(passwordField);

        showPassword = new JCheckBox("Show Password");
        inputPanel.add(showPassword);

        copyButton = new JButton("Copy to Clipboard");
        inputPanel.add(copyButton);

        suggestButton = new JButton("Suggest Strong Password");
        inputPanel.add(suggestButton);

        add(inputPanel);

        strengthLabel = new JLabel("Password Strength: ");
        add(strengthLabel);

        lengthLabel = new JLabel("Password Length: 0");
        add(lengthLabel);

        strengthMeter = new JProgressBar(0, 10);
        add(strengthMeter);

        attachListeners();
    }

    private void attachListeners() {
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { check(); }
            public void removeUpdate(DocumentEvent e) { check(); }
            public void insertUpdate(DocumentEvent e) { check(); }
            private void check() {
                String password = new String(passwordField.getPassword());
                updateStrengthLabel(password);
                updateLengthLabel(password);
                updateStrengthMeter(password);
            }
        });

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        });

        copyButton.addActionListener(e -> copyPasswordToClipboard());
        suggestButton.addActionListener(e -> suggestStrongPassword());
    }

    private void updateStrengthLabel(String password) {
        int strength = getPasswordStrength(password);
        String strengthText = "Weak";
        Color color = Color.RED;

        if (strength >= 10) {
            strengthText = "Very Strong";
            color = new Color(0, 100, 0);
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

    private void updateStrengthMeter(String password) {
        strengthMeter.setValue(getPasswordStrength(password));
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

    private void suggestStrongPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        passwordField.setText(sb.toString());
        showPassword.setSelected(false);
        passwordField.setEchoChar('•');
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordStrengthChecker().setVisible(true));
    }
}
