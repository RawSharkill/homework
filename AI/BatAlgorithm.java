import com.sun.org.apache.xpath.internal.operations.Bool;
import org.omg.CORBA.MARSHAL;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BatAlgorithm {
    public static void main(String[]args){

        //问题空间
        int dimension=30;
        //蝙蝠数量
        int population=100;

        //迭代次数
        int iteration=10000;

        int t=1;//次数迭代次数

        Bat[] bats=new Bat[population];
        initialize(dimension,bats,population);

        //初始随机产生最好解
        Double[]Xbest=new Double[dimension];
        for(int i=0;i<dimension;i++)
        {
            Random r1=new Random();
            Xbest[i]=r1.nextDouble()*10-5;
        }
        Bat Bbest=new Bat(dimension);
        double l=Double.MAX_VALUE;
        int index=0;//记录是哪只蝙蝠最优
        while(t<iteration){
            //1产生新解
            for(int i=0;i<population;i++) {
                double rand=Math.random();//生成随机数
                Bat b=bats[i];
                double w=W(t,iteration);
                b.fly(w, Xbest, dimension,t,iteration);

                if(rand>b.r){
                    move(b,t,dimension,bats);
                }
                double rand2 = Math.random();
                Function f=new Function();
                //double s=f.sphere(b.location,dimension);//1
                //double s=f.schwefel(b.location,dimension);//2
                //double s=f.schwefel2(b.location,dimension);//3
                //double s=f.schewefel3(b.location,dimension);//4
                //double s=f.rosenBrock(b.location,dimension);//5
                //double s=f.step(b.location,dimension);//6
                //double s=f.quarticWithNoise(b.location,dimension);//7
                double s=f.schwefel4(b.location,dimension);//8
                //double s=f.rastrign(b.location,dimension);//9
                //double s=f.ackley(b.location,dimension);//10
                //double s=f.griewank(b.location,dimension);//11
                //double s=f.penalized(b.location,dimension);//12
                //double s=f.penalized2(b.location,dimension);//13

                b.value=s;
                if(rand2<b.A &&s<l){
                    System.out.println(s);
                    b.change(t);
                    l=s;
                    index=i;
                    Bbest=bats[index];
                    Xbest=Bbest.location;//更新最优解
                }
            }
            t++;
        }
        System.out.println("best  "+l);
    }
    static void movex(Bat b,int iteration,int n){
        Double[] newlocation = new Double[n];
        Double[] Ts=new Double[n];
        for(int i=0;i<n;i++)
        {
            double ts=T(iteration);
            Ts[i]=ts;
        }

        for(int j=0;j<n;j++)
        {
            double term=b.location[j]*(1+Ts[j]);
            newlocation[j]=term;
        }

        b.location=newlocation;
    }
    static void move(Bat b,int iteration,int dim,Bat[]bats){
        Random r=new Random();
        double d=r.nextDouble()*2-1;
        for(int i=0;i<dim;i++)
        b.location[i]=b.location[i]+d*aveA(bats);

    }
    static double aveA(Bat[] bats){
        double x=0;
        for(int i=0;i<bats.length;i++)
            x+=bats[i].A;
        double l=bats.length+0.0;
        return x/l;
    }
    static double T(int n){
        Random r=new Random();
        double x=r.nextGaussian();//高斯分布

        double y=0;//卡方分布
        Random r1=new Random();
        for(int i=0;i<n;i++)
        {
            double x1=r1.nextGaussian();
            y+=x1*x1;
        }

        double t;
        t=x/Math.sqrt(y/n);
        return t;
    }
   static double W(int t,int iteration){
        double  wmax=2.0;
        double wmin=1.0;
        double x=iteration+0.0;
        double c=(1/x)*Math.log(wmax/wmin);
        double w;
        w=wmax-(wmax-wmin)*Math.exp(c*t);
        return w;
    }
    static void initialize(int n,Bat[] bats,int populaition){
        for(int i=0;i<populaition;i++){
            Bat b=new Bat(n);
            bats[i]=b;
        }
    }
}
