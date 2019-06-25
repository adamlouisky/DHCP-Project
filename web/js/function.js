
function clock() {
    $(".running").each(function() {

        var startTime = parseInt($(this).data("startTime"));
        if( startTime==0) return;
        var nowTime=new Date().getTime();
        var tip=Math.round((nowTime-startTime)/1000);
        var t = longToDuration(tip);
        $(this).html(t);

    })
    $(".clock").each(function() {
        var tip = parseInt($(this).data("duration"));
        $(this).data("duration",tip+1);

        var t = longToDuration(tip);
        $(this).html(t);

    })
}

function formatSimpleTimeStr(timelong) {
    if (timelong==0) return "";
    var dt;
    if (timelong == undefined) {
        dt = new Date();
    } else {
        dt = new Date(timelong);
    }
    var dateStr = dt.getFullYear() + '-'
        + (dt.getMonth()+1 < 10 ? '0' + (dt.getMonth()+1) : (dt.getMonth()+1)) + '-'
        + (dt.getDate() < 10 ? '0' + dt.getDate() : dt.getDate());
    now = new Date();
    var toDay = now.getFullYear() + '-'
        + (now.getMonth() < 10 ? '0' + now.getMonth() : now.getMonth())
        + '-' + (now.getDate() < 10 ? '0' + now.getDate() : now.getDate());

    if (dateStr == toDay) {
        return timeStr = (dt.getHours() < 10 ? '0' + dt.getHours() : dt
                .getHours())
            + ':'
            + (dt.getMinutes() < 10 ? '0' + dt.getMinutes() : dt
                .getMinutes())
            + ':'
            + (dt.getSeconds() < 10 ? '0' + dt.getSeconds() : dt
                .getSeconds());
    } else {
        return dt.getFullYear()
            + '-'
            + (dt.getMonth()+1 < 10 ? '0' + (dt.getMonth()+1) : (dt.getMonth()+1))
            + '-'
            + (dt.getDate() < 10 ? '0' + dt.getDate() : dt.getDate())
            + " "
            + (dt.getHours() < 10 ? '0' + dt.getHours() : dt.getHours())
            + ':'
            + (dt.getMinutes() < 10 ? '0' + dt.getMinutes() : dt
                .getMinutes())
            + ':'
            + (dt.getSeconds() < 10 ? '0' + dt.getSeconds() : dt
                .getSeconds());
    }
}

function longToDuration(time) {
    var s = time % 60;
    var s1 = parseInt(time / 60);
    var m = s1 % 60;
    var h = parseInt(s1 / 60);
    if (h < 10) {
        s1 = "0" + h;
    } else {
        s1 = "" + h;
    }
    s1 += ":";
    if (m < 10) {
        s1 += "0" + m;
    } else {
        s1 += m;
    }
    s1 += ":"
    if (s < 10) {
        s1 += "0" + s;
    } else {
        s1 += s;
    }
    return s1;
}