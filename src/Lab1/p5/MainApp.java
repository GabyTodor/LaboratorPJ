package Lab1.p5;

import java.util.Random;

public class MainApp {
    public static void main(String[] args) {
        Random random = new Random();
        int n=random.nextInt(21);
        System.out.println("Numarul generat random este: "+n);
        int a=0,b=1,c;
        c=a+b;
        while(a<n){
            a=b;
            b=c;
            c=a+b;
        }
        if(a==n) System.out.println(n + " apartine sirului Fibonacci");
        else {
            System.out.println(n + " NU apartine sirului Fibonacci");
        }
    }
}

