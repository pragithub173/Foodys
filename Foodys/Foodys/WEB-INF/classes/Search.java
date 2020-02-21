import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
//@WebServlet("/Search")
public class Search extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        Hashtable<String,Restuarants> hm = new Hashtable<String,Restuarants>();

        PrintWriter pw = response.getWriter();


        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodys","root","Naanu");

            PreparedStatement ps = con.prepareStatement("select * from restaurants order by name");


            // out.println("<body style='background-color:#8B0000;'>");
           // out.print("<body background-color = black");
            // out.print("<center><h1>Result:</h1></center>");

            ResultSet rs = ps.executeQuery();

            /* Printing column names */

            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next())

            {
                // out.print("<table style: border-collapse= collapse border =  1px solid black; align = center>");
                // out.print("<tr>");


                //  out.print("<th rowspan = 5><img src=" + rs.getString(2) + " width = 200 height = 200></th>");


                // out.print("<td>" + rs.getString(1) + "</td></tr>");




                // out.print("<tr><td>" + rs.getString(3) + "</td></tr>");


                // out.print("<tr><td>" + rs.getString(4) + "</td></tr>");


                // out.print("<tr><td>" + rs.getString(5) + "</td></tr>");


                // out.print("<tr><td width = 400>" + rs.getString(6) + "</td></tr>");
                // out.print("</table>");

                hm.put(rs.getString(2),new Restuarants(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));

            }

            
        //out.print(h.get("Seoul Taco").getname());
        Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		pw.print("<div id='container' align='center'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + "Restuarants" + " </a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Restuarants> entry : hm.entrySet()) {
			Restuarants Restuarants = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + Restuarants.getname() + "</h3>");
            pw.print("<strong>" + Restuarants.getaddress() + "$</strong><ul>");
            pw.print("<h3>" + Restuarants.getrating() + "</h3>");
			pw.print("<li id='item'><img src='"
					+ Restuarants.getimg_url() + "' alt='' /></li>");
			pw.print("<li><form method='post' action='TabletList'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='restuarants'>"+
                    "<input type='hidden' name='restuarantsname' value='"+Restuarants.getname()+"'>"+
                    "<input type='hidden' name='restuarantsaddress' value='"+Restuarants.getaddress()+"'>"+
                    "<input type='hidden' name='latitude' value='"+Restuarants.getlatitude()+"'>"+
                    "<input type='hidden' name='longitude' value='"+Restuarants.getlongitude()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy From this Restuarant'></form></li>");
			// pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
			// 		"<input type='hidden' name='type' value='tablets'>"+
			// 		"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
			// 		"<input type='hidden' name='price' value='"+Tablet.getPrice()+"'>"+
			// 		"<input type='hidden' name='access' value=''>"+
			// 	    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			// pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
			// 		"<input type='hidden' name='type' value='tablets'>"+
			// 		"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
			// 		"<input type='hidden' name='access' value=''>"+
			// 	    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		//utility.printHtml("Footer.html");

        } catch (Exception e2)

        {

            e2.printStackTrace();

        } finally {
            pw.close();

        }

    }



}