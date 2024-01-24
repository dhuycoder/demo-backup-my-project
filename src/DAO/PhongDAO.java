package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import database.JDBCUtil;
import database.JDBCUtil;
import model.phong;

public class PhongDAO implements DAOInterface<phong> {
	public static PhongDAO getInstance() {
		return new PhongDAO();
	}
	@Override
	public int insert(phong t) {
		try {
			// b1 tao ket noi
			Connection con = JDBCUtil.getConnection();
			// b2 tao statement
			Statement st = con.createStatement();
			//b3 tao cau lenh
			String sql = "Insert into phong(MaP,MaT,LoaiP,GiaP,TrangThaiP)"+
						" Values ('" +t.getRoomNumber()+"','"+t.getBuildingNumber()+"','"+t.getRoomType()+"',"+Double.parseDouble(t.getPrice())+","+0+")";
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
	public int delete(phong t) {
		try {
			// b1 ket noi voi database
			Connection con = JDBCUtil.getConnection();
			// b2 tao statement 
			Statement st = con.createStatement();
			// b3 viet cau lenh 
			String sql = "Delete from phong"+
						" Where MaP = '"+t.getRoomNumber()+"' and MaT = '"+t.getBuildingNumber()+"'"; 
			System.out.println(sql);
			//b4 thuc hien cau lenh
			int check = st.executeUpdate(sql);
			System.out.println("so dong da duoc thay doi: "+check);
			//b5 ngat ket noi voi dtb
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int update(phong t) {
		try {
			int tmp = 0 ;
			if(t.getRoomState().equals("Đã thuê")) {
				tmp = 1;
			}
			// b1 ket noi voi database
			Connection con = JDBCUtil.getConnection();
			// b2 tao statement 
			Statement st = con.createStatement();
			// b3 viet cau lenh 
			String sql = "update phong"+
						" set MaP = '"+t.getRoomNumber()+"',"+"LoaiP = '"+t.getRoomType()+"',"+"GiaP = "+Double.parseDouble(t.getPrice())+",MaT = '"+t.getBuildingNumber()+"',TrangThaiP ="+tmp+
						" Where MaP = '"+t.getRoomNumber()+"'"; 
			System.out.println(sql);
			//b4 thuc hien cau lenh
			int check = st.executeUpdate(sql);
			System.out.println("so dong da duoc thay doi: "+check);
			//b5 ngat ket noi voi dtb
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public ArrayList<phong> selectALL() {
		String tmp;
		ArrayList<phong> result = new ArrayList<phong>();
		Connection c = JDBCUtil.getConnection();
		try {
			Statement st = c.createStatement();
			String sql = "Select * from phong";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String roomNumber = rs.getString("MaP");
				String buildingNubmer = rs.getString("MaT");
				String roomType = rs.getString("LoaiP");
				String price = String.valueOf(rs.getDouble("GiaP"));
				int roomState = rs.getInt("TrangThaiP");
				if(roomState == 1) {
					tmp = "Đã thuê";
				}
				else {
					tmp = "Còn trống";
				}
				phong phong1 = new phong(roomNumber,price,roomType,buildingNubmer,tmp);
				result.add(phong1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public phong selectbyId(phong t) {
		phong tmp = new phong();
		String roomStatetmp;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from phong"
					+" where MaP = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getRoomNumber());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String roomNumber = rs.getString("MaP");
				String buildingNumber = rs.getString("MaT");
				String roomType = rs.getString("LoaiP");
				String price = String.valueOf(rs.getDouble("GiaP")) ;
				int roomState = rs.getInt("TrangThaiP");
				if(roomState == 1) {
					roomStatetmp = "Đã thuê";
				}
				else {
					roomStatetmp = "Còn trống";
				}
				tmp = new phong(roomNumber, price, roomType, buildingNumber, roomStatetmp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}

	@Override
	public ArrayList<phong> selectbyCondition(String condition) {
		String roomStatetmp;
		ArrayList<phong> result = new ArrayList<phong>();
		Connection c = JDBCUtil.getConnection();
		try {
			Statement st = c.createStatement();
			String sql = "Select * from phong"
						+" where "+condition;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String roomNumber = rs.getString("MaP");
				String buildingNubmer = rs.getString("MaT");
				String roomType = rs.getString("LoaiP");
				String price = String.valueOf(rs.getDouble("GiaP")) ;
				Integer roomState = rs.getInt("TrangThaiP");
				if(roomState == 1) {
					roomStatetmp = "Đã thuê";
				}
				else {
					roomStatetmp = "Còn trống";
				}
				phong phong1 = new phong(roomNumber,price,roomType,buildingNubmer,roomStatetmp);
				result.add(phong1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public int deleteByCondition(String condition) {
		try {
			// b1 ket noi voi database
			Connection con = JDBCUtil.getConnection();
			// b2 tao statement 
			Statement st = con.createStatement();
			// b3 viet cau lenh 
			String sql = "Delete from phong"
						+" where "+condition;
			System.out.println(sql);
			//b4 thuc hien cau lenh
			int check = st.executeUpdate(sql);
			System.out.println("so dong da duoc thay doi: "+check);
			//b5 ngat ket noi voi dtb
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	public int update2(phong t) {
		try {
			int tmp = 0;
			if(t.getRoomState().equals("Đã thuê")) {
				tmp = 1;
			}
			// b1 ket noi voi database
			Connection con = JDBCUtil.getConnection();
			// b2 tao statement 
			Statement st = con.createStatement();
			// b3 viet cau lenh 
			String sql = "update phong"+
						" set MaP = '"+t.getRoomNumber()+"',"+"LoaiP = '"+t.getRoomType()+"',"+"GiaP = "+Double.parseDouble(t.getPrice())+",MaT = '"+t.getBuildingNumber()+"',TrangThaiP ="+tmp+
						" Where MaP = '"+t.getRoomNumber()+"' and MaT = '"+t.getBuildingNumber()+"'"; 
			System.out.println(sql);
			//b4 thuc hien cau lenh
			int check = st.executeUpdate(sql);
			System.out.println("so dong da duoc thay doi: "+check);
			//b5 ngat ket noi voi dtb
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	public ArrayList<String> selectRoomState(String condition) {
		ArrayList<String> lst = new ArrayList<String>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "select TrangThaiP from phong"
					+" where "+condition ;
		try {
			String tmp;
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int roomState = rs.getInt("TrangThaiP");
				if(roomState == 1) {
					tmp = "Đã thuê";
				}
				else {
					tmp = "Còn trống";
				}
				lst.add(tmp);
			}
		JDBCUtil.closeConnection(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;
	}
	// ------------------------------------------------------------------------------------
	public String selectSumPriceByCondition(String condition) {
		double price  =0;
		String tmp = null;
		Connection conn = JDBCUtil.getConnection();
		String query = "Select Sum(GiaP) from phong"
						+ " where " + condition;
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				price = rs.getDouble("Sum(GiaP)");
				tmp = String.valueOf(price);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
		
	}
	// ------------------------------------------------------------------------------------
	public ArrayList<String> selectRoomIdByCondition(String condition) {
		ArrayList<String> result = new ArrayList<String>();
		Connection c = JDBCUtil.getConnection();
		try {
			String sql = "Select MaP from phong"
						+" where "+condition;
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				String roomNumber = rs.getString("MaP");
				result.add(roomNumber);
			}
			JDBCUtil.closeConnection(c);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	
	}
	// ------------------------------------------------------------------------------------
}

