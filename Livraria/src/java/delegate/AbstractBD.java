package delegate;

import entidades.Entidade;
import java.io.Serializable;
import java.util.List;
import service.AbstractService;

public abstract class AbstractBD<T extends Entidade> {

    protected AbstractService<T> service;

    public abstract void setService(AbstractService<T> service);

    public void salvar(T entidade) {
        service.salvar(entidade);
    }

    public void salvarLista(List<T> lista) {
        service.salvarLista(lista);
    }

    public T carregar(Class classe, Serializable codigo) {
        return service.carregar(classe, codigo);
    }

    public List<T> listar(Class classe) {
        return service.listar(classe);
    }

    public List<T> consultaPersonalizada(String consulta) {
        return service.consultaPersonalizada(consulta);
    }

    public List<T> consultaNativa(String consulta) {
        return service.consultaNativa(consulta);
    }

}
