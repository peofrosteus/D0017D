/**
 * D0017D - Inlämningsuppgift 2 - Räknar ut pris för genererad solenergi med formlen 
 * Produktion (kWh) = Solinstrålning (Wh/m2/timme) x soltimmar x storlek (m2) x verkningsgrad
 * 
 * @author Peo Frosteus, pereno-0
 * Programmet tar input från användaren, datum, tid för 
 * soluppgång och solnedgång och beräknar genererad energi 
 * samt värdet av denna.
 * Endast datum för juni och juli godtas
 * Angiven tid för soluppgång måste vara 
 * senare än dito för solnedgång.
 * Programmet använder konstansta värden angivna
 * i uppgiften.
 * Formel för beräkning som används är även denna
 * angiven i uppgiften.
 * Vid godkänd inmatning skrivs resultatet av beräkningen ut.
 * Kontroll gör för korrekt inmatad dag 
 * Avrundningar sker i slutet, innan utskrift 
 */

//Importera scanner för user input
import java.util.Scanner;
//Import av paket för Decimal Format
import java.text.DecimalFormat;

public class Uppgift2 {

    public static void main(String[] args) {

        // Skapa decimalformat med två gällande siffror
        DecimalFormat df = new DecimalFormat("##.##");

        // Deklatera konstanter
        final int NOOFPANELS; // Antal paneler
        final float PANELSIZE; // Panelernas storlek
        final int SOLARCONSTANT; // Solkonstant
        final float EFFIENCY; // Verkningsgrad
        final float PRICE; // Elpris
        final int minPerHour; // Minuter per timme

        // Tilldela konstanter värden
        NOOFPANELS = 27;
        PANELSIZE = 1.7f;
        SOLARCONSTANT = 166;
        EFFIENCY = 0.2f;
        PRICE = 0.9f;
        minPerHour = 60;

        // Deklarera variabler
        String todaysDate; // Dagens datum
        String sunriseTime; // Klockslag för soluppgång
        String sunsetTime; // Klockslag för solnedgång
        int year, month, day; // År, månad och dag
        int riseHour, riseMinute; // Timme och minut för soluppgång
        int setHour, setMinute; // Timme och minut för solnedgång
        float sunHour; // Antal soltimmar
        float production; // Antal producerade kilowattimmar
        float prodValue; // Värdet av producerade elen

        // Inmatningas-variabler
        Scanner dateInput = new Scanner(System.in); // Dagens datum
        Scanner sunriseInput = new Scanner(System.in); // Klockslag för soluppgång
        Scanner sunsetInput = new Scanner(System.in); // Klockslag för solnedgång

        // Hämta inmatningar
        System.out.print("Skriv in dagens datum [YYYY-MM-DD]> ");
        dateInput.useDelimiter("-|\\s"); // - som delimiter, så att vi slipper ett extra - efter dagen
        year = dateInput.nextInt();
        month = dateInput.nextInt();
        day = dateInput.nextInt();

        if (day < 0 | month < 0 | year < 0) { // Om något av inmatade tal är negativa, avsluta programmet
            System.out.printf("Felaktigt datum");
            System.exit(0);
        }

        System.out.print("Skriv in tidpunkt soluppgång [hh:mm]> ");
        sunriseInput.useDelimiter(":|\\s"); // : som delimter
        riseHour = sunriseInput.nextInt();
        riseMinute = sunriseInput.nextInt();

        if (riseHour < 0 | riseMinute < 0) { // Om något av inmatade tal är negativa, avsluta programmet
            System.out.printf("Felaktig tidpunkt för soluppgång angiven.");
            System.exit(0);
        }

        System.out.print("Skriv in tidpunkt solnedgång [hh:mm]> ");
        sunsetInput.useDelimiter(":|\\s"); // : som delimter
        setHour = sunsetInput.nextInt();
        setMinute = sunsetInput.nextInt();

        dateInput.close(); // Stänger scanner
        sunsetInput.close(); // Stänger scanner
        sunriseInput.close(); // Stänger scanner

        if (setHour < 0 | setMinute < 0) { // Om något av inmatade tal är negativa, avsluta programmet
            System.out.printf("Felaktig tidpunkt för solnedgång angiven.");
            System.exit(0);
        }

        // Beräkna antalet soltimmar
        sunHour = (setHour + setMinute / minPerHour) - (riseHour + riseMinute / minPerHour);

        // Kolla om starttiden är senare än sluttiden, detta är om antalet soltimmar är
        // negativt
        if (sunHour < 0) {
            System.out.printf("Felaktig inmatning. Soluppgång får inte komma efter solnedgång.");
        }
        // Kolla att angiven månad är i juni eller juli
        else if (month < 6 | month > 7) {
            System.out.printf("Felaktigt datum. Angivet datum måste infalla i juni eller juli.");
        }
        // Kolla att angiven dag är giltig, mellan 0 och 30 för juni och mellan 0 och 31
        // för juli
        else if ((month == 6 & (day < 0 | day > 30)) | (month == 7 & (day < 0 | day > 31))) {
            System.out.printf("Felaktig dag angiven. Juni har 30 dagar. Juli har 31 dagar.");
        }
        // Kolla att angiven tid för soluppgång är korrekt
        else if ((riseHour < 0 | riseHour > 23) | (riseMinute < 0 | riseMinute > 59)) {
            System.out.printf("Felaktig tidpunkt för soluppgång angiven.");
        }
        // Kolla att angiven tid för solnedgång är korrekt
        else if ((setHour < 0 | setHour > 23) | (setMinute < 0 | setMinute > 59)) {
            System.out.printf("Felaktig tidpunkt för solnedgång angiven.");
        }
        // Om inmatningen är okej, gå vidare med beräkning och utskrift
        else {
            // Beräkna elproduktionen, Produktion (kWh) = Solinstrålning (Wh/m2/timme) x
            // soltimmar x storlek (m2) x verkningsgrad
            production = (SOLARCONSTANT * sunHour * NOOFPANELS * PANELSIZE * EFFIENCY) / 1000;

            // Beräkna priset för elproduktionen
            prodValue = production * PRICE;

            // Skriv ut resultetet
            System.out.printf("==========================================\n");
            System.out.printf("Soltimmar: %s timmar\n", df.format(sunHour));
            System.out.printf("Produktionen %s-0%s-%s blir %s kWh till ett värde av %s kr", year, month, day,
                    df.format(production), df.format(prodValue));
        }

    }

}