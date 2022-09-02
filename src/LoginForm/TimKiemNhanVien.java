package LoginForm;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class TimKiemNhanVien extends JFrame implements ActionListener
{
	JFrame jf;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,ln;
    JButton b0,b1,b2;
    JComboBox msname;
    String s;
	Font f;
    Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

	TimKiemNhanVien()
	{
		jf=new JFrame();
		f = new Font("Times New Roman",Font.BOLD,20);
		jf.setLayout(null);

	    ln=new JLabel("Tìm kiếm nhân viên");
	    ln.setFont(new Font("Times New Roman",Font.BOLD,25));
	    ln.setForeground(Color.blue);
	    ln.setBounds(300,30,300,40);
	    jf.add(ln);

		l1 = new JLabel("Tên nhân viên");
		//l1.setFont(f);
		l1.setBounds(50,100,200,25);
		jf.add(l1);

		t1 = new JTextField(20);
		t1.setBounds(250,100,200,25);t1.setToolTipText("Điền tên nhân viên cần tìm kiếm, ví dụ Nguyễn Văn Anh");
		jf.add(t1);

		l2 = new JLabel("Số điện thoại");
		//l2.setFont(f);
		l2.setBounds(50,140,200,25);
		jf.add(l2);

    	t2 = new JTextField(20);
		t2.setBounds(250,140,200,25);t2.setToolTipText("Điền số điện thoại của nhân viên cần tìm kiếm, ví dụ 01234566789");
		jf.add(t2);

		l3 = new JLabel("Mã nhân viên");
		//l3.setFont(f);
		l3.setBounds(50,180,200,25);
		jf.add(l3);

     	t3 = new JTextField(20);
		t3.setBounds(250,180,200,25);t3.setToolTipText("Điền mã nhân viên cần tìm kiếm, ví dụ Anh.NV0101");
		jf.add(t3);

        b0 = new JButton("Tìm kiếm",new ImageIcon("images//search.png"));
        b0.setBounds(150,330,110,35);b0.setToolTipText("Click để tìm kiếm nhân viên");
		jf.add(b0);b0.addActionListener(this);

		b1 = new JButton("Làm mới",new ImageIcon("images//clear.png"));
		b1.setBounds(450,330,110,35);b1.setToolTipText("Click để xóa thông tin đã điền");
	    jf.add(b1); b1.addActionListener(this);

	    scrlPane.setBounds(0,380,900,600);
        jf.add(scrlPane);
        tabGrid.setFont(new Font ("Times New Roman",0,15));

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

	     jf.setTitle("Tìm kiếm nhân viên ");
	     jf.setSize(800,700);
		 jf.setLocation(20,20);
		 jf.setResizable(false);
		 jf.getContentPane().setBackground(Color.cyan);
	     jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
	 if(ae.getSource()==b0)
	 {//fetch
	     try
	      {
	    	 if(((t1.getText()).isEmpty()))
	    	 {
	    		 if ((t2.getText()).isEmpty())
	    		 {
	    			 if ((t3.getText()).isEmpty()) //Chưa điền thông tin nào
	    			 {
	    				 JOptionPane.showMessageDialog(this,"Vui lòng điền ít nhất 1 thông tin để tìm kiếm","Warning!!!",JOptionPane.WARNING_MESSAGE);
	    			 }
	    			 else
	    	    	 {
	    	    		 //Tìm kiếm với mã nhân viên
	    				 int foundrec = 0;
	    	    		 int r=0;
	    	    		 //Class.forName("com.mysql.jdbc.Driver");
	    	    		 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
	    	    		 System.out.println("Connected to database.");

	    	    		 ps=con.prepareStatement("select * from mobileshop.nhanvien where maNV='"+t3.getText()+"'");
	    	    		 rs=ps.executeQuery();
	    	    		 if (model.getRowCount()>0)
	    	    			{
	    	    				for(int c=model.getRowCount()-1;c>=0;c--)
	    		    			{
	    		    				model.removeRow(c);
	    		    			}
	    	    			}
	    	    		 while(rs.next())
	    	    		 {
	    	    			 model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10) });
	    	    			 foundrec = 1;
	    	    		 }
	    	 	        if (foundrec == 0)
	                    {
	                        JOptionPane.showMessageDialog(null,"Không tìm thấy nhân viên! Vui lòng kiểm tra lại thông tin","Dialog",JOptionPane.WARNING_MESSAGE);
	                    }
	    			 
	    	    	 } 
	    		 }
	    		 else
	    		 {
	    			 if ((t3.getText()).isEmpty())
	    			{
	    				//tìm kiếm với số điện thoại
	    				 int foundrec = 0;
	    				 int r=0;
	    				 //Class.forName("com.mysql.jdbc.Driver");
	    				 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
	    				 System.out.println("Connected to database.");

	    				 ps=con.prepareStatement("select * from mobileshop.nhanvien where SDT='"+t2.getText()+"'");
	    				 rs=ps.executeQuery();
	    				 if (model.getRowCount()>0)
	    	    			{
	    	    				for(int c=model.getRowCount()-1;c>=0;c--)
	    		    			{
	    		    				model.removeRow(c);
	    		    			}
	    	    			}
	    				 while(rs.next())
	    				 {
	    					 model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10) });
	    					 foundrec = 1;
	    				 }
	    				 if (foundrec == 0)
	    				 {
	    					 JOptionPane.showMessageDialog(null,"Không tìm thấy nhân viên! Vui lòng kiểm tra lại thông tin","Dialog",JOptionPane.WARNING_MESSAGE);
	    				 }
	    			}
	    			 else
	    			 {
	    				 //TÌm kiếm với SDT và mã NV
	    				 int foundrec = 0;
	    				 int r=0;
	    				 //Class.forName("com.mysql.jdbc.Driver");
	    				 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
	    				 System.out.println("Connected to database.");

	    				 ps=con.prepareStatement("select * from mobileshop.nhanvien where SDT='"+t2.getText()+"' and maNV='"+t3.getText()+"'");
	    				 rs=ps.executeQuery();
	    				 if (model.getRowCount()>0)
	    	    			{
	    	    				for(int c=model.getRowCount()-1;c>=0;c--)
	    		    			{
	    		    				model.removeRow(c);
	    		    			}
	    	    			}
	    				 while(rs.next())
	    				 {
	    					 model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10) });
	    					 foundrec = 1;
	    				 }
	    				 if (foundrec == 0)
	    				 {
	    					 JOptionPane.showMessageDialog(null,"Không tìm thấy nhân viên! Vui lòng kiểm tra lại thông tin","Dialog",JOptionPane.WARNING_MESSAGE);
	    				 }
	    			 }
	    		}
	    	
	    	 }
	    	 else
	    	 {
	    		 if ((t2.getText()).isEmpty())
	    			{
	    			 	if ((t3.getText()).isEmpty())
	    			 	{
	    			 		 int foundrec = 0;
	    		    		 int r=0;
	    		    		 //Class.forName("com.mysql.jdbc.Driver");
	    		    		 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
	    		    		 System.out.println("Connected to database.");

	    		    		 ps=con.prepareStatement("select * from mobileshop.nhanvien where name='"+t1.getText()+"'");
	    		    		 rs=ps.executeQuery();
	    		    		 if (model.getRowCount()>0)
		    	    			{
		    	    				for(int c=model.getRowCount()-1;c>=0;c--)
		    		    			{
		    		    				model.removeRow(c);
		    		    			}
		    	    			}
	    		    		 while(rs.next())
	    		    		 {
	    		    			 model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10) });
	    		    			 foundrec = 1;
	    		    		 }
	    		 	        if (foundrec == 0)
	    	                {
	    	                    JOptionPane.showMessageDialog(null,"Không tìm thấy nhân viên! Vui lòng kiểm tra lại thông tin","Dialog",JOptionPane.WARNING_MESSAGE);
	    	                }
	    			 	}
	    			 	else
	    			 	{
	    			 		 int foundrec = 0;
	    		    		 int r=0;
	    		    		 //Class.forName("com.mysql.jdbc.Driver");
	    		    		 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
	    		    		 System.out.println("Connected to database.");

	    		    		 ps=con.prepareStatement("select * from mobileshop.nhanvien where name='"+t1.getText()+"' and maNV ='"+t3.getText()+"'");
	    		    		 rs=ps.executeQuery();
	    		    		 if (model.getRowCount()>0)
		    	    			{
		    	    				for(int c=model.getRowCount()-1;c>=0;c--)
		    		    			{
		    		    				model.removeRow(c);
		    		    			}
		    	    			}
	    		    		 while(rs.next())
	    		    		 {
	    		    			 model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10) });
	    		    			 foundrec = 1;
	    		    		 }
	    		 	        if (foundrec == 0)
	    	                {
	    	                    JOptionPane.showMessageDialog(null,"Không tìm thấy nhân viên! Vui lòng kiểm tra lại thông tin","Dialog",JOptionPane.WARNING_MESSAGE);
	    	                }
	    			 	}
	    			}
	    		 else
	    		 {
	    			 int foundrec = 0;
		    		 int r=0;
		    		 //Class.forName("com.mysql.jdbc.Driver");
		    		 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
		    		 System.out.println("Connected to database.");

		    		 ps=con.prepareStatement("select * from mobileshop.nhanvien where name='"+t1.getText()+"' and SDT='"+t2.getText()+"'");
		    		 rs=ps.executeQuery();
		    		 if (model.getRowCount()>0)
 	    			{
 	    				for(int c=model.getRowCount()-1;c>=0;c--)
 		    			{
 		    				model.removeRow(c);
 		    			}
 	    			}
		    		 while(rs.next())
		    		 {
		    			 model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10) });
		    			 foundrec = 1;
		    		 }
		 	        if (foundrec == 0)
	                {
	                    JOptionPane.showMessageDialog(null,"Không tìm thấy nhân viên! Vui lòng kiểm tra lại thông tin","Dialog",JOptionPane.WARNING_MESSAGE);
	                }
	    		 }
	         }
	         con.close();
        }
	    catch(SQLException se)
	     	{
	    		System.out.println(se);
	    		//JOptionPane.showMessageDialog(null,"SQL Error."+se);
	     	}
	    catch(Exception e)
	     	{
	    		System.out.println(e);
	    		//JOptionPane.showMessageDialog(null,"Error."+e);
	     	}
	 }
	 else if(ae.getSource()==b1)
	    {
	         t1.setText("");
	         t2.setText("");
	         t3.setText("");
	         
	    }
}
 

 public static void main(String args[])
 {
  new TimKiemNhanVien();
 }
}


