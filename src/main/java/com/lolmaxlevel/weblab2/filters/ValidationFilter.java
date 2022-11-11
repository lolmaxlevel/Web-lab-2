package com.lolmaxlevel.weblab2.filters;

import com.lolmaxlevel.weblab2.misc.WrongDataException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import static com.lolmaxlevel.weblab2.misc.ParseData.*;

@WebFilter(filterName = "ValidationFilter", urlPatterns = "/control")
public class ValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        httpServletRequest.setAttribute("startTime", System.nanoTime());

        if (!httpServletRequest.getMethod().equals("GET") || (httpServletRequest.getParameterMap().size()!=4
                && httpServletRequest.getParameterMap().size()!=1)){
            httpServletRequest.setAttribute("error", "bad request");
            filterChain.doFilter(httpServletRequest, servletResponse);
        }
        try {
            String xVal = httpServletRequest.getParameter("xval").trim();
            String yVal = httpServletRequest.getParameter("yval").trim();
            String rVal = httpServletRequest.getParameter("rval").trim();
            String timezone = httpServletRequest.getParameter("timezone").trim();

            if (xVal.equals("")) throw new WrongDataException("x is empty");
            if (yVal.equals("")) throw new WrongDataException("y is empty");
            if (rVal.equals("")) throw new WrongDataException("r is empty");
            if (timezone.equals("")) throw new WrongDataException("timezone is empty");

            double xValue = parseX(xVal);
            double yValue = parseY(yVal);
            double rValue = parseR(rVal);
            double timezoneValue = parseDoubleFromStr(timezone);

            httpServletRequest.setAttribute("x", xValue);
            httpServletRequest.setAttribute("y", yValue);
            httpServletRequest.setAttribute("r", rValue);
            httpServletRequest.setAttribute("timezone", timezoneValue);
        }
        catch (WrongDataException | NullPointerException e){
            httpServletRequest.setAttribute("error", e.getMessage());
        }
        filterChain.doFilter(httpServletRequest, servletResponse);
    }
}
