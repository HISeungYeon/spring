<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
$(function(){
	let count=1;
	$("#filePlus").on("click",function(){
		count++;
		let file = '<div class="form-group"> <input type:"text" name="attachVOList['+ count +'].filename" class="form-control form-control-user" value="첨부파일명" /> </div>'
// 		 alert("들어오나!");
		 $(".file").append(file);
	 
	});
	
	$("#fileRemove").on("click",function(){
		$(".file").remove();
	});
	
	$("#userPw").focusout(function(){
		let userPw = $(this).val();
		//기본키
		let userNo = "${param.userNo}";
		let data = {"userNo":userNo,"userPw":userPw};
		console.log("userNo : " + userNo + ", userPw : " + userPw)
		//입력한 비밀번호가 맞는지 체킹
		$.ajax({
			url: "/previews/detailPwCheck",
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify(data),
			type:"post",
			dataType:"json",
			success:function(rslt){
				console.log("rslt : " + JSON.stringify(rslt));
				let cnt = rslt.result;
				if(cnt>0){ //비밀번호 맞음
					$("#btnSubmit").removeAttr("disabled");
				}else{ //비밀번호 다름
					$("#btnSubmit").attr("disabled","disabled");
				}
			}
		});
		
	});
});


</script>
<script type="text/javascript">

