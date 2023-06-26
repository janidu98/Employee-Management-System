package employeedetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {


    JFrame frameLogin;

    JLabel username = new JLabel("Username");
    JLabel password = new JLabel("Password");

    JTextField textUsername = new JTextField();
    JPasswordField textPassword = new JPasswordField();

    JButton login = new JButton("Login");
    JButton exit = new JButton("Exit");

    public Login()
    {
        prepareGUI();
        addComponents();
        addActionEvent();
    }

    public void prepareGUI()
    {
        frameLogin = new JFrame("User Login");
        frameLogin.setSize(500,300);
        frameLogin.setLocationRelativeTo(null);
        frameLogin.setLayout(null);
        frameLogin.setVisible(true);
    }

    public void addComponents()
    {
        //Set label to frame
        username.setBounds(50, 30, 200, 50);
        username.setFont(new Font("Arial", Font.BOLD, 25));
        frameLogin.add(username);

        password.setBounds(50, 80, 200, 50);
        password.setFont(new Font("Arial", Font.BOLD, 25));
        frameLogin.add(password);

        //Set textField to frame
        textUsername.setBounds(230, 30, 200, 40);
        textUsername.setFont(new Font("Arial", Font.BOLD, 25));
        frameLogin.add(textUsername);

        textPassword.setBounds(230, 80, 200, 40);
        textPassword.setFont(new Font("Arial", Font.BOLD, 25));
        frameLogin.add(textPassword);

        //Set button to frame
        login.setBounds(290, 180, 150, 40);
        login.setFont(new Font("Arial", Font.BOLD, 25));
        frameLogin.add(login);

        exit.setBounds(50, 180, 150, 40);
        exit.setFont(new Font("Arial", Font.BOLD, 25));
        frameLogin.add(exit);

    }

    public void addActionEvent()
    {
        login.addActionListener(this);
        exit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        if(source == login)
        {

            if(textUsername.getText().equals("janidu98"))
            {
                if(textPassword.getText().equals("jc98821"))
                {
                    frameLogin.dispose();
                    Employee emp = new Employee();
                    //JOptionPane.showMessageDialog(frameLogin, "Welcome to Employee Mangement System", "Employee Details System", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(frameLogin, "Error : Password is incorrect", "Employee Details System", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(textPassword.getText().equals("jc98821"))
            {
                JOptionPane.showMessageDialog(frameLogin, "Error : Username is incorrect", "Employee Details System", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(frameLogin, "Error : Username and Password are incorrect", "Employee Details System", JOptionPane.ERROR_MESSAGE);
            }

        }

        if(source == exit)
        {
            System.exit(0);
        }
    }

    public static void main(String[] args)
    {
        Login lg = new Login();
    }

}
