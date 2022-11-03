package kr.or.ddit.vo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

//자바빈 클래스
public class MemberVO {
	//회원 아이디
	private String userId = "hi";
	//비밀번호
	private String password = "java";
	//보유 코인
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
	
	private MultipartFile picture;
	private MultipartFile picture2;
	// pictureList[0]
	private List<MultipartFile> pictureList;
	
	private MultipartFile[] pictureArray;


	public MemberVO() {}

	
	public MultipartFile[] getPictureArray() {
		return pictureArray;
	}


	public void setPictureArray(MultipartFile[] pictureArray) {
		this.pictureArray = pictureArray;
	}


	public List<MultipartFile> getPictureList() {
		return pictureList;
	}




	public void setPictureList(List<MultipartFile> pictureList) {
		this.pictureList = pictureList;
	}




	public MultipartFile getPicture2() {
		return picture2;
	}




	public void setPicture2(MultipartFile picture2) {
		this.picture2 = picture2;
	}




	public MultipartFile getPicture() {
		return picture;
	}


	public void setPicture(MultipartFile picture) {
		this.picture = picture;
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


	public boolean isMarriaged() {
		return marriaged;
	}



	public void setMarriaged(boolean marriaged) {
		this.marriaged = marriaged;
	}



	public String[] getHobbyList() {
		return hobbyList;
	}

	public void setHobbyList(String[] hobbyList) {
		this.hobbyList = hobbyList;
	}

	public String[] getCars() {
		return cars;
	}


	public void setCars(String[] cars) {
		this.cars = cars;
	}
	
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}


	public String getCar() {
		return car;
	}


	public void setCar(String car) {
		this.car = car;
	}


	public String getHobby() {
		return hobby;
	}


	public void setHobby(String hobby) {
		this.hobby = hobby;
	}


	@Override
	public String toString() {
		return "MemberVO [userId=" + userId + ", password=" + password + ", coin=" + coin + ", birth=" + birth
				+ ", gender=" + gender + ", nationality=" + nationality + ", cars=" + Arrays.toString(cars) + ", car="
				+ car + ", hobbyList=" + Arrays.toString(hobbyList) + ", hobby=" + hobby + ", marriaged=" + marriaged
				+ ", addressVO=" + addressVO + ", cardVOList=" + cardVOList + ", picture=" + picture + ", picture2="
				+ picture2 + ", pictureList=" + pictureList + ", pictureArray=" + Arrays.toString(pictureArray) + "]";
	}





}
