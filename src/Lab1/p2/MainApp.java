package Lab1.p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        int suma=0,minim=Integer.MAX_VALUE,maxim=Integer.MIN_VALUE,contor=0;
        try(Scanner scanner=new Scanner(new File("src/Lab1/lab1_p2/in.txt"))) {
            while(scanner.hasNextInt()) {
                int nr=scanner.nextInt();
                suma+=nr;
                if(minim>nr) minim=nr;
                if(maxim<nr) maxim=nr;
                contor++;
            }
        }
        catch(FileNotFoundException e) {throw new RuntimeException(e);}
        try{
            FileWriter writer=new FileWriter("src/Lab1/lab1_p2/out.txt");
            writer.write("suma: "+suma+"\n");
            writer.write("valoarea minima: "+minim+"\n");
            writer.write("valoarea maxima: "+maxim+"\n");
            writer.write("media: "+(float)suma/contor+"\n");
            writer.close();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

