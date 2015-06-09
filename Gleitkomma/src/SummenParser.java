/**
 * Hilfsklasse zum Parsen von Summen in Textform.
 * 
 * @author Fredrik Winkler
 * @version 10. Juni 2015
 */
public class SummenParser
{
    /**
     * Rechnet eine einfache Summenformel aus. Beispiel: parse("1+2+3") -> 6.
     * 
     * @throws NumberFormatException
     *             bei ungültiger Eingabe
     */
    public static double parse(String formel)
    {
        String[] summanden = summanden(formel);
        return addiere(summanden);
    }

    /**
     * Liefert die Summanden einer Formel, die durch '+' getrennt sind.
     */
    private static String[] summanden(String formel)
    {
        return formel.replaceAll(",", ".").split(AM_PLUS_ZEICHEN);
    }

    private static final String AM_PLUS_ZEICHEN = "\\s*\\+\\s*";

    /**
     * Addiert die Zahlen, die in Textform im Array abgelegt sind.
     */
    private static double addiere(String[] zahlen)
    {
        double summe = 0.0;
        for (String zahl : zahlen)
        {
            summe += Double.parseDouble(zahl);
        }
        return summe;
    }
}
