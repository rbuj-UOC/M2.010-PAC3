/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.transmissions.donacions;

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
public class Donacio extends Transmissio{

    private LinkedList<ItemDonacio> llista;

    /**
     * La constructora.
     */
    public Donacio(){
        super();
        llista = new LinkedList<ItemDonacio>();
    }
    
    /**
     * La constructora.
     */
    public Donacio(String str){
        super(str);
        llista = new LinkedList<ItemDonacio>();
    }
    
    /**
     * La constructora.
     */
    public Donacio(long p_id, Data p_dataTransmissio){
        super(p_id, p_dataTransmissio);
        llista = new LinkedList<ItemDonacio>();
    }
    
    /**
     * La constructora.
     */
    public Donacio(Donacio d){
        super(d.Id(), d.DataTransmissio());
        llista = new LinkedList<ItemDonacio>(d.llista);
    }
    
    @Override
    public String toString(){
        // TODO
        return "Hello";
    }

    @Override
    public int addItem(String str){
        ItemDonacio item = new ItemDonacio(str);
        llista.add(item);
        return llista.indexOf(item);
    }

    @Override
    public void addItem(int idx, String str){
        ListIterator<ItemDonacio> it = llista.listIterator(idx);
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
        ListIterator<ItemDonacio> it = llista.listIterator();
        String strResposta = "";
        while (it.hasNext()) {
            ItemDonacio itemDonacio = it.next();
            strResposta += itemDonacio.informe(Id(), DataTransmissio().toString(),
                    llistaPropietaris, this.getClass().getSimpleName());
        }
        return strResposta;
    }
}
