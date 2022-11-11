package com.lolmaxlevel.weblab2.misc;

public class CheckIfHit {
    public static boolean isInCircle(double x,double y,double r){
        if (x>=0 && y<=0) return Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r/2,2);
        else return false;
    }
    public static boolean isInTriangle(double x,double y,double r){
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
    public static boolean isInRectangle(double x,double y,double r){
        return  (x>=0 && y>=0 && (x<=r/2 && y<=r));
    }

    public static boolean isHit(double x, double y, double r){
        return isInCircle(x, y, r)||isInRectangle(x, y, r)||isInTriangle(x, y, r);
    }
}
