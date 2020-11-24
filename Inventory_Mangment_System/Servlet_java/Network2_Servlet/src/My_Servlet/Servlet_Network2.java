package My_Servlet;

import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Servlet_Network2" )
public class Servlet_Network2 extends HttpServlet {

    ProductManeger pm=new ProductManeger();
    EmployeeManeger em=new EmployeeManeger();
    private HashMap<String,String> readbodyRequest(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        //request.setCharacterEncoding("UTF-8");
        try (BufferedReader br = request.getReader()) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        String[] boday_date =sb.toString().split("&");
        HashMap<String,String> param_body = new HashMap<String, String>();
        for(int i=0;i<boday_date.length;i++)
        {

                //String key_vale_str = boday_date[i].split("-")[0];
                //key_vale_str = key_vale_str.split("=")[1];
                //key_vale_str.split("\"");
                //key_vale_str=key_vale_str.substring(1);
                param_body.put(boday_date[i].split("=")[0],boday_date[i].split("=")[1]);



        }
        return param_body;
    }




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String,String> result_param=readbodyRequest(request);

        String type_request=result_param.get("type");
        String IDProduct= result_param.get("IDProduct");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out=response.getWriter();
        // out.println(type_request);

        try {
            if(type_request.equals("get_me_all_data"))
            {
                out.println(pm.selectAllProduct());

            }
            else if(type_request.equals("show_info_of_product"))
            {

                String result_show_info=pm.show_info_of_product(Integer.parseInt(result_param.get("IDProduct")));
                if(result_show_info.equals("not found"))
                {
                    response.setContentType("application/html");
                }
                out.println(result_show_info);
            }
            else if(type_request.equals("dec"))
            {
                response.setContentType("application/html");
                out.println(pm.delete_amount(Integer.parseInt(result_param.get("id")),Integer.parseInt(result_param.get("amount"))));
            }
            else if(type_request.equals("inc"))
            {
                response.setContentType("application/html");
                out.println(pm.insert_amount(Integer.parseInt(result_param.get("id")),Integer.parseInt(result_param.get("amount"))));
            }
            else if(type_request.equals("ChangePassword"))
            {
                response.setContentType("application/html");
                out.println(em.ChangePassword(Integer.parseInt(result_param.get("id_user")),result_param.get("lastpass"),result_param.get("newpass")));
            }
            else if(type_request.equals("login"))
            {
                response.setContentType("application/html");
                out.println(em.is_authorized(result_param.get("username"),result_param.get("pass")));
            }
            else {

            }


        }
        finally {
            out.close();
        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String type_request=request.getParameter("type");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out=response.getWriter();
       // out.println(type_request);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            if(type_request.equals("get_me_all_data"))
            {
                out.println(pm.selectAllProduct());

            }
            else if(type_request.equals("show_info_of_product"))
            {
                String result_show_info=pm.show_info_of_product(Integer.parseInt(request.getParameter("IDProduct")));
                if(result_show_info.equals("not found"))
                {
                    response.setContentType("application/html");
                }
                out.println(result_show_info);
            }
            else if(type_request.equals("dec"))
            {
                response.setContentType("application/html");
                out.println(pm.delete_amount(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("amount"))));
            }
            else if(type_request.equals("inc"))
            {
                response.setContentType("application/html");
                out.println(pm.insert_amount(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("amount"))));
            }
            else if(type_request.equals("ChangePassword"))
            {
                response.setContentType("application/html");
                out.println(em.ChangePassword(Integer.parseInt(request.getParameter("id_user")),request.getParameter("lastpass"),request.getParameter("newpass")));
            }
            else if(type_request.equals("login"))
            {
                response.setContentType("application/html");
                out.println(em.is_authorized(request.getParameter("username"),request.getParameter("pass")));
            }
            else {

            }


        }
        finally {
            out.close();
        }



    }


}

