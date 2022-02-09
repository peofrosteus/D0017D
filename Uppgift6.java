import java.util.Scanner; // För inmatningar
import java.util.Date; // För datumfunktioner

public class Uppgift6 {

    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        // Deklarera variabler
        int userSelection; // Användarens val från menyn
        int[][] articles = new int[10][3]; // Matrisen för artiklar
        int articleNumber; // Akrikelnummer
        int[][] sales = new int[10][3]; // Matrisen för försäljning
        Date[] salesDate = new Date[10]; // Transaktionsdatum

        // Tilldela värden på variabler
        articleNumber = 1000; // Första artikelnumret

        while (true) {
            userSelection = menu(); // Hämta användarens inmatning från menyn

            switch (userSelection) // Hantera meny-inmatningen
            {

                case 1:
                    articles = checkFull(articles); // Kolla om matrisen är full till mer än hälften
                    articleNumber = insertArticle(articles, articleNumber);
                    break;

                case 2:
                    removeArticle(articles);
                    break;

                case 3:
                    printArticles(articles);
                    break;

                case 4:
                    sellArticle(sales, salesDate, articles);
                    break;

                case 5:
                    printSales(sales, salesDate);
                    break;

                case 6:
                    System.out.printf("Avslutar...");
                    System.exit(0);
                    break;

                default:
                    System.out.printf("Felaktig inmatning\n");
                    break;

            }
        }
    }

    // Presenterar menyn, läser in och returnerar användarens val.
    public static int menu() {

        int userSelection; // Skapa input att returnera från menyn

        // Skriver ut menyn
        System.out.printf("1. Lägg in artiklar \r\n" + "2. Ta bort artikel\r\n" + "3. Visa artiklar\r\n"
                + "4. Försäljning\r\n" + "5. Orderhistorik\r\n" + "6. Avsluta\r\n" + "Ditt val: ");
        userSelection = input(); // Hämta inmatning
        return userSelection; // Returnera inmatning

    }

    // Väntar på inmatning av ett heltal och returnerar det heltal som användaren
    // matat in.
    public static int input() {
        int num; // skapa variabel att returnera
        while (true) {
            if (userInput.hasNextInt()) // On nästa inmatningen är en int, returnera en int
            {
                num = userInput.nextInt(); // Tilldela värdet nästa int
                return Math.abs(num); // Ta absolutbeloppet ifall användaren angett ett negativt tal
            } else {
                return 0;
            }
        }

    }

    public static int[][] checkFull(int[][] articles) {
        int lastIndex = 0; // Index för den första tomma raden, sätt till noll initalt
        int i = 0; // Skapa variabel för iteration

        while (i < articles.length) {
            if (articles[i][0] == 0) {
                lastIndex = i; // i är den första tomma raden, sätt det värdet på lastIndex
                break; // Gå ur while-loopen
            }
            i++;
        }

        // Kolla om matrisen för artiklar är fylld till mer än hälften
        if (lastIndex > articles.length / 2) {

            int[][] newarticles = new int[articles.length * 2][articles[0].length]; // Skapa en ny matris med dubbelt så
                                                                                    // många rader och samma antal
                                                                                    // kolumner

            // Kopiera in den gamla matrisen in i den nya
            for (i = 0; i < articles.length; i++) // Loopa igenom alla rader
            {
                for (int j = 0; j < articles[i].length; j++) // Loopa igenom alla kolumner
                {
                    newarticles[i][j] = articles[i][j];
                }
            }
            return newarticles; // Returnerar den nya matrisen
        } else // Om matrisen inte är fylld till mer än hälften
        {
            return articles; // Returnerar den gamla matrisen
        }
    }

    // Lägger till ett valbart antal artiklar i matrisen
    public static int insertArticle(int[][] articles, int articleNumber) {
        int noOfArticles; // Antal artikelnummer att lägga till
        int artStock; // Antal artiklar av varje nummer att lägga till
        int artPrice; // Priset för artikel

        System.out.printf("Hur många artiklar vill du lägga till? \n > ");
        noOfArticles = input(); // Hämta input

        for (int i = 0; i <= noOfArticles - 1; i++) {
            // Slumpa fram antal artiklar
            artStock = (int) (Math.random() * 10) + 1;

            // Slumpa fram artikelpris
            artPrice = (int) (Math.random() * 1000) + 1;

            // Loopa igenom matisen och leta efter en ledig position
            int j = 0;
            while (j < articles.length) {
                // Sätt in i matrisen, om det är en ledig position
                if (articles[j][0] == 0) {
                    articles[j][0] = articleNumber;
                    articles[j][1] = artStock;
                    articles[j][2] = artPrice;

                    break; // Gå ur loopen när ledig position hittats
                }
                j++;
            }
            // Addera 1 till arikelnumret
            articleNumber++;
        }

        return articleNumber;
    }

    public static void removeArticle(int[][] articles) {
        int artToRemove; // Artiklenumret som skall tas bort

        System.out.printf("Vilket artikelnummer vill du ta bort?\n> ");
        artToRemove = input(); // Hämta input

        // Loopa igenom alla rader
        for (int i = 0; i < articles.length; i++) {
            // Kolla om första kolumenen på rad i är artToRemove
            if (articles[i][0] == artToRemove) {
                // Sätt allt på den raden till noll
                articles[i][0] = 0;
                articles[i][1] = 0;
                articles[i][2] = 0;

                System.out.printf("Artikelnnummer %s har tagits bort\n", artToRemove); // Utskrift om bekräftelse

            }
        }

    }

    // Skriver ut alla artiklar med artikelnummer, antal och pris.
    public static void printArticles(int[][] articles) {
        System.out.print("Artiklar\nArt.nr. \tAntal \t \tPris\n"); // Header

        for (int i = 0; i < articles.length; i++) // Loopa igenom alla rader
        {
            for (int j = 0; j < articles[i].length; j++) // Loopa igenom alla kolumner
            {
                System.out.print(articles[i][j] + "\t\t"); // Skriv ut varje element tab-separerat

            }
            System.out.print("\n"); // radbryting
        }
    }

    public static void sellArticle(int[][] sales, Date[] salesDate, int[][] articles) {
        int artNum; // Artikeln som skall säljas
        int noOfArt; // Antal som skall säljas
        int artPrice; // Priset för varje artikel
        Date now = new Date(); // Försäljningsdatum

        System.out.printf("Vilket artikelnummer vill du sälja?\n> ");
        artNum = input();
        System.out.printf("Hur många vill du sälja?\n> ");
        noOfArt = input();

        // Kolla först att det finns ett sådant artiklenummer
        // Loopa igenom alla rader
        int i = 0;
        while (i < articles.length) {
            // Kolla om första kolumenen på rad i är samma artikelnummer som skall säljas
            if (articles[i][0] == artNum) {
                // Kolla om det finns tillräckligt antal att sälja
                if (articles[i][1] >= noOfArt) {
                    articles[i][1] = articles[i][1] - noOfArt; // Minska antalet artiklar med antalet som skall säljas
                    artPrice = articles[i][2]; // Priset på artiklen

                    int j = 0;
                    while (j < sales.length) {
                        // Sätt in i matrisen, om det är en ledig position
                        if (sales[j][0] == 0) {
                            sales[j][0] = artNum; // Stoppa in artikelnummer i säljmatrisen
                            sales[j][1] = noOfArt; // Stoppa in antalet sålda artiklar i säljmatrisen
                            sales[j][2] = artPrice * noOfArt;

                            salesDate[j] = now; // Säljdatum i säljmatrisen
                            break; // Gå ur loopen när ledig position hittats
                        }
                        j++;
                    }
                    break; // Gå ur while-loopen
                }
            }
            i++;
        }
    }

    // Metod som skriver ut sålda artiklar med datum, artikelnummer, antal och
    // summa.
    public static void printSales(int[][] sales, Date[] salesDate) {
        System.out.printf("Datum\t\t\t\tArt.nr\t\tAntal\t\tSumma\n"); // Skriver ut en header

        // Loopa igenom alla rader i sales
        for (int i = 0; i <= sales.length - 1; i++) {

            // Skriver ut datum, artikelnummer, antal och summa.
            System.out.printf("%0$tF %1$tT \t\t %s \t\t %s \t\t %s\n", salesDate[i], sales[i][0], sales[i][1],
                    sales[i][2]);

        }
    }
}