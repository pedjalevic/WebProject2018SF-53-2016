package model;


public class Ticket {
	private int id;
	private Flight departureFlight;
	private Flight returnFlight;
	private int departureSeat;
	private int returnSeat;
	private String reservationDate;
	private String sellDate;
	private User ticketUser;
	private String passengerName;
	private String passengerLastName;
	private boolean deleted;
	
	
	public Ticket(int id, Flight departureFlight, Flight returnFlight, int departureSeat, int returnSeat,
			String reservationDate, String sellDate, User ticketUser, String passengerName, String passengerLastName,
			boolean deleted) {
		super();
		this.id = id;
		this.departureFlight = departureFlight;
		this.returnFlight = returnFlight;
		this.departureSeat = departureSeat;
		this.returnSeat = returnSeat;
		this.reservationDate = reservationDate;
		this.sellDate = sellDate;
		this.ticketUser = ticketUser;
		this.passengerName = passengerName;
		this.passengerLastName = passengerLastName;
		this.deleted = deleted;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Flight getDepartureFlight() {
		return departureFlight;
	}


	public void setDepartureFlight(Flight departureFlight) {
		this.departureFlight = departureFlight;
	}


	public Flight getReturnFlight() {
		return returnFlight;
	}


	public void setReturnFlight(Flight returnFlight) {
		this.returnFlight = returnFlight;
	}


	public int getDepartureSeat() {
		return departureSeat;
	}


	public void setDepartureSeat(int departureSeat) {
		this.departureSeat = departureSeat;
	}


	public int getReturnSeat() {
		return returnSeat;
	}


	public void setReturnSeat(int returnSeat) {
		this.returnSeat = returnSeat;
	}


	public String getReservationDate() {
		return reservationDate;
	}


	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}


	public String getSellDate() {
		return sellDate;
	}


	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}


	public User getTicketUser() {
		return ticketUser;
	}


	public void setTicketUser(User ticketUser) {
		this.ticketUser = ticketUser;
	}


	public String getPassengerName() {
		return passengerName;
	}


	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}


	public String getPassengerLastName() {
		return passengerLastName;
	}


	public void setPassengerLastName(String passengerLastName) {
		this.passengerLastName = passengerLastName;
	}


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	@Override
	public String toString() {
		return "Ticket [id=" + id + ", departureFlight=" + departureFlight + ", returnFlight=" + returnFlight
				+ ", departureSeat=" + departureSeat + ", returnSeat=" + returnSeat + ", reservationDate="
				+ reservationDate + ", sellDate=" + sellDate + ", ticketUser=" + ticketUser + ", passengerName="
				+ passengerName + ", passengerLastName=" + passengerLastName + ", deleted=" + deleted + "]";
	}
	
}
