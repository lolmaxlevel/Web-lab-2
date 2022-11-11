package com.lolmaxlevel.weblab2.misc;

import java.util.Arrays;
import java.util.List;

public class ParseData {
    private static final Integer yMax = 5;
    private static final Integer yMin = -5;
    private static final List<Double> possibleR = Arrays.asList(1.0, 1.5, 2.0, 2.5, 3.0);
    public static Double parseDoubleFromStr(String a) throws WrongDataException {
        try {
            return Double.parseDouble(a);
        } catch (NumberFormatException e) {
            throw new WrongDataException("bad input");
        }
    }
    public static Double parseX(String x) throws WrongDataException {
        double parsedX = parseDoubleFromStr(x);

        if(parsedX<-4 || parsedX > 4) throw new WrongDataException("x is out of range");
        else return parsedX;
    }
    public static Double parseY(String y) throws WrongDataException {
        double parsedY = parseDoubleFromStr(y);

        if(parsedY < yMin || parsedY > yMax) throw new WrongDataException("y is out of range");
        else return parsedY;
    }
    public static Double parseR(String r) throws WrongDataException {
        double parsedR = parseDoubleFromStr(r);

        if(!possibleR.contains(parsedR)) throw new WrongDataException("r is out of range");
        else return parsedR;
    }
}
