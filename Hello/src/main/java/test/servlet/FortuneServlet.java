package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 클라이언트가 "/fortune" 경로 요청을 해오면 FortuneServlet 클래스로 생성된
 * 객체가 직접 응답하도록 할 예정!
 * 
 * 1. HttpServlet 클래스를 상속 받는다
 * 2. service() 메소드를 오버라이드 한다
 * 3. 어떤 경로 요청이 오면 이 객체로 응답할지 정보를 맵핑한다
 */

//3.
@WebServlet("/fortune")
public class FortuneServlet extends HttpServlet{//1.
	//2.
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//오늘의 운세
		String fortuneToday="동쪽으로 가면 귀인을 만나요!";
		
		//응답 인코딩 설정
		response.setCharacterEncoding("utf-8");
		//응답 컨텐츠 설정
		response.setContentType("text/html; charset=utf-8");
		
		// 클라이언트에게 출력할 수 있는 객체의 참조값 얻어내기
		PrintWriter pw=response.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title>오늘의 운세 페이지</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<p> 오늘의 운세: "+fortuneToday+" </p>");
		pw.println("<img src=\"images/SouthKorea.png\">");
		pw.println("</body>");
		pw.println("</html>");		
		pw.close();
	}
}
