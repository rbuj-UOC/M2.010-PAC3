/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.data;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class Data {

    private short dia;
    private short mes;
    private short any;

    public Data() {
    }

    public Data(Data d) {
        setDia(d.Dia());
        setMes(d.Mes());
        setAny(d.Any());
    }

    public Data(String str) {
        int start = 0;
        int end = str.indexOf('-');
        setDia(Short.parseShort(str.substring(start, end)));
        start = end + 1;
        end = str.indexOf('-', start);
        setMes(Short.parseShort(str.substring(start, end).trim()));
        start = end + 1;
        end = str.length();
        setAny(Short.parseShort(str.substring(start, end).trim()));
    }

    public Data(short p_dia, short p_mes, short p_any) {
        setDia(p_dia);
        setMes(p_mes);
        setAny(p_any);
    }

    /**
     * Mètode Getter.
     * @return Dia de la data.
     */
    public short Dia() {
        return dia;
    }

    /**
     * Mètode Getter.
     * @return Mes de la data.
     */
    public short Mes() {
        return mes;
    }

    /**
     * Mètode Getter.
     * @return Any de la data.
     */
    public short Any() {
        return any;
    }

    /**
     * Mètode Setter. Estableix el dia de la data.
     * @param p_dia Dia de la data.
     */
    public final void setDia(short p_dia) {
        dia = p_dia;
    }

    /**
     * Mètode Setter. Estableix el mes de la data.
     * @param p_mes Mes de la data.
     */
    public final void setMes(short p_mes) {
        mes = p_mes;
    }

    /**
     * Mètode Setter. Estableix la data a partir d'una altra data.
     * @param data Data amb els valors.
     */
    public final void set(Data data) {
        setDia(data.Dia());
        setMes(data.Mes());
        setAny(data.Any());
    }

    /**
     * Mètode Setter. Estableix l'any de la data.
     * @param p_any Any de la data.
     */
    public final void setAny(short p_any) {
        any = p_any;
    }

    /*
     * Altres
     */
    @Override
    public String toString() {
        return String.format("%02d",dia)
                + "-" + String.format("%02d",mes) + "-" + String.valueOf(any);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Data)) {
            return false;
        }
        Data d = (Data) o;
        return ((d.Dia() == dia) && (d.Mes() == mes) && (d.Any() == any));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.dia;
        hash = 71 * hash + this.mes;
        hash = 71 * hash + this.any;
        return hash;
    }

    public int compareTo(Data d) {
        final int MENOR = -1;
        final int IGUAL = 0;
        final int MAJOR = 1;
        if ((d.Dia() == dia) && (d.Mes() == mes) && (d.Any() == any)) {
            return IGUAL;
        } else {
            if (d.Any() < any) {
                return MENOR;
            } else {
                if (d.Any() > any) {
                    return MAJOR;
                } else {
                    if (d.Mes() < mes) {
                        return MENOR;
                    } else {
                        if (d.Mes() > mes) {
                            return MAJOR;
                        } else {
                            if (d.Dia() < dia) {
                                return MENOR;
                            } else {
                                if (d.Dia() > dia) {
                                    return MAJOR;
                                }
                            }
                        }
                    }
                }
            }
        }
        return IGUAL;
    }
}
