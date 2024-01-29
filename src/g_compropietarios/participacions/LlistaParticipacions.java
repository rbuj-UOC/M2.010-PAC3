/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.participacions;

import g_compropietarios.fraccio.Fraccio;
import g_compropietarios.propietaris.LlistaPropietaris;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class LlistaParticipacions {

    private LinkedList<Participacio> llista;

    /**
     * Constructora.
     */
    public LlistaParticipacions() {
        llista = new LinkedList<Participacio>();
    }

    /**
     * Constructora.
     * @param str cadena de text amb les participacions.
     */
    public LlistaParticipacions(String str) {
        llista = new LinkedList<Participacio>();
        if (str.length() > 0) {
            int start = 0;
            do {
                int end = str.indexOf('&', start);
                if (end == -1) {
                    end = str.length();
                }
                add(new Participacio(str.substring(start, end).trim()));
                start = end + 1;
            } while (start < str.length());
        }
    }

    /**
     * Constructora.
     * @param str cadena de text amb les participacions.
     */
    public LlistaParticipacions(LlistaParticipacions p_llistaParticipacions) {
        llista = new LinkedList<Participacio>();
        if (!p_llistaParticipacions.isEmpty()) {
            ListIterator<Participacio> it = p_llistaParticipacions.llista.listIterator();
            while (it.hasNext()) {
                llista.add(it.next());
            }
        }
    }

    /**
     * Afegeix una participació a la llista.
     * @param  p La participació a afegir a la llista.
     * @see Participacio
     */
    public final void add(Participacio p) {
        if (llista.isEmpty()) {
            llista.add(p);
        } else {
            ListIterator<Participacio> it = llista.listIterator();
            boolean major = false;
            Participacio pant = null;
            while (it.hasNext() && (major == false)) {
                pant = it.next();
                if (p.compareTo(pant) > 0) {
                    major = true;
                    llista.add(it.previousIndex(), p);
                }
            }
            if (major == false) {
                llista.add(p);
            }
        }
    }

    /**
     * Elimina una participació de la llista segons el seu identificador.
     * @param  id L'identificador de la participació a eliminar.
     */
    public void del(long id) {
        if (!llista.isEmpty()) {
            ListIterator<Participacio> it = llista.listIterator();
            Participacio aux;
            do {
                aux = it.next();
            } while (it.hasNext() && aux.Id() != id);
            if (aux.Id() == id) {
                it.remove();
            }
        }
    }

    /**
     * Suma de les paricipacions d'una propietat.
     * @return Fraccio amb el resultat de la suma.
     */
    public Fraccio suma() {
        Fraccio aux = null;
        ListIterator<Participacio> it = llista.listIterator();
        while (it.hasNext()) {
            if (aux == null) {
                aux = new Fraccio(it.next().F_participacio());
            } else {
                aux.add(it.next().F_participacio());
            }
        }
        return aux;
    }

    /**
     * Nombre de participacions en la llista.
     * @return Nombre de propietaris.
     */
    public int size() {
        return llista.size();
    }

    /**
     * La llista de participacions està buida?
     * @return cert si la llista està buida, altrament fals.
     */
    public boolean isEmpty() {
        return llista.isEmpty();
    }

    /**
     * Buidar la llista.
     */
    public void clear() {
        llista.clear();
    }

    /**
     * Obtenir un iterador per a la llista.
     * @return Iterador de la llista.
     */
    public ListIterator<Participacio> listIterator() {
        return llista.listIterator();
    }

    /**
     * Comprova si existeix l'identificador d'un propietari en la llista de
     * participacions.
     * @return cert si la existeix el propietari, altrament fals.
     */
    public boolean existeixPropietari(long Id) {
        ListIterator<Participacio> it = llista.listIterator();
        boolean trobat = false;
        while ((it.hasNext()) && (trobat == false)) {
            trobat = (Id == it.next().Id());
        }
        return trobat;
    }

    @Override
    public String toString() {
        String resposta = "";
        ListIterator<Participacio> it = llista.listIterator();
        while (it.hasNext()) {
            resposta += it.next().toString();
            if (it.hasNext()) {
                resposta += " & ";
            }
        }
        return resposta;
    }

    public String informe(long idPropietat, long idAnticPropietari,
            String strDataTransmissio, LlistaPropietaris llistaPropietaris,
            String strTipus, String strDadesAdicionals) {
        String resposta = "";
        ListIterator<Participacio> it = llista.listIterator();
        while (it.hasNext()) {
            Participacio participacio = it.next();
            resposta+=String.format("%4s",String.valueOf(idPropietat)) + " "
                    + String.format("%10s",strDataTransmissio) + " "
                    + String.format("%-25s",llistaPropietaris.getNom(idAnticPropietari))  
                    + String.format("%-25s",llistaPropietaris.getNom(participacio.Id())) + " "
                    + String.format("%-12s",strTipus)  
                    + strDadesAdicionals + "\n";
        }
        return resposta;
    }
}
