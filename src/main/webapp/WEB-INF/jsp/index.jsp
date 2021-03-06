<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Bootstrap -->
<link href="${APP_PATH }/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${APP_PATH }/bootstrap-3.3.5-dist/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<script src="${APP_PATH }/bootstrap-3.3.5-dist/js/jquery-3.0.0.min.js"></script>
<script src="${APP_PATH }/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="${APP_PATH }/bootstrap-3.3.5-dist/js/bootstrap-datetimepicker.min.js"></script>
<script src="${APP_PATH }/bootstrap-3.3.5-dist/js/bootstrap-datetimepicker.zh-CN.js"></script>
<style type="text/css">
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
}
a:active {
	text-decoration: none;
}
.nav .open>a, .nav .open>a:focus, .nav .open>a:hover {
	background-color: #579ec8;
}
.nav>li>a:focus, .nav>li>a:hover {
	background-color: #579ec8;
}
.topscan {
	width: 100%;
	min-height: 50px;
	background-color: #438eb9;
	position: relative;
}
.top-left {
	color: #00040c;
	line-height: 20px;
	padding: 15px 10px;
	float: left;
}
.user-click {
	float: right;
	padding-right: 20px;
}
.leftscan a:hover {
	color: #1963aa;
	background-color: white;
	display: block;
	background-color: white;
}
.leftscan a {
	color: #585858;
	display: block;
	height: 38px;
	line-height: 36px;
	display: block;
}
.leftscan li {
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	border-left: 1px solid #ccc;
	border-right: hidden;
}
body {
	width: 100%;
	height: 100%;
	margin: 0;
	padding: 0;
	background-color: #e4e6e9;
}
ul, li {
	margin: 0;
	padding: 0;
	text-align: center;
}
td {
	text-align: center;
}
tr {
	text-align: center;
}
th {
	text-align: center;
}
</style>
</head>
<body>
	<div class="topscan">
		<div class="top-left">?????????????????????</div>
		<input id="role" name="role" value="${sessionScope.user.role }" hidden="hidden"/>
		<div class="user-click">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" style="color: white;"> welcome,
						${sessionScope.user.name } <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${APP_PATH }/index/exit">??????</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	<div>
		<div class="leftscan"
			style="width: 20%; min-height: 100%; float: left; background-color: #eee;">
			<ul style="list-style: none; color: #1963aa;">
				<c:if test="${sessionScope.user.role!=3 }">
				<li><a href="${APP_PATH }/index/toindex?tag=0">???????????????</a></li>
				</c:if>
				<li><a id="depotcardIndex" href="${APP_PATH }/index/toDepotcardIndex" target="main"
					onclick="$('div#main').load(this.href);return false;">???????????????</a></li>
