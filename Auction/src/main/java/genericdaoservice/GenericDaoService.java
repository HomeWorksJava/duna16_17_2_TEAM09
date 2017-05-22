
package genericdaoservice;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 * @author krisztian
 */
public interface GenericDaoService {
    
    public EntityManager getEM();
    public void save(Object pSort);
 
    public List getEntities(String pQuery, Map<String,Object> pParams);

    public Object getEntity(String pQuery, Map<String,Object> pParams);
    
}