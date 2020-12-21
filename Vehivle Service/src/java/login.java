/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author samee
 */
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String cus_id = request.getParameter("cus_id");
            String passwd = request.getParameter("passwd");
            Connection conn = null;
            String name=null;
            boolean flag=false;
            //out.println(cus_id);
            //out.println(passwd+"\n");
            
            DBconnection db = new DBconnection();
            
            conn = db.getCon();
            
            Statement stmt = conn.createStatement();
            String query = "select * from customer";
            
            ResultSet rs= stmt.executeQuery(query);
            
            
            while(rs.next())
            {
                String db_cus_id = rs.getString("customer_id");
                String db_passwd = rs.getString("passwd");
                
            
                //out.println(db_cus_id);
                //out.println(db_passwd);
            
                //if(db_cus_id != cus_id && db_passwd != passwd)
                if(db_cus_id.equals(cus_id) && db_passwd.equals(passwd))
                {
                    flag=true;
                    name = rs.getString("fname");
                    break;
                    //out.println("Login Successful  Correct Cred");
                    //out.println("Welcome "+name);
                }
                
                
            }
            if(flag)
            {
                out.println("Login Successful  Correct Cred");
                out.println("Welcome "+name);
            }
            else
            {
                
                out.println("<script type=\"text/javascript\">");
                out.println("if(!alert('Wrong Customer ID or password!')){window.location.replace('index.html');}");
                out.println("</script>"); 
                
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
