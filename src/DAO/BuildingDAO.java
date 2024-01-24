package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Result;

import database.JDBCUtil;
import model.Building;
import model.phong;

public class BuildingDAO implements DAOInterface<Building> {
	public static BuildingDAO getInstance() {
		return new BuildingDAO();
	}
	@Override
	public int insert(Building t) {
		int check = 0;
		Connection c = JDBCUtil.getConnection();
		String sql ="Insert into toanha(MaT,DiaChi)"
				+" values(?,?)";
		try {
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getBuidingNumber());
			pst.setString(2, t.getAddress());
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public int delete(Building t) {
		int check = 0;
		Connection c = JDBCUtil.getConnection();
		String sql ="Delete from toanha"
					+" where MaT = ?";
		try {
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getBuidingNumber());
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public int update(Building t) {
		int check = 0;
		Connection c = JDBCUtil.getConnection();
		String sql ="update toanha"
				+" set DiaChi = ?"
				+" where MaT = ?";
		
		try {
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getAddress());
			pst.setString(2,t.getBuidingNumber());
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public ArrayList<Building> selectALL() {
		ArrayList<Building> lst = new ArrayList<Building>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "Select * From toanha";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String buildingNumber = rs.getString("MaT");
				String addressBuilding = rs.getString("DiaChi");
				Building tmp = new Building(buildingNumber, addressBuilding);
				lst.add(tmp);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lst;
	}

	@Override
	public Building selectbyId(Building t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Building> selectbyCondition(String condition) {
		ArrayList<Building> result = new ArrayList<Building>();
		Connection c = JDBCUtil.getConnection();
		try {
			Statement st = c.createStatement();
			String sql = "Select * from toanha"
						+" where "+condition;
			ResultSet rs = st.executeQuery(sql);
			System.out.println(sql);
			while(rs.next()) {
				String buildingNubmer  = rs.getString("MaT");
				String addressBuilding = rs.getString("DiaChi");
			
				Building phong1 = new Building(buildingNubmer,addressBuilding);
				result.add(phong1);
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
			String sql = "Select MaT from toanha";
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
	

}
