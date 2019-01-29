package dao;

import java.util.List;
import model.Locall;

/**
 *
 * @author joliveira
 */
public class LocallDAO extends GenericDAO<Locall> {

    public List<Locall> listarTodos() {

        List<Locall> result = null;
        connect();

        try {
            result = getManager().createQuery("from Locall l").getResultList();
            encerrar();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            encerrar();
            return result;
        }
    }

    public Locall listarPorID(Long id) {

        Locall result = null;
        connect();

        try {
            result = (Locall) getManager().createQuery("from Locall l where l.id = " + id).getSingleResult();
            encerrar();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            encerrar();
            return result;
        }
    }

}
