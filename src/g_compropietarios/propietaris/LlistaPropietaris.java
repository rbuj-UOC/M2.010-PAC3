/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.propietaris;

import g_compropietarios.estadistiques.Estadistica;
import g_compropietarios.propietats.LlistaPropietats;
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
public class LlistaPropietaris {

    private LinkedList<Propietari> llista;

    /**
     * Constructora.
     */
    public LlistaPropietaris() {
        llista = new LinkedList<Propietari>();
    }

    /**
     * Afegeix un propietari a la llista.
     * @param  p El propietari a afegir a la llista.
     * @see Propietari
     */
    public void add(Propietari p) {
        if (llista.isEmpty()) {
            llista.add(p);
        } else {
            ListIterator<Propietari> it = llista.listIterator();
            boolean major = false;
            Propietari pant = null;
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
     * Elimina un propietari de la llista segons el seu identificador.
     * @param  id L'identificador del propietari a eliminar.
     */
    public void del(long id) {
        if (!llista.isEmpty()) {
            ListIterator<Propietari> it = llista.listIterator();
            Propietari aux;
            do {
                aux = it.next();
            } while (it.hasNext() && aux.Id() != id);
            if (aux.Id() == id) {
                it.remove();
            }
        }
    }

    /**
     * Afegeix els propietaris d'una llista a la llista de propietaris.
     * @param  llistaPropietaris Llista de propietaris a afegir.
     */
    public void append(LlistaPropietaris llistaPropietaris) {
        if (!llistaPropietaris.llista.isEmpty()) {
            ListIterator<Propietari> it = llistaPropietaris.llista.listIterator();
            do {
                llista.add(new Propietari(it.next()));
            } while (it.hasNext());
        }
    }

    /**
     * Nombre de propietaris en la llista.
     * @return Nombre de propietaris.
     */
    public int size() {
        return llista.size();
    }

    /**
     * La llista de propietaris està buida?
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
    public ListIterator<Propietari> listIterator() {
        return llista.listIterator();
    }

    /**
     * Clonacio del la llista.
     */
    public void assign(LlistaPropietaris p_llistaPropietaris) {
        llista.clear();
        ListIterator<Propietari> it = p_llistaPropietaris.listIterator();
        while (it.hasNext()) {
            llista.add(new Propietari(it.next()));
        }
    }

    /**
     * Actualitzar la llista de propietaris eliminant els que no tenen cap
     * participació en cap propietat.
     * @param  llistaPropietats Llista de propietats
     * @see LlistaPropietats
     */
    public void update(LlistaPropietats llistaPropietats) {
        ListIterator<Propietari> itLlistaPropietaris = llista.listIterator();
        while (itLlistaPropietaris.hasNext()) {
            Propietari propietari = itLlistaPropietaris.next();
            if (!llistaPropietats.existeixPropietari(propietari.Id())) {
                itLlistaPropietaris.remove();
            }
        }
    }

    @Override
    public String toString() {
        String resposta = "";
        ListIterator<Propietari> itLlistaPropietaris = llista.listIterator();
        while (itLlistaPropietaris.hasNext()) {
            resposta += itLlistaPropietaris.next().toString() + "\n";
        }
        return resposta;
    }

    public void llistatParticipacions(LlistaPropietats llistaPropietats, int any) {
        try {
            FileWriter fstream = new FileWriter(String.valueOf(any + 1) + "-participaciones.txt");
            BufferedWriter out = new BufferedWriter(fstream);

            long numPropietaris = 0;
            Estadistica estadistica = null;
            ListIterator<Propietari> itLlistaPropietaris = llista.listIterator();
            out.write("  id Nombre                   Email              #F   Part(/)   Part(&)\n");
            out.write("----------------------------------------------------------------------------\n");
            while (itLlistaPropietaris.hasNext()) {
                Propietari propietari = itLlistaPropietaris.next();
                Estadistica estadisticaParcial = llistaPropietats.llistatParticipacions(propietari.Id());
                if (estadistica == null) {
                    estadistica = new Estadistica(estadisticaParcial);
                } else {
                    estadistica.add(estadisticaParcial);
                }
                out.write(String.format("%4s", String.valueOf(propietari.Id())) + " "
                        + String.format("%-25s", propietari.Nom())
                        + String.format("%-20s", propietari.Email())
                        + estadisticaParcial.toString() + "\n");
                numPropietaris++;
            }
            out.write("----------------------------------------------------------------------------\n");
            out.write(String.format("%4s", String.valueOf(numPropietaris))
                    + String.format("%-46s", " ")
                    + estadistica.toString() + "\n");
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(LlistaPropietaris.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNom(long idPropietari) {
        String resposta = String.valueOf(idPropietari);
        boolean trobat = false;
        ListIterator<Propietari> itLlistaPropietaris = llista.listIterator();
        while (itLlistaPropietaris.hasNext() && (trobat==false)) {
            Propietari propietari = itLlistaPropietaris.next();
            if (propietari.Id()==idPropietari){
                resposta = propietari.Nom();
                trobat = true;
            }
        }
        return resposta;
    }
}
