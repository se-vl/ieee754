import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Eine pädagogische Swing-Anwendung zum Begreifen von Gleitkommazahlen. Die
 * Werkzeugklasse kümmert sich primär um die Verdrahtung der Listener.
 * 
 * @author Fredrik Winkler
 * @version 10. Juni 2015
 */
public class GleitkommaWerkzeug
{
    private GleitkommaUI _ui;

    private IEEE754Double _wert = IEEE754Double.POSITIVE_ZERO;

    /**
     * Initialisiert das GleitkommaWerkzeug.
     */
    public GleitkommaWerkzeug()
    {
        _ui = new GleitkommaUI();
        registriereListener();
    }

    private void registriereListener()
    {
        _ui.getKonvertiereButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                verarbeiteEingabe();
            }
        });
        _ui.getVorgaengerButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setzeVorgaenger();
            }
        });
        _ui.getNachfolgerButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setzeNachfolger();
            }
        });
    }

    private void verarbeiteEingabe()
    {
        try
        {
            String eingabe = _ui.getEingabe().getText();
            double ergebnis = SummenParser.parse(eingabe);
            _wert = IEEE754Double.valueOf(ergebnis);
        }
        catch (NumberFormatException ex)
        {
            _wert = IEEE754Double.POSITIVE_ZERO;
        }
        aktualisiereAnzeige();
    }

    private void aktualisiereAnzeige()
    {
        aktualisiereBinaereAnzeige();
        aktualisiereDezimaleAnzeige();

        _ui.getFrame().pack();
        _ui.getEingabe().requestFocusInWindow();
    }

    private void aktualisiereBinaereAnzeige()
    {
        _ui.getBinaereAnzeige().setText(_wert.internalFormat());
    }

    private void aktualisiereDezimaleAnzeige()
    {
        _ui.getDezimaleAnzeige().setText(_wert.decimalValue());
    }

    private void setzeVorgaenger()
    {
        _wert = _wert.predecessor();
        aktualisiereAnzeige();
    }

    private void setzeNachfolger()
    {
        _wert = _wert.successor();
        aktualisiereAnzeige();
    }
}
