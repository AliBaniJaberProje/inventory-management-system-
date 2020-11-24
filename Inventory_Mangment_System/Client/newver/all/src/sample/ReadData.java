package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class ReadData {




    public static int show_id_input_to_inc() {

        TextInputDialog dialog1 = new TextInputDialog("id ");
        dialog1.setTitle("insert #Items of a Specific Product from Inventory");
        dialog1.setHeaderText("");
        dialog1.setContentText("Please enter  id of producct :");
        Optional<String> id_product = dialog1.showAndWait();
        if (id_product.isPresent()){
            System.out.println("Your name: " + id_product.get());
        }
        int id_product_int = -1;
        try {
            id_product_int=Integer.parseInt(id_product.get().toString());
        }
        catch (Exception e )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Please enter valid data in another attempt\n ");
            alert.setContentText(" error!");

            alert.showAndWait();

        }
        return id_product_int;

    }

    public static int show_amount_input_to_dec_id() {
        TextInputDialog dialog2 = new TextInputDialog("walter");
        dialog2.setTitle("Withdraw #Items of a Specific Product from Inventory");
        dialog2.setHeaderText("");
        dialog2.setContentText("Please enter  amount to dec in : ");
        Optional<String> aount_dec = dialog2.showAndWait();
        if (aount_dec.isPresent()){
            System.out.println("Your name: " + aount_dec.get());
        }
        int int_amount_pro=-1;
        try {
            if(Integer.parseInt(aount_dec.get().toString())<=0)
                throw new Exception();
            int_amount_pro=Integer.parseInt(aount_dec.get().toString());

        }
        catch (Exception e )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Please enter valid data in another attempt\n ");
            alert.setContentText(" error!");

            alert.showAndWait();

        }
        return int_amount_pro;
    }

    public static int show_to_read_id_to_dec() {
        TextInputDialog dialog1 = new TextInputDialog("walter");
        dialog1.setTitle("Withdraw #Items of a Specific Product from Inventory");
        dialog1.setHeaderText("");
        dialog1.setContentText("Please enter  id of producct :");
        Optional<String> id_product = dialog1.showAndWait();
        if (id_product.isPresent()){
            System.out.println("Your name: " + id_product.get());
        }
        int id_product_int = -1;
        try {
            id_product_int=Integer.parseInt(id_product.get().toString());
        }
        catch (Exception e )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Please enter valid data in another attempt\n ");
            alert.setContentText(" error!");

            alert.showAndWait();

        }
        return id_product_int;



    }

    public static int show_to_read_amount_to_dec() {

        TextInputDialog dialog2 = new TextInputDialog("walter");
        dialog2.setTitle("Withdraw #Items of a Specific Product from Inventory");
        dialog2.setHeaderText("");
        dialog2.setContentText("Please enter  amount to dec in : ");
        Optional<String> aount_dec = dialog2.showAndWait();
        if (aount_dec.isPresent()){
            System.out.println("Your name: " + aount_dec.get());
        }
        int int_amount_pro=-1;
        try {
            if(Integer.parseInt(aount_dec.get().toString())<=0)
                throw new Exception();
            int_amount_pro=Integer.parseInt(aount_dec.get().toString());

        }
        catch (Exception e )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Please enter valid data in another attempt\n ");
            alert.setContentText(" error!");

            alert.showAndWait();

        }
        return int_amount_pro;

    }

    public int show_input_id_product() {
        TextInputDialog dialog1 = new TextInputDialog("");
        dialog1.setTitle("show_info_of_product");
        dialog1.setHeaderText("");
        dialog1.setContentText("Please enter  id of producct to show info  :");
        Optional<String> id_product = dialog1.showAndWait();
        if (id_product.isPresent()){
            System.out.println("Your name: " + id_product.get());
        }
        int id_product_int = 0;
        try {
            id_product_int=Integer.parseInt(id_product.get());
        }
        catch (Exception e )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Please enter valid data in another attempt\n ");
            alert.setContentText(" error!");

            alert.showAndWait();

        }
        return id_product_int;
    }

    public String show_to_read_old_pass() {

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("old password");
        dialog.setContentText("Please enter your password:");
        Optional<String> lastpass = dialog.showAndWait();
        return  lastpass.get();
    }

    public String show_to_read_new_pass() {


        TextInputDialog dialog2 = new TextInputDialog("walter");
        dialog2.setTitle("Text Input Dialog");
        dialog2.setHeaderText("new password ");
        dialog2.setContentText("Please enter new password :");
        Optional<String> newpass = dialog2.showAndWait();
        return newpass.get();

    }


}


