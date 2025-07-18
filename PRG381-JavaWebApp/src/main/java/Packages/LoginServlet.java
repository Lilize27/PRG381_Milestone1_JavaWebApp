/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
//package Packages;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.security.MessageDigest;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// *
// * @author Cosmo
// */
//@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
//public class LoginServlet extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {
//            out.close();
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
//*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
package Packages;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cosmo
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //get form data from login.jsp 
        String studentNumber = request.getParameter("student_number");
        String password = request.getParameter("password");
                
        //validating input
        if (studentNumber == null || studentNumber.isEmpty() || password == null || password.isEmpty() ){
            request.setAttribute("error", "Please enter bth student number and password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        try (Connection conn = ConnectionDB.getConnection()){
            //query the database for the student
            String sql = "SELECT name, password FROM users WHERE student_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, studentNumber);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                String storedHashedPassword = rs.getString("password");
                String studentName = rs.getString("name");
                
                //hash enetered password to compare with one stored in database
                String hashedInputPassword = hashPassword(password);
                
                if (storedHashedPassword.equals(hashedInputPassword)){
                    //Login succesfull; redirect to dashboard and create session
                   HttpSession session = request.getSession();
                   session.setAttribute("studentNumber", studentNumber);
                   session.setAttribute("studentName", studentName);
                   response.sendRedirect("dashboard.jsp");
                }else{
                    //incorrect password; redirect to login
                    request.setAttribute("error", "Incorrect Password.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }else {
                //student not found: redirect to login
                request.setAttribute("error", "Student Number not Found");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e){
            e.printStackTrace();
            request.setAttribute("error", "Login failed due to error: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        
    }
    
      //Hash password (same as registration)
    private String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }
    
  // Handles POST requests from login.jsp
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    processRequest(request, response);
}

// Handles GET requests 
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    processRequest(request, response);
}

// Description of the servlet
@Override
public String getServletInfo() {
    return "Handles student login authentication";
}

   
}
