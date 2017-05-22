/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import datamodell.Auction;
import java.util.List;

/**
 *
 * @author imi
 */

public interface AuctionService 
{
    public List<Auction> getAll();
    public Auction getAuctionById(String id);
}
