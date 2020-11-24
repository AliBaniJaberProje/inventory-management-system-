package sample;

import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


public class Controller {

    @FXML
    private Button login;

    @FXML
    ComboBox Settings;

    @FXML
    AnchorPane login_panel ;

    @FXML
    TextField inputusername;
    @FXML
    TextField inputpass;
    @FXML
    AnchorPane page1 ;
    @FXML
    TableView dataTeable;
    @FXML
    TableColumn id;
    @FXML
    TableColumn Name;
    @FXML
    TableColumn Mont;

    @FXML
    TableColumn Dollar;

    @FXML
    TableColumn shekel;
    @FXML
   Button add_data_to_table;
    @FXML
    Button add_new_product;
    @FXML
    Button withdraw_item;
    @FXML
    Button show_info_of_product;
    @FXML
    Button ChangePassword;
    String id_user="";
    ProcessResponse processresponse=new ProcessResponse();

    My_HttpURL obj_request=new My_HttpURL();
    ReadData readdata=new ReadData();
    String type_of_request="GET";
    String servername ="php";
    @FXML
    MenuButton Select_Server;

    @FXML
    MenuButton Select_Method;

    @FXML
    public void handeLAllRequest(ActionEvent  event)
    {
      if(valedation_request_functionalte()) {
          if (event.getSource() == add_data_to_table) {
              System.out.println("Show All Products in Inventory");
              add_data_to_table(type_of_request);
          }
          if (event.getSource() == add_new_product) {
              handel_add_new_product(type_of_request);
          }

          if (event.getSource() == withdraw_item) {
              handle_withdraw_item(type_of_request);
          }
          if (event.getSource() == show_info_of_product) {
              show_info_of_product(type_of_request);
          }
          if (event.getSource() == ChangePassword) {
              this.changepasswordfunction(type_of_request);
          }
      }
      else {
          processresponse.process_not_found_server_or_method();
      }


    }
    private void config_table(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Mont.setCellValueFactory(new PropertyValueFactory<>("mount"));
        Dollar.setCellValueFactory(new PropertyValueFactory<>("USD"));
        shekel.setCellValueFactory(new PropertyValueFactory<>("EUR"));
    }
    private void handle_withdraw_item(String method) {

        int id_pro=ReadData.show_to_read_id_to_dec();
        if(id_pro==-1)
            return;
        int amount=ReadData.show_to_read_amount_to_dec();
        if(amount==-1)
            return;


        StringBuffer respopons=new StringBuffer("");
        if(method.equals("GET"))
            respopons=obj_request.Send_Request_GET(servername,"dec",0,id_pro,amount,0.0,
                    null,null,null,null);
        if(method.equals("POST")){
            respopons=obj_request.Send_Request_POST(servername,"dec",0,id_pro,amount,0.0,
                    null,null,null,null);
        }
             //respopons= obj_request.sendPOST_to_dec_product(id_product_int,int_amount_pro);
        processresponse.proccess_respons_incremant(respopons,id_pro,amount);
        this.add_data_to_table(method);



    }
    private boolean valedation(String data){
        return true;

    }
    String username=null;
    String pass=null;
    @FXML
    Label Employee_ID;

    private boolean valedation_login_request(){
        if(server2.getText().equals("Select Server")||method2.getText().equals("Select Method"))
        {
            return false;
        }
        return true;
    }

    @FXML
    Label access_time;

    @FXML
    public void handlerLongIn()
    {
      if(valedation_login_request()) {
          System.out.println(inputusername.getText() + inputpass.getText());
          username = inputusername.getText();
          pass = inputpass.getText();

          boolean result1 = valedation(username);
          boolean result2 = valedation(pass);
          String id_user_log = "-1";
          int id_user_log_int = -1;
          if (result1 && result2) {
              if (type_of_request.equals("GET")) {
                  id_user_log_int = Integer.parseInt(obj_request.Send_Request_GET(servername, "login", 0, 0, 0, 0.0
                          , null, null, username, pass).toString());
                  Employee_ID.setText("" + id_user_log_int);
              }
              StringBuffer result = null;
              if (type_of_request.equals("POST")) {
                  result = obj_request.Send_Request_POST(servername, "login", 0, 0, 0, 0.0
                          , null, null, username, pass);
                  //id_user_log_int=Integer.parseInt(new String(result));
                  Employee_ID.setText(String.valueOf(result).replace("\r\n", ""));

              }

              if (!(id_user_log_int == -1) || !(String.valueOf(result).replace("\r\n", "").equals("-1"))) {
                  login_panel.setVisible(false);
                  page1.setVisible(true);

                  config_table();
                  add_data_to_table(type_of_request);
                  method.setText(type_of_request);
                  server.setText(servername);
                  Date now = new Date();
                  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");

                  access_time.setText("access-Time :"+simpleDateFormat.format(now)+" AM");
              }
              else{


                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Error Dialog");
                  alert.setHeaderText("There is no employee with this data ");
                  alert.setContentText("  Please try again");

                  alert.showAndWait();
              }
          }
      }
      else {
          processresponse.process_not_found_server_or_method();
      }

    }

