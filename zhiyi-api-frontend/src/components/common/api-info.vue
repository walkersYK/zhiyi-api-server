<template>
    <div>
        <span>接口信息</span>
        <div >
            <el-input style="width: 200px" placeholder="用户名查询" v-slot="scope"></el-input><el-button type="primary">查询</el-button>
        </div>
        <el-table :data="users">
            <el-table-column label="ID" prop='id'></el-table-column><!--prop后端数据库中的属性 -->
            <el-table-column label="用户名" prop='username'></el-table-column>
            <el-table-column label="姓名" prop='name'></el-table-column>
            <el-table-column label="地址" prop='address'></el-table-column>
            <el-table-column label="录入">
                <template v-slot="scope">
                    <el-button type="primary" plain>编辑</el-button>
                    <el-button type="danger" plain>删除</el-button>
                </template>
            </el-table-column>
            <el-table-column lable="文件上传">
                <!--//绑定行对象-->
                <template v-slot="scope">
                    <el-upload
                            action = "http://localhost:9090/file/upload"
                            :header="{token: user.token}"
                            :show-file-list="false"
                            :on-success="(row,res,file,fileList)=>handleTableFileUpload(scope.row,res,file,fileList)">
                        <el-button size="mini" type="primary">点击上传</el-button>
                    </el-upload>
                </template>
            </el-table-column>
            <el-table-column label="文件上传">
                <template v-slot="scope">
                    <el-image v-if="scope.row.avatar":src="scope.row.avatar" style="width:50px; height: 50px"></el-image>
                    <div><el-button @click="preview(scope.row.avatar)">预览</el-button></div>
                </template>
            </el-table-column>

        </el-table>
    </div>

</template>

<script>
    import request from "@/utils/request";

    export default {
        name: "api-info",
        data() {
            return {
                isCollapse: false,  // 不收缩
                asideWidth: '200px',
                collapseIcon: 'el-icon-s-fold',
                user: JSON.parse(localStorage.getItem('honey-user') || '{}'),//获取登录用户信息方便上传校验
                users:[]
            }
        },
        created() {
            this.mounted()
        },
        methods: {
            mounted() {   // 页面加载完成之后触发
                request.get('/user/selectAll').then(res => {
                    this.users=res.data
                })
            },
            preview(){

            },
            handleMultipleFileUpload(response, file, fileList) {
                this.urls = fileList.map(v => v.response?.data)
            },
            handleFileUpload(response, file, fileList) {
                console.log(response, file, fileList)
            },
            handleTableFileUpload(row, file, fileList) {
                console.log(row, file, fileList)
                row.avatar = file.response.data
                // this.$set(row, 'avatar', file.response.data)
                console.log(row)
                // 触发更新就可以了
                request.put('/user/update', row).then(res => {
                    if (res.code === '200') {
                        this.$message.success('上传成功')
                    } else {
                        this.$message.error(res.msg)
                    }
                })
            },
        }
    }
</script>

<style scoped>

</style>