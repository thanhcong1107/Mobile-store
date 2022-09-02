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

public class ThemNhanVien extends JFrame implements ActionListener
{
	JFrame jf;
	JTextField ten,ngay_sinh,sdt,dia_chi,ma_NV,ma_CV,tai_khoan,mat_khau;
	JLabel name,date,Sdt,address,ma_nv,chuc_vu,ma_chuc_vu,account,password,gioi_tinh,ln;
    JButton b_save,b_clear;
    JComboBox ChucVu,GioiTinh;
    String s,sid1,Chuc_Vu,tabt,sex;
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

	ThemNhanVien()
	{
		jf=new JFrame();
		f = new Font("Times New Roman",Font.BOLD,15);
		jf.setLayout(null);

	    ln=new JLabel("Thêm nhân viên mới");
	    ln.setFont(new Font("Times New Roman",Font.BOLD,25));
	    ln.setForeground(Color.blue);
	    ln.setBounds(300,30,400,40);
	    jf.add(ln);

		name = new JLabel("Tên nhân viên");
		name.setBounds(50,100,150,25);
		jf.add(name);

		ten = new JTextField(20);
		ten.setBounds(250,100,150,25);ten.setToolTipText("Điền tên nhân viên");
		jf.add(ten);

		date = new JLabel("Ngày sinh");
		date.setBounds(50,140,150,25);
		jf.add(date);

    	ngay_sinh = new JTextField(20);
		ngay_sinh.setBounds(250,140,150,25);ngay_sinh.setToolTipText("Điền ngày sinh theo định dạng DD/MM/YYYY");
		jf.add(ngay_sinh);

		Sdt = new JLabel("Số điện thoại");
		Sdt.setBounds(50,180,150,25);
		jf.add(Sdt);

     	sdt = new JTextField(20);
		sdt.setBounds(250,180,150,25);sdt.setToolTipText("Điền số điện thoại");
		jf.add(sdt);

		address = new JLabel("Địa chỉ");
		address.setBounds(50,220,150,25);
    	jf.add(address);

        dia_chi= new JTextField(20);
		dia_chi.setBounds(250,220,150,25);dia_chi.setToolTipText("Điền địa chỉ ");
		jf.add(dia_chi);
		
		gioi_tinh = new JLabel("Giới tính");
		gioi_tinh.setBounds(50,260,150,25);
    	jf.add(gioi_tinh);
    	
    	GioiTinh=new JComboBox();
    	GioiTinh.setBounds(250,260,150,25);
    	jf.add(GioiTinh);
        GioiTinh.addItem("---");
		GioiTinh.addItem("Nam");
		GioiTinh.addItem("Nữ");
		GioiTinh.addActionListener(new ActionListener()
	    {
		   public void actionPerformed(ActionEvent ae)
		   {
			   sex =(String)GioiTinh.getSelectedItem();
			   
               
		   }
        });
		

//Cột 2
		ma_nv = new JLabel("Mã nhân viên");
		ma_nv.setBounds(470,100,150,25);
    	jf.add(ma_nv);
    	
		ma_NV= new JTextField(20);
		ma_NV.setBounds(670,100,150,25);ma_NV.setToolTipText("Điền mã nhân viên, ví dụ Nguyễn Văn Anh, ngày sinh 01/01/2000 -> Mã NV : Anh.NV0101");
		jf.add(ma_NV);
		

		account= new JLabel("Tài khoản");
		account.setBounds(470,220,150,25);
    	jf.add(account);

        tai_khoan= new JTextField(20); 
		tai_khoan.setBounds(670,220,150,25);tai_khoan.setToolTipText("Điền tài khoản đăng nhập hệ thống cho nhân viên, ví vụ Nguyễn Văn Anh, số điện thoại 0123456789 -> Tài khoản : Anh.NV6789");
		jf.add(tai_khoan);

		
		password= new JLabel("Mật khẩu");
		password.setBounds(470,260,150,25);
    	jf.add(password);
    	
    	mat_khau= new JTextField(20);
		mat_khau.setBounds(670,260,100,25);mat_khau.setToolTipText("Điền mật khẩu đăng nhập hệ thống cho nhân viên, ví vụ Nguyễn Văn Anh, ngày sinh 01/01/2000 -> Mật khẩu: Anh0101");
		jf.add(mat_khau);

		ma_chuc_vu= new JLabel("Mã chức vụ");
		ma_chuc_vu.setBounds(470,180,150,25);
    	jf.add(ma_chuc_vu);
    	
		ma_CV= new JTextField(20);
		ma_CV.setBounds(670,180,150,25);
		jf.add(ma_CV);
		
		chuc_vu= new JLabel("Chức vụ");
		chuc_vu.setBounds(470,140,150,25);
    	jf.add(chuc_vu);
    	
        ChucVu=new JComboBox();
        ChucVu.addItem("---");
        ChucVu.addItem("Quản lý");
		ChucVu.addItem("Nhân viên bán hàng");
		
		
		ChucVu.setBounds(670,140,150,25);ChucVu.setToolTipText("Chọn chức vụ");
		jf.add(ChucVu);
		ChucVu.addActionListener(new ActionListener()
	    {
		   public void actionPerformed(ActionEvent ae)
		   {
			   Chuc_Vu =(String)ChucVu.getSelectedItem();
			   if (Chuc_Vu.equalsIgnoreCase("Quản lý")) {
                   ma_CV.setText("QL");
                   ma_CV.setEditable(false);
               }
			   else if (Chuc_Vu.equalsIgnoreCase("Nhân viên bán hàng")) {
                   ma_CV.setText("NVBH");
                   ma_CV.setEditable(false);
               }
			   
		   }
        });

        b_save = new JButton("Lưu",new ImageIcon("images//save.png"));
        b_save.setBounds(150,330,110,35);b_save.setToolTipText("Click để lưu thông tin");
		jf.add(b_save);b_save.addActionListener(this);

		b_clear = new JButton("Làm mới",new ImageIcon("images//clear.png"));
		b_clear.setBounds(550,330,110,35);b_clear.setToolTipText("Click để xóa toàn bộ thông tin đã điền");
	    jf.add(b_clear); b_clear.addActionListener(this);

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

	     jf.setTitle("Thêm nhân viên mới ");
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
				if(((ten.getText()).isEmpty())||((ngay_sinh.getText()).isEmpty())||((sdt.getText()).isEmpty())||((dia_chi.getText()).isEmpty())||((ma_NV.getText()).isEmpty())||((tai_khoan.getText()).isEmpty())||
						((mat_khau.getText()).equals("")) || sex.equals("---") || Chuc_Vu.equals("---") )
				{
					JOptionPane.showMessageDialog(this,"* Hãy điền đầy đủ thông tin !","Warning!!!",JOptionPane.WARNING_MESSAGE);
				}
				
	        	else
	        	{
	        		
	        		//Class.forName("com.mysql.jdbc.Driver");
	        		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
	        		
	        		ps=con.prepareStatement("insert into mobileshop.nhanvien (name,NgaySinh,SDT,DiaChi,MaNV,ChucVu,MaChucVu,TaiKhoan,MatKhau,GioiTinh)values(?,?,?,?,?,?,?,?,?,?)");

	        		ps.setString(1,ten.getText());
	        		ps.setString(2,ngay_sinh.getText());
	        		ps.setString(3,sdt.getText());
	        		ps.setString(4,dia_chi.getText());
	        		ps.setString(5,ma_NV.getText());
	        		ps.setString(6,Chuc_Vu);
	        		ps.setString(7,ma_CV.getText());
	        		ps.setString(8,tai_khoan.getText());
	        		ps.setString(9,mat_khau.getText());
	        		ps.setString(10,sex);
	        		
	        		ps.executeUpdate();
	        		model.insertRow(r++, new Object[]{ten.getText(),ngay_sinh.getText(),sdt.getText(),dia_chi.getText(),ma_NV.getText(),ChucVu.getSelectedItem(),ma_CV.getText(),tai_khoan.getText(),mat_khau.getText(),sex });
	        		int reply=JOptionPane.showConfirmDialog(null,"Thêm nhân viên thành công.Bạn có muốn thêm 1 nhân viên khác?","Thêm nhân viên",JOptionPane.YES_NO_OPTION);

	        		if (reply == JOptionPane.YES_OPTION)
	        		{
	        			ten.setText("");
	        			ngay_sinh.setText("");
	        			sdt.setText("");
	        			dia_chi.setText("");
	        			ma_NV.setText("");
	        			ChucVu.setSelectedItem("---");
	        			ma_CV.setText("");
	        			tai_khoan.setText("");
	        			mat_khau.setText("");
	        			GioiTinh.setSelectedItem("---");
	        			
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
		{   ten.setText("");
			ngay_sinh.setText("");
			sdt.setText("");
			dia_chi.setText("");
			ma_NV.setText("");
			ChucVu.setSelectedItem("---");
			ma_CV.setText("");
			tai_khoan.setText("");
			mat_khau.setText("");
			GioiTinh.setSelectedItem("---");

		}

	}
	
	public static void main(String args[])
	{
	      new ThemNhanVien();
	}
}
