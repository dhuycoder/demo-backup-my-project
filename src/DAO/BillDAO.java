package DAO;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Bill;
import net.sf.jasperreports.repo.InputStreamResource;

public class BillDAO implements DAOInterface<Bill> {
	public static BillDAO getInstance() {
		return new BillDAO();
	}

	@Override
	public int insert(Bill t) {
		int check = 0;
		Connection conn =  JDBCUtil.getConnection();
		String sql = "Insert hoadon(IdHoaDon,IdTaiKhoan,MaT,MaP,MaK,SoDien,NgayXuatHD,GiaTien)"+
					" Values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getBillNumber());
			pst.setInt(2, t.getAccountNumber());
			pst.setString(3, t.getBuildingNumber());
			pst.setString(4, t.getRoomNumber());
			pst.setString(5, t.getCustomerNumber());
			pst.setInt(6, t.getElectricNumber());
			pst.setDate(7,Date.valueOf(t.getInvoiceDate()));
			pst.setBigDecimal(8, new BigDecimal(t.getBillPrice()));
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int delete(Bill t) {
		int check = 0;
		Connection conn =  JDBCUtil.getConnection();
		String sql = "delete from hoadon"
					+" where IdHoaDon = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getBillNumber());
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int update(Bill t) {
		// TODO Auto-generated method stub
		return 0;
	}
	// ------------------------------------------------------------------------------------
	@Override
	public ArrayList<Bill> selectALL() {
		ArrayList<Bill> lst = new ArrayList<Bill>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from hoadon";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int billNumber = rs.getInt("IdHoaDon");
				int AccountNumber = rs.getInt("IdTaiKhoan");
				String buildingNumber = rs.getString("MaT");
				String roomNumber = rs.getString("MaP");
				String customerId = rs.getString("MaK");
				int electricNumber = rs.getInt("SoDien");
				LocalDate invoiceDate = rs.getDate("NgayXuatHD").toLocalDate();
				String billPrice = String.valueOf(rs.getBigDecimal("GiaTien"));
				Bill tmp = new Bill(billNumber, AccountNumber, buildingNumber, roomNumber, invoiceDate, billPrice,customerId,electricNumber);
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
	@Override
	public Bill selectbyId(Bill t) {
		// TODO Auto-generated method stub
		return null;
	}
	// ------------------------------------------------------------------------------------
	@Override
	public ArrayList<Bill> selectbyCondition(String condition) {
		ArrayList<Bill> lst = new ArrayList<Bill>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from hoadon"
					+" where "+condition;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int billNumber = rs.getInt("IdHoaDon");
				int AccountNumber = rs.getInt("IdTaiKhoan");
				String buildingNumber = rs.getString("MaT");
				String roomNumber = rs.getString("MaP");
				String customerId = rs.getString("MaK");
				int electricNumber = rs.getInt("SoDien");
				LocalDate invoiceDate = rs.getDate("NgayXuatHD").toLocalDate();
				String billPrice = String.valueOf(rs.getBigDecimal("GiaTien"));
				Bill tmp = new Bill(billNumber, AccountNumber, buildingNumber, roomNumber, invoiceDate, billPrice,customerId,electricNumber);
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
	public int selectIdByCondition(String Condition) {
		int billNumber = 0;
		Connection conn = JDBCUtil.getConnection();
		String query = "select IdHoaDon from hoadon"
					+ " where "+Condition;
		System.out.println(query);
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				billNumber = rs.getInt("IdHoaDon");
			}
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return billNumber;
	}
	// ------------------------------------------------------------------------------------
	public int insertBillTmp(Bill t) {
		int check = 0;
		Connection conn =  JDBCUtil.getConnection();
		String sql = "Insert hoadontmp(IdHoaDon,IdTaiKhoan,MaT,MaP,MaK,SoDien,NgayXuatHD,GiaTien)"+
					" Values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getBillNumber());
			pst.setInt(2, t.getAccountNumber());
			pst.setString(3, t.getBuildingNumber());
			pst.setString(4, t.getRoomNumber());
			pst.setString(5, t.getCustomerNumber());
			pst.setInt(6, t.getElectricNumber());
			pst.setDate(7,Date.valueOf(t.getInvoiceDate()));
			pst.setDouble(8, Double.parseDouble(t.getBillPrice()));
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	// ------------------------------------------------------------------------------------
	public Bill selectBillbyCondition(String condition) {
		Bill tmp = new Bill();
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from hoadon"
					+" where "+condition;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int billNumber = rs.getInt("IdHoaDon");
				int AccountNumber = rs.getInt("IdTaiKhoan");
				String buildingNumber = rs.getString("MaT");
				String roomNumber = rs.getString("MaP");
				String customerId = rs.getString("MaK");
				int electricNumber = rs.getInt("SoDien");
				LocalDate invoiceDate = rs.getDate("NgayXuatHD").toLocalDate();
				String billPrice = String.valueOf(rs.getBigDecimal("GiaTien"));
				tmp = new Bill(billNumber, AccountNumber, buildingNumber, roomNumber, invoiceDate, billPrice,customerId,electricNumber);
			}
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}
	//-----------------------------------------------------------------------------
	public InputStream getReport(String report_name,String column_name) {
		InputStream input =null;
		String query = "Select "+column_name+" from reports where report_name = '"+report_name+"'";
		
		try {
			Connection connect = JDBCUtil.getConnection();
			PreparedStatement pst = connect.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				input = rs.getBinaryStream(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return input;
	}
	//----------------------------------------------------------------------
	public int deleteBillTmp() {
		int check = 0;
		Connection conn =  JDBCUtil.getConnection();
		String sql = "delete from hoadontmp";
					
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			check = pst.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	// ------------------------------------------------------------------------------------
	public String SelectPricePerMonth(int month) {
		String price = "0";
		Connection conn = JDBCUtil.getConnection();
		String sql = "Select Sum(GiaTien) as TongDoanhThu From hoadon"
					+" where Month(NgayXuatHD) = "+month
					+" group by Month(NgayXuatHD)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				price = String.valueOf(rs.getDouble("TongDoanhThu"));
				if(price.isEmpty()) {
					price = "0";
				}
			}
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return price;
	}
	// ------------------------------------------------------------------------------------
	public String SelectPricePerYear(int year) {
		String price = "0";
		Connection conn = JDBCUtil.getConnection();
		String sql = "Select Sum(GiaTien) as TongDoanhThu From hoadon"
					+" where Year(NgayXuatHD) = "+year
					+" group by Year(NgayXuatHD)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				price = String.valueOf(rs.getDouble("TongDoanhThu"));
				if(price.isEmpty()) {
					price = "0";
				}
			}
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return price;
	}
	// ------------------------------------------------------------------------------------
}
