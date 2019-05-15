package com.dronerecon.ws;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class PortalDBService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		PrintWriter out = response.getWriter();

		String areaID = request.getParameter("area_id");
		int tileX = Integer.parseInt(request.getParameter("tilex"));
		int tileY = Integer.parseInt(request.getParameter("tiley"));
		int r = Integer.parseInt(request.getParameter("r"));
		int g = Integer.parseInt(request.getParameter("g"));

		DBManager oDBManager = new DBManager();

		// Set DB location (Currently uses current DB file name and adds direct path
		// from C drive before it).
		oDBManager.DBLocation = System.getProperty("catalina.base") + "\\webapps\\dronereconportal\\db\\"
				+ oDBManager.DBLocation;

		oDBManager.insertAreaGridTile(areaID, tileX, tileY, r, g);

		// Response with confirmation of DB record written.
		out.println("{\"success\":true}");
	}
}
