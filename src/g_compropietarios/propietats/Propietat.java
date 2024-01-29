/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.propietats;

import g_compropietarios.fraccio.Fraccio;
import g_compropietarios.participacions.LlistaParticipacions;
import g_compropietarios.participacions.Participacio;
import java.util.ListIterator;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class Propietat {

    private long id;
    private String nom;
    private Fraccio participacio;
    private LlistaParticipacions llistaParticipacions;

    /**
     * La constructora.
     */
    public Propietat() {
        participacio = new Fraccio();
        llistaParticipacions = new LlistaParticipacions();
    }

    /**
     * Construeix una instància a partir d'una cadena de text.
     * @param linia la cadena de text que conté els valors.
     */
    public Propietat(String linia) {
        int start = 0;
        int end = linia.indexOf(';');
        setId(Long.parseLong(linia.substring(start, end)));
        start = end + 1;
        end = linia.indexOf(';', start);
        setNom(linia.substring(start, end).trim());
        start = end + 1;
        end = linia.indexOf(';', start);
        participacio = new Fraccio(linia.substring(start, end).trim());
        start = end + 1;
        llistaParticipacions = new LlistaParticipacions(linia.substring(start, linia.length()).trim());
    }

    /**
     * Construeix una instància a partir d'un identificador.
     * @param p_id l'identificador de la propietat.
     */
    public Propietat(long p_id) {
        setId(p_id);
    }

    /**
     * Construeix una instància a partir dels valors d'una altra instància.
     * @param p Propietat amb els valors a copiar a la nova instància.
     */
    public Propietat(Propietat p) {
        setId(p.Id());
        setNom(p.Nom());
        participacio = new Fraccio(p.Participacio());
    }

    /**
     * Construeix una instància a partir dels valors passats.
     * @param p_id Identificador de la propietat.
     * @param p_nom Nom de la propietat.
     * @param p_participacio Participació general de la propietat en la comunitat.
     */
    public Propietat(long p_id, String p_nom, Fraccio p_participacio) {
        setId(p_id);
        setNom(p_nom);
        participacio = new Fraccio(p_participacio);
    }

    /**
     * Mètode Getter.
     * @return Identificador de la propietat.
     */
    public long Id() {
        return id;
    }

    /**
     * Mètode Getter.
     * @return Nom de la propietat.
     */
    public String Nom() {
        return nom;
    }

    /**
     * Mètode Getter.
     * @return Participació general de la propietat en la comunitat.
     */
    public Fraccio Participacio() {
        return participacio;
    }

    /**
     * Mètode Setter. Estableix l'identificador de la propietat.
     * @param p_id Identificador de la propietat.
     */
    public final void setId(long p_id) {
        id = p_id;
    }

    /**
     * Mètode Setter. Estableix el nom de la de la propietat.
     * @param p_nom Nom de la propietat.
     */
    public final void setNom(String p_nom) {
        nom = p_nom;
    }

    /**
     * Mètode Setter. Estableix la participació de la de la propietat.
     * @param p_participacio Participació general de la propietat en la comunitat.
     */
    public final void setParticipacio(Fraccio p_participacio) {
        participacio.set(p_participacio);
    }

    public Fraccio Verifica() {
        if (!(llistaParticipacions.suma().unitat())) {
            System.out.println("Error: la suma de les participacions de la propietat " + String.valueOf(Id()) + " no és la unitat");
        }
        return Participacio();
    }

    /**
     * Obtenir un iterador per a la llista.
     * @return Iterador de la llista.
     */
    public ListIterator<Participacio> listIterator() {
        return llistaParticipacions.listIterator();
    }

    /**
     * Elimina una paricipació de la llista de participacions.
     * @param Id Identificador del propietari.
     */
    public void eliminarParticipacio(long Id) {
        llistaParticipacions.del(Id);
    }

    /**
     * Afegeix una paricipació a la llista de participacions.
     * @param participacio Participació a afegir a la llista.
     */
    public void afegirParticipacio(Participacio participacio) {
        llistaParticipacions.add(participacio);
    }

    /**
     * Comprova si existeix l'identificador d'un propietari en la llista de
     * participacions.
     * @return cert si la existeix el propietari, altrament fals.
     */
    public boolean existeixPropietari(long Id) {
        return llistaParticipacions.existeixPropietari(Id);
    }

    /*
     * Altres
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Propietat)) {
            return false;
        }
        Propietat p = (Propietat) o;
        return (p.Id() == id);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 41 * hash + (this.nom != null ? this.nom.hashCode() : 0);
        hash = 41 * hash + (this.participacio != null ? this.participacio.hashCode() : 0);
        return hash;
    }

    public int compareTo(Propietat p) {
        final int MENOR = -1;
        final int IGUAL = 0;
        final int MAJOR = 1;
        if (p.Id() > id) {
            return MAJOR;
        }
        if (p.Id() == id) {
            return IGUAL;
        }
        if (p.Id() < id) {
            return MENOR;
        }
        return IGUAL;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + "; " + nom + "; " + participacio.toString() + "; " + llistaParticipacions.toString();
    }

    void incrementaParticipacio(Participacio pNouPropietari) {
        ListIterator<Participacio> it = llistaParticipacions.listIterator();
        boolean trobat = false;
        while (it.hasNext() && trobat == false) {
            Participacio p = it.next();
            if (p.compareTo(pNouPropietari) == 0) {
                p.F_participacio().add(pNouPropietari.F_participacio());
                trobat = true;
            }
        }
    }
}
