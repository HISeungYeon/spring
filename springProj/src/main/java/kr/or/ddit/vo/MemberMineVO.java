package kr.or.ddit.vo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

//자바빈 클래스
public class MemberMineVO {
	
	
	private static final long serialVersionUID =
			-4274700572038677000L;
	//필수 입력
	@NotBlank
	private String memId;
	
	//필수 입력 + 최대 3글자 까지 허용
	@NotBlank
	@Size(max=3)
	private String memName;
	private String memPass;
	//자기 소개
	private String introduction;
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
	
	private int coin = 100;
	
	//생일(기본 : 2022/11/01 => 변경 2022-11-01)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birth;
	
	//성별
	private String gender;
	
	//국적
	private String nationality;
	
	//보유 자동차
	private String[] cars;
	private String car;
	
	//취미
	private String[] hobbyList;
	private String hobby;
	
	//결혼 유무
	private boolean marriaged;
	
	//중첩된 자바빈 (1:1)
	private AddressVO addressVO;
	
	//중첩된 자바빈(1:N)
	private List<CardVO> cardVOList;
	
	//개발자? ( Y or null)
	private String developer; 
	//외국인? (boolean형이 훨씬 깔끔)
	private boolean foreigner;
	
	private List<AttachVO> attachVOList;
	
	public MemberMineVO() {}

	
	public List<AttachVO> getAttachVOList() {
		return attachVOList;
	}




	public void setAttachVOList(List<AttachVO> attachVOList) {
		this.attachVOList = attachVOList;
	}




	public String getDeveloper() {
		return developer;
	}


	public void setDeveloper(String developer) {
		this.developer = developer;
	}


	public boolean isForeigner() {
		return foreigner;
	}


	public void setForeigner(boolean foreigner) {
		this.foreigner = foreigner;
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


	public String getIntroduction() {
		return introduction;
	}


	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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


	public int getCoin() {
		return coin;
	}


	public void setCoin(int coin) {
		this.coin = coin;
	}


	public Date getBirth() {
		return birth;
	}


	public void setBirth(Date birth) {
		this.birth = birth;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String[] getCars() {
		return cars;
	}


	public void setCars(String[] cars) {
		this.cars = cars;
	}


	public String getCar() {
		return car;
	}


	public void setCar(String car) {
		this.car = car;
	}


	public String[] getHobbyList() {
		return hobbyList;
	}


	public void setHobbyList(String[] hobbyList) {
		this.hobbyList = hobbyList;
	}


	public String getHobby() {
		return hobby;
	}


	public void setHobby(String hobby) {
		this.hobby = hobby;
	}


	public boolean isMarriaged() {
		return marriaged;
	}


	public void setMarriaged(boolean marriaged) {
		this.marriaged = marriaged;
	}


	public AddressVO getAddressVO() {
		return addressVO;
	}


	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}


	public List<CardVO> getCardVOList() {
		return cardVOList;
	}


	public void setCardVOList(List<CardVO> cardVOList) {
		this.cardVOList = cardVOList;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "MemberMineVO [memId=" + memId + ", memName=" + memName + ", memPass=" + memPass + ", introduction="
				+ introduction + ", memBir=" + memBir + ", memAdd1=" + memAdd1 + ", memAdd2=" + memAdd2 + ", memMail="
				+ memMail + ", memJob=" + memJob + ", memLike=" + memLike + ", memMileage=" + memMileage + ", fileName="
				+ fileName + ", memImage=" + Arrays.toString(memImage) + ", coin=" + coin + ", birth=" + birth
				+ ", gender=" + gender + ", nationality=" + nationality + ", cars=" + Arrays.toString(cars) + ", car="
				+ car + ", hobbyList=" + Arrays.toString(hobbyList) + ", hobby=" + hobby + ", marriaged=" + marriaged
				+ ", addressVO=" + addressVO + ", cardVOList=" + cardVOList + ", developer=" + developer
				+ ", foreigner=" + foreigner + ", attachVOList=" + attachVOList + "]";
	}


	
}
