package sample;
//import com.google.gson.Gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProcessResponse {


    String Oline_API_EUR() {
        StringBuffer response = null;
        String EUR = null;
        try {
            response = new StringBuffer();
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
             EUR = jsonarray_data.getAsJsonObject("rates").getAsJsonPrimitive("EUR").getAsString();
            String base = jsonarray_data.getAsJsonPrimitive("base").getAsString();
            String data_update = jsonarray_data.getAsJsonPrimitive("date").getAsString();
            System.out.println(EUR + base + data_update);
        } catch (Exception e) {
            int j = 0;
        }
        return EUR;
    }

    public void process_allPoduct_in_Table(TableView dataTeable, StringBuffer response) {

//        APIcurrency obj_APIcurrency=new APIcurrency();
        APIcurrency.get_form_online_API();//update value if change
//
        Float eur=Float.parseFloat(Oline_API_EUR());
//        String update_currency=APIcurrency.get_form_update_date();
//        String base_values=APIcurrency.getBase();

        dataTeable.getItems().clear();
        String id_respons="";
        String name_respons="";
        String amount_respons="";
        String pricPerItem_respons="";


        Record_In_Teable in_data;

        JsonArray jsonObject4 = new JsonParser().parse(response.toString()).getAsJsonArray();


            for (int i = 0; i < jsonObject4.size(); i++) {
                JsonObject g =(JsonObject)jsonObject4.get(i);
                amount_respons = g.getAsJsonPrimitive("amount").getAsString();

                pricPerItem_respons =  g.getAsJsonPrimitive("pricPerItem").getAsString();
                id_respons= g.getAsJsonPrimitive("id_product").getAsString();
                name_respons= g.getAsJsonPrimitive("name_pro").getAsString();
                Float re=Float.parseFloat(pricPerItem_respons)*eur;
                float x=re;
              //  double r=Math.(x);
                x=Math.round(x);
                dataTeable.getItems().add(new Record_In_Teable("" + id_respons, "" + name_respons, "" + amount_respons, "" + pricPerItem_respons,
                        ""+re));

            }
        }
    public void proccess_respons_show_info(StringBuffer response) {

        if(response.toString().equals("not found"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("the product is not exist");
            alert.setContentText("pleas enther id exist");

            alert.showAndWait();
        }
        else{

            //String result[]=response.toString().split(",");
            String id_respons="";
            String name_respons="";
            String amount_respons="";
            String pricPerItem_respons="";

            Record_In_Teable in_data;
            JsonArray jsonarray_data = new JsonParser().parse(response.toString()).getAsJsonArray();

            JsonObject jsonObject4 = (JsonObject) jsonarray_data.get(0);
                amount_respons = jsonObject4.getAsJsonPrimitive("amount").getAsString();

                pricPerItem_respons =  jsonObject4.getAsJsonPrimitive("pricPerItem").getAsString();
                id_respons= jsonObject4.getAsJsonPrimitive("id_product").getAsString();
                name_respons= jsonObject4.getAsJsonPrimitive("name_pro").getAsString();
               // "" + id_respons, "" + name_respons, "" + amount_respons, "" + pricPerItem_respons, +"20";

            Float eur=Float.parseFloat(Oline_API_EUR());




            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText(
                    "   id_product          : "+id_respons+
                    "\n name_product        : "+name_respons+
                    "\n amount              : "+amount_respons+
                    "\n pricPerItem in $    : "+pricPerItem_respons +"$"+
                    "\n pricPerItem in EUR  : "+Float.parseFloat(pricPerItem_respons)*eur);

            alert.showAndWait();




        }
    }
    public void processChangepassword(StringBuffer respons,String lastpass, String newpass) {

        if(respons.toString().replace("\r","").replace("\n","").equals("Record updated successfully"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("password change to "+" [ "+newpass+"]");
            alert.showAndWait();

        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR  Dialog");
            alert.setHeaderText("ERROR ");
            alert.setContentText("last  password not correct !");
            alert.showAndWait();
        }

    }
    public void proccess_respons_incremant(StringBuffer respopons, int id_pro, int amount) {

        if(respopons.toString().equals("cant do this operation")){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("the amount is not enough");
            alert.setContentText("Please enter a amount of the product");

            alert.showAndWait();

        }
         if(respopons.toString().equals("not found "))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("There is no product with this id "+id_pro+"it\n");
            alert.setContentText("Please enter a amount of the product");

            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(" update the product information successfully");
            alert.setContentText(" successfully  ");

            alert.showAndWait();
        }



    }
    public void process_not_found_server_or_method() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("access database error ");
        alert.setContentText("please select server and method to access database ");

        alert.showAndWait();
    }

}
