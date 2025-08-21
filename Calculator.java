import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    JTextField inputField;
    String operator;
    double num1, num2, result;

    public Calculator() {
        setTitle("Simple Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.BOLD, 24));
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        setLayout(new BorderLayout(10, 10));
        add(inputField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9.]")) {
            inputField.setText(inputField.getText() + command);
        } else if (command.matches("[+\\-*/]")) {
            operator = command;
            num1 = Double.parseDouble(inputField.getText());
            inputField.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(inputField.getText());
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": result = num2 != 0 ? num1 / num2 : 0; break;
            }
            inputField.setText(String.valueOf(result));
        } else if (command.equals("C")) {
            inputField.setText("");
            num1 = num2 = result = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
