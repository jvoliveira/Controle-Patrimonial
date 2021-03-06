
package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class GenericDAO<T> {

    protected final EntityManagerFactory factory = Persistence.createEntityManagerFactory("ControlePatrimonialPU");
    
    protected final EntityManager manager = factory.createEntityManager();

    public EntityManager getManager() {
        return manager;
    }

    protected void connect() {
        manager.getTransaction().begin();
    }

    protected void encerrar() {
        manager.getTransaction().commit();
        manager.close();
    }

    /*
    Para atualizar a classe no banco é somente necessário o merge e não o refresh, 
    pois o merge volta com a classe para o estage de maneged e o refresh não faz isso. 
    */
    public void atualizar(T c) {
        try {
            connect();
            manager.merge(c);
            encerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void incluir(T c) {
        try {
            connect();
            manager.persist(c);
            encerrar();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    /*
    Para excluir é necessário fazer uma pesquisa pela classe dentro do banco.
    O Class<T> clazz, é uma classe abastrata para ser utilizada quando se trabalha com 
    classe genérica. Por isso usei ele, pois quando faço uma pesquisa no banco eu preciso passar
    a classe a qual estou pesquisando. Assim, quando eu passo o ID, o JPA sabe exatamente oque procurar 
    dentro do banco, ou seja, ele precisa procurar a disciplina com o ID X e depois remove-la. 
    */
    
    public void excluir(Class<T> clazz, Long id) {
        try {
            connect();
            T t = manager.find(clazz, id);
            manager.remove(t);
            encerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
