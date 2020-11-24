package My_Servlet;

public class Product {

  private   int id_product;
  private  String name_pro;
  private  String amount;
  private String pricperitem;


  public Product(int id_product, String name_pro, String amount, String pricperitem) {
        this.id_product = id_product;
        this.name_pro = name_pro;
        this.amount = amount;
        this.pricperitem = pricperitem;
  }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_pro() {
        return name_pro;
    }

    public void setName_pro(String name_pro) {
        this.name_pro = name_pro;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPricperitem() {
        return pricperitem;
    }

    public void setPricperitem(String pricperitem) {
        this.pricperitem = pricperitem;
    }
}
