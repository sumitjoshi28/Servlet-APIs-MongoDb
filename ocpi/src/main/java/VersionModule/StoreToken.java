package VersionModule;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.User;
import MongoDb.DbConnection;

public class StoreToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SecureRandom random = new SecureRandom();
	
	public String generator(String token) {

		long longToken = Math.abs(random.nextLong());
		String random = Long.toString(longToken, 16);
		return (":" + random);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String cpoId = request.getParameter("cpoId");
			User user = new User();
			String token = generator(cpoId);
			user.setCpoId(token);
			
			DbConnection dbConnection = new DbConnection();
			dbConnection.createToken(user);

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print("Token generated");
			out.flush();
		}

	}

