package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Customer;
import model.Member;

public class MemberDAO implements DAOInterface<Member>{
	public static MemberDAO getInstance() {
		return new MemberDAO();
	}
	@Override
	public int insert(Member t) {
		int check = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "Insert into thanhvien(STT,MaK,HoTen,GioiTinh,CCCD,SDT,NgaySinh,DiaChi)"
					+" Values(?,?,?,?,?,?,?,?)";
		System.out.println(sql);
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getMemberNumber());
			pst.setString(2, t.getCustomerNumber());
			pst.setString(3, t.getMemberName());
			pst.setString(4, t.getGenderMember());
			pst.setString(5, t.getMemberIdCard());
			pst.setString(6, t.getMemberPhoneNumber());
			pst.setDate(7, Date.valueOf(t.getMemberBirthDay()));
			pst.setString(8, t.getMemberHomeTown());
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}

	@Override
	public int delete(Member t) {
		int check = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from thanhvien"
					+" where STT = ? and MaK = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getMemberNumber());
			pst.setString(2, t.getCustomerNumber());
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}

	@Override
	public int update(Member t) {
		int check = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "update thanhvien"
					+" set HoTen =?,GioiTinh =?,CCCD =?,SDT =?,NgaySinh =?,DiaChi =?"
					+" where STT = ? and MaK =?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getMemberName());
			pst.setString(2, t.getGenderMember());
			pst.setString(3, t.getMemberIdCard());
			pst.setString(4, t.getMemberPhoneNumber());
			pst.setDate(5, Date.valueOf(t.getMemberBirthDay()));
			pst.setString(6, t.getMemberHomeTown());
			pst.setInt(7, t.getMemberNumber());
			pst.setString(8, t.getCustomerNumber());
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}

	@Override
	public ArrayList<Member> selectALL() {
		ArrayList<Member> lst = new ArrayList<Member>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "Select * From thanhvien";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int memberNumber = rs.getInt("STT");
				String customerNumber = rs.getString("MaK");
				String memberName = rs.getString("HoTen");
				String memberGender = rs.getString("GioiTinh");
				String memberIdCard = rs.getString("CCCD");
				String memberPhoneNumber = rs.getString("SDT");
				Date memberBirthDay = rs.getDate("NgaySinh");
				String memberHomeTown = rs.getString("DiaChi");
				Member tmp = new Member(memberNumber, customerNumber, memberGender, memberBirthDay.toLocalDate(),
						memberIdCard, memberPhoneNumber, memberHomeTown, memberName);
				lst.add(tmp);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lst;
	}

	@Override
	public Member selectbyId(Member t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Member> selectbyCondition(String condition) {
		ArrayList<Member> lst = new ArrayList<Member>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "Select * From thanhvien"
						+" where "+condition;
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int memberNumber = rs.getInt("STT");
				String customerNumber = rs.getString("MaK");
				String memberName = rs.getString("HoTen");
				String memberGender = rs.getString("GioiTinh");
				String memberIdCard = rs.getString("CCCD");
				String memberPhoneNumber = rs.getString("SDT");
				Date memberBirthDay = rs.getDate("NgaySinh");
				String memberHomeTown = rs.getString("DiaChi");
				Member tmp = new Member(memberNumber, customerNumber, memberGender, memberBirthDay.toLocalDate(),
						memberIdCard, memberPhoneNumber, memberHomeTown, memberName);
				lst.add(tmp);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lst;
	}

}