<%--				<li><a href="${APP_PATH }/index/findAllCoupon" target="main"--%>
<%--					onclick="$('div#main').load(this.href);return false;">???????????????</a></li>--%>
<%--				<li><a href="${APP_PATH }/index/findAllEmail" target="main"--%>
<%--					onclick="$('div#main').load(this.href);return false;">????????????</a></li>--%>
				<li><a href="${APP_PATH }/index/findAllIllegalinfo" target="main"
					onclick="$('div#main').load(this.href);return false;">????????????</a></li>
				<c:if test="${sessionScope.user.role==1 }">
				<li><a href="${APP_PATH }/index/findAllIncome" target="main"
					onclick="$('div#main').load(this.href);return false;">????????????</a></li>
				</c:if>
				<li><a href="${APP_PATH }/index/findAllUser" target="main"
					onclick="$('div#main').load(this.href);return false;">????????????</a></li>
				<li><a href="${APP_PATH }/index/findAllDepot" target="main"
					onclick="$('div#main').load(this.href);return false;">????????????</a></li>
				<c:if test="${sessionScope.user.role==1 }">
				<li><a href="${APP_PATH }/index/system" target="main"
					onclick="$('div#main').load(this.href);return false;">????????????</a></li>
				</c:if>
			</ul>
		</div>
		<div id="main"
			style="float: left; width: 80%; min-height: 100%; margin-right: 0; border: 1px solid #ccc; background-color: white;">

		<c:if test="${sessionScope.user.role!=3 }">
			<table class="table">
				<caption>
					<div style="float: left; line-height: 10px; padding: 10px 10px;">???????????????</div>
					<div class="col-lg-6" style="width: 30%; float: left;">
						<div class="input-group">
							<input id="inputcardnum" placeholder="???????????????/?????????" type="text" class="form-control">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button" onclick="checkOutByCardnum()">??????</button>
							</span>
						</div>
						<!-- /input-group -->
					</div>

					<div class="dropdown" style="float: right; margin-right: 10%">
						<button type="button" class="btn dropdown-toggle"
							id="dropdownMenu1" data-toggle="dropdown">
							???????????? <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu"
							aria-labelledby="dropdownMenu1">
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="${APP_PATH }/index/toindex?tag=0">????????????</a></li>
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="${APP_PATH }/index/toindex?tag=1">????????????</a></li>
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="${APP_PATH }/index/toindex?tag=2">????????????</a></li>
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="${APP_PATH }/index/toindex?tag=3">????????????</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1"
								href="${APP_PATH }/index/toindex?tag=4">????????????</a></li>
						</ul>
					</div>

				</caption>
				<tr>
					<th>?????????</th>
					<th>??????</th>
					<th>??????</th>
					<th>??????</th>
					<th>??????</th>
				</tr>
				<c:forEach items="${parkspaces.pages }" var="item">
					<tr>
						<td>${item.parkid }</td>
						<td>${item.status==0?"???":"??????" }</td>
						<td>${item.tag==1?"????????????":item.tag==2?"????????????":"????????????" }</td>
						<td><c:if test="${item.status==0 }">
								<input class="btn btn-default" type="button"
									onclick="checkIn(${item.parkid},${item.id })" value="??????">
							</c:if> <c:if test="${item.status!=0 }">
								<input onclick="checkOut(${item.parkid})"
									class="btn btn-default" type="button" value="??????"/>
								<input onclick="addIllegal(${item.parkid})"
									class="btn btn-default" type="button" value="??????"/>
							</c:if>
							</td>
						<td><input class="btn btn-default" type="button" onclick="checkDetail(${item.parkid})" value="??????"></td>
					</tr>
				</c:forEach>
			</table>
			<ul class="pagination">
				
				<li><a href="${APP_PATH }/index/toindex?tag=${parkspaces.tag}&&page=${parkspaces.current}">&laquo;</a></li>
				<li><a href="${APP_PATH }/index/toindex?tag=${parkspaces.tag}&&page=${parkspaces.current+1}">${parkspaces.current+1}</a></li>
				<c:if test="${parkspaces.current+1>=parkspaces.countPage}">
				<li><a href="${APP_PATH }/index/toindex?tag=${parkspaces.tag}&&page=${parkspaces.current+1}">&raquo;</a></li>
				</c:if>
				<c:if test="${parkspaces.current+1<parkspaces.countPage}">
				<li><a href="${APP_PATH }/index/toindex?tag=${parkspaces.tag}&&page=${parkspaces.current+2}">&raquo;</a></li>
				</c:if>
			</ul>
			</c:if>
		</div>
	</div>
	<!-- ????????????Modal??? -->
	<form id="checkForm">
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">??</button>
						<h4 class="modal-title" id="myModalLabel"></h4>
					</div>
					<div class="modal-body"></div>
					<div class="modal-footer">
						<button id="loseSubmit" style="display:none" type="button" class="btn btn-primary">?????????</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">??????
						</button>
						<button id="checkSubmit" type="button" class="btn btn-primary">????????????</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
	</form>
	<div id="payForm">
		<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">??</button>
						<h4 class="modal-title" id="myModalLabel">
						<button type="button" onclick="pay_zfb()" style="width: 30%" class="btn btn-default" >?????????
						</button>
						<button type="button" onclick="pay_wx()" style="width: 30%" class="btn btn-default" >??????
						</button>
						<button type="button" onclick="pay_cash()" style="width: 30%" class="btn btn-default" >??????
						</button>
						</h4>
					</div>
					<div class="modal-body1">
					<div id="pay_zfb">
					<h4>????????????<span id="zfb_text"></span>???</h4>
					<img alt="" style="width: 300px;height: 300px;margin-left: 20%" src="${APP_PATH }/pic/zfb.png">
					</div>
					<div id="pay_wx" hidden="hidden">
					<h4>?????????<span id="wx_text"></span>???</h4>
					<img alt="" style="width: 300px;height: 300px;margin-left: 20%" src="${APP_PATH }/pic/wx.png">
					</div>
					<div id="pay_cash" hidden="hidden">
					<h4>?????????<span id="cash_text"></span>???</h4>
					</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">??????
						</button>
						<button id="paySubmit" type="button" onclick="" class="btn btn-primary">??????</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
		</div>
	</div>
	<!-- /.modal -->
