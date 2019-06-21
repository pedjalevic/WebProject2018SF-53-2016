package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Flight;
import model.Ticket;
import model.User;

public class TicketDAO {
	
	public static ArrayList<Ticket> getUserReservation(int id) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM tickets WHERE ticketUser = ? AND sellDate is null AND deleted = ?  order by reservationDate desc";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 2;
				int f1 = rset.getInt(index++);
				Flight departureFlight = FlightDAO.getFlight(f1);
				int f2 = rset.getInt(index++);
				Flight returnFlight = FlightDAO.getFlight(f2);
				int departureSeat = rset.getInt(index++);
				int returnSeat = rset.getInt(index++);
				Date d1 = rset.getDate(index++);
				System.out.println(d1);
				Date d2 = rset.getDate(index++);
				String owner = rset.getString(index++);
				User ticketUser = UserDAO.get(owner);
				String passengerName = rset.getString(index++);
				String passengerLastName  = rset.getString(index++);
				boolean deleted = rset.getBoolean(index++);
				String reservationDate = FlightDAO.dateToString(d1);
				String sellDate = FlightDAO.dateToString(d1);
				Ticket t = new Ticket(id, departureFlight, returnFlight, departureSeat, returnSeat, reservationDate, sellDate, ticketUser, passengerName, passengerLastName, deleted);
				tickets.add(t);
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
		return tickets;
	}
	
	public static boolean departureFreeSeat(int id, int departureSeat) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select deleted from tickets where departureFlight = ? and departureSeat = ? and deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setInt(2, departureSeat);
			pstmt.setBoolean(3, false);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int index = 1;
				boolean d1 =rset.getBoolean(index);
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
		return true;
	}
	
	public static boolean returnFreeSeat(int id, int returnSeat) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select deleted from tickets where returnFlight = ? and returnSeat = ? and deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setInt(2, returnSeat);
			pstmt.setBoolean(3, false);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int index = 1;
				boolean d1 =rset.getBoolean(index);
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
		return true;
	}
	
	public static int getTicketNumber(int id) {
		Connection conn = ConnectionManager.getConnection();
		int number=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT count(departureFlight) FROM tickets where departureFlight = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();
		
			if (rset.next()) {
				number=rset.getInt(1);
				
			}
			return number;
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
	public static int getTicketNumber1(int id) {
		Connection conn = ConnectionManager.getConnection();
		int number1=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT count(returnFlight) FROM tickets where returnFlight = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();
		
			if (rset.next()) {
				number1=rset.getInt(1);
				
			}
			return number1;
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
	
	public static ArrayList<Ticket> getUserSell(int id) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM tickets WHERE ticketUser = ? AND sellDate is not null order by sellDate desc;";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 2;
				int f1 = rset.getInt(index++);
				Flight departureFlight = FlightDAO.getFlight(f1);
				int f2 = rset.getInt(index++);
				Flight returnFlight = FlightDAO.getFlight(f2);
				int departureSeat = rset.getInt(index++);
				int returnSeat = rset.getInt(index++);
				Date d1 = rset.getDate(index++);
				Date d2 = rset.getDate(index++);
				String owner = rset.getString(index++);
				User ticketUser = UserDAO.get(owner);
				String passengerName = rset.getString(index++);
				String passengerLastName  = rset.getString(index++);
				boolean deleted = rset.getBoolean(index++);
				String reservationDate = FlightDAO.dateToString(d1);
				String sellDate = FlightDAO.dateToString(d2);
				Ticket t = new Ticket(id, departureFlight, returnFlight, departureSeat, returnSeat, reservationDate, sellDate, ticketUser, passengerName, passengerLastName, deleted);
				tickets.add(t);
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
		return tickets;
	}
	
	public static boolean addTicketOneWay(Ticket ticket) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO tickets (departureFlight, departureSeat, reservationDate, ticketUser, passengerName,passengerLastName,deleted) VALUES (?, ?, ?, ? ,? ,?,?)";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, ticket.getDepartureFlight().getId());
			pstmt.setInt(index++, ticket.getDepartureSeat());
			pstmt.setString(index++, ticket.getReservationDate());
			pstmt.setInt(index++, ticket.getTicketUser().getId());
			pstmt.setString(index++, ticket.getPassengerName());
			pstmt.setString(index++, ticket.getPassengerLastName());
			pstmt.setBoolean(index++, ticket.isDeleted());
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
	public static boolean addTicketTwoWay(Ticket ticket) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO tickets (departureFlight,returnFlight,returnSeat, departureSeat, reservationDate, ticketUser, passengerName,passengerLastName,deleted) VALUES (?, ?, ?, ?, ?, ? , ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, ticket.getDepartureFlight().getId());
			pstmt.setInt(index++, ticket.getReturnFlight().getId());
			pstmt.setInt(index++, ticket.getDepartureSeat());
			pstmt.setInt(index++, ticket.getReturnSeat());
			pstmt.setString(index++, ticket.getReservationDate());
			pstmt.setInt(index++, ticket.getTicketUser().getId());
			pstmt.setString(index++, ticket.getPassengerName());
			pstmt.setString(index++, ticket.getPassengerLastName());
			pstmt.setBoolean(index++, ticket.isDeleted());
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
