import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Eine pädagogische Swing-Anwendung zum Begreifen von Gleitkommazahlen. Die
 * UI-Klasse kümmert sich um das Erzeugen der Komponenten und um das Layout.
 * 
 * @author Fredrik Winkler
 * @version 10. Juni 2015
 */
public class GleitkommaUI
{
    private static final int EINGABE_BREITE = 40;
    private static final float SCHRIFT_GROESSE = 24f;
    private static final String FENSTER_TITEL = "Was Sie schon immer über Gleitkommazahlen wissen wollten...";

    private JFrame _frame;

    private JTextField _eingabe;

    private JPanel _buttonPanel;
    private JButton _vorgaengerButton;
    private JButton _konvertiereButton;
    private JButton _nachfolgerButton;

    private JTextField _binaereAnzeige;
    private JTextArea _dezimaleAnzeige;

    /**
     * Erzeugt die Komponenten und ordnet sie an.
     */
    public GleitkommaUI()
    {
        initFrame();
        initEingabe();
        initButtons();
        initAnzeige();
        zeigeGUIan();
    }

    private void initFrame()
    {
        _frame = new JFrame(FENSTER_TITEL);
        _frame.setLayout(new BoxLayout(_frame.getContentPane(),
                BoxLayout.Y_AXIS));
    }

    private void initEingabe()
    {
        _eingabe = new JTextField(EINGABE_BREITE);
        verwendeGrosseSchriftIn(_eingabe);

        _frame.add(_eingabe);
    }

    private void initButtons()
    {
        _buttonPanel = new JPanel();

        initVorgaengerButton();
        initKonvertiereButton();
        initNachfolgerButton();

        _frame.add(_buttonPanel);
    }

    private void initVorgaengerButton()
    {
        _vorgaengerButton = new JButton("-");
        _buttonPanel.add(_vorgaengerButton);
    }

    private void initKonvertiereButton()
    {
        _konvertiereButton = new JButton("V");
        _buttonPanel.add(_konvertiereButton);
    }

    private void initNachfolgerButton()
    {
        _nachfolgerButton = new JButton("+");
        _buttonPanel.add(_nachfolgerButton);
    }

    private void initAnzeige()
    {
        initBinaereAnzeige();
        initDezimaleAnzeige();
    }

    private void initBinaereAnzeige()
    {
        _binaereAnzeige = new JTextField();
        verwendeGrosseSchriftIn(_binaereAnzeige);
        _binaereAnzeige.setEditable(false);

        _frame.add(_binaereAnzeige);
    }

    private void initDezimaleAnzeige()
    {
        _dezimaleAnzeige = new JTextArea();
        verwendeGrosseSchriftIn(_dezimaleAnzeige);
        _dezimaleAnzeige.setEditable(false);
        _dezimaleAnzeige.setLineWrap(true);

        _frame.add(_dezimaleAnzeige);
    }

    private void zeigeGUIan()
    {
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.pack();
        _frame.setVisible(true);
    }

    private static void verwendeGrosseSchriftIn(JComponent component)
    {
        Font normal = component.getFont();
        Font gross = normal.deriveFont(SCHRIFT_GROESSE);
        component.setFont(gross);
    }

    public JFrame getFrame()
    {
        return _frame;
    }

    public JTextField getEingabe()
    {
        return _eingabe;
    }

    public JButton getVorgaengerButton()
    {
        return _vorgaengerButton;
    }

    public JButton getKonvertiereButton()
    {
        return _konvertiereButton;
    }

    public JButton getNachfolgerButton()
    {
        return _nachfolgerButton;
    }

    public JTextField getBinaereAnzeige()
    {
        return _binaereAnzeige;
    }

    public JTextArea getDezimaleAnzeige()
    {
        return _dezimaleAnzeige;
    }
}
