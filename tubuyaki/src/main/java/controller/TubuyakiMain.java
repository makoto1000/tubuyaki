package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Dao.GetTubuyakiListDao;
import Dao.RegisterTubuyakiDao;
import Dao.UserRegisterDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CheckLogin;
import model.CheckRegister;
import model.Tubuyaki;
import model.User;

@WebServlet("/TubuyakiMain")
public class TubuyakiMain extends HttpServlet{
	
	HttpSession session;
	private static final Logger logger = Logger.getLogger("TubuyakiMain");
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{
		
		//ログ出力の準備
		logger.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.ALL);
		logger.addHandler(handler);
		
		//post送信時の処理判別のための変数
		String operation = request.getParameter("operation");
		//登録エラー時に返すメッセージ格納用
		String message;
		
		//ログイン時の処理
		if(operation.equals("ログイン")){
		String email = (String)request.getParameter("email");
		String pass = (String)request.getParameter("pass");
		
			//メールアドレスが入力されているかチェック
			if(email == null || email.isEmpty()) {
				message = "正しいメールアドレスを入力してください";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginfailured.jsp");
				dispatcher.forward(request, response);
				return;
				}
		
			//パスワードが入力されているかチェック
			if(pass == null || pass.isEmpty()) {
				message = "正しいパスワードを入力してください";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginfailured.jsp");
				dispatcher.forward(request, response);
				return;
			}	
		
			//メールアドレス、パスワードを判定
			CheckLogin checkLogin = new CheckLogin();
			Optional<Map<String, String>> checkResult = checkLogin.check(email, pass);
			String name;
			String userId;
			if(checkResult.isPresent()) {
				name = (String)checkResult.get().get("name");
				userId = (String)checkResult.get().get("userid");
				request.setAttribute("name", name);
				session = request.getSession();
				session.setAttribute("userid", userId);
				session.setAttribute("name", name);
			}else {
				System.out.println("checkLoginでtrueが返されている");
				message = "メールアドレスもしくはパスワードが違います";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginfailured.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			//DBからつぶやきリストを取得する処理
			GetTubuyakiListDao getTubuyakiListDao = new GetTubuyakiListDao();
			if(getTubuyakiListDao.getTubuyakiList().isPresent()) {
				List<Tubuyaki> tubuyakiList = getTubuyakiListDao.getTubuyakiList().get();
				request.setAttribute("tubuyakiList", tubuyakiList);
			}else {
				String messageOfNoTubuyaki = "つぶやきはまだありません";
				request.setAttribute("messageOfNoTubuyaki", messageOfNoTubuyaki);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/myPage.jsp");
				return;
			}
			
			RequestDispatcher dispatcherMypage = request.getRequestDispatcher("WEB-INF/jsp/myPage.jsp");
			dispatcherMypage.forward(request, response);
			}
			
		    //ここまでif文の処理↑
	
		//登録時の処理
		if(operation.equals("登録")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			
			//名前が未入力か調べる処理
			if(name == null || name.isEmpty()) {
				message = "名前が未入力です";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			//メールアドレスが未入力か調べる処理
			if(email == null || email.isEmpty()) {
				message = "メールアドレスが未入力です";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			//パスワードが未入力か調べる処理
			if(pass == null || pass.isEmpty()) {
				message = "パスワードが未入力です";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			//未登録か調べる処理
			CheckRegister checkRegister = new CheckRegister();
			boolean checkResult = checkRegister.check(name, email);
			if(!(checkResult)) {
				message = "既に使用されています";
			    request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
				dispatcher.forward(request, response);
			}
			
			//ユーザー情報をDBに登録する処理
			User user = new User(name, email, pass);
			UserRegisterDao userRegisterDao = new UserRegisterDao();
			userRegisterDao.userRegister(user);
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/completeRegister.jsp");
			dispatcher.forward(request, response);
		}
		
			//つぶやき時の処理
			if(operation.equals("つぶやく")) {
				String tubuyaki = request.getParameter("tubuyaki");
				//つぶやきが入力されていない場合の処理
				if(tubuyaki == null || tubuyaki.isEmpty()) {
					message = "つぶやきが入力されていません";
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/myPage.jsp");
					dispatcher.forward(request, response);
					return;
				}
				
				//つぶやきをDBに登録する処理
				RegisterTubuyakiDao registerTubuyakiDao = new RegisterTubuyakiDao();
				registerTubuyakiDao.registerTubuyaki((String)session.getAttribute("userid"), tubuyaki);
			}
	}
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
			
			//actionの値を取得
			String action = request.getParameter("action");
			//actionがregisterの場合の処理
			if(action.equals("register")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
	
}
