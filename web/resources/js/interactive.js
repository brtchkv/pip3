const width = 181;
const height = 181;
const extraValue = 0;
const trueR = 45;

$(() => {
    drawFigure(trueR);
});

function updateView() {
    drawFigure(getR());
}

function getR() {
    let result = $('input[name$="r"]').val().replace(",", ".") * trueR;
    if (isNaN(result) || result < trueR || result > trueR * 3)
        return trueR;
    else
        return result;
}

function getClick(event) {

        let width = $('#canvas').width();
        let height = $('#canvas').height();

        let r = $("input[name$='r']").val();

        let rect = $("#canvas")[0].getBoundingClientRect();

        let x = (event.clientX - rect.left - width / 2) / getR() * r;
        let y = (height / 2 - (event.clientY - rect.top)) / getR() * r;
        // x = (Math.round(x*2)*1.)/2;

        $("input[name*='xFromGraph']").val(x.toFixed(2));
        $("input[name$='y']").val(y.toFixed(2));
        // $('#form').submit();
}

$(document).ready(function () {

    // let canvas = $('#canvas');
    // let ctx = canvas[0].getContext('2d');
    // let rxp = /{([^}]+)}/g,
    //     curMatch;
    // let width = canvas.width();
    // let height = canvas.height();
    //
    // if ($("#canvas").attr('history')) {
    //     userAttempts = $("#canvas").attr('history');
    //     console.log(userAttempts);
    //     while (curMatch = rxp.exec(userAttempts)) {
    //         console.log(curMatch[1].replace(
    //             /'/g, "\""));
    //         obj = JSON.parse("{" + curMatch[1].replace(
    //             /'/g, "\"") + "}");
    //         let x = (width * (2 * obj.x + 3 * obj.r)) / (6 * obj.r);
    //         let y = (height * (3 * obj.r - 2 * obj.y)) / (6 * obj.r);
    //
    //         drawCircle(x, y, obj.correct);
    //     }
    // }

});


function drawCircle(x, y, result) {
    if (result === 'true')
        ctx.fillStyle = 'rgb(255, 255, 255)';
    else
        ctx.fillStyle = "#3E97FF";
    ctx.beginPath();
    ctx.arc(x, y, 2.5, 0, Math.PI * 2);
    ctx.fill();
}

function drawFigure(r){

    if (r < trueR){
        r = r * trueR;
    }

    let canvas = document.getElementById("canvas");
    let ctx = canvas.getContext("2d");

    ctx.fillStyle = "rgba(255, 255, 255, 1)";
    ctx.fillRect(0, 0, width + extraValue, height + extraValue);

    ctx.fillStyle = "#3E97FF";
    ctx.beginPath();
    ctx.rect(width / 2 - r / 2, height / 2 - r, r/2, r);
    ctx.fill();
    ctx.closePath();

    ctx.beginPath();
    ctx.moveTo(width / 2, height / 2 - r);
    ctx.lineTo(width / 2 + r, height / 2);
    ctx.lineTo(width / 2, height / 2);
    ctx.fill();
    ctx.closePath();

    ctx.beginPath();
    ctx.arc(width / 2, height / 2, r / 2, 0, 2 * Math.PI, false);
    ctx.fill();
    ctx.closePath();

    ctx.beginPath();
    ctx.fillStyle = "#FFFFFF";
    ctx.rect(width / 2 - r, height / 2 , r, r/2 + r/4);
    ctx.fill();
    ctx.closePath();

    drawPlane(r)
}

function drawPlane(r) {

    let canvas = document.getElementById("canvas");
    let ctx = canvas.getContext("2d");

    ctx.beginPath();
    ctx.moveTo(0, height / 2);
    ctx.lineTo(width + extraValue, height / 2);
    ctx.lineTo(width - 10 + extraValue, height / 2 - 5);
    ctx.moveTo(width + extraValue, height / 2);
    ctx.lineTo(width - 10 + extraValue, height / 2 + 5);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(width / 2, height + extraValue);
    ctx.lineTo(width / 2, 0);
    ctx.lineTo(width / 2 - 5, 10);
    ctx.moveTo(width / 2, 0);
    ctx.lineTo(width / 2 + 5, 10);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(width / 2 + r, height / 2 - 5);
    ctx.lineTo(width / 2 + r, height / 2 + 5);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(width / 2 - r, height / 2 - 5);
    ctx.lineTo(width / 2 - r, height / 2 + 5);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(width / 2 - 5, height / 2 + r);
    ctx.lineTo(width / 2 + 5, height / 2 + r);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(width / 2 - 5, height / 2 - r);
    ctx.lineTo(width / 2 + 5, height / 2 - r);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(width / 2 - r/2, height / 2 - 5);
    ctx.lineTo(width / 2 - r/2, height / 2 + 5);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(width / 2 + r/2, height / 2 - 5);
    ctx.lineTo(width / 2 + r/2, height / 2 + 5);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(width / 2 - 5, height / 2 + r/2);
    ctx.lineTo(width / 2 + 5, height / 2 + r/2);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(width / 2 - 5, height / 2 - r/2);
    ctx.lineTo(width / 2 + 5, height / 2 - r/2);
    ctx.stroke();

    ctx.fillStyle = "#000000";
    ctx.font = "10px Arial";
    ctx.fillText("X", width - 10 + extraValue, height / 2 - 15);
    ctx.fillText("Y", width / 2 - 18, 12);
    ctx.fillText("R", width / 2 + r - 5, height / 2 + 15);
    ctx.fillText("R", width / 2 + 4, height / 2 - r - 5);
    ctx.fillText("-R", width / 2 - r - 5, height / 2 + 15);
    ctx.fillText("-R", width / 2 + 4, height / 2 + r + 10);
    ctx.fillText("R/2", width / 2 + r / 2, height / 2 + 15);
    ctx.fillText("R/2", width / 2 + 6, height / 2 - r / 2 + 2);
    ctx.fillText("-R/2", width / 2 - r / 2 - 20, height / 2 - 5);
    ctx.fillText("-R/2", width / 2 + 4, height / 2 + r / 2 + 9);
}

function smoothOut() {
    $('#main').addClass('animated zoomOut fast');
}

function showTable() {
    $('#dataTable').removeClass('hide');
    $('#tableResponse').addClass('hide');

    document.getElementById("historyButton").onclick = hideTable;
}

function hideTable() {
    $('#dataTable').addClass('hide');
    $('#tableResponse').removeClass('hide');

    document.getElementById("historyButton").onclick = showTable;
}

function showForm() {
    $('#fields').removeClass('hide');
    $('#dataTable').addClass('hide');

    document.getElementById("historyButton").onclick = hideForm;
}

function hideForm() {
    $('#dataTable').removeClass('hide');
    $('#fields').addClass('hide');

    document.getElementById("historyButton").onclick = showForm;
}
