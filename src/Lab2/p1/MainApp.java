package Lab2.p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws FileNotFoundException {
        String[] judete;
        int k=0;
        Scanner scanner = new Scanner(new File("src/Lab2/p1/judete_in.txt"));

        judete = scanner.nextLine().split(",");
        scanner.close();

        Arrays.sort(judete);

        Scanner scanner_citire = new Scanner(System.in);
        String cautat=scanner_citire.nextLine();
        scanner_citire.close();

        int i = Arrays.binarySearch(judete, cautat);
        System.out.println("Judetul: " + cautat + " se afla pe pozitia "+i);
    }
}
