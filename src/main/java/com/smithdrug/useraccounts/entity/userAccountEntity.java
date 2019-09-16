package com.smithdrug.useraccounts.entity;

import java.sql.Date;

public class userAccountEntity {

	private int ACCNO;
	private Date CDATE;
	private Date UDATE;
	private Date EDATE;
	private String STATUS;
	private int FPSID;
	private int CLICK;
	private String EMAIL;
	public int getACCNO() {
		return ACCNO;
	}
	public void setACCNO(int aCCNO) {
		ACCNO = aCCNO;
	}
	public Date getCDATE() {
		return CDATE;
	}
	public void setCDATE(Date cDATE) {
		CDATE = cDATE;
	}
	public Date getUDATE() {
		return UDATE;
	}
	public void setUDATE(Date uDATE) {
		UDATE = uDATE;
	}
	public Date getEDATE() {
		return EDATE;
	}
	public void setEDATE(Date eDATE) {
		EDATE = eDATE;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public int getFPSID() {
		return FPSID;
	}
	public void setFPSID(int fPSID) {
		FPSID = fPSID;
	}
	public int getCLICK() {
		return CLICK;
	}
	public void setCLICK(int cLICK) {
		CLICK = cLICK;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public userAccountEntity(int aCCNO, Date cDATE, Date uDATE, Date eDATE, String sTATUS, int fPSID, int cLICK, String eMAIL) {
		super();
		ACCNO = aCCNO;
		CDATE = cDATE;
		UDATE = uDATE;
		EDATE = eDATE;
		STATUS = sTATUS;
		FPSID = fPSID;
		CLICK = cLICK;
		EMAIL = eMAIL;
	}
	
	
}
