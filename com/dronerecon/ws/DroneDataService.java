package com.dronerecon.ws;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.URL;

/**
 *
 * @author Wesley Socia
 */
public class DroneDataService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		PrintWriter out = response.getWriter();

		String areaID = request.getParameter("area_id");
		int tileX = Integer.parseInt(request.getParameter("tilex"));
		int tileY = Integer.parseInt(request.getParameter("tiley"));
		int totalCols = Integer.parseInt(request.getParameter("totalcols"));
		int totalRows = Integer.parseInt(request.getParameter("totalrows"));
		String sDirection = "right";
		int r = Integer.parseInt(request.getParameter("r"));
		int g = Integer.parseInt(request.getParameter("g"));

		try {
			URL url = new URL(
					"http://127.0.0.1:8080/dronereconportal/PortalDBService?area_id=" + areaID + "&tilex=" + tileX + "&tiley=" + tileY + "&r=" + r + "&g=" + g);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out
					.println("An error occurred in callWeatherWebService() in WeatherServiceManager: " + ex.toString());
		}
		
		// Set drone direction based on row
		if (tileY % 2 == 0) {
			if (tileX == totalCols - 1) {
				sDirection = "left";
				tileY++;
			} else {
				tileX++;
				sDirection = "right";
			}
		} else {
			if (tileX == 0) {
				sDirection = "right";
				tileY++;
			} else {
				tileX--;
				sDirection = "left";
			}
		}

		// Set drone to stop if it reaches end
		if (tileY == totalRows) {
			sDirection = "stop";
		}

		out.println("{\"area_id\":\"" + areaID + "\",\"nextTileX\":\"" + tileX + "\",\"nextTileY\":\"" + tileY
				+ "\",\"direction\":\"" + sDirection + "\"}");

	}
}
