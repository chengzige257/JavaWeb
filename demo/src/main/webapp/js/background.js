var canvas = document.getElementById("canvas");
	var context = canvas.getContext("2d");

	canvas.width = document.documentElement.clientWidth;
	canvas.height = document.documentElement.clientHeight;

	context.fillStyle = "rgba(0, 0, 0, 0.2)";
	context.strokeStyle = "rgba(0, 0, 0, 0.3)";
	context.lineWidth = 0.5;

	var balls = [];
 	function createBall() {

	 // 半径 [5, 12]
	 var _r = Math.floor(Math.random() * (12 - 5 + 1) + 5 );
   // x坐标
   var _x = Math.floor(Math.random() * (canvas.width - _r + 1) + _r );
   // y坐标
   var _y = Math.floor(Math.random() * (canvas.height - _r + 1) + _r );
   // 水平速度 [-0.5, +0.5]
   var _vx = Math.random() * 0.5 * Math.pow( -1, Math.floor(Math.random() * 2 + 1) );
   // 垂直速度 [-0.5, +0.5]
   var _vy = Math.random() * 0.5 * Math.pow( -1, Math.floor(Math.random() * 2 + 1) );

	 // 把每一个圆的信息存放到数组中
   balls.push({
     x: _x,
     y: _y,
     r: _r,
     vx: _vx,
     vy: _vy
   });
 	}

	var num = 23;
	for(var i = 0; i < num; i++) {
  	createBall();
	}


	function distance(point1, point2) {
		return Math.sqrt( Math.pow( (point1.x - point2.x), 2 ) + Math.pow( (point1.y - point2.y), 2 ) );
	}

	function setStrokeStyle(r,g,b,d,maxd) {
		var rate = 3 * (maxd - d) / maxd;
		if( rate>1 ) rate = 1;
		var StrokeStyle = "rgba(" +r+ "," +g+ "," +b+ "," +0.4*rate+ ")";
		return StrokeStyle;
	}


	function render(){
		context.clearRect(0,0,canvas.width,canvas.height);

		//画圆
		for(var k = 0; k < num; k++) {
			context.save();
			context.beginPath();
			context.arc( balls[k].x, balls[k].y, balls[k].r, 0, Math.PI*2 );
			context.fill();
			context.restore();
		}

		//连线
		for(var i = 0; i < num; i++) {
		  for(var j = i + 1; j < num; j++) {
				var diagonal = Math.sqrt(canvas.width * canvas.width + canvas.height * canvas.height)//窗口对角线长
				var dist = distance( balls[i], balls[j] );
		    if( dist < diagonal * 0.2) {
		      context.beginPath();
		      context.moveTo( balls[i].x, balls[i].y );
		     	context.lineTo( balls[j].x, balls[j].y );
					context.strokeStyle = setStrokeStyle(0,0,0,dist,diagonal*0.2);
					context.stroke();
		   	}
		 	}
		}

		//实现圆的移动，当圆碰到窗口时反弹
		for(var k = 0; k < num; k++) {
		  balls[k].x += balls[k].vx;
		  balls[k].y += balls[k].vy;

			if( balls[k].x + balls[k].r > canvas.width || balls[k].x - balls[k].r < 0) {
				balls[k].vx *= -1;
			}

			if( balls[k].y + balls[k].r > canvas.height || balls[k].y - balls[k].r < 0) {
		    balls[k].vy *= -1;
		  }
		}

	}//render


	(function loop(){
  	render();
  	requestAnimationFrame(loop);
	})();
