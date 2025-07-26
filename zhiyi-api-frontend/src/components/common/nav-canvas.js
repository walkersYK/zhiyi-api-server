export class NavCanvas {
  constructor(el, tabSelector, selectedTabIndex) {
    this.canvas = document.getElementById(el);
    this.tabs = document.querySelectorAll(tabSelector);
    this.ctx = this.canvas.getContext('2d');

    this.config = {
      currentIndex: selectedTabIndex || 0,
      nextIndex: 0,
      animating: false,
      progress: 0,
      speed: 0.1,
      waveHeight: 3,
      waveColor: '#1a56db',
      waveOpacity: 0.8
    };

    this.init();
    this.startAnimation();
  }

  init() {
    this.resize();
    this.setupCanvas();
  }

  setupCanvas() {
    const { devicePixelRatio } = window;
    const rect = this.canvas.getBoundingClientRect();

    this.canvas.width = rect.width * devicePixelRatio;
    this.canvas.height = rect.height * devicePixelRatio;
    this.canvas.style.width = `${rect.width}px`;
    this.canvas.style.height = `${rect.height}px`;

    this.ctx.scale(devicePixelRatio, devicePixelRatio);
    this.ctx.globalCompositeOperation = 'source-over';
  }

  resize() {
    this.tabPositions = this.calculateTabPositions();
    this.setupCanvas();
  }

  calculateTabPositions() {
    const positions = [];
    let totalWidth = 0;

    this.tabs.forEach(tab => {
      const rect = tab.getBoundingClientRect();
      positions.push({
        left: totalWidth,
        width: rect.width,
        center: totalWidth + rect.width / 2
      });
      totalWidth += rect.width;
    });

    return positions;
  }

  startAnimation() {
    this.animationId = requestAnimationFrame(this.animate.bind(this));
  }

  animate() {
    this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);

    if (this.config.animating) {
      this.config.progress += this.config.speed;

      if (this.config.progress >= 1) {
        this.config.progress = 1;
        this.config.animating = false;
        this.config.currentIndex = this.config.nextIndex;
      }
    }

    this.drawWave();
    this.animationId = requestAnimationFrame(this.animate.bind(this));
  }

  drawWave() {
    const { currentIndex, nextIndex, progress } = this.config;
    const currentPos = this.tabPositions[currentIndex];
    let targetPos = currentPos;

    if (this.config.animating) {
      const nextPos = this.tabPositions[nextIndex];
      targetPos = {
        left: currentPos.left + (nextPos.left - currentPos.left) * progress,
        width: currentPos.width + (nextPos.width - currentPos.width) * progress,
        center: currentPos.center + (nextPos.center - currentPos.center) * progress
      };
    }

    this.ctx.fillStyle = this.config.waveColor;
    this.ctx.globalAlpha = this.config.waveOpacity;

    const waveHeight = this.config.waveHeight;
    const bottom = this.canvas.height / window.devicePixelRatio;

    // 绘制流动波浪效果
    this.ctx.beginPath();
    this.ctx.moveTo(0, bottom);

    // 波浪左侧
    this.ctx.lineTo(targetPos.left, bottom);

    // 波浪曲线
    const waveWidth = targetPos.width * 0.8;
    const cp1x = targetPos.center - waveWidth * 0.5;
    const cp2x = targetPos.center + waveWidth * 0.5;

    this.ctx.bezierCurveTo(
        cp1x, bottom - waveHeight,
        cp2x, bottom - waveHeight,
        targetPos.left + targetPos.width, bottom
    );

    // 波浪右侧
    this.ctx.lineTo(this.canvas.width / window.devicePixelRatio, bottom);
    this.ctx.lineTo(this.canvas.width / window.devicePixelRatio, bottom + 10);
    this.ctx.lineTo(0, bottom + 10);
    this.ctx.closePath();
    this.ctx.fill();

    // 重置透明度
    this.ctx.globalAlpha = 1;
  }

  toggle(index) {
    if (index !== this.config.currentIndex && !this.config.animating) {
      this.config.nextIndex = index;
      this.config.animating = true;
      this.config.progress = 0;
    }
  }

  destroy() {
    cancelAnimationFrame(this.animationId);
  }
}