const canvas = document.getElementById("graph"),
    ctx = canvas.getContext('2d');

let x = canvas.width;
let y = canvas.height;
ctx.lineWidth = 1.5;
ctx.strokeStyle = 'black';


function drawAxes() {
    drawLineFromTo(x/2,0,x/2,y);
    drawLineFromTo(0,y/2,x,y/2);
    ctx.lineWidth = ctx.lineWidth-0.5;
    //arrows
    drawLineFromTo(x/2, 0, x/2-5, 10);
    drawLineFromTo(x/2, 0, x/2+5, 10);
    drawLineFromTo(x, y/2, x-10, y/2+5);
    drawLineFromTo(x, y/2, x-10, y/2-5);
    //-R/2 lines
    drawLineFromTo(x/4, y/2, x/4, y/2-5);
    drawLineFromTo(x/4, y/2, x/4, y/2+5);
    drawLineFromTo(x/2, 3*(y/4), x/2+5, 3*(y/4));
    drawLineFromTo(x/2, 3*(y/4), x/2-5, 3*(y/4));
    //R/2 lines
    drawLineFromTo(3*(x/4), y/2, 3*(x/4), y/2-5);
    drawLineFromTo(3*(x/4), y/2, 3*(x/4), y/2+5);
    drawLineFromTo(x/2, y/4, x/2+5, y/4);
    drawLineFromTo(x/2, y/4, x/2-5, y/4);
    ctx.lineWidth = ctx.lineWidth+0.5;

}
function drawTriangle(){
    ctx.lineWidth = ctx.lineWidth-0.5;
    let triangle = new Path2D();
    triangle.moveTo(x/4, y/2);
    triangle.lineTo(x/2, y);
    triangle.lineTo(x/2, y/2);
    triangle.lineTo(x/4, y/2);
    triangle.closePath();
    ctx.fillStyle = `rgb(51,153,255)`
    ctx.fill(triangle,"evenodd");
    ctx.lineWidth = ctx.lineWidth+0.5;
}
function drawCircle(){
    ctx.lineWidth = ctx.lineWidth-0.5;
    let circle = new Path2D();
    circle.arc(x/2, y/2, x/4, Math.PI + (Math.PI * 2) / 2, Math.PI + (Math.PI * 3) / 2);
    circle.lineTo(x/2, y/2);
    ctx.fillStyle = `rgb(51,153,255)`;
    ctx.fill(circle,"evenodd");
    ctx.lineWidth = ctx.lineWidth+0.5;
}
function drawRectangle(){
    ctx.fillStyle = `rgb(51,153,255)`;
    ctx.fillRect(x/2,y/2,x/4,-y/2);
}
function drawText(r){
    ctx.font = '15px monospace';
    ctx.fillStyle = `black`;
    if (r<=0){
        r = "R/2";
    }
    else r = (r/2).toString();
    ctx.fillText(r,x/2 + 10, y/4+5)
    ctx.fillText(r,3*(x/4)-8, y/2-8)
    ctx.fillText(r,x/2 + 10, 3*(y/4)+5)
    ctx.fillText(r,(x/4)-8, y/2-8)
}
function redrawGraph(r=0){
    ctx.clearRect(0, 0, x, y);
    drawTriangle();
    drawCircle();
    drawRectangle();
    drawAxes();
    drawText(r);
}
function drawLineFromTo(x1,y1,x2,y2){
    ctx.moveTo(x1,y1);
    ctx.lineTo(x2,y2);
    ctx.stroke();
}
redrawGraph();
$('#r-text-input').on('change', function() {
    redrawGraph(this.value);
});