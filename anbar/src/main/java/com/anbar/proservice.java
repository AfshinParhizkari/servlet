package main.java.com.anbar;

import java.sql.SQLException;

public class proservice {
	public boolean prosrv(Product pro) throws SQLException
	{
		try {
			Prodao daolayer = new Prodao();
			boolean newid = daolayer.savetodb(pro);
			return newid;
			
		} catch (Exception e) {
			throw new SQLException(e);
			}

		
	}

}
