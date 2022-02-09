/**
 * D0017D - Inlämningsuppgift 1
 * @author Peo Frosteus, pereno-0
 * Beräknar laddeffekt och laddtid för olika spänningar och strömstyrkor 
 * och skriver ut dessa i en tabell.
 */

//Import av paket för Decimal Format
import java.text.DecimalFormat;

public class Uppgift1 {

    public static void main(String[] args) {

        // Skapa decimalformat med två gällande siffror
        DecimalFormat df = new DecimalFormat("##.##");

        // Deklarera konstanter
        float batCapacity; // Batteriets kapacitet
        int current10A; // Ström om 10 A
        int current16A; // Ström om 16 A
        int current32A; // Ström om 32 A
        int voltage230; // Spänning om 230 V
        int voltage400; // Spänning om 400 V

        // Deklarera effekt
        float powerRow1; // Effeketen som skall skrivas ut på rad 1
        float powerRow2; // Effeketen som skall skrivas ut på rad 2
        double powerRow3; // Effeketen som skall skrivas ut på rad 3
        double powerRow4; // Effeketen som skall skrivas ut på rad 4
        double powerRow5; // Effeketen som skall skrivas ut på rad 5

        // Deklarera laddtider
        float timeRow1; // Tiden som skall skrivas ut på rad 1
        float timeRow2; // Tiden som skall skrivas ut på rad 2
        double timeRow3; // Tiden som skall skrivas ut på rad 3
        double timeRow4; // Tiden som skall skrivas ut på rad 4
        double timeRow5; // Tiden som skall skrivas ut på rad 5

        // Tilldela värde för batteriets kapacitet
        batCapacity = 35.8f;

        // Tilldela värden för ström
        current10A = 10;
        current16A = 16;
        current32A = 32;

        // Tilldela värden för spänning
        voltage230 = 230;
        voltage400 = 400;

        // Räkna ut effekterna
        powerRow1 = (current10A * voltage230) / 1000f;
        powerRow2 = (current16A * voltage230) / 1000f;
        powerRow3 = (current10A * voltage400 * Math.sqrt(3)) / 1000;
        powerRow4 = (current16A * voltage400 * Math.sqrt(3)) / 1000;
        powerRow5 = (current32A * voltage400 * Math.sqrt(3)) / 1000;

        // Räkna ut laddningstider
        timeRow1 = batCapacity / powerRow1;
        timeRow2 = batCapacity / powerRow2;
        timeRow3 = batCapacity / powerRow3;
        timeRow4 = batCapacity / powerRow4;
        timeRow5 = batCapacity / powerRow5;

        // Skriv ut tabellen
        System.out.print("Batteri " + batCapacity + " kWh\n");
        System.out.print("Ström(A)\tSpänning(V)\tLaddeffekt(kW)\tLaddningstid(h)\n");
        System.out.print("===============================================================\n");
        System.out.print(
                current10A + "\t\t" + voltage230 + "\t\t" + df.format(powerRow1) + "\t\t" + df.format(timeRow1) + "\n");
        System.out.print(
                current16A + "\t\t" + voltage230 + "\t\t" + df.format(powerRow2) + "\t\t" + df.format(timeRow2) + "\n");
        System.out.print(
                current10A + "\t\t" + voltage400 + "\t\t" + df.format(powerRow3) + "\t\t" + df.format(timeRow3) + "\n");
        System.out.print(
                current16A + "\t\t" + voltage400 + "\t\t" + df.format(powerRow4) + "\t\t" + df.format(timeRow4) + "\n");
        System.out.print(
                current32A + "\t\t" + voltage400 + "\t\t" + df.format(powerRow5) + "\t\t" + df.format(timeRow5) + "\n");
    }

}