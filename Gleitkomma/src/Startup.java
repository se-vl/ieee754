import javax.swing.SwingUtilities;

/**
 * Eine pädagogische Swing-Anwendung zum Begreifen von Gleitkommazahlen. In
 * dieser Klasse wird die Anwendung gestartet.
 * 
 * @author Fredrik Winkler
 * @version 10. Juni 2015
 */
public class Startup
{
    public static void main(String[] args)
    {
        // Swing-GUIs müssen im "Event-Dispatch-Thread" zusammengebaut werden: 
        // https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
        SwingUtilities.invokeLater(new RequestToBuildGUI());
    }

    private static class RequestToBuildGUI implements Runnable
    {
        @Override
        public void run()
        {
            new GleitkommaWerkzeug();
        }
    }
}
