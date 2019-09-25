package VersionModule;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.User;
import MongoDb.DbConnection;

public class StoreVersionDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String version = request.getParameter("version");
		String url = request.getParameter("url");
		
		DbConnection dbConn = new DbConnection();
		String token = dbConn.readToken();
		token = request.getParameter("token");

		if ((version == null || version.equals("")) || (url == null || url.equals(""))) {

			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			out.print("Mandatory parameters are missing");
			out.flush();
		}
		else if((token == null || token.equals(""))){
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			out.print("Unauthorized Access, token is missing");
			out.flush();
			
		}

		else {

			User user = new User();
			user.setVersion(version);
			user.setUrl(url);

			DbConnection dbConnection = new DbConnection();
			dbConnection.createDocument(user);
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			out.print("Successfully added to the database");
			out.flush();

		}
	}
	
}
