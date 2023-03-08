import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends javax.swing.JFrame implements ActionListener {
    static JTextField text;

    Calculator(){};

    public static void main(String[] args) {
        createWindow();
    }
    public static void createWindow() {
        JFrame frame = new JFrame("Calculator App");
        JPanel panel = new JPanel();
        frame.add(panel);

        //create text field and make it non-editable
        text = new JTextField(16);
        Font newFont = new Font(text.getFont().getName(), text.getFont().getStyle(), 24);
        text.setFont(newFont);
        text.setEditable(false);
        text.setHorizontalAlignment(0);
        text.setSize(50, 50);
        text.setBounds(75, 75, 150, 150);
        panel.add(text);

        Calculator c = new Calculator();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 750);
        frame.setVisible(true);
        // buttons for numbers 0-9
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton button0 = new JButton("0");
        JButton decimalButton = new JButton(".");
        Dimension size = button1.getPreferredSize();
        int buttonWidth = size.width + 20;
        int buttonHeight = size.height + 20;

        button1.setBounds(75, 225, buttonWidth, buttonHeight);
        button2.setBounds(150, 225, buttonWidth, buttonHeight);
        button3.setBounds(225, 225, buttonWidth, buttonHeight);
        button4.setBounds(75, 275, buttonWidth, buttonHeight);
        button5.setBounds(150, 275, buttonWidth, buttonHeight);
        button6.setBounds(225, 275, buttonWidth, buttonHeight);
        button7.setBounds(75, 325, buttonWidth, buttonHeight);
        button8.setBounds(150, 325, buttonWidth, buttonHeight);
        button9.setBounds(225, 325, buttonWidth, buttonHeight);
        button0.setBounds(150, 375, buttonWidth, buttonHeight);
        decimalButton.setBounds(225, 375, buttonWidth, buttonHeight);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        panel.add(button8);
        panel.add(button9);
        panel.add(button0);
        panel.add(decimalButton);

        // math buttons
        JButton equalsButton = new JButton("=");
        JButton addButton = new JButton("+");
        JButton subtractButton = new JButton("-");
        JButton multiplyButton = new JButton("*");
        JButton divideButton = new JButton("/");
        equalsButton.setBounds(300, 375, buttonWidth, buttonHeight);
        addButton.setBounds(300, 325, buttonWidth, buttonHeight);
        subtractButton.setBounds(300, 275, buttonWidth, buttonHeight);
        multiplyButton.setBounds(300, 225, buttonWidth, buttonHeight);
        divideButton.setBounds(300, 175, buttonWidth, buttonHeight);
        panel.add(equalsButton);
        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(divideButton);

        // extra buttons
        JButton negateButton = new JButton("+/-");
        JButton clearButton = new JButton("C");
        negateButton.setBounds(75, 375, buttonWidth, buttonHeight);
        clearButton.setBounds(75, 175, buttonWidth, buttonHeight);
        panel.add(negateButton);
        panel.add(clearButton);

        // add listeners
        button0.addActionListener(c);
        button1.addActionListener(c);
        button2.addActionListener(c);
        button3.addActionListener(c);
        button4.addActionListener(c);
        button5.addActionListener(c);
        button6.addActionListener(c);
        button7.addActionListener(c);
        button8.addActionListener(c);
        button9.addActionListener(c);
        decimalButton.addActionListener(c);
        equalsButton.addActionListener(c);
        addButton.addActionListener(c);
        subtractButton.addActionListener(c);
        multiplyButton.addActionListener(c);
        divideButton.addActionListener(c);
        negateButton.addActionListener(c);
        clearButton.addActionListener(c);

    }
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.length() > 1) {
            if (command.charAt(0) == '+' && command.charAt(1) == '/' && command.charAt(2) == '-') {
                text.setText(negate(text.getText()));
            }
        }
        else {
            if (command.charAt(0) == '=') {
                text.setText(evaluate(text.getText()));
            }
            else if (command.charAt(0) == 'C') {
                text.setText("");
            }
            else {
                text.setText(text.getText() + command);
            }
        }
    }
    public static String evaluate(String expression) {
        char[] arr = expression.toCharArray();
        String operand1 = "";
        boolean negateOperand1 = false;
        String operand2 = "";
        boolean negateOperand2 = false;
        String operator = "";
        double result = 0;
        String error = "";
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 && arr[i] == '-') {
                negateOperand1 = true;
            }
            else {
                if (arr[i] >= '0' && arr[i] <= '9' || arr[i] == '.') {
                    if (operator.isEmpty()) {
                        operand1 += arr[i];
                    } else {
                        operand2 += arr[i];
                    }
                }
                if (arr[i] == '+' || arr[i] == '-' || arr[i] == '/' || arr[i] == '*') {
                    operator += arr[i];
                }
            }
        }
        if (!negateOperand1) {
            if (operator.equals("+"))
                result = (Double.parseDouble(operand1) + Double.parseDouble(operand2));
            else if (operator.equals("-"))
                result = (Double.parseDouble(operand1) - Double.parseDouble(operand2));
            else if (operator.equals("/")) {
                if (Double.parseDouble(operand2) != 0)
                    result = (Double.parseDouble(operand1) / Double.parseDouble(operand2));
                else
                    error = "Cannot divide by zero";
            }
            else
                result = (Double.parseDouble(operand1) * Double.parseDouble(operand2));
        }
        else {
            if (operator.equals("+"))
                result = (-(Double.parseDouble(operand1)) + Double.parseDouble(operand2));
            else if (operator.equals("-"))
                result = (-(Double.parseDouble(operand1)) - Double.parseDouble(operand2));
            else if (operator.equals("/")) {
                if (Double.parseDouble(operand2) != 0)
                    result = (-(Double.parseDouble(operand1)) / Double.parseDouble(operand2));
                else
                    error = "Cannot divide by zero";
            }
            else
                result = (-(Double.parseDouble(operand1)) * Double.parseDouble(operand2));
            operand1 = "-" + operand1;
        }
        if (result % 1 == 0) {
            result = (int) result;
        }
        if (error.isEmpty())
            return operand1 + operator + operand2 + "=" + result;
        else
            return error;
    }
    public static String negate(String expression) {
        char[] arr = expression.toCharArray();
        String operand = "-";
        for (int i = 0; i < arr.length; i++) {
            operand += arr[i];
        }
        return operand;
    }
}
