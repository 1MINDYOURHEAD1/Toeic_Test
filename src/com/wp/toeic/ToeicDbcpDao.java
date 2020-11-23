package com.wp.toeic;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ToeicDbcpDao extends ToeicDao {
	private String dbcpResourceName;

	// 생성자와
	public ToeicDbcpDao(String dbcpResourceName) {
		this.dbcpResourceName = dbcpResourceName;
	}

	// connect DB
	protected void connectDB() throws SQLException {
		// context.xml 을 쓰기위한 과정
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup(dbcpResourceName);

			conn = ds.getConnection();
		} catch (NamingException e) {
			throw new SQLException(e);
		}
	}
}
