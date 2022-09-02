package LoginForm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.Normalizer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XoaDM extends JFrame implements ActionListener
{
	JFrame jf;
	JTextField tenDM,maDM;
	JLabel TenDM,MaDM,ln;
    	JButton b_delete,b_open;
   	
	Font f;
    
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

	XoaDM()
	{
		jf=new JFrame();
		f = new Font("Times New Roman",Font.BOLD,15);
		jf.setLayout(null);

	    	ln=new JLabel("Thêm danh mục mới");
	   	ln.setFont(new Font("Times New Roman",Font.BOLD,25));
	    	ln.setForeground(Color.blue);
	    	ln.setBounds(300,30,400,40);
	    	jf.add(ln);

		TenDM = new JLabel("Tên danh mục");
		//l1.setFont(f);
		TenDM.setBounds(50,100,150,25);
		jf.add(TenDM);

		tenDM = new JTextField(20);
		tenDM.setBounds(250,100,150,25);tenDM.setToolTipText("Điền tên danh mục");
		jf.add(tenDM);tenDM.setEditable(false);

		MaDM = new JLabel("Mã danh mục");
		//l2.setFont(f);
		MaDM.setBounds(50,140,150,25);
		jf.add(MaDM);

    		maDM = new JTextField(20);
		maDM.setBounds(250,140,150,25);maDM.setToolTipText("Điền mã danh mục");
		jf.add(maDM); 	
		
        	b_delete = new JButton("Xóa",new ImageIcon("images//delete.png"));
        	b_delete.setBounds(550,330,110,35);b_delete.setToolTipText("Click để xóa");
		jf.add(b_delete);b_delete.addActionListener(this);

		b_open = new JButton("Mở",new ImageIcon("images//open.png"));
		b_open.setBounds(150,330,110,35);b_open.setToolTipText("Click để hiển thị thông tin ");
	    	jf.add(b_open); b_open.addActionListener(this);

	    	scrlPane.setBounds(0,380,900,600);
        	jf.add(scrlPane);
        	tabGrid.setFont(new Font ("Times New Roman",0,15));

        	model.addColumn("Tên danh mục");
        	model.addColumn("Mã danh mục");
        	int r = 0;
        	try
        	{

        		//Class.forName("com.mysql.jdbc.Driver");
   			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
   			System.out.println("Connected to database.");
   			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
           		rs = stmt.executeQuery("select * from mobileshop.danhmuc");
             		while(rs.next())
               		{
               			model.insertRow(r++,new Object[]{rs.getString(2),rs.getString(1)});

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
        
	     	jf.setTitle("Thêm danh mục mới ");
	     	jf.setSize(900,700);
		jf.setLocation(20,20);
		jf.setResizable(false);
		jf.getContentPane().setBackground(Color.cyan);
	     	jf.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b_delete)
		{
			if((maDM.getText()).equals("") )
  		    	{
  				JOptionPane.showMessageDialog(this,"Hãy điền mã danh mục cần xóa !","Warning!!!",JOptionPane.WARNING_MESSAGE);
  		    	}
  			else
  			{
  				int reply=JOptionPane.showConfirmDialog(null,"Bạn có chắc muốn xóa danh mục này?","Xóa danh mục",JOptionPane.YES_NO_OPTION);
  				if (reply == JOptionPane.YES_OPTION)
        			{
        				try 
        				{
        					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
        		    			System.out.println("Connected to database.");
        		    			stmt=con.createStatement();
        		    			ps=con.prepareStatement("delete from mobileshop.danhmuc where MaDM='"+maDM.getText()+"'");
        		    			ps.executeUpdate();
        					JOptionPane.showMessageDialog(null, "Sản phẩm đã bị xóa");
        				
        					tenDM.setText("");tenDM.setEditable(false);
        					maDM.setText("");maDM.setEditable(true);
        					for(int c=model.getRowCount()-1;c>=0;c--)
    	    					{
    	    						model.removeRow(c);
    	    					}
    	    					rs = stmt.executeQuery("select * from mobileshop.danhmuc");
    	    					int r=0;
    	    	       				while(rs.next())
    	    	            			{
    	    	            				model.insertRow(r++,new Object[]{rs.getString(2),rs.getString(1)});

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
        			}
  				else if (reply == JOptionPane.NO_OPTION)
        			{	
    					maDM.setText("");maDM.setEditable(true);
    					tenDM.setText("");tenDM.setEditable(false);
        			}
  			}
		}
 
		else if(ae.getSource()==b_open)
		{   
			if((maDM.getText()).isEmpty())
	       	 	{
	  	 		JOptionPane.showMessageDialog(this,"Để chỉnh sửa thông tin, hãy nhập mã danh mục!","Warning!!!",JOptionPane.WARNING_MESSAGE);
	        	}
	        	else
	        	{

	        		try
	        		{//fetch
	        			int foundrec = 0;
	        			//Class.forName("com.mysql.jdbc.Driver");
	        			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
	        			System.out.println("Connected to database.");
	    	  
	        			ps=con.prepareStatement("select * from mobileshop.danhmuc where MaDM='"+maDM.getText()+"'");
	        			rs=ps.executeQuery();
	        			while(rs.next())
	        			{
	        				tenDM.setText(rs.getString(2));tenDM.setEditable(false);
	        				maDM.setText(rs.getString(1));maDM.setEditable(false);
	        				foundrec = 1;
	        			}
	        			if (foundrec == 0)
	        			{
	        				JOptionPane.showMessageDialog(null,"Danh mục không tồn tại, vui lòng kiểm tra lại thông tin","Dialogs",JOptionPane.WARNING_MESSAGE);
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
	        	}

		}

	}
	
	public static void main(String args[])
	{
	      new XoaDM();
	}
}
