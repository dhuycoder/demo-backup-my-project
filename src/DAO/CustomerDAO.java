package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Building;
import model.Customer;

public class CustomerDAO implements DAOInterface<Customer> {
	public static CustomerDAO getInstance() {
		return new CustomerDAO();
	}
	@Override
	public int insert(Customer t) {
		int check = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "Insert into khachthue(MaK,MaT,MaP,HoTenK,GioiTinh,CCCD,SDT,NgaySinh,Que,NgayThue)"
					+" Values(?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getCustomerNumber());
			pst.setString(2, t.getBuildingNumber());
			pst.setString(3, t.getRoomNumber());
			pst.setString(4, t.getCustomerName());
			pst.setString(5, t.getCustomerGender());
			pst.setString(6, t.getCustomerIdCard());
			pst.setString(7, t.getCustomerPhoneNumber());
			pst.setDate(8, java.sql.Date.valueOf(t.getCustomerBirthDay()));
			pst.setString(9, t.getCustomerHomeTown());
			pst.setDate(10, java.sql.Date.valueOf(t.getCustomerArrivalDate()));
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}

	@Override
	public int delete(Customer t) {
		int check = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete  from khachthue"
					+" where MaK = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getCustomerNumber());
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}

	@Override
	public int update(Customer t) {
		int check = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "Update khachthue"
					+" set MaT =?,MaP =?,HoTenK =?,GioiTinh =?,CCCD =?,SDT =?,NgaySinh =?,Que =?,NgayThue =?"
					+" where MaK =?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getBuildingNumber());
			pst.setString(2, t.getRoomNumber());
			pst.setString(3, t.getCustomerName());
			pst.setString(4, t.getCustomerGender());
			pst.setString(5, t.getCustomerIdCard());
			pst.setString(6, t.getCustomerPhoneNumber());
			pst.setDate(7, java.sql.Date.valueOf(t.getCustomerBirthDay()));
			pst.setString(8, t.getCustomerHomeTown());
			pst.setDate(9, java.sql.Date.valueOf(t.getCustomerArrivalDate()));
			pst.setString(10, t.getCustomerNumber());
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}

	@Override
	public ArrayList<Customer> selectALL() {
		ArrayList<Customer> lst = new ArrayList<Customer>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "Select * From khachthue";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String customerNumber = rs.getString("MaK");
				String buildingNumber = rs.getString("MaT");
				String roomNumber = rs.getString("MaP");
				String customerName = rs.getString("HoTenK");
				String customerGender = rs.getString("GioiTinh");
				String customerIdCard = rs.getString("CCCD");
				String customerPhoneNumber = rs.getString("SDT");
				Date customerBirthDay = rs.getDate("NgaySinh");
				String customerHomeTown = rs.getString("Que");
				Date customerArrivalDate = rs.getDate("NgayThue");
				Customer tmp = new Customer(customerNumber, buildingNumber, roomNumber, customerName, customerGender, customerIdCard, null, customerHomeTown, null, customerPhoneNumber);
				lst.add(tmp);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lst;
	}

	@Override
	public Customer selectbyId(Customer t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Customer> selectbyCondition(String condition) {
		ArrayList<Customer> lst = new ArrayList<Customer>();
		try {
			Connection c = JDBCUtil.getConnection();
			Statement st = c.createStatement();
			String sql = "Select * From khachthue"
						+" where "+condition;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String customerNumber = rs.getString("MaK");
				String buildingNumber = rs.getString("MaT");
				String roomNumber = rs.getString("MaP");
				String customerName = rs.getString("HoTenK");
				String customerGender = rs.getString("GioiTinh");
				String customerIdCard = rs.getString("CCCD");
				String customerPhoneNumber = rs.getString("SDT");
				Date customerBirthDay = rs.getDate("NgaySinh");
				String customerHomeTown = rs.getString("Que");
				Date customerArrivalDate = rs.getDate("NgayThue");
				Customer tmp = new Customer(customerNumber, buildingNumber, roomNumber, customerName, customerGender, customerIdCard, null, customerHomeTown, null, customerPhoneNumber);
				lst.add(tmp);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lst;
	}
	public int deleteByCondition(String condition) {
		int check = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from khachthue"
					+" where " +condition;
		System.out.println(sql);
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}
	public int updateByCondition(Customer t,String condition) {
		int check = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "Update khachthue"
					+" set MaT =?,MaP =?,HoTenK =?,GioiTinh =?,CCCD =?,SDT =?,NgaySinh =?,Que =?,NgayThue =?"
					+" where "+condition;
		System.out.println(sql);
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getBuildingNumber());
			pst.setString(2, t.getRoomNumber());
			pst.setString(3, t.getCustomerName());
			pst.setString(4, t.getCustomerGender());
			pst.setString(5, t.getCustomerIdCard());
			pst.setString(6, t.getCustomerPhoneNumber());
			pst.setDate(7, java.sql.Date.valueOf(t.getCustomerBirthDay()));
			pst.setString(8, t.getCustomerHomeTown());
			pst.setDate(9, java.sql.Date.valueOf(t.getCustomerArrivalDate()));
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}
	public ArrayList<String> selectMaK() {
		ArrayList<String> lst = new ArrayList<String>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "Select MaK From khachthue";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String customerNumber = rs.getString("MaK");
				lst.add(customerNumber);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lst;
	}
	// ------------------------------------------------------------------------------------
	public String selectMaKByCondition(String condition) {
		String kq = "";
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "Select MaK From khachthue"
						+" where "+condition;
			System.out.println(sql);
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String customerNumber = rs.getString("MaK");
				kq = customerNumber;
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return kq;
	}
	// ------------------------------------------------------------------------------------
	public ArrayList<Customer> selectMaKAndMaT() {
		ArrayList<Customer> lst = new ArrayList<Customer>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "Select MaK, MaT From khachthue";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String customerNumber = rs.getString("MaK");
				String buildingNumber = rs.getString("MaT");
				Customer tmp = new Customer(customerNumber, buildingNumber);
				lst.add(tmp);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lst;
	}
	public Customer selectbyCondition1(String condition) {
		Customer tmp = new Customer();
		try {
			Connection c = JDBCUtil.getConnection();
			Statement st = c.createStatement();
			String sql = "Select * From khachthue"
						+" where "+condition;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String customerNumber = rs.getString("MaK");
				String buildingNumber = rs.getString("MaT");
				String roomNumber = rs.getString("MaP");
				String customerName = rs.getString("HoTenK");
				String customerGender = rs.getString("GioiTinh");
				String customerIdCard = rs.getString("CCCD");
				String customerPhoneNumber = rs.getString("SDT");
				Date customerBirthDay = rs.getDate("NgaySinh");
				String customerHomeTown = rs.getString("Que");
				Date customerArrivalDate = rs.getDate("NgayThue");
				tmp = new Customer(customerNumber, buildingNumber, roomNumber, customerName, customerGender, customerIdCard, customerBirthDay.toLocalDate(), customerHomeTown, customerArrivalDate.toLocalDate(), customerPhoneNumber);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tmp;
	}
	
}
