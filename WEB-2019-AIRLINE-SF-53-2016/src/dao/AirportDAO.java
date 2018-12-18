package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Airport;

public class AirportDAO {
	
	public static ArrayList<Airport> getAll() {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Airport> airports = new ArrayList<Airport>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from airports where deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String name = rset.getString(index++);
				boolean deleted = rset.getBoolean(index++);
				Airport a = new Airport(id, name, deleted);
				airports.add(a);
			}
			return airports;

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
	public static Airport get (int id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from airports where id = ? and deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int index = 2;
				String name = rset.getString(index++);
				boolean deleted = rset.getBoolean(index++);
				Airport a = new Airport(id, name, deleted);
				return a;

			}
		} catch (Exception ex) {
			System.out.println("Greska u sql upitu");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (Exception ex1) {
				ex1.getStackTrace();
			}
		}
		return null;
	}
}