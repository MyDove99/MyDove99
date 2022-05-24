

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class empServlet
 */
@WebServlet("/emp")
public class empServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public empServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		empDAO dao = new empDAO();
		ArrayList<empVO> list = dao.listMember();
		
		out.print("<html><head><title>result from t_member</title></head><body>");
		out.print("<table border=1><tr><th>ID</th><th>비밀번호</th><th>이름</th><th>모바일</th><th>번호</th></tr>");
		for(int i=0; i<list.size(); i++) {
			empVO evo = list.get(i);
			int eid = evo.getEmployee_id();
			String emp_name = evo.getEmp_name();
			String m_name = evo.getManager_name();
			String d_name = evo.getDepartment_name();
			out.print("<tr><td>"+eid+"</td><td>"+emp_name +"</td><td>"+m_name+"</td><td>"+d_name+"</td><td>"+(i+1)+"</td></tr>");
		}
		out.print("</table></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
