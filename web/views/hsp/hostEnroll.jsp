<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String Id = (String)session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>hostEnroll</title>
<style type="text/css">

</style>
<script type="text/javascript">
	function hostEnroll(){	
		if( $("#pnumber").val()=="" || $("#hostprefergender").val()=="선택" || $("#sleeping").val()=="" 
				|| $("#hostcity").val()=="" ){
			alert("빈칸 확인해주세요.");
			return false;
		}
	}
</script>
</head>
<body>
	<div>
		<form action="/hifive/hostenroll?userid=<%=Id %>" method="post">
			<table border="0">			
			<tr ><td>
				<div class="input-group mb-3" >
					<div class="input-group-prepend">
						&nbsp;&nbsp;&nbsp;
						<span class="input-group-text" id="pnums">* 인원</span>
					</div>
					<input type="number" placeholder="0" max="10" class="form-control" id="pnumber" name="num">
				</div>
				</td>
				<td>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span class="input-group-text" id="preferG">* 성별</span>
					</div>
					<select class="custom-select" id="hostprefergender" name="gender">
						<option selected>선택</option>
						<option value="M">남성</option>
						<option value="F">여성</option>
						<option value="B">상관없음</option>
					</select>
				</div>
			</div>
			</td>
			</tr>
			</table>			
			
			<div class="col input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="pnums">가능 조건</span>
					</div>
					<span class="input-group-text" style="background:white;">									                    
	                    <input type="checkbox" name="possible" id="possible" value="kids"> 아이동반 
	                &nbsp;&nbsp;	                                   
	                    <input type="checkbox" name="possible" id="possible" value="pet"> 애완동물동반
                	&nbsp;&nbsp;	                
	                    <input type="checkbox" name="possible" id="possible" value="smoking"> 흡연
	                &nbsp;&nbsp;	                 
	                    <input type="checkbox" name="possible" id="possible" value="drinking"> 음주  
					</span>
	               </div>
	               <div class="col input-group mb-3">
	               <div class="input-group-prepend">
						<span class="input-group-text">* 숙소</span>
					</div>
					<select class="custom-select" name="sleeping" id="sleeping">
                                <option value="">선택</option>
                              <option value="living">거실</option>
                              <option value="single">단독 방</option>
                              <option value="sharing">공용 방</option>
                              <option value="sofa">소파</option>
                           </select> 
                     </div>
                     <div class="col input-group mb-3">
	               <div class="input-group-prepend">
						<span class="input-group-text">* 주소</span>
					</div>
					<input type="text" class="form-control" id="hostcity" name="hostcity"> &nbsp;
                     <input type="button" class="btn btn-outline-dark text-dark" value="입력"><br>
				               
                     </div>  
                   <div class="col input-group mb-3">
	               <div class="input-group-prepend">
						<span class="input-group-text">기타 사항</span>
					</div>
					<textarea class="form-control" name="hostetc" id="hostetc" rows="5" cols="60"></textarea>

                           </div> 
					<button type="submit" class="btn btn-primary" id="hostenrollbtn" onclick="return hostEnroll();">등록하기</button>

		</form>
	</div>
	 
</body>
</html>
