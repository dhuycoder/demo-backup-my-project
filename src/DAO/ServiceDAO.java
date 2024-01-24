package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Building;
import model.Service;

public class ServiceDAO implements DAOInterface<Service> {

	public static ServiceDAO getInstance() {
		return new ServiceDAO();
	}

	@Override
	public int insert(Service t) {
		int check = 0;
		Connection c = JDBCUtil.getConnection();
		String sql ="Insert into dichvu(maDV,MaT,tenDV,giaDV)"
				+" values(?,?,?,?)";
		try {
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getServiceNumber());
			pst.setString(2, t.getBuilldingNumber());
			pst.setString(3, t.getServiceName());
			pst.setDouble(4, Double.parseDouble(t.getServicePrice()) );
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public int delete(Service t) {
		int check = 0;
		Connection c = JDBCUtil.getConnection();
		String sql ="delete from dichvu"
				+" where maDV = ? and MaT = ?";
		System.out.println(sql);
		try {
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getServiceNumber());
			pst.setString(2, t.getBuilldingNumber());
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public int update(Service t) {
		int check = 0;
		Connection c = JDBCUtil.getConnection();
		String sql ="update dichvu"
					+" set tenDV =?,giaDV=?"
					+" where maDV = ? and MaT = ?";
		try {
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getServiceName());
			pst.setDouble(2, Double.parseDouble(t.getServicePrice()) );
			pst.setString(3, t.getServiceNumber());
			pst.setString(4, t.getBuilldingNumber());
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public ArrayList<Service> selectALL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service selectbyId(Service t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Service> selectbyCondition(String condition) {
		ArrayList<Service> result = new ArrayList<Service>();
		Connection c = JDBCUtil.getConnection();
		try {
			Statement st = c.createStatement();
			String sql = "Select * from dichvu"
						+" where "+condition;
			ResultSet rs = st.executeQuery(sql);
			System.out.println(sql);
			while(rs.next()) {
				String buildingNubmer  = rs.getString("MaT");
				String serviceNumber = rs.getString("maDV");
				String serviceName = rs.getString("tenDV");
				String servicePrice = String.valueOf(rs.getDouble("GiaDV")) ;
				Service service = new Service(serviceNumber,buildingNubmer,serviceName,servicePrice);
				result.add(service);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<String> selectBuildingNumber() {
		ArrayList<String> result = new ArrayList<String>();
		Connection c = JDBCUtil.getConnection();
		try {
			Statement st = c.createStatement();
			String sql = "Select MaT from dichvu";
			ResultSet rs = st.executeQuery(sql);
			System.out.println(sql);
			while(rs.next()) {
				String buildingNubmer  = rs.getString("MaT");
				result.add(buildingNubmer);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	// ------------------------------------------------------------------
	public String selectSumPriceByCondition(String condition) {
		double price  =0;
		String tmp = null;
		Connection conn = JDBCUtil.getConnection();
		String query = "Select Sum(GiaDV) from dichvu"
						+ " where " + condition;
		System.out.println(query);
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				price = rs.getDouble("Sum(GiaDV)");
				tmp = String.valueOf(price);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
		
	}
	// -----------------------------------------------------------------------
}
