$.ajaxSetup({  
    cache: false, //close AJAX cache  
    contentType:"application/x-www-form-urlencoded;charset=utf-8",   
    complete:function(XHR,textStatus){     
        var resText = XHR.responseText;  
        if(resText=='ajaxSessionTimeOut'){     
            sessionTimeOut();  
        }  
        else if(resText=='ajaxNoLimit'){     
            noLimit();  
        }          
    }   
});  
  
function sessionTimeOut(){  
    alert('操作提示','用户登录会话已过期，请重新登录！');  
    setTimeout('window.top.location.href = "login!exit.action"', 15);  
}  
  
function noLimit(){  
    alert('操作提示','无相应操作权限，请联系系统管理员！');  
}  