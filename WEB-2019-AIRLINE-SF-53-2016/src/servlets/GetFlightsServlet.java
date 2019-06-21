package servlets;

import java.io.IOException;
import java.util.ArrayList;
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
import dao.UserDAO;
import model.Airport;
import model.Flight;
import model.User;


public class GetFlightsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String status = request.getParameter("status") + "1";
		System.out.println(status);
		if(status.equals("search1")) {
			try {
				String flightNumber = request.getParameter("flightNumber");
				String orderByColumn = request.getParameter("orderbyValue");
				String ascDes = request.getParameter("ascdesValue");
				int departureAirport=Integer.parseInt(request.getParameter("departureAirport"));
				int arrivalAirport=Integer.parseInt(request.getParameter("arrivalAirport"));
				String departureDate1=request.getParameter("departureDate1");
				String departureDate2=request.getParameter("departureDate2");
				String arrivalDate1=request.getParameter("arrivalDate1");
				String arrivalDate2=request.getParameter("arrivalDate2");
				double ticketPrice1 = Double.parseDouble(request.getParameter("ticketPrice1"));
				double ticketPrice2 = Double.parseDouble(request.getParameter("ticketPrice2"));
				ArrayList<Flight> flightSearch = null;
				flightSearch = FlightDAO.searchFlights(departureAirport, arrivalAirport, ticketPrice1, ticketPrice2, departureDate1, departureDate2, arrivalDate1, arrivalDate2, flightNumber, orderByColumn, ascDes);
				ArrayList<User> users = UserDAO.getAll();
				HttpSession session = request.getSession();
				User loggedInUser = (User) session.getAttribute("loggedInUser");
				ArrayList<Flight> flights = null;
	
				flights = FlightDAO.allFlight();
				Map<String, Object> data = new HashMap<>();
				ArrayList<Airport> airports = AirportDAO.getAll();
				data.put("flights", flights);
				data.put("users", users);
				data.put("airports", airports);
				data.put("user", loggedInUser);
				data.put("flightSearch", flightSearch);
				ObjectMapper mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(data);
				System.out.println(jsonData);
				
				response.setContentType("application/json");
				response.getWriter().write(jsonData);
	
			} catch (Exception e) {
				System.out.println(e);
			}
		}else {
			ArrayList<User> users = UserDAO.getAll();
			HttpSession session = request.getSession();
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			ArrayList<Flight> flights = null;
			flights = FlightDAO.allFlight();
			Map<String, Object> data = new HashMap<>();
			ArrayList<Airport> airports = AirportDAO.getAll();
			data.put("flights", flights);
			data.put("users", users);
			data.put("airports", airports);
			data.put("user", loggedInUser);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);
			
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}