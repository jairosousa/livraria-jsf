package delegate;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import service.AbstractService;

@Component(value = "genericBD")
public class GenericBD extends AbstractBD implements Serializable {

    @Override
    @Value("#{genericService}")
    public void setService(AbstractService service) {
        this.service = service;
    }
}
