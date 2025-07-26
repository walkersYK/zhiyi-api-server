<template>
    <div class="manager-container">
        <!-- 顶部应用栏 -->
        <div class="app-header">
            <div class="header-content">
                <div class="logo-container">
                    <div class="logo"></div>
                    <div class="app-name">智易快搭</div>
                </div>

                <!-- 顶部导航栏 -->
                <nav-main @change="handleNavChange"></nav-main>

                <div class="user-info" @click="toggleUserDropdown">
                    <div class="user-avatar"></div>
                    <div class="username" v-if="!isSidebarCollapsed">管理员</div>
                    <div class="user-dropdown">
                        <i class="el-icon-caret-bottom"></i>
                    </div>
                    <!-- 用户下拉菜单 -->
                    <transition name="el-zoom-in-top">
                        <div class="dropdown-menu" v-show="showUserDropdown">
                            <div class="dropdown-item" @click="logout">
                                <i class="el-icon-switch-button"></i>
                                <span>退出登录</span>
                            </div>
                        </div>
                    </transition>
                </div>
            </div>
        </div>

        <!-- 主内容区 -->
        <div class="main-container">
            <!-- 侧边栏 -->
            <div class="app-sidebar" :class="{ 'collapsed': isSidebarCollapsed }">
                <div class="sidebar-header">
                    <span v-if="!isSidebarCollapsed">应用分组</span>
                    <el-button
                            type="text"
                            class="sidebar-toggle"
                            @click="toggleSidebar">
                        <i :class="isSidebarCollapsed ? 'el-icon-s-unfold' : 'el-icon-s-fold'"></i>
                    </el-button>
                    <el-button
                            v-if="!isSidebarCollapsed"
                            type="text"
                            class="add-group-btn">
                        <i class="el-icon-plus"></i> 新建分组
                    </el-button>
                </div>
                <div class="sidebar-menu">
                    <div
                            v-for="group in groups"
                            :key="group.id"
                            class="menu-item"
                            :class="{ 'active': activeGroup === group.id }"
                            @click="setActiveGroup(group.id)">
                        <i :class="group.icon" class="menu-icon"></i>
                        <span class="menu-text" v-if="!isSidebarCollapsed">{{ group.name }}</span>
                        <el-tooltip
                                v-if="isSidebarCollapsed"
                                :content="group.name"
                                placement="right">
                            <span class="group-count">{{ group.count }}</span>
                        </el-tooltip>
                        <span class="group-count" v-else>{{ group.count }}</span>
                    </div>
                </div>
            </div>

            <!-- 内容区域 -->
            <div class="content-area" :class="{ 'expanded': isSidebarCollapsed }">
                <div class="content-header">
                    <h2>{{ activeGroupName }}应用</h2>
                    <div class="header-actions">
                        <el-input
                                placeholder="搜索应用..."
                                prefix-icon="el-icon-search"
                                v-model="searchKeyword"
                                class="search-input">
                        </el-input>
                        <el-button type="primary" icon="el-icon-plus">新建应用</el-button>
                    </div>
                </div>

                <div class="content-body">
                    <router-view></router-view>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import NavMain from '@/components/common/nav-main';

    export default {
        components: {
            NavMain
        },
        data() {
            return {
                searchKeyword: "",
                activeGroup: 1,
                isSidebarCollapsed: false,
                showUserDropdown: false,
                groups: [
                    { id: 1, name: "全部", icon: "el-icon-menu", count: 24 },
                    { id: 2, name: "未分组", icon: "el-icon-folder", count: 5 },
                    { id: 3, name: "常用", icon: "el-icon-star-on", count: 8 },
                    { id: 4, name: "最近", icon: "el-icon-time", count: 12 }
                ]
            };
        },
        computed: {
            activeGroupName() {
                const group = this.groups.find(g => g.id === this.activeGroup);
                return group ? group.name : '';
            }
        },
        methods: {
            handleNavChange(nav) {
                console.log("导航切换:", nav);
            },
            setActiveGroup(groupId) {
                this.activeGroup = groupId;
            },
            toggleSidebar() {
                this.isSidebarCollapsed = !this.isSidebarCollapsed;
            },
            toggleUserDropdown() {
                this.showUserDropdown = !this.showUserDropdown;
            },
            logout() {
                // 这里添加退出登录的逻辑
                console.log("执行退出登录操作");
                this.showUserDropdown = false;
                // 实际项目中可能是调用退出API然后跳转到登录页
                this.$router.push('/login');
            },
            // handleClickOutside(e) {
            //     if (!this.$el.contains(e.target) {
            //         this.showUserDropdown = false;
            //     }
            // }
        },
        mounted() {
            document.addEventListener('click', this.handleClickOutside);
        },
        beforeUnmount() {
            document.removeEventListener('click', this.handleClickOutside);
        }
    };
