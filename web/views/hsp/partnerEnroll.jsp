<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String user_id = (String)session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>surferEnroll</title>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	function partnerEnroll(){		
		var today = new Date();
		var sDate = new Date($("#startdateP").val());
		var eDate = new Date($("#enddateP").val());
		
		if($("#destinationP").val()=="" || $("#startdateP").val()=="" || $("#enddateP").val()=="" || $("#numberP").val()==""){
			alert("빈칸을 확인해주세요.");
			return false;
		}		
		if((sDate > eDate) || (today > sDate)){
			alert("날짜를 확인해주세요.");
			return false;
		}
		if($("#numberP").val()<=0) {
			alert("인원수를 확인해주세요.");
			return false;
		}
	}
	
	function sample5_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수
                console.log(data);
                console.log(data.sido+data.sigungu);

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("destinationP").value = fullAddr;
            }
        }).open();
    }
</script>

</head>
<div>
	<form action="/hifive/partnerenroll?userid=<%= user_id %>" method="post">
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">* 목적지</span>
			</div>
			<input type="text" class="form-control" id="destinationP" name="destination">
			<input type="button" class="btn btn-outline-dark" onclick="sample5_execDaumPostcode()" value="입력"><br>
		</div>
		<br>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">* 여행기간</span>
			</div>
			<input type="date" class="form-control" id="startdateP" name="startdate"> &nbsp; ~ &nbsp; 
			<input type="date" class="form-control" id="enddateP" name="enddate">
		</div>
		<br>
		<div class="input-group mb-3" >
			<div class="input-group-prepend">
				<span class="input-group-text">* 인원</span>
			</div>
			<input type="number" placeholder="0" max="10" class="form-control col-sm-3" id="numberP" name="num">
		</div>
		<br>
		<button type="submit" class="btn btn-primary" id="surferenrollbtn" onclick="return partnerEnroll();">등록하기</button>
	</form>
</div>
</body>
</html>