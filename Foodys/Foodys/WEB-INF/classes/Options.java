import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
public class Options extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        Hashtable<String,Restuarants> hm = new Hashtable<String,Restuarants>();

        PrintWriter pw = response.getWriter();


        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Quickchef","root","root");
            PreparedStatement ps = con.prepareStatement("select * from restaurants");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next())

            {
               hm.put(rs.getString(1),new Restuarants(rs.getString(7),rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
        pw.print("<form name ='Options' action='OptionsReview' method='post'>");
                pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Review</a>");
		pw.print("</h2><div class='entry'>");
                	pw.print("<table><tr></tr><tr></tr><tr><td>Rating: </td>");
					pw.print("<td>");
						pw.print("<select name='reviewrating'>");
						pw.print("<option value='1' selected>1</option>");
						pw.print("<option value='2'>2</option>");
						pw.print("<option value='3'>3</option>");
						pw.print("<option value='4'>4</option>");
						pw.print("<option value='5'>5</option>");  
					pw.print("</td></tr>");
				
					pw.print("<tr>");
					pw.print("<td>Restuarant Name: </td>");
					pw.print("<td> <input type='text' name='zipcode'> </td>");
			        pw.print("</tr>");		


					pw.print("<tr>");
					pw.print("<td> Restuarant City: </td>");
					pw.print("<td> <input type='text' name='retailercity'> </td>");
			        pw.print("</tr></table>");

                pw.print("</h2></div></div></div>");		
		utility.printHtml("Footer.html");

        } catch (Exception e2)

        {

            e2.printStackTrace();

        } finally {
            pw.close();

        }

    }



}