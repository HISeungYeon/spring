<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/resources/sbadmin2/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
   $("[name='show']").on("change",function(){
      let val = $(this).val();
      location.href="<%=request.getContextPath()%>/board/list?currentPage=1&show="+val;
   });
});
</script>
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">Member List</h6>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
				<form name="frm" id="frm" action="/board/list" method="get">
					<div class="row">
						<div class="col-sm-12 col-md-6">
							<div class="dataTables_length" id="dataTable_length">
								<label>Show <select id="show" name="show"
	                        aria-controls="dataTable"
	                        class="custom-select custom-select-sm form-control form-control-sm"><option
	                              value="10"
	                              <c:if test='${param.show eq 10}'>selected</c:if>
	                              >10</option>
	                           <option value="25"
	                              <c:if test='${param.show eq 25}'>selected</c:if>
	                           >25</option>
	                           <option value="50"
	                              <c:if test='${param.show eq 50}'>selected</c:if>
	                           >50</option>
	                           <option value="100"
	                              <c:if test='${param.show eq 100}'>selected</c:if>
	                           >100</option></select> entries
								</label>
							</div>
						</div>
						<div class="col-sm-12 col-md-6">
							<div id="dataTable_filter" class="dataTables_filter">
								<label>
									<input type="search" name="keyword"
									class="form-control form-control-sm"
									placeholder="검색어를 입력하세요"
									aria-controls="dataTable"
									value="${param.keyword}" />
								</label>
								<label>
									<button type="submit" class="btn btn-primary btn-icon-split btn-sm">
	                                        <span class="text">통합검색</span>
	                                </button>
								</label>
							</div>
						</div>
					</div>
				</form>
				<div class="row">
					<div class="col-sm-12">
						<table class="table table-bordered dataTable" id="dataTable"
							width="100%" cellspacing="0" role="grid"
							aria-describedby="dataTable_info" style="width: 100%;">
							<thead>
								<tr role="row">
									<th class="sorting sorting_asc" tabindex="0"
										aria-controls="dataTable" rowspan="1" colspan="1"
										aria-sort="ascending"
										aria-label="Name: activate to sort column descending"
										style="width: 20px;">ID</th>
									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="Position: activate to sort column ascending"
										style="width: 20px;">NAME</th>
									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="Office: activate to sort column ascending"
										style="width: 20px;">JOB</th>
									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="Age: activate to sort column ascending"
										style="width: 35px;">LIKE</th>
									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="Salary: activate to sort column ascending"
										style="width: 35px;">MILEAGE</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th rowspan="1" colspan="1">ID</th>
									<th rowspan="1" colspan="1">NAME</th>
									<th rowspan="1" colspan="1">JOB</th>
									<th rowspan="1" colspan="1">LIKE</th>
									<th rowspan="1" colspan="1">MILEAGE</th>
								</tr>
							</tfoot>
							<tbody>
							<!-- 
								befor = > data : List<MemberMineVO> list / memberList : MemberMineVO
								페이징 처리 after => data : ArticlePage이므로
												data.content를 해야지만 List<MemberMineVO> list가 나옴
								
								stat.cunt : 행번호(1,2,3,4,5..)
								stat.index : 인덱스번호(0,1,2,3,4,5,..)
							 -->
							<c:forEach var="memberList" items="${data.content}" varStatus="stat">
								<c:if test="${stat.count%2!=0}">
									<tr class="odd" style="background-color:aliceblue;">
								</c:if>
								<c:if test="${stat.count%2==0}">
									<tr class="even">
								</c:if>
									<td class="sorting_1">${memberList.memId}</td>
									<td><a href="/board/detail?memId=${memberList.memId}">${memberList.memName}</a></td>
									<td>${memberList.memJob}</td>
									<td>${memberList.memLike}</td>
									<td>${memberList.memMileage}</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 col-md-5">
						<div class="dataTables_info" id="dataTable_info" role="status"
							aria-live="polite">
							<!-- /board/list를 수정해보쟈 -->
							<c:if test="${param.show==null}">
								<c:set var="show" value="10" />
							</c:if>
							<c:if test="${param.show!=null}">
								<c:set var="show" value="${param.show}" />
							</c:if>
							<c:if test="${param.currentPage==null}">
								<c:set var="currentPage" value="1" />
							</c:if>
							<c:if test="${param.currentPage!=null}">
								<c:set var="currentPage" value="${param.currentPage}" />
							</c:if>
							<!-- 한 화면에 보여지는 행 수 : show -->
							<c:set var="show" value="${show}" />
							<!-- 종료행 번호 : currentPage * 10행 -->
							<c:set var="endRow" value="${currentPage * show}" />
							<!-- 시작행번호 : 종료행 번호 - (10-1) -->
							<c:set var="startRow" value="${endRow-(show-1)}" />
							<!-- showing 351 to 400 of 360 entries 보정 -->
							<c:if test="${endRow > data.total}">
								<c:set var="endRow" value="${data.total}" />
							</c:if>
							Showing ${startRow} to ${endRow} of ${data.total} entries
						</div>
					</div>
					<div class="col-sm-12 col-md-7">
						<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate" style="display:inline-block">
							<ul class="pagination">
								<li class="paginate_button page-item previous 
									<c:if test='${data.startPage lt 6}'>disabled</c:if>"
									id="dataTable_previous"><a href="/board/list?currentPage=${data.startPage-5}&show=${show}"
									aria-controls="dataTable" data-dt-idx="0" tabindex="0"
									class="page-link">Previous</a></li>
								<c:forEach var="pNo" begin="${data.startPage}" end="${data.endPage}">
								<!-- calss="....active => 현재페이지를 파랑색으로 보임 -->
									<li class="paginate_button page-item
										<c:if test='${currentPage==pNo}'>active</c:if>
									">
										<a href="/board/list?currentPage=${pNo}&show=${show}"
										aria-controls="dataTable" data-dt-idx="1" tabindex="0" 
										class="page-link">${pNo}</a></li>
								</c:forEach>
								<!-- EL 태그 정리
									== : eq(equla)
									!= : ne(not equal)
									< : lt (less than)
									> : gt (greater than)
									<= : le (less equal)
									>= : ge (greater equal)
								
								 -->
								<li class="paginate_button page-item next
									<c:if test='${data.endPage ge data.totalPages}'>disabled</c:if>" 
									id="dataTable_next">
									<a href="/board/list?currentPage=${data.startPage+5}&show=${show}" aria-controls="dataTable" data-dt-idx="3" tabindex="0"
									class="page-link">Next</a></li>
							</ul>
						</div>
						<div style="display:inline-block; float: right;">
							<a href="/board/insert" type="button" class="btn btn-primary" >회원 등록</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>