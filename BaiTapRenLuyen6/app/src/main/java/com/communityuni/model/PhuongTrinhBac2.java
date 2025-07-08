package com.communityuni.model;

/**
 * Created by cafe on 23/12/2016.
 */

public class PhuongTrinhBac2 {
    private double a;
    private double b;
    private double c;

    public PhuongTrinhBac2() {
    }

    public PhuongTrinhBac2(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
    return a;
}

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }
    public String giai()
    {
        if(a==0)//trở thành phương trình bậc 1: bx+c=0
        {
            if(b==0 && c==0)
                return "Vô số nghiệm";
            if(b==0&&c!=0)
                return "Vô nghiệm";
            return "x="+(-b/c);
        }
        else
        {
            double delta=Math.pow(b,2)-4*a*c;
            if(delta<0)
                return "Vô nghiệm";
            if(delta==0)
            {
                double x=-b/(2*a);
                return  "No kép x1=x2="+x;
            }
            double x1=(-b-Math.sqrt(delta))/(2*a);
            double x2=(-b+Math.sqrt(delta))/(2*a);
            return "x1="+x1+"\nx2="+x2;
        }
    }
}
