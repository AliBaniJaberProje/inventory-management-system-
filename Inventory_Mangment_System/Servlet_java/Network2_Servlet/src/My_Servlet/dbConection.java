package My_Servlet;

import java.sql.*;

public class dbConection {
    Connection con;
    public dbConection(){
        con=null;
    }
    public  ResultSet db_executer(String SQL,boolean update_query ){
        try{
            Class.forName("com.mysql.jdbc.Driver");
             con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/api","root","");
            Statement stmt=con.createStatement();
            ResultSet rs=null;
            if(!update_query)
                rs=stmt.executeQuery(SQL);
            else
                stmt.executeUpdate(SQL);

            //con.close();
            return rs;
        }catch(Exception e){ System.out.println(e);}
        return null;
    }

    public void close_db_con(){
       try {
           con.close();
       }
       catch (Exception e)
       {

       }
    }

}






