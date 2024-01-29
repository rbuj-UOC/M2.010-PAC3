/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.transmissions.finques;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class LlistaFinques {

    private LinkedList<Finca> llista;

    /**
     * Constructora.
     */
    public LlistaFinques() {
        llista = new LinkedList<Finca>();
    }

    /**
     * Constructora.
     */
    public LlistaFinques(LlistaFinques llistaFinques) {
        llista = new LinkedList<Finca>();
        ListIterator<Finca> it = llistaFinques.llista.listIterator();
        while (it.hasNext()) {
            llista.add(it.next());
        }
    }

    /**
     * Afegeix una propietat a la llista.
     * @param  p La propietat a afegir a la llista.
     * @see Propietat
     */
    public void add(Finca f) {
        if (llista.isEmpty()) {
            llista.add(f);
        } else {
            ListIterator<Finca> it = llista.listIterator();
            Finca aux;
            do {
                aux = it.next();
            } while (it.hasNext() && aux.compareTo(f) > 0);
            it.add(f);
        }
    }

    /**
     * Elimina una propietat de la llista segons el seu identificador.
     * @param  id L'identificador de la propietat a eliminar.
     */
    public void del(long id) {
        if (!llista.isEmpty()) {
            ListIterator<Finca> it = llista.listIterator();
            Finca aux;
            do {
                aux = it.next();
            } while (it.hasNext() && aux.Id() != id);
            if (aux.Id() == id) {
                it.remove();
            }
        }
    }

    /**
     * Obtenir un iterador per a la llista.
     * @return Iterador de la llista.
     */
    public ListIterator<Finca> listIterator() {
        return llista.listIterator();
    }

    /**
     * Nombre de propietats en la llista.
     * @return Nombre de propietaris.
     */
    public int size() {
        return llista.size();
    }

    /**
     * La llista de propietats està buida?
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
}
