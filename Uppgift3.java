
/**
 * D0017D - Inlämningsuppgift 3 - Simulerar spelet 12 med tre tärningar
 * @author Peo Frosteus, pereno-0
 * Programmet simulerar spelet 12
 * Användaren uppmanas att välja att slå tärning 1, 2 eller 3
 * Man får bara slå varje tärning en gång per omgång
 * Vinst är en tärningssumma av 12 på 2 eller 3 tärningar
 * Vid slutet på varje omgång anges resultat och 
 * antalet vinster/förluster sparas tills dess att programmet avslutas
 * Om användaren anger q avslutas programet
 * 
 */

import java.util.Scanner; //Importera scanner för user input
import java.util.Random; // Importera Random för slumptal

public class Uppgift3 {
    public static void main(String[] args) {

        // Deklarera konstanter
        final int SIDES; // Antal sidor på en tärning
        final int WINNINGSUM; // Summan som ger vinst

        // Deklarea variabler
        String userInput; // Inmatningen
        int dice1; // Tärning #1
        int dice2; // Tärning #2
        int dice3; // Tärning #3
        int noOfWins; // Antal vinster
        int noOfLosses; // Antal förluster
        int sum; // Summa av alla tärningar

        // Sätt värden på konstanter
        SIDES = 6;
        WINNINGSUM = 12;

        // Sätt världen på variabler
        noOfWins = 0;
        noOfLosses = 0;
        sum = 0;

        // Deklarera inmatning
        Scanner keyboard = new Scanner(System.in);

        // Starta nytt spel
        System.out.printf("Välkommen till spelet 12. Du ska slå 1-3 tärningar och försöka få summan 12...\n");
        do {
            // Sätt alla tärningar till noll
            dice1 = 0;
            dice2 = 0;
            dice3 = 0;

            do // Starta programmer och kör tills användaren matar in 'q'
            {
                // Uppmana användaren att välja
                System.out.printf("Ange vilken tärning du vill slå [1,2,3](avsluta med q): ");

                userInput = keyboard.nextLine();

                // Kolla inmatningen
                switch (userInput) {

                    case "1": // Om användaren väljer tärning 1
                        if (dice1 == 0) { // Om tärningen inte är slagen i denna omgång
                            dice1 = (int) (Math.random() * SIDES) + 1;
                            break;
                        } else { // Om tärningen ÄR slagen i denna omgång
                            System.out.printf("Du har redan slagit den här tärningen en gång, välj en annan.\n");
                            break;
                        }

                    case "2": // Om användaren väljer tärning 2
                        if (dice2 == 0) { // Om tärningen inte är slagen i denna omgång
                            dice2 = (int) (Math.random() * SIDES) + 1;
                            break;
                        } else { // Om tärningen ÄR slagen i denna omgång
                            System.out.printf("Du har redan slagit den här tärningen en gång, välj en annan.\n");
                            break;
                        }

                    case "3": // Om användaren väljer tärning 3
                        if (dice3 == 0) { // Om tärningen inte är slagen i denna omgång
                            dice3 = (int) (Math.random() * SIDES) + 1;
                            break;
                        } else { // Om tärningen ÄR slagen i denna omgång
                            System.out.printf("Du har redan slagit den här tärningen en gång, välj en annan.\n");
                            break;
                        }

                    case "q":
                        System.out.printf("Avslutar spel!");
                        System.exit(0);
                        break;

                    default:
                        System.out.printf("Felaktig inmatning.\n");
                        break;

                }
                sum = dice1 + dice2 + dice3; // Summera efter varje slag
                System.out.printf("%s %s %s Summa: %s - Vinst: %s Förlust: %s \n", dice1, dice2, dice3, sum, noOfWins,
                        noOfLosses);

            } while ((dice1 == 0 || dice2 == 0 || dice3 == 0) && sum != WINNINGSUM); // Kör omgången tills dess att alla
                                                                                     // tärningar är slagna ELLER om
                                                                                     // summan
                                                                                     // blir 12 innan dess

            // Spel avslutat
            if (sum == WINNINGSUM) // Om summan är 12, öka vinst-räknaren med 1, och skriv ut utfallet av omgången.
            {
                noOfWins++;
                System.out.printf("Vinst! Summan blev %s \n", sum);
            } else // Annars, öka förlust-räknaren med 1
            {
                noOfLosses++;
                System.out.printf("Förlust! Summan blev %s \n", sum);
            }

            System.out.printf("Nästa omgång!\n");

        } while (true); // Kör programmet så länge som användaren inte anger 'q'
    }
}