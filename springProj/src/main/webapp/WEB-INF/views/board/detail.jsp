<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>  
<script type="text/javascript">
$(function() {

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
						placeholder="memId" value="${memberMineVO.memId}" readonly />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">비밀번호</label>
					<input type="text" class="form-control" name="memPass"
						id="memPass" placeholder="memPass" value="${memberMineVO.memPass}" readonly />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">이름</label>
					<input type="text" class="form-control" name="memName"
						id="memName" placeholder="memName" value="${memberMineVO.memName}" readonly />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">이메일</label>
					<input type="text" class="form-control" name="memMail"
						id="memMail" placeholder="memMail" value="${memberMineVO.memMail}" readonly />
					<font id = "checkEmail" size = "2"></font>
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">생년월일</label>
					<input type="text" class="form-control" name="memBir" id="memBir"
						placeholder="memBir" value="${memberMineVO.memBir}" readonly />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">주소</label>
					<div class="p-5">
	                    <div class="form-group">
	                        <input type="email" class="form-control form-control-user" value="${memberMineVO.memAdd1}" name="memAdd1" id="memAdd1" placeholder="memAdd1" />
	                    </div>
	                    <div class="form-group">
	                        <input type="email" class="form-control form-control-user" value="${memberMineVO.memAdd2}" name="memAdd2" id="memAdd2" placeholder="memAdd2" />
	                    </div>
                     </div>
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">직업</label>
					<input type="text" class="form-control" name="memJob"
						id="memJob" placeholder="memJob" value="${memberMineVO.memJob}" readonly />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">마일리지</label>
					<input type="text" class="form-control" name="memMileage" id="memMileage"
						placeholder="memMileage" value="${memberMineVO.memMileage}" readonly />
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">증명사진</label>
					<input class="form-control" type="file" name="memImage" id="memImage"
						placeholder="memImage" style="display:none;" multiple>
				</div>
				<div class="mb-3">
					<div class="imgs_wrap">
						<!-- MemberMineController에서 직접 가져옴 -->
						<c:forEach var="attachVO" items="${attachVOList}">
							<img src="/resources/upload${attachVO.attachName}" style="width:300px"/>
						</c:forEach>
						<hr />
						<!-- MemberMineVO에서 가져옴 -->
						<c:forEach var="attachVO" items="${memberMineVO.attachVOList}">
							<img src="/resources/upload${attachVO.attachName}" style="width:300px"/>
						</c:forEach>
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