package VersionModule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import Model.User;
import MongoDb.DbConnection;

public class GetVersionDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DbConnection dbConn = new DbConnection();
		String token = dbConn.readToken();
		String passToken = request.getParameter("token");

		if (passToken != null && passToken.equals(token))

		{
			List<User> user = dbConn.readAll();
			Gson gson = new Gson();
			String jsonStr = gson.toJson(user);

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(jsonStr);
			out.flush();
		}

		else

		{
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print("Unauthorized Access,Token expired or not found");
			out.flush();
		}

	}

}
