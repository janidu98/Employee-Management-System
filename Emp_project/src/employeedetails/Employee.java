package employeedetails;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Vector;

public class Employee implements ActionListener {

    //create the Instance variables
    Connection con;
    Statement st;
    ResultSet rs;
    int q;

    //Create the Memory variables
    String sql, sex;

    JFrame frame;

    DefaultTableModel model = new DefaultTableModel();

    JLabel title = new JLabel("Employee Details System");
    JLabel id = new JLabel("Employee ID");
    JLabel name = new JLabel("Employee Name");
    JLabel address = new JLabel("Address");
    JLabel dob = new JLabel("Date of Birth");
    JLabel gender = new JLabel("Gender");
    JLabel telNo = new JLabel("Telephone NO");

    JTextField textId = new JTextField();
    JTextField textName = new JTextField();
    JTextField textDob = new JTextField();
    JTextField textTel = new JTextField(10);

    JTextArea textAddress = new JTextArea();

    JRadioButton male = new JRadioButton("Male");
    JRadioButton female = new JRadioButton("Female");
    ButtonGroup group = new ButtonGroup();

    JButton btnSearch = new JButton("Search Record");
    JButton btnNew = new JButton("New Record");
    JButton btnUpdate = new JButton("Update Record");
    JButton btnDelete = new JButton("Delete Record");
    JButton btnClr = new JButton("Clear");
    JButton btnExt = new JButton("Exit");

    //String column[] = {"ID", "Name", "Address", "DOB", "Gender", "Tel No"};
    JTable table = new JTable(model) {
        public Component prepareRenderer(TableCellRenderer renderer,
                                         int row, int column) {
            Component c = super.prepareRenderer(renderer, row, column);
            Color color1 = new Color(220, 220, 220);
            Color color2 = Color.WHITE;
            if (!c.getBackground().equals(getSelectionBackground())) {
                Color coleur = (row % 2 == 0 ? color1 : color2);
                c.setBackground(coleur);
                coleur = null;
            }
            return c;
        }
    };
    ;

    // table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
    JScrollPane sp = new JScrollPane(table);

    public Employee() {
        prepareGUI();
        addComponents();
        updateDB();
        addActionEvent();
    }

    //create the Connection method
    void conn() {
        //using error handler tool
        try {
            //Add the class
            Class.forName("com.mysql.jdbc.Driver");
            //Set the Connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?zeroDateTimeBehavior=convertToNull", "root", "");

        } catch (ClassNotFoundException | SQLException ex) {
            //Catch the error
            JOptionPane.showMessageDialog(frame, "Error : " + ex, "Employee Details System", JOptionPane.ERROR_MESSAGE);
        }
    }

    void updateDB() {
        //using error handler tool
        try {
            //Add the class
            Class.forName("com.mysql.jdbc.Driver");
            //Set the Connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?zeroDateTimeBehavior=convertToNull", "root", "");
            sql = "select * from empdetails";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            ResultSetMetaData stData = rs.getMetaData();

            q = stData.getColumnCount();
            // DefaultTableModel DefaultTableModel = null;

            //DefaultTableModel RecordTable = new DefaultTableModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector columnData = new Vector();

                for (int i = 1; i <= q; i++) {
                    columnData.add(rs.getString("empId"));
                    columnData.add(rs.getString("empName"));
                    columnData.add(rs.getString("empAddress"));
                    columnData.add(rs.getString("dob"));
                    columnData.add(rs.getString("gender"));
                    columnData.add(rs.getString("telNo"));
                }
                model.addRow(columnData);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            //Catch the error
            JOptionPane.showMessageDialog(frame, "Error : " + ex, "Employee Details System", JOptionPane.ERROR_MESSAGE);
        }
    }

    void clr() {
        textId.setText("");
        textName.setText("");
        textAddress.setText("");
        textDob.setText("");
//        male.setSelected(false);
//        female.setSelected(false);
        group.clearSelection();
        textTel.setText("");
    }

