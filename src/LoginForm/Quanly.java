package LoginForm;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Quanly extends JFrame implements ActionListener
{   
	JFrame jf;
	JMenuBar mbar;
	JMenu m1,m2,m3,m4,m5;
	JMenuItem m1_1,m1_2,m1_3,m1_4,m1_5,m2_1,m2_2,m2_3,m2_4,m2_5,m3_1,m3_2,m3_3,m4_1,m4_2,m5_1;
	JLabel l1;
	GridBagLayout gbl;

	public Quanly()
	{
        	jf=new JFrame();
		gbl=new GridBagLayout();
		jf.setLayout(gbl);

		l1=new JLabel("Phần mềm quản lý cửa hàng điện thoại di động");
		l1.setFont(new Font("Times New Roman",Font.BOLD,20));
		jf.add(l1);

		mbar = new JMenuBar();
		jf.setJMenuBar(mbar);

		m1=new JMenu("Quản lý nhân viên");
		mbar.add(m1);
		m1_1 = new JMenuItem("Thêm nhân viên",new ImageIcon("images//addnew.png"));
		m1.add(m1_1);
		m1_2 = new JMenuItem("Tìm kiếm nhân viên",new ImageIcon("images//search.png"));
		m1.add(m1_2);
		m1_3 = new JMenuItem("Chỉnh sửa thông tin",new ImageIcon("images//update.png"));
		m1.add(m1_3);
		m1_4 = new JMenuItem("Xóa nhân viên",new ImageIcon("images//delete.png"));
		m1.add(m1_4);
	    	m1_5 = new JMenuItem("Danh sách nhân viên",new ImageIcon("images//list.png"));
		m1.add(m1_5);

		m2=new JMenu("Quản lý sản phẩm");
		mbar.add(m2);
		m2_1 = new JMenuItem("Thêm 1 sản phẩm",new ImageIcon("images//addnew.png"));
		m2.add(m2_1);
		m2_2 = new JMenuItem("Tìm kiếm sản phẩm",new ImageIcon("images//search.png"));
		m2.add(m2_2);
		m2_3 = new JMenuItem("Chỉnh sửa thông tin sản phẩm",new ImageIcon("images//update.png"));
		m2.add(m2_3);
		m2_4 = new JMenuItem("Xóa sản phẩm",new ImageIcon("images//delete.png"));
		m2.add(m2_4);
	    	m2_5 = new JMenuItem("Danh sách sản phẩm",new ImageIcon("images//list.png"));
		m2.add(m2_5);


		m3=new JMenu("Quản lý danh mục");
	    	mbar.add(m3);
	    	m3_1 = new JMenuItem("Thêm danh mục",new ImageIcon("images//addnew.png"));
		m3.add(m3_1);
		m3_2 = new JMenuItem("Chỉnh sửa thông tin danh mục",new ImageIcon("images//update.png"));
		m3.add(m3_2);
		m3_3 = new JMenuItem("Xóa danh mục",new ImageIcon("images//delete.png"));
		m3.add(m3_3);

		m4=new JMenu("Hóa đơn");
		mbar.add(m4);
		m4_1 = new JMenuItem("Hóa đơn bán hàng",new ImageIcon("images//report.png"));
		m4.add(m4_1);
		

		m5=new JMenu("Thoát");
		mbar.add(m5);
		m5_1 = new JMenuItem("Thoát",new ImageIcon("images//exit.png"));
		m5.add(m5_1);

        	m1_1.addActionListener(this);
		m1_2.addActionListener(this);
		m1_3.addActionListener(this);
		m1_4.addActionListener(this);
    		m1_5.addActionListener(this);

		m2_1.addActionListener(this);
		m2_2.addActionListener(this);
		m2_3.addActionListener(this);
		m2_4.addActionListener(this);
	    	m2_5.addActionListener(this);

		m3_1.addActionListener(this);
		m3_2.addActionListener(this);
		m3_3.addActionListener(this);
		
		m4_1.addActionListener(this);
		
		
		m5_1.addActionListener(this);

		jf.setTitle("Quản lý");
		jf.setLocation(20,20);
	    	jf.setSize(900,700);
	    	jf.setResizable(true);
		jf.getContentPane().setBackground(Color.cyan);
		jf.setVisible(true);

	}

	public void actionPerformed(ActionEvent ae)
	{

		if(ae.getSource()==m1_1)
		{
			new ThemNhanVien();
		}
		else if(ae.getSource()==m1_2)
		{
			new TimKiemNhanVien();
		}
		else if(ae.getSource()==m1_3)
		{
			new ChinhSuaThongTin();
		}
		else if(ae.getSource()==m1_4)
		{
			new XoaNhanVien();
		}
	    	else if(ae.getSource()==m1_5)
		{
	    	new HienThiDanhSach();
		}


		else if(ae.getSource()==m2_1)
		{
			new ThemSP();
		}
		else if(ae.getSource()==m2_2)
		{
			new TimKiemSP();
		}
		else if(ae.getSource()==m2_3)
		{
			new ChinhSuaThongTinSP();
		}
		else if(ae.getSource()==m2_4)
		{
			new XoaSP();
		}
		else if(ae.getSource()==m2_5)
		{
			new DanhSachSP();
		}

		else if(ae.getSource()==m3_1)
		{
			new ThemDM();
		}

		else if(ae.getSource()==m3_2)
		{
			new ChinhSuaDM();
		}
		
		else if(ae.getSource()==m3_3)
		{
			new XoaDM();
		}

		else if(ae.getSource()==m4_1)
		{
	        new HoaDonBanHang();
		}
		

		else if (ae.getSource()==m5_1)
		{
			System.exit(0);
		}

	}

	public static void main(String args[])
	{
		new Quanly();
	}
}
