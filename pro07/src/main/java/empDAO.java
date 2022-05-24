import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class empDAO {
	private Statement stmt;
	private Connection conn;
	
	public ArrayList<empVO> listMember() {
		ArrayList<empVO> list = new ArrayList<empVO>();
		try {
			connDB(); // 켰고
			String query ="select a.employee_id,a.emp_name,b.emp_name manager_name,c.department_name "+
						  "from employees a,employees b, departments c "+
						  "where a.manager_id=b.employee_id "+
						  " and a.department_id=c.department_id"; // 할거 적어주고
			this.stmt = conn.createStatement(); //db 상태 가져오고
			ResultSet rs = stmt.executeQuery(query); //할거 쳐주고
			while (rs.next()) { //친거 정보 적어주고
				int eid = rs.getInt("employee_id");
				String emp_name = rs.getString("emp_name");
				String m_name = rs.getString("manager_name");
				String d_name = rs.getString("department_name");
				empVO evo = new empVO();
				evo.setEmployee_id(eid);
				evo.setEmp_name(emp_name);
				evo.setManager_name(m_name);
				evo.setDepartment_name(d_name);
				list.add(evo);
			}
			rs.close(); // 계속 켜두면 터진다? 그래서 닫아주고
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace(); // 오류걸리면 표기해주고
		}
		return list; //list 반환해주고
	}
	
	private void connDB() { //db 키는거
		String driver="oracle.jdbc.driver.OracleDriver"; //ojdbc6
		String url ="jdbc:oracle:thin:@localhost:1521:orcl";
		String userid="ora_user";
		String passcode="human123";
		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(url,userid,passcode);
			if(conn==null) {
				System.out.println("데이터베이스 접속실패");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
