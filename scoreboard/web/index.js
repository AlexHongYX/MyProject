var xmlHttp=null;
function Change(str) {
    if(str.length==0){
        document.getElementById("name").innerHTML="";
        return;
    }
    //try—catch验证版本问题
    try{
        xmlHttp=new XMLHttpRequest();
    }catch (e) {
        try
        {
            //如果版本不匹配就按照原来版本新建xmlHttp
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch(e)
        {
            alert ("Your browser does not support XMLHTTP!");
            return;
        }
    }
    var url= "/scoreboard/getNameServlet?id="+str;
    //open最后一个参数代表是否异步执行
    xmlHttp.open("GET",url,false);
    xmlHttp.send(null);
    document.getElementById("name").innerHTML=xmlHttp.responseText;
}