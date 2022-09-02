package LoginForm;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class InvalidException extends Exception
{
}

class Login extends JFrame implements ActionListener
{
	JFrame Login;
	JButton b_login,b_clear,b_exit;
	JLabel l1,l2,l3,l4,l5,l6;
	JTextField username;
	JPasswordField password;
	Font f;
	int cnt=0,cnt1=0;

	Login()
	{
		//ImageIcon img = new ImageIcon(getClass().getResource("images//cuahangdienthoai.png"));
		Login=new JFrame();
        f = new Font("Times New Roman",Font.BOLD,20);
		Login.setLayout(null);
        l5 = new JLabel("Cừa hàng quản lý điện thoại");l5.setFont(new Font("Times New Roman",Font.BOLD,20));
		
		
        l5.setBounds(250,20,300,100);
		Login.add(l5);

		l3 = new JLabel(new ImageIcon("images//users.png"));
		l3.setBounds(150,250,50,25);
		Login.add(l3);

		l1 = new JLabel("Tên đăng nhập : "); l1.setFont(f);
		l1.setBounds(200,250,200,25);
		Login.add(l1);

		username = new JTextField(20);
		username.setBounds(350,250,200,25);
		username.setToolTipText("Enter Username");
		Login.add(username);

        l4 = new JLabel(new ImageIcon("images//pass.png"));
		l4.setBounds(150,300,50,25);
		Login.add(l4);

		l2 = new JLabel("Mật khẩu  : "); l2.setFont(f);
		l2.setBounds(200,300,200,25);
		Login.add(l2);

		password = new JPasswordField(20);
		password.setBounds(350,300,200,25);
		password.setToolTipText("Enter Password");
		Login.add(password);

		b_login = new JButton("Login",new ImageIcon("images//Login.png"));
		b_login.setBounds(200,400,100,35);
		Login.add(b_login);b_login.addActionListener(this);

		b_clear = new JButton("Clear",new ImageIcon("images//clear.png"));
		b_clear.setBounds(320,400,100,35);
		Login.add(b_clear);b_clear.addActionListener(this);

		b_exit = new JButton("Exit",new ImageIcon("images//exit.png"));
		b_exit.setBounds(440,400,100,35);
		Login.add(b_exit);b_exit.addActionListener(this);

		Login.setTitle("Login");
		Login.setLocation(20,20);
		Login.setSize(800,600);
		Login.setResizable(true);
		Login.getContentPane().setBackground(Color.cyan);
		Login.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b_login)
		{
			try
			{
				String user_name =username.getText();
				String pass_word=new String(password.getPassword());
				
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop","root","thanhcong1107");
	    		System.out.println("Connected to database.");

	    		PreparedStatement ps=con.prepareStatement("select * from mobileshop.nhanvien where TaiKhoan='"+user_name+"' and MatKhau='"+pass_word+"'");
	    		ResultSet rs=ps.executeQuery();
                
                if (rs.next()) {
                	String tai_khoan = rs.getString(8);System.out.println(tai_khoan);
                	String mat_khau = rs.getString(9);System.out.println(mat_khau);
                	String chuc_vu = rs.getString(7);System.out.println(chuc_vu);
                	String role = rs.getString(7);
                	
                	if((user_name.equals(tai_khoan))&&(pass_word.equals(mat_khau)))
                	{
                	
                		if (role.compareTo("QL")==0)
                		{
                			//JOptionPane.showMessageDialog(null, "Login Quan ly Successfully");
                			new Quanly();
                		}
 
                		else if (role.compareTo("NVBH")==0)
                		{
                			//JOptionPane.showMessageDialog(null, "Login NVBH Successfully");
                			new Nhanvienbanhang();
                		}
                		
                	}
                }
                else {
                        
                		throw new InvalidException();
                    }
                	
                con.close();
			}
			catch(Exception e1)
			{
			    cnt++;
			    JOptionPane.showMessageDialog(null," Tài khoản hoặc mật khẩu sai! ","WARNING",JOptionPane.ERROR_MESSAGE);
			    username.setText("");
			    password.setText("");
			    if(cnt==3)
			    {
			         JOptionPane.showMessageDialog(null,"Xin lỗi !!! Bạn đã đăng nhập sai quá 3 lần!!!","WARNING",JOptionPane.ERROR_MESSAGE);
			         System.exit(0);
			    }
            }

		}

		else if(ae.getSource()==b_clear)
		{
			username.setText("");
			password.setText("");

		}

		else if(ae.getSource()==b_exit)
		{
		    System.exit(0);
		}
	}

	public static void main(String args[])
	{
		new Login();

	}

}

