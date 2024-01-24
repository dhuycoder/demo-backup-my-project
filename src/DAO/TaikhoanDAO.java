package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Taikhoan;

public class TaikhoanDAO implements DAOInterface<Taikhoan> {
	public static TaikhoanDAO getInstance() {
		return new TaikhoanDAO();
	}

	@Override
	public int insert(Taikhoan t) {
		try {
			// b1 tao ket noi
			Connection con = JDBCUtil.getConnection();
			// b2 tao statement
			Statement st = con.createStatement();
			//b3 tao cau lenh
			String sql = "Insert into dangnhap(ID,TaiKhoan,MatKhau,tenNguoiDung)"+
						" Values (" +t.getIdAccount()+",'"+t.getUsername()+"','"+t.getPassword()+"','"+t.getUser()+"')";
			System.out.println(sql);
			//b4
			int check = st.executeUpdate(sql);
			System.out.println("so dong bi thay doi:"+check);
			//b5
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(Taikhoan t) {
		try {
			// b1 tao ket noi
			Connection con = JDBCUtil.getConnection();
			// b2 tao statement
			Statement st = con.createStatement();
			//b3 tao cau lenh
			String sql = "Delete from dangnhap"
						+" where ID  = "+t.getIdAccount();
			System.out.println(sql);
			//b4
			int check = st.executeUpdate(sql);
			System.out.println("so dong bi thay doi:"+check);
			//b5
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Taikhoan t) {
		try {
			// b1 tao ket noi
			Connection con = JDBCUtil.getConnection();
			// b2 tao statement
			Statement st = con.createStatement();
			//b3 tao cau lenh
			String sql = "Update dangnhap "
						+"Set ID  = "+t.getIdAccount()+",TaiKhoan = '"+t.getUsername()+"',MatKhau = '"+t.getPassword()+"',tenNguoiDung = '"+t.getUser()+"'"
						+" where ID  = "+t.getIdAccount();
			System.out.println(sql);
			//b4
			int check = st.executeUpdate(sql);
			System.out.println("so dong bi thay doi:"+check);
			//b5
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<Taikhoan> selectALL() {
		ArrayList<Taikhoan> ketqua = new ArrayList<Taikhoan>();
		Connection c = JDBCUtil.getConnection();
		try {
			Statement st = c.createStatement();
			String sql = "select * from dangnhap";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int ID  = rs.getInt("ID");
				String TaiKhoan = rs.getString("TaiKhoan");
				String MatKhau = rs.getString("MatKhau");
				String User = rs.getString("tenNguoiDung");
				Taikhoan taikhoan = new Taikhoan(ID , TaiKhoan, MatKhau,User);
				ketqua.add(taikhoan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketqua;
	}
// ------------------------------------------------------------------------------------
	@Override
	public Taikhoan selectbyId(Taikhoan t) {
		Connection c = JDBCUtil.getConnection();
		Taikhoan taikhoan = null;
		try {
			Statement st = c.createStatement();
			String sql = "select * from dangnhap"
						+" where ID  = "+t.getIdAccount();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int ID  = rs.getInt("ID");
				String TaiKhoan = rs.getString("TaiKhoan");
				String MatKhau = rs.getString("MatKhau");
				String User = rs.getString("tenNguoiDung");
				taikhoan = new Taikhoan(ID , TaiKhoan, MatKhau,User);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return taikhoan ;
	}
//-------------------------------------------------------------------------------------------

	@Override
	public ArrayList<Taikhoan> selectbyCondition(String condition) {
		ArrayList<Taikhoan> ketqua = new ArrayList<Taikhoan>();
		Connection c = JDBCUtil.getConnection();
		try {
			Statement st = c.createStatement();
			String sql = "select * from taikhoan"
						+" where "+condition;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int ID  = rs.getInt("ID");
				String TaiKhoan  = rs.getString("TaiKhoan");
				String MatKhau = rs.getString("MatKhau");
				String User = rs.getString("tenNguoiDung");
				Taikhoan taikhoan = new Taikhoan(ID , TaiKhoan, MatKhau,User);
				ketqua.add(taikhoan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketqua;
	}
	public int selectID(String condition) {
		int ID = 0;
		Connection c = JDBCUtil.getConnection();
		try {
			Statement st = c.createStatement();
			String sql = "select ID from dangnhap"
						+" where "+condition;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				 ID  = rs.getInt("ID");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ID;
	}
	
}
