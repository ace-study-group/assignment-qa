package Backbone; 

import java.util.Date;

public class Singleton
{
	   private static Singleton singleton = new Singleton( );

	   /* A private Constructor prevents any other
	    * class from instantiating.
	    */
	   public Singleton() { }

	   /* Static 'instance' method */
	   public static Singleton getInstance( ) {
	      return singleton;
	   }

		public static String status; 
		
		
		public static String getStatus()
		{
			return status;
		}

		public static void setStatus(String status)
		{
			Singleton.status = status;
		}
	      
		
}
