<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>  
<script type="text/javascript">
$(function(result) {
	
		$("#aSubmit").on("click", function() {
			$("#frm").submit();
		});
		
		// --아이디 중복 검사
		$('#memId').focusout(function(){
			let userId = $('#memId').val(); // input_id에 입력되는 값
//  			alert(userId);
			
			$.ajax({
				url : "/board/idCheck",
// 				contentType:"application/json;charest=utf-8",
				type : "post",
				data : {userId: userId},
				dataType : 'json',
				success : function(result){
					if(userId==""){
						$("#checkId").html('아이디를 입력해주세요. (ᓄಠ_ಠ)ᓄ  ');
						$("#checkId").attr('color','red');
						$("#checkId").focus();
						return;
					}
					if(result == 1){
						$("#checkId").html('사용할 수 없는 아이디에요.. (  ˊ࿁ˋ ) ᐝ  ');
						$("#checkId").attr('color','red');
						$("#checkId").focus();
// 						$("#aSubmit").attr("disabled", true); //설정
					} else{
						$("#checkId").html('사용할 수 있는 아이디에요 ٩(๑❛ᴗ❛๑)۶ ');
						$("#checkId").attr('color','green');
						$("#aSubmit").attr("disabled", false); //해제
					} 
				},
				error: function (request, status, error) {
			        console.log("code: " + request.status)
			        console.log("message: " + request.responseText)
			        console.log("error: " + error);
					alert("서버요청실패");
				}
			});
		});
		
		
		$('#memMail').focusout(function(){
		//이메일 검사
		  let email_rule =  /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		  let email =$("#memMail").val();
		 
		  if(!email_rule.test(email)){
			$("#checkEmail").html('이메일을 형식에 맞게 입력해주세요.');
			$("#checkEmail").attr('color','red');
			
		  }
		  if(email_rule.test(email)){
			$("#checkEmail").html('사용할 수 있는 이메일 입니다.');
			$("#checkEmail").attr('color','green');
		  }
		});
		
// 		if(email_rule.test(email) && )
		
		//이미지 미리보기 시작---------------------
		let sel_file = [];
		
		$("#memImage").on("change", handleImgFileSelect);
		//파라미터 e: onchange 이벤트 객체
		function handleImgFileSelect(e){
			//이벤트가 발생된 타겟 안에 이미지 파일들을 가져와보쟈
			let files = e.target.files;
			//이미지가 여러개가 있을 수 있으므로 이미지를 분리하여 배열로 만듦
			let fileArr = Array.prototype.slice.call(files);
			//파일 타입의 배열 반복 f : 파일 배열 안에 들어있는 각각의 이미지 파일 객체 
			fileArr.forEach(function(f){
				if(!f.type.match("image.*")){
					alert("이미지 확장자만 가능합니닷");
					// 함수 종료
					return;
				}
				//이미지 객체를 (f) 전역 배열타입 변수 (sel_file)에 넣쟈
				sel_file.push(f);
				//이미지 객체를 읽을 자바스크립트의  reader 객체 생성
				let reader = new FileReader();
				//e : reader가 이미지 객체를 읽는 이벤트
				reader.onload = function(e){
					//e.target : 이미지 객체 
					//e.target.result : reader가 이미지를 다 읽은 결과 
					let img_html = "<img src=\"" + e.target.result + "\" />";
					//div 사이에 이미지가 렌더링되어 화면에 보임
					//객체.append : 누적, .html : 새로고침, innerHTML : J/S
					$(".imgs_wrap").append(img_html);
				}
				//f : 이미지 파일 객체를 읽은 후 다음 이미지 파일(f)을 위해 초기화
				reader.readAsDataURL(f);
			});//end forEach
		}
		
		//이미지 미리보기 끝---------------------
	});
//다음 우편번호 검색
function openHomeSearch(){
// 	alert("하이루");
	
	new daum.Postcode({
		oncomplete:function(data){
			$("input[name='memAdd1']").val(data.address); //주소
			$("input[name='memAdd2']").val(data.buildingName); //상세주소
		}
	}).open();
}



</script>
<div class="card shadow mb-4">
	<!-- Card Header - Accordion -->
	<a href="#collapseCardExample" class="d-block card-header py-3"
		data-toggle="collapse" role="button" aria-expanded="true"
		aria-controls="collapseCardExample">
		<h6 class="m-0 font-weight-bold text-primary">Collapsable Card
			Example</h6>
	</a>
	<!-- Card Content - Collapse -->
	<div class="collapse show" id="collapseCardExample">
		<div class="card-body">
			<form id="frm" action="/board/insert" method="post" enctype="multipart/form-data">
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">아이디</label>
					<input type="text" class="form-control" name="memId" id="memId"
						placeholder="memId" value="hi" required />
					<font id = "checkId" size = "2"></font>
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">비밀번호</label>
					<input type="text" class="form-control" name="memPass"
						id="memPass" placeholder="memPass" value="asdf" required />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">이름</label>
					<input type="text" class="form-control" name="memName"
						id="memName" placeholder="memName" value="이승돌" required />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">이메일</label>
					<input type="text" class="form-control" name="memMail"
						id="memMail" placeholder="memMail" value="asdf@naver.com" required />
					<font id = "checkEmail" size = "2"></font>
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">생년월일</label>
					<input type="date" class="form-control" name="memBir" id="memBir"
						placeholder="memBir" value="2022-11-01" required />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">주소</label>
					<div class="p-5">
	                    <div class="form-group">
	                        <input type="email" class="form-control form-control-user" value="하이동" name="memAdd1" id="memAdd1" placeholder="memAdd1" />
	                    </div>
	                    <div class="form-group">
	                        <input type="email" class="form-control form-control-user" value="룰루루루루" name="memAdd2" id="memAdd2" placeholder="memAdd2" />
	                    </div>
	                    <div class="form-group row">
	                        <a href="#" class="btn btn-info btn-icon-split" onclick="openHomeSearch()">
                                 <span class="icon text-white-50">
                                     <i class="fas fa-info-circle"></i>
                                 </span>
                                 <span class="text">주소검색</span>
                             </a>
	                    </div>
                     </div>
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">직업</label>
					<input type="text" class="form-control" name="memJob"
						id="memJob" placeholder="memJob" value="학생" required />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">취미</label>
					<p>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="checkbox" name="memLike" id="memLike1" value="footsal">
						  <label class="form-check-label" for="like1">footsal</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="checkbox" name="memLike" id="memLike2" value="movie" checked >
						  <label class="form-check-label" for="like2">movie</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="checkbox" name="memLike" id="memLike3" value="drama" >
						  <label class="form-check-label" for="like3">drama</label>
						</div>
					</p>
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">마일리지</label>
					<input type="text" class="form-control" name="memMileage" id="memMileage"
						placeholder="memMileage" value="1000" required />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">증명사진</label>
					<input class="form-control" type="file" name="memImage" id="memImage"
						placeholder="memImage" multiple>
				</div>
				<div class="mb-3">
					<div class="imgs_wrap">
					</div>
				</div>
				
				<div class="mb-3">
					<button id="aSubmit" class="btn btn-primary btn-icon-split" disabled>
						<span class="icon text-white-50"> <i class="fas fa-flag"></i>
					</span> <span class="text">등록</span>
					</button>
				</div>
			</form>
		</div>
	</div>
</div>