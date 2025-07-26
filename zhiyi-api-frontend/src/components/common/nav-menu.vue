<template>
    <!-- 侧边栏 -->
    <div class="project-manage">
        <div style="color: #ffffff; font-size: 14px">
            <div class="my-project project-all " href="#home" style="padding-left: 10px; height: 56px; border-bottom: 1px solid #27343e">
                <span style="margin-left: 1px;">我的分组</span>
                <i role="img" class="btn-add-icon n-icon" style="--n-bezier: cubic-bezier(0.4, 0, 0.2, 1);">
                </i>
            </div>
        </div>
        <!---->
        <div class="project">
            <div class="manage-main dragging active" href="#tt" >
                <div class="main-project">
                    <span class="project-name project-ungrouped">全部应用</span>
                </div>
            </div>
            <div class="manage-main dragging" href="#hoe" >
                <div class="main-project">
                    <span class="project-name project-ungrouped">未分组</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "nav-menu"
    }
</script>

<style scoped>
    .project-manage {
        min-width: 200px;
        max-width: 220px;
        color: #222;
        background: #fff;
        border-right: 1px solid #e0e0e0;
        font-size: 15px;
        overflow-y: auto;
        height: 100vh;
        box-shadow: 2px 0 8px 0 #e0e0e022;
        transition: all 0.3s;
        padding-top: 12px;
    }
    .my-project .project-manage .manage-title .btn-add-icon {
        font-size: 14px;
        cursor: pointer;
    }
    .n-icon {
        height: 1em;
        width: 1em;
        line-height: 1em;
        text-align: center;
        display: inline-block;
        position: relative;
        fill: currentColor;
        -webkit-transform: translateZ(0);
        transform: translateZ(0);
    }
    .my-project .project-manage .active {
        background: #f2f6fa;
    }
    .project div:hover:not(.active) {
        background: #f7f8fa;
    }
    .my-project .project-manage .project-all {
        padding-left: 24px;
        transition: color .2s;
        cursor: pointer;
        border-bottom: 1px solid #f0f0f0;
        height: 48px;
        display: flex;
        align-items: center;
    }
    .my-project .project-manage .my-project {
        line-height: 36px;
        height: 36px;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding-right: 20px;
    }
    .my-project .project-manage .project-num {
        user-select: none;
        color: #888;
    }
    .my-project .project-manage .manage-main {
        display: flex;
        flex-direction: column;
        font-size: 13px;
    }
    .my-project .project-manage .main-project{
        position: relative;
        height: 36px;
        padding: 0 20px 0 24px;
        display: flex;
        font-size: 13px;
        align-items: center;
        justify-content: flex-start;
        cursor: pointer;
        transition: color .2s;
    }
    .my-project .project-manage .main-project .project-name {
        width: 100px;
        font-size: 15px;
        text-overflow: ellipsis;
        overflow: hidden;
        white-space: nowrap;
        pointer-events: none;
    }
    .my-project .project-manage .project-ungrouped {
        color: #2681ff;
    }
    @media (max-width: 900px) {
        .project-manage {
            min-width: 140px;
            max-width: 160px;
            font-size: 14px;
        }
    }
    @media (max-width: 600px) {
        .project-manage {
            display: none !important;
        }
    }
</style>