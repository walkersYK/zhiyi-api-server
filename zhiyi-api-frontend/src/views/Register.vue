<!-- 优化后的注册页面 -->
<template>
    <div class="register-container">
        <!-- 背景效果 -->
        <div class="bg-effects">
            <div class="color"></div>
            <div class="color"></div>
            <div class="color"></div>
        </div>

        <!-- 注册卡片 -->
        <div class="register-card">
            <div class="card-image">
                <img src="@/assets/register.png" alt="注册" />
            </div>

            <div class="card-form">
                <h2 class="form-title">欢迎注册智易快搭</h2>

                <el-form :model="user" :rules="rules" ref="registerRef">
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

                    <el-form-item prop="confirmPass">
                        <el-input
                                prefix-icon="el-icon-lock"
                                show-password
                                placeholder="请确认密码"
                                v-model="user.confirmPass"
                                class="form-input">
                        </el-input>
                    </el-form-item>

                    <el-form-item prop="role">
                        <el-radio-group v-model="user.role" class="role-group">
                            <el-radio label="用户" class="role-radio"></el-radio>
                        </el-radio-group>
                    </el-form-item>

                    <el-form-item>
                        <el-button
                                type="primary"
                                class="register-btn"
                                @click="register">
                            注 册
                        </el-button>
                    </el-form-item>

                    <div class="form-footer">
                        <div class="footer-link" @click="$router.push('/login')">
                            已经有账号了？<span>登录</span>
                        </div>
                    </div>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Register",
        data() {
            const validatePassword = (rule, value, callback) => {
                if (!value) {
                    callback(new Error('请确认密码'));
                } else if (value !== this.user.password) {
                    callback(new Error('两次输入的密码不一致'));
                } else {
                    callback();
                }
            };

            return {
                user: {
                    username: '',
                    password: '',
                    confirmPass: '',
                    role: '用户'
                },
                rules: {
                    username: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 6, message: '密码长度至少6位', trigger: 'blur' }
                    ],
                    confirmPass: [
                        { validator: validatePassword, trigger: 'blur' }
                    ],
                    role: [
                        { required: true, message: '请选择角色', trigger: 'change' },
                    ]
                }
            }
        },
        methods: {
            register() {
                this.$refs.registerRef.validate(valid => {
                    if (valid) {
                        try {
                            this.$request.post('/user/register', {
                                userAccount: this.user.username,
                                userPassword: this.user.password,
                                checkPassword: this.user.confirmPass
                            }).then(res => {
                                if (res.data) {
                                    this.$router.push('/view');
                                    this.$message.success('注册成功');
                                }
                            });
                        } catch (error) {
                            this.$message.error(error.message || '注册失败');
                        }
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .register-container {
        height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        position: relative;
        overflow: hidden;
        background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
    }

    .bg-effects {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        z-index: 1;
    }

    .bg-effects .color {
        position: absolute;
        filter: blur(150px);
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

    .register-card {
        display: flex;
        width: 900px;
        height: 500px;
        background: rgba(255, 255, 255, 0.85);
        backdrop-filter: blur(10px);
        border-radius: 16px;
        overflow: hidden;
        box-shadow: 0 15px 35px rgba(0, 0, 0, 0.15);
        z-index: 10;
    }

    .card-image {
        flex: 1;
        background: linear-gradient(135deg, #43cea2 0%, #185a9d 100%);
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 30px;
    }

    .card-image img {
        max-width: 100%;
        max-height: 100%;
        object-fit: contain;
    }

    .card-form {
        flex: 1;
        padding: 40px;
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .form-title {
        font-size: 24px;
        font-weight: 600;
        color: #2c3e50;
        text-align: center;
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
        margin-bottom: 18px;
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

    .role-group {
        width: 100%;
        display: flex;
        justify-content: center;
        margin: 10px 0 20px;
    }

    .role-radio:deep(.el-radio__label) {
        font-size: 15px;
        color: #2c3e50;
    }

    .register-btn {
        width: 100%;
        height: 46px;
        font-size: 16px;
        font-weight: 500;
        border-radius: 8px;
        background: linear-gradient(135deg, #43cea2 0%, #185a9d 100%);
        border: none;
        transition: all 0.3s;
    }

    .register-btn:hover {
        transform: translateY(-2px);
        box-shadow: 0 4px 12px rgba(67, 206, 162, 0.4);
    }

    .form-footer {
        text-align: center;
        margin-top: 25px;
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