/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.transmissions;

import g_compropietarios.propietaris.LlistaPropietaris;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class LlistaTransmissions {

    private LinkedList<Transmissio> llista;

    /**
     * Constructora.
     */
    public LlistaTransmissions() {
        llista = new LinkedList<Transmissio>();
    }

    /**
     * Afegeix una propietat a la llista.
     * @param  p La propietat a afegir a la llista.
     * @see Transmissio
     */
    public int add(Transmissio t) {
        if (llista.isEmpty()) {
            llista.add(t);
        } else {
            ListIterator<Transmissio> it = llista.listIterator();
            boolean major = false;
            Transmissio tant = null;
            while (it.hasNext() && (major == false)) {
                tant = it.next();
                if (t.compareTo(tant) > 0) {
                    major = true;
                    llista.add(it.previousIndex(), t);
                }
            }
            if (major == false) {
                llista.add(t);
            }
        }
        return llista.indexOf(t);
    }

    public int addItem(int idx, String str) {
        ListIterator<Transmissio> it = llista.listIterator(idx);
        return it.next().addItem(str);
    }

    public void addItem(int idx, int idxItem, String str) {
        ListIterator<Transmissio> it = llista.listIterator(idx);
        it.next().addItem(idxItem, str);
    }

    private String getMotiu(int idx) {
        ListIterator<Transmissio> it = llista.listIterator(idx);
        return it.next().Motiu();
    }

    /**
     * Elimina una propietat de la llista segons el seu identificador.
     * @param  id L'identificador de la propietat a eliminar.
     */
    public void del(long id) {
        if (!llista.isEmpty()) {
            ListIterator<Transmissio> it = llista.listIterator();
            Transmissio aux;
            do {
                aux = (Transmissio) it.next();
            } while (it.hasNext() && aux.Id() != id);
            if (aux.Id() == id) {
                it.remove();
            }
        }
    }

    /**
     * Obtenir un iterodr per a la llista de transmissions.
     * @return Iterador a la llista de transmissions.
     */
    public ListIterator<Transmissio> listIterator() {
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

    public void informe(LlistaPropietaris llistaPropietaris, int any) {
        try {
            FileWriter fstream = new FileWriter(String.valueOf(any + 1) + "-informe.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            ListIterator<Transmissio> itLlistaTransmissions = llista.listIterator();
            out.write(" #NF Fecha Tr.  Prop.Anterior            Prop.Nuevo                Motivo      Datos addicionales\n");
            out.write("---------------------------------------------------------------------------------------------------------------------\n");
            while (itLlistaTransmissions.hasNext()) {
                Transmissio transmissio = itLlistaTransmissions.next();
                out.write(transmissio.informe(llistaPropietaris));
            }
            out.write("---------------------------------------------------------------------------------------------------------------------\n");
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(LlistaPropietaris.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
