/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.propietats;

import g_compropietarios.estadistiques.Estadistica;
import g_compropietarios.fraccio.Fraccio;
import g_compropietarios.participacions.Participacio;
import g_compropietarios.percentatges.Percentatge;
import g_compropietarios.transmissions.LlistaTransmissions;
import g_compropietarios.transmissions.Transmissio;
import g_compropietarios.transmissions.compraventes.Compraventa;
import g_compropietarios.transmissions.compraventes.ItemCompraventa;
import g_compropietarios.transmissions.donacions.Donacio;
import g_compropietarios.transmissions.donacions.ItemDonacio;
import g_compropietarios.transmissions.finques.Finca;
import g_compropietarios.transmissions.herencies.Herencia;
import g_compropietarios.transmissions.herencies.ItemHerencia;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class LlistaPropietats {

    private LinkedList<Propietat> llista;

    /**
     * Constructora.
     */
    public LlistaPropietats() {
        llista = new LinkedList<Propietat>();
    }

    /**
     * Afegeix una propietat a la llista.
     * @param  p La propietat a afegir a la llista.
     * @see Propietat
     */
    public void add(Propietat p) {
        if (llista.isEmpty()) {
            llista.add(p);
        } else {
            ListIterator<Propietat> it = llista.listIterator();
            boolean major = false;
            Propietat pant = null;
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
     * Elimina una propietat de la llista segons el seu identificador.
     * @param  id L'identificador de la propietat a eliminar.
     */
    public void del(long id) {
        if (!llista.isEmpty()) {
            ListIterator<Propietat> it = llista.listIterator();
            Propietat aux;
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
        ListIterator<Propietat> it = llista.listIterator();
        while (it.hasNext()) {
            if (aux == null) {
                aux = new Fraccio(it.next().Participacio());
            } else {
                aux.add(it.next().Participacio());
            }
        }
        return aux;
    }

    /**
     * Aplica una llista de transmissions a la llista de propietats.
     * @param 
     */
    public void apply(LlistaTransmissions llistaTransmissions) {
        ListIterator<Transmissio> itLlistaTransmissions = llistaTransmissions.listIterator();
        while (itLlistaTransmissions.hasNext()) {
            Transmissio transmissio = itLlistaTransmissions.next();
            if (transmissio instanceof Herencia) {
                applyHerencia(transmissio.listIterator(), transmissio.Id());
                refreshHerencia(transmissio.listIterator(), transmissio.Id());
            } else {
                if (transmissio instanceof Compraventa) {
                    applyCompraventa(transmissio.listIterator(), transmissio.Id());
                    refreshCompraventa(transmissio.listIterator(), transmissio.Id());
                } else {
                    if (transmissio instanceof Donacio) {
                        applyDonacio(transmissio.listIterator(), transmissio.Id());
                        refreshDonacio(transmissio.listIterator(), transmissio.Id());
                    }
                }
            }
        }
    }

    private void applyCompraventa(ListIterator listIterator, long idPropietariAntic) {
        while (listIterator.hasNext()) {
            ItemCompraventa item = (ItemCompraventa) listIterator.next();
            applyFinca(item.listIterator(), idPropietariAntic);
        }
    }

    private void applyDonacio(ListIterator listIterator, long idPropietariAntic) {
        while (listIterator.hasNext()) {
            ItemDonacio item = (ItemDonacio) listIterator.next();
            applyFinca(item.listIterator(), idPropietariAntic);
        }
    }

    private void applyHerencia(ListIterator listIterator, long idPropietariAntic) {
        while (listIterator.hasNext()) {
            ItemHerencia item = (ItemHerencia) listIterator.next();
            applyFinca(item.listIterator(), idPropietariAntic);
        }
    }

    private void applyFinca(ListIterator<Finca> it, long idPropietariAntic) {
        while (it.hasNext()) {
            Finca finca = it.next();
            applyParticipacio(finca, idPropietariAntic);
        }
    }

    private void applyParticipacio(Finca finca, long idPropietariAntic) {
        ListIterator<Propietat> itLlistaPropietats = llista.listIterator();
        if (finca.Id() == 0) {
            while (itLlistaPropietats.hasNext()) {
                Propietat propietat = itLlistaPropietats.next();
                ListIterator<Participacio> itLlistaParticipacions = propietat.listIterator();
                boolean trobat = false;
                while (itLlistaParticipacions.hasNext() && (trobat == false)) {
                    Participacio pAnticPropietari = itLlistaParticipacions.next();
                    if (pAnticPropietari.Id() == idPropietariAntic) {
                        Fraccio fraccio = new Fraccio(pAnticPropietari.F_participacio());
                        ListIterator<Participacio> itParticipacioFinca = finca.listIterator();
                        while (itParticipacioFinca.hasNext()) {
                            Participacio pTransmissio = itParticipacioFinca.next();
                            Participacio pNouPropietari = new Participacio(pTransmissio);
                            pNouPropietari.F_participacio().mul(fraccio);
                            if (propietat.existeixPropietari(pNouPropietari.Id())) {
                                propietat.incrementaParticipacio(pNouPropietari);
                            } else {
                                propietat.afegirParticipacio(pNouPropietari);
                            }
                        }
                        trobat = true;
                    }
                }
            }
        } else {
            boolean trobatPropietat = false;
            while (itLlistaPropietats.hasNext() && (trobatPropietat == false)) {
                Propietat propietat = itLlistaPropietats.next();
                if (propietat.Id() == finca.Id()) {
                    ListIterator<Participacio> itLlistaParticipacions = propietat.listIterator();
                    boolean trobatPropietari = false;
                    while (itLlistaParticipacions.hasNext() && (trobatPropietari == false)) {
                        Participacio pAnticPropietari = itLlistaParticipacions.next();
                        if (pAnticPropietari.Id() == idPropietariAntic) {
                            Fraccio fraccio = new Fraccio(pAnticPropietari.F_participacio());
                            ListIterator<Participacio> itParticipacioFinca = finca.listIterator();
                            while (itParticipacioFinca.hasNext()) {
                                Participacio pTransmissio = itParticipacioFinca.next();
                                Participacio pNouPropietari = new Participacio(pTransmissio);
                                pNouPropietari.F_participacio().mul(fraccio);
                                if (propietat.existeixPropietari(pNouPropietari.Id())) {
                                    propietat.incrementaParticipacio(pNouPropietari);
                                } else {
                                    propietat.afegirParticipacio(pNouPropietari);
                                }
                            }
                            trobatPropietari = true;
                        }
                    }
                    trobatPropietat = true;
                }
            }
        }
    }

    private void refreshHerencia(ListIterator listIterator, long idPropietariAntic) {
        while (listIterator.hasNext()) {
            ItemHerencia item = (ItemHerencia) listIterator.next();
            refreshFinca(item.listIterator(), idPropietariAntic);
        }
    }

    private void refreshDonacio(ListIterator listIterator, long idPropietariAntic) {
        while (listIterator.hasNext()) {
            ItemDonacio item = (ItemDonacio) listIterator.next();
            refreshFinca(item.listIterator(), idPropietariAntic);
        }
    }

    private void refreshCompraventa(ListIterator listIterator, long idPropietariAntic) {
        while (listIterator.hasNext()) {
            ItemCompraventa item = (ItemCompraventa) listIterator.next();
            refreshFinca(item.listIterator(), idPropietariAntic);
        }
    }

    private void refreshFinca(ListIterator<Finca> it, long idPropietariAntic) {
        while (it.hasNext()) {
            eliminarParticipacioFinca(idPropietariAntic, it.next().Id());
        }
    }

    /**
     * Obtenir un iterador per a la llista.
     * @return Iterador de la llista.
     */
    public ListIterator<Propietat> listIterator() {
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

    /**
     * Comprova si existeix l'identificador d'un propietari en la llista de
     * propietats.
     * @return cert si la existeix el propietari, altrament fals.
     */
    public boolean existeixPropietari(long Id) {
        ListIterator<Propietat> itLlistaPropietats = llista.listIterator();
        boolean trobat = false;
        while ((trobat == false) && (itLlistaPropietats.hasNext())) {
            trobat = itLlistaPropietats.next().existeixPropietari(Id);
        }
        return trobat;
    }

    /**
     * Comprova que la suma de les participacions sigui igual a 1.
     */
    public void verifica() {
        Fraccio aux = suma();
        if (!(aux.unitat())) {
            System.out.println("Error la suma de les participacions generals no és la unitat " + aux.toString());
        }
    }

    @Override
    public String toString() {
        String resposta = "";
        ListIterator<Propietat> it = llista.listIterator();
        while (it.hasNext()) {
            resposta += it.next().toString() + "\n";
        }
        return resposta;
    }

    private void eliminarParticipacioFinca(long idPropietari, long idPropietat) {
        if (idPropietat != 0) {
            ListIterator<Propietat> itLlistaPropietats = llista.listIterator();
            while (itLlistaPropietats.hasNext()) {
                Propietat propietat = itLlistaPropietats.next();
                if (propietat.Id() == idPropietat) {
                    ListIterator<Participacio> itLlistaParticipacions = propietat.listIterator();
                    boolean trobat = false;
                    while (itLlistaParticipacions.hasNext() && (trobat == false)) {
                        trobat = (itLlistaParticipacions.next().Id() == idPropietari);
                    }
                    if (trobat) {
                        itLlistaParticipacions.remove();
                    }
                }
            }
        } else {
            ListIterator<Propietat> itLlistaPropietats = llista.listIterator();
            while (itLlistaPropietats.hasNext()) {
                ListIterator<Participacio> itLlistaParticipacions = itLlistaPropietats.next().listIterator();
                boolean trobat = false;
                while (itLlistaParticipacions.hasNext() && (trobat == false)) {
                    trobat = (itLlistaParticipacions.next().Id() == idPropietari);
                }
                if (trobat) {
                    itLlistaParticipacions.remove();
                }
            }
        }
    }

    public Estadistica llistatParticipacions(long idPropietari) {
        Fraccio fraccioParticipacio = null;
        long numPropietats = 0;
        ListIterator<Propietat> itLlistaPropietats = llista.listIterator();
        while (itLlistaPropietats.hasNext()) {
            Propietat propietat = itLlistaPropietats.next();
            ListIterator<Participacio> itLlistaParticipacions = propietat.listIterator();
            boolean trobat = false;
            while (itLlistaParticipacions.hasNext() && (trobat == false)) {
                Participacio participacio = itLlistaParticipacions.next();
                trobat = (participacio.Id() == idPropietari);
                if (trobat) {
                    numPropietats++;
                    Fraccio fraccioAux = new Fraccio(participacio.F_participacio());
                    fraccioAux.mul(propietat.Participacio());
                    if (fraccioParticipacio == null) {
                        fraccioParticipacio = new Fraccio(fraccioAux);
                    } else {
                        fraccioParticipacio.add(fraccioAux);
                    }
                }
            }
        }
        return new Estadistica(fraccioParticipacio, new Percentatge(fraccioParticipacio), numPropietats);
    }
}
