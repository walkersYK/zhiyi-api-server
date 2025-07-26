<template>
    <div class="app-container">
        <!-- 页面标题和操作按钮 -->
        <div class="header">
            <h1><i class="fas fa-plug"></i> 接口详情</h1>
            <div class="actions">
                <el-button type="primary" icon="el-icon-back" @click="$router.go(-1)">返回列表</el-button>
            </div>
        </div>

        <!-- 接口信息卡片 -->
        <div class="card">
            <div class="card-title">
                <i class="fas fa-info-circle"></i> 接口基本信息
            </div>

            <div class="info-grid">
                <div class="info-item">
                    <div class="info-label">接口状态</div>
                    <div class="info-value">
            <span class="status-badge" :class="`status-${interfaceInfo.status === 1 ? 'active' : 'inactive'}`">
              {{ interfaceInfo.status === 1 ? '已上线' : '未上线' }}
            </span>
                    </div>
                </div>

                <div class="info-item">
                    <div class="info-label">接口描述</div>
                    <div class="info-value">{{ interfaceInfo.description || '暂无描述' }}</div>
                </div>

                <div class="divider"></div>

                <div class="info-item">
                    <div class="info-label">请求参数</div>
                    <div class="info-value">
                        <pre style="background: #f8f8f8; padding: 16px; border-radius: 6px; font-size: 14px;">{{ interfaceInfo.requestHeader || '暂无请求参数信息' }}</pre>
                    </div>
                </div>
            </div>
        </div>

        <!-- 标签导航 -->
        <div class="nav-tabs">
            <div
                    class="tab-item"
                    :class="{ active: showPage === 'home' }"
                    @click="showPage = 'home'"
            >
                <i class="fas fa-code"></i> 示例代码
            </div>
            <div
                    class="tab-item"
                    :class="{ active: showPage === 'about' }"
                    @click="showPage = 'about'"
            >
                <i class="fas fa-terminal"></i> 在线调试工具
            </div>
        </div>

        <!-- 示例代码区域 -->
        <div class="card" v-show="showPage === 'home'">
            <div class="card-title">
                <i class="fas fa-code"></i> 示例代码
            </div>

            <div class="code-block">
                <div class="code-header">
                    <span>JavaScript 调用示例</span>
                    <button class="copy-btn" @click="copyCode">
                        <i class="fas fa-copy"></i> 复制代码
                    </button>
                </div>
                <div class="code-content">
                    <pre><code class="language-javascript">{{ code }}</code></pre>
                </div>
            </div>
        </div>

        <!-- 在线调试工具区域 -->
        <div class="card" v-show="showPage === 'about'">
            <div class="card-title">
                <i class="fas fa-terminal"></i> 在线调试工具
            </div>

            <div class="form-container">
                <el-form :model="invokeForm" ref="invokeForm" label-width="0">
                    <el-form-item>
                        <el-input
                                type="textarea"
                                :rows="8"
                                placeholder="请输入 JSON 格式的请求参数"
                                v-model="invokeForm.userRequestParams"
                        ></el-input>
                    </el-form-item>

                    <div style="display: flex; justify-content: flex-end;">
                        <el-button
                                type="primary"
                                @click="invokeInterfaceInfo(invokeForm.userRequestParams)"
                                :loading="invokeLoading"
                        >
                            <i class="fas fa-paper-plane"></i> 调用接口
                        </el-button>
                    </div>
                </el-form>

                <div class="result-card">
                    <div class="result-header">返回结果</div>
                    <div class="response-content">
                        <pre>{{ res || '调用结果将显示在这里...' }}</pre>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'interfaceInfo',
        data() {
            return {
                showPage: 'home',
                interfaceInfo: {
                    status: 0,
                    description: '',
                    requestHeader: ''
                },
                invokeForm: {
                    userRequestParams: JSON.stringify({
                        userId: "10001",
                        fields: ["name", "email", "status"]
                    }, null, 2),
                },
                invokeLoading: false,
                res: '',
                code: `// 接口调用示例
async function callApi() {
  const response = await fetch('/api/interface/invoke', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer YOUR_TOKEN'
    },
    body: JSON.stringify({
      id: 123,
      userRequestParams: {
        // 你的请求参数
      }
    })
  });

  if (!response.ok) {
    throw new Error('请求失败');
  }

  return await response.json();
}

callApi().then(data => {
  console.log('调用结果:', data);
}).catch(error => {
  console.error('调用失败:', error);
});`
            };
        },
        created() {
            this.loadInterfaceInfo();
        },
        methods: {
            async loadInterfaceInfo() {
                try {
                    const id = Number(this.$route.params.id);
                    const response = await this.$request.get('/interfaceInfo/get', {
                        params: {
                            id: id+1,
                        }});
                    this.interfaceInfo = response.data.data;
                } catch (error)  {
                    console.error('请求失败:', error);
                    this.$message.error('获取接口信息失败');
                }
            },
            async invokeInterfaceInfo(value){
                try {
                    this.invokeLoading = true;
                    const id = Number(this.$route.params.id);
                    const response = await this.$request.post('/interfaceInfo/invoke', {
                        id: id+1,
                        userRequestParams: value,
                    });
                    this.res = JSON.stringify(response.data, null, 2);
                    this.$message.success("调用成功");
                } catch (e) {
                    console.error(e);
                    this.$message.error('调用失败');
                    this.res = e.response?.data?.message || e.message;
                } finally {
                    this.invokeLoading = false;
                }
            },
            copyCode() {
                if (navigator.clipboard) {
                    navigator.clipboard.writeText(this.code).then(() => {
                        this.$message.success('代码已复制到剪贴板');
                    }).catch(err => {
                        this.copyCodeFallback();
                    });
                } else {
                    this.copyCodeFallback();
                }
            },
            copyCodeFallback() {
                const textArea = document.createElement('textarea');
                textArea.value = this.code;
                document.body.appendChild(textArea);
                textArea.focus();
                textArea.select();

                try {
                    const successful = document.execCommand('copy');
                    if (successful) {
                        this.$message.success('代码已复制到剪贴板');
                    }
                } catch (err) {
                    this.$message.error('复制失败，请手动复制');
                }

                document.body.removeChild(textArea);
            }
        }
    }
