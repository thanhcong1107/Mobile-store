package LoginForm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class DanhSachSP extends JFrame
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

  public DanhSachSP()
  {
    jf.setLayout(null);
  	ln = new JLabel("Danh sách sản phẩm");
    ln.setFont(new Font("Times New Roman",Font.BOLD,25));
    ln.setForeground(Color.blue);
    ln.setBounds(300,30,300,25);
    jf.add(ln);

    scrlPane.setBounds(0,80,900,600);
    jf.add(scrlPane);
    tabGrid.setFont(new Font ("Times New Roman",0,14));

    model.addColumn("Tên sản phẩm");
    model.addColumn("Mã danh mục");
    model.addColumn("Tên danh mục");
    model.addColumn("Quốc gia");
    model.addColumn("Mã sản phẩm");
    model.addColumn("Số lượng");
    model.addColumn("Đơn giá");
    model.addColumn("Trạng thái");
/*
     jf.setTitle("Tìm kiếm nhân viên ");
     jf.setSize(900,900);
	 jf.setLocation(20,20);
	 jf.setResizable(true);
	 jf.getContentPane().setBackground(Color.pink);
     jf.setVisible(true);
*/
  		int r = 0;
     try
     {

     	//Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
		System.out.println("Connected to database.");
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        rs = stmt.executeQuery("select * from mobileshop.sanpham");
          while(rs.next())
            {
            	model.insertRow(r++,new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),});

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


    jf.setTitle("Danh sách sản phẩm");
    jf.setSize(900,700);
	jf.setLocation(20,20);
	jf.setResizable(true);
    jf.getContentPane().setBackground(Color.cyan);
    jf.setVisible(true);
  }


  public static void main(String args[])
    {
    	new DanhSachSP();
    }
}
