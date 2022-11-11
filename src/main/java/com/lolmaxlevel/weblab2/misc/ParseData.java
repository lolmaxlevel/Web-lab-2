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
        double normal_x = parseDoubleFromStr(x);

        if(normal_x<-4 || normal_x > 4) throw new WrongDataException("x is out of range");
        else return normal_x;
    }
    public static Double parseY(String y) throws WrongDataException {
        double normal_y = parseDoubleFromStr(y);

        if(normal_y < yMin || normal_y > yMax) throw new WrongDataException("y is out of range");
        else return normal_y;
    }
    public static Double parseR(String r) throws WrongDataException {
        double normal_r = parseDoubleFromStr(r);

        if(!possibleR.contains(normal_r)) throw new WrongDataException("r is out of range");
        else return normal_r;
    }
}
