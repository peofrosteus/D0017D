
/**
 * D0017D - Inlämningsuppgift 5 - Programmet räknar ut bas- och mantelarean för en kon som användaren anger, samt förkortar de bråk som användaren matar in
 * @author Peo Frosteus, pereno-0
 * 
 *  Flödesschema:
 *  1: Starta programet
 *  2: Skriv ut "Test av area och volymmetoderna"
 *  3: Gå in i en while-loop
 *  4: Hämta radie och höjd
 *  5: Om någon av inmatningarna är "q" gå till steg 10
 *  6: Skriv ut värden på radie och höjd
 *  7: Beräkna basarea, ytans area och volymen
 *  8: Skriv ut basarea, ytans area och volymen, med två decimaler
 *  9: Gå till steg 4
 *  10: Gå in en ny while-loop
 *  11: Skriv ut Test av bråktalsmetoderna
 *  12: Hämta täljare och nämnare
 *  13: Om någon av inmatningarna är "q" gå till steg 17
 *  14: Beräkna och förkorta bråket
 *  15: Printa ut det förkortade bråket
 *  16: Gå till steg 11
 *  17: Avsluta programet
 *  
 */
import java.text.DecimalFormat;
import java.util.*; //Importera scanner för user input

public class Uppgift5 {
    private static Scanner userInput = new Scanner(System.in); // För inmatning

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("##.##"); // Skapa decimalformat med två gällande siffror

        // Deklarera variabler
        float baseArea; // Basarean
        float surfaceArea; // Mantelarean
        // float hypotenuse; // Hypotenusan
        float volume; // Volymen
        // String input; // Användarens input
        int[] res = new int[3];

        Integer radius; // Radien, Integer används för att kunna till null-värde
        Integer height; // Höjden
        Integer nominator; // Täljaren
        Integer denominator; // Nämnaren

        // Första utskriften
        System.out.printf("----------------------------------\n" + "# Test av area och volymmetoderna\n"
                + "---------------------------------- \n");

        // Räkna ut volym och areaor så länge som höjd och radie inte är null
        System.out.printf("> ");
        while (true) {
            // Hämta radie och höjd i tur och ordning och gå ur loopen om någon av dem är
            // null
            radius = getInput(); // Hämta radie
            if (radius == null) {
                break;
            }
            height = getInput(); // Hämta höjd
            if (height == null) {
                break;
            }

            System.out.printf("r = %s h = %s", radius, height); // Skriv ut värden på radie och höjd
            System.out.printf("\n"); // Radbrytning

            baseArea = area(radius); // Beräkna basarea
            surfaceArea = area(radius, height); // Beräkna ytans area
            volume = volume(radius, height); // Beräkna volymen

            System.out.printf("Basytans area: %s", df.format(baseArea));
            System.out.printf("\n"); // Radbrytning
            System.out.printf("Mantelytans area: %s", df.format(surfaceArea));
            System.out.printf("\n"); // Radbrytning
            System.out.printf("Volym: %s", df.format(volume));
            System.out.printf("\n"); // Radbrytning
            System.out.printf("\n"); // Radbrytning
        }

        // Början av bråken
        System.out.printf("----------------------------------\n" + "# Test av bråktalsmetoderna\n"
                + "----------------------------------\n");
        System.out.printf("> ");

        // börja nästa while loop. hämta täljare och nämnare i tur och ordning och gå ur
        // loopen om någon av dem är
        // null
        while (true) {
            nominator = getInput(); // Hämta täljare
            if (nominator == null) {
                break;
            }
            denominator = getInput(); // Hämta nämare
            if (denominator == null) {
                break;
            }
            res = fraction(nominator, denominator); // Beräkna kvoten

            System.out.printf("%s/%s = ", nominator, denominator); // Printa ut täljare och nämare
            printFraction(res); // Skriv ut det förkortade bråket
        }

    } // Slut på main

    // Metod för inmatning
    public static Integer getInput() {
        int num = Integer.MAX_VALUE; // skapa variabel att returnera
        userInput.useDelimiter("\\s|\\s"); // mellanslag som delimter
        while (true) {
            if (userInput.hasNextInt()) // On nästa inmatningen är en int, returnera en int
            {
                num = userInput.nextInt(); // Tilldela värdet nästa int
                return Math.abs(num); // Ta absolutbeloppet ifall användaren angett ett negativt tal
            }
            if (userInput.next().equals("q")) // Om nästa inmatning är q, returnera null
            {
                return null;
            }
        }
    }

    // Metoden för basarea
    public static float area(int radius) {
        return (float) (Math.pow(radius, 2) * Math.PI);
    }

    // Metod för mantelytans area
    public static float area(int radius, int height) {
        float side = pythagoras(radius, height); // Konens sida
        return (float) (side * radius * Math.PI);
    }

    // Metod för pythgoras sats
    public static float pythagoras(int sideA, int sideB) {
        return (float) Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2));
    }

    // Metod för volymen
    public static float volume(int radius, int height) {
        return (float) ((Math.pow(radius, 2) * height * Math.PI) / 3);
    }

    // Beräkning av bråken
    public static int[] fraction(int nominator, int denominator) {
        if (denominator == 0) {
            return null; // Om täljaren är noll, returnera null
        } else {

            int lcd; // Minsta gemensamma nämaren
            int integ; // Heltalsdelen
            int newNom; // Nya täljaren efter förkortning
            int newDem; // Nya nämnaren , efter förkortning
            int nom; // Slutgiliga täljaren
            int dem; // Slutgiltiga nämnaren

            lcd = sgd(nominator, denominator); // För att hitta minsta gemensamma nämaren

            newNom = nominator / lcd; // Förkorta täljaren
            newDem = denominator / lcd; // Förkorta nämnaren

            // Beräkna nya bråket
            integ = nominator / denominator; // Heltalsdelen
            nom = newNom % newDem;
            dem = newDem;

            int[] res = { integ, nom, dem }; // Skapa en lokal array

            return res; // Returnera arrayen
        }
    }

    // Metod för Euklides algoritm
    public static int sgd(int a, int b) {
        int c; // Temporär variabel

        if (b > a) // Byt plats på a och b om b är större än a
        {
            c = a;
            a = b;
            b = c;
        }

        while (b != 0) // Så länge b inte är 0
        {
            c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    // Metod för att skriva ut förkortade bråket
    public static void printFraction(int[] parts) {

        if (parts == null) { // Om nämaren var noll
            System.out.printf("\"Error\"\n");
        } else if (parts[0] == 0) // Om det inte fanns någon heltalsdel
        {
            System.out.printf("%s/%s\n", parts[1], parts[2]);
        } else if (parts[1] / parts[2] == 1) // Om det bara fanns en heltalsdel
        {
            System.out.printf("%s\n", parts[0]);
        } else if (parts[0] == 0 & parts[1] == 0 & parts[2] == 0) {
            System.out.printf("0\n");
        } else if (parts[1] == 0) // Om täljaren är noll
        {
            System.out.printf("%s\n", parts[0]);
        } else {
            System.out.printf("%s %s/%s\n", parts[0], parts[1], parts[2]); // Om det fanns en heltalsdel och ett bråk
        }
    }
}