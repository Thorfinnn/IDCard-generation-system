

import java.sql.*;
import java.util.Scanner;

public class DataBaseConnection {
    static Connection con=null;
    static final String url="jdbc:mysql://localhost:3306/project1";
    static final String uname="root";
    static final String password="Taher@2002";


    public static Connection getConnection()
    {
        if(con!=null)
        {
            return con;
        }
        else
        {
            try
            {

                Class.forName("com.mysql.cj.jdbc.Driver");
                con=DriverManager.getConnection(url,uname,password);
                return con;
            }
            catch(Exception exp)
            {
                exp.printStackTrace();
                return con;
            }
        }
    }
    public static boolean checkconnection()
    {

        try
        {

            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(url,uname,password);
            return true;
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
            return false;
        }
    }
    public static void  closeConnection()
    {
        try {
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static boolean check(String username, String password) throws SQLException {
        if(checkconnection()){
            String query= "Select count(userid) from idlogin where username='"+username+"' AND password='"+password+"';";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            System.out.println(rs);
            if(rs.getInt(1) >0){
                return true;
            }else{
                return false;
            }
        }else{
            System.out.println("connection error");
            return false;
        }
    }
    public static boolean registration(String name,String mobileno,String id,String password,String emailid) throws SQLException{
        if (check(id, password)){
            System.out.println("id and password already exists");
            return false;
        }else{
            String query1="Select max(userid) from idlogin";
            PreparedStatement preparedStatement = con.prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int userid;
            userid=rs.getInt(1);
            userid++;
            String query= "Insert into idlogin "+"values("+userid+",'"+id+"','"+password+"');";
            PreparedStatement st = con.prepareStatement(query);
            //st.setInt(1,userid);
            //st.setString(1,id);
            //st.setString(2,password);
            st.executeUpdate();
            String query3="insert into idinfo "+"values('"+name+"',"+userid+",'"+id+"','"+emailid+"');";
            PreparedStatement st1=con.prepareStatement(query3);
            st1.executeUpdate();
            return true;
        }
    }
    public static void main(String args[]) throws SQLException {
        getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username:\t");
        String username=sc.nextLine();
        System.out.println("Enter password:\t");
        String password=sc.nextLine();
        if(check(username,password)){
            System.out.println("Login successful");
        }else{
            System.out.println("Invalid username or password");
        }
        System.out.println("Enter username:\t");
        username=sc.nextLine();
        System.out.println("Enter password:\t");
        password=sc.nextLine();
        //if(registration(username,password)){
            System.out.println("Done");
        //}else{
            System.out.println("Not done");
        //}
    }

}


