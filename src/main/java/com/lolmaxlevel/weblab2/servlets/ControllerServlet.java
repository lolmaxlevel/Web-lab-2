package com.lolmaxlevel.weblab2.servlets;

import java.io.*;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "controllerServlet", value = "/control")
public class ControllerServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getAttribute("error") != null){
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        if (request.getParameter("xval") != null && request.getParameter("yval") != null && request.getParameter("rval") != null){
            getServletContext().getNamedDispatcher("AreaCheckServlet").forward(request, response);
        }
        else if(request.getParameter("reset") != null){
            getServletContext().getNamedDispatcher("ClearDataServlet").forward(request, response);
        }
        else {
            response.sendError(400, "bad request");
        }
    }

    public void destroy() {
    }
}