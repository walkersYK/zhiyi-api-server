<!-- 优化后的登录页面 -->
<template>
    <div class="login-container">
        <!-- 顶部导航栏 -->
        <header class="app-header">
            <div class="brand">
                <div class="logo"></div>
                <div class="title">智易快搭</div>
            </div>
            <nav class="main-nav">
                <ul>
                    <li class="active"><a>首页</a></li>
                    <li><a>表单系统</a></li>
                    <li><a>Gitee</a></li>
                    <li><a>操作手册</a></li>
                </ul>
            </nav>
        </header>

        <!-- 主内容区 -->
        <main class="login-main">
            <!-- 背景效果 -->
            <div class="bg-effects">
                <div class="color"></div>
                <div class="color"></div>
                <div class="color"></div>

                <div class="square" style="--i: 0"></div>
                <div class="square" style="--i: 1"></div>
                <div class="square" style="--i: 2"></div>
                <div class="square" style="--i: 3"></div>
                <div class="square" style="--i: 4"></div>
            </div>

            <!-- 登录表单 -->
            <div class="login-form-container">
                <div class="form-card">
                    <h2 class="form-title">平台登录</h2>
                    <el-form :model="user" :rules="rules" ref="loginRef">
                        <el-form-item prop="username">
                            <el-input
                                    prefix-icon="el-icon-user"
                                    placeholder="请输入账号"
                                    v-model="user.username"
                                    class="form-input">
                            </el-input>
                        </el-form-item>

                        <el-form-item prop="password">
                            <el-input
                                    prefix-icon="el-icon-lock"
                                    show-password
                                    placeholder="请输入密码"
                                    v-model="user.password"
                                    class="form-input">
                            </el-input>
                        </el-form-item>

                        <el-form-item prop="code">
                            <div class="code-input-group">
                                <el-input
                                        prefix-icon="el-icon-circle-check"
                                        placeholder="请输入验证码"
                                        v-model="user.code"
                                        class="form-input code-input">
                                </el-input>
                                <div class="code-wrapper">
                                    <valid-code @update:value="getCode" />
                                </div>
                            </div>
                        </el-form-item>

                        <el-form-item>
                            <el-button
                                    type="primary"
                                    class="login-btn"
                                    @click="login">
                                登 录
                            </el-button>
                        </el-form-item>

                        <div class="form-footer">
                            <div class="footer-link" @click="$router.push('/register')">
                                还没有账号？<span>注册</span>
                            </div>
                            <div class="footer-link">
                                <span>忘记密码</span>
                            </div>
                        </div>
                    </el-form>
                </div>
            </div>
        </main>
    </div>
</template>

