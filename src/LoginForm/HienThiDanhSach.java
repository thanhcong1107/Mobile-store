package LoginForm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class HienThiDanhSach extends JFrame
 {
    JFrame jf=new JFrame();
    JLabel ln;
    Connection con;
    PreparedStatement ps;
    Statement stmt;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel() {
      	 public boolean isCellEditable(int row, int column) {
    	       //all cells false
    	       return false;
    	    }
      };
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

  public HienThiDanhSach()
  {
    	jf.setLayout(null);
  	ln = new JLabel("Danh sách nhân viên");
    	ln.setFont(new Font("Times New Roman",Font.BOLD,25));
    	ln.setForeground(Color.blue);
    	ln.setBounds(300,30,300,25);
    	jf.add(ln);

    	scrlPane.setBounds(0,80,900,600);
    	jf.add(scrlPane);
    	tabGrid.setFont(new Font ("Times New Roman",0,14));

    	model.addColumn("Tên nhân viên");
    	model.addColumn("Ngày sinh");
    	model.addColumn("Số điện thoại");
    	model.addColumn("Địa chỉ");
    	model.addColumn("Mã nhân viên");
    	model.addColumn("Chức vụ");
    	model.addColumn("Mã chức vụ");
    	model.addColumn("Tài khoản");
    	model.addColumn("Mật khẩu");
    	model.addColumn("Giới tính");

  	int r = 0;
     	try
     	{

     		//Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
		System.out.println("Connected to database.");
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        	rs = stmt.executeQuery("select * from mobileshop.nhanvien");
        	while(rs.next())
            	{
            		model.insertRow(r++,new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)});

            	}

             	con.close();
       	}
      	catch(SQLException se)
       	{
       		System.out.println(se);
          	JOptionPane.showMessageDialog(null,"SQL Error:"+se);
       	}
       	catch(Exception e)
       	{
       	   	System.out.println(e);
           	JOptionPane.showMessageDialog(null,"Error:"+e);
       	}


    	jf.setTitle("Danh sách nhân viên");
    	jf.setSize(900,700);
	jf.setLocation(20,20);
	jf.setResizable(true);
    	jf.getContentPane().setBackground(Color.cyan);
    	jf.setVisible(true);
  }


  public static void main(String args[])
    {
    	new HienThiDanhSach();
    }
}