</script>

<style scoped>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    :root {
        --primary-color: #5c6bc0;
        --primary-light: #8e99f3;
        --primary-dark: #26418f;
        --secondary-color: #26a69a;
        --text-dark: #2c3e50;
        --text-light: #7f8c8d;
        --bg-light: #f5f7fa;
        --bg-card: #ffffff;
        --border-color: #e0e0e0;
        --success-color: #4caf50;
        --warning-color: #ff9800;
        --error-color: #f44336;
        --shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
    }

    .app-container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 20px;
        background-color: var(--bg-light);
    }

    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 24px;
        padding-bottom: 16px;
        border-bottom: 1px solid var(--border-color);
    }

    .header h1 {
        font-size: 24px;
        font-weight: 600;
        color: var(--text-dark);
    }

    .header .actions {
        display: flex;
        gap: 12px;
    }

    .card {
        background: var(--bg-card);
        border-radius: 12px;
        box-shadow: var(--shadow);
        padding: 24px;
        margin-bottom: 24px;
        transition: all 0.3s ease;
    }

    .card:hover {
        box-shadow: 0 6px 16px rgba(0, 0, 0, 0.12);
    }

    .card-title {
        font-size: 18px;
        font-weight: 600;
        color: var(--primary-dark);
        margin-bottom: 20px;
        display: flex;
        align-items: center;
    }

    .card-title i {
        margin-right: 10px;
        font-size: 20px;
    }

    .info-grid {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
        gap: 20px;
    }

    .info-item {
        padding: 12px 0;
    }

    .info-label {
        font-weight: 500;
        color: var(--text-light);
        margin-bottom: 8px;
        font-size: 14px;
    }

    .info-value {
        font-size: 16px;
        color: var(--text-dark);
        word-break: break-word;
    }

    .status-badge {
        display: inline-block;
        padding: 4px 12px;
        border-radius: 20px;
        font-size: 13px;
        font-weight: 500;
    }

    .status-active {
        background-color: rgba(76, 175, 80, 0.15);
        color: var(--success-color);
    }

    .status-inactive {
        background-color: rgba(255, 152, 0, 0.15);
        color: var(--warning-color);
    }

    .nav-tabs {
        display: flex;
        border-bottom: 1px solid var(--border-color);
        margin-bottom: 24px;
    }

    .tab-item {
        padding: 12px 24px;
        cursor: pointer;
        font-weight: 500;
        color: var(--text-light);
        position: relative;
        transition: all 0.3s ease;
    }

    .tab-item.active {
        color: var(--primary-color);
    }

    .tab-item.active::after {
        content: '';
        position: absolute;
        bottom: -1px;
        left: 0;
        right: 0;
        height: 3px;
        background-color: var(--primary-color);
        border-radius: 3px 3px 0 0;
    }

    .tab-item:hover:not(.active) {
        color: var(--primary-light);
    }

    .form-container {
        max-width: 800px;
        margin: 0 auto;
    }

    .code-block {
        position: relative;
        background: #2d2d2d;
        border-radius: 8px;
        overflow: hidden;
        margin-top: 20px;
    }

    .code-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px 16px;
        background: #3a3a3a;
        color: #ccc;
        font-size: 14px;
    }

    .code-content {
        padding: 16px;
        overflow-x: auto;
    }

    pre {
        margin: 0;
        font-family: 'Fira Code', 'Consolas', monospace;
    }

    .result-card {
        background: #f9f9f9;
        border: 1px solid var(--border-color);
        border-radius: 8px;
        padding: 16px;
        margin-top: 24px;
        position: relative;
    }

    .result-header {
        position: absolute;
        top: -12px;
        left: 16px;
        background: var(--bg-card);
        padding: 0 8px;
        font-weight: 500;
        color: var(--text-light);
        font-size: 14px;
    }

    .response-content {
        padding-top: 8px;
        font-family: 'Fira Code', 'Consolas', monospace;
        white-space: pre-wrap;
        word-break: break-all;
        line-height: 1.5;
        max-height: 300px;
        overflow-y: auto;
    }

    .btn-primary {
        background: var(--primary-color);
        border: none;
        color: white;
        padding: 10px 24px;
        border-radius: 6px;
        font-weight: 500;
        cursor: pointer;
        transition: all 0.3s ease;
        font-size: 14px;
    }

    .btn-primary:hover {
        background: var(--primary-dark);
        transform: translateY(-2px);
    }

    .btn-primary:active {
        transform: translateY(0);
    }

    .copy-btn {
        background: #3a3a3a;
        border: 1px solid #555;
        color: #ccc;
        padding: 6px 12px;
        border-radius: 4px;
        cursor: pointer;
        font-size: 13px;
        transition: all 0.2s ease;
    }

    .copy-btn:hover {
        background: #4a4a4a;
        color: white;
    }

    .divider {
        height: 1px;
        background: var(--border-color);
        margin: 24px 0;
    }

    @media (max-width: 768px) {
        .info-grid {
            grid-template-columns: 1fr;
        }

        .nav-tabs {
            overflow-x: auto;
            white-space: nowrap;
            -webkit-overflow-scrolling: touch;
        }

        .card {
            padding: 16px;
        }
    }
</style>