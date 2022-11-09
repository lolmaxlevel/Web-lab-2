package com.lolmaxlevel.weblab2.servlets;

import java.io.*;
import java.util.ArrayList;


import com.lolmaxlevel.weblab2.misc.Result;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ClearDataServlet", value = "/clear")
public class ClearDataServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        getServletContext().setAttribute("results", new ArrayList<Result>());
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    public void destroy() {
    }
}