//onsubmit 시 이 함수를 거쳐와랏
function fn_chk(){
	let userPw = document.getElementById("userPw").value;
	let userPwCheck = document.getElementById("userPwCheck").value;
	//비밀번호가 다를 때 보여주는 멘트
	let spanPwCheck = document.getElementById("spanPwCheck");
	
	console.log("userPw : " + userPw + ", userPwCheck : " + userPwCheck);
	
	if(userPw != userPwCheck){
		spanPwCheck.innerHTML = "비밀번호가 다릅니다." ;
		//submit안됨.
		return false;
	}
	
// 	$("#memVO").submit();
	//submit시작!
	return true;
}
</script>
<div class="container">

	<div class="card o-hidden border-0 shadow-lg my-5">
	    <div class="card-body p-0">
	        <!-- Nested Row within Card Body -->
	        <div class="row">
	        <!-- 왼쪽 대표 이미지 -->
                <div class="col-lg-5 d-none d-lg-block bg-register-image" 
                	<c:if test="${memVO.attachVOList[0].filename != null}">
                   style="background-image:url('/resources/upload${memVO.attachVOList[0].filename}');background-size:cover;"
                	</c:if>
                   ></div>
	            <div class="col-lg-7">
	                <div class="p-5">
	                    <div class="text-center">
	                        <h1 class="h4 text-gray-900 mb-4">회원 정보</h1>
	                    </div>
	                    <form:form modelAttribute="memVO" class="user" method="post"
	                    	action="/previews/updatePost" onsubmit="return fn_chk()">
	                    	<form:hidden path="userNo" />
	                        <div class="form-group row">
	                            <div class="col-sm-6 mb-3 mb-sm-0">
	                            	<!-- id="userId", name="userId" => path="userId" -->
	                                <form:input class="form-control form-control-user" path="userId" placeholder="userId" readonly="true" />
	                            	<font color="red">
	                            		<form:errors path="userId" />
	                            	</font>
	                            </div>
	                            <div class="col-sm-6">
	                                <form:input class="form-control form-control-user" path="userName" placeholder="userName" readonly="true" />
	                            	<font color="red">
	                            		<form:errors path="userName" />
	                            	</font>
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <form:input class="form-control form-control-user" path="userEmail" placeholder="Email Address" readonly="true" />
	                        	<font color="red">
	                            		<form:errors path="userEmail" />
	                            </font>
	                        </div>
	                        <div class="form-group">
	                            <form:input class="form-control form-control-user" path="updDate" placeholder="변경일자" readonly="true" />
	                        	<font color="red">
	                            		<form:errors path="updDate" />
	                            </font>
	                        </div>
	                        <!-- 일반 모드 시작 -->
	                        <div id="spn1">
		                        <div class="form-group">
		                        	<c:forEach var="attachVO" items="${memVO.attachVOList}">
			                        	<c:set var="filename" value="${attachVO.filename}" />
		                                <c:set var="filenameLen" value="${fn:length(filename)}" />
		                                <img src="/resources/upload${fn:substring(filename,0,12)}s_${fn:substring(filename,12,filenameLen)}" />
		                            </c:forEach>
		                            <form:input class="form-control form-control-user" path="attachVOList[0].filename" placeholder="첨부파일명" />
		                        	<font color="red">
		                            		<form:errors path="attachVOList[0].filename" />
		                            </font>
			                    </div>
	                        	<p>
	                        		<button type="button" id="edit" class="btn btn-outline-warning btn-user btn-block"
	                        			style="width:50%;float:left;">
			                        	수정
			                        </button>
	                        		<button type="button" id="delete" class="btn btn-outline-danger btn-user btn-block"
	                        			style="width:50%">
			                        	삭제
			                        </button>
	                        	</p>
	                        	<p>
	                        		<a href="/previews/list" type="button" class="btn btn-primary btn-user btn-block">
			                        	목록
			                        </a>
	                        	</p>
	                        </div>
	                        <!-- 일반 모드 끝 -->
	                        
	                        <!-- 수정 모드 시작 -->
	                        <div id="spn2" style="display:none;">
	                        <!-- 첨부파일 추가 -->
	                        <button type="button" id="filePlus" class="btn btn-success btn-circle">
                                        <i class="fas fa-check"></i>
                            </button>
                            <!-- 첨부파일 제거 -->
                            <button type="button" id="fileRemove" class="btn btn-danger btn-circle">
                                        <i class="fas fa-trash"></i>
                             </button>
		                        <div class="form-group">
		                        <!-- attachVOList : List<AttachVO>
		                        	attachVOList[0] : AttachVO
		                         -->
		                            <form:input class="form-control form-control-user" path="attachVOList[0].filename" placeholder="첨부파일명" />
		                        	<font color="red">
		                            		<form:errors path="attachVOList[0].filename" />
		                            </font>
		                        </div>
                            <div class="file">
	                        </div>
	                        <div class="form-group row">
	                            <div class="col-sm-6 mb-3 mb-sm-0">
	                                <form:password class="form-control form-control-user" path="userPw" placeholder="Password" />
	                            	<font color="red">
	                            		<form:errors path="userPw" />
	                            	</font>
	                            </div>
	                            <div class="col-sm-6">
	                                <input type="password" class="form-control form-control-user" id="userPwCheck" placeholder="Repeat Password">
	                            	<font color="red">
	                            		<span id="spanPwCheck"></span>
	                            	</font>
	                            </div>
	                        </div>
	                        <button type="submit" id="btnSubmit" class="btn btn-outline-primary btn-user btn-block" disabled >
	                        	확인
	                        </button>
	                        <a href="/previews/detail?userNo=${param.userNo}" type="button" class="btn btn-outline-danger btn-user btn-block">
	                    		취소
	                    	</a>
	                    	</div>
	                    	<!-- 수정 모드 끝 -->
	                    </form:form>
                    	<hr>
                    	<p /><p /><p /><p /><p /><p /><br /><br /><br /><br />
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$("#edit").on("click", function(){
		//일반모드는 가림
		$("#spn1").css("display", "none");
		//수정모드는 보임
		$("#spn2").css("display", "block");
		//입력란 활성화
		$(".form-control-user").removeAttr("readonly");
		$("img").remove();
		$("#userId").attr("readonly", "true");
		
	});
	
	$("#delete").on("click", function(){
		$("#memVO").attr("action", "/previews/deletePost");
		
		//true(1) / false(0)
		let result = confirm("ㅇㅁㅇ삭제할까욤?");
		
		console.log("result : " + result);
		
		if(result>0){
			$("#memVO").submit();
		}else{
			alert("삭제 취소 ! ^O^")
		}
		
	});
	
});

</script>









