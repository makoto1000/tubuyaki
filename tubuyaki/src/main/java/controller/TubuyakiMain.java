package controller;

import java.io.IOException;

import org.apache.jasper.runtime.ProtectedFunctionMapper;
import org.apache.jasper.tagplugins.jstl.core.If;
import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CheckLogin;
import model.CheckRegister;
import model.User;

@WebServlet("/TubuyakiMain")
public class TubuyakiMain extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{
		
		//post送信時の処理判別のための変数
		String operation = request.getParameter("operation");
		//登録エラー時に返すメッセージ格納用
		String message;
		
		//ログイン時の処理
		if(operation.equals("ログイン")){
		String email = (String)request.getParameter("email");
		String pass = (String)request.getParameter("pass");
		
			//メールアドレスチェック
			if(email == null || email.isEmpty()) {
				message = "正しいメールアドレスを入力してください";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginfailured.jsp");
				dispatcher.forward(request, response);
				return;
				}
		
			//パスワードチェック
			if(pass == null || pass.isEmpty()) {
				message = "正しいパスワードを入力してください";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginfailured.jsp");
				dispatcher.forward(request, response);
				return;
			}	
		
			//メールアドレス、パスワード未登録チェック
			CheckLogin checkLogin = new CheckLogin();
			boolean checkResult = checkLogin.check(email, pass);
			if(!(checkResult)) {
				message = "このメールアドレスもしくはパスワードは既に使用されています";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginfailured.jsp");
				dispatcher.forward(request, response);
				return;
			}
		
			User user = new User(email, pass);
			//データベースに登録をする処理
			}
		
			RequestDispatcher dispatcherMypage = request.getRequestDispatcher("WEB-INF/jsp/myPage.jsp");
			dispatcherMypage.forward(request, response);
			
		    //ここまでif文の処理↑
	
		//登録時の処理
		if(operation.equals("register")) {
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
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/completeRegister.jsp");
			dispatcher.forward(request, response);
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
