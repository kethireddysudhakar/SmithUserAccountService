package com.smithdrug.useraccounts.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smithdrug.useraccounts.entity.UserInformation;

public class UserRowMapper implements RowMapper<UserInformation>{

	@Override
	public UserInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserInformation userInfo = new UserInformation();
		userInfo.setAccountNumber(rs.getString("RMCUST").trim());
		userInfo.setAddress1(rs.getString("RMADD1").trim());
		userInfo.setAddress2(rs.getString("RMADD2").trim());
		userInfo.setAddress3(rs.getString("RMADD3").trim());
		userInfo.setCity(rs.getString("RMCITY").trim());
		userInfo.setCountry(rs.getString("RMCNTR").trim());
		userInfo.setCustName(rs.getString("RMNAME").trim());
		userInfo.setEmailAddress(rs.getString("RMEMAL").trim());
		userInfo.setLocation(rs.getString("RMLOC").trim());
		userInfo.setPhone(rs.getString("RMMFON").trim());
		userInfo.setState(rs.getString("RMSTAT").trim());
		userInfo.setZip(rs.getString("RMMZIP").trim());
		return userInfo;
	}

}
