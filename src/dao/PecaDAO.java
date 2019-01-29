package dao;

import java.util.List;
import model.Peca;

/**
 *
 * @author joliveira
 */
public class PecaDAO extends GenericDAO<Peca> {

    public List<Peca> listarTodos() {

        List<Peca> result = null;
        connect();

        try {
            result = getManager().createQuery("from Peca p").getResultList();
            encerrar();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            encerrar();
            return result;
        }
    }

    public Peca listarPorID(Long id) {

        Peca result = null;
        connect();

        try {
            result = (Peca) getManager().createQuery("from Peca p where p.id = " + id).getSingleResult();
            encerrar();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            encerrar();
            return result;
        }
    }

}
