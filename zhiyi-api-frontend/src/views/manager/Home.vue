<template>
    <div class="home-container">
        <!-- 创建方式选择 -->
        <div class="creation-options">
            <h3 class="section-title">选择创建方式</h3>
            <div class="option-cards">
                <el-card
                        v-for="(option, index) in creationOptions"
                        :key="index"
                        class="option-card"
                        shadow="hover"
                        @click.native="option.action">
                    <div class="card-icon">
                        <i :class="option.icon"></i>
                    </div>
                    <div class="card-title">{{ option.title }}</div>
                </el-card>
            </div>
        </div>

        <!-- 全部应用 -->
        <div class="all-apps">
            <div class="section-header">
                <h3 class="section-title">全部应用</h3>
                <el-input
                        placeholder="搜索应用..."
                        prefix-icon="el-icon-search"
                        v-model="searchKeyword"
                        class="search-input">
                </el-input>
            </div>

            <div class="app-grid">
                <el-card
                        v-for="(item, index) in filteredItems"
                        :key="index"
                        class="app-card"
                        shadow="hover"
                        @click.native="handleViewItem(index)">
                    <div class="app-image" :style="{backgroundImage: 'url(' + item.descriptionImg + ')'}"></div>
                    <div class="app-info">
                        <div class="app-name">{{ item.name }}</div>
                        <div class="app-desc">{{ item.description }}</div>
                    </div>
                </el-card>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                searchKeyword: "",
                creationOptions: [
                    { title: "PC端创建", icon: "el-icon-monitor", action: this.design },
                    { title: "模板创建", icon: "el-icon-collection", action: this.design }
                ],
                items: []
            }
        },
        computed: {
            filteredItems() {
                return this.items.filter(item =>
                    item.name.toLowerCase().includes(this.searchKeyword.toLowerCase()) ||
                    item.description.toLowerCase().includes(this.searchKeyword.toLowerCase())
                );
            }
        },
        methods: {
            design() {
                window.location.href = '/panel';
            },
            async handleViewItem(index) {
                try {
                    await this.$router.push({path: `/interface_info/${index}`});
                } catch (error) {
                    console.error('Error processing item at index:', index, error);
                }
            },
            fetchData() {
                this.$request.get('/interfaceInfo/list')
                    .then(response => {
                        this.items = response.data.data;
                    })
                    .catch(error => {
                        console.error(error);
                    });
            }
        },
        mounted() {
            this.fetchData();
        }
    }
</script>

<style scoped>
    .home-container {
        padding: 20px;
    }

    .section-title {
        font-size: 18px;
        font-weight: 600;
        color: var(--text-primary);
        margin-bottom: 16px;
    }

    .creation-options {
        margin-bottom: 30px;
    }

    .option-cards {
        display: flex;
        gap: 20px;
    }

    .option-card {
        width: 180px;
        height: 120px;
        cursor: pointer;
        transition: transform 0.3s;
    }

    .option-card:hover {
        transform: translateY(-5px);
    }

    .card-icon {
        font-size: 36px;
        color: var(--primary-color);
        text-align: center;
        margin: 20px 0 10px;
    }

    .card-title {
        text-align: center;
        font-size: 16px;
        font-weight: 500;
    }

    .section-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 20px;
    }

    .search-input {
        width: 300px;
    }

    .app-grid {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
        gap: 20px;
    }

    .app-card {
        height: 240px;
        cursor: pointer;
        transition: transform 0.3s;
    }

    .app-card:hover {
        transform: translateY(-5px);
    }

    .app-image {
        height: 160px;
        background-size: cover;
        background-position: center;
    }

    .app-info {
        padding: 12px;
    }

    .app-name {
        font-weight: 600;
        margin-bottom: 8px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .app-desc {
        font-size: 13px;
        color: var(--text-secondary);
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        overflow: hidden;
    }
</style>