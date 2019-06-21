package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.FlightDAO;
import dao.TicketDAO;
import model.Flight;
import model.Ticket;
import model.User;


public class TryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 1;
		try {
			id=Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			System.out.println("greskica");
		}
		int id2 = 1;
		try {
			id2=Integer.parseInt(request.getParameter("id2"));
		} catch (Exception e) {
			System.out.println("greskica");
		}
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		ArrayList<Flight> flightsReturn = null;
		Flight flight = FlightDAO.getFlight(id);
		String string1 = FlightDAO.getData(id);
		flightsReturn = FlightDAO.getReturnFlight(flight.getId(), flight.getDepartureAirport().getId(), flight.getArrivalAirport().getId(), string1);
		Map<String, Object> data = new HashMap<>();
		System.out.println(flightsReturn);
		data.put("flightsReturn", flightsReturn);
		data.put("flight", flight);
		data.put("user", loggedInUser);
		data.put("id", id);
		data.put("id2", id2);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);
		
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		int id=Integer.parseInt(request.getParameter("id"));
		int id2=Integer.parseInt(request.getParameter("id2"));
		int id3=Integer.parseInt(request.getParameter("departureSeat"));
		int id4=Integer.parseInt(request.getParameter("returnSeat"));
			try {
				int a = 1;
				Flight f1 = FlightDAO.getFlight(id);
				Flight f2 = FlightDAO.getFlight(id2);
				boolean fre = TicketDAO.departureFreeSeat(id, id3);
				if(fre == false) {
					Map<String, Object> data = new HashMap<>();
					data.put("status", "failure");
					ObjectMapper mapper = new ObjectMapper();
					String jsonData = mapper.writeValueAsString(data);
					System.out.println(jsonData);
	
					response.setContentType("application/json");
					response.getWriter().write(jsonData);
					}
				boolean fre1 = TicketDAO.returnFreeSeat(id2, id4);
				if(fre1 == false) {
					Map<String, Object> data = new HashMap<>();
					data.put("status", "failure");
					ObjectMapper mapper = new ObjectMapper();
					String jsonData = mapper.writeValueAsString(data);
					System.out.println(jsonData);
	
					response.setContentType("application/json");
					response.getWriter().write(jsonData);
					}
				
				Date d=new Date();
				String date=FlightDAO.dateToStringForWrite(d);
				Ticket ticket = new Ticket(a, f1, f2, id3, id4, date, date, loggedInUser, loggedInUser.getUserName(), loggedInUser.getUserName(), false);
				TicketDAO.addTicketTwoWay(ticket);
				Map<String, Object> data = new HashMap<>();
				data.put("status", "success");
				ObjectMapper mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(data);
				System.out.println(jsonData);

				response.setContentType("application/json");
				response.getWriter().write(jsonData);
				
			} catch (Exception ex) {
			}
	}	
}

