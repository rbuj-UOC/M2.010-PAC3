/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.transmissions.herencies;

import g_compropietarios.data.Data;
import g_compropietarios.propietaris.LlistaPropietaris;
import g_compropietarios.transmissions.Transmissio;
import g_compropietarios.transmissions.finques.Finca;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class Herencia extends Transmissio {

    private LinkedList<ItemHerencia> llista;

    /**
     * La constructora.
     */
    public Herencia() {
        super();
        llista = new LinkedList<ItemHerencia>();
    }

    /**
     * La constructora.
     */
    public Herencia(String str) {
        super(str);
        llista = new LinkedList<ItemHerencia>();
    }

    /**
     * La constructora.
     */
    public Herencia(long p_id, Data p_dataTransmissio) {
        super(p_id, p_dataTransmissio);
        llista = new LinkedList<ItemHerencia>();
    }

    /**
     * La constructora.
     */
    public Herencia(Herencia h) {
        super(h.Id(), h.DataTransmissio());
        llista = new LinkedList<ItemHerencia>(h.llista);
    }

    @Override
    public String toString() {
        // TODO
        return "Hello";
    }

    @Override
    public int addItem(String str) {
        ItemHerencia item = new ItemHerencia(str);
        llista.add(item);
        return llista.indexOf(item);
    }

    @Override
    public void addItem(int idx, String str) {
        ListIterator<ItemHerencia> it = llista.listIterator(idx);
        Finca f = new Finca(str);
        it.next().add(f);
    }

    /**
     * Obtenir un iterador per a la llista.
     * @return Iterador de la llista.
     */
    @Override
    public ListIterator listIterator() {
        return llista.listIterator();
    }

    @Override
    public String informe(LlistaPropietaris llistaPropietaris) {
        ListIterator<ItemHerencia> it = llista.listIterator();
        String strResposta = "";
        while (it.hasNext()) {
            ItemHerencia itemHerencia = it.next();
            strResposta += itemHerencia.informe(Id(), DataTransmissio().toString(),
                    llistaPropietaris, this.getClass().getSimpleName());
        }
        return strResposta;
    }
}
