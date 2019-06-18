package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.User;
import model.User.Role;

public class UserDAO {
	
	public static User get(String userName) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM users WHERE userName = ? AND deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 2;
				String username = rset.getString(index++);
				String userPassword = rset.getString(index++);
				Date d= rset.getDate(index++);
				String registrationDate=FlightDAO.dateToString(d);
				Role role = Role.valueOf(rset.getString(index++));
				boolean blocked = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				User u = new User(username, userPassword, registrationDate, role, blocked, deleted);
				return u;

			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return null;
	}
	public static boolean addUser(User user) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO users (userName, userPassword, registrationDate, role, blocked,deleted) VALUES (?, ?, ?, ? ,? ,?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, user.getUserName());
			pstmt.setString(index++, user.getUserPassword());
			pstmt.setString(index++, user.getRegistrationDate());
			pstmt.setString(index++, user.getRole().toString());
			pstmt.setBoolean(index++, user.isBlocked());
			pstmt.setBoolean(index++, user.isDeleted());
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		return false;
	}

}
