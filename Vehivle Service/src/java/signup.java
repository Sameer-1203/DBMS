/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.exit;
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

/**
 *
 * @author samee
 */
public class signup extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            boolean flag=false;
            Connection conn = null;
            
            DBconnection db = new DBconnection();
            
            conn = db.getCon();
            
            Statement stmt = conn.createStatement();
            

            
            String cus_id = request.getParameter("cus_id");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String bday = request.getParameter("bday");
            String gender = request.getParameter("gender");
            String mobile_no = request.getParameter("mobile");
            String house = request.getParameter("house");
            String street = request.getParameter("street");
            String locality = request.getParameter("locality");
            String city = request.getParameter("city");
            String question = request.getParameter("question");
            String answer = request.getParameter("answer");
            String passwd = request.getParameter("passwd");
            String rpwd = request.getParameter("rpwd");
            
            
            String query = "select * from customer";
            
            ResultSet rs= stmt.executeQuery(query);
            
            if(!(passwd.equals(rpwd)))
            {
                out.println("<script type=\"text/javascript\">");
                out.println("if(!alert('Passwords do not match!')){window.location.replace('signup.html');}");
                out.println("</script>");
            }
            
            else if(passwd.length()<8)
            {
                out.println("<script type=\"text/javascript\">");
                out.println("if(!alert('Password length is less than 8!')){window.location.replace('signup.html');}");
                out.println("</script>");
            }
            
            
            {
                while(rs.next())
                {
                    String db_cus_id = rs.getString("customer_id");
                
            
                    if(db_cus_id.equals(cus_id))
                    {
                        out.println("<script type=\"text/javascript\">");
                        out.println("if(!alert('Customer ID already exists in Database!')){window.location.replace('signup.html');}");
                        out.println("</script>");
                        flag=true;
                        break;
                    }
                }
                
                if(!flag)
                {
                } else {
                    out.println("Registration succesfull");
               
                    String update = "insert into customer values('"+cus_id+"','"+fname+"','"+lname+"','"+email+"','"+bday+"','"+gender+"','"+mobile_no+"','"+passwd+"','"+question+"','"+answer+"','"+house+"','"+street+"','"+locality+"','"+city+"')";
            
                    stmt.executeUpdate(update);
                }
                   
                
            }
            
                


                        
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
        }
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
