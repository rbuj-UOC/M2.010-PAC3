/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.propietaris;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class Propietari {

    private long id;
    private String nom;
    private String email;

    /**
     * La constructora.
     */
    public Propietari() {
    }

    /**
     * Construeix una instància a partir d'una cadena de text.
     * @param str la cadena de text que conté els valors.
     */
    public Propietari(String linia) {
        int start = 0;
        int end = linia.indexOf(';');
        setId(Long.parseLong(linia.substring(start, end)));
        start = end + 1;
        end = linia.indexOf(';', start);
        setNom(linia.substring(start, end).trim());
        start = end + 1;
        setEmail(linia.substring(start, linia.length()).trim());
    }

    /**
     * Construeix una instància a partir d'un identificador.
     * @param p_id l'identificador del propietari.
     */
    public Propietari(long p_id) {
        setId(p_id);
    }

    /**
     * Construeix una instància a partir dels valor d'una altra instància.
     * @param p Priopietari amb els valors a copiar a la nova instància.
     */
    public Propietari(Propietari p) {
        setId(p.Id());
        setNom(p.Nom());
        setEmail(p.Email());
    }

    /**
     * Construeix una instància a partir dels valors passats.
     * @param p_id Identificador del propietari.
     * @param p_nom Nom del propietari.
     * @param p_email Correu electrònic del propietari.
     */
    public Propietari(long p_id, String p_nom, String p_email) {
        setId(p_id);
        setNom(p_nom);
        setEmail(p_email);
    }

    /**
     * Mètode Getter.
     * @return Nom del propietari.
     */
    public String Nom() {
        return (nom);
    }

    /**
     * Mètode Getter.
     * @return Identificador del propietari.
     */
    public long Id() {
        return (id);
    }

    /**
     * Mètode Getter.
     * @return Correu electrònic del propietari.
     */
    public String Email() {
        return (email);
    }

    /**
     * Mètode Setter. Estableix el nom del propietari.
     * @param p_nom Nom del propietari.
     */
    public final void setNom(String p_nom) {
        nom = p_nom;
    }

    /**
     * Mètode Setter. Estableix l'identificador propietari.
     * @param p_id Identicador del propietari.
     */
    public final void setId(long p_id) {
        id = p_id;
    }

    /**
     * Mètode Setter. Estableix l'email del propietari.
     * @param p_email Email del propietari.
     */
    public final void setEmail(String p_email) {
        email = p_email;
    }

    /*
     * Altres
     */
    @Override
    public String toString() {
        return String.valueOf(id) + "; " + nom + "; " + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Propietari)) {
            return false;
        }
        Propietari p = (Propietari) o;
        return (p.Id() == id);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + (this.nom != null ? this.nom.hashCode() : 0);
        hash = 71 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

    public int compareTo(Propietari p) {
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
}
