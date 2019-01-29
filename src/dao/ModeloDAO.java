package dao;

import java.util.ArrayList;
import java.util.List;
import model.Marca;
import model.Modelo;

/**
 *
 * @author joliveira
 */
public class ModeloDAO extends GenericDAO<Modelo> {

    public List<Modelo> listarTodos() {

        List<Modelo> result = null;
        connect();

        try {
            result = getManager().createQuery("from Modelo m").getResultList();
            encerrar();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            encerrar();
            return result;
        }
    }

    public Modelo listarPorID(Long id) {

        Modelo result = null;
        connect();

        try {
            result = (Modelo) getManager().createQuery("from Modelo m where m.id = " + id).getSingleResult();
            encerrar();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            encerrar();
            return result;
        }
    }
    
    public List<Modelo> listarPorMarca(Marca m) {

        List<Modelo> aux;
        List<Modelo> result = new ArrayList();
        connect();
        try {
            aux = getManager().createQuery("from Modelo m").getResultList();
            for (Modelo modelo : aux) {
                if(modelo.getMarca().equals(m)){
                    result.add(modelo);
                }
            }
            
            encerrar();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            encerrar();
            return result;
        }
    }

}
