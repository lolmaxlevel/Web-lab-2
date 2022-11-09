package com.lolmaxlevel.weblab2.servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.lolmaxlevel.weblab2.misc.Result;
import com.lolmaxlevel.weblab2.misc.WrongDataException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "AreaCheckServlet", value = "/check")
public class AreaCheckServlet extends HttpServlet {
    private final List<Double> possibleR = Arrays.asList(1.0, 1.5, 2.0, 2.5, 3.0);
    private final Integer yMax = 5;
    private final Integer yMin = -5;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long startTime = System.nanoTime();

        String xVal = request.getParameter("xval").trim();
        String yVal = request.getParameter("yval").trim();
        String rVal = request.getParameter("rval").trim();
        String timezone = request.getParameter("timezone").trim();

        try{

            if (xVal.equals("")) throw new WrongDataException("x is empty");
            if (yVal.equals("")) throw new WrongDataException("y is empty");
            if (rVal.equals("")) throw new WrongDataException("r is empty");
            if (timezone.equals("")) throw new WrongDataException("timezone is empty");

            double xValue = parseX(xVal);
            double yValue = parseY(yVal);
            double rValue = parseR(rVal);
            double timezoneValue = parseDoubleFromStr(timezone);

            boolean isHit = isInCircle(xValue, yValue, rValue)
                    || isInTriangle(xValue, yValue, rValue)
                    || isInRectangle(xValue,yValue,rValue);


            ArrayList<Result> results;

            if (getServletContext().getAttribute("results") == null){
                results = new ArrayList<Result>();
            }
            else {
                results = (ArrayList<Result>) getServletContext().getAttribute("results");
            }
            results.add(new Result(xValue,yValue,rValue,isHit, (int) timezoneValue, System.nanoTime()-startTime));
            request.getServletContext().setAttribute("results", results);
        } catch (WrongDataException e){
            //add "error" to table
            response.sendError(400, e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/results.jsp");
    }

    public void destroy() {
    }
    private Double parseDoubleFromStr(String a) throws WrongDataException {
        try {
            return Double.parseDouble(a);
        } catch (NumberFormatException e) {
            throw new WrongDataException("bad input");
        }
    }
    private Double parseX(String x) throws WrongDataException {
        double normal_x = parseDoubleFromStr(x);

        if(normal_x<-4 || normal_x > 4) throw new WrongDataException("x is out of range");
        else return normal_x;
    }
    private Double parseY(String y) throws WrongDataException {
        double normal_y = parseDoubleFromStr(y);

        if(normal_y < yMin || normal_y > yMax) throw new WrongDataException("y is out of range");
        else return normal_y;
    }
    private Double parseR(String r) throws WrongDataException {
        double normal_r = parseDoubleFromStr(r);

        if(!possibleR.contains(normal_r)) throw new WrongDataException("r is out of range");
        else return normal_r;
    }
    private boolean isInCircle(double x,double y,double r){
        if (x>=0 && y<=0) return Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r/2,2);
        else return false;
    }
    private boolean isInTriangle(double x,double y,double r){
        //https://www.cyberforum.ru/algorithms/thread144722.html
        //Реализация - считаются произведения (1, 2, 3 - вершины треугольника, 0 - точка):
        //(x1 - x0) * (y2 - y1) - (x2 - x1) * (y1 - y0)
        //(x2 - x0) * (y3 - y2) - (x3 - x2) * (y2 - y0)
        //(x3 - x0) * (y1 - y3) - (x1 - x3) * (y3 - y0)
        double a = 0 - (-r/2 - 0) * (0 - y);
        double b = (-r/2 - x) * (-r - 0) - (0 - (-r/2)) * (0 - y);
        double c = (-x) * (0 - (-r)) - (0) * (-r - y);
        return ((a>=0 && b>=0 && c>=0) || (a<=0 && b<=0 && c<=0));
    }
    private boolean isInRectangle(double x,double y,double r){
        return  (x>=0 && y>=0 && (x<=r/2 && y<=r));
    }
}