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

import dao.FlightDAO;
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
		doGet(request, response);
	}

}
