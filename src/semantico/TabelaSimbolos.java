/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semantico;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author gilvolpe
 */
public class TabelaSimbolos implements Constantes {

    public TabelaSimbolos() {
        nivelCorrent = 0;

        lista = new ArrayList< ArrayList<Descritor>>();

        ArrayList<Descritor> array = new ArrayList<Descritor>();

//-------- 6 primeiros elementos da tabela que são pré-definidos ---------------
        Descritor inteiro = new Descritor("int", nivelCorrent);
        inteiro.setNbytes(BYTES_INT);
        inteiro.setCategoria(TYPE);

        Descritor boolean_t = new Descritor("TRUE", nivelCorrent);
        boolean_t.setValor(1);
        boolean_t.setNbytes(BYTES_INT);
        boolean_t.setCategoria(CONSTANTE);

        Descritor boolean_f = new Descritor("FALSE", nivelCorrent);
        boolean_f.setValor(0);
        boolean_f.setNbytes(BYTES_INT);
        boolean_f.setCategoria(CONSTANTE);

        Descritor readlong = new Descritor("readlong", nivelCorrent);
        readlong.setCategoria(PROC);
        readlong.setNpar(-1);

        Descritor writelong = new Descritor("writelong", nivelCorrent);
        writelong.setCategoria(PROC);
        writelong.setNpar(-1);

        Descritor writeline = new Descritor("writeline", nivelCorrent);
        writeline.setCategoria(PROC);
        writeline.setNpar(-1);
//------------------------------------------------------------------------------

//-------------- adicionando os 6 elementos na TS ------------------------------
        array.add(inteiro);
        array.add(boolean_t);
        array.add(boolean_f);

        array.add(readlong);
        array.add(writelong);
        array.add(writeline);
//------------------------------------------------------------------------------

        //posição zero está todos os caras
        // que estão relacionados a nivel zero mesmo !!!
        lista.add(array);
    }

    void elimina(int nivel) {
        if (nivel != 0 && nivel < lista.size() && nivel > 0) {
            lista.remove(nivel);
        }
    }

    public int getNivelCorrent() {
        return nivelCorrent;
    }

    public int getPosition(String id) {

        Iterator it = (lista.get(0)).iterator();

        while (it.hasNext()) {
            Object obj = it.next();

            if (obj instanceof Descritor) {
                Descritor elem = (Descritor) obj;
                if (elem.getIdent().compareTo(id) == 0) {
                    return (lista.get(0)).indexOf(elem);
                }
            }
        }

        return -1;
    }

    /**
     * Busca ident na TS
     *
     * @param ident identificador a ser buscado na TS
     * @return retorna o identificador
     */
    Descritor busca(String ident) {

        for (ArrayList<Descritor> array : lista) {

            Iterator elem = array.iterator();

            int nivel = lista.indexOf(array);

            while (elem.hasNext()) {
                Object obj2 = elem.next();

                if (obj2 instanceof Descritor) {
                    Descritor comp = new Descritor(ident, nivel);
//                    Descritor des = (Descritor) elem.next();
                    Descritor des = (Descritor) obj2;

                    if (comp.equals(des)) {
                        return des;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param nivel indica o nível que se encontra o ident
     * @param ident identificador a ser procurado
     * @return true se encontrou o ident para aquele nivelcorr; false cc.
     */
    boolean declarado(String ident, int nivel) {

        if (nivel < lista.size()) {

            ArrayList<Descritor> array = lista.get(nivel);
            Iterator it = array.iterator();

            while (it.hasNext()) {
                Object obj2 = it.next();

                if (obj2 instanceof Descritor) {
                    Descritor comp = new Descritor(ident, nivel);
//                    Descritor des = (Descritor) it.next();
                    Descritor des = (Descritor) obj2;

                    if (comp.equals(des)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    boolean insere(String ident) {

        Descritor des = new Descritor(ident, nivelCorrent);

        if (!declarado(ident, nivelCorrent)) {
            lista.get(nivelCorrent).add(des);
            return true;
        }

        return false;
    }

    void insere(Descritor desc) {
        if (!declarado(desc.getIdent(), desc.getNivel())) {
            lista.get(nivelCorrent).add(desc);
        }
    }

    /**
     * aumenta o nivel atual.
     */
    public void addNivelCorrente() {
        ArrayList<Descritor> array = new ArrayList<Descritor>();
        lista.add(array);
        this.nivelCorrent = lista.size() - 1;
    }

    public void subNivelCorrente() {
        if (this.nivelCorrent > 0) {
            lista.remove(nivelCorrent);
            this.nivelCorrent--;
        }
    }

    @Override
    public String toString() {
        String str = "";

        for (ArrayList<Descritor> array : lista) {

            Iterator elem = array.iterator();
            int nivel = lista.indexOf(array);

            str += nivel + "\n";
            while (elem.hasNext()) {
                Object obj = elem.next();

                if (obj instanceof Descritor) {
//                    Descritor des = (Descritor) elem.next();
                    Descritor des = (Descritor) obj;
                    str += des.toString();
                }
            }
        }
        return str;
    }
    private ArrayList< ArrayList<Descritor>> lista;
    private int nivelCorrent;

    /*
     * Para testar as coisas dessa joça
     */
    public static void main(String[] args) {

        TabelaSimbolos ts = new TabelaSimbolos();

//        System.out.println(ts);
//
//        ts.addNivelCorrente();
//
//        String jaca = "jaca";
//
//        ts.insere(jaca);
//
//        System.out.println(ts);
//
//        ts.elimina(1);
//
//        System.out.println(ts);
//
//        Descritor des = ts.busca("int");
//
//        System.out.println(des);
    }
}
