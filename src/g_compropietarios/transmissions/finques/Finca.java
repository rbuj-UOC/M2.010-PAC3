/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.transmissions.finques;

import g_compropietarios.data.Data;
import g_compropietarios.participacions.LlistaParticipacions;
import g_compropietarios.participacions.Participacio;
import g_compropietarios.propietaris.LlistaPropietaris;
import java.util.ListIterator;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class Finca {

    private long id;
    private LlistaParticipacions llistaParticipacions;

    /**
     * La constructora.
     */
    public Finca() {
        llistaParticipacions = new LlistaParticipacions();
    }

    /**
     * Construeix una instància a partir d'una cadena de text.
     * @param str la cadena de text que conté els valors.
     */
    public Finca(String str) {
        int start = 0;
        int end = str.indexOf(';');
        setId(Long.parseLong(str.substring(start, end)));
        start = end + 1;
        end = str.length();
        llistaParticipacions = new LlistaParticipacions(str.substring(start, end).trim());
    }

    /**
     * Construeix una instància a partir d'un identificador.
     * @param p_id l'identificador de la propietat.
     */
    public Finca(long p_id) {
        setId(p_id);
    }

    /**
     * Construeix una instància a partir dels valors d'una altra instància.
     * @param f Finca amb els valors a copiar a la nova instància.
     */
    public Finca(Finca f) {
        setId(f.Id());
    }

    /**
     * Construeix una instància a partir dels valors passats.
     * @param p_id Identificador de la propietat.
     * @param p_llistaParticipacions Llista de participacions.
     */
    public Finca(long p_id, LlistaParticipacions p_llistaParticipacions) {
        setId(p_id);
        llistaParticipacions = new LlistaParticipacions(p_llistaParticipacions);
    }

    /**
     * Mètode Getter.
     * @return Identificador de la propietat.
     */
    public long Id() {
        return id;
    }

    /**
     * Mètode Setter. Estableix l'identificador de la propietat.
     * @param p_id Identificador de la propietat.
     */
    public final void setId(long p_id) {
        id = p_id;
    }

    /*
     * Altres
     */
    @Override
    public String toString() {
        return String.valueOf(id) + " " + llistaParticipacions.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Finca)) {
            return false;
        }
        Finca f = (Finca) o;
        return (f.Id() == id);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + (this.llistaParticipacions != null ? this.llistaParticipacions.hashCode() : 0);
        return hash;
    }

    public int compareTo(Finca f) {
        final int MENOR = -1;
        final int IGUAL = 0;
        final int MAJOR = 1;
        if (f.Id() > id) {
            return MAJOR;
        }
        if (f.Id() == id) {
            return IGUAL;
        }
        if (f.Id() < id) {
            return MENOR;
        }
        return IGUAL;
    }

    /**
     * Obtenir un iterador per a la llista.
     * @return Iterador de la llista.
     */
    public ListIterator<Participacio> listIterator() {
        return llistaParticipacions.listIterator();
    }

    public String informe(long idPropietat, long idAnticPropietari, 
            String strDataTransmissio, LlistaPropietaris llistaPropietaris, 
            String strTipus, String strDadesAdicionals) {
        return llistaParticipacions.informe(idPropietat, idAnticPropietari, 
                strDataTransmissio, llistaPropietaris, strTipus, strDadesAdicionals);
    }
}
