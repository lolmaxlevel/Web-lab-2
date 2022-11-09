package com.lolmaxlevel.weblab2.misc;

import java.time.LocalDateTime;


public class Result{
    double x;
    double y;
    double r;
    boolean isHit;
    LocalDateTime time;
    long exec_time;

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

    public long getExec_time() {
        return exec_time;
    }

    public Result(double x, double y, double r, boolean isHit, int date, long exec_time){
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.time = LocalDateTime.now().plusHours(date/60);
        this.exec_time = exec_time;
    }

}
