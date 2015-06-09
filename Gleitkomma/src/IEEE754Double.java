import java.math.BigDecimal;

/**
 * Eine Wrapperklasse um einen double, welche das Wandern durch den Wertebereich
 * von double erleichtert. Außerdem kann man sich die interne Repräsentation als
 * anschaulichen String geben lassen.
 * 
 * @author Fredrik Winkler
 * @version 10. Juni 2015
 */
public class IEEE754Double
{
    public static final IEEE754Double POSITIVE_ZERO = valueOf(0);
    public static final IEEE754Double NEGATIVE_ZERO = valueOf(Long.MIN_VALUE);

    private final long _bits;

    private IEEE754Double(long bits)
    {
        _bits = bits;
    }

    /**
     * Liefert einen Wrapper für den double, der durch die übergebenen Bits
     * repräsentiert wird.
     */
    public static IEEE754Double valueOf(long bits)
    {
        return new IEEE754Double(bits);
    }

    /**
     * Liefert einen Wrapper für den übergebenen double.
     */
    public static IEEE754Double valueOf(double value)
    {
        return valueOf(Double.doubleToLongBits(value));
    }

    /**
     * Liefert den eingepackten double.
     */
    public double toDouble()
    {
        return Double.longBitsToDouble(_bits);
    }

    /**
     * Liefert die nächstkleinere darstellbare Zahl.
     */
    public IEEE754Double predecessor()
    {
        if (equals(POSITIVE_ZERO))
        {
            return NEGATIVE_ZERO;
        }
        else
        {
            return valueOf(_bits - signum(_bits));
        }
    }

    /**
     * Liefert die nächstgrößere darstellbare Zahl.
     */
    public IEEE754Double successor()
    {
        if (equals(NEGATIVE_ZERO))
        {
            return POSITIVE_ZERO;
        }
        else
        {
            return valueOf(_bits + signum(_bits));
        }
    }

    /**
     * Liefert -1 für negative Zahlen (x <= -0.0) und +1 für nicht-negative Zahlen (x >= +0.0).
     */
    private static long signum(long x)
    {
        return (x >> 62) | 1;
    }

    public String internalFormat()
    {
        String sign = (_bits < 0) ? "-" : "";
        int character = (int) (_bits >> 52) & 0x7ff;
        char beforePoint = '1';
        int exponent = character - 1023;
        if (character == 0)
        {
            beforePoint = '0';
            ++exponent;
        }
        String mantissa = Long.toBinaryString(_bits & 0xfffffffffffffL);
        String leadingZeros = ZEROS52.substring(mantissa.length());
        return String.format("%s2^%d * %c.%s%s", sign, exponent, beforePoint,
                leadingZeros, mantissa);
    }

    private static final String ZEROS52 = "0000000000000000000000000000000000000000000000000000";

    public String decimalValue()
    {
        return new BigDecimal(toDouble()).toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        return (obj instanceof IEEE754Double) && equals((IEEE754Double) obj);
    }

    private boolean equals(IEEE754Double that)
    {
        return this._bits == that._bits;
    }

    @Override
    public int hashCode()
    {
        return (int) (_bits ^ (_bits >>> 32));
    }
}
