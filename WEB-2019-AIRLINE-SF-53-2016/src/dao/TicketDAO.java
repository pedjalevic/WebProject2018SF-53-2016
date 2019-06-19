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

}
