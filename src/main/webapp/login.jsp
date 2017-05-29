<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="/js/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" >
    $(document).keyup(function(data){
        if(data.keyCode==13){
            submitForm();
        }
    });

    function submitForm(){
        $.post("/login",$("form").serialize(), function (data) {
            if (data.success){
                //跳转到首页
                window.location.href="/index"
            }else {
                //返回登录界面
                alert(data.msg);
            }
        })
    }

    function reset(){
        $("form input[name]").val("");
    }
</script>
</head>
<body>
  <section class="container">
    <div class="login">
      <h1>用户登录</h1>
      <form  method="post">
        <p><input type="text" name="username" value="" placeholder="账号"></p>
        <p><input type="password" name="password" value="" placeholder="密码"></p>
        <p class="submit">
        	<input type="button" value="登录" onclick="submitForm()">
        	<input type="button" value="重置" onclick="reset()">
        </p>
      </form>
    </div>
  </section>
</body>
</html>