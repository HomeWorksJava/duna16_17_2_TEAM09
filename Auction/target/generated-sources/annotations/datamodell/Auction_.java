package datamodell;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Auction.class)
public abstract class Auction_ {

	public static volatile SingularAttribute<Auction, String> timeLimit;
	public static volatile SingularAttribute<Auction, String> itemName;
	public static volatile SingularAttribute<Auction, Integer> highestBid;
	public static volatile SingularAttribute<Auction, String> bidder;
	public static volatile SingularAttribute<Auction, Long> id;
	public static volatile SingularAttribute<Auction, String> categories;
	public static volatile SingularAttribute<Auction, String> status;

}

