package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fortune2")
public class FortuneServlet2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 20개의 운세 목록
        String[] fortunes = {
            "동쪽으로 가면 귀인을 만나요!",
            "서쪽으로 가면 돈을 벌 수 있어요!",
            "오늘은 조심히 행동하세요.",
            "뜻밖의 선물을 받게 될 거예요!",
            "좋은 친구를 만나게 될 거예요.",
            "지금 떠오른 생각을 실천해보세요.",
            "잠시 휴식을 취해보세요.",
            "계획했던 일이 순조롭게 풀려요.",
            "전화 한 통이 기쁨을 가져다줄 거예요.",
            "오늘은 행운의 날이에요!",
            "도움이 필요한 사람을 도와보세요.",
            "기대하지 않은 곳에서 기회를 얻어요.",
            "옛 친구에게 연락이 올 거예요.",
            "실수가 전화위복이 돼요.",
            "포기하지 마세요, 좋은 일이 생길 거예요.",
            "당신의 노력이 결실을 맺어요.",
            "즐거운 소식이 찾아올 거예요.",
            "자신감을 가지세요, 당신은 잘하고 있어요.",
            "마음의 여유를 가지세요.",
            "오늘은 당신 편이에요, 무엇이든 해보세요!"
        };

        // 랜덤하게 운세 선택
        Random rand = new Random();
        String fortuneToday = fortunes[rand.nextInt(fortunes.length)];

        // 응답 인코딩 설정
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        // 응답 내용 출력
        PrintWriter pw = response.getWriter();
        pw.println("<!doctype html>");
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<meta charset='utf-8'>");
        pw.println("<title>오늘의 운세 페이지</title>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<p> 오늘의 운세: " + fortuneToday + " </p>");
        pw.println("<img src=\"images/SouthKorea.png\">");
        pw.println("</body>");
        pw.println("</html>");
        pw.close();
    }
}