package My_Servlet;

import com.google.gson.Gson;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class ProductManeger {

    public String  selectAllProduct()
    {
        try
        {
            String SQLrequest = "SELECT id_product,name_pro ,amount,pricPerItem FROM product ORDER  BY id_product";
            dbConection db_co = new dbConection();
            ResultSet respons = db_co.db_executer(SQLrequest,false);
            String result="[ ";
            Gson gson = new Gson();
            ArrayList<HashMap<String,String>> all_pro_json=new ArrayList<HashMap<String,String>>();
            while (respons.next())
            {
                HashMap<String,String> entity= new HashMap<String,String>();
                entity.put("id_product",respons.getInt(1)+"");
                entity.put("name_pro",respons.getString(2));
                entity.put("amount",respons.getString(3));
                entity.put("pricPerItem",respons.getString(4));
                all_pro_json.add(entity);

                //result+="{ id_product :"+respons.getInt(1)+",name_pro :"+respons.getString(2)+",amount : "+respons.getString(3)+" , pricPerItem:"+respons.getString(4) +"},";

            }
            //result+="]";
            result = gson.toJson(all_pro_json);
            db_co.close_db_con();
            return result;

        }
        catch (Exception e)
        {

        }
        return null;



    }

    public String insert_amount(int id_pro, int insertion_amount)
    {
        try {
            String SQLrequest = "SELECT amount FROM product WHERE id_product="+id_pro;
            dbConection db_co = new dbConection();
            ResultSet respons = db_co.db_executer(SQLrequest,false);
            int last_amount = -1;
            if (respons.next()) {
                    System.out.println(respons.getInt(1));
                    last_amount = respons.getInt(1);
                    db_co.close_db_con();

                    int new_amount=last_amount+insertion_amount;

                    db_co.close_db_con();
                    String SQLrequest_updateaccess="UPDATE product SET amount='"+new_amount+"' WHERE id_product="+id_pro;
                    dbConection db_co2 = new dbConection();
                    ResultSet respons_updateaccess = db_co2.db_executer(SQLrequest_updateaccess,true);
                    db_co2.close_db_con();
                    return "Record updated successfully";



            }
            return "not found ";
        }
        catch (Exception e)
        {

        }
        return null;



    }

    public String delete_amount(int id_pro, int delete_amount)
    {

        try {
            String SQLrequest = "SELECT amount FROM product WHERE id_product="+id_pro;
            dbConection db_co = new dbConection();
            ResultSet respons = db_co.db_executer(SQLrequest,false);
            int last_amount = -1;
            if (respons.next()) {
              //  System.out.println(respons.getInt(1));
                last_amount = respons.getInt(1);
                db_co.close_db_con();

                int new_amount=last_amount-delete_amount;
                if(new_amount>=0) {
                    db_co.close_db_con();
                    String SQLrequest_updateaccess = "UPDATE product SET amount='" + new_amount + "' WHERE id_product=" + id_pro;
                    dbConection db_co2 = new dbConection();
                    ResultSet respons_updateaccess = db_co2.db_executer(SQLrequest_updateaccess, true);
                    db_co2.close_db_con();
                    return "Record updated successfully";
                }
                return "cant do this operation";


            }
            return "not found ";
        }
        catch (Exception e)
        {

        }
        return null;

    }

    public String show_info_of_product(int id_pro)
    {
        try
        {
            String SQLrequest = "SELECT * FROM product where id_product="+id_pro;
            dbConection db_co = new dbConection();
            ResultSet respons = db_co.db_executer(SQLrequest,false);
            String result;
            ArrayList<HashMap<String,String>> all_pro_json=new ArrayList<HashMap<String,String>>();
            if (respons.next())
            {    Gson gson = new Gson();
                    HashMap<String,String> entity= new HashMap<String,String>();
                    entity.put("id_product",respons.getInt(1)+"");
                    entity.put("name_pro",respons.getString(2));
                    entity.put("amount",respons.getString(3));
                    entity.put("pricPerItem",respons.getString(4));
                    all_pro_json.add(entity);
                result=  gson.toJson(all_pro_json);
                db_co.close_db_con();
                return result;
            }
            else
            {
                db_co.close_db_con();
               return  "not found";
            }


        }
        catch (Exception e)
        {

        }
        return null;



    }






}