</script>

<style scoped>
    /* 基础变量 */
    :root {
        --primary-color: #1a56db;
        --primary-light: #e0f2fe;
        --sidebar-bg: #ffffff;
        --header-bg: #ffffff;
        --content-bg: #f8fafc;
        --text-primary: #1e293b;
        --text-secondary: #64748b;
        --border-color: #e2e8f0;
        --shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05), 0 2px 4px -1px rgba(0, 0, 0, 0.03);
        --sidebar-width: 260px;
        --sidebar-collapsed-width: 64px;
        --header-height: 60px;
    }

    /* 重置与基础样式 */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    html, body {
        height: 100%;
        overflow: hidden;
    }

    .manager-container {
        display: flex;
        flex-direction: column;
        height: 100vh;
        background-color: var(--content-bg);
        font-family: 'Segoe UI', 'PingFang SC', 'Microsoft YaHei', sans-serif;
    }

    /* 顶部导航栏样式 */
    .app-header {
        height: var(--header-height);
        background-color: var(--header-bg);
        box-shadow: var(--shadow);
        z-index: 100;
        position: relative;
        border-bottom: 1px solid var(--border-color);
    }

    .header-content {
        display: flex;
        justify-content: space-between;
        align-items: center;
        height: 100%;
        padding: 0 24px;
        max-width: 1600px;
        margin: 0 auto;
    }

    .logo-container {
        display: flex;
        align-items: center;
        gap: 12px;
        flex: 0 0 200px;
    }

    .logo {
        width: 40px;
        height: 40px;
        background: #0fb8fc;
        border-radius: 10px;
        display: flex;
        align-items: center;
        justify-content: center;
        color: white;
        font-weight: bold;
        font-size: 20px;
    }

    .app-name {
        font-size: 22px;
        font-weight: 700;
        color: var(--text-primary);
        letter-spacing: 0.5px;
    }

    .user-info {
        display: flex;
        align-items: center;
        gap: 8px;
        flex: 0 0 auto;
        justify-content: flex-end;
        position: relative;
        cursor: pointer;
        padding: 8px 12px;
        border-radius: 6px;
    }

    .user-info:hover {
        background-color: rgba(0, 0, 0, 0.04);
    }

    .username {
        font-size: 14px;
        font-weight: 500;
        color: var(--text-primary);
        max-width: 100px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .user-avatar {
        width: 32px;
        height: 32px;
        background: linear-gradient(45deg, #4f46e5, #7c3aed);
        border-radius: 50%;
        cursor: pointer;
        transition: transform 0.2s;
        display: flex;
        align-items: center;
        justify-content: center;
        color: white;
        font-weight: bold;
        font-size: 14px;
    }

    .user-dropdown {
        color: var(--text-secondary);
        transition: color 0.2s;
        font-size: 12px;
    }

    .user-info:hover .user-dropdown {
        color: var(--primary-color);
    }

    /* 下拉菜单样式 */
    .dropdown-menu {
        position: absolute;
        top: 100%;
        right: 0;
        background-color: white;
        border-radius: 6px;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        padding: 6px 0;
        min-width: 120px;
        z-index: 1000;
        border: 1px solid var(--border-color);
    }

    .dropdown-item {
        padding: 8px 16px;
        display: flex;
        align-items: center;
        gap: 8px;
        color: var(--text-primary);
        font-size: 14px;
    }

    .dropdown-item:hover {
        background-color: var(--primary-light);
        color: var(--primary-color);
    }

    .dropdown-item i {
        font-size: 16px;
    }

    /* 主容器布局 */
    .main-container {
        display: flex;
        flex: 1;
        overflow: hidden;
        max-width: 1600px;
        width: 100%;
        margin: 0 auto;
        padding: 24px;
        gap: 24px;
    }

    /* 侧边栏样式 */
    .app-sidebar {
        width: var(--sidebar-width);
        background-color: var(--sidebar-bg);
        color: var(--text-primary);
        display: flex;
        flex-direction: column;
        border-radius: 16px;
        box-shadow: var(--shadow);
        z-index: 90;
        overflow: hidden;
        height: calc(100vh - var(--header-height) - 48px);
        border: 1px solid var(--border-color);
        transition: all 0.3s ease;
        flex-shrink: 0;
    }

    .app-sidebar.collapsed {
        width: var(--sidebar-collapsed-width);
    }

    .sidebar-header {
        padding: 20px;
        font-size: 18px;
        font-weight: 600;
        border-bottom: 1px solid var(--border-color);
        display: flex;
        justify-content: space-between;
        align-items: center;
        height: 64px;
    }

    .sidebar-toggle {
        font-size: 20px;
        color: var(--text-secondary);
        padding: 0;
        margin-right: -8px;
    }

    .sidebar-toggle:hover {
        color: var(--primary-color);
        background: none;
    }

    .add-group-btn {
        color: var(--primary-color);
        font-size: 14px;
        padding: 4px 8px;
    }

    .add-group-btn:hover {
        background-color: var(--primary-light);
        border-radius: 4px;
    }

    .sidebar-menu {
        padding: 16px 0;
        flex: 1;
        overflow-y: auto;
    }

    .menu-item {
        display: flex;
        align-items: center;
        padding: 12px 16px;
        margin: 4px 8px;
        border-radius: 8px;
        cursor: pointer;
        transition: all 0.2s;
        gap: 12px;
        position: relative;
        justify-content: center;
    }

    .app-sidebar:not(.collapsed) .menu-item {
        justify-content: flex-start;
        padding: 12px 24px;
        margin: 4px 12px;
    }

    .menu-item:hover {
        background-color: var(--primary-light);
    }

    .menu-item.active {
        background-color: var(--primary-light);
        color: var(--primary-color);
        font-weight: 500;
    }

    .menu-item.active::before {
        content: "";
        position: absolute;
        left: 0;
        top: 0;
        bottom: 0;
        width: 4px;
        background-color: var(--primary-color);
        border-radius: 0 4px 4px 0;
    }

    .menu-icon {
        font-size: 18px;
        width: 24px;
        text-align: center;
        color: var(--text-secondary);
    }

    .menu-text {
        flex: 1;
    }

    .app-sidebar.collapsed .menu-text {
        display: none;
    }

    .menu-item.active .menu-icon {
        color: var(--primary-color);
    }

    .group-count {
        background-color: var(--border-color);
        border-radius: 10px;
        padding: 2px 10px;
        font-size: 12px;
        min-width: 30px;
        text-align: center;
    }

    .menu-item.active .group-count {
        background-color: var(--primary-color);
        color: white;
    }

    /* 内容区域样式 */
    .content-area {
        flex: 1;
        display: flex;
        flex-direction: column;
        overflow: hidden;
        background-color: var(--content-bg);
        transition: all 0.3s ease;
    }

    .content-area.expanded {
        margin-left: calc(var(--sidebar-collapsed-width) - var(--sidebar-width) - 24px);
    }

    .content-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 24px;
        padding: 0 8px;
    }

    .content-header h2 {
        font-size: 24px;
        font-weight: 700;
        color: var(--text-primary);
    }

    .header-actions {
        display: flex;
        align-items: center;
        gap: 16px;
    }

    .search-input {
        width: 280px;
    }

    /* 内容主体区域 */
    .content-body {
        flex: 1;
        overflow-y: auto;
        padding: 8px;
        background-color: white;
        border-radius: 16px;
        box-shadow: var(--shadow);
        border: 1px solid var(--border-color);
    }

    /* 响应式调整 */
    @media (max-width: 1200px) {
        :root {
            --sidebar-width: 220px;
        }

        .header-content {
            padding: 0 20px;
        }
    }

    @media (max-width: 992px) {
        .content-header {
            flex-direction: column;
            align-items: flex-start;
            gap: 16px;
        }

        .header-actions {
            width: 100%;
            justify-content: space-between;
        }

        .search-input {
            flex: 1;
        }
    }

    @media (max-width: 768px) {
        .app-sidebar {
            position: fixed;
            left: 0;
            top: var(--header-height);
            bottom: 0;
            transform: translateX(-100%);
            transition: transform 0.3s ease;
            z-index: 1000;
            border-radius: 0;
            width: 280px;
        }

        .app-sidebar.active {
            transform: translateX(0);
        }

        .main-container {
            padding: 16px;
        }

        .content-area.expanded {
            margin-left: 0;
        }

        .username {
            display: none;
        }
    }
</style>