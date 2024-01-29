/*
 * Introducció al desenvolupament de programari
 * Màster programari lliure - UOC - PAC3
 */
package g_compropietarios.transmissions.donacions;

import g_compropietarios.propietaris.LlistaPropietaris;
import g_compropietarios.transmissions.finques.Finca;
import g_compropietarios.transmissions.finques.LlistaFinques;
import java.util.ListIterator;

/**
 * @author Robert Antoni Buj Gelonch <rbuj@uoc.edu>
 * @version PAC3 v1.0
 */
public class ItemDonacio extends LlistaFinques {

    private boolean ambObligacions;
    private String descripcio;

    /**
     * La constructora.
     */
    public ItemDonacio(boolean p_ambObligacions, String p_descripcio) {
        super();
        setAmbObligacions(p_ambObligacions);
        setDescripcio(p_descripcio);
    }

    /**
     * La constructora.
     */
    public ItemDonacio(ItemDonacio itemDonacio) {
        super((LlistaFinques) itemDonacio);
        setAmbObligacions(itemDonacio.AmbObligacions());
        setDescripcio(itemDonacio.Descripcio());
    }

    /**
     * La constructora.
     */
    public ItemDonacio(String str) {
        super();
        int start = 1;
        int end = str.indexOf(';');
        setAmbObligacions(str.substring(start, end).trim().compareTo("con obligaciones") == 0);
        if (ambObligacions) {
            start = end + 1;
            end = str.length();
            setDescripcio(str.substring(start, end).trim());
        }
    }

    /**
     * Mètode Getter.
     * @return Cert en cas que en tingui obligacions, altrament fals.
     */
    public boolean AmbObligacions() {
        return ambObligacions;
    }

    /**
     * Mètode Getter.
     * @return Descripció de la donació.
     */
    public String Descripcio() {
        return descripcio;
    }

    /**
     * Mètode Setter. Estableix si la donació té o no una oblicació.
     * @param p_ambObligacions Cert en cas que tingui una obligació, fals altrament.
     */
    public final void setAmbObligacions(boolean p_ambObligacions) {
        ambObligacions = p_ambObligacions;
    }

    /**
     * Mètode Setter. Estableix la descripció de l'obligació de la donació.
     * @param p_descripcio Descripció de l'obligació.
     */
    public final void setDescripcio(String p_descripcio) {
        descripcio = p_descripcio;
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
        if (ambObligacions) {
            return "Tipo: Con obligaciones (" + descripcio + ")";
        } else {
            return "Tipo: Simple";
        }
    }
}
