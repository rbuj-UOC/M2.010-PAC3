/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.transmissions;

import g_compropietarios.data.Data;
import g_compropietarios.propietaris.LlistaPropietaris;
import java.util.ListIterator;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
abstract public class Transmissio {

    private long id;
    private Data dataTransmissio;

    /**
     * La constructora.
     */
    public Transmissio() {
        dataTransmissio = new Data();
    }

    /**
     * La constructora.
     */
    public Transmissio(long p_id, Data p_dataTransmissio) {
        setId(p_id);
        dataTransmissio = new Data(p_dataTransmissio);
    }

    /**
     * Construeix una instància a partir d'una cadena de text.
     * @param linia la cadena de text que conté els valors.
     */
    public Transmissio(String linia) {
        int start = 1;
        int end = linia.indexOf(';');
        setId(Long.parseLong(linia.substring(start, end).trim()));
        start = end + 1;
        end = linia.indexOf(';', start);
        dataTransmissio = new Data(linia.substring(start, end).trim());
    }

    /**
     * Mètode Getter.
     * @return L'identificador del propietari de la transmissió.
     */
    public long Id() {
        return id;
    }

    /**
     * Mètode Getter.
     * @return Motiu de la transmissió.
     */
    public String Motiu() {
        return this.getClass().getSimpleName();
    }

    /**
     * Mètode Getter.
     * @return Data de la transmissio.
     */
    public Data DataTransmissio() {
        return dataTransmissio;
    }

    /**
     * Mètode Setter. Estableix l'identificador de l'antic propietari.
     * @param p_id Identificador del propietari de la transmissió.
     */
    public final void setId(long p_id) {
        id = p_id;
    }

    /**
     * Mètode Setter. Estableix la data de la transmissió.
     * @param data Data de la transmissió.
     * @see Data
     */
    public final void setDataTransmissio(Data data) {
        dataTransmissio.set(data);
    }

    public int compareTo(Transmissio t) {
        final int MENOR = -1;
        final int IGUAL = 0;
        final int MAJOR = 1;
        if ((t.Id() == id) && (t.DataTransmissio().compareTo(dataTransmissio) == 0)) {
            return IGUAL;
        } else {
            if (t.Id() > id) {
                return MAJOR;
            } else {
                if (t.Id() < id) {
                    return MENOR;
                } else {
                    if (t.DataTransmissio().compareTo(dataTransmissio) > 0) {
                        return MAJOR;
                    } else {
                        return MENOR;
                    }
                }
            }
        }
    }

    @Override
    abstract public String toString();

    abstract public int addItem(String str);
    abstract public void addItem(int idx, String str);
    abstract public ListIterator listIterator();
    abstract public String informe(LlistaPropietaris llistaPropietaris);
}
