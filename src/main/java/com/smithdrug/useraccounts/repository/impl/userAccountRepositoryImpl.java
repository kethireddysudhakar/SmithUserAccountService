package com.smithdrug.useraccounts.repository.impl;

import javax.naming.ldap.LdapName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Repository;

import com.smithdrug.useraccounts.entity.UserInformation;
import com.smithdrug.useraccounts.repository.userAccountRepository;
import com.smithdrug.useraccounts.rowmapper.UserRowMapper;

@Repository
public class userAccountRepositoryImpl implements userAccountRepository{
	
	@Autowired
	@Qualifier("jdbcTemplatedb2")
	private JdbcTemplate jdbcDB2Template;
	
	@Autowired
	@Qualifier("jdbcTemplateas400")
	private JdbcTemplate jdbcAS400Template;
	
	@Value("${sql.fsp.getUserInfo}")
	private String getUserInfoSql;
	
	@Value("${sql.fps.insertResetRequest}")
	private String insertFPSRequestSQL;
	
	@Value("${sql.fps.updateResetRequest}")
	private String updateFPSRequestSQL;
	
	@Value("${sql.fps.updateExpiryRequest}")
	private String updateExpiryRequestSQL;
	
	@Value("${sql.fps.deleteResetRequest}")
	private String deleteFPSRequestSQL;
	
	@Value("${sql.fsp.validateResetToken}")
	private String validateResetSQL;
	
	@Autowired
    private LdapTemplate ldapTemplate;
	
	private static final String BASE_DN = "OU=Metaverse";

	
	public boolean changeUserPasswordLDAP(String accountNumber,String password) {
		LdapName dn = LdapNameBuilder.newInstance(BASE_DN)
	            .add("OU", "Orga_Unit")
	            .add("OU", "Orga_Unit")
	            .add("CN", "ldap_cn").build();

	        DirContextAdapter context = new DirContextAdapter(dn);

	        context.setAttributeValues(
	           "objectclass", 
	           new String[] 
	           { "top", 
	             "person", 
	             "organizationalPerson"});
	        context.setAttributeValue("cn", accountNumber);
	        context.setAttributeValue("userPassword",password);

	        ldapTemplate.bind(context);
		return false;
	}

	

	@Override
	public UserInformation getUserInfo(String accountNumber) {
		Object[] params = new Object[] {accountNumber};
		return jdbcAS400Template.queryForObject(getUserInfoSql,params,new UserRowMapper());
	}
	
}
