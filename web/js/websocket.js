var wsocket;
var wsUri;

const DHCPDISCOVER=1;
const DHCPOFFER=2;

function initWS(){
    var pathname=document.location.pathname;
    pathname=pathname.substring(0,pathname.lastIndexOf("/")+1);
    wsUri="ws://"+ document.location.host+ pathname+ "eventendpoint";
}

function connect() {
    wsocket=new WebSocket(wsUri);
    wsocket.onmessage=onMessage;
    wsocket.onopen=onOpen;
    wsocket.onclose=function(){
        console.log("连接已经关闭");
    }
    wsocket.onerror=function(){
        console.log("连接失败");
    }
}

function onMessage(evt) {
    //Handle the message recieved
    console.log("Recv: "+evt.data);

    var json=JSON.parse(evt.data);
    var type=json.msgType;
    var mac=json.mac;
    var id=mac.replace(/:/g,"-");
    var update=formatSimpleTimeStr(json.time);
    var now=new Date().getTime();
    switch (json.msgType){
        case DHCPDISCOVER:
            if ($("#"+id).length>0){
                $("#"+id).parent("tbody").prepend($("#"+id));
                $("#"+id).removeClass("over");
                $("#"+id+" td:eq(15)").html(update);
            }else {
                var str = "<tr id='"
                    + id
                    + "'><td>1</td><td></td><td>"+mac+"</td>"
                    + "<td></td><td></td><td></td><td></td><td></td><td></td><td data-count='0'>0</td><td></td>" +
                    "<td data-count='0'>0</td><td data-count='0'>0</td><td data-count='0'>0</td><td data-count='0'>0</td>"
                    + "<td>" + update + "</td></tr>";
                $("#assignedBody").prepend(str);
            }

            for (var i=0;i<$("#assignedBody tr").length;i++){
                $("#assignedBody tr:eq("+i+") td:first").text(i+1);
            }
            break;
        case DHCPOFFER:
            if ($("#" + id).length > 0) {
                $("#" + id).parent("tbody").prepend($("#" + id));
                $("#" + id).removeClass("over");
                $("#" + id + " td:eq(1)").html(json.server);
                $("#" + id + " td:eq(3)").html(json.ip);		//ip
                $("#" + id + " td:eq(4)").html(json.mask);	//mask
                $("#" + id + " td:eq(5)").html(json.router);	//router
                $("#" + id + " td:eq(6)").html(json.dns);	//dns
                $("#" + id + " td:eq(7)").html(json.lease);	//lease
                $("#" + id + " td:eq(8)").html(update);	//offer time
                var $this=$("#" + id + " td:eq(9)");

                $this.data("count",$this.data("count")+1);
                $this.html($this.data("count"));

                $("#" + id + " td:eq(15)").html(update);

            } else {
                var str = "<tr id='"
                    + id
                    + "'><td>1</td><td>"+json.server+"</td><td>"+mac+"</td>"
                    + "<td>"+json.ip+"</td><td>"+json.mask+"</td><td>"+json.router+"</td><td>"+json.dns+"</td><td>"+json.lease+"</td><td>"+update+"</td>" +
                    "<td data-count='1'>1</td><td></td>" +
                    "<td data-count='0'>0</td><td data-count='0'>0</td><td data-count='0'>0</td><td data-count='0'>0</td>"
                    + "<td>" + update + "</td></tr>";
                $("#assignedBody").prepend(str);
            }
            for (var i = 0; i < $("#assignedBody tr").length; i++) {
                $("#assignedBody tr:eq(" + i + ") td:first").text(i + 1);
            }
            break;
        case  DHCPREQUEST:
            if ($("#" + id).length > 0) {
                $("#" + id).parent("tbody").prepend($("#" + id));
                $("#" + id).removeClass("over");
                $("#" + id + " td:eq(1)").html(json.server);
                $("#" + id + " td:eq(3)").html(json.ip);		//ip
//			$("#" + id + " td:eq(4)").html(json.mask);	//mask
//			$("#" + id + " td:eq(5)").html(json.router);	//router
//			$("#" + id + " td:eq(6)").html(json.dns);	//dns
//			$("#" + id + " td:eq(7)").html(json.lease);	//lease
//			$("#" + id + " td:eq(8)").html(update);	//offer time
//			var $this=$("#" + id + " td:eq(9)");
////			var count=$this.data("count");
////			debug($this.data("count"));
////			$this.data("count",count+1);
////			debug($this.data("count"));
//			$this.data("count",$this.data("count")+1);
//			$this.html($this.data("count"));
////			$("#" + id + " td:eq(9)").html();	//offer count
//			$("#" + id + " td:eq(10)").html(json.server);	//reply time
//			$("#" + id + " td:eq(11)").html(json.server);	//reply count
//			$("#" + id + " td:eq(12)").html(json.server);	//inform count
//			$("#" + id + " td:eq(13)").html(json.server);	//release count
//			$("#" + id + " td:eq(14)").html(json.server);	//decline count
                $("#" + id + " td:eq(15)").html(update);

            } else {
                var str = "<tr id='"
                    + id
                    + "'><td>1</td><td>"+json.server+"</td><td>"+mac+"</td>"
                    + "<td>"+json.ip+"</td><td></td><td></td><td></td><td></td><td></td>" +
                    "<td data-count='1'>1</td><td></td>" +
                    "<td data-count='0'>0</td><td data-count='0'>0</td><td data-count='0'>0</td><td data-count='0'>0</td>"
                    + "<td>" + update + "</td></tr>";
                $("#assignedBody").prepend(str);
            }
            for (var i = 0; i < $("#assignedBody tr").length; i++) {
                $("#assignedBody tr:eq(" + i + ") td:first").text(i + 1);
            }
            break;
        case  DHCPDECLINE :
            if ($("#" + id).length > 0) {
                $("#" + id).parent("tbody").prepend($("#" + id));
                $("#" + id).removeClass("over");
                $("#" + id + " td:eq(1)").html(json.server);
                $("#" + id + " td:eq(3)").html(json.ip);		//ip
//			$("#" + id + " td:eq(4)").html(json.mask);	//mask
//			$("#" + id + " td:eq(5)").html(json.router);	//router
//			$("#" + id + " td:eq(6)").html(json.dns);	//dns
//			$("#" + id + " td:eq(7)").html(json.lease);	//lease
//			$("#" + id + " td:eq(8)").html(update);	//offer time
//			var $this=$("#" + id + " td:eq(9)");
//			$this.data("count",$this.data("count")+1);
//			$this.html($this.data("count"));
//			$("#" + id + " td:eq(9)").html();	//offer count
//			$("#" + id + " td:eq(10)").html(json.server);	//reply time
//			$("#" + id + " td:eq(11)").html(json.server);	//reply count
//			$("#" + id + " td:eq(12)").html(json.server);	//inform count
//			$("#" + id + " td:eq(13)").html(json.server);	//release count
                var $this=$("#" + id + " td:eq(14)");
//			var count=$this.data("count");
//			debug($this.data("count"));
//			$this.data("count",count+1);
//			debug($this.data("count"));
                $this.data("count",$this.data("count")+1);
                $this.html($this.data("count"));	//decline count

                $("#" + id + " td:eq(15)").html(update);

            } else {
                var str = "<tr id='"
                    + id
                    + "'><td>1</td><td>"+json.server+"</td><td>"+mac+"</td>"
                    + "<td>"+json.ip+"</td><td></td><td></td><td></td><td></td><td></td>" +
                    "<td data-count='0'>0</td><td></td>" +
                    "<td data-count='0'>0</td><td data-count='0'>0</td><td data-count='0'>0</td><td data-count='1'>1</td>"
                    + "<td>" + update + "</td></tr>";
                $("#assignedBody").prepend(str);
            }
            for (var i = 0; i < $("#assignedBody tr").length; i++) {
                $("#assignedBody tr:eq(" + i + ") td:first").text(i + 1);
            }
            break;
        case  DHCPACK :

            if ($("#" + id).length > 0) {
                $("#" + id).parent("tbody").prepend($("#" + id));
                $("#" + id).removeClass("over");
                $("#" + id + " td:eq(1)").html(json.server);
                $("#" + id + " td:eq(3)").html(json.ip);		//ip
                $("#" + id + " td:eq(4)").html(json.mask);	//mask
                $("#" + id + " td:eq(5)").html(json.router);	//router
                $("#" + id + " td:eq(6)").html(json.dns);	//dns
                $("#" + id + " td:eq(7)").data("lease",json.lease);
                $("#" + id + " td:eq(7)").data("startTime",now);
                $("#" + id + " td:eq(7)").addClass("running");
//			$("#" + id + " td:eq(7)").html(json.lease);	//lease
//			$("#" + id + " td:eq(8)").html(update);	//offer time
//			var $this=$("#" + id + " td:eq(9)");
//			$this.data("count",$this.data("count")+1);
//			$this.html($this.data("count"));	//offer count

                $("#" + id + " td:eq(10)").html(update);	//reply time
                var $this=$("#" + id + " td:eq(11)");
                $this.data("count",$this.data("count")+1);
                $this.html($this.data("count"));			//reply count
//			$("#" + id + " td:eq(12)").html(json.server);	//inform count
//			$("#" + id + " td:eq(13)").html(json.server);	//release count
//			$("#" + id + " td:eq(14)").html(json.server);	//decline count
                $("#" + id + " td:eq(15)").html(update);

            } else {
                var str = "<tr id='"
                    + id
                    + "'><td>1</td><td>"+json.server+"</td><td>"+mac+"</td>"
                    + "<td>"+json.ip+"</td><td>"+json.mask+"</td><td>"+json.router+"</td><td>"+json.dns+"</td><td class='running' data-lease='"+json.lease+"' data-start-time='"+now+"'></td><td></td>" +
                    "<td data-count='0'>0</td><td>"+update+"</td>" +
                    "<td data-count='1'>1</td><td data-count='0'>0</td><td data-count='0'>0</td><td data-count='0'>0</td>"
                    + "<td>" + update + "</td></tr>";
                $("#assignedBody").prepend(str);
            }
            for (var i = 0; i < $("#assignedBody tr").length; i++) {
                $("#assignedBody tr:eq(" + i + ") td:first").text(i + 1);
            }
            break;
        case  DHCPNAK :

            break;
        case  DHCPRELEASE :

            break;
        case  DHCPINFORM :
            if ($("#" + id).length > 0) {
                $("#" + id).parent("tbody").prepend($("#" + id));
                $("#" + id).removeClass("over");
//			$("#" + id + " td:eq(1)").html(json.server);
                $("#" + id + " td:eq(3)").html(json.ip);		//ip
//			$("#" + id + " td:eq(4)").html(json.mask);	//mask
//			$("#" + id + " td:eq(5)").html(json.router);	//router
//			$("#" + id + " td:eq(6)").html(json.dns);	//dns
//			$("#" + id + " td:eq(7)").html(json.lease);	//lease
//			$("#" + id + " td:eq(8)").html(update);	//offer time
//			var $this=$("#" + id + " td:eq(9)");
//			$this.data("count",$this.data("count")+1);
//			$this.html($this.data("count"));	//offer count

//			$("#" + id + " td:eq(12)").html(update);	//reply time
                var $this=$("#" + id + " td:eq(12)");
                $this.data("count",$this.data("count")+1);
                $this.html($this.data("count"));			//reply count
//			$("#" + id + " td:eq(12)").html(json.server);	//inform count
//			$("#" + id + " td:eq(13)").html(json.server);	//release count
//			$("#" + id + " td:eq(14)").html(json.server);	//decline count
                $("#" + id + " td:eq(15)").html(update);

            } else {
                var str = "<tr id='"
                    + id
                    + "'><td>1</td><td></td><td>"+mac+"</td>"
                    + "<td>"+json.ip+"</td><td></td><td></td><td></td><td></td><td></td>" +
                    "<td data-count='0'>0</td><td></td>" +
                    "<td data-count='0'>0</td><td data-count='1'>1</td><td data-count='0'>0</td><td data-count='0'>0</td>"
                    + "<td>" + update + "</td></tr>";
                $("#assignedBody").prepend(str);
            }
            for (var i = 0; i < $("#assignedBody tr").length; i++) {
                $("#assignedBody tr:eq(" + i + ") td:first").text(i + 1);
            }
            break;
    }

}

function onOpen() {
    //do something when the session is created
    console.log("连接建立");
    window.setInterval(clock,1000);
    wsocket.send("Hello, I am LiLei.")
}