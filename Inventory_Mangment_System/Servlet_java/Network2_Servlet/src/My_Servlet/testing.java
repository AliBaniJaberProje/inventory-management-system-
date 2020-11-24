package My_Servlet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class testing {
    public static void main(String a[])
    {
        EmployeeManeger em=new EmployeeManeger();
//       int result= em.is_authorized("ali","ali");
//       if(result==-1)
//       {
//           System.out.println("not found ");
//       }
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(now));

//       System.out.println(em.ChangePassword(3,"ali","123456789643"));
//        ProductManeger pro=new ProductManeger();
//        String result2=pro.selectAllProduct();
//        System.out.println(result2);
//        System.out.println(pro.insert_amount(12,12));
//        System.out.println("-------------------------------------------");
//        System.out.println(pro.delete_amount(27,1000));
//
//        System.out.println("-------------------------------------------");
//        System.out.println(pro.show_info_of_product(10));










    }

}
