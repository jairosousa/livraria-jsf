package dao;

import entidades.Entidade;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.stereotype.Repository;

@Repository("genericDAO")
@Scope(value = "singleton")
public class GenericDAO<T extends Entidade> implements InterfaceDAO<T> {

    @Autowired
    private SharedEntityManagerBean jpaTemplate;

    @Override
    public void salvar(T entidade) {
        if (entidade != null) {
            salvarSemFlush(entidade);
            try {
                jpaTemplate.getObject().flush();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void salvarSemFlush(T entidade) {
        if (entidade != null) {
            try {
                if (entidade.getFlagRemover()) {
                    if (entidade.getId() != null) {
                        if (jpaTemplate.getObject().find(entidade.getClass(), entidade.getId()) != null) {
                            jpaTemplate.getObject().remove(jpaTemplate.getObject().getReference(entidade.getClass(), entidade.getId()));
                        }
                    }
                } else {
                    if (entidade.getId() == null) {
                        jpaTemplate.getObject().persist(entidade);
                    } else {
                        if (jpaTemplate.getObject().find(entidade.getClass(), entidade.getId()) != null) {
                            jpaTemplate.getObject().merge(entidade);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());;
            }
        }
    }

    @Override
    public void salvarLista(List<T> lista) {
        Integer i = 0;
        if (lista != null) {
            for (T entidade : lista) {
                salvarSemFlush(entidade);
                i++;
                // sincroniza a cada 500 registros
                if ((i % 500) == 0) {
                    try {
                        jpaTemplate.getObject().flush();
                    } catch (Exception e) {
                        System.out.println(e.getLocalizedMessage());;
                    }
                }
            }
            // sincroniza os ultimos registros
            if (i % 500 != 0) {
                try {
                    jpaTemplate.getObject().flush();
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());;
                }
            }
        }
    }

    @Override
    public T carregar(Class<T> classe, Serializable chave) {
        if (classe == null || chave == null) {
            return null;
        }
        return jpaTemplate.getObject().find(classe, chave);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> listar(Class<T> classe) {
        if (classe == null) {
            return null;
        }
        return jpaTemplate.getObject().createQuery("select a from " + classe.getSimpleName() + " a ").getResultList();
    }

    @Override
    public List<T> consultaPersonalizada(String consulta) {
        if (consulta == null) {
            return null;
        }
        return jpaTemplate.getObject().createQuery(consulta).getResultList();
    }

    @Override
    public List<T> consultaNativa(String consulta) {
        if (consulta == null) {
            return null;
        }
        return jpaTemplate.getObject().createNativeQuery(consulta).getResultList();
    }

    public SharedEntityManagerBean getJpaTemplate() {
        return jpaTemplate;
    }
}
