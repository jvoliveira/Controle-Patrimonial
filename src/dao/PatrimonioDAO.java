package dao;

import dao.*;
import java.util.List;
import model.Patrimonio;
import model.Patrimonio;

/**
 *
 * @author joliveira
 */
public class PatrimonioDAO extends GenericDAO<Patrimonio> {

    public List<Patrimonio> listarTodos() {

        List<Patrimonio> result = null;
        connect();

        try {
            result = getManager().createQuery("from Patrimonio p").getResultList();
            encerrar();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            encerrar();
            return result;
        }
    }

    public Patrimonio listarPorID(Long id) {

        Patrimonio result = null;
        connect();

        try {
            result = (Patrimonio) getManager().createQuery("from Patrimonio p where p.id = " + id).getSingleResult();
            encerrar();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            encerrar();
            return result;
        }
    }

}
