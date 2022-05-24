import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
	private Statement stmt;
	private Connection conn;
	
	public boolean exist(String userid,String passwd) {
		boolean result=false;
		try {
			connDB();
			String query ="select * from t_member where id='"+userid+"'and pwd='"+passwd+"'";
			this.stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int n=0;
			while(rs.next()) {
				n++;
			}
			if(n==1) {
				result=true;
			}else {
				result=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void updateMember(MemberVO mvo) {
		try {
			connDB();
			String query ="update t_member set name=?,pwd=?,mobile=?,joindate=? where id=?";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setString(1, mvo.getName());
			psmt.setString(2, mvo.getPwd());
			psmt.setString(3, mvo.getMobile());
			psmt.setString(4, mvo.getJoinDate());
			psmt.setString(5, mvo.getId());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberVO getMember(String id) {
		MemberVO mvo = new MemberVO();
		try {
			connDB();
			String query ="select * from t_member where id='"+id+"'";
			this.stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			mvo.setId(rs.getString("id"));
			mvo.setName(rs.getString("name"));
			mvo.setPwd(rs.getString("pwd"));
			mvo.setMobile(rs.getString("mobile"));
			mvo.setJoinDate(rs.getString("joindate"));
			this.stmt.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mvo;
	}
	
	public void deleteMember(String id) {
		try {
			connDB();
			String query ="delete from t_member where id=?";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setString(1,id);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addnewMember(MemberVO mvo) {
		try {
			connDB();
			String query ="insert into t_member values(?,?,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setString(1, mvo.getId());
			psmt.setString(2, mvo.getPwd());
			psmt.setString(3, mvo.getName());
			psmt.setString(4, mvo.getMobile());
			psmt.setString(5, mvo.getJoinDate());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<MemberVO> listMember() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		try {
			connDB(); // 켰고
			String query ="select * from t_member"; // 할거 적어주고
			this.stmt = conn.createStatement(); //db 상태 가져오고
			ResultSet rs = stmt.executeQuery(query); //할거 쳐주고
			while (rs.next()) { //친거 정보 적어주고
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString("id"));
				mvo.setPwd(rs.getString("pwd"));
				mvo.setName(rs.getString("name"));
				mvo.setMobile(rs.getString("mobile"));
				mvo.setJoinDate(rs.getString("joindate"));
				list.add(mvo);
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
