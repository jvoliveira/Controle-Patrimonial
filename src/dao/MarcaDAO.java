package dao;

import java.util.List;
import model.Marca;

/**
 *
 * @author joliveira
 */
public class MarcaDAO extends GenericDAO<Marca> {

    public List<Marca> listarTodos() {

        List<Marca> result = null;
        connect();

        try {
            result = getManager().createQuery("from Marca m").getResultList();
            encerrar();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            encerrar();
            return result;
        }
    }

    public Marca listarPorID(Long id) {

        Marca result = null;
        connect();

        try {
            result = (Marca) getManager().createQuery("from Marca m where m.id = " + id).getSingleResult();
            encerrar();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            encerrar();
            return result;
        }
    }

}
