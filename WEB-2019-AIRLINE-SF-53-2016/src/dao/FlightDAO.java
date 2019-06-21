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
	
	public static ArrayList<Flight> searchFlights(int departureAirportId, int arrivalAirportId, double ticketPrice1, double ticketPrice2,
			String data1 , String data2, String data3 , String data4, String flightNumber1, String orderByColumn, String ascDes){
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Flight> flightSearch = new ArrayList<Flight>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		System.out.println(flightNumber1);
		try {
			String query = "select * from flights where flightNumber like ? and departureDate >= ? and departureDate <= ? and arrivalDate >= ? and arrivalDate <= ? and departureAirport = ? and arrivalAirport = ? and ticketPrice >= ? and ticketPrice <= ? ORDER BY "+ orderByColumn+" "+ascDes ;
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			String flightNumber11 = "%" + flightNumber1 + "%";
			pstmt.setString(1, flightNumber11);
			Date myDate=stringToDateForWrite(data1);
			java.sql.Date date=new java.sql.Date(myDate.getTime());
			pstmt.setDate(2,date);
			Date myDate2=stringToDateForWrite(data2);
			java.sql.Date date20=new java.sql.Date(myDate2.getTime());
			pstmt.setDate(3,date20);
			Date myDate3=stringToDateForWrite(data3);
			java.sql.Date date30=new java.sql.Date(myDate3.getTime());
			pstmt.setDate(4,date30);
			Date myDate4=stringToDateForWrite(data4);
			java.sql.Date date40=new java.sql.Date(myDate4.getTime());
			pstmt.setDate(5,date40);
			pstmt.setInt(6, departureAirportId);
			pstmt.setInt(7, arrivalAirportId);
			pstmt.setDouble(8, ticketPrice1);
			pstmt.setDouble(9, ticketPrice2);
			System.out.println(pstmt);
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
				flightSearch.add(f);
				
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
		return flightSearch;
	}
	
	public static ArrayList<Flight> getReturnFlight(int id, int da, int aa, String dataD) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Flight> returnFlight = new ArrayList<Flight>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from flights where id = ? and departureAirport = ? and arrivalAirport = ? and arrivalDate >= ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setInt(2, da);
			pstmt.setInt(3, aa);
			Date myDate=stringToDateForWrite(dataD);
			java.sql.Date arrivalDateReturn=new java.sql.Date(myDate.getTime());
			pstmt.setDate(4, arrivalDateReturn);
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
				returnFlight.add(f);
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
		return returnFlight;
	}
	public static String getData(int id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select arrivalDate from flights where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int index = 1;
				Date d = rset.getDate(index);
				String d1 = FlightDAO.dateToStringForWrite(d);
				return d1;
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
	
	public static boolean dataFlight(int id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select deleted from flights where id = ? and departureDate >= ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,id);
			Date d=new Date();
			String d1 = FlightDAO.dateToStringForWrite(d);
			Date myDate=stringToDateForWrite(d1);
			java.sql.Date arrivalDate=new java.sql.Date(myDate.getTime());
			pstmt.setDate(2,arrivalDate);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int index = 1;
				boolean deleted = rset.getBoolean(index);
				return deleted;
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
		}
		return true;
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