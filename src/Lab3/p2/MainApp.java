package Lab3.p2;

import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.System.exit;

public class MainApp {
    static class Produs{
        private String prodName;
        private int prodQuantity;
        private float prodPrice;
        private LocalDate prodDate;
        public static float incasari=0;
        public Produs(String prodName, int prodQuantity, float prodPrice, LocalDate prodDate) {
            this.prodName = prodName;
            this.prodQuantity = prodQuantity;
            this.prodPrice = prodPrice;
            this.prodDate = prodDate;
        }
        public String getProdName() {return prodName;}
        public int getProdQuantity() {return prodQuantity;}
        public float getProdPrice() {return prodPrice;}
        public LocalDate getProdDate() {return prodDate;}
        public void setQuantity(int prodQuantity) {this.prodQuantity = prodQuantity;}
        public void setIncasari(float incasari) {this.incasari += incasari;}
    }

    public static void main(String[] args) throws IOException {
        String nume;
        int cant;
        float pret;
        LocalDate date;
        ArrayList<Produs> produse = new ArrayList<Produs>();
        try(Scanner sc = new Scanner(new File("src/Lab3/p2/produse.csv"))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] data = line.split(",");
                nume = data[0];
                pret = Float.parseFloat(data[1]);
                cant = Integer.parseInt(data[2]);
                date = LocalDate.parse(data[3]);
                produse.add(new Produs(nume,cant,pret,date));
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Meniul de forma:");
        System.out.println("0.Exit");
        System.out.println("1.Afisare produse ");
        System.out.println("2.Afisare produse expirate ");
        System.out.println("3.Vanzare produs ");
        System.out.println("4.Afisare produse cu pret minim ");
        System.out.println("5.Salvare produse cu o cantitate mai mica decat una introdusa, in fisier");
        System.out.print("Introduceti optiunea dorita: ");
        Scanner sc = new Scanner(System.in);
        int opt=sc.nextInt();
        do {
            switch (opt) {
                default:
                    System.out.println("Optiunea invalida!");
                    break;
                case 0:
                    exit(0);
                case 1:
                    for (Produs prod : produse) {
                        System.out.println(prod.getProdName() + " " + prod.getProdQuantity() + " " +
                                prod.getProdPrice() + " " + prod.getProdDate() );
                    }
                    break;
                case 2:
                    for (Produs prod : produse)
                        if(prod.getProdDate().isBefore(LocalDate.now())){
                            System.out.println(prod.getProdName() + " " + prod.getProdQuantity() + " " +
                                    prod.getProdPrice() + " " + prod.getProdDate());
                        }
                    break;
                case 3:
                    boolean ok=true;
                    String nume_vandut;
                    do {
                        System.out.print("Nume produs vandut: ");
                        nume_vandut = sc.next();
                        for (Produs prod : produse)
                            if(prod.getProdName().equals(nume_vandut)){
                                ok=false;
                            }
                        if(ok) System.out.println("Nu s-a gasit produsul! Reincercati!");
                    }while(ok);
                    System.out.print("Cantitatea dorita: ");
                    int cant_vanduta = sc.nextInt();
                    for (Produs prod : produse) {
                        if (prod.getProdName().equals(nume_vandut))
                            if (prod.getProdQuantity() > cant_vanduta) {
                                prod.setIncasari(prod.getProdPrice() * cant_vanduta);
                                prod.setQuantity(prod.getProdQuantity() - cant_vanduta);
                            } else if (prod.getProdQuantity() == cant_vanduta) {
                                produse.remove(prod);
                                prod.setIncasari(prod.getProdPrice() * cant_vanduta);
                                break;
                            } else
                                System.out.println("Cantitatea dorita este prea mare!");
                    }
                    System.out.println("Incasari totale: "+Produs.incasari);
                    break;
                case 4:
                    float minim=Float.MAX_VALUE;
                    for (Produs prod : produse)
                        if(prod.getProdPrice() < minim)
                            minim=prod.getProdPrice();
                    for (Produs prod : produse)
                        if(prod.getProdPrice()==minim)
                            System.out.println(prod.getProdName() + " " + prod.getProdQuantity() + " " +
                                    prod.getProdPrice() + " " + prod.getProdDate());
                    break;
                case 5:
                    try {
                        FileWriter fw = new FileWriter("src/Lab3/p2/Produse_salvate.txt");
                        System.out.print("Introduceti cantitatea dorita: ");
                        int cant_dorita = sc.nextInt();
                        for (Produs prod : produse)
                            if (prod.getProdQuantity() < cant_dorita) {
                                fw.write(prod.getProdName() + " " + prod.getProdQuantity() + " " + prod.getProdPrice() + " "
                                        + prod.getProdDate() +"\n");
                            }
                        fw.close();
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
            System.out.print("Introduceti optiunea dorita: ");
            opt=sc.nextInt();
        }while(true);
    }
}
