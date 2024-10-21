package Lab2.p3;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        int pozitia;
        Scanner scanner=new Scanner(System.in);
        System.out.print("Sirul de baza: ");
        StringBuilder sir=new StringBuilder(scanner.nextLine());
        System.out.print("Sirul introdus: ");
        StringBuilder sir_introdus=new StringBuilder(scanner.nextLine());
        System.out.print("Pozitia: ");
        pozitia=scanner.nextInt();
        sir.insert(pozitia,sir_introdus);
        System.out.println(sir.toString());

        System.out.print("Pozitia de unde sa inceapa stergerea: ");
        int pozitia_inceput=scanner.nextInt();
        System.out.print("Numarul de caractere: ");
        int nr_caractere=scanner.nextInt();
        int poz_final=pozitia_inceput+nr_caractere;
        sir.delete(pozitia_inceput,poz_final);
        System.out.print(sir.toString());
    }
}


