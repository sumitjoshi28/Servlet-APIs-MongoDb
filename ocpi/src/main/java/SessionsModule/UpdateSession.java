package SessionsModule;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MongoDb.DbConnection;

public class UpdateSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DbConnection dbConn = new DbConnection();
		PrintWriter out = response.getWriter();

		String token = dbConn.readToken();
		String paramtoken = request.getHeader("token");

		if (paramtoken != null && paramtoken.equals(token)) {
			dbConn.updateSession();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print("Session updated with the given changes");
		} else {
			out.println("Unauthorize accessed Token not found");
		} 
	}

}
