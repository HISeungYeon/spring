package kr.or.ddit.vo;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

//자바빈 클래스
public class MemberMineVO {
	
	private static final long serialVersionUID =
			-4274700572038677000L;
	
	private String memId;
	private String memPass;
	private String memName;
	//생일(기본 : 2022/11/01 => 변경 2022-11-01)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date memBir;
	private String memAdd1;
	private String memAdd2;
	private String memMail;
	private String memJob;
	private String memLike;
	private int memMileage;
	
	private String fileName = "null";
	private MultipartFile[] memImage;
	
	public MemberMineVO() {}
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public MultipartFile[] getMemImage() {
		return memImage;
	}



	public void setMemImage(MultipartFile[] memImage) {
		this.memImage = memImage;
	}



	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPass() {
		return memPass;
	}

	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public Date getMemBir() {
		return memBir;
	}

	public void setMemBir(Date memBir) {
		this.memBir = memBir;
	}

	public String getMemAdd1() {
		return memAdd1;
	}

	public void setMemAdd1(String memAdd1) {
		this.memAdd1 = memAdd1;
	}

	public String getMemAdd2() {
		return memAdd2;
	}

	public void setMemAdd2(String memAdd2) {
		this.memAdd2 = memAdd2;
	}

	public String getMemMail() {
		return memMail;
	}

	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}

	public String getMemJob() {
		return memJob;
	}

	public void setMemJob(String memJob) {
		this.memJob = memJob;
	}

	public String getMemLike() {
		return memLike;
	}

	public void setMemLike(String memLike) {
		this.memLike = memLike;
	}

	public int getMemMileage() {
		return memMileage;
	}

	public void setMemMileage(int memMileage) {
		this.memMileage = memMileage;
	}


	@Override
	public String toString() {
		return "MemberMineVO [memId=" + memId + ", memPass=" + memPass + ", memName=" + memName + ", memBir=" + memBir
				+ ", memAdd1=" + memAdd1 + ", memAdd2=" + memAdd2 + ", memMail=" + memMail + ", memJob=" + memJob
				+ ", memLike=" + memLike + ", memMileage=" + memMileage + ", fileName=" + fileName + ", memImage="
				+ Arrays.toString(memImage) + "]";
	}



	
}
