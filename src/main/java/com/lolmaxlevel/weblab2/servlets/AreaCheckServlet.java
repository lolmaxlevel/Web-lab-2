package com.lolmaxlevel.weblab2.servlets;

import java.io.*;
import java.util.ArrayList;
import com.lolmaxlevel.weblab2.misc.Result;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static com.lolmaxlevel.weblab2.misc.CheckIfHit.*;


@WebServlet(name = "AreaCheckServlet", value = "/check")
public class AreaCheckServlet extends HttpServlet {
    ArrayList<Result> results;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

            Double x = (Double)request.getAttribute("x");
            Double y = (Double)request.getAttribute("y");
            Double r = (Double)request.getAttribute("r");
            Double timezone = (Double) request.getAttribute("timezone");
            boolean isHit = isHit(x, y, r);

            if (getServletContext().getAttribute("results") == null){
                results = new ArrayList<>();
            }
            else {
                results = (ArrayList<Result>) getServletContext().getAttribute("results");
            }
            results.add(new Result(x,y,r, isHit, timezone.intValue(), System.nanoTime()
                    - (long)request.getAttribute("startTime")));
            request.getServletContext().setAttribute("results", results);
        //avoids "response already committed" error, not a great solution, possibly should be rewritten
        if (!response.isCommitted()){response.sendRedirect(request.getContextPath() + "/results.jsp");}
        }


    public void destroy() {
    }
}