</body>
<script type="text/javascript">
$(document).ready(function(){
	var role=$("#role").val();
	if(role==3)
		{
			$("#depotcardIndex").click();
		}
	}); 
	$('#myModal').on('hidden.bs.modal', function () {
		$("#checkSubmit").show();
		$(".modal-body").empty();
		$("#loseSubmit").hide();
	})
	$('#myModal1').on('hidden.bs.modal', function () {
		$("#pay_zfb").show();
		$("#pay_cash").hide();
		$("#pay_wx").hide();
		$("#loseSubmit").hide();
	})
	/* ?????????????????????*/
	function checkIn(parknum,id) {
	    var tem=0;
        $.ajax({
            type: 'post',
            url: '/depot-system/index/depot/checkTem',
            async:false,
            success: function (data) {
                if(data.code==100)
                {
                    tem=1;
                }
            }
        })

		var html = "<input id=\"id\" name=\"id\" value=\""+id+"\" hidden=\"hidden\"/>"
				+"<input id=\"parkNum\" name=\"parkNum\" value=\""+parknum+"\" hidden=\"hidden\"/><label>???????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input id=\"cardNum\" name=\"cardNum\" placeholder=\"???????????????\" type=\"text\" class=\"form-control\">"
				+ "</div>"
				+ "</div>"
				+ "<label>????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input id=\"carNum\" name=\"carNum\" placeholder=\"??????????????????\" type=\"text\" class=\"form-control\">"
				+ "</div></div>"
				+ "<label>?????????????????????</label>"
				+"<select onchange=\"changeParkTem()\" id=\"parkTem\" name=\"parkTem\" style=\"width:100px\" class=\"form-control\"> "
				+"<option value=\"0\">???</option>";
                if(tem==1)
                {
                    html+="<option value=\"1\">???</option>";
                }
                html+="</select>";
		$("#myModalLabel").html("????????????");
		$("#checkSubmit").html("??????");
		$("#checkSubmit").attr("onclick","checkInSubmit()");
		$(".modal-body").append(html);
		$("#myModal").modal('show');
	}
	function changeParkTem(){
		var parkTem=$("#parkTem").val();
		if(parkTem==1)
			{
			$("#cardNum").val('');
			$('#cardNum').attr("readonly",true);
			}
		else{
			$('#cardNum').attr("readonly",false);
		}
	}
	/* ???????????? */
	function checkInSubmit(){
		var parkTem=$("#parkTem option:selected").val();
		var carNum=$("#carNum").val();
		var cardNum=$("#cardNum").val();
		if(parkTem==0)
			{
			if(cardNum.trim()=='')
				{
				alert("???????????????!");
				return false;
				}
			}
		if(parkTem==1&&cardNum.trim()!='')
			{
				alert("??????????????????????????????!");
			}
		if(carNum.trim()=='')
			{
				alert("??????????????????!");
				return false;
			}
		$.ajax({
			type:'post',
			url:'/depot-system/index/check/checkIn',
			datatype:'text',
			data:$("#checkForm").serializeArray(),
			contentType:'application/x-www-form-urlencoded',
			success:function(data){
				if(data.code==100)
					{
					alert("???????????????");
				$("#myModal").modal('hide');
				window.location.href="/depot-system/index/toindex";
					}else{
						alert(data.extend.va_msg);
					}
			}
		})
	}
	/* ????????????????????? */
	function checkOut(parknum) {
		 $.ajax({
			type:'get',
			url:'/depot-system/index/check/findParkinfoByParknum',
			datatype:'json',
			data:{parkNum:parknum},
			success:function(data){
				debugger;
				if(data.code==100)
					{
				var parkTem="???";
				if(data.extend.parkInfo.parktem==1)
					{
					parkTem="???";
					}
				var html = 
				// ?????????????????????
				"<input id=\"payid\" name=\"payid\" value=\"9\" hidden=\"hidden\"/>"
				// ?????????????????? 
				+"<input id=\"pay_money\" name=\"pay_money\" value=\"0\" hidden=\"hidden\"/>"
				// ???????????????????????????????????? (0?????????1???????????????9??????)
				+"<input id=\"pay_type\" name=\"pay_type\" value=\"9\" hidden=\"hidden\"/>"
				+"<input id=\"parkNum\" name=\"parkNum\" value=\""+parknum+"\" hidden=\"hidden\"/><label>????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input id=\"parknum\" name=\"parknum\" value=\""+parknum+"\" type=\"text\" class=\"form-control\" readonly>"
				+ "</div>"
				+ "</div>"
				+"<label>???????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input id=\"cardNum\" name=\"cardNum\" value=\""+data.extend.parkInfo.cardnum+"\" type=\"text\" class=\"form-control\" readonly>"
				+ "</div>"
				+ "</div>"
				+ "<label>????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input id=\"carNum\" name=\"carNum\" value=\""+data.extend.parkInfo.carnum+"\" type=\"text\" class=\"form-control\" readonly >"
				+ "</div></div>"
				+ "<label>?????????????????????</label><br>"
				+parkTem
				$("#myModalLabel").html("????????????");
				$("#checkSubmit").html("??????");
				$("#checkSubmit").attr("onclick","ispay("+parknum+")");
				$(".modal-body").append(html);
				$("#myModal").modal('show');
					}
			}
		}) 
	}
	/*?????????????????????*/
	function pay_zfb()
	{
		$("#pay_zfb").show();
		$("#pay_cash").hide();
		$("#pay_wx").hide();
		$("#payid").val(1);
		
	}
	/*??????????????????*/
	function pay_wx()
	{
		$("#pay_cash").hide();
		$("#pay_zfb").hide();
		$("#pay_wx").show();
		$("#payid").val(2);
	}
	/*??????????????????*/
	function pay_cash()
	{
		$("#pay_wx").hide();
		$("#pay_zfb").hide();
		$("#pay_cash").show();
		$("#payid").val(0);
	}
	/*??????????????????*/
	function ispay(parknum)
	{
		 $.ajax({
			type:'post',
			url:'/depot-system/index/check/ispay',
			datatype:'json',
			data:{parknum:parknum},
			success:function(data){
				if(data.code==100)
					{
					$("#myModal1").modal('show');
					$("#zfb_text").text(data.extend.money_pay);
					$("#wx_text").text(data.extend.money_pay);
					$("#cash_text").text(data.extend.money_pay);
					$("#pay_money").val(data.extend.money_pay);
					alert(data.extend.va_msg);
					}
				else{
					alert(data.extend.money_pay);
					$("#pay_type").val(data.extend.type);
					$("#pay_money").val(data.extend.money_pay);
					if(data.extend.type==9)
						{
						alert("???????????????");
						return false;
						}
					//??????????????????
					if(data.extend.type==0)
						{
						checkOutSubmit();
						}
					//???????????????????????????
					else{
						checkOutSubmit();
					}
				}
				$("#paySubmit").attr("onclick","checkOutSubmit()");
			}
		}) 
	}
	/* ???????????? */
	function checkOutSubmit(){
		$.ajax({
			type:'post',
			url:'/depot-system/index/check/checkOut',
			datatype:'text',
			data:$("#checkForm").serializeArray(),
			contentType:'application/x-www-form-urlencoded',
			success:function(data){
				$("#myModal").modal('hide');
				window.location.href="/depot-system/index/toindex";
			}
		})
	}
	
	/* ????????????/?????????????????????????????? */
	function checkOutByCardnum() {
		var cardnum=$("#inputcardnum").val();
		if(cardnum=="")
			{
			alert("??????????????????!");
			return false;
			}
		 $.ajax({
			type:'get',
			url:'/depot-system/index/check/findParkinfoByCardnum',
			datatype:'json',
			data:{cardnum:cardnum},
			success:function(data){
				debugger;
				if(data.code==100)
					{
				var parkTem="???";
				if(data.extend.parkInfo.parktem==1)
					{
					parkTem="???";
					}
				var html = "<input id=\"parkNum\" name=\"parkNum\" value=\""+data.extend.parkInfo.parknum+"\" hidden=\"hidden\"/><label>????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input readonly id=\"parkNum\" name=\"parkNum\" value=\""+data.extend.parkInfo.parknum+"\" type=\"text\" class=\"form-control\">"
				+ "</div>"
				+ "</div>"
				+"<label>???????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input readonly id=\"cardNum\" name=\"cardNum\" value=\""+data.extend.parkInfo.cardnum+"\" type=\"text\" class=\"form-control\">"
				+ "</div>"
				+ "</div>"
				+ "<label>????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input readonly id=\"carNum\" name=\"carNum\" value=\""+data.extend.parkInfo.carnum+"\" type=\"text\" class=\"form-control\">"
				+ "</div></div>"
				+ "<label>?????????????????????</label><br>"
				+parkTem
				$("#myModalLabel").html("????????????");
				$("#checkSubmit").html("??????");
				$("#checkSubmit").attr("onclick","checkOutSubmit()");
				$(".modal-body").append(html);
				$("#myModal").modal('show');
					}
			}
		}) 
	}
	
	
	/* ??????????????????????????? */
	function checkDetail(parknum) {
		 $.ajax({
			type:'get',
			url:'/depot-system/index/check/findParkinfoDetailByParknum',
			datatype:'json',
			data:{parkNum:parknum},
			success:function(data){
				var username="";
				if(data.extend.user!=null){
					username=data.extend.user.username;
				}
				if(data.code==100)
					{
				var parkTem="???";
				if(data.extend.parkInfo.parktem==1)
					{
					parkTem="???";
					}
				var html = "<label>???????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input value=\""+data.extend.parkInfo.parknum+"\" type=\"text\" class=\"form-control\" readonly ??unselectable=\"on\">"
				+ "</div>"
				+ "</div>"
				+"<label>???????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input value=\""+username+"\" type=\"text\" class=\"form-control\" readonly ??unselectable=\"on\">"
				+ "</div>"
				+ "</div>"
				+"<label>???????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input value=\""+data.extend.parkInfo.cardnum+"\" type=\"text\" class=\"form-control\" readonly ??unselectable=\"on\">"
				+ "</div>"
				+ "</div>"
				+ "<label>????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input value=\""+data.extend.parkInfo.carnum+"\" type=\"text\" class=\"form-control\" readonly ??unselectable=\"on\">"
				+ "</div></div>"
				+ "<label>???????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input value=\""+data.extend.parkin+"\" type=\"text\" class=\"form-control\" readonly ??unselectable=\"on\">"
				+ "</div></div>"
				+ "<label>?????????????????????</label><br>"
				+parkTem
				$("#myModalLabel").html("???????????????");
				$("#checkSubmit").hide();
				$(".modal-body").append(html);
				$("#myModal").modal('show');
					}
				else{
					alert("???????????????????????????");
				}
			}
		}) 
	}
	
	/* ?????????????????????*/
	function addIllegal(parknum) {
		$.ajax({
			type:'get',
			url:'/depot-system/index/check/findParkinfoByParknum',
			datatype:'json',
			data:{parkNum:parknum},
			success:function(data){
		var html ="<label>????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+"<input readonly type=\"text\" class=\"form-control\" name=\"parknum\" value=\""+parknum+"\" />"
				+"</div>"
				+"</div>"
				+"<label>???????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input readonly id=\"cardNum\" name=\"cardNum\" value=\""+data.extend.parkInfo.cardnum+"\" placeholder=\"???????????????\" type=\"text\" class=\"form-control\">"
				+ "</div>"
				+ "</div>"
				+ "<label>????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<input readonly id=\"carNum\" name=\"carNum\" value=\""+data.extend.parkInfo.carnum+"\" placeholder=\"??????????????????\" type=\"text\" class=\"form-control\">"
				+ "</div></div>"
				+ "<label>???????????????</label><div style=\"width: 30%;\">"
				+ "<div class=\"input-group\">"
				+ "<textarea id=\"illegalInfo\" name=\"illegalInfo\" placeholder=\"?????????????????????\" type=\"text\" class=\"form-control\" ></textarea>"
				+ "</div></div>"
				+ "<label>?????????????????????</label>"
				+"<select id=\"parkTem\" name=\"parkTem\" style=\"width:100px\" class=\"form-control\"> "
				+"<option value=\"0\">???</option><option value=\"1\">???</option> </select>";
		$("#myModalLabel").html("????????????");
		$("#checkSubmit").html("??????");
		$("#checkSubmit").attr("onclick","illegalSubmit()");
		$(".modal-body").append(html);
		$("#myModal").modal('show');
			}
		})
	}
	
	/* ????????????*/
	function illegalSubmit()
	{
		$.ajax({
			type:'post',
			url:'/depot-system/index/check/illegalSubmit',
			datatype:'text',
			data:$("#checkForm").serializeArray(),
			contentType:'application/x-www-form-urlencoded',
			success:function(data){
				if(data.code==100)
					{
					alert(data.extend.va_msg);
				$("#myModal").modal('hide');
				window.location.href="/depot-system/index/toindex";
					}else{
					alert(data.extend.va_msg)
					}
			}
		})
	}
	
	
</script>
</html>