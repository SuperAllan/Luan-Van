package vn.com.luanvan.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UIUsecaseId implements java.io.Serializable {
	private int uiid;
	private int usecaseid;
	public UIUsecaseId(){}
	@Column(name = "uiid", nullable = false)
	public int getUiid() {
		return uiid;
	}
	public void setUiid(int uiid) {
		this.uiid = uiid;
	}
	@Column(name = "USECASEID", nullable = false)
	public int getUsecaseid() {
		return usecaseid;
	}
	public void setUsecaseid(int usecaseid) {
		this.usecaseid = usecaseid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + uiid;
		result = prime * result + usecaseid;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UIUsecaseId other = (UIUsecaseId) obj;
		if (uiid != other.uiid)
			return false;
		if (usecaseid != other.usecaseid)
			return false;
		return true;
	}
}
