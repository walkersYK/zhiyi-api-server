<template>
    <div class="draw-container">
        <el-card shadow="never" class="draw-card">
            <div class="draw-header">
                <h3 class="draw-title">智能图表生成</h3>
                <div class="draw-subtitle">上传数据文件，自动生成可视化图表</div>
            </div>

            <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
                <el-form-item label="分析目标" prop="goal">
                    <el-input
                            type="textarea"
                            v-model="form.goal"
                            placeholder="请输入您的分析目标，例如：分析用户增长趋势"
                            :autosize="{minRows: 3, maxRows: 5}"
                            show-word-limit
                            maxlength="300"></el-input>
                </el-form-item>

                <el-form-item label="图表名称" prop="name">
                    <el-input v-model="form.name" placeholder="请输入图表名称"></el-input>
                </el-form-item>

                <el-form-item label="图表类型" prop="chartType">
                    <el-select v-model="form.chartType" placeholder="请选择图表类型">
                        <el-option label="折线图" value="line"></el-option>
                        <el-option label="柱状图" value="bar"></el-option>
                        <el-option label="堆叠图" value="stack"></el-option>
                        <el-option label="饼图" value="pie"></el-option>
                        <el-option label="雷达图" value="radar"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="数据文件" prop="file" required>
                    <el-upload
                            class="upload-demo"
                            drag
                            :limit="1"
                            :on-change="handleFileChange"
                            :auto-upload="false"
                            :file-list="fileList"
                            accept=".csv,.xlsx,.xls">
                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                        <div class="el-upload__tip" slot="tip">支持CSV/Excel文件，文件大小不超过10MB</div>
                    </el-upload>
                </el-form-item>

                <el-form-item>
                    <el-button
                            type="primary"
                            @click="submitForm"
                            :loading="submitting"
                            :disabled="!fileList.length">
                        {{ submitting ? '分析中...' : '开始分析' }}
                    </el-button>
                    <el-button @click="resetForm">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>

        <!-- 结果展示 -->
        <div class="result-container" v-if="showResult">
            <el-row :gutter="20">
                <el-col :span="12">
                    <el-card shadow="never" class="result-card">
                        <div slot="header" class="result-header">
                            <span>分析结论</span>
                        </div>
                        <div class="result-content">
                            {{ chart.genResult || '暂无分析结果' }}
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="12">
                    <el-card shadow="never" class="result-card">
                        <div slot="header" class="result-header">
                            <span>可视化图表</span>
                        </div>
                        <div class="chart-container">
                            <e-charts :option="option" autoresize v-if="option" />
                            <div v-else class="empty-chart">
                                <el-empty description="暂无图表数据"></el-empty>
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
    import ECharts from 'vue-echarts'

    export default {
        components: {
            ECharts
        },
        data() {
            return {
                form: {
                    goal: '',
                    name: '',
                    chartType: ''
                },
                rules: {
                    goal: [
                        { required: true, message: '请输入分析目标', trigger: 'blur' }
                    ],
                    name: [
                        { required: true, message: '请输入图表名称', trigger: 'blur' },
                        { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur' }
                    ],
                    chartType: [
                        { required: true, message: '请选择图表类型', trigger: 'change' }
                    ]
                },
                fileList: [],
                chart: null,
                option: null,
                submitting: false,
                showResult: false
            }
        },
        methods: {
            handleFileChange(file, fileList) {
                const validTypes = [
                    'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
                    'application/vnd.ms-excel',
                    'text/csv'
                ];

                if (!validTypes.includes(file.raw.type)) {
                    this.$message.error('只能上传Excel或CSV文件');
                    this.fileList = [];
                    return false;
                }

                if (file.size > 10 * 1024 * 1024) {
                    this.$message.error('文件大小不能超过10MB');
                    this.fileList = [];
                    return false;
                }

                this.fileList = fileList.slice(-1);
                return true;
            },

            async submitForm() {
                try {
                    await this.$refs.formRef.validate();

                    if (this.fileList.length === 0) {
                        this.$message.warning('请先上传数据文件');
                        return;
                    }

                    this.submitting = true;

                    const formData = new FormData();
                    formData.append('file', this.fileList[0].raw);

                    const response = await this.$request.post(
                        'chart/gen?goal=' + this.form.goal +
                        '&name=' + this.form.name +
                        '&chartType=' + this.form.chartType,
                        formData,
                        {
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        }
                    );

                    if (response.data.data) {
                        this.chart = response.data.data;
                        this.showResult = true;
                        try {
                            this.option = JSON.parse(this.chart.genChart);
                        } catch (e) {
                            console.error('解析图表配置失败:', e);
                            this.option = null;
                        }
                        this.$message.success('分析成功');
                    } else {
                        this.$message.error('分析失败，请重试');
                    }
                } catch (error) {
                    console.error('分析出错:', error);
                    this.$message.error('分析出错，请检查输入后重试');
                } finally {
                    this.submitting = false;
                }
            },

            resetForm() {
                this.$refs.formRef.resetFields();
                this.fileList = [];
                this.showResult = false;
                this.chart = null;
                this.option = null;
            }
        }
    }
</script>

<style scoped>
    .draw-container {
        padding: 20px;
    }

    .draw-card {
        margin-bottom: 20px;
        border-radius: 8px;
    }

    .draw-header {
        margin-bottom: 20px;
    }

    .draw-title {
        font-size: 18px;
        font-weight: 600;
        color: var(--text-primary);
        margin-bottom: 8px;
    }

    .draw-subtitle {
        font-size: 14px;
        color: var(--text-secondary);
    }

    .result-container {
        margin-top: 20px;
    }

    .result-card {
        border-radius: 8px;
        height: 100%;
    }

    .result-header {
        font-weight: 600;
        color: var(--text-primary);
    }

    .result-content {
        min-height: 300px;
        white-space: pre-wrap;
        line-height: 1.6;
    }

    .chart-container {
        height: 400px;
    }

    .empty-chart {
        height: 300px;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .upload-demo {
        width: 100%;
    }
</style>