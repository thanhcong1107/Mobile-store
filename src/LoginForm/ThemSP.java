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

public class ThemSP extends JFrame implements ActionListener
{
	JFrame jf;
	JTextField tenSP,tenDM,DonGia,maSP,SoLuong,TrangThai;
	JLabel ten_SP,Ma_DM,Ten_DM,Quoc_Gia,Ma_SP,Don_Gia,So_Luong,Trang_Thai,ln;
    	JButton b_save,b_clear,b_all;
    	JComboBox MaDM,QuocGia,Trang_thai;//,tabtype;
    	String ma_dmuc,quoc_gia,tabt;
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

	ThemSP()
	{
		jf=new JFrame();
		f = new Font("Times New Roman",Font.BOLD,15);
		jf.setLayout(null);

	    	ln=new JLabel("Thêm sản phẩm mới");
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

		Ma_DM = new JLabel("Mã danh mục");
		Ma_DM.setBounds(50,140,150,25);
		jf.add(Ma_DM);

    		MaDM = new JComboBox();
		MaDM.setBounds(250,140,150,25);MaDM.setToolTipText("Điền ngày sinh theo định dạng DD/MM/YYYY");
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
						enDM.setEditable(false);
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
			}
	     	});
		//Lấy thông tin từ CSDL để thêm vào combobox
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
			System.out.println("Connected to database.");
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
		tenDM.setBounds(250,180,150,25);
		jf.add(tenDM);
		
	    
		
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
		maSP.setBounds(670,100,150,25);
		jf.add(maSP);
		
		So_Luong = new JLabel("Số lượng");
		So_Luong.setBounds(470,140,150,25);
    		jf.add(So_Luong);
    	
		SoLuong= new JTextField(20);
		SoLuong.setBounds(670,140,150,25);SoLuong.setToolTipText("Điền mã nhân viên, ví dụ Nguyễn Văn Anh, ngày sinh 01/01/2000 -> Mã NV : Anh.NV0101");
		jf.add(SoLuong);

		Don_Gia= new JLabel("Đơn giá");
		Don_Gia.setBounds(470,180,150,25);
    		jf.add(Don_Gia);

        	DonGia= new JTextField(20);
		DonGia.setBounds(670,180,150,25);DonGia.setToolTipText("Điền tài khoản đăng nhập hệ thống cho nhân viên, ví vụ Nguyễn Văn Anh, số điện thoại 0123456789 -> Tài khoản : Anh.NV6789");
		jf.add(DonGia);

		
		Trang_Thai= new JLabel("Trạng Thái");
		Trang_Thai.setBounds(470,220,150,25);
    		jf.add(Trang_Thai);
    	
    		TrangThai= new JTextField(20);
		TrangThai.setBounds(670,220,150,25);
		jf.add(TrangThai);
		TrangThai.setText("Đang kinh doanh");
		TrangThai.setEditable(false);

        	b_save = new JButton("Lưu",new ImageIcon("images//save.png"));
        	b_save.setBounds(150,330,110,35);b_save.setToolTipText("Click để lưu thông tin");
		jf.add(b_save);b_save.addActionListener(this);

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
        
        

	     	jf.setTitle("Thêm sản phẩm mới ");
	     	jf.setSize(900,700);
		jf.setLocation(20,20);
		jf.setResizable(false);
		jf.getContentPane().setBackground(Color.cyan);
	     	jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b_save)
		{
			try
			{
				int r=0;
				if((tenSP.getText()).equals("") || ma_dmuc.equals("---") || quoc_gia.equals("---") || (maSP.getText()).equals("") || (SoLuong.getText()).equals("") || (DonGia.getText()).equals(""))		
				{
					JOptionPane.showMessageDialog(this,"* Hãy điền đầy đủ thông tin !","Warning!!!",JOptionPane.WARNING_MESSAGE);
				}
				
	        	else
	        	{
	        		
	        		//Class.forName("com.mysql.jdbc.Driver");
	        		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
	        		
	        		ps=con.prepareStatement("insert into mobileshop.sanpham (TenSP,MaDM,tenDM,QuocGia,MaSP,SoLuong,DonGia,TrangThai)values(?,?,?,?,?,?,?,?)");

	        		ps.setString(1,tenSP.getText());
	        		ps.setString(2,(String)MaDM.getSelectedItem());
	        		ps.setString(3,tenDM.getText());
	        		ps.setString(4,(String)QuocGia.getSelectedItem());
	        		ps.setString(5,maSP.getText());
	        		ps.setString(6,SoLuong.getText());
	        		ps.setString(7,DonGia.getText());
	        		ps.setString(8,TrangThai.getText());
	        		
	        		ps.executeUpdate();
	        		model.insertRow(r++, new Object[]{tenSP.getText(),ma_dmuc,tenDM.getText(),(String)QuocGia.getSelectedItem(),maSP.getText(),SoLuong.getText(),DonGia.getText(),TrangThai.getText() });
	        		int reply=JOptionPane.showConfirmDialog(null,"Thêm sản phẩm thành công.Bạn có muốn thêm 1 sản phẩm khác?","Thêm sản phẩm",JOptionPane.YES_NO_OPTION);

	        		if (reply == JOptionPane.YES_OPTION)
	        		{
	        			tenSP.setText("");
	        			MaDM.setSelectedItem("---");
	        			tenDM.setText("");
	        			QuocGia.setSelectedItem("---");
	        			maSP.setText("");
	        			SoLuong.setText("");
	        			DonGia.setText("");
	        			TrangThai.setText("Đang kinh doanh");
	        		}
	        		else if (reply == JOptionPane.NO_OPTION)
	        		{
	        			jf.setVisible(false);
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
 
		else if(ae.getSource()==b_clear)
		{   
			tenSP.setText("");
			MaDM.setSelectedItem("---");
			QuocGia.setSelectedItem("---");
			maSP.setText("");
			SoLuong.setText("");
			DonGia.setText("");
			TrangThai.setText("Đang kinh doanh");
			
			

		}

	}

	public static void main(String args[])
	{
	      new ThemSP();
	}
}
