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

public class TaoHoaDon extends JFrame implements ActionListener
{
	JFrame jf;
	JTextField Ma_HD,Ten_KH,SDT_KH,Ten_NV,Ngay_Lap,Ten_SP,So_Luong,Tong_Tien;
	JLabel MaHD,TenKH,SDTKH,MaNV,TenNV,NgayLap,MaSP,TenSP,SoLuong,TongTien,VND,ln;
	JButton b_add,b_cancel,b_save, b_print;
	JComboBox Ma_SP,Ma_NV;
    String ma_sp,ma_nv,sid1,Chuc_Vu,sex,tabt;
    int Gtri_HD=0;
	Font f;
	Date date1,date2;
    GregorianCalendar calendar,calendar1;
    String strDate1,strDate2;
    Connection con;
	PreparedStatement ps,ps1,ps2;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel model = new DefaultTableModel() {

	    @Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }
	};


    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
  
	TaoHoaDon()
	{
		jf=new JFrame();
		f = new Font("Times New Roman",Font.BOLD,15);
		jf.setLayout(null);

	    ln=new JLabel("Hóa đơn bán hàng");
	    ln.setFont(new Font("Times New Roman",Font.BOLD,25));
	    ln.setForeground(Color.blue);
	    ln.setBounds(300,10,400,40);
	    jf.add(ln);

		MaHD = new JLabel("Mã hóa đơn");
		MaHD.setBounds(50,50,150,25);
		jf.add(MaHD);

		Ma_HD = new JTextField(20);
		Ma_HD.setBounds(200,50,150,25);
		jf.add(Ma_HD);
		date2= new Date();
     	calendar1=new GregorianCalendar();
	    calendar1.setTime(date2);
	    int AM_PM = calendar1.get(Calendar.AM_PM);
	    String Am_Pm;
	    if(AM_PM==0) { Am_Pm ="AM";}
	    else { Am_Pm ="PM";}
        strDate1 =calendar1.get(Calendar.DATE)+"_"+(calendar1.get(Calendar.MONTH)+1)+"_"+calendar1.get(Calendar.YEAR)+"_"+calendar1.get(Calendar.HOUR)+":"+calendar1.get(Calendar.MINUTE)+":"+calendar1.get(Calendar.SECOND)+Am_Pm;
	    Ma_HD.setText(strDate1);Ma_HD.setEditable(false);

		TenKH = new JLabel("Tên khách hàng");
		TenKH.setBounds(50,90,150,25);
		jf.add(TenKH);
		
    	Ten_KH = new JTextField(20);
		Ten_KH.setBounds(200,90,150,25);Ten_KH.setToolTipText("Điền tên khách hàng");
		jf.add(Ten_KH);
		
		SDTKH = new JLabel("Số điện thoại KH");
		SDTKH.setBounds(50,130,150,25);
		jf.add(SDTKH);

     	SDT_KH = new JTextField(20);
		SDT_KH.setBounds(200,130,150,25);SDT_KH.setToolTipText("Điền số điện thoại");
		jf.add(SDT_KH);

		
//Cột 2
		MaNV = new JLabel("Mã nhân viên");
		MaNV.setBounds(450,50,150,25);
    	jf.add(MaNV);
    	
		Ma_NV= new JComboBox();
		Ma_NV.setBounds(600,50,150,25);Ma_NV.setToolTipText("Điền mã nhân viên");
		jf.add(Ma_NV);
		Ma_NV.addItem("---");
		Ma_NV.addActionListener(new ActionListener()
	    {
		   public void actionPerformed(ActionEvent ae)
		   {
			   ma_nv =(String)Ma_NV.getSelectedItem();
			   try {	
				   //Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
					System.out.println("Connected to database.");
					ps=con.prepareStatement("select name from mobileshop.nhanvien where MaNV ='"+ma_nv+"'");
					rs=ps.executeQuery();
					while(rs.next())
					{
						
						Ten_NV.setText(rs.getString(1));
						
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
//lấy toàn bộ mã sản phẩm trong CSDL đưa vào combo box
		try {	
			   //Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
				System.out.println("Connected to database.");
				ps=con.prepareStatement("select MaNV from mobileshop.nhanvien where MaChucVu = 'NVBH'");
				rs=ps.executeQuery();
				while(rs.next())
				{
					
					Ma_NV.addItem(rs.getString(1));			
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
		

		TenNV= new JLabel("Tên nhân viên");
		TenNV.setBounds(450,90,150,25);
    	jf.add(TenNV);

        Ten_NV= new JTextField(20);
		Ten_NV.setBounds(600,90,150,25);Ten_NV.setToolTipText("Tên nhân viên tạo hóa đơn");
		jf.add(Ten_NV);Ten_NV.setEditable(false);

		NgayLap= new JLabel("Ngày lập hóa đơn");
		NgayLap.setBounds(450,130,150,25);
    	jf.add(NgayLap);
    	
    	Ngay_Lap= new JTextField(20);
		Ngay_Lap.setBounds(600,130,150,25);
		jf.add(Ngay_Lap);
		date1= new Date();
     	calendar=new GregorianCalendar();
	    calendar.setTime(date1);
        strDate1 =calendar.get(Calendar.DATE)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
	    Ngay_Lap.setText(strDate1);Ngay_Lap.setEditable(false);

//hàng nhập sản phẩm để thêm vào hóa đơn	
		MaSP= new JLabel("Mã sản phẩm");
		MaSP.setBounds(30,170,150,25);
    	jf.add(MaSP);
    	
        Ma_SP=new JComboBox();
        Ma_SP.addItem("---");
		Ma_SP.setBounds(130,170,120,25);Ma_SP.setToolTipText("Chọn mã sản phẩm");
		jf.add(Ma_SP);
		Ma_SP.addActionListener(new ActionListener()
	    {
		   public void actionPerformed(ActionEvent ae)
		   {
			   ma_sp =(String)Ma_SP.getSelectedItem();
			   try {	
				   //Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
					System.out.println("Connected to database.");
					ps=con.prepareStatement("select tenSP from mobileshop.sanpham where MaSP='"+ma_sp+"'");
					rs=ps.executeQuery();
					while(rs.next())
					{
						
						Ten_SP.setText(rs.getString(1));
						
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
//lấy toàn bộ mã sản phẩm trong CSDL đưa vào combo box
		try {	
			   //Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
				System.out.println("Connected to database.");
				ps=con.prepareStatement("select MaSP from mobileshop.sanpham where SoLuong>0");
				rs=ps.executeQuery();
				while(rs.next())
				{
					
					Ma_SP.addItem(rs.getString(1));			
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
		TenSP= new JLabel("Tên sản phẩm");
		TenSP.setBounds(300,170,100,25);
    	jf.add(TenSP);
    	
    	Ten_SP= new JTextField(20);
		Ten_SP.setBounds(400,170,150,25);Ten_SP.setToolTipText("");
		jf.add(Ten_SP);Ten_SP.setEditable(false);
		
		SoLuong= new JLabel("Số lượng");
		SoLuong.setBounds(600,170,100,25);
    	jf.add(SoLuong);
    	
    	So_Luong= new JTextField(20);
		So_Luong.setBounds(700,170,50,25);So_Luong.setToolTipText("");
		jf.add(So_Luong);
//
//hàng hiện thị giá trị hóa đơn
		TongTien= new JLabel("Tổng tiền");
		TongTien.setBounds(500,475,75,25);
    	jf.add(TongTien);
    	
    	Tong_Tien= new JTextField(20);
		Tong_Tien.setBounds(570,475,150,25);Tong_Tien.setToolTipText("");
		jf.add(Tong_Tien);Tong_Tien.setEditable(false);
		
		VND= new JLabel("VND");
		VND.setBounds(730,475,75,25);
    	jf.add(VND);
		
		b_add = new JButton(new ImageIcon("images//Add.png"));
        b_add.setBounds(770,170,50,25);b_add.setToolTipText("Click để thêm sản phẩm vào hóa đơn");
		jf.add(b_add);b_add.addActionListener(this);
		
		b_cancel = new JButton("Hủy",new ImageIcon("images//delete.png"));
        b_cancel.setBounds(50,550,100,25);b_cancel.setToolTipText("Click để hủy hóa đơn");
		jf.add(b_cancel);b_cancel.addActionListener(this);

		b_save = new JButton("Lưu",new ImageIcon("images//save.png"));
		b_save.setBounds(250,550,100,25);b_save.setToolTipText("Click để lưu hóa đơn");
	    jf.add(b_save); b_save.addActionListener(this);
	    
	    b_print = new JButton("In",new ImageIcon("images//print.png"));
		b_print.setBounds(450,550,100,25);b_print.setToolTipText("Click để in hóa đơn");
	    jf.add(b_print); b_print.addActionListener(this);

	    scrlPane.setBounds(0,250,900,200);
        jf.add(scrlPane);
        tabGrid.setFont(new Font ("Times New Roman",0,15));

        model.addColumn("Tên sản phẩm");
        model.addColumn("Mã sản phẩm");
        model.addColumn("Số lượng");
        model.addColumn("Thành tiền");
        

	     jf.setTitle("Hóa đơn bán hàng");
	     jf.setSize(900,700);
		 jf.setLocation(20,20);
		 jf.setResizable(false);
		 jf.getContentPane().setBackground(Color.cyan);
	     jf.setVisible(true);
 }

 public void actionPerformed(ActionEvent ae)
 	{
  		if(ae.getSource()==b_add)
  		{
			try {
				
				if ( ((String)(Ma_SP.getSelectedItem())).equals("---") || (So_Luong.getText()).equals(""))
				{
					JOptionPane.showMessageDialog(this, "Thiếu thông tin");
				}
				else
				{
				
				String masp = (String)Ma_SP.getSelectedItem();
				
				int f = Integer.parseInt(So_Luong.getText());
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
				System.out.println("Connected to database.");
				ps=con.prepareStatement("select * from mobileshop.sanpham where MaSP='" + masp + "'");
				rs=ps.executeQuery();

				if (rs.next()) {

					int c = rs.getInt(6);
					int gtri_row = f* rs.getInt(7);
					if (c >= f) {

						

						Object[] row = new Object[4];
						row[0] = Ten_SP.getText();
						row[1] = String.valueOf(Ma_SP.getSelectedItem());
						row[2] = So_Luong.getText();
						row[3] = gtri_row;

						model.addRow(row);
						Gtri_HD = Gtri_HD + gtri_row;
						Tong_Tien.setText(Integer.toString(Gtri_HD));
						Ma_SP.setSelectedItem("---");
						Ten_SP.setText("");
						So_Luong.setText("");
					}

					else {
						JOptionPane.showMessageDialog(this, "Số lượng máy không đủ");
					}

				}
				
				con.close();
			}
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
  		else if(ae.getSource()==b_cancel)
  		{
  			jf.dispose();  		}
  		else if(ae.getSource()==b_save)
  		{    
  			try
  			{
  				if ((Ten_KH.getText()).isEmpty() || (SDT_KH.getText()).isEmpty() || ((String)Ma_NV.getSelectedItem()).equals("---") || model.getRowCount() <=0 )
  				{
  					JOptionPane.showMessageDialog(this, "Hóa đơn không hợp lệ");
  				}
  				else
  				{
  					
  					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
	        		System.out.println("Connected to database.");
	        		Date date = new Date();
	        		java.sql.Date sqldate = new java.sql.Date(date.getTime());
	        		ps=con.prepareStatement("insert into mobileshop.hoadon (MaHD,MaNV,TenKH,SDT_KH,NgayLap,TongTien)values(?,?,?,?,?,?)");
	        		ps.setString(1,Ma_HD.getText());
	        		ps.setString(2,(String)Ma_NV.getSelectedItem());
	        		ps.setString(3,Ten_KH.getText());
	        		ps.setString(4,SDT_KH.getText());
	        		ps.setDate(5,sqldate);
	        		ps.setInt(6,Gtri_HD);
	        		ps.executeUpdate();
	        		
	        		System.out.println("Thêm hóa đơn thành công");
	        		for (int i=0;i<model.getRowCount();i++)
	        		{
	        			String masp = model.getValueAt(i, 1).toString();
	        			String soluong =model.getValueAt(i, 2).toString();
	        			int num = Integer.parseInt(soluong);
	        			String tongtien = model.getValueAt(i, 3).toString();
	        			int money = Integer.parseInt(tongtien);
	        			//lưu chi tiết hóa đơn
	        			{
	        				ps1= con.prepareStatement("insert into mobileshop.chitiethoadon (MaHD,MaSP,SoLuong,TongTien) values(?,?,?,?)");
	        				ps1.setString(1,Ma_HD.getText());
	        				ps1.setString(2,masp);
	        				ps1.setInt(3,num);
	        				ps1.setInt(4,money);
	        				ps1.executeUpdate();
	        			}
	        			
	        			
	        		}
	        		JOptionPane.showMessageDialog(null, "Hóa đơn đã được lưu");
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
 public static void main(String args[])
 {
  new TaoHoaDon();
 }}