    private boolean valedation_request_functionalte(){
        if(server2.getText().equals("Select Server")||method2.getText().equals("Select Method"))
        {
            return false;
        }
        return true;
    }

  @FXML
  public void php_server(){

        servername ="php";
        server.setText("PHP-Server ");
        server2.setText("PHP-Server ");
  }
  @FXML
  public void java_server()
  {
       servername ="java";
       server.setText("JAVA-Servlet");
       server2.setText("JAVA-Servlet");
  }
  @FXML
  public void post_method()
  {
      type_of_request="POST";
      method.setText("Method-POST");
      method2.setText("Method-POST");
  }
  @FXML
  public void get_method()
  {
        type_of_request="GET";
        method.setText("Method-GET");
        method2.setText("Method-GET");
  }

  @FXML
     Label method;

    @FXML
    Label server;
    @FXML
    Label method2;

    @FXML
    Label server2;



 private void add_data_to_table(String method ){


        StringBuffer response;
        if(method.equals("GET"))
         response =obj_request.Send_Request_GET(servername,"get_me_all_data",0,0,0,0.0
                 ,null,null,null,null);
        else
        {
            response =obj_request.Send_Request_POST(servername,"get_me_all_data",0,0,0,0.0
            ,null,null,null,null);
        }
        processresponse.process_allPoduct_in_Table(dataTeable,response );

    }

 private void show_info_of_product(String method) {

     int id_product=readdata.show_input_id_product();
     StringBuffer  response = null;
     if(method.equals("GET"))
         response= obj_request.Send_Request_GET(servername,"show_info_of_product",0,id_product,0,0.0, null,null,null,null);
     if(method.equals("POST"))
         response=obj_request.Send_Request_POST(servername,"show_info_of_product",0,id_product,0,0.0, null,null,null,null);

     processresponse.proccess_respons_show_info(response);


   }
 public void changepasswordfunction(String method)
 {

        String old_pass=readdata.show_to_read_old_pass();
        String new_pass=readdata.show_to_read_new_pass();


        System.out.println("changepasswordfunction done ");
        StringBuffer respons=new StringBuffer("");
        if(method.equals("GET"))
        {
            respons=obj_request.Send_Request_GET(servername,"ChangePassword",Integer.parseInt(Employee_ID.getText().replace("\r\n","")),0,0,0.0,
                    old_pass,new_pass,null,null);

        }
        if(method.equals("POST"))
        {
            respons=obj_request.Send_Request_POST(servername,"ChangePassword",Integer.parseInt(Employee_ID.getText()),0,0,0.0,
                    old_pass,new_pass,null,null);        }
        processresponse.processChangepassword( respons, old_pass,  new_pass);
        System.out.println(respons.toString());



 }

 public void handel_add_new_product(String  method ) {

       int id_pro=ReadData.show_id_input_to_inc();
       if(id_pro==-1)
           return;
       int amount=ReadData.show_amount_input_to_dec_id();
       if(amount==-1)
           return;

        StringBuffer respopons=new StringBuffer("");
       if(method.equals("GET"))
            respopons=obj_request.Send_Request_GET(servername,"inc",0,id_pro,amount,0.0,
                    null,null,null,null);
       if(method.equals("POST"))
            respopons=obj_request.Send_Request_POST(servername,"inc",0,id_pro,amount,0.0,
                    null,null,null,null);
       processresponse.proccess_respons_incremant(respopons,id_pro,amount);

       this.add_data_to_table(method);


    }


 public void logoutfunction()
 {
        page1.setVisible(false);
        login_panel.setVisible(true);
        method.setText("Select Method");
        method2.setText("Select Method");
        server.setText("Select Server");
        server2.setText("Select Server");
        inputusername.setText("");
        inputpass.setText("");
 }


}
