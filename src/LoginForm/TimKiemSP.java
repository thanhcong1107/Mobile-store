package LoginForm;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimKiemSP extends JFrame implements ActionListener
{
	JFrame jf;
	JTextField tenSP,tenDM,DonGia,maSP,SoLuong,TrangThai,Gia_Min,Gia_Max;
	JLabel ten_SP,Ma_DM,Ten_DM,Quoc_Gia,Ma_SP,Don_Gia,So_Luong,Trang_Thai,Tim_Kiem,Gia_min,Gia_max,ln;
    JButton b_search,b_clear,b_all;
    JComboBox MaDM,Timkiem,Trang_thai;//,tabtype;
    String ma_dmuc,quoc_gia,timkiem,tabt;
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

	TimKiemSP()
	{
		jf=new JFrame();
		f = new Font("Times New Roman",Font.BOLD,15);
		jf.setLayout(null);

	    ln=new JLabel("Tìm kiếm sản phẩm");
	    ln.setFont(new Font("Times New Roman",Font.BOLD,25));
	    ln.setForeground(Color.blue);
	    ln.setBounds(300,30,400,40);
	    jf.add(ln);
	    
	    ten_SP = new JLabel("Tên sản phẩm");
		ten_SP.setBounds(50,140,100,25);
		jf.add(ten_SP);
		ten_SP.setVisible(false);
		
		tenSP = new JTextField(20);
		tenSP.setBounds(250,140,100,25);tenSP.setToolTipText("Điền tên sản phẩm");
		jf.add(tenSP);
		tenSP.setVisible(false);
		
		
		
		Ma_SP = new JLabel("Mã sản phẩm");
		Ma_SP.setBounds(50,140,100,25);
		jf.add(Ma_SP);
		Ma_SP.setVisible(false);
		
		
		
		
		
		maSP = new JTextField(20);
		maSP.setBounds(250,140,100,25);maSP.setToolTipText("Điền mã sản phẩm");
		jf.add(maSP);
		maSP.setVisible(false);
		
		Gia_min = new JLabel("Từ");
		Gia_min.setBounds(50,140,50,25);
		jf.add(Gia_min);
		Gia_min.setVisible(false);
		
		Gia_Min = new JTextField(20);
		Gia_Min.setBounds(80,140,80,25);
		jf.add(Gia_Min);
		Gia_Min.setVisible(false);
		
		Gia_max = new JLabel("Đến");
		Gia_max.setBounds(250,140,50,25);
		jf.add(Gia_max);
		Gia_max.setVisible(false);
		
		Gia_Max = new JTextField(20);
		Gia_Max.setBounds(280,140,80,25);
		jf.add(Gia_Max);
		Gia_Max.setVisible(false);
		
	    Tim_Kiem = new JLabel("Chọn thông tin tìm kiếm");
		Tim_Kiem.setBounds(50,100,150,25);
		jf.add(Tim_Kiem);
		
	    Timkiem = new JComboBox();
		Timkiem.setBounds(250,100,150,25);Timkiem.setToolTipText("Chọn phương pháp tìm kiếm");
		jf.add(Timkiem);
	    Timkiem.addItem("---");
	    Timkiem.addItem("Tên sản phẩm");
	    Timkiem.addItem("Mã sản phẩm");
	    Timkiem.addItem("Giá sản phẩm");
	    Timkiem.addActionListener(new ActionListener()
     	{
			public void actionPerformed(ActionEvent ae)
			{
				timkiem = (String) Timkiem.getSelectedItem();
				/*
				if (timkiem.equals("---"))
				{
					JOptionPane.showMessageDialog(null,"* Hãy chọn loại thông tin muốn tìm kiếm !","Warning!!!",JOptionPane.WARNING_MESSAGE);
				}
				*/
				if(timkiem.equals("Tên sản phẩm"))
				{
					
					ten_SP.setVisible(true);
					
					
					tenSP.setVisible(true);
				}
				else if (timkiem.equals("Mã sản phẩm"))
				{
					
					ten_SP.setVisible(false);
					tenSP.setVisible(false);
					Ma_SP.setVisible(true);
					maSP.setVisible(true);
				}
				else if (timkiem.equals("Giá sản phẩm"))
				{
					ten_SP.setVisible(false);
					tenSP.setVisible(false);
					Ma_SP.setVisible(false);
					maSP.setVisible(false);
					Gia_min.setVisible(true);
					Gia_Min.setVisible(true);
					Gia_max.setVisible(true);
					Gia_Max.setVisible(true);
				}
			}
	    
     	});
	    
	    
	  
        b_search = new JButton("Tìm kiếm",new ImageIcon("images//search.png"));
        b_search.setBounds(150,330,110,35);b_search.setToolTipText("Click để lưu thông tin");
		jf.add(b_search);b_search.addActionListener(this);

		b_clear = new JButton("Làm mới",new ImageIcon("images//clear.png"));
		b_clear.setBounds(550,330,110,35);b_clear.setToolTipText("Click để xóa toàn bộ thông tin đã điền");
	    jf.add(b_clear); b_clear.addActionListener(this);

	    scrlPane.setBounds(0,380,900,600);
        jf.add(scrlPane);
        
        tabGrid.setFont(new Font ("Times New Roman",0,15));
        
        model.addColumn("Tên sản phẩm");
        model.addColumn("Mã danh mục");
        model.addColumn("Tên danh mục");
        model.addColumn("Quốc gia");
        model.addColumn("Mã sản phẩm");
        model.addColumn("Số lượng");
        model.addColumn("Đơn giá");
        model.addColumn("Trạng thái");
        
        

	     jf.setTitle("Tìm kiếm sản phẩm");
	     jf.setSize(900,700);
		 jf.setLocation(20,20);
		 jf.setResizable(false);
		 jf.getContentPane().setBackground(Color.cyan);
	     jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b_search)
		{
				
				if(timkiem.equals("---"))		
				{
					JOptionPane.showMessageDialog(this,"* Hãy chọn 1 thông tin để tìm kiếm !","Warning!!!",JOptionPane.WARNING_MESSAGE);
				}
				
	        	else
	        	{
	        		try
	        		{
	        			if(timkiem.equals("Tên sản phẩm"))
	        			{
	        				int foundrec = 0;
		    	    		int r=0;
		    	    		//Class.forName("com.mysql.jdbc.Driver");
		    	    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
		    	    		System.out.println("Connected to database.");
		    	    		ps=con.prepareStatement("select * from mobileshop.sanpham where tenSP='"+tenSP.getText()+"'");
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
		    	    			model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8) });
		    	    			foundrec = 1;
		    	    			
		    	    		}
		    	 	        if (foundrec == 0)
		                    {
		                        JOptionPane.showMessageDialog(null,"Không tìm thấy sản phẩm! Vui lòng kiểm tra lại thông tin","Dialog",JOptionPane.WARNING_MESSAGE);
		                    }
	        			}
	        			if (timkiem.equals("Mã sản phẩm"))
	        			{
	        				int foundrec = 0;
		    	    		 int r=0;
		    	    		 //Class.forName("com.mysql.jdbc.Driver");
		    	    		 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
		    	    		 System.out.println("Connected to database.");
		    	    		 ps=con.prepareStatement("select * from mobileshop.sanpham where MaSP='"+maSP.getText()+"'");
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
		    	    			 
		    	    			 model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8) });
		    	    			 foundrec = 1;
		    	    		 }
		    	 	        if (foundrec == 0)
		                    {
		                        JOptionPane.showMessageDialog(null,"Không tìm thấy sản phẩm! Vui lòng kiểm tra lại thông tin","Dialog",JOptionPane.WARNING_MESSAGE);
		                    }
	        			}
	        			if (timkiem.equals("Giá sản phẩm"))
	        			{
	        				 
	        				 int foundrec = 0;
		    	    		 int r=0;
		    	    		 int min = Integer.parseInt(Gia_Min.getText());System.out.println(min);
		    	    		 int max = Integer.parseInt(Gia_Max.getText());System.out.println(max);
		    	    		 //Class.forName("com.mysql.jdbc.Driver");
		    	    		 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
		    	    		 System.out.println("Connected to database.");
		    	    		 ps=con.prepareStatement("select * from mobileshop.sanpham where DonGia>='"+Gia_Min.getText()+"'and DonGia <='"+Gia_Max.getText()+"'");
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
		    	    			
		    	    			 model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8) });
		    	    			 foundrec = 1;
		    	    		 }
		    	 	        if (foundrec == 0)
		                    {
		                        JOptionPane.showMessageDialog(null,"Không tìm thấy sản phẩm! Vui lòng kiểm tra lại thông tin","Dialog",JOptionPane.WARNING_MESSAGE);
		                    }
	        			}
	        		
	        		
			

			con.close();
		}
		catch(SQLException se)
		{
			System.out.println(se);
			//JOptionPane.showMessageDialog(null,"SQL Error:"+se);
		}
		catch(Exception e)
		{
			System.out.println(e);
			//JOptionPane.showMessageDialog(null,"Error:"+e);
		}
		}
	}
		else if(ae.getSource()==b_clear)
		{   
			
			Timkiem.setSelectedItem("---");	
			ten_SP.setVisible(false);
			tenSP.setVisible(false);
			Ma_SP.setVisible(false);
			maSP.setVisible(false);
			Gia_min.setVisible(false);
			Gia_Min.setVisible(false);
			Gia_max.setVisible(false);
			Gia_Max.setVisible(false);
		}

	}

	public static void main(String args[])
	{
	      new TimKiemSP();
	}
}
