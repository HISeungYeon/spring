package kr.or.ddit.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CardVO {
	
	private String userId;
	
	private String no;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date validMonth;

	public CardVO() {}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Date getValidMonth() {
		return validMonth;
	}

	public void setValidMonth(Date validMonth) {
		this.validMonth = validMonth;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "CardVO [userId=" + userId + ", no=" + no + ", validMonth=" + validMonth + "]";
	}

	
	
}
