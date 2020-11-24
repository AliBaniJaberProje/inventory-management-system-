
<?php
require_once __DIR__.'/config.php';

class APIP{

    function Select()
    {
        $db=new Connect();
        $users=array();
        $data=$db->prepare('SELECT * FROM users ORDER  BY id');
        $data->execute();
        while ($outputdata = $data->fetch(PDO::FETCH_ASSOC))
        {
            $users[]=array(
                'id'=>$outputdata['id'],
                'username'=>$outputdata['username'],
                'password'=>$outputdata['password'],
                'email'=>$outputdata['email']
            );
        }
        return json_encode($users);
    }
}

$APIP=new APIP;
header('Content-Tyoe:application/json');
//echo $APIP->Select();

//--GET

if(isset($_GET["type"]) &&  $_GET["type"]=="ChangePassword") {

    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "api";

    $conn = new mysqli($servername, $username, $password, $dbname);
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
        $id_user = $_GET["id_user"];
        $lastpass = $_GET["lastpass"];
//        echo "id".$id_user;
//        echo  "llast".$lastpass;
//        echo  " ".$_GET["newpass"];
        $sql = "SELECT id FROM users WHERE id='$id_user' and password='$lastpass'";
        $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $new_pass = $_GET["newpass"];
      //  echo $new_pass;

        $sql = "UPDATE users SET password='$new_pass' WHERE id='$id_user'";

        if ($conn->query($sql) === TRUE) {
             echo "Record updated successfully";
        } else {
             echo "Error updating record: " . $conn->error;
        }

    } else {
        echo "error of pass";
    }
    $conn->close();
}

if(isset($_GET["type"]) &&  $_GET["type"]=="login") {

    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "api";

    $conn = new mysqli($servername, $username, $password, $dbname);
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    $username = $_GET["username"];
    $pass = $_GET["pass"];
    $sql = "SELECT id FROM users WHERE username='$username' and password='$pass'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $user_id= $row["id"];
        $newlast_Access_time=date('Y-m-d H:i:s');
        $sql = "UPDATE users SET last_Access_time='$newlast_Access_time' WHERE id='$user_id'";

        if ($conn->query($sql) === TRUE) {
            echo $user_id;
        } else {
            echo "Error updating record: " . $conn->error;
        }



    } else {
        echo "-1";
    }
    $conn->close();
}

//POST
if(isset($_POST["type"]) &&  $_POST["type"]=="ChangePassword") {

    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "api";

    $conn = new mysqli($servername, $username, $password, $dbname);
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    $id_user = $_POST["id_user"];
    $lastpass = $_POST["lastpass"];
    $sql = "SELECT id FROM users WHERE id='$id_user' and password='$lastpass'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $new_pass = $_POST["newpass"];


        $sql = "UPDATE users SET password='$new_pass' WHERE id='$id_user'";

        if ($conn->query($sql) === TRUE) {
            echo "Record updated successfully";
        } else {
            echo "Error updating record: " . $conn->error;
        }

    } else {
        echo "error of pass";
    }
    $conn->close();
}

if(isset($_POST["type"]) &&  $_POST["type"]=="login") {

    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "api";

    $conn = new mysqli($servername, $username, $password, $dbname);
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    $username = $_POST["username"];
    $pass = $_POST["pass"];
    $sql = "SELECT id FROM users WHERE username='$username' and password='$pass'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $user_id= $row["id"];
        $newlast_Access_time=date('Y-m-d H:i:s');

        $sql = "UPDATE users SET last_Access_time='$newlast_Access_time' WHERE id='$user_id'";

        if ($conn->query($sql) === TRUE) {
            echo $user_id;
        } else {
            echo "Error updating record: " . $conn->error;
        }



    } else {
        echo "-1";
    }
    $conn->close();
}

?>

