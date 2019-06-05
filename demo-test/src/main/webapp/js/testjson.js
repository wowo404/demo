var timestamp = (Date.parse(new Date())).toString();
var tt = parseInt(timestamp.substring(0, 10));

function t() {
    now = new Date((tt + i + 28800) * 1000);
    i++;
    document.getElementById("times").innerHTML = now.toUTCString();
}
var i = 0;
setInterval("t()", 1000);

function l() {
    today = new Date();
    document.getElementById("local").innerHTML = today.toLocaleString();
}
setInterval("l()", 1000);