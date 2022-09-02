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

public class ChiTietHoaDon extends JFrame 
{
	JFrame jf;
	JTextField Ten_KH,SDT_KH,Ten_NV,Ma_NV,Ngay_Lap,Ma_SP,Ten_SP,So_Luong,Tong_Tien;
	JLabel MaHD,TenKH,SDTKH,MaNV,TenNV,NgayLap,MaSP,TenSP,SoLuong,TongTien,VND,ln;
	JButton b_open,b_cancel,b_save, b_print;
	JComboBox Ma_HD;
    	String ma_sp,ma_nv,sid1,Chuc_Vu,sex,tabt;
    	int Gtri_HD=0;
	Font f;
	
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

	//table.setModel(model);
    	JTable tabGrid = new JTable(model);
    	JScrollPane scrlPane = new JScrollPane(tabGrid);
    
	ChiTietHoaDon()
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

		Ma_HD = new JComboBox();
		Ma_HD.setBounds(200,50,150,25);
		jf.add(Ma_HD);
		Ma_HD.addItem("---");
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
			System.out.println("Connected to database1.");
			ps=con.prepareStatement("select MaHD from mobileshop.hoadon");
			rs=ps.executeQuery();
			while(rs.next())
			{
				String mahd=rs.getString(1);
				Ma_HD.addItem(mahd);
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
		Ma_HD.addActionListener(new ActionListener()
     		{
			public void actionPerformed(ActionEvent ae)
			{
				String mahd =(String)Ma_HD.getSelectedItem();
				String manv="";
				try 
				{	
					//Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
					System.out.println("Connected to database.");
					ps=con.prepareStatement("select* from mobileshop.hoadon where MaHD ='"+mahd+"'");
					rs=ps.executeQuery();
					while(rs.next())
					{
							
						Ma_NV.setText(rs.getString(2));manv = rs.getString(2);
						Ten_KH.setText(rs.getString(3));
						SDT_KH.setText(rs.getString(4));
						Ngay_Lap.setText(rs.getString(5));
						Tong_Tien.setText(rs.getString(6));
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
				int r=0;
				try
			     	{

			     		//Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
					System.out.println("Connected to database.");
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			       	 	rs = stmt.executeQuery("select * from mobileshop.chitiethoadon where MaHD = '"+mahd+"'");
			         	while(rs.next())
			            	{
			            		model.insertRow(r++,new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});

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

				try
				{
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
					System.out.println("Connected to database.");
					ps=con.prepareStatement("select* from mobileshop.nhanvien where MaNV ='"+manv+"'");
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

		TenKH = new JLabel("Tên khách hàng");
		TenKH.setBounds(50,90,150,25);
		jf.add(TenKH);
		
    		Ten_KH = new JTextField(20);
		Ten_KH.setBounds(200,90,150,25);Ten_KH.setToolTipText("Điền tên khách hàng");
		jf.add(Ten_KH);Ten_KH.setEditable(false);
		
		SDTKH = new JLabel("Số điện thoại KH");
		SDTKH.setBounds(50,130,150,25);
		jf.add(SDTKH);

     		SDT_KH = new JTextField(20);
		SDT_KH.setBounds(200,130,150,25);SDT_KH.setToolTipText("Điền số điện thoại");
		jf.add(SDT_KH);
		SDT_KH.setEditable(false);

		
//Cột 2
		MaNV = new JLabel("Mã nhân viên");
		MaNV.setBounds(450,50,150,25);
    		jf.add(MaNV);
    	
		Ma_NV= new JTextField();
		Ma_NV.setBounds(600,50,150,25);Ma_NV.setToolTipText("Điền mã nhân viên");
		jf.add(Ma_NV);Ma_NV.setEditable(false);
		
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
		jf.add(Ngay_Lap);Ngay_Lap.setEditable(false);
		


		TongTien= new JLabel("Tổng tiền");
		TongTien.setBounds(500,475,75,25);
    		jf.add(TongTien);
    	
    		Tong_Tien= new JTextField(20);
		Tong_Tien.setBounds(570,475,150,25);Tong_Tien.setToolTipText("");
		jf.add(Tong_Tien);Tong_Tien.setEditable(false);
		
		VND= new JLabel("VND");
		VND.setBounds(730,475,75,25);
    		jf.add(VND);
    	
    	
		
		
         
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

 
 public static void main(String args[])
 	{
	 	new ChiTietHoaDon();
 	}	
}


