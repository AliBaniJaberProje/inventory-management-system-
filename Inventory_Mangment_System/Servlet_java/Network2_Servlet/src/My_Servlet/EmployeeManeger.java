package My_Servlet;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SplittableRandom;

public class EmployeeManeger
{

    public EmployeeManeger() {

    }

    public int is_authorized(String username, String pass) {

        try {
            String SQLrequest = "SELECT id FROM users WHERE username=" + "'"+username+"'" + " and password=" + "'"+pass+"'";
            dbConection db_co = new dbConection();
            ResultSet respons = db_co.db_executer(SQLrequest,false);
            int id_user = -1;
            if (respons.next()) {
                System.out.println(respons.getInt(1));
                id_user = respons.getInt(1);
                db_co.close_db_con();
                Date now = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String new_access_time=""+simpleDateFormat.format(now);
                String SQLrequest_updateaccess="UPDATE users SET last_Access_time='"+new_access_time+"'WHERE id="+id_user;


                dbConection db_co2 = new dbConection();
                ResultSet respons_updateaccess = db_co2.db_executer(SQLrequest_updateaccess,true);
                db_co2.close_db_con();

            }
            return id_user;
        }
        catch (Exception e)
        {

        }
        return -1;
    }

    public String ChangePassword(int id_user , String lasrpass , String newpass )
    {
        try
        {
            String SQLrequest = "SELECT id FROM users WHERE id="+id_user+" and password='"+lasrpass+"'";
            dbConection db_co = new dbConection();
            ResultSet respons = db_co.db_executer(SQLrequest,false);
            String result="";
            if (respons.next()) {
                System.out.println(respons.getInt(1));
                if(respons.getInt(1)==id_user)
                {
                    db_co.close_db_con();
                    String SQLrequest_updateaccess="UPDATE users SET password='"+newpass+"' WHERE id="+id_user;
                    dbConection db_co2 = new dbConection();
                    ResultSet respons_updateaccess = db_co2.db_executer(SQLrequest_updateaccess,true);
                    db_co2.close_db_con();
                    return "Record updated successfully";
                }
            }
           return "error of pass";

        }
        catch (Exception e)
        {

        }
        return "";
    }




}


