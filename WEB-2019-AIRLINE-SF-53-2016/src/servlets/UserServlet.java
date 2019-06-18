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


import dao.TicketDAO;
import model.Ticket;
import model.User;



public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			ArrayList<Ticket> reservationTicket = TicketDAO.getUserReservation(loggedInUser.getId());
			ArrayList<Ticket> sellTicket = TicketDAO.getUserSell(loggedInUser.getId());
			Map<String, Object> data = new HashMap<>();
			data.put("user", loggedInUser);
			data.put("reservationTicket", reservationTicket);
			data.put("sellTicket", sellTicket);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}catch (Exception e) {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		}
	}
