package dao;

import java.io.Serializable;
import java.util.List;

public interface InterfaceDAO<T> {

	public void salvar(T entidade);

        public void salvarLista(List<T> lista);

	public T carregar(Class<T> classe,Serializable chavePrimaria);

	public List<T> listar(Class<T> classe);

        public List<T> consultaPersonalizada(String consulta);

        public List<T> consultaNativa(String consulta);

}
