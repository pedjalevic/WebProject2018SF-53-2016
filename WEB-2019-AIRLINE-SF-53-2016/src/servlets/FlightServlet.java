package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.AirportDAO;
import dao.FlightDAO;
import dao.TicketDAO;
import model.Airport;
import model.Flight;
import model.User;


public class FlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			HttpSession session = request.getSession();
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			Flight flight = FlightDAO.getFlight(id);
			Map<String, Object> data = new HashMap<>();
			data.put("flight", flight);
			data.put("user", loggedInUser);
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			}catch (Exception e) {
				
			}
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status=request.getParameter("status");
		if(status.equals("add")) {
			String flightNumber=request.getParameter("flightNumber");
			int da=Integer.parseInt(request.getParameter("departureAirport"));
			int aa=Integer.parseInt(request.getParameter("arrivalAirport"));
			Airport departureAirport = AirportDAO.get(da);
			Airport arrivalAirport = AirportDAO.get(aa);
			String departureDate=request.getParameter("departureDate");
			String arrivalDate=request.getParameter("arrivalDate");
			int seatNumber = Integer.parseInt(request.getParameter("seatNumber"));
			int freeSeat = Integer.parseInt(request.getParameter("freeSeat"));
			double ticketPrice = Double.parseDouble(request.getParameter("ticketPrice"));
			
			int id=FlightDAO.getFlightId();
			Flight f = new Flight(id, flightNumber, departureDate, arrivalDate, departureAirport, arrivalAirport, seatNumber, freeSeat, ticketPrice, false);
			FlightDAO.addFlight(f);
			Map<String, Object> data = new HashMap<>();
			
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}
		else if(status.equals("edit")) {
			int id=Integer.parseInt(request.getParameter("id"));
			Flight flight = FlightDAO.getFlight(id);
			int da=Integer.parseInt(request.getParameter("departureAirport"));
			int aa=Integer.parseInt(request.getParameter("arrivalAirport"));
			System.out.println(aa);
			Airport departureAirport = AirportDAO.get(da);
			Airport arrivalAirport = AirportDAO.get(aa);
			String arrivalDate=request.getParameter("arrivalDate");
			int seatNumber = Integer.parseInt(request.getParameter("seatNumber"));
			double ticketPrice = Double.parseDouble(request.getParameter("ticketPrice"));
			flight.setDepartureAirport(departureAirport);
			flight.setArrivalAirport(arrivalAirport);
			flight.setSeatNumber(seatNumber);
			flight.setTicketPrice(ticketPrice);
			flight.setArrivalDate(arrivalDate);
			try {
				int freeSeat = seatNumber - TicketDAO.getTicketNumber(id) - TicketDAO.getTicketNumber1(id);
				if (freeSeat >= 0) {
					flight.setFreeSeat(freeSeat);
				}
			} catch (Exception e) {
				status = "failure";
				
				Map<String, Object> data = new HashMap<>();
				data.put("status", status);
				ObjectMapper mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(data);
				System.out.println(jsonData);
				response.setContentType("application/json");
				response.getWriter().write(jsonData);
			}
			
			FlightDAO.updateFlight(flight);
			Map<String, Object> data = new HashMap<>();
			
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}else if(status.equals("delete")) {
			int id=Integer.parseInt(request.getParameter("flightId"));
			Flight flight = FlightDAO.getFlight(id);
			flight.setDeleted(true);
			FlightDAO.deleteFlight(flight);
			Map<String, Object> data = new HashMap<>();
			
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);
			
		}else {
			status = "failure";
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", status);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}
		
	}

}