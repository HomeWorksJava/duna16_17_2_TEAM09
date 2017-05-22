/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import api.AuctionService;
import datamodell.Auction;
import genericdaoservice.GenericDaoService;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author imi
 */

@Path("auction")
public class AuctionServiceImpl implements AuctionService {

    @Inject 
        GenericDaoService gds;
    
    @GET
    @Path("osszes")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Auction> getAll() {
       return gds.getEntities("Auction.getAll", new HashMap<>());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Auction getAuctionById(@PathParam("id")String id) 
    {
            Long searchingid = Long.parseLong(id);      
            HashMap params = new HashMap<>();
            params.put("id",searchingid);
        
        
       return (Auction) gds.getEntity("Auction.getAuctionById", params);
    }
    
}
