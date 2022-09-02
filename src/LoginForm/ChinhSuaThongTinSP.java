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

public class ChinhSuaThongTinSP extends JFrame implements ActionListener
{
	JFrame jf;
	JTextField tenSP,tenDM,DonGia,maSP,SoLuong,TrangThai;
	JLabel ten_SP,Ma_DM,Ten_DM,Quoc_Gia,Ma_SP,Don_Gia,So_Luong,Trang_Thai,ln;
    JButton b_open,b_clear,b_save;
    JComboBox MaDM,QuocGia,Trang_thai;
    String ma_dmuc,quoc_gia,tabt,trang_thai;
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

	ChinhSuaThongTinSP()
	{
		jf=new JFrame();
		f = new Font("Times New Roman",Font.BOLD,15);
		jf.setLayout(null);

	    ln=new JLabel("Chỉnh sửa thông tin sản phẩm");
	    ln.setFont(new Font("Times New Roman",Font.BOLD,25));
	    ln.setForeground(Color.blue);
	    ln.setBounds(300,30,400,40);
	    jf.add(ln);

		ten_SP = new JLabel("Tên sản phẩm");
		ten_SP.setBounds(50,100,150,25);
		jf.add(ten_SP);

		tenSP = new JTextField(20);
		tenSP.setBounds(250,100,150,25);tenSP.setToolTipText("Điền tên sản phẩm");
		jf.add(tenSP);
		tenSP.setEditable(false);

		Ma_DM = new JLabel("Mã danh mục");
		Ma_DM.setBounds(50,140,150,25);
		jf.add(Ma_DM);

    	MaDM = new JComboBox();
		MaDM.setBounds(250,140,150,25);MaDM.setToolTipText("Chọn mã danh mục cho sản phẩm");
		jf.add(MaDM);
		MaDM.addItem("---");
		MaDM.addActionListener(new ActionListener()
	     	{
				public void actionPerformed(ActionEvent ae)
				{
					ma_dmuc =(String)MaDM.getSelectedItem();
					try {	
						   //Class.forName("com.mysql.jdbc.Driver");
							con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
							System.out.println("Connected to database.");
							ps=con.prepareStatement("select* from mobileshop.danhmuc where maDM ='"+ma_dmuc+"'");
							rs=ps.executeQuery();
							while(rs.next())
							{
								
								tenDM.setText(rs.getString(2));
								tenDM.setEditable(false);
							}

							//con.close();
						}
						   catch(SQLException se)
							{
					    	   System.out.println(se);
							}
							catch(Exception e)
							{
								System.out.println(e);
							} 
				}
	     	});
			//Lấy thông tin từ CSDL để thêm vào combobox
		try
			{
				//Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
				System.out.println("Connected to database1.");
				ps=con.prepareStatement("select MaDM from mobileshop.danhmuc");
				rs=ps.executeQuery();
				while(rs.next())
				{
					String dmuc=rs.getString(1);
					MaDM.addItem(dmuc);
				}

				con.close();
			}
		catch(SQLException se)
		{
    	   System.out.println(se);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		Ten_DM = new JLabel("Tên danh mục");
		Ten_DM.setBounds(50,180,150,25);
		jf.add(Ten_DM);
		
		tenDM= new JTextField();
		tenDM.setBounds(250,180,150,25);//maSP.setToolTipText("Điền địa chỉ ");
		jf.add(tenDM);tenDM.setEditable(false);
		
	    
		
		Quoc_Gia = new JLabel("Quốc gia");
		Quoc_Gia.setBounds(50,220,150,25);
		jf.add(Quoc_Gia);

     	QuocGia = new JComboBox();
		QuocGia.setBounds(250,220,150,25);QuocGia.setToolTipText("Chọn quốc gia nhập điện thoại");
		jf.add(QuocGia);
		QuocGia.addItem("---");
		QuocGia.addItem("Việt Nam");
		QuocGia.addItem("Mỹ");
		QuocGia.addItem("Trung Quốc");
		QuocGia.addItem("Nhật Bản");
		QuocGia.addItem("Hàn Quốc");
		QuocGia.addActionListener(new ActionListener()
     	{
			public void actionPerformed(ActionEvent ae)
			{
				quoc_gia =(String)QuocGia.getSelectedItem();
			}
     	});
		
		//Cột 2
		Ma_SP= new JLabel("Mã sản phẩm");
		Ma_SP.setBounds(470,100,150,25);
    	jf.add(Ma_SP);

        maSP= new JTextField();
		maSP.setBounds(670,100,150,25);maSP.setToolTipText("Điền mã sản phẩm ");
		jf.add(maSP);
		
		So_Luong = new JLabel("Số lượng");
		So_Luong.setBounds(470,140,150,25);
    	jf.add(So_Luong);
    	
		SoLuong= new JTextField(20);
		SoLuong.setBounds(670,140,150,25);SoLuong.setToolTipText("Điền số lượng sản phẩm");
		jf.add(SoLuong);SoLuong.setEditable(false);

		Don_Gia= new JLabel("Đơn giá");
		Don_Gia.setBounds(470,180,150,25);
    	jf.add(Don_Gia);

        DonGia= new JTextField(20);
		DonGia.setBounds(670,180,150,25);DonGia.setToolTipText("Điền giá bán cho 1 sản phẩm");
		jf.add(DonGia);DonGia.setEditable(false);

		
		Trang_Thai= new JLabel("Trạng Thái");
		Trang_Thai.setBounds(470,220,150,25);
    	jf.add(Trang_Thai);
    	
    	Trang_thai= new JComboBox();
		Trang_thai.setBounds(670,220,150,25);
		jf.add(Trang_thai);
		Trang_thai.addItem("---");
		Trang_thai.addItem("Đang kinh doanh");
		Trang_thai.addItem("Tạm hết hàng");
		Trang_thai.addItem("Ngừng kinh doanh");
		Trang_thai.setSelectedItem("---");
		
		Trang_thai.addActionListener(new ActionListener()
     	{
			public void actionPerformed(ActionEvent ae)
			{
				trang_thai =(String)Trang_thai.getSelectedItem();
			}
     	});
		Trang_thai.setEditable(false);

        b_open = new JButton("Mở",new ImageIcon("images//open.png"));
        b_open.setBounds(150,330,110,35);b_open.setToolTipText("Click để hiện thị toàn bộ thông tin sản phẩm");
		jf.add(b_open);b_open.addActionListener(this);

		b_clear = new JButton("Làm mới",new ImageIcon("images//clear.png"));
		b_clear.setBounds(550,330,110,35);b_clear.setToolTipText("Click để xóa toàn bộ thông tin đã điền");
	    jf.add(b_clear); b_clear.addActionListener(this);

        b_save= new JButton("Lưu",new ImageIcon("images//all.png"));
		b_save.setBounds(350,330,110,35);b_save.setToolTipText("click để lưu thông tin sản phẩm");
		jf.add(b_save); b_save.addActionListener(this);

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
        

	     jf.setTitle("Chỉnh sửa thông tin sản phẩm ");
	     jf.setSize(900,700);
		 jf.setLocation(20,20);
		 jf.setResizable(false);
		 jf.getContentPane().setBackground(Color.cyan);
	     jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b_open)
		{
			if((maSP.getText()).equals("") )		
			{
				JOptionPane.showMessageDialog(this,"* Hãy nhập mã sản phẩm cần chỉnh sửa !","Warning!!!",JOptionPane.WARNING_MESSAGE);
			}
			
	        else
	        {
	        	try
	        	{
	        		int foundrec = 0;
	        		//Class.forName("com.mysql.jdbc.Driver");
	        		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
	        		System.out.println("Connected to database2.");
	        		ps=con.prepareStatement("select * from mobileshop.sanpham where MaSP='"+maSP.getText()+"'");
	        		rs=ps.executeQuery();
	        		while (rs.next())
	        		{
	        			tenSP.setEditable(true);
	        			MaDM.setEditable(false);
	        			//tenDM.setText(rs.getString(3));
	        			QuocGia.setEditable(false);
	        			
	        			maSP.setEditable(true);
	        			SoLuong.setEditable(true);
	        			DonGia.setEditable(true);
	        			Trang_thai.setEditable(false);
	        			      			
	        			tenSP.setText(rs.getString(1));
	        			QuocGia.setSelectedItem(rs.getString(4));
	        			
	        			maSP.setText(rs.getString(5));
	        			SoLuong.setText(rs.getString(6));
	        			DonGia.setText(rs.getString(7));
	        			Trang_thai.setSelectedItem(rs.getString(8));
	        			MaDM.setSelectedItem(rs.getString(2));
	        			foundrec = 1;
	        		}
	        		if (foundrec ==0)
	        		{
	        			JOptionPane.showMessageDialog(null,"Sản phẩm không tồn tại, vui lòng kiểm tra lại thông tin","Dialogs",JOptionPane.WARNING_MESSAGE);
	        		}
	        		//con.close();
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
		else if(ae.getSource()==b_clear)
		{   
			tenSP.setText("");tenSP.setEditable(false);
			MaDM.setSelectedItem("---");MaDM.setEditable(false);
			tenDM.setText("");tenDM.setEditable(false);
			QuocGia.setSelectedItem("---");QuocGia.setEditable(false);
			maSP.setText("");
			SoLuong.setText("");SoLuong.setEditable(false);
			DonGia.setText("");DonGia.setEditable(false);
			Trang_thai.setSelectedItem("---");Trang_thai.setEditable(false);
			
		}
		//*
		else if(ae.getSource()==b_save)
		{
			try
	   		{
  				if((tenSP.getText()).equals("") || ma_dmuc.equals("---") || quoc_gia.equals("---") || (maSP.getText()).equals("") || (SoLuong.getText()).equals("") || (DonGia.getText()).equals("") || trang_thai.equals("---") )
  				{
  					JOptionPane.showMessageDialog(this,"* Vui lòng điền đủ thông tin !","Warning!!!",JOptionPane.WARNING_MESSAGE);
  				}
  				
  				else
  				{
  					//Class.forName("com.mysql.jdbc.Driver");
  					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
  					System.out.println("Connected to database.");
  					stmt=con.createStatement();
  					String str1="UPDATE mobileshop.sanpham SET tenSP='"+tenSP.getText()+"',MaDM='"+ma_dmuc+"',TenDM='"+tenDM.getText()+"',QuocGia='"+quoc_gia+"',MaSP='"+maSP.getText()+"',SoLuong='"+SoLuong.getText()+"',DonGia='"+DonGia.getText()+"',TrangThai='"+trang_thai+"' where MaSP='"+maSP.getText()+"'";
  					stmt.executeUpdate(str1);
  					JOptionPane.showMessageDialog(null, "Thông tin sản phẩm đã được cập nhật");
  					for(int c=model.getRowCount()-1;c>=0;c--)
	    			{
	    				model.removeRow(c);
	    			}
	    			rs = stmt.executeQuery("select * from mobileshop.sanpham");
	    			int r=0;
	    	          while(rs.next())
	    	            {
	    	        	  model.insertRow(r++,new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),});

	    	            }
  					tenSP.setText("");tenSP.setEditable(false);
  					MaDM.setSelectedItem("---");MaDM.setEditable(false);
  					tenDM.setText("");tenDM.setEditable(false);
  					QuocGia.setSelectedItem("---");QuocGia.setEditable(false);
  					maSP.setText("");
  					SoLuong.setText("");SoLuong.setEditable(false);
  					DonGia.setText("");DonGia.setEditable(false);
  					Trang_thai.setSelectedItem("---");Trang_thai.setEditable(false);
			
  					con.close();
  				}
	   		}
			catch(SQLException se)
			{
				System.out.println(se);System.out.println("V");
				JOptionPane.showMessageDialog(null,"SQL Error:"+se);
				
			}
			catch(Exception e)
			{
				System.out.println(e);
				JOptionPane.showMessageDialog(null,"Error:"+e);
			}
	   
		}
//*/
	}
	public static void main(String args[])
	{
	      new ChinhSuaThongTinSP();
	}
}
