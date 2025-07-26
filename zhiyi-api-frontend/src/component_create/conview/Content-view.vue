<template>
    <div id="CT_image" style="width: 100% ; height: 100%">
        <el-card
                id="CT_image_1"
                class="box-card"
                style="
            border-radius: 8px;
            width: 100%;
            height: 100%;
            margin-bottom: -30px;
          "
        >
            <div class="demo-image__preview1">
                <div
                        v-loading="loading"
                        element-loading-text="上传图片中"
                        element-loading-spinner="el-icon-loading"
                >
                    <el-image
                            :src="url_1"
                            class="image_1"
                            :preview-src-list="srcList"
                            style="border-radius: 3px 3px 0 0"
                    >
                        <div slot="error">
                            <div slot="placeholder" class="error">
                                <el-button
                                        v-show="showbutton"
                                        type="primary"
                                        icon="el-icon-upload"
                                        class="download_bt"
                                        v-on:click="true_upload"
                                >
                                    上传图像
                                    <input
                                            ref="upload"
                                            style="display: none"
                                            name="file"
                                            type="file"
                                            @change="update"
                                    />
                                </el-button>
                            </div>
                        </div>
                    </el-image>
                </div>
                <div class="img_info_1" style="border-radius: 0 0 5px 5px">
                    <span style="color: white; letter-spacing: 6px">原始图像</span>
                </div>
            </div>
            <div class="demo-image__preview2">
                <div
                        v-loading="loading"
                        element-loading-text="处理中,请耐心等待"
                        element-loading-spinner="el-icon-loading"
                >
                    <el-image
                            :src="url_2"
                            class="image_1"
                            :preview-src-list="srcList1"
                            style="border-radius: 3px 3px 0 0"
                    >
                        <div slot="error">
                            <div slot="placeholder" class="error">{{ wait_return }}</div>
                        </div>
                    </el-image>
                </div>
                <div class="img_info_1" style="border-radius: 0 0 5px 5px">
                    <span style="color: white; letter-spacing: 4px">检测结果</span>
                </div>
            </div>
        </el-card>
    </div>

</template>

<script>
    import axios from "axios";

    export default {
        name: "content-view",
        data() {
            return {
                server_url: "http://127.0.0.1:5000/api/v1/prediction/commodity",
                activeName: "first",
                active: 0,
                url_1: "",
                url_2: "",
                textarea: "",
                srcList: [],
                srcList1: [],
                feature_list: [],
                feature_list_1: [],
                feat_list: [],
                url: "",
                wait_return: "等待上传",
                wait_upload: "等待上传",
                loading: false,
                table: false,
                isNav: false,
                showbutton: true,
                percentage: 0,
                fullscreenLoading: false,
                dialogTableVisible: false,
            };
        },
        methods: {
            true_upload() {
                this.$refs.upload.click();
            },
            // 上传文件
            update(e) {
                this.percentage = 0;
                this.dialogTableVisible = true;
                this.url_1 = "";
                this.url_2 = "";
                this.srcList = [];
                this.srcList1 = [];
                this.wait_return = "";
                this.wait_upload = "";
                this.feature_list = [];
                this.feat_list = [];
                this.fullscreenLoading = true;
                this.loading = true;
                this.showbutton = false;
                let file = e.target.files[0];
                this.url_1 = this.$options.methods.getObjectURL(file);
                let param = new FormData(); //创建form对象
                param.append("file", file, file.name); //通过append向form对象添加数据
                var timer = setInterval(() => {
                    this.myFunc();
                }, 30);
                let config = {
                    headers: { "Content-Type": "multipart/form-data" },
                }; //添加请求头
                axios
                    .post(this.server_url + "/upload", param, config)
                    .then((response) => {
                        this.percentage = 100;
                        clearInterval(timer);
                        this.url_1 = response.data.image_url;
                        this.srcList.push(this.url_1);
                        this.url_2 = response.data.draw_url;
                        this.srcList1.push(this.url_2);
                        this.fullscreenLoading = false;
                        this.loading = false;

                        this.feat_list = Object.keys(response.data.image_info);

                        for (var i = 0; i < this.feat_list.length; i++) {
                            response.data.image_info[this.feat_list[i]][2] = this.feat_list[i];
                            this.feature_list.push(response.data.image_info[this.feat_list[i]]);
                        }

                        this.feature_list.push(response.data.image_info);
                        this.feature_list_1 = this.feature_list[0];
                        this.dialogTableVisible = false;
                        this.percentage = 0;
                        this.notice1();
                    });
            },
        },
    }
</script>

<style scoped>
    #CT_image_1 {
        width: 90%;
        height: 40%;
        margin: 0px auto;
        padding: 0px auto;
        margin-right: 180px;
        margin-bottom: 0px;
        border-radius: 4px;
    }
    #CT_image {
        margin-top: 5px;
    }
    .box-card {
        width: 680px;
        height: 200px;
        border-radius: 8px;
        margin-top: -20px;
    }
    .demo-image__preview1 {
        width: 250px;
        height: 290px;
        margin: 20px 60px;
        float: left;
    }
    .image_1 {
        width: 275px;
        height: 260px;
        background: #ffffff;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    }
    .error {
        margin: 100px auto;
        width: 50%;
        padding: 10px;
        text-align: center;
    }
    .download_bt {
        padding: 10px 16px !important;
    }
    .img_info_1 {
        height: 30px;
        width: 275px;
        text-align: center;
        background-color: #21b3b9;
        line-height: 30px;
    }

    .demo-image__preview2 {
        width: 250px;
        height: 290px;

        margin: 20px 460px;
        /* background-color: green; */
    }



</style>