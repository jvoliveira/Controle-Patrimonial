package dao;

import java.util.List;
import model.Equipamento;

/**
 *
 * @author joliveira
 */
public class EquipamentoDAO extends GenericDAO<Equipamento> {

    public List<Equipamento> listarTodos() {

        List<Equipamento> result = null;
        connect();

        try {
            result = getManager().createQuery("from Equipamento e").getResultList();
            encerrar();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            encerrar();
            return result;
        }
    }

    public Equipamento listarPorID(Long id) {

        Equipamento equipamento = null;
        connect();

        try {
            equipamento = (Equipamento) getManager().createQuery("from Equipamento e where e.id = " + id).getSingleResult();
            encerrar();
            return equipamento;
        } catch (Exception e) {
            e.printStackTrace();
            encerrar();
            return equipamento;
        }
    }

}
