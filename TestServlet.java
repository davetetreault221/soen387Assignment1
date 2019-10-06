/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Enumeration;

/**
 *
 * @author Xavier Vani-Charron
 * @author Dave Tetreault
 */
@WebServlet(urlPatterns = {"/TestServlet", "/TestServlet/index.html", "/TestServlet/index2.html"})
public class TestServlet extends HttpServlet {

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

        if (request.getParameter("format").equals("html")) {
            //html
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<style>table, th, td {  border: 1px solid black; }</style>");
                out.println("<head>");
                out.println("<title>Html Output</title>");
                out.println("</head>");
                out.println("<body>");

                out.print(
                        "<table>\n"
                        + "  <tr>\n"
                        + "    <td>Request Method</td>\n"
                        + "    <td>" + request.getMethod() + "</td>\n"
                        + "  </tr>\n"
                        + "  <tr>\n"
                        + "    <td>Request Header</td>"
                        + "</tr>\n"
                );

                Enumeration<String> headerNames = request.getHeaderNames();

                while (headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();
                    out.println("<tr>");
                    out.println("<td>Header Name: " + headerName + "</td>");
                    String headerValue = request.getHeader(headerName);
                    out.println("<td>Header Value: " + headerValue + "</td>");
                    out.println("</tr>");
                }

                out.println("<tr> <td>Query Parameters </td></tr>");

                Enumeration<String> parameterNames = request.getParameterNames();

                while (parameterNames.hasMoreElements()) {

                    String paramName = parameterNames.nextElement();
                    out.println("<tr>");
                    out.write("<td>Name: " + paramName + "</td>");

                    String[] paramValues = request.getParameterValues(paramName);
                    for (int i = 0; i < paramValues.length; i++) {
                        String paramValue = paramValues[i];
                        out.println("<td> Value: " + paramValue + "</td>");
                    }
                    out.println("</tr>");
                }

                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            }

        } else if (request.getParameter("format").equals("xml")) {
            //xml
            response.setContentType("text/xml;charset=UTF-8");

            try (PrintWriter out = response.getWriter()) {
                out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                out.println("<response>");
                    out.println("<request-method>");
                        out.println(request.getMethod());
                    out.println("</request-method>");
                out.println("<request-headers>");
                    
                    Enumeration<String> headerNames = request.getHeaderNames();
                    while (headerNames.hasMoreElements()) {
                        String headerName = headerNames.nextElement();
                        out.println("<header name =\""+headerName+"\">");
                        String headerValue = request.getHeader(headerName);
                        out.println(headerValue);
                        out.println("</header>");
                    }
                    
                out.println("</request-headers>");
                
                out.println("<query-string>");
                    
                    Enumeration<String> parameterNames = request.getParameterNames();

                    while (parameterNames.hasMoreElements()) {

                        String paramName = parameterNames.nextElement();
                        out.write("<"+paramName+">");

                        String[] paramValues = request.getParameterValues(paramName);
                        for (int i = 0; i < paramValues.length; i++) {
                            String paramValue = paramValues[i];
                            out.write(paramValue);
                        }
                        out.write("</"+paramName+">");
                    }
                out.println("</query-string>");
                
                out.println("</response>");
            }

        } else if (request.getParameter("format").equals("plain")) {
            //plain text
            response.setContentType("text/plain;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("Request Method: " + request.getMethod());
                out.println();
                out.println("Request Headers: ");
                Enumeration<String> headerNames = request.getHeaderNames();

                while (headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();
                    out.println("Header Name: " + headerName);
                    String headerValue = request.getHeader(headerName);
                    out.println("Header Value: " + headerValue);
                }
                out.println();

                //**************************************************************************
                //Printing The Query Params 
                //**************************************************************************
                out.println("Query Parameters");

                Enumeration<String> parameterNames = request.getParameterNames();

                while (parameterNames.hasMoreElements()) {

                    String paramName = parameterNames.nextElement();
                    out.write("Name: " + paramName);

                    String[] paramValues = request.getParameterValues(paramName);
                    for (int i = 0; i < paramValues.length; i++) {
                        String paramValue = paramValues[i];
                        out.println(" Value: " + paramValue);
                    }

                }
                //**************************************************************************
            }
        } else if (request.getParameter("format").equals("")) {
            //html format for whatever else
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<style>table, th, td {  border: 1px solid black; }</style>");
                out.println("<head>");
                out.println("<title>Html Output</title>");
                out.println("</head>");
                out.println("<body>");

                out.print(
                        "<table>\n"
                        + "  <tr>\n"
                        + "    <td>Request Method</td>\n"
                        + "    <td>" + request.getMethod() + "</td>\n"
                        + "  </tr>\n"
                        + "  <tr>\n"
                        + "    <td>Request Header</td>"
                        + "</tr>\n"
                );

                Enumeration<String> headerNames = request.getHeaderNames();

                while (headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();
                    out.println("<tr>");
                    out.println("<td>Header Name: " + headerName + "</td>");
                    String headerValue = request.getHeader(headerName);
                    out.println("<td>Header Value: " + headerValue + "</td>");
                    out.println("</tr>");
                }

                out.println("<tr> <td>Query Parameters </td></tr>");

                Enumeration<String> parameterNames = request.getParameterNames();

                while (parameterNames.hasMoreElements()) {

                    String paramName = parameterNames.nextElement();
                    out.println("<tr>");
                    out.write("<td>Name: " + paramName + "</td>");

                    String[] paramValues = request.getParameterValues(paramName);
                    for (int i = 0; i < paramValues.length; i++) {
                        String paramValue = paramValues[i];
                        out.println("<td> Value: " + paramValue + "</td>");
                    }
                    out.println("</tr>");
                }

                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            //error here
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("Error no such format option.");
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
