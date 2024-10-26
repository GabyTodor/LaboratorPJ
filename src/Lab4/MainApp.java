package Lab4;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class MainApp {
    // Enum pentru starea echipamentelor
    public enum StareEchipament {
        achizitionat, expus, vandut;
    }

    // Enum pentru modul de tipărire
    public enum ModTiparire {
        color, alb_negru;
    }

    // Enum pentru formatul de copiere
    public enum FormatCopiere {
        A3,A4;
    }

    // Enum pentru sistemele de operare
    public enum SistemOperare {
        windows,linux;
    }

    /**
     * Fiecare echipament este înregistrat cu o denumire,
     * un număr de inventar nr_inv, are un preţ pret şi este plasat într-o anumită zonă din magazie
     * zona_mag. Orice echipament poate fi într-una din situaţiile: achizitionat, expus, vandut.
     * Firma comercializează următoarele tipuri de echipamente: imprimante, copiatoare şi sisteme de calcul.
     * @author Gaby
     * @version 1
     * @since 2024
     */
    public abstract static class Echipament implements Serializable {
        protected String denumire;
        protected int nrInv;
        protected double pret;
        protected String zonaMag;
        protected StareEchipament stare;
        public String tip;

        /**
         * Constructorul clasei Echipament
         * @param denumire numele echipamentului
         * @param nrInv numar inventar - cate bucati
         * @param pret pretul unui echipament
         * @param zonaMag zona din magazie unde se afla
         * @param stare starea echipamentului - achizitionat, expus, vandut
         * @param tip tipul echipamentului - imprimante, copiatoare, sisteme de calcul
         */
        public Echipament(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare,String tip) {
            this.denumire = denumire;
            this.nrInv = nrInv;
            this.pret = pret;
            this.zonaMag = zonaMag;
            this.stare = stare;
            this.tip = tip;
        }
        String ToString(){
            return "Imprimanta: " + denumire + ", Nr. Inventar: " + nrInv +
                    ", Preț: " + pret + ", Zona: " + zonaMag + ", Stare: " + stare;
        }

        /**
         * Metoda care afiseaza toate detaliile unui echipament in particular
         */
        public abstract void afiseazaDetalii();

        /**
         * Getter care da acces de citire a variabilei membre care contine denumirea echipamentului
         * @return denumire
         */
        public String getDenumire() {
            return denumire;
        }

        /**
         * Getter care da acces de citire a variabilei membre care contine starea
         * @return stare
         */

        public StareEchipament getStare() {
            return stare;
        }

        /**
         * Setter pentru starea echipamentului
         * @param stare
         */
        public void setStare(StareEchipament stare) {
            this.stare = stare;
        }

    }

    /**
     * Imprimantele sunt caracterizate numărul de pagini scrise pe minut ppm, rezoluţie (număr de
     * puncte per inch dpi) şi număr de pagini/cartuş p_car şi modul de tipărire (color sau alb negru).
     * Mosteneste clasa Echipament preluand proprietatile de baza de la aceasta.
     * @author Gaby
     * @version 1
     * @since 2024
     */
    public static class Imprimanta extends Echipament implements Serializable {
        private int ppm; // pagini per minut
        private String dpi; // rezoluție
        private int pCar; // pagini per cartuș
        private ModTiparire modTiparire;

        /**
         * Constructorul aferent clasei Imprimanta
         * @param tip - imprimanta
         * @param ppm - pagini scrise pe minut
         * @param dpi - rezolutia
         * @param pCar - pagini scrise per cartus
         * @param modTiparire - mod tiparire : color sau alb-negru
         */
        public Imprimanta(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare,
                          String tip,int ppm, String dpi, int pCar, ModTiparire modTiparire) {
            super(denumire, nrInv, pret, zonaMag, stare,tip);
            this.ppm = ppm;
            this.dpi = dpi;
            this.pCar = pCar;
            this.modTiparire = modTiparire;
        }

        /**
         * Setter pentru modul de tiparire specific al unei imprimante
         * @param modTiparire
         */
        public void setModTiparire(ModTiparire modTiparire) {
            this.modTiparire = modTiparire;
        }

        /**
         * Functie de afisare a proprietatilor unei imprimante
         */
        @Override
        public void afiseazaDetalii() {
            System.out.println("Imprimanta: " + denumire + ", Nr. Inventar: " + nrInv +
                    ", Preț: " + pret + ", Zona: " + zonaMag + ", Stare: " + stare +
                    ", PPM: " + ppm + ", DPI: " + dpi + ", Pagini/Cartuș: " + pCar +
                    ", Mod Tipărire: " + modTiparire);
        }
    }

    /**
     * Copiatoarele sunt caracterizate de numărul de pagini/toner p_ton şi formatul de copiere
     * (A3 sau A4). Mosteneste clasa Echipament preluand proprietatile de baza de la aceasta.
     * @author Gaby
     * @version 1
     * @since 2024
     */
    public static class Copiator extends Echipament implements Serializable{
        private int pTon; // pagini per toner
        private FormatCopiere formatCopiere;

        /**
         * Constructorul clasei copiator
         * @param tip - copiator
         * @param pTon - numar pagini per toner
         * @param formatCopiere - poate fi A3 sau A4
         */
        public Copiator(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare,
                        String tip,int pTon, FormatCopiere formatCopiere) {
            super(denumire, nrInv, pret, zonaMag, stare,tip);
            this.pTon = pTon;
            this.formatCopiere = formatCopiere;
        }

        /**
         * Setter pentru formatul de copiere al unui copiator
         * @param formatCopiere
         */
        public void setFormatCopiere(FormatCopiere formatCopiere) {
            this.formatCopiere = formatCopiere;
        }

        /**
         * Functie de afisare a detaliilor unui copiator
         */

        @Override
        public void afiseazaDetalii() {
            System.out.println("Copiator: " + denumire + ", Nr. Inventar: " + nrInv +
                    ", Preț: " + pret + ", Zona: " + zonaMag + ", Stare: " + stare +
                    ", Pagini/Toner: " + pTon + ", Format Copiere: " + formatCopiere);
        }
    }

    /**
     * Sistemele de calcul au un monitor de un anumit tip tip_mon, un procesor de o anumită
     * viteză vit_proc, o capacitate a HDD c_hdd şi li se poate instala unul din sistemele de operare
     * Windows sau Linux. Mosteneste clasa Echipament preluand proprietatile de baza de la aceasta.
     * @author Gaby
     * @version 1
     * @since 2024
     */
    public static class SistemCalcul extends Echipament implements Serializable{
        private String tipMon; // tipul monitorului
        private double vitProc; // viteza procesorului
        private int cHdd; // capacitatea HDD
        private SistemOperare sistemOperare;

        /**
         * Constructorul clasei SistemCalcul
         * @param tip - sistem de calcul
         * @param tipMon - tipul monitorului
         * @param vitProc - viteza de procesare
         * @param cHdd - capacitate hdd
         * @param sistemOperare - tipul sistemului de operare: Linux sau Windows
         */
        public SistemCalcul(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare,
                            String tip, String tipMon, double vitProc, int cHdd, SistemOperare sistemOperare) {
            super(denumire, nrInv, pret, zonaMag, stare,tip);
            this.tipMon = tipMon;
            this.vitProc = vitProc;
            this.cHdd = cHdd;
            this.sistemOperare = sistemOperare;
        }

        /**
         * Setter pentru sistemul de operare
         * @param sistemOperare
         */
        public void instaleazaSistemOperare(SistemOperare sistemOperare) {
            this.sistemOperare = sistemOperare;
        }

        /**
         * Functie pentru afisarea proprietatilor sistemelor de calcul
         */
        @Override
        public void afiseazaDetalii() {
            System.out.println("Sistem de calcul: " + denumire + ", Nr. Inventar: " + nrInv +
                    ", Preț: " + pret + ", Zona: " + zonaMag + ", Stare: " + stare +
                    ", Tip Monitor: " + tipMon + ", Viteză Procesor: " + vitProc +
                    ", Capacitate HDD: " + cHdd + ", Sistem Operare: " + sistemOperare);
        }
    }
    static void scrie(Object o,String fis){
        try{
            FileOutputStream f=new FileOutputStream(fis);
            ObjectOutputStream oos=new ObjectOutputStream(f);
            oos.writeObject(o);
            oos.close();
            f.close();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    static Object citeste(String fis){
        try{
            FileInputStream f = new FileInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(f);
            Object o=ois.readObject();
            ois.close();
            f.close();
            return o;
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        List<Echipament> echipamente = new ArrayList<Echipament>();
        Scanner scanner=new Scanner(System.in);
        try(Scanner sc=new Scanner(new File("src/Lab4/echipamente.txt"))) {
            while(sc.hasNext()){
                String line=sc.nextLine();
                String[] data=line.split(";");
                String denumire=data[0];
                int nrInv= Integer.parseInt(data[1]);
                double pret=Double.parseDouble(data[2]);
                String zonaMag=data[3];
                StareEchipament stare=StareEchipament.valueOf(data[4]);
                String tip=data[5];
                if(tip.equals("imprimanta")){
                    int ppm= Integer.parseInt(data[6]);
                    String dpi= data[7];
                    int pCar= Integer.parseInt(data[8]);
                    ModTiparire modTiparire=ModTiparire.valueOf(data[9]);
                    Imprimanta imprimanta=new Imprimanta(denumire,nrInv,pret,zonaMag,stare,tip,ppm,dpi,pCar,modTiparire);
                    echipamente.add((Echipament)imprimanta);
                }
                if(tip.equals("copiator")){
                    int pTon= Integer.parseInt(data[6]);
                    FormatCopiere formatCopiere=FormatCopiere.valueOf(data[7]);
                    Copiator copiator=new Copiator(denumire,nrInv,pret,zonaMag,stare,tip,pTon,formatCopiere);
                    echipamente.add((Echipament)copiator);
                }
                if(tip.equals("sistem de calcul")){
                    String tipMon=data[6];
                    double vitProc=Double.parseDouble(data[7]);
                    int cHdd= Integer.parseInt(data[8]);
                    SistemOperare sistemOperare=SistemOperare.valueOf(data[9]);
                    SistemCalcul sistemCalcul=new SistemCalcul(denumire,nrInv,pret,zonaMag,stare,tip,tipMon,vitProc,cHdd,sistemOperare);
                    echipamente.add((Echipament)sistemCalcul);
                }
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String denumire_c="",nou="";
        boolean gasit;
        System.out.println("\nMeniu:");
        System.out.println("1. Afișarea tuturor echipamentelor");
        System.out.println("2. Afișarea imprimantelor");
        System.out.println("3. Afișarea copiatoarelor");
        System.out.println("4. Afișarea sistemelor de calcul");
        System.out.println("5. Modificarea stării unui echipament");
        System.out.println("6. Setarea modului de tipărire pentru imprimante");
        System.out.println("7. Setarea formatului de copiere pentru copiatoare");
        System.out.println("8. Instalarea sistemului de operare pentru sisteme de calcul");
        System.out.println("9. Afișarea echipamentelor vândute");
        System.out.println("9. Să se realizeze două metode statice pentru serializarea / deserializarea colecției de " +
                "obiecte în fișierul echip.bin ");
        System.out.println("0. Ieșire");
        do {
            System.out.print("Introduceti optiunea dorita: ");
            int opt= scanner.nextInt();
            scanner.nextLine();
            switch (opt){
                case 0:
                    exit(0);
                    break;
                case 1:
                    for(Echipament echipament : echipamente){
                        echipament.afiseazaDetalii();
                    }
                    break;
                case 2:
                    for(Echipament echipament : echipamente){
                        if(echipament.tip.equals("imprimanta"))
                             echipament.afiseazaDetalii();
                    }
                    break;
                case 3:
                    for(Echipament echipament : echipamente){
                        if(echipament.tip.equals("copiator"))
                            echipament.afiseazaDetalii();
                    }
                    break;
                case 4:
                    for(Echipament echipament : echipamente){
                        if(echipament.tip.equals("sistem de calcul"))
                            echipament.afiseazaDetalii();
                    }
                    break;
                case 5:
                    System.out.print("Introduceti denumirea echipamentului: ");
                    denumire_c=scanner.nextLine();
                    System.out.print("Introduceti noua stare : 1-achizitionat 2-expus 3-vandut  : ");
                    int s_noua=scanner.nextInt();
                    scanner.nextLine();
                    gasit=false;
                    for(Echipament echipament : echipamente){
                        if(echipament.getDenumire().equals(denumire_c)) {
                            gasit=true;
                            if (s_noua == 1) echipament.setStare(StareEchipament.achizitionat);
                            else if (s_noua == 2) echipament.setStare(StareEchipament.expus);
                            else if(s_noua == 3) echipament.setStare(StareEchipament.vandut);
                            else System.out.println("Introduceti 1 2 sau 3!!");
                        }
                    }
                    if(!gasit){
                        System.out.println("echipament negasit");
                    }
                    break;
                case 6:
                    System.out.print("Introduceti denumirea imprimantei: ");
                    denumire_c=scanner.nextLine();
                    System.out.print("Introduceti mod scriere : 1-color 2-alb_negru  : ");
                    s_noua= scanner.nextInt();
                    gasit=false;
                    for(Echipament echipament : echipamente){
                        if(echipament.getDenumire().equals(denumire_c)&&echipament.tip.equals("imprimanta")){
                            gasit=true;
                            Imprimanta i=(Imprimanta)echipament;
                            if(s_noua==1) i.setModTiparire(ModTiparire.color);
                            else if(s_noua==2) i.setModTiparire(ModTiparire.alb_negru);
                            else System.out.println("Introduceti 1 sau 2!!");

                        }
                    }
                    if(!gasit){
                        System.out.println("echipament negasit");
                    }
                    break;
                case 7:
                    System.out.println("Introduceti denumirea copiatorului: ");
                    denumire_c=scanner.nextLine();
                    System.out.println("Introduceti noul format de copiere 1-A3 2-A4  : ");
                    s_noua= scanner.nextInt();
                    scanner.nextLine();
                    gasit=false;
                    for(Echipament echipament : echipamente){
                        if(echipament.getDenumire().equals(denumire_c)&&echipament.tip.equals("copiator")){
                            gasit=true;
                            Copiator c=(Copiator) echipament;
                            if(s_noua==1) c.setFormatCopiere(FormatCopiere.A3);
                            else if(s_noua==2) c.setFormatCopiere(FormatCopiere.A4);
                            else System.out.println("Introduceti 1 sau 2!!");
                        }
                    }
                    if(!gasit){
                        System.out.println(" echipament negasit");
                    }
                    break;
                case 8:
                    System.out.println("Introduceti denumirea sistemului de calcul: ");
                    denumire_c=scanner.nextLine();
                    System.out.println("Ce sistem de operare? 1-Windows 2-Linux: ");
                    int s_nou=scanner.nextInt();
                    scanner.nextLine();
                    gasit=false;
                    for(Echipament echipament : echipamente){
                        if(echipament.getDenumire().equals(denumire_c) && echipament.tip.equals("sistem de calcul")){
                            gasit=true;
                            SistemCalcul sc=(SistemCalcul) echipament;
                            if(s_nou==1)
                                sc.instaleazaSistemOperare(SistemOperare.windows);
                            else if(s_nou==2)
                                sc.instaleazaSistemOperare(SistemOperare.linux);
                            else System.out.println("Introduceti 1 sau 2!!");
                        }
                    }
                    if(!gasit){
                        System.out.println("echipament negasit");
                    }
                    break;
                case 9:
                    for(Echipament echipament : echipamente)
                        if(echipament.getStare().toString().equals("vandut"))
                            echipament.afiseazaDetalii();
                    break;
                case 10:
                    scrie(echipamente,"echip.bin");
                    List<Echipament> q;
                    q= (List<Echipament>) citeste("echip.bin");
                    for(Echipament echipament : q){
                        System.out.println(echipament.toString());
                    }
                    break;
                default:
                    System.out.println("Optiunea invalida!");
                    break;
            }
        }while(true);
    }
}
