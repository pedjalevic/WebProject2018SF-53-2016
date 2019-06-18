package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Airport;
import model.Flight;

public class FlightDAO {
	
	public static ArrayList<Flight> allFlight(){
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Flight> flights = new ArrayList<Flight>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from flights where deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String flightNumber = rset.getString(index++);
				Date d = rset.getDate(index++);
				Date d2 = rset.getDate(index++);
				int i = rset.getInt(index++);
				int i2 = rset.getInt(index++);
				int seatNumber = rset.getInt(index++);
				int freeSeat = rset.getInt(index++);
				double ticketPrice = rset.getDouble(index++);
				boolean deleted = rset.getBoolean(index++);
				Airport departureAirport = AirportDAO.get(i);
				Airport arrivalAirport = AirportDAO.get(i2);
				String departureDate = dateToString(d);
				String arrivalDate = dateToString(d2);
				Flight f = new Flight(id, flightNumber, departureDate, arrivalDate, departureAirport,
						arrivalAirport, seatNumber, freeSeat, ticketPrice, deleted);
				flights.add(f);
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
		return flights;
	}
	
	public static Flight getFlight(int id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from flights where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int index = 2;
				String flightNumber = rset.getString(index++);
				Date d = rset.getDate(index++);
				Date d2 = rset.getDate(index++);
				int i = rset.getInt(index++);
				int i2 = rset.getInt(index++);
				int seatNumber = rset.getInt(index++);
				int freeSeat = rset.getInt(index++);
				double ticketPrice = rset.getDouble(index++);
				boolean deleted = rset.getBoolean(index++);
				Airport departureAirport = AirportDAO.get(i);
				Airport arrivalAirport = AirportDAO.get(i2);
				String departureDate = dateToString(d);
				String arrivalDate = dateToString(d2);
				Flight f = new Flight(id, flightNumber, departureDate, arrivalDate, departureAirport,
						arrivalAirport, seatNumber, freeSeat, ticketPrice, deleted);
				return f;
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
	
	
	public static String dateToString(Date date) {
		SimpleDateFormat formatvr = new SimpleDateFormat("ss:mm:HH dd.MM.yyyy");
		String datum;
		datum = formatvr.format(date);
		return datum;
	}

	public static Date stringToDate(String datum) {

		try {
			DateFormat formatvr = new SimpleDateFormat("ss:mm:HH dd.MM.yyyy");

			return formatvr.parse(datum);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static String dateToStringForWrite(Date date) {
		SimpleDateFormat formatvr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datum;
		datum = formatvr.format(date);
		return datum;
	}

	public static Date stringToDateForWrite(String datum) {

		try {
			DateFormat formatvr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			return formatvr.parse(datum);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;

	}
	
}