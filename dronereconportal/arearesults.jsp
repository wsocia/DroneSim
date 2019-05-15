<%@ page import="com.dronerecon.ws.AreaGridTile" %>
<%@ page import="com.dronerecon.ws.DBManager" %>
<%@ page import="java.util.ArrayList" %>

<%
String areaID = (request.getParameter("area_id"));
DBManager manager = new DBManager();
manager.DBLocation = System.getProperty("catalina.base") + "\\webapps\\dronereconportal\\db\\" + manager.DBLocation;
ArrayList<AreaGridTile> tileList = manager.readAreaGridTiles(areaID);

AreaGridTile temp = tileList.get(0);

for (int i = 1; i < tileList.size(); i++)
{
	if (tileList.get(i).r > temp.r)
	{
		temp = tileList.get(i);
	}
}

int highRx = temp.x;
int highRy = temp.y;

for (int i = 1; i < tileList.size(); i++)
{
	if (tileList.get(i).g > temp.g)
	{
		temp = tileList.get(i);
	}
}

int highGx = temp.x;
int highGy = temp.y;

out.print("Highest R value: " + highRx + "," + highRy);
%>
</br>
<%
out.print("Highest G value: " + highGx + "," + highGy);
%>