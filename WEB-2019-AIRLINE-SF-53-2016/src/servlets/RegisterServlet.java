package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.FlightDAO;
import dao.UserDAO;
import model.User;
import model.User.Role;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("userName");
		String userPassword = request.getParameter("password");
		try {
			User user = UserDAO.get(username);
			if (user != null) throw new Exception("Error");
			Date d=new Date();
			String date=FlightDAO.dateToStringForWrite(d);
			User newUser=new User(username, userPassword, date, Role.USER, false, false);
			UserDAO.addUser(newUser);
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
