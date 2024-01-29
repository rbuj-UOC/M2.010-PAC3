/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.fraccio;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class Fraccio {

    private long numerador;
    private long denominador;

    /**
     * Constructora.
     */
    public Fraccio() {
    }

    /**
     * Constructora que inicialitza una fracció segons l'argument.
     * @param fraccio Valors a inicialitzar la nova instància.
     */
    public Fraccio(Fraccio fraccio) {
        setNumerador(fraccio.Numerador());
        setDenominador(fraccio.Denominador());
    }

    /**
     * Constructora que inicialitza una fracció segons els arguments passats.
     * @param num Numerador de la fracció.
     * @param den Denominador de la fracció.
     */
    public Fraccio(long num, long den) {
        setNumerador(num);
        setDenominador(den);
    }

    /**
     * Constructora que inicialitza una fracció amb una cadena de text.
     * @param str Cadena de text que conté la fracció.
     */
    public Fraccio(String str) {
        int start = 0;
        int end = str.indexOf('/');
        if (end == -1) {
            if (str.trim().compareTo("1") == 0) {
                setNumerador(1);
                setDenominador(1);
            } else {
                System.out.println("Error: format incorrecte de fraccio");
            }
        } else {
            setNumerador(Long.parseLong(str.substring(start, end)));
            start = end + 1;
            setDenominador(Long.parseLong(str.substring(start, str.length()).trim()));
        }
    }

    /**
     * Mètode Getter.
     * @return Numerador de la fracció.
     */
    public long Numerador() {
        return numerador;
    }

    /**
     * Mètode Getter.
     * @return Denominador de la fracció.
     */
    public long Denominador() {
        return denominador;
    }

    /**
     * Estableix el valor de la fracció segons els paràmetres passats.
     * @param fraccio Fracció amb els valors a assignar.
     */
    public void set(Fraccio fraccio) {
        setNumerador(fraccio.Numerador());
        setDenominador(fraccio.Denominador());
    }

    /**
     * Mètode Setter. Estableix l'identificador de la propietat.
     * @param p_id Identificador de la propietat.
     */
    public final void setNumerador(long p_numerador) {
        numerador = p_numerador;
    }

    /**
     * Mètode Setter. Estableix el nom de la de la propietat.
     * @param p_nom Nom de la propietat.
     */
    public final void setDenominador(long p_denominador) {
        denominador = p_denominador;
    }

    /**
     * Suma a la fracció una altra fracció.
     * @param fraccio Fracció amb els valors a afegir.
     */
    public void add(Fraccio fraccio) {
        Fraccio aux = new Fraccio(this);
        numerador = (aux.Numerador() * fraccio.Denominador()) + (fraccio.Numerador() * aux.Denominador());
        denominador = aux.Denominador() * fraccio.Denominador();
        simplifica();
    }

    /**
     * Multiplica la fracció amb una altra fracció.
     * @param fraccio Fracció amb els valors a multiplicar.
     */
    public void mul(Fraccio fraccio) {
        numerador = numerador * fraccio.Numerador();
        denominador = denominador * fraccio.Denominador();
        simplifica();
    }

    /**
     * Divideix la fracció amb una altra fracció.
     * @param fraccio Fracció amb els valors a dividir.
     */
    public void div(Fraccio fraccio) {
        numerador = numerador * fraccio.Denominador();
        denominador = denominador * fraccio.Numerador();
        simplifica();
    }

    /**
     * Simplifica una fracció utilitzant els nombres primers més petits de 100.
     */
    public void simplifica() {
        long[] factors = {2, 3, 5, 7, 11, 13, 17, 19, 23, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89};
        int i;
        if (numerador != denominador) {
            for (i = 0; i < factors.length; i++) {
                while (((numerador % factors[i]) == 0)
                        && ((denominador % factors[i]) == 0)) {
                    numerador = numerador / factors[i];
                    denominador = denominador / factors[i];
                }
            }
        } else {
            numerador = 1;
            denominador = 1;
        }
    }

    /**
     * La fracció representa a la unitat?
     * @return cert en cas que contingui la unitat, altrament fals.
     */
    public boolean unitat() {
        return (numerador == denominador);
    }

    @Override
    public String toString() {
        if (numerador != denominador) {
            return (String.valueOf(numerador) + "/" + String.valueOf(denominador));
        } else {
            return ("1");
        }
    }
}
