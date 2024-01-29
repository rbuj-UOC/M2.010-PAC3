/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.transmissions.compraventes;

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
public class Compraventa extends Transmissio {

    private LinkedList<ItemCompraventa> llista;

    /**
     * La constructora.
     */
    public Compraventa() {
        super();
        llista = new LinkedList<ItemCompraventa>();
    }

    /**
     * La constructora.
     */
    public Compraventa(String str) {
        super(str);
        llista = new LinkedList<ItemCompraventa>();
    }

    /**
     * La constructora.
     */
    public Compraventa(long p_id, Data p_dataTransmissio) {
        super(p_id, p_dataTransmissio);
        llista = new LinkedList<ItemCompraventa>();
    }

    /**
     * La constructora.
     */
    public Compraventa(Compraventa c) {
        super(c.Id(), c.DataTransmissio());
        llista = new LinkedList<ItemCompraventa>(c.llista);
    }

    @Override
    public String toString() {
        // TODO
        return "Hello";
    }

    @Override
    public int addItem(String str){
        ItemCompraventa item = new ItemCompraventa(str);
        llista.add(item);
        return llista.indexOf(item);
    }

    @Override
    public void addItem(int idx, String str){
        ListIterator<ItemCompraventa> it = llista.listIterator(idx);
        Finca f = new Finca(str);
        it.next().add(f);
    }

    /**
     * Obtenir un iterodr per a la llista.
     * @return Iterador de la llista.
     */
    @Override
    public ListIterator listIterator() {
        return llista.listIterator();
    }

    @Override
    public String informe(LlistaPropietaris llistaPropietaris){
        ListIterator<ItemCompraventa> it = llista.listIterator();
        String strResposta = "";
        while (it.hasNext()) {
            ItemCompraventa itemCompraventa = it.next();
            strResposta += itemCompraventa.informe(Id(), DataTransmissio().toString(),
                    llistaPropietaris, this.getClass().getSimpleName());
        }
        return strResposta;
    }
}
