

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.Weeks;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONObject;

/**
 * Servlet implementation class GetAge
 */
@WebServlet("/GetAge")
public class GetAge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(getMilesAge().toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private JSONObject getMilesAge(){
		JSONObject milesAge = new JSONObject();
		DateTime birthDate = DateTime.parse("11/11/2016 00:09:00", DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));		
		LocalDateTime localDateTime = new LocalDateTime(DateTimeZone.forID("America/Chicago")); 
		
		System.out.println(birthDate.toString());
		System.out.println(localDateTime.toString());
		
		Period p = new Period(birthDate, localDateTime.toDateTime());
		
		int years = p.getYears();
		
		p = new Period(birthDate.minusYears(years), localDateTime.toDateTime());
		
		int months = p.getMonths();
		
		p = new Period(birthDate.minusMonths(months), localDateTime.toDateTime());
		
		int weeks = p.getWeeks();
		
		p = new Period(birthDate.minusWeeks(weeks), localDateTime.toDateTime());
		
		int days = p.getDays();
		
		p = new Period(birthDate.minusDays(days), localDateTime.toDateTime());
		
		int hours = p.getHours();
		
		p = new Period(birthDate.minusHours(hours), localDateTime.toDateTime());
		
		int minutes = p.getMinutes();
		
		milesAge.put("local Time", localDateTime.toString());
		milesAge.put("birth Date", birthDate.toString());
		
		milesAge.put("years", String.valueOf(years));
		milesAge.put("months", String.valueOf(months));
		milesAge.put("weeks",String.valueOf(weeks));
		milesAge.put("days", String.valueOf(days));
		milesAge.put("hours", String.valueOf(hours));
		milesAge.put("minutes", String.valueOf(minutes));
		
		String w;
		String m;
		String d;
		String h;
		String min;
		
		if (months > 1){
			m = "months";
		}else{
			m = "month";
		}
		
		if (weeks > 1){
			w = "weeks";
		}else{
			w = "week";
		}
		if(days > 1){
			d = "days";
		}else{
			d = "day";
		}
		if (minutes > 1){
			min = "minutes";
		}else{
			min = "minute";
		}
		if(hours > 1){
			h = "hours";
		}else{
			h = "hour";
		}
				
		String speech = "Sweet baby Miles is " 
						+ String.valueOf(years) + " years, "
						+ String.valueOf(months) + " " + m + ", "
						+ String.valueOf(weeks) + " " + w + ", "
						+ String.valueOf(days) + " " + d + ", "
						+ String.valueOf(hours) + " " + h + " and "
						+ String.valueOf(minutes) + " " + min + " "
						+ "old";
		
		JSONObject top = new JSONObject();
		
		JSONObject body = new JSONObject();
		
		body.put("speech", speech);
		body.put("displayText", speech);
		//body.put("data", milesAge);
		//body.put("contextOut", "");
		body.put("source", "me");
		
		top.put("Body", body);
				
		return body;
	}

}
