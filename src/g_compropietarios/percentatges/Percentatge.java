/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.percentatges;

import g_compropietarios.fraccio.Fraccio;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class Percentatge {

    private long enter;
    private long decimal;

    public Percentatge() {
    }

    public Percentatge(Fraccio fraccio) {
        enter = (100 * fraccio.Numerador()) / fraccio.Denominador();
        decimal = ((10000 * fraccio.Numerador()) / fraccio.Denominador()) - (enter * 100);
    }

    public Percentatge(Percentatge p) {
        enter = p.Enter();
        decimal = p.Decimal();
    }

    public long Enter() {
        return enter;
    }

    public long Decimal() {
        return decimal;
    }

    public void setEnter(long p_enter) {
        enter = p_enter;
    }

    public void setDecimal(long p_decimal) {
        decimal = p_decimal;
    }

    public void add(Percentatge p) {
        decimal = decimal + p.Decimal();
        if (decimal > 100) {
            decimal = decimal - 100;
            enter = enter + 1;
        }
        enter = enter + p.Enter();
    }

    @Override
    public String toString() {
        return (String.valueOf(enter) + "," + String.valueOf(decimal) + "%");
    }
}
