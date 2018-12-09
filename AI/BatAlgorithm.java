import org.omg.CORBA.MARSHAL;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BatAlgorithm {
    public static void main(String[]args){

        //问题空间
        int dimension=3;
        //蝙蝠数量
        int population=50;

        //迭代次数
        int iteration=1000;

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
        double local=Double.MAX_VALUE;
        int index=0;//记录是哪只蝙蝠最优
        while(t<iteration){
            double rand=Math.random();//生成随机数
            //1产生新解
            for(int i=0;i<population;i++) {
                Bat b=bats[i];
                b.fly(rand, Xbest, dimension,t,iteration);

                if(rand>b.r){
                    movex(b,t,dimension);
                }

                rand=Math.random();
                if(rand<b.A){
                    b.change(t);
                }
            }
            //找出最优解
            for(int i=0;i<population;i++){
                Function f=new Function();
                Bat b=bats[i];
                //1
                //b.value=f.sphere(b.location,dimension);
                //2
               b.value=f.schwefel(b.location,dimension);
                //3
               // b.value=f.schwefel2(b.location,dimension);
                //4
               // b.value=f.schewefel3(b.location,dimension);
                if(b.value<local)
                {
                    local=b.value;
                    index=i;
                }
            }
            Bbest=bats[index];
            Xbest=Bbest.location;//更新最优解
            t++;
        }
        System.out.println(local);
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
//    static double W(int t,int iteration){
//        int wmax=2;
//        int wmin=1;
//        double c=1/iteration*Math.log(wmax/wmin);
//        double w;
//        w=wmax-(wmax-wmin)*Math.exp(c*t);
//        return w;
//    }
    static void initialize(int n,Bat[] bats,int populaition){
        for(int i=0;i<populaition;i++){
            Bat b=new Bat(n);
            bats[i]=b;
        }
    }
}
