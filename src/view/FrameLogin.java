package view;
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 







import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.DaoLogin;
import dao.H2DB_Initializer;
import main.Main;
 
public class FrameLogin extends JFrame implements ActionListener{
 
	private static final long serialVersionUID = 1L;
	BufferedImage img = null;
    JTextField loginTextField;
    JPasswordField passwordField;
    DaoLogin daoLogin;
    JButton bt;
    Main main;
 
    // 메인
    public static void main(String[] args) throws Exception {
        FrameLogin frameLogin = new FrameLogin();
        
        H2DB_Initializer hdInitializer = new H2DB_Initializer();
        hdInitializer.initDatabase();
        
        DaoLogin daoLogin = new DaoLogin();
        daoLogin.insertUser();
        
        frameLogin.setDaoLogin(daoLogin);
    }
 
    // 생성자
    public FrameLogin() {
        setTitle("로그인 테스트");
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        // 레이아웃 설정
        setLayout(null);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1600, 900);
        layeredPane.setLayout(null);
 
        // 패널1
        // 이미지 받아오기
        try {
            img = ImageIO.read(new File("img/login.png"));
        } catch (IOException e) {
            System.out.println("이미지 불러오기 실패");
            System.exit(0);
        }
         
        MyPanel panel = new MyPanel();
        panel.setBounds(0, 0, 1600, 900);
         
 
        // 로그인 필드
        loginTextField = new JTextField(15);
        loginTextField.setBounds(731, 399, 280, 30);
        layeredPane.add(loginTextField);
        loginTextField.setOpaque(false);
        loginTextField.setForeground(Color.green);
        loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        // 패스워드
        passwordField = new JPasswordField(15);
        passwordField.setBounds(731, 529, 280, 30);
        passwordField.setOpaque(false);
        passwordField.setForeground(Color.green);
        passwordField.setText("message");
        passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        layeredPane.add(passwordField);
 
        // 로그인버튼 추가
        bt = new JButton(new ImageIcon("img/btLogin_hud.png"));
        bt.setBounds(755, 689, 104, 48);
        bt.addActionListener(this);//액션걸림
 
        // 버튼 투명처리
        bt.setBorderPainted(false);
        bt.setFocusPainted(false);
        bt.setContentAreaFilled(false);
 
        layeredPane.add(bt);
 
        // 마지막 추가들
        layeredPane.add(panel);
        add(layeredPane);
        setVisible(true);
    }
 
    class MyPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		public void paint(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }

	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setDaoLogin(DaoLogin daoLogin) {
		this.daoLogin = daoLogin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("액션 체크 됨 ");
		System.out.println(loginTextField.getText());
		System.out.println(passwordField.getPassword());
		
		//사용자가 값을 넣었는지 체크한 후에
		if(loginTextField.getText().equals("")|| new String(passwordField.getPassword()).equals("")){
			JOptionPane.showMessageDialog(null, "아이디나 비밀번호를 입력해주세요", "아이디나 비밀번호 입력", JOptionPane.INFORMATION_MESSAGE);
		}else{
			//사용자가 값을 넣었으면 로그인 체크를 한다. 
			System.out.println("사용자 로그인을 해야되는데.. 데이터베이스에 접근해야 됩니다. ");
			boolean logincheck = daoLogin.loginCheck(loginTextField.getText(), new String(passwordField.getPassword()));
			System.out.println("로그인 성공여부는 :"+logincheck);
			
			if(logincheck){
				//로그인에 성공한 경우
				JOptionPane.showMessageDialog(null, "로그인 성공", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);
				//TODO main 에게 이거는 닫아주고, 새로운 매니지 뷰 화면 열어줘 요청
				main.showFrameManage(this);
			}else{
				
				//로그인에 실패한 경우
				JOptionPane.showMessageDialog(null, "로그인 실패", "로그인 실패", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
 
}