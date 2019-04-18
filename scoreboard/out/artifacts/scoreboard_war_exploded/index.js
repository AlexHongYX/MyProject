var xmlHttp=null;
function Change(str) {
    if(str.length==0){
        document.getElementById("name").innerHTML="";
        return;
    }
    try{
        xmlHttp=new XMLHttpRequest();
    }catch (e) {
        try
        {
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch(e)
        {
            alert ("Your browser does not support XMLHTTP!");
            return;
        }
    }
    var url= "/scoreboard/getNameServlet?id="+str;
    xmlHttp.open("GET",url,false);
    xmlHttp.send(null);
    document.getElementById("name").innerHTML=xmlHttp.responseText;
}