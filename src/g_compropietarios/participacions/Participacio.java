/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.participacions;

import g_compropietarios.fraccio.Fraccio;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class Participacio {
    private long id;
    private Fraccio f_participacio;
    
    /**
     * La constructora.
     */
    public Participacio(){
        f_participacio = new Fraccio();
    }
    
    /**
     * Construeix una instància a partir d'una cadena de text.
     * @param str La cadena de text que conté els valors.
     */
    public Participacio(String str){
        int start = 0;
        int end = str.indexOf(':');
        setId(Long.parseLong(str.substring(start, end).trim()));
        start = end + 1;
        f_participacio = new Fraccio(str.substring(start, str.length()).trim());
    }
        
    /**
     * Construeix una instància a partir d'un identificador.
     * @param p_id L'identificador de la participació.
     */
    public Participacio(long p_id){
        setId(p_id);
    }

    /**
     * Construeix una instància a partir dels valors d'una altra instància.
     * @param p Participació amb els valors a copiar a la nova instància.
     */
    public Participacio(Participacio p){
        setId(p.Id());
        f_participacio = new Fraccio(p.F_participacio());
    }
    
    /**
     * Construeix una instància a partir dels valors passats.
     * @param p_id Identificador del propietari.
     * @param p_f_participacio Participació en la propietat.
     */
    public Participacio(long p_id, Fraccio p_f_participacio){
        setId(p_id);
        f_participacio = new Fraccio(p_f_participacio);
    }
        
    /**
     * Mètode Getter.
     * @return Identificador del propietari.
     */
    public long Id(){
        return id;
    }
    
    /**
     * Mètode Getter.
     * @return Participacio en la propietat.
     */
    public Fraccio F_participacio(){
        return f_participacio;
    }
    
    /**
     * Mètode Setter. Estableix l'identificador del propietari.
     * @param p_id Identicador del propietari.
     */
    public final void setId(long p_id){
        id = p_id;
    }
    
    /**
     * Mètode Setter. Estableix la participació en la propietat.
     * @param p_f_participacio Participació en la propietat.
     */
    public final void setF_participacio(Fraccio p_f_participacio){
        f_participacio.set(p_f_participacio);
    }
    
    /*
     * Altres
     */
    @Override
    public String toString(){
         return String.valueOf(id) + " : " + f_participacio.toString();
    }

    @Override
    public boolean equals(Object o){
         if (this == o) return true;
         if ( !(o instanceof Participacio) ) return false;
         Participacio p = (Participacio)o;
         return (p.Id() == id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + (this.f_participacio != null ? this.f_participacio.hashCode() : 0);
        return hash;
    }
    
    public int compareTo(Participacio p){
        final int MENOR = -1;
        final int IGUAL = 0;
        final int MAJOR = 1;
        if (p.Id()>id) return MAJOR;
        if (p.Id()==id) return IGUAL;
        if (p.Id()<id) return MENOR;
        return IGUAL;
    }
}
