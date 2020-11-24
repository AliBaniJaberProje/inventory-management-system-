package My_Servlet;

public class User
{
  private   String id_user;
  private   String username;
  private   String amount;
  private   String pricPrem;

    public User(String id_user, String username, String amount, String pricPrem) {
        this.id_user = id_user;
        this.username = username;
        this.amount = amount;
        this.pricPrem = pricPrem;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPricPrem() {
        return pricPrem;
    }

    public void setPricPrem(String pricPrem) {
        this.pricPrem = pricPrem;
    }
}

