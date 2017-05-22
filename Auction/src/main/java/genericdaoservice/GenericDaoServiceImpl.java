/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericdaoservice;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author krisztian
 */
//@ApplicationScoped
@Stateless
public class GenericDaoServiceImpl implements GenericDaoService{
    
   
    @PersistenceContext
     EntityManager entityManager;
    
    public EntityManager getEM(){return entityManager;}
    
    
    
    public void save(Object pSort){
        entityManager.merge(pSort);
    }
 
    public List getEntities(String pQuery, Map<String,Object> pParams){
        return queryFactory(pQuery, pParams).getResultList();
    }

    public Object getEntity(String pQuery, Map<String,Object> pParams){
        return queryFactory(pQuery, pParams).getSingleResult();
    }
 
    
    private Query queryFactory(String pQuery, Map<String,Object> pParams){
        Query q = entityManager.createNamedQuery(pQuery);
        Iterator<String> it = pParams.keySet().iterator();
        String key;
        while(it.hasNext()){
            key = it.next();
            q.setParameter(key, pParams.get(key));
        }
        return q;
    }
    
}
