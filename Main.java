import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;

@WebServlet("/Main")
public class Main extends javax.servlet.
  http.HttpServlet implements javax.servlet.Servlet {
  static final long serialVersionUID = 1L;
   
  public Main () {
    super();
  }   	
	
  protected void doGet(HttpServletRequest request, 
    HttpServletResponse response)throws ServletException, IOException {

	response.setContentType("text/html");
	PrintWriter out=response.getWriter();
    String username=request.getParameter("username");
        String password=request.getParameter("password");
        String usercode=request.getParameter("usercode");
    try {
	Class.forName("com.mysql.jdbc.Driver").newInstance();

	Connection con = DriverManager.getConnection(
	  "jdbc:mysql://localhost:3306/authSystem?user=root&password=jhonadavieddsouza@2019");
	Statement stmt = con.createStatement();
	//Note that the variable is not part of the string.
	
	PreparedStatement ps = con.prepareStatement("select * from accountinfo");
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
	 String Username = rs.getString("username");
	     String Password=rs.getString("password");
	     String Usercode=rs.getString("usercode");
	     if(Username.equals(username)&&Password.equals(password)&&Usercode.equals(usercode))
	     {
	    	 response.sendRedirect("Success.html");
	    	 return;
	     }
	     else if (!Username.equals(username)&&Password.equals(password)&&Usercode.equals(usercode))
	      {
	    		response.sendRedirect("Username.html");
	    		return;
	    	} 
	     else if (Username.equals(username)&&!Password.equals(password)&&Usercode.equals(usercode))
	     {
	    	 response.sendRedirect("Password.html");
	    	 return;
	     }
	     else if(Username.equals(username)&&Password.equals(password)&&!Usercode.equals(usercode))
	     {
	    	response.sendRedirect("Passcode.html");
	    	return;
	     }
	     else
	     {
	response.sendRedirect("error.html");
	return;
	     }
    }
    }catch (Exception ex) {
	System.out.println(ex);
	System.exit(0);
    }
  }  	
	
  protected void doPost(HttpServletRequest request, 
    HttpServletResponse response)throws ServletException, IOException {
    // TODO Auto-generated method stub
  }   	  	    
}
