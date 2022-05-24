

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class preupdate
 */
@WebServlet("/update")
public class preupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public preupdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userid= (String)session.getAttribute("userid");
		if(userid==null || userid.equals("")) {
			response.sendRedirect("login3.html");
			return;
		}
		response.setContentType("text/html; charset=utf-8");
		String id =request.getParameter("id");
		MemberDAO dao = new MemberDAO();
		MemberVO mvo =dao.getMember(id);
		PrintWriter out =response.getWriter();
		String outstr=("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset='UTF-8'>"
				+ "<title>Update t_member</title>"
				+ "</head>"
				+ "<body align=center>"
				+ "<form method=post action=modify id=frmUpdate>"
				+ "<table align=center border=1>"
				+ "<tr><td>ID</td><td><input type=text name=id value='"+id+"' readonly></td></tr>"
				+ "<tr><td>비밀번호</td><td><input type=text name=password value='"+mvo.getPwd()+"'></td></tr>"
				+ "<tr><td>이름</td><td><input type=text name=name value='"+mvo.getName()+"'></td></tr>"
				+ "<tr><td>모바일</td><td><input type=text name=mobile value='"+mvo.getMobile()+"'></td></tr>"
				+ "<tr><td>등록일</td><td><input type=date name=joindate value='"+mvo.getJoinDate()+"'></td></tr>"
				+ "<tr><td colspan=2 align=center><input type=submit value='수정완료'><input type=reset value='비우기'></td></tr>"
				+ "</table></form></body></html>");
		out.print(outstr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
