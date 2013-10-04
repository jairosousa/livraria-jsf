package service;

import dao.InterfaceDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("genericService")
public class GenericService extends AbstractService {

    @Override
    @Value("#{genericDAO}")
    public void setDao(InterfaceDAO dao) {
        this.dao = dao;
    }

}
