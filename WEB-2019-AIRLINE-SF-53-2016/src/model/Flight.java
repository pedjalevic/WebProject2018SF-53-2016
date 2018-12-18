package model;

import java.sql.Date;

public class Flight {
	private int id;
	private String flightNumber;
	private Date departureDate;
	private Date arrivalDate;
	private Airport departureAirport;
	private Airport arrivalAirport;
	private int seatNumber;
	private double ticketPrice;
	private boolean deleted;
	
	public Flight(int id, String flightNumber, Date departureDate, Date arrivalDate, Airport departureAirport,
			Airport arrivalAirport, int seatNumber, double ticketPrice, boolean deleted) {
		super();
		this.id = id;
		this.flightNumber = flightNumber;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.seatNumber = seatNumber;
		this.ticketPrice = ticketPrice;
		this.deleted = deleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightNumber=" + flightNumber + ", departureDate=" + departureDate
				+ ", arrivalDate=" + arrivalDate + ", departureAirport=" + departureAirport + ", arrivalAirport="
				+ arrivalAirport + ", seatNumber=" + seatNumber + ", ticketPrice=" + ticketPrice + ", deleted="
				+ deleted + "]";
	}
	
}
