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
import dao.UserDAO;
import model.Ticket;
import model.User;
import model.User.Role;



public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			System.out.println(loggedInUser);
			String username=request.getParameter("userName");
			User owner = UserDAO.get(username);
			ArrayList<Ticket> reservationTicket = TicketDAO.getUserReservation(loggedInUser.getId());
			ArrayList<Ticket> sellTicket = TicketDAO.getUserSell(loggedInUser.getId());
			Map<String, Object> data = new HashMap<>();
			data.put("owner", owner);
			data.put("reservationTicket", reservationTicket);
			data.put("sellTicket", sellTicket);
			data.put("user", loggedInUser);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			System.out.println(jsonData);
		}catch (Exception e) {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status=request.getParameter("status");
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		String userName1=request.getParameter("userName");
		User u =UserDAO.get(userName1);
		if(status.equals("edit")) {
			if (loggedInUser.getRole().equals("USER")) {
				String name=request.getParameter("name");
				String password=request.getParameter("password");
				String userName=request.getParameter("userName");
				u =UserDAO.get(userName);
				u.setUserName(name);
				u.setUserPassword(password);
			}
			else {
				String name=request.getParameter("name");
				String password=request.getParameter("password");
				String role=request.getParameter("role");
				boolean blocked=Boolean.parseBoolean(request.getParameter("blocked"));
				Role r;
				if(role.equals("user")) {
					r=Role.USER;
				}
				else {
					r=Role.ADMIN;
				}
				String userName=request.getParameter("userName");
				u =UserDAO.get(userName);
				u.setUserName(name);
				u.setUserPassword(password);
				u.setRole(r);
				u.setBlocked(blocked);
			}
			UserDAO.updateUser(u);
			Map<String, Object> data = new HashMap<>();
			
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}else if(status.equals("delete")) {
			String userName=request.getParameter("userName");
			u =UserDAO.get(userName);
			u.setDeleted(true);
			UserDAO.updateUser(u);
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}
	}

}