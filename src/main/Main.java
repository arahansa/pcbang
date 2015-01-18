package main;

import asset.Setting;
import view.FrameLogin;
import view.FrameManage;
import dao.DaoLogin;
import dao.H2DB_Initializer;

public class Main {
	
	private FrameManage frameManage;
	private FrameLogin frameLogin;
	
	private DaoLogin daoLogin;
	
	
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.frameLogin = new FrameLogin();
		main.frameLogin.setMain(main);
        //database 초기화(테이블 만듦)
        H2DB_Initializer hdInitializer = new H2DB_Initializer();
        hdInitializer.initDatabase();
        //login을 위한 데이터 액세스 오브젝트 만들고, 사용자 입력.
        DaoLogin daoLogin = new DaoLogin();
        daoLogin.insertUser();
        //프레임에 dao 주입
        main.frameLogin.setDaoLogin(daoLogin);
	}
	
	public void showFrameManage(FrameLogin frameLogin){
		frameLogin.dispose();
		FrameManage manageView = new FrameManage();
		try {
			manageView.setRectangles(FrameManage.class, manageView, Setting.class, Setting.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