<script>
    import ValidCode from "@/component_create/conview/ValidCode";

    export default {
        name: "Login",
        components: { ValidCode },
        data() {
            const validateCode = (rule, value, callback) => {
                if (!value) {
                    callback(new Error('请输入验证码'));
                } else if (value.toLowerCase() !== this.code) {
                    callback(new Error('验证码错误'));
                } else {
                    callback();
                }
            };

            return {
                code: '',
                user: {
                    username: '',
                    password: '',
                    code: ''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                    ],
                    code: [
                        { validator: validateCode, trigger: 'blur' }
                    ],
                }
            }
        },
        methods: {
            getCode(code) {
                this.code = code.toLowerCase();
            },
            login() {
                this.$refs.loginRef.validate(valid => {
                    if (valid) {
                        try {
                            this.$request.post('/user/login', {
                                userAccount: this.user.username,
                                userPassword: this.user.password
                            }).then(res => {
                                if (res.data) {
                                    this.$router.push('/home');
                                    this.$message.success('登录成功');
                                }
                            });
                        } catch (e) {
                            this.$message.error(e.message || '登录失败');
                        }
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .login-container {
        height: 100vh;
        display: flex;
        flex-direction: column;
        background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
    }

    .app-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0 40px;
        height: 70px;
        background: rgba(255, 255, 255, 0.9);
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        z-index: 100;
    }

    .brand {
        display: flex;
        align-items: center;
    }

    .logo {
        width: 40px;
        height: 40px;
        background: linear-gradient(135deg, #43cea2 0%, #185a9d 100%);
        border-radius: 8px;
        margin-right: 12px;
    }

    .title {
        font-size: 24px;
        font-weight: 600;
        color: #2c3e50;
        letter-spacing: 1px;
    }

    .main-nav ul {
        display: flex;
        list-style: none;
        margin: 0;
        padding: 0;
    }

    .main-nav li {
        margin: 0 20px;
        position: relative;
    }

    .main-nav li a {
        color: #34495e;
        font-size: 16px;
        font-weight: 500;
        text-decoration: none;
        padding: 5px 0;
        position: relative;
        transition: color 0.3s;
    }

    .main-nav li a:hover {
        color: #3498db;
    }

    .main-nav li.active a {
        color: #3498db;
    }

    .main-nav li.active a::after {
        content: '';
        position: absolute;
        bottom: 0;
        left: 0;
        width: 100%;
        height: 2px;
        background: #3498db;
        border-radius: 1px;
    }

    .login-main {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center;
        position: relative;
        overflow: hidden;
        padding: 20px;
    }

    .bg-effects {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
    }

    .bg-effects .color {
        position: absolute;
        filter: blur(150px);
        z-index: 1;
    }

    .bg-effects .color:nth-child(1) {
        top: 0;
        left: 100px;
        background: #ff359b;
        height: 600px;
        width: 100%;
    }

    .bg-effects .color:nth-child(2) {
        bottom: 0;
        right: 0;
        background: #fffd87;
        height: 500px;
        width: 800px;
    }

    .bg-effects .color:nth-child(3) {
        bottom: 50px;
        left: 0;
        background: #00d2ff;
        height: 500px;
        width: 500px;
    }

    .square {
        position: absolute;
        border-radius: 10px;
        background: rgba(255, 255, 255, 0.15);
        backdrop-filter: blur(5px);
        border: 1px solid rgba(255, 255, 255, 0.3);
        animation: float 10s ease-in-out infinite;
        animation-delay: calc(-1s * var(--i));
        z-index: 2;
    }

    @keyframes float {
        0%, 100% { transform: translateY(-40px); }
        50% { transform: translateY(40px); }
    }

    .square:nth-child(1) { width: 100px; height: 100px; top: 15%; right: 15%; }
    .square:nth-child(2) { width: 120px; height: 120px; top: 45%; left: 10%; }
    .square:nth-child(3) { width: 80px; height: 80px; bottom: 20%; right: 10%; }
    .square:nth-child(4) { width: 50px; height: 50px; bottom: 10%; left: 15%; }
    .square:nth-child(5) { width: 60px; height: 60px; top: 20%; left: 20%; }

    .login-form-container {
        position: relative;
        z-index: 10;
        width: 100%;
        max-width: 420px;
        padding: 20px;
    }

    .form-card {
        background: rgba(255, 255, 255, 0.85);
        backdrop-filter: blur(10px);
        border-radius: 16px;
        padding: 35px 30px;
        box-shadow: 0 8px 32px rgba(31, 38, 135, 0.2);
        border: 1px solid rgba(255, 255, 255, 0.4);
    }

    .form-title {
        text-align: center;
        font-size: 26px;
        font-weight: 600;
        color: #2c3e50;
        margin-bottom: 30px;
        position: relative;
    }

    .form-title::after {
        content: '';
        position: absolute;
        bottom: -12px;
        left: 50%;
        transform: translateX(-50%);
        width: 60px;
        height: 4px;
        background: linear-gradient(to right, #43cea2, #185a9d);
        border-radius: 2px;
    }

    .form-input {
        border-radius: 8px;
        border: 1px solid #e0e6ed;
        transition: all 0.3s;
    }

    .form-input:hover {
        border-color: #3498db;
    }

    .form-input:deep(.el-input__inner) {
        height: 46px;
        padding-left: 40px;
        background: rgba(245, 247, 250, 0.7);
    }

    .form-input:deep(.el-input__prefix) {
        left: 12px;
    }

    .code-input-group {
        display: flex;
        gap: 12px;
    }

    .code-input {
        flex: 1;
    }

    .code-wrapper {
        flex: 1;
        height: 46px;
        display: flex;
        align-items: center;
        justify-content: center;
        background: #f8f9fa;
        border-radius: 8px;
        border: 1px solid #e0e6ed;
        overflow: hidden;
    }

    .login-btn {
        width: 100%;
        height: 46px;
        font-size: 16px;
        font-weight: 500;
        border-radius: 8px;
        background: linear-gradient(135deg, #43cea2 0%, #185a9d 100%);
        border: none;
        transition: all 0.3s;
    }

    .login-btn:hover {
        transform: translateY(-2px);
        box-shadow: 0 4px 12px rgba(67, 206, 162, 0.4);
    }

    .form-footer {
        display: flex;
        justify-content: space-between;
        margin-top: 20px;
        font-size: 14px;
        color: #7f8c8d;
    }

    .footer-link {
        cursor: pointer;
        transition: color 0.3s;
    }

    .footer-link span {
        color: #3498db;
        font-weight: 500;
        position: relative;
    }

    .footer-link span::after {
        content: '';
        position: absolute;
        bottom: -2px;
        left: 0;
        width: 100%;
        height: 1px;
        background: #3498db;
        transform: scaleX(0);
        transition: transform 0.3s;
    }

    .footer-link:hover span {
        color: #2980b9;
    }

    .footer-link:hover span::after {
        transform: scaleX(1);
    }
</style>