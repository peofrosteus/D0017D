
/**
 * D0017D - Inlämningsuppgift 4 - Slumpar fram tal enligt användarens val samt sorterar och skriver ut dessa
 * @author Peo Frosteus, pereno-0
 * Användaren anger hur många slumptal i intervaller 0 - 999
 * som önskas.
 * Programmet skapar sedan dessa slumptal och lägger dem i en array med med samma storlek som antalet tal
 * Därefter skrivs arrayen ut.
 * Sedan ska programet ordna slumptalen så att dom ligger i ordningen minsta jämna heltal till största jämna heltal och sedan 
 * största udda heltal till minsta udda heltal
 * Även antalet udda och jämna tal skall skrivas ut, avskilda med ett bindestreck
 * 
 * Sorteringsalgoritmen som används är samma som från övningsuppgift 5, modifierad beroende på om sortering skall ske fallande eller stigande
 * 
 * Sista sorteringen, när jämna tal ordnas till stigande till vänster i arrayen och udda tal sorteras fallande 
 * går till på följande sätt:
 * 
 * Först stegas arrayen igenom och jämna tal multipliceras med -1
 * Arrayen sorteras sedan från minst till störst, alla jämna tal hamnar längst till vänster
 * Stega sedan igenom arrayen en gång till, alla negativa tal multipliceras med -1
 * Sortera sedan arrayen stigande från index 0 till index som motsvarar det sista jämna talet => Alla jämna tal sortade till vänster
 * Sortera sedan arrayen fallande från index som motsvarar det första udda talet till slutet av arrayen => Alla udda tal sorterade till höger
 * 
 * Skriv sedan ut arrayen, när man skrivit ut det sista jämna talet, skriv ut ett bindestreck och fortsätt sedan med de udda talen
 * Sista utskriften anger antalet tal i arrayen samt hur många av dessa som är jämna respektive udda
 * 
 */
import java.util.Scanner; //Importera scanner för user input

public class Uppgift4 {
    public static void main(String[] args) {
        // Deklarera konstanter
        final int LOWERLIMIT; // Nedre gränsvärdet av slumptal
        final int UPPERLIMIT; // Övre gränsvärdet av slumptal

        // Tilldela konstansta värden
        LOWERLIMIT = 0;
        UPPERLIMIT = 999;

        // Först, låt användaren ange hur många slumptal som önskas
        // Deklarerea inmatningen
        int noOfRandom; // Antal slumptal
        Scanner keyboard = new Scanner(System.in); // Ny scanner

        System.out.printf("Hur många slumptal i intervallet %s - %s önskas? ", LOWERLIMIT, UPPERLIMIT);
        noOfRandom = keyboard.nextInt();

        // Kontollera inmatningen
        if (noOfRandom < LOWERLIMIT | noOfRandom > UPPERLIMIT) {
            System.out.printf("Felaktig inmatning! Avslutar...");
            System.exit(0);
        }

        // Deklarera variabler
        int[] randomArray = new int[noOfRandom]; // Skapa en ny array med längden som användaren angett
        int noOfOdd; // Antal udda tal i arrayen
        int noOfEven; // Antal jämna tal i arrayen
        int max; // Att användas för sortering
        int temp; // Att användas för sortering

        // Tilldela variabelvärden
        noOfOdd = 0; // Sätt antalet udda till noll till att börja med
        noOfEven = 0; // Sätt antalet jämna till noll till att börja med
        max = 0; // Utgångspunkt för sortering
        temp = 0; // Utgångspunkt för sortering

        // Skapa slumptal och stoppa in arrayen
        for (int i = 0; i < noOfRandom; i++) {

            randomArray[i] = (int) (Math.random() * UPPERLIMIT) + 1;

        }
        System.out.printf("\n"); // Radbrytning

        // Printa de slumpande talen, samt avgör om de är udda eller jämna
        System.out.printf("Här är de slumpade talen:\n");

        for (int i = 0; i < noOfRandom; i++) {
            System.out.printf(randomArray[i] + " ");

            if ((randomArray[i] % 2) == 0) // Om jämt
            {
                noOfEven++; // Addera jämn-räknaren med ett
            } else // Annars, om udda
            {
                noOfOdd++; // Addera udda-räknaren med ett
            }
        }
        System.out.printf("\n\n"); // Radbrytningar

        // ### Sortering börjar ###

        // Multiplicera alla jämna tal med -1
        for (int i = 0; i < noOfRandom; i++) {
            if ((randomArray[i] % 2) == 0) // Om jämt
            {
                randomArray[i] = (-1) * randomArray[i];
            }
        }

        // Sortera minst till störst, alla jämna tal hamnar längst till vänster eftersom
        // de är negativa
        for (int i = 0; i < noOfRandom - 1; i++) {
            max = i;
            for (int k = i + 1; k < noOfRandom; k++) {
                if (randomArray[k] < randomArray[max]) {
                    max = k;
                }
            }
            temp = randomArray[i];
            randomArray[i] = randomArray[max];
            randomArray[max] = temp;
        }

        // Multiplicera alla negativa tal med -1
        for (int i = 0; i < noOfRandom; i++) {
            if ((randomArray[i] % 2) == 0) // Om mindre än 0
            {
                randomArray[i] = (-1) * randomArray[i];
            }
        }

        // Sortera från de jämna talen, index 0 till noOfEven, minst till störst
        for (int i = 0; i < noOfEven - 1; i++) {
            max = i;
            for (int k = i + 1; k < noOfEven; k++) {
                if (randomArray[k] < randomArray[max]) {
                    max = k;
                }
            }
            temp = randomArray[i];
            randomArray[i] = randomArray[max];
            randomArray[max] = temp;
        }

        // Sortera från den udda talen, index noOfEven till noOfRandom, störst till
        // minst
        for (int i = noOfEven; i < noOfRandom - 1; i++) {
            max = i;

            for (int k = i + 1; k < noOfRandom; k++) {
                if (randomArray[k] > randomArray[max]) {
                    max = k;
                }
            }

            temp = randomArray[i];
            randomArray[i] = randomArray[max];
            randomArray[max] = temp;
        }

        // Skriv ut alla tal, skriv ut ett - efter index noOfEven
        System.out.printf("Här är de slumpade talen ordnade: \n");

        for (int i = 0; i < noOfRandom; i++) {
            System.out.print(randomArray[i] + " ");
            if (i == noOfEven - 1) { // När man är klar med de jämna talen
                System.out.print("- ");
            }
        }

        System.out.printf("\n\n"); // Radbrytningar

        System.out.printf("Av ovanstående %s tal var %s jämna och %s udda", noOfRandom, noOfEven, noOfOdd);

        keyboard.close(); // Stänger inmatningen
    }
}