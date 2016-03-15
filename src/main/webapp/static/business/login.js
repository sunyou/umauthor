$(function () {
	
    //登陆
    $("#submit_btn").click(function () {
        login();
    });
    
    //重置
    $("#reset_btn").click(function(){
    	cleanAll();
    });

	//图片验证码切换
	$("[name='checkcodeImage']").click(function(){
		newCheckcode();
	});
});

//用户登陆验证
function login() {
	var username = $.trim($("#username").val());//用户名
	var password = $.trim($("#password").val());//密码
	//var checkCode = $.trim($("#checkcode").val());//随机码

	//用户名为空
	if(username==""){
        $("#chkMsg").html('<i class="prompt_icon"></i>请输入用户名！');
        $("#username").focus();
        return false;
  	}
	//密码为空
	if(password==""){
	   $("#chkMsg").html('<i class="prompt_icon"></i>请输入密码！');
	   $("#password").focus();
	   return false;
	}
	
	//$("#chkMsg").html("<img src='images/loading.gif' style='width:16px;height:16px'>校验中,请稍候..");
	//$("#login").attr("disabled",true);
	$("#submit").attr("disabled",true);
	$("#reset").attr("disabled",true);
	$.ajax({
		type: "POST",
		dataType : 'json',
		url: 'doLogin',  
		data: {"name":username,"password":password/*,"checkCode":checkCode*/},
		success: function(data){
			loginBack(data.code,data.message);
		},
		error: function(data){
   			alert("请求超时，请稍候再试!");
   			//$("#login").attr("disabled",false);
   			$("#submit").attr("disabled",false);
   			$("#reset").attr("disabled",false);
		}
	});
return false;
}

//切换随机码
function newCheckcode(){
	$("#checkcode").val('');
	var url = $("[name='checkcodeImage']").attr("src");
		if(url.indexOf("?") != -1){
			$("[name='checkcodeImage']").attr("src",$("[name='checkcodeImage']").attr("src") + "&nocache=" + new Date().getTime());
		}else{
			$("[name='checkcodeImage']").attr("src",$("[name='checkcodeImage']").attr("src") + "?nocache=" + new Date().getTime());
		}
}

//操作成功后返回值的操作
function loginBack(code,message) {
    switch (code) {
        case "-1" :
        {
        	$("#chkMsg").html('<i class="prompt_icon"></i>'+message);
            break;
   			$("#submit").attr("disabled",false);
   			$("#reset").attr("disabled",false);
        }case "-2" :
        {
        	$("#chkMsg").html('<i class="prompt_icon"></i>'+message);
            break;
   			$("#submit").attr("disabled",false);
   			$("#reset").attr("disabled",false);
        }case "-3" :
        {
        	$("#chkMsg").html('<i class="prompt_icon"></i>'+message);
            break;
   			$("#submit").attr("disabled",false);
   			$("#reset").attr("disabled",false);
        }case "-4" :
        {
        	$("#chkMsg").html('<i class="prompt_icon"></i>'+message);
            break;
   			$("#submit").attr("disabled",false);
   			$("#reset").attr("disabled",false);
        }case "-5" :
        {
        	$("#chkMsg").html('<i class="prompt_icon"></i>'+message);
            break;
   			$("#submit").attr("disabled",false);
   			$("#reset").attr("disabled",false);
        }
        case "0" :
        {
            //window.top.location = "main.jsp";
        	window.top.location = "main";
            break;
        }
        /*case "1" :
        {
        	$("#chkMsg").html('<i class="prompt_icon"></i>该用户不存在！');
   			$("#submit").attr("disabled",false);
   			$("#reset").attr("disabled",false);
            break;
        }
        case "2" :
        {
        	$("#chkMsg").html('<i class="prompt_icon"></i>用户名或密码错误！');
   			$("#submit").attr("disabled",false);
   			$("#reset").attr("disabled",false);
            break;
        }*/
        default :
        {
        	$("#chkMsg").html('<i class="prompt_icon"></i>系统忙,请稍候再试！');
   			$("#submit").attr("disabled",false);
   			$("#reset").attr("disabled",false);
        }
    }
    newCheckcode();
}

//回车键盘监听事件
document.onkeydown = keyListener;
function keyListener(e) {
    e = e ? e : event;
    if (e.keyCode == 13) {
        login();
    }
}

//重填
function cleanAll() {
    $("#username").val("");
    $("#password").val("");
    newCheckcode();
 }