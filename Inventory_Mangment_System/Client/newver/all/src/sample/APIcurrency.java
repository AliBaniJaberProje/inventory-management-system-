package sample;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIcurrency  {





    public static void get_form_online_API()
    {

            StringBuffer response = null;
        try {
            response=new StringBuffer();
            String url_to_send;
            URL obj = new URL("https://api.exchangeratesapi.io/latest?base=USD");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    System.out.println(inputLine);
                }

                in.close();
                //in.reset();
            } else {
                System.out.println("GET request not worked");
            }


            System.out.println(String.valueOf(response));
            JsonObject jsonarray_data = new JsonParser().parse(response.toString()).getAsJsonObject();
            String EUR=jsonarray_data.getAsJsonObject("rates").getAsJsonPrimitive("EUR").getAsString();
            String base=jsonarray_data.getAsJsonPrimitive("base").getAsString();
            String data_update=jsonarray_data.getAsJsonPrimitive("date").getAsString();
            System.out.println(EUR + base+ data_update);
        }
        catch (Exception e)
        {
          int j=0;
        }







    }



}
