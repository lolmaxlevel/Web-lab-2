package com.lolmaxlevel.weblab2.misc;

import java.time.LocalDateTime;


public class Result{
    double x;
    double y;
    double r;
    boolean isHit;
    LocalDateTime time;
    long execTime;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public boolean isHit() {
        return isHit;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public long getExecTime() {
        return execTime;
    }

    public Result(double x, double y, double r, boolean isHit, int date, long execTime){
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.time = LocalDateTime.now().plusHours(date/60);
        this.execTime = execTime;
    }

}
