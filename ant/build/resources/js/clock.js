function fillCanvas(name, width, height) {
    let canvas = document.getElementById(name);
    if (canvas == null)
        return;
    let ctx = canvas.getContext("2d");

    ctx.fillStyle = "rgba(255,228,225, 0.85)";
    ctx.beginPath();
    ctx.arc(width / 2, height / 2, width / 2 - 1, 0, Math.PI * 2, true);
    ctx.fill();
    ctx.stroke();
    ctx.closePath();
    // ctx.fillRect(0, 0, width, height);
}

function paintClock(name, width, height) {

    let canvas = document.getElementById(name);
    if (canvas == null)
        return;
    let ctx = canvas.getContext("2d");
    let time = new Date();

    ctx.lineWidth = 1.8;

    ctx.fillStyle = "black";
    ctx.beginPath();
    ctx.arc(width / 2, height / 2, width / 2 - 1, 0, Math.PI * 2, true);
    ctx.stroke();

    drawMarkers(ctx, width, height);

    ctx.beginPath();
    ctx.moveTo(width / 2 - (width / 2 - 130) * Math.cos(Math.PI / 2 - 6 * (time.getMinutes() + (1 / 60) * (time.getSeconds() + (time.getMilliseconds() / 1000))) * (Math.PI / 180)),
        height / 2 + (height / 2 - 130) * Math.sin(Math.PI / 2 - 6 * (time.getMinutes() + (1 / 60) * (time.getSeconds() + (time.getMilliseconds() / 1000))) * (Math.PI / 180)));
    ctx.lineTo(width / 2 + (width / 2 - 15) * Math.cos(Math.PI / 2 - 6 * (time.getMinutes() + (1 / 60) * (time.getSeconds() + (time.getMilliseconds() / 1000))) * (Math.PI / 180)),
        height / 2 - (height / 2 - 30) * Math.sin(Math.PI / 2 - 6 * (time.getMinutes() + (1 / 60) * (time.getSeconds() + (time.getMilliseconds() / 1000))) * (Math.PI / 180)));
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(width / 2 - (width / 2 - 135) * Math.cos(Math.PI / 2 - 30 * (time.getHours() + (1 / 60) * time.getMinutes()) * (Math.PI / 180)),
        height / 2 + (height / 2 - 135) * Math.sin(Math.PI / 2 - 30 * (time.getHours() + (1 / 60) * time.getMinutes()) * (Math.PI / 180)));
    ctx.lineTo(width / 2 + (width / 2 - 70) * Math.cos(Math.PI / 2 - 30 * (time.getHours() + (1 / 60) * time.getMinutes()) * (Math.PI / 180)),
        height / 2 - (height / 2 - 70) * Math.sin(Math.PI / 2 - 30 * (time.getHours() + (1 / 60) * time.getMinutes()) * (Math.PI / 180)));
    ctx.stroke();

    ctx.strokeStyle = "red";
    drawLine(ctx, 125, 30, (time.getSeconds() + (time.getMilliseconds() / 1000)), width, height);

    ctx.strokeStyle = "white";
    ctx.fillStyle = "white";
    ctx.beginPath();
    ctx.arc(width / 2, height / 2, 5, 0, Math.PI * 2, true);
    ctx.stroke();
    ctx.fill();
}

function paintExtraClock(name, width, height) {
    let canvas = document.getElementById(name);
    if (canvas == null)
        return;
    let ctx = canvas.getContext("2d");

    ctx.lineWidth = 1;

    ctx.beginPath();
    ctx.strokeStyle = "#EEE7EA";
    ctx.fillStyle = "white";
    ctx.font = "20px Arial";
    date = new Date().getDate();
    if (date < 10)
        date = "0" + date;
    month = new Date().getMonth() + 1;
    if (month < 10)
        month = "0" + month;
    ctx.fillStyle = "black";
    ctx.fillText(date + "." + month, width + 5, height + 18);
}

function drawLine(ctx, a, b, c, width, height) {
    ctx.beginPath();
    ctx.moveTo(width / 2 - (width / 2 - a) * Math.cos(Math.PI / 2 - 6 * c * (Math.PI / 180)),
        height / 2 + (height / 2 - a) * Math.sin(Math.PI / 2 - 6 * c * (Math.PI / 180)));
    ctx.lineTo(width / 2 + (width / 2 - b) * Math.cos(Math.PI / 2 - 6 * c * (Math.PI / 180)),
        height / 2 - (height / 2 - b) * Math.sin(Math.PI / 2 - 6 * c * (Math.PI / 180)));
    ctx.stroke();
}

function drawMarkers(ctx, width, height) {
    for (let i = 0; i <= 30; i += 5) {
        if (i / 5 == 3 || i / 5 == 6 || i / 5 == 12) {
            ctx.lineWidth = 3;
            ctx.strokeStyle = "red";
            drawLine(ctx, 40, height - 10, i, width, height);
            drawLine(ctx, width - 10, 40, i, width, height);
            ctx.strokeStyle = "black";
            ctx.lineWidth = 1.8;
            continue;
        } else {
            ctx.strokeStyle = "black";
        }

        drawLine(ctx, 20, height - 10, i, width, height);
        drawLine(ctx, width - 10, 20, i, width, height);
    }
}

function paintClocks() {
    const canvasName = "clock";
    const width = 300;
    const height = 300;
    fillCanvas(canvasName, width, height);
    paintExtraClock(canvasName, width - 110, height / 2 - 10);
    paintClock(canvasName, width, height);
}

$(() => {
    paintClocks();
    addHover();
});

setInterval(() => {
    paintClocks();
}, 50);

function addHover() {
    $("canvas").hover(function () {
        $(this).attr("title", new Date().toLocaleString());
    }, function () {
        $(this).attr("title", new Date().toLocaleString());
    });
}