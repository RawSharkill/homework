import java.util.List;
import java.util.Random;

public class Function {
    //D 都表示 求解函数的维数
    public double sphere(Double[]x, int D){
        double value = 0;
        for(int i=0; i<D; i++)
            value += x[i]*x[i];
        return value;
    }
    public double schwefel(Double []x,int D){
        double value = 0.0;
        double temp1 = 0.0;
        double temp2 = 1.0;
        for(int i=0; i<D; i++){
            temp1 += Math.abs(x[i]);
            temp2 *= Math.abs(x[i]);
        }
        value =temp1+temp2;
        return value;
    }


    public double schwefel2(Double []x,int D){
        double value = 0;
        double temp = 0.0;
        for(int i=0; i<D; i++){
            temp = 0.0;
            for(int j=0; j<i; j++)
                temp += x[j];
            value += temp*temp;
        }
        return value;
    }


    public double schewefel3(Double []x,int D){
        double value = Math.abs(x[0]);
        for(int i=1; i<D; i++){
            if(Math.abs(x[i])>value)
                value = Math.abs(x[i]);
        }
        return value;
    }

    public double rosenBrock(Double []x, int D){
        double value = 0;
        for(int i=0; i<D-1; i++)
            value += 100*(x[i+1]-x[i]*x[i])*(x[i+1]-x[i]*x[i])+(1-x[i])*(1-x[i]);
        return value;
    }

    public double step(Double []x,int D){
        double value = 0;
        double t1 = 0.0;
        double t2 = 0.0;
        for(int i=0; i<D; i++){
            t1 = x[i]+0.5;
            t2 = (int)t1;
            value += t2*t2;
        }
        return value;
    }

    public double quarticWithNoise(Double []x, int D){
        double value = 0;
        for(int i=0; i<D; i++){
            value += i*Math.pow(x[i], 4);
        }
        Random random = new Random();
        value = value + random.nextDouble();
        return value;
    }

    public double schwefel4(Double[]x, int D ){
        double value = 0;
        for(int i=0; i<D; i++)
            value += -x[i]*Math.sin(Math.sqrt(Math.abs(x[i])));
        //value += D*418.9828872724337;
        return value;
    }

    public double rastrign(double[]x, int D){
        double value = 0;
        for(int i=0; i<D; i++)
            value += x[i]*x[i]-10*Math.cos(
                    2*Math.PI*x[i])+10;
        return value;
    }


    public double ackley(Double[]x, int D){
        double value = 0;
        double temp1, temp2;
        temp1 = 0.0;
        temp2 = 0.0;
        for(int i=0; i<D; i++){
            temp1 += x[i]*x[i];
            temp2 += Math.cos(2*Math.PI*x[i]);
        }
        temp1/=D;
        temp1=-0.2*Math.sqrt(temp1);
        temp1=-20*Math.exp(temp1);
        temp2/=D;
        temp2=Math.exp(temp2);
        value=temp1-temp2+20+Math.E;
        return value;
    }

    public double griewank(Double[]x ,int D){
        double value = 0.0;
        double temp1, temp2;
        temp1 = 0.0;
        temp2 = 1.0;
        for(int i=0; i<D; i++){
            temp1 += x[i]*x[i];
            temp2 *= Math.cos(x[i]/Math.sqrt((double)(i+1)));
        }
        value = temp1/4000-temp2+1;
        return value;
    }

    public double penalized(Double[]x, int D){

        double value = 0.0;
        double f1=0.0;
        double f2=0;
        double u[] = new double[D];
        double y[] = new double[D];
        for(int i=0; i<D; i++){
            if(x[i]>10){u[i] = 100*Math.pow(x[i]-10, 4);}
            else if(x[i]<-10){u[i]=100*Math.pow((-x[i]-10), 4);}
            else {u[i]=0;}
            f1=f1+u[i];
        }
        for(int i=0; i<D; i++){
            y[i]=1+(1/4.0)*(x[i]+1);
        }
        for(int i=0; i<D-1; i++){
            f2=f2+((y[i]-1)*(y[i]-1))*(1+10*Math.sin(Math.PI*y[i+1])*Math.sin(Math.PI*y[i+1]));
        }
        f2=(Math.PI/(D+0.0))*(f2+10*Math.sin(Math.PI*y[0])*Math.sin(Math.PI*y[0])+(y[D-1]-1)*(y[D-1]-1));
        value = f1 + f2;
        return value;
    }

    public double penalized2(Double[]x ,int D ){

        double value = 0;
        double f1=0.0;
        double f2=0.0;
        double u[] = new double[D];

        for(int i=0; i<D; i++){
            if(x[i]>5){u[i] = 100*Math.pow(x[i]-5, 4);}
            else if(x[i]<-5){u[i]=100*Math.pow((-x[i]-5), 4);}
            else {u[i]=0;}
            f1=f1+u[i];
        }
        for(int i=0; i<D-1; i++){
            f2=f2+(x[i]-1)*(x[i]-1)*(1+Math.sin
                    (3*Math.PI*x[i+1])*Math.sin(3*Math.PI*x[i+1]));
        }
        f2 = f2 + Math.sin(3*Math.PI*x[0])
                *Math.sin(3*Math.PI*x[0])+(x[D-1]-1)*(x[D-1]-1)*(1+Math.sin(2*Math.PI*x[D-1])*Math.sin(2*Math.PI*x[D-1]));
        value = 0.1*f2 + f1;
        return value;
    }

}
