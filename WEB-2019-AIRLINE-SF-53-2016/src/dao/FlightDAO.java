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
	
	public static int getFlightId() {
		Connection conn = ConnectionManager.getConnection();
		int id=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT MAX(id) FROM flights";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
		
			if (rset.next()) {
				id=rset.getInt(1);
				
			}
			id++;
			return id;
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
		return 0;
	}
	
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
	
//	public static ArrayList<Flight> searchFlights(int departureAirportId, int arrivalAirportId, double ticketPrice1, double ticketPrice2,
//			String data1 , String data2, String data3 , String data4, String flightNumber, String orderBy, String orderByColumn,
//			String ascDes, String ed, String ed1){
//		Connection conn = ConnectionManager.getConnection();
//		ArrayList<Flight> flights = new ArrayList<Flight>();
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		try {
	//	String columnName = "<>";
	//	String query = "SELECT MAX(id) FROM flights where id "+ columnName +" ? ";
//			String query = "select * from flights where flightNumber like ? and departureDate >= ? and departureDate <= ? and arrivalDate >= ? and arrivalDate <=  and departureAirport ? ? and arrivalAirport ? ? and ticketPrice >= ? ticketPrice <= ? order by ? ?"  ;
//			
//			pstmt = conn.prepareStatement(query);
//			pstmt.setBoolean(1, false);
//			rset = pstmt.executeQuery();
//			while (rset.next()) {
//				int index = 1;
//				int id = rset.getInt(index++);
//				String flightNumber = rset.getString(index++);
//				Date d = rset.getDate(index++);
//				Date d2 = rset.getDate(index++);
//				int i = rset.getInt(index++);
//				int i2 = rset.getInt(index++);
//				int seatNumber = rset.getInt(index++);
//				int freeSeat = rset.getInt(index++);
//				double ticketPrice = rset.getDouble(index++);
//				boolean deleted = rset.getBoolean(index++);
//				Airport departureAirport = AirportDAO.get(i);
//				Airport arrivalAirport = AirportDAO.get(i2);
//				String departureDate = dateToString(d);
//				String arrivalDate = dateToString(d2);
//				Flight f = new Flight(id, flightNumber, departureDate, arrivalDate, departureAirport,
//						arrivalAirport, seatNumber, freeSeat, ticketPrice, deleted);
//				flights.add(f);
//			}
//
//		} catch (Exception ex) {
//			System.out.println("Greska u SQL upitu!");
//			ex.printStackTrace();
//		} finally {
//			try {
//				pstmt.close();
//			} catch (SQLException ex1) {
//				ex1.printStackTrace();
//			}
//			try {
//				rset.close();
//			} catch (SQLException ex1) {
//				ex1.printStackTrace();
//			}
//		}
//		return flights;
//	}
	
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
	
	public static boolean addFlight(Flight flight) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO flights(flightNumber,departureDate,arrivalDate,departureAirport,arrivalAirport,seatNumber,freeSeat,ticketPrice,deleted)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, flight.getFlightNumber());
			Date myDate=stringToDateForWrite(flight.getDepartureDate());
			java.sql.Date date=new java.sql.Date(myDate.getTime());
			pstmt.setDate(index++,date);
			Date myDate1=stringToDateForWrite(flight.getArrivalDate());
			java.sql.Date date1=new java.sql.Date(myDate1.getTime());
			pstmt.setDate(index++,date1);
			Airport a = flight.getDepartureAirport();
			pstmt.setInt(index++, a.getId());
			Airport b = flight.getArrivalAirport();
			pstmt.setInt(index++, b.getId());
			pstmt.setInt(index++, flight.getSeatNumber());
			pstmt.setInt(index++, flight.getFreeSeat());
			pstmt.setDouble(index++, flight.getTicketPrice());
			pstmt.setBoolean(index++, flight.isDeleted());

			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
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
	
	public static boolean updateFlight(Flight flight) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE flights SET arrivalDate = ?, departureAirport = ?, arrivalAirport = ?, seatNumber = ?,freeSeat = ?, ticketPrice = ?, deleted = ?  WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			Date myDate=stringToDateForWrite(flight.getArrivalDate());
			java.sql.Date arrivalDate=new java.sql.Date(myDate.getTime());
			pstmt.setDate(index++,arrivalDate);
			System.out.println(flight.getDepartureAirport().getId());
			System.out.println(flight.getArrivalAirport().getId());
			pstmt.setInt(index++, flight.getDepartureAirport().getId());
			pstmt.setInt(index++, flight.getArrivalAirport().getId());
			pstmt.setInt(index++, flight.getSeatNumber());
			pstmt.setInt(index++, flight.getFreeSeat());
			pstmt.setDouble(index++, flight.getTicketPrice());
			pstmt.setBoolean(index++, flight.isDeleted());
			pstmt.setInt(index++, flight.getId());

			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
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
	
	public static boolean deleteFlight(Flight flight) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE flights SET deleted = ?  WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setBoolean(index++, flight.isDeleted());
			pstmt.setInt(index++, flight.getId());

			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
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