    public void prepareGUI() {
        frame = new JFrame("Employee Details");
        frame.setSize(865, 920);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void addComponents() {
        //Set labels to frame
        title.setBounds(150, 25, 650, 80);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(title);

        id.setBounds(50, 130, 150, 50);
        id.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(id);

        name.setBounds(50, 180, 200, 50);
        name.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(name);

        address.setBounds(50, 230, 200, 50);
        address.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(address);

        dob.setBounds(50, 280, 200, 50);
        dob.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(dob);

        gender.setBounds(50, 330, 200, 50);
        gender.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(gender);

        telNo.setBounds(50, 380, 200, 50);
        telNo.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(telNo);

        //Set textFields to frame
        textId.setBounds(280, 130, 450, 35);
        textId.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(textId);

        textName.setBounds(280, 180, 450, 35);
        textName.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(textName);

        textAddress.setBounds(280, 230, 450, 35);
        textAddress.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(textAddress);

        textDob.setBounds(280, 280, 300, 35);
        textDob.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(textDob);

        male.setBounds(280, 330, 100, 35);
        male.setFont(new Font("Arial", Font.BOLD, 20));
        male.setSelected(true);
        frame.add(male);

        female.setBounds(400, 330, 150, 35);
        female.setFont(new Font("Arial", Font.BOLD, 20));
        male.setSelected(true);
        frame.add(female);

        group.add(male);
        group.add(female);
        group.clearSelection();

        textTel.setBounds(280, 380, 300, 35);
        textTel.setFont(new Font("Arial", Font.BOLD, 20));
        //only number code
        textTel.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  //if it is not a number, ignore the event
                }
            }
        });
        frame.add(textTel);

        //Set button to frame
        btnSearch.setBounds(50, 480, 300, 35);
        btnSearch.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(btnSearch);

        btnNew.setBounds(50, 530, 300, 35);
        btnNew.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(btnNew);

        btnUpdate.setBounds(50, 580, 300, 35);
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(btnUpdate);

        btnDelete.setBounds(450, 480, 300, 35);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(btnDelete);

        btnClr.setBounds(450, 530, 300, 35);
        btnClr.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(btnClr);

        btnExt.setBounds(450, 580, 300, 35);

        btnExt.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(btnExt);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Address");
        model.addColumn("DOB");
        model.addColumn("Gender");
        model.addColumn("Tel No");

        // table.setPreferredScrollableViewportSize(new Dimension(5,5));
        // table.setFillsViewportHeight(true);
        table.setRowHeight(table.getRowHeight() + 5);

        sp.setBounds(25, 650, 1000, 200);
        sp.setVisible(true);
        frame.add(sp);
    }

    public void addActionEvent() {
        btnSearch.addActionListener(this);
        btnNew.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClr.addActionListener(this);
        btnExt.addActionListener(this);
        male.addActionListener(this);
        female.addActionListener(this);
        // table.addMouseListener((MouseListener) this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == btnSearch) {
            try {
                //call the connection
                conn();
                //Assigning the select statement
                sql = "select * from empdetails where empId='" + textId.getText() + "'";
                //Create the Statement Object
                st = con.createStatement();
                //Read the Statement Object using ResultSet Object
                rs = st.executeQuery(sql);
                rs.next();

//                while(rs.next())
//           {
//               model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
//           }
                //Assigning the values
                textName.setText(rs.getString(2));
                textAddress.setText(rs.getString(3));
                textDob.setText(rs.getString(4));
                sex = rs.getString(5);
                //Check sex value
                switch (sex) {
                    case "Male":
                        male.setSelected(true);
                        break;
                    case "Female":
                        female.setSelected(true);
                        break;
                    default:
                        male.setSelected(false);
                        female.setSelected(false);
                        break;
                }

                textTel.setText("0" + rs.getString(6));

                //Display message
                JOptionPane.showMessageDialog(frame, "Record found successfully !", "Employee Details System", JOptionPane.INFORMATION_MESSAGE);

                //Close the connection
                con.close();
                updateDB();
            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error : Record not found !!!", "Employee Details System", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (source == btnNew) {
            try {
                //Call the connection
                conn();
                //Set the New record statement
                sql = "insert into empdetails values('" + textId.getText() + "','" + textName.getText() + "','" + textAddress.getText() + "','" + textDob.getText() + "','" + sex + "','" + textTel.getText() + "')";
                //Create the Statement
                st = con.createStatement();
                //Execute the Statement
                st.executeUpdate(sql);
                //rs.next();

                //Display Message
                JOptionPane.showMessageDialog(frame, "New Record added successfully !", "Employee Details System", JOptionPane.INFORMATION_MESSAGE);

                //Close the connection
                con.close();
                //for updating db
                updateDB();
                //Call the clear procedure
                clr();
            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error : " + ex, "Employee Details System", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (source == btnUpdate) {
            try {
                //Call the connection
                conn();
                //Set the Save record statement
                sql = "update empdetails set empName='" + textName.getText() + "',empAddress='" + textAddress.getText() + "',dob='" + textDob.getText() + "',gender='" + sex + "',telNo='" + textTel.getText() + "'where empId='" + textId.getText() + "'";
                //Create the Statement
                st = con.createStatement();
                //Execute the Statement
                st.executeUpdate(sql);
                rs.next();
                //Display Message
                JOptionPane.showMessageDialog(frame, "Record Updated successfully !", "Employee Details System", JOptionPane.INFORMATION_MESSAGE);

                //Close the Connection
                con.close();
                //for uptading db
                updateDB();
                //Call the Clear procedure
                clr();
            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error : " + ex, "Employee Details System", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (source == btnDelete) {

            try {
                int deleteItem = JOptionPane.showConfirmDialog(frame, "Do you want to Delete this Record?", "Employee Details System", JOptionPane.YES_NO_OPTION);

                if (deleteItem == JOptionPane.YES_OPTION) {
                    //Call the connection
                    conn();
                    //Set the delete record statement
                    sql = "delete from empdetails where empId='" + textId.getText() + "'";
                    //Create the statement
                    st = con.createStatement();
                    //Execute the Statement
                    st.executeUpdate(sql);
                    rs.next();

                    JOptionPane.showMessageDialog(frame, "Record Deleted Successfully !", "Employee Details System", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Record is not Deleted", "Employee Details System", JOptionPane.INFORMATION_MESSAGE);
                }

                //Close the connection
                con.close();
                //for uptading db
                updateDB();
                //Call the clear procedure
                clr();
            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error : " + ex, "Employee Details System", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (source == male) {
            sex = "Male";
        }

        if (source == female) {
            sex = "Female";
        }

        if (source == btnClr) {
            clr();
        }

        if (source == btnExt) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Employee emp = new Employee();
    }

}
