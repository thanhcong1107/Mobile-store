package LoginForm;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Nhanvienbanhang extends JFrame implements ActionListener
{   JFrame jf;
	JMenuBar mbar;
	JMenu m1,m2,m3,m4,m5;
	JMenuItem m1_1,m1_2,m1_3,m1_4,m1_5,m2_1,m2_2,m2_3,m2_4,m2_5,m3_1,m3_2,m3_3,m4_1,m4_2,m5_1;
	JLabel l1;
	GridBagLayout gbl;

	public Nhanvienbanhang()
	{
        	jf=new JFrame();
		gbl=new GridBagLayout();
		jf.setLayout(gbl);

		l1=new JLabel("Phần mềm quản lý cửa hàng điện thoại di động");
		l1.setFont(new Font("Times New Roman",Font.BOLD,20));
		jf.add(l1);

		mbar = new JMenuBar();
		jf.setJMenuBar(mbar);

		m1=new JMenu("Sản phẩm");
		mbar.add(m1);
		m1_1 = new JMenuItem("Tìm kiếm sản phẩm",new ImageIcon("images//search.png"));
		m1.add(m1_1);
		m1_2 = new JMenuItem("Danh sách sản phẩm",new ImageIcon("images//all.png"));
		m1.add(m1_2);
		

		m2=new JMenu("Hóa đơn");
		mbar.add(m2);
		m2_1 = new JMenuItem("Tạo hóa đơn",new ImageIcon("images//addnew.png"));
		m2.add(m2_1);
		
		m2_2 = new JMenuItem("Hóa đơn bán hàng",new ImageIcon("images//report.png"));
		m2.add(m2_2);
		m2_3 = new JMenuItem("Chi tiết hóa đơn",new ImageIcon("images//report.png"));
		m2.add(m2_3);
		
		m5=new JMenu("Thoát");
		mbar.add(m5);
		m5_1 = new JMenuItem("Thoát",new ImageIcon("images//exit.png"));
		m5.add(m5_1);

        	m1_1.addActionListener(this);
		m1_2.addActionListener(this);

		m2_1.addActionListener(this);
		m2_2.addActionListener(this);
		m2_3.addActionListener(this);
		
		m5_1.addActionListener(this);

		jf.setTitle("Nhân viên bán hàng");
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
			new TimKiemSP();
		}
		else if(ae.getSource()==m1_2)
		{
			new DanhSachSP();
		}

		else if(ae.getSource()==m2_1)
		{
			new TaoHoaDon();
		}
		
		else if(ae.getSource()==m2_2)
		{
			new HoaDonBanHang();
		}
		
		else if(ae.getSource()==m2_3)
		{
			new ChiTietHoaDon();
		}
		
		else if (ae.getSource()==m5_1)
		{
			System.exit(0);
		}

	}

	public static void main(String args[])
	{
		new Nhanvienbanhang();
	}
}
