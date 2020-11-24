<?php
require_once __DIR__.'/config.php';

class APIP{

    function Select()
    {

        $db=new Connect();
        $item_of_product=array();
        $data=$db->prepare('SELECT * FROM product ORDER  BY id_product');
        $data->execute();
        while ($outputdata = $data->fetch(PDO::FETCH_ASSOC))
        {
            $item_of_product[]=array(
                'id_product'=>$outputdata['id_product'],
                'name_pro'=>$outputdata['name_pro'],
                'amount'=>$outputdata['amount'],
                'pricPerItem'=>$outputdata['pricPerItem']
            );
        }
        return json_encode($item_of_product);
    }


}

$APIP=new APIP;
header('Content-Tyoe:application/json');



if(isset($_GET["type"]) && $_GET['type']=="get_me_all_data")
{
     echo $APIP->Select();


}

if(isset($_GET["type"]) && $_GET['type']=="show_info_of_product")
{
    $db=new Connect();
    $item_of_product=array();
   $id_pro=$_GET["IDProduct"];
    $data=$db->prepare("SELECT * FROM product where id_product='$id_pro'");
    $data->execute();
    if ($outputdata = $data->fetch(PDO::FETCH_ASSOC))
    {
        $item_of_product[]=array(
            'id_product'=>$outputdata['id_product'],
            'name_pro'=>$outputdata['name_pro'],
            'amount'=>$outputdata['amount'],
            'pricPerItem'=>$outputdata['pricPerItem']
        );
        echo json_encode($item_of_product);
    }
    else{
        echo "not found";
    }



}

if(isset($_GET["type"]) &&  $_GET["type"]=="insert_new_products")
{
    $nameproduct=$_GET["name"];
    $amountproduct=$_GET["amount"];
    $pricPerItem=$_GET["pricPerItem"];

    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "api";

// Create connection
    $conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    $sql = "INSERT INTO product (id_product,name_pro,amount,pricPerItem)
VALUES ( NULL ,'" . $nameproduct . "', " . $amountproduct . ", " . $pricPerItem .")" ;

    if ($conn->query($sql) === TRUE) {
        // echo "New record created successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $conn->close();


}

if(isset($_GET["type"]) &&  $_GET["type"]=="dec")
{

    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "api";

    $conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    $id=$_GET["id"];
    $sql = "SELECT amount FROM product WHERE id_product='$id'";
    $result = $conn->query($sql);
    $row = $result->fetch_assoc();
    if ($result->num_rows > 0) {
        // output data of each row

        $amount=$_GET["amount"];
       // echo intval($result["amount"]);

        //echo intval($row["amount"]);
        $new_amount=intval($row["amount"])-intval($amount);
        if($new_amount>=0) {

            $sql = "UPDATE product SET amount='$new_amount' WHERE id_product='$id'";

            if ($conn->query($sql) === TRUE) {
                echo "Record updated successfully";
            } else {
                echo "Error updating record: " . $conn->error;
            }

        }
        else{
            echo "cant do this operation";
        }



    } else {
        echo "not found ";
    }
    $conn->close();



}

if(isset($_GET["type"]) &&  $_GET["type"]=="inc")
{

    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "api";

    $conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    $id=$_GET["id"];
    $sql = "SELECT amount FROM product WHERE id_product='$id'";
    $result = $conn->query($sql);
    $row = $result->fetch_assoc();
    if ($result->num_rows > 0) {
        // output data of each row

        $amount=$_GET["amount"];
        if(intval($amount)<=0)
        {
            echo "cant update info ";
        }

        else {


            $new_amount = intval($row["amount"]) + intval($amount);
            if ($new_amount >= 0) {

                $sql = "UPDATE product SET amount='$new_amount' WHERE id_product='$id'";

                if ($conn->query($sql) === TRUE) {
                    echo "Record updated successfully";
                } else {
                    echo "Error updating record: " . $conn->error;
                }

            } else {
                echo "cant do this operation";
            }

        }


    } else {
        echo "not found ";
    }
    $conn->close();



}







///-----------------------------POST


if(isset($_POST["type"]) && $_POST['type']=="get_me_all_data")
{
    echo $APIP->Select();
}

if(isset($_POST["type"]) && $_POST['type']=="show_info_of_product")
{
    $db=new Connect();
    $item_of_product=array();
    $id_pro=$_POST["IDProduct"];
    $data=$db->prepare("SELECT * FROM product where id_product='$id_pro'");
    $data->execute();
    if ($outputdata = $data->fetch(PDO::FETCH_ASSOC))
    {
        $item_of_product[]=array(
            'id_product'=>$outputdata['id_product'],
            'name_pro'=>$outputdata['name_pro'],
            'amount'=>$outputdata['amount'],
            'pricPerItem'=>$outputdata['pricPerItem']
        );
        echo json_encode($item_of_product);
    }
    else{
        echo "not found";
    }



}

if(isset($_POST["type"]) &&  $_POST["type"]=="insert_new_products")
{
    $nameproduct=$_POST["name"];
    $amountproduct=$_POST["amount"];
    $pricPerItem=$_POST["pricPerItem"];

    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "api";

// Create connection
    $conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    $sql = "INSERT INTO product (id_product,name_pro,amount,pricPerItem)
VALUES ( NULL ,'" . $nameproduct . "', " . $amountproduct . ", " . $pricPerItem .")" ;

    if ($conn->query($sql) === TRUE) {
        // echo "New record created successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $conn->close();


}

if(isset($_POST["type"]) &&  $_POST["type"]=="dec")
{
    $servername="localhost";
    $username="root";
    $password="";
    $dbname="api";

    $conn = new mysqli($servername, $username, $password, $dbname);
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    $id=$_POST["id"];
    $sql = "SELECT amount FROM product WHERE id_product='$id'";
    $result = $conn->query($sql);
   // echo $_POST["id"];
    if ($result->num_rows >0) {
       // echo $_POST["id"];
        $amount=$_POST["amount"];
        // echo intval($result["amount"]);
        $row = $result->fetch_assoc();
        //echo intval($row["amount"]);
        $new_amount=intval($row["amount"])-intval($amount);
      // echo number_format($new_amount);

        if($new_amount>=0) {
           // echo intval($amount);
           // echo $new_amount;
            $sql = "UPDATE product SET amount='$new_amount' WHERE id_product='$id'";

            if ($conn->query($sql) === TRUE) {
                echo "Record updated successfully";
            } else {
                echo "Error updating record: " . $conn->error;
            }

        }
        else{
            echo "cant do this operation";
        }



    } else {
        echo "not found ";
    }
    $conn->close();



}

if(isset($_POST["type"]) &&  $_POST["type"]=="inc")
{

    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "api";
    $conn = new mysqli($servername, $username, $password, $dbname);
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    $id=$_POST["id"];
    $sql = "SELECT amount FROM product WHERE id_product='$id'";
    $result = $conn->query($sql);
    $row = $result->fetch_assoc();
    if ($result->num_rows > 0) {

        $amount=$_POST["amount"];
        if(intval($amount)<=0)
        {
            echo "cant update info ";
        }
        else {
            $new_amount = intval($row["amount"]) + intval($amount);
            if ($new_amount >= 0) {

                $sql = "UPDATE product SET amount='$new_amount' WHERE id_product='$id'";

                if ($conn->query($sql) === TRUE) {
                    echo "Record updated successfully";
                } else {
                    echo "Error updating record: " . $conn->error;
                }

            } else {
                echo "cant do this operation";
            }

        }


    } else {
        echo "not found ";
    }
    $conn->close();



}







?>