<template>
  <div>
    <button class="Documents-btn" @click="showCode = true">
      <span class="folderContainer">
        <!-- SVG图标部分保持不变 -->
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 146 113"
          height="113"
          width="146"
          class="fileBack"
        >
          <path
            fill="url(#paint0_linear_117_4)"
            d="M0 4C0 1.79086 1.79086 0 4 0H50.3802C51.8285 0 53.2056 0.627965 54.1553 1.72142L64.3303 13.4371C65.2799 14.5306 66.657 15.1585 68.1053 15.1585H141.509C143.718 15.1585 145.509 16.9494 145.509 19.1585V109C145.509 111.209 143.718 113 141.509 113H3.99999C1.79085 113 0 111.209 0 109V4Z"
          ></path>
          <defs>
            <linearGradient
              gradientUnits="userSpaceOnUse"
              y2="95.4804"
              x2="72.93"
              y1="0"
              x1="0"
              id="paint0_linear_117_4"
            >
              <stop stop-color="#8F88C2"></stop>
              <stop stop-color="#5C52A2" offset="1"></stop>
            </linearGradient>
          </defs>
        </svg>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 88 99"
          height="99"
          width="88"
          class="filePage"
        >
          <rect fill="url(#paint0_linear_117_6)" height="99" width="88"></rect>
          <defs>
            <linearGradient
              gradientUnits="userSpaceOnUse"
              y2="160.5"
              x2="81"
              y1="0"
              x1="0"
              id="paint0_linear_117_6"
            >
              <stop stop-color="white"></stop>
              <stop stop-color="#686868" offset="1"></stop>
            </linearGradient>
          </defs>
        </svg>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 160 79"
          height="79"
          width="160"
          class="fileFront"
        >
          <path
            fill="url(#paint0_linear_117_5)"
            d="M0.29306 12.2478C0.133905 9.38186 2.41499 6.97059 5.28537 6.97059H30.419H58.1902C59.5751 6.97059 60.9288 6.55982 62.0802 5.79025L68.977 1.18034C70.1283 0.410771 71.482 0 72.8669 0H77H155.462C157.87 0 159.733 2.1129 159.43 4.50232L150.443 75.5023C150.19 77.5013 148.489 79 146.474 79H7.78403C5.66106 79 3.9079 77.3415 3.79019 75.2218L0.29306 12.2478Z"
          ></path>
          <defs>
            <linearGradient
              gradientUnits="userSpaceOnUse"
              y2="82.8317"
              x2="66.9106"
              y1="8.71323"
              x1="38.7619"
              id="paint0_linear_117_5"
            >
              <stop stop-color="#C3BBFF"></stop>
              <stop stop-color="#51469A" offset="1"></stop>
            </linearGradient>
          </defs>
        </svg>
      </span>
      <p class="text">Code</p>
    </button>
    <div v-if="showCode" class="code-modal-bg" @click.self="showCode = false">
      <div class="code-modal">
        <div class="code-modal-header">
          拖拽生成HTML源码
          <button class="copy-btn" @click="copyCode">复制</button>
          <button class="close-btn" @click="showCode = false">×</button>
        </div>
        <pre class="code-content"><code>{{ htmlCode }}</code></pre>
        <div v-if="copied" class="copied-tip">已复制！</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "code",
  props: {
    list: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      showCode: false,
      copied: false
    }
  },
  computed: {
    htmlCode() {
      // 生成body内容，所有视觉样式全部内联
      const getWidgetStyle = (item) => {
        let style = [
          `position:absolute`,
          `left:${item.x}px`,
          `top:${item.y}px`,
          `width:${item.w}px`,
          `height:${item.h}px`,
          `border-radius:14px`,
          `background:linear-gradient(120deg,#e0e7ff 0%,#f8fafc 100%)`,
          `box-shadow:0 0 16px 2px #a5b4fc33 inset`,
          `color:#3730a3`,
          `font-size:32px`,
          `text-align:center`,
          `line-height:${item.h}px`,
          `outline:2px solid #a5b4fc80`,
          `transition:box-shadow 0.3s,background 0.3s,color 0.3s,transform 0.2s`,
          `z-index:1`,
        ];
        if (item.styles) {
          for (const k in item.styles) {
            if (item.styles[k]) style.push(`${k}:${item.styles[k]}`);
          }
        }
        return style.join(';');
      };
      const bodyContent = this.list.map(item => {
        const style = getWidgetStyle(item);
        if (item.component === 'CustomText') {
          return `<div style=\"${style}\">${item.value || item.label || ''}</div>`;
        } else if (item.component === 'BarChart') {
          return `<div style=\"${style}\"><!-- 这里可插入ECharts等图表代码 --></div>`;
        } else if (item.component === 'ContentInfo') {
          return `<div style=\"${style}\">${item.label || ''}</div>`;
        } else {
          return `<div style=\"${style}\">${item.label || ''}</div>`;
        }
      }).join('\n');
      // 生成完整HTML模板
      return `<!DOCTYPE html>\n<html lang=\"zh-CN\">\n<head>\n  <meta charset=\"UTF-8\">\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n  <title>导出页面</title>\n  <style>\n    body { position: relative; min-height: 100vh; background: #f8fafc; margin: 0; }\n  </style>\n</head>\n<body>\n${bodyContent}\n<script>\n// 可在此处插入交互脚本\n<\/script>\n</body>\n</html>`;
    }
  },
  methods: {
    copyCode() {
      navigator.clipboard.writeText(this.htmlCode).then(() => {
        this.copied = true;
        setTimeout(() => (this.copied = false), 1200);
      });
    }
  }
}
</script>

<style scoped>
    .Documents-btn {
        display: flex;
        align-items: center;
        justify-content: flex-start;
        width: fit-content;
        height: 45px;
        border: none;
        padding: 0px 15px;
        border-radius: 20px;
        background-color: rgb(49, 49, 83);
        gap: 10px;
        cursor: pointer;
        transition: all 0.3s;
    }
    .folderContainer {
        width: 40px;
        height: fit-content;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-end;
        position: relative;
    }
    .fileBack {
        z-index: 1;
        width: 80%;
        height: auto;
    }
    .filePage {
        width: 50%;
        height: auto;
        position: absolute;
        z-index: 2;
        transition: all 0.3s ease-out;
    }
    .fileFront {
        width: 85%;
        height: auto;
        position: absolute;
        z-index: 3;
        opacity: 0.95;
        transform-origin: bottom;
        transition: all 0.3s ease-out;
    }
    .text {
        color: white;
        font-size: 14px;
        font-weight: 600;
        letter-spacing: 0.5px;
    }
    .Documents-btn:hover .filePage {
        transform: translateY(-5px);
    }
    .Documents-btn:hover {
        background-color: rgb(58, 58, 94);
    }
    .Documents-btn:hover .fileFront {
        transform: rotateX(30deg);
    }
    .code-modal {
        position: relative;
        z-index: 2;
        background: #fff;
        border-radius: 10px;
        box-shadow: 0 4px 32px 0 #0002;
        padding: 24px 18px 18px 18px;
        min-width: 320px;
        max-width: 90vw;
        max-height: 80vh;
        overflow: auto;
        display: flex;
        flex-direction: column;
        align-items: flex-start;
    }
    .code-modal-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 15px;
        padding-bottom: 10px;
        border-bottom: 1px solid #eee;
    }
    .copy-btn {
        background-color: #4CAF50;
        color: white;
        padding: 8px 12px;
        border-radius: 8px;
        border: none;
        cursor: pointer;
        font-size: 14px;
        font-weight: 600;
        transition: background-color 0.3s;
    }
    .copy-btn:hover {
        background-color: #388E3C;
    }
    .close-btn {
        position: absolute;
        top: 10px;
        right: 14px;
        background: none;
        border: none;
        font-size: 22px;
        color: #888;
        cursor: pointer;
        z-index: 3;
    }
    .code-content {
        background: #f7f8fa;
        color: #222;
        font-size: 13px;
        border-radius: 6px;
        padding: 12px;
        margin: 0;
        width: 100%;
        overflow-x: auto;
        font-family: 'Fira Mono', 'Consolas', 'Menlo', monospace;
        white-space: pre-wrap;
        word-break: break-all;
    }
    .copied-tip {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border-radius: 8px;
        font-size: 16px;
        font-weight: 600;
        z-index: 10;
        opacity: 0.9;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
    }
    @media (max-width: 600px) {
        .code-modal-content {
            min-width: 0;
            width: 98vw;
            padding: 12px 4px 8px 4px;
        }
        .code-block {
            font-size: 11px;
            padding: 6px;
        }
    }


</style>