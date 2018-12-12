import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Bat {
    //对于单个蝙蝠的属性值
    Double []location;//位置
    Double[]vector;//速度
    double f;
    double r;
    double A;
    double value;
    int fmax=2;
    int fmin=0;
    double aerfa=0.9;//音强衰减系数
    double gama=0.95;//频率增加系数
    //脉冲频率
    int rmax=1;
    int rmin=0;
    //音强
    int amax=2;
    int amin=1;


    Bat(int n){
        Random r1=new Random();
        location = new Double[n];
        for(int i=0;i<n;i++)
        {
            double l=r1.nextDouble()*10-5;
            location[i]=l;
        }

        vector=new Double[n];
        for(int i=0;i<n;i++){
            double v=r1.nextDouble()*1  -0.5;
            vector[i]=v;
        }
        f=r1.nextDouble()*(fmax-fmin)+fmin;
        r=r1.nextDouble()*(rmax-rmin)+rmin;
        A=r1.nextDouble()*(amax-amin)+amin;
        value=0;
    }

    void fly(double w,Double[] Xbest,int n,int t,int iteration){
        double rand=Math.random();
        f=rand*(fmax-fmin)+fmin;
        for(int i=0;i<n;i++)
        {
            vector[i]=w*vector[i]+(Xbest[i]-location[i])*f;
           // vector[i]=vector[i]+(Xbest[i]-location[i])*f;
            location[i]+=vector[i];
            if(location[i]>4 )
                location[i]=4.0;
            if(location[i]<-4)
                location[i]=-4.0;
        }
    }
    double W(int t,int iteration){
        double wmax=0.5;
        double wmin=0.05;
        double c=1/iteration*Math.log(wmax/wmin);
        double w;
        w=wmax-(wmax-wmin)*Math.exp(c*t);
        return w;
    }
    void change(int t){
        r=r*Math.exp((-1*gama*t));
        A=A*aerfa;
    }
}
