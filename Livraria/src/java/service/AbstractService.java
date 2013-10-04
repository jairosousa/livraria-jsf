package service;

import dao.InterfaceDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public abstract class AbstractService<T> {

    protected InterfaceDAO<T> dao;

    public void setDao(InterfaceDAO<T> dao) {
        this.dao = dao;
    }

    public void salvar(T entity) {
        dao.salvar(entity);
    }

    public void salvarLista(List<T> lista) {
        dao.salvarLista(lista);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public T carregar(Class classe, Serializable chave) {
        return dao.carregar(classe, chave);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<T> listar(Class classe) {
        return dao.listar(classe);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<T> consultaPersonalizada(String consulta) {
        return dao.consultaPersonalizada(consulta);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<T> consultaNativa(String consulta) {
        return dao.consultaNativa(consulta);
    }
}