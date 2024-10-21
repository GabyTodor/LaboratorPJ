package Lab2.p4;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    static class Persoana {
        private String nume, cnp;
        long varsta;

        //constructor
        public Persoana(String nume, String cnp) {
            this.nume = nume;
            this.cnp = cnp;
        }

        //settere si gettere: Alt+Insert
        public String getNume() {
            return nume;
        }

        public void setNume(String nume) {
            this.nume = nume;
        }

        public String getCnp() {
            return cnp;
        }

        public void setCnp(String cnp) {
            this.cnp = cnp;
        }

        //metoda getVarsta
        long getVarsta() {
            int zi = Integer.parseInt(cnp.substring(5, 7));
            int luna = Integer.parseInt(cnp.substring(3, 5));
            int an = Integer.parseInt(cnp.substring(1, 3));
            int sex = Integer.parseInt(cnp.charAt(0) + "");
            ;
            if (sex == 1 || sex == 2)
                an += 1900;
            else
                an += 2000;
            LocalDate birth_date = LocalDate.of(an, luna, zi);

            long zile_dif = ChronoUnit.YEARS.between(birth_date, LocalDate.now());

            return zile_dif;
        }
    }
    static boolean validare_cnp(String cnp){
        if(cnp.length()!=13)return false; //lungime cnp

        //sa fie doar cifre
        for(int i=0;i<cnp.length();i++)
            if(cnp.charAt(i)<'0'||cnp.charAt(i)>'9')
                return false;
        if(cnp.charAt(0)=='0'||cnp.charAt(0)=='3'||cnp.charAt(0)=='4'||cnp.charAt(0)=='7'||cnp.charAt(0)=='8'
                ||cnp.charAt(0)=='9') return false; // prima sa fie 1 2 5 6

        //calcul si verificare cifra de control
        int suma=0;
        String nr="279146358279";
        for(int i=0;i<12;i++){
            int a=Integer.parseInt(cnp.charAt(i)+"");
            int b=Integer.parseInt(nr.charAt(i)+"");
            suma+=a*b;
        }
        int ultima_cifra=Integer.parseInt(cnp.charAt(12)+"");
        if(ultima_cifra!=suma%11)
            return false;
        return true;
    }
    public static void main(String[] args) {
        List<Persoana> persoane=new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("n= ");
        int n=sc.nextInt();
        String nume,cnp;
        sc.nextLine();
        for(int i=0;i<n;i++){
            System.out.print("nume: ");
            nume=sc.nextLine();
            do {
                System.out.print("cnp: ");
                cnp = sc.nextLine();
            }while(!validare_cnp(cnp));
            persoane.add(new Persoana(nume,cnp));
        }
        for(Persoana persoana:persoane){
            System.out.print(persoana.getNume()+" ");
            System.out.print(persoana.getCnp()+" ");
            System.out.println(persoana.getVarsta());
        }
        sc.close();
    }
}

