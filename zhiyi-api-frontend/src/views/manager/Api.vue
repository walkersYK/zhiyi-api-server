<template>
    <div class="api-container">
        <!-- 搜索和操作区域 -->
        <div class="api-header">
            <div class="search-area">
                <el-input
                        placeholder="查询接口名称"
                        v-model="searchKeyword"
                        clearable
                        style="width: 300px"
                        @keyup.enter.native="loadData">
                    <el-button slot="append" icon="el-icon-search" @click="loadData"></el-button>
                </el-input>
            </div>

            <div class="action-area">
                <el-button type="primary" icon="el-icon-plus" @click="handleAdd">新增接口</el-button>
                <el-button type="danger" icon="el-icon-delete" @click="delBatch" :disabled="!selectedIds.length">批量删除</el-button>
            </div>
        </div>

        <!-- 表格区域 -->
        <el-table
                :data="tableData"
                stripe
                style="width: 100%"
                @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center"></el-table-column>
            <el-table-column prop="id" label="ID" width="80" align="center"></el-table-column>
            <el-table-column prop="name" label="接口名称" min-width="150"></el-table-column>
            <el-table-column prop="description" label="描述" min-width="200" show-overflow-tooltip></el-table-column>
            <el-table-column prop="url" label="URL" min-width="200" show-overflow-tooltip></el-table-column>
            <el-table-column prop="method" label="方法" width="100" align="center"></el-table-column>
            <el-table-column label="状态" width="100" align="center">
                <template slot-scope="scope">
                    <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
                        {{ scope.row.status === 1 ? '开启' : '关闭' }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="220" align="center" fixed="right">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="text"
                            icon="el-icon-edit"
                            @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button
                            size="mini"
                            type="text"
                            icon="el-icon-delete"
                            @click="del(scope.row.id)">删除</el-button>
                    <el-button
                            size="mini"
                            type="text"
                            :icon="scope.row.status === 1 ? 'el-icon-close' : 'el-icon-check'"
                            :class="scope.row.status === 1 ? 'danger-text' : 'success-text'"
                            @click="toggleStatus(scope.row)">
                        {{ scope.row.status === 1 ? '下线' : '发布' }}
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页 -->
        <div class="pagination-area">
            <el-pagination
                    @current-change="handleCurrentChange"
                    :current-page="pageNum"
                    :page-size="pageSize"
                    layout="total, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>

        <!-- 编辑/新增对话框 -->
        <el-dialog
                :title="form.id ? '编辑接口' : '新增接口'"
                :visible.sync="dialogVisible"
                width="50%">
            <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
                <el-form-item label="接口名称" prop="name">
                    <el-input v-model="form.name" placeholder="请输入接口名称"></el-input>
                </el-form-item>
                <el-form-item label="请求方法" prop="method">
                    <el-select v-model="form.method" placeholder="请选择请求方法">
                        <el-option label="GET" value="GET"></el-option>
                        <el-option label="POST" value="POST"></el-option>
                        <el-option label="PUT" value="PUT"></el-option>
                        <el-option label="DELETE" value="DELETE"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="接口URL" prop="url">
                    <el-input v-model="form.url" placeholder="请输入接口URL"></el-input>
                </el-form-item>
                <el-form-item label="请求头" prop="requestHeader">
                    <el-input type="textarea" v-model="form.requestHeader" placeholder="请输入请求头"></el-input>
                </el-form-item>
                <el-form-item label="响应头" prop="responseHeader">
                    <el-input type="textarea" v-model="form.responseHeader" placeholder="请输入响应头"></el-input>
                </el-form-item>
                <el-form-item label="接口描述" prop="description">
                    <el-input type="textarea" v-model="form.description" placeholder="请输入接口描述"></el-input>
                </el-form-item>
                <el-form-item label="描述图片">
                    <el-upload
                            class="avatar-uploader"
                            action="http://localhost:9090/file/upload"
                            :show-file-list="false"
                            :on-success="handleAvatarSuccess">
                        <img v-if="form.descriptionImg" :src="form.descriptionImg" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import request from "@/utils/request";

    export default {
        name: "Api",
        data() {
            return {
                tableData: [],
                pageNum: 1,
                pageSize: 10,
                total: 0,
                searchKeyword: '',
                selectedIds: [],
                dialogVisible: false,
                form: {},
                rules: {
                    name: [{ required: true, message: '请输入接口名称', trigger: 'blur' }],
                    method: [{ required: true, message: '请选择请求方法', trigger: 'change' }],
                    url: [{ required: true, message: '请输入接口URL', trigger: 'blur' }]
                }
            }
        },
        created() {
            this.loadData();
        },
        methods: {
            loadData() {
                request.get('/interfaceInfo/list', {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        name: this.searchKeyword
                    }
                }).then(res => {
                    this.tableData = res.data.data;
                    this.total = res.data.total || 0;
                });
            },
            handleSelectionChange(rows) {
                this.selectedIds = rows.map(item => item.id);
            },
            handleAdd() {
                this.form = {};
                this.dialogVisible = true;
                this.$nextTick(() => {
                    this.$refs.formRef?.clearValidate();
                });
            },
            handleEdit(row) {
                this.form = JSON.parse(JSON.stringify(row));
                this.dialogVisible = true;
            },
            toggleStatus(row) {
                const action = row.status === 1 ? 'offline' : 'online';
                const message = row.status === 1 ? '下线' : '发布';

                this.$confirm(`确定要${message}该接口吗?`, '提示', {
                    type: 'warning'
                }).then(() => {
                    request.post(`/interfaceInfo/${action}`, { id: row.id }).then(res => {
                        this.$message.success(`${message}成功`);
                        this.loadData();
                    });
                }).catch(() => {});
            },
            del(id) {
                this.$confirm('确定要删除该接口吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    request.post('/interfaceInfo/delete?id=' + id).then(res => {
                        this.$message.success('删除成功');
                        this.loadData();
                    });
                }).catch(() => {});
            },
            delBatch() {
                if (!this.selectedIds.length) {
                    this.$message.warning('请选择要删除的接口');
                    return;
                }

                this.$confirm('确定要删除选中的接口吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    request.delete('/interfaceInfo/delete', { data: this.selectedIds }).then(res => {
                        this.$message.success('删除成功');
                        this.loadData();
                    });
                }).catch(() => {});
            },
            save() {
                this.$refs.formRef.validate(valid => {
                    if (valid) {
                        const url = this.form.id ? '/interfaceInfo/update' : '/interfaceInfo/add';
                        request.post(url, this.form).then(res => {
                            this.$message.success('保存成功');
                            this.dialogVisible = false;
                            this.loadData();
                        });
                    }
                });
            },
            handleCurrentChange(val) {
                this.pageNum = val;
                this.loadData();
            },
            handleAvatarSuccess(res) {
                this.$set(this.form, 'descriptionImg', res);
            }
        }
    }
</script>

<style scoped>
    .api-container {
        padding: 20px;
    }

    .api-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 20px;
    }

    .search-area {
        display: flex;
        align-items: center;
    }

    .action-area {
        display: flex;
        gap: 10px;
    }

    .pagination-area {
        margin-top: 20px;
        text-align: right;
    }

    .danger-text {
        color: #F56C6C;
    }

    .success-text {
        color: #67C23A;
    }

    .avatar-uploader >>> .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader >>> .el-upload:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }

    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>