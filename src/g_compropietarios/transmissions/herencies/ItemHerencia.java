/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.transmissions.herencies;

import g_compropietarios.data.Data;
import g_compropietarios.propietaris.LlistaPropietaris;
import g_compropietarios.transmissions.finques.Finca;
import g_compropietarios.transmissions.finques.LlistaFinques;
import java.util.ListIterator;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class ItemHerencia extends LlistaFinques {

    private String parentesc;
    private Data dataAcceptacio;

    /**
     * La constructora.
     */
    public ItemHerencia() {
        super();
        dataAcceptacio = new Data();
    }

    /**
     * La constructora.
     */
    public ItemHerencia(String str) {
        super();
        int start = 1;
        int end = str.indexOf(';');
        setParentesc(str.substring(start, end).trim());
        start = end + 1;
        end = str.length();
        dataAcceptacio = new Data(str.substring(start, end).trim());
    }

    /**
     * La constructora.
     */
    public ItemHerencia(String p_parentesc, Data p_dataAcceptacio) {
        super();
        setParentesc(p_parentesc);
        setDataAcceptacio(p_dataAcceptacio);
    }

    /**
     * La constructora.
     */
    public ItemHerencia(ItemHerencia itemHerencia) {
        super((LlistaFinques) itemHerencia);
        setParentesc(itemHerencia.Parentesc());
        setDataAcceptacio(itemHerencia.DataAcceptacio());
    }

    /**
     * Mètode Getter.
     * @return Parentesc de la herència.
     */
    public String Parentesc() {
        return parentesc;
    }

    /**
     * Mètode Getter.
     * @return Data de l'acceptació de la herència.
     */
    public Data DataAcceptacio() {
        return dataAcceptacio;
    }

    /**
     * Mètode Setter. Estableix el parentesc de l'herència.
     * @param p_parentesc Parentesc de l'herència.
     */
    public final void setParentesc(String p_parentesc) {
        parentesc = p_parentesc;
    }

    /**
     * Mètode Setter. Estableix la data d'acceptació de l'herència.
     * @param p_dataAcceptacio Data de l'acceptació de l'herència.
     * @see Data
     */
    public final void setDataAcceptacio(Data p_dataAcceptacio) {
        dataAcceptacio = p_dataAcceptacio;
    }

    public String informe(long idAnticPropietari, String strDataTransmissio,
            LlistaPropietaris llistaPropietaris, String strTipus) {
        String resposta = "";
        ListIterator<Finca> it = listIterator();
        while (it.hasNext()) {
            Finca finca = it.next();
            resposta += finca.informe(finca.Id(), idAnticPropietari, 
                    strDataTransmissio, llistaPropietaris, strTipus, 
                    this.toString());
        }
        return resposta;
    }

    @Override
    public String toString() {
        return "Parentesco: " + parentesc + " (F.Ac: " + 
                dataAcceptacio.toString() + ")";
    }
}
