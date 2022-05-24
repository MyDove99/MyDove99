

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class addnewServlet
 */
@WebServlet("/addnew")
public class addnewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addnewServlet() {
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
		
		MemberVO mvo = new MemberVO();
		//MemberVO 선언
		
		mvo.setId(request.getParameter("id"));
		mvo.setPwd(request.getParameter("password"));
		mvo.setName(request.getParameter("name"));
		mvo.setMobile(request.getParameter("mobile"));
		mvo.setJoinDate(request.getParameter("joindate"));
		//MemberVO에 값을 넣는 부분
		MemberDAO dao = new MemberDAO();
		dao.addnewMember(mvo);
		response.sendRedirect("member");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
