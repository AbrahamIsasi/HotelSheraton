package org.sam.alurahotel.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
private DataSource dataSource;
	
	public ConnectionFactory() {
		var pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("debora2020");
		pooledDataSource.setMaxPoolSize(10);
		
		this.dataSource = pooledDataSource;
	}

	public Connection recupetaConexion(){
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
