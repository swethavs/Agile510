package com.mapro.mongo.utility;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class MongDBConnectionInstance {

private static DB instance = null;

protected MongDBConnectionInstance() { }

public static DB getMongoConnectionInstance() {
		      if(instance == null) {
		         try {
		        	 Mongo mongo= new Mongo("localhost", 27017);
		        	 instance=mongo.getDB("maProDB");
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		      return instance;
		   }
		
}
