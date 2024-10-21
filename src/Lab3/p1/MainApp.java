package Lab3.p1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.hypot;

class Parabola{
    private double a;
    private double b;
    private double c;
    public Parabola(double a,double b,double c){
        this.a=a;
        this.b=b;
        this.c=c;
    }
    public double[] varf(){
        double[] doubles ={-b / (2 * a), (-b * b + 4 * a * c) / (4 * a)};
        return doubles;
    }
    @Override
    public String toString() {
        return a+"x^2 + "+b+"x +"+c+" ";
    }

    public static double[] mijloc(Parabola p1, Parabola p2){
        double[] mijloc={(p1.varf()[0] + p2.varf()[0]) /2 , (p1.varf()[1] + p2.varf()[1]) /2};
        return mijloc;
    }

    public static double lungime(Parabola p1, Parabola p2){
        double[] v1=p1.varf();
        double[] v2=p2.varf();
        return hypot(v1[0]-v2[0],v1[1]-v2[1]);
    }
}
public class MainApp {
    public static void main(String[] args) {
        List<Parabola> parabolas = new ArrayList<Parabola>();
        try(Scanner sc = new Scanner(new File("C:/Users/Gaby/IdeaProjects/LaboratorPJ/src/Lab3/p1/in.txt"))) {
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(" ");
                double a = Double.parseDouble(data[0]);
                double b = Double.parseDouble(data[1]);
                double c = Double.parseDouble(data[2]);
                Parabola parabola = new Parabola(a,b,c);
                parabolas.add(parabola);
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        for(Parabola parabola : parabolas) {
            System.out.print(parabola.toString());
            System.out.println(Arrays.toString(parabola.varf()));

        }
        //am decis ca cele 2 parabole din colectie sa fie primele
        System.out.println("Mijlocul celor 2 varfuri a parabolelor alese: "+ Arrays.toString(Parabola.mijloc(parabolas.get(0), parabolas.get(1))));
        System.out.println("Lungimea segmentului ce uneste cele 2 parabole alese: "+Parabola.lungime(parabolas.get(0),parabolas.get(1)));
    }
}
