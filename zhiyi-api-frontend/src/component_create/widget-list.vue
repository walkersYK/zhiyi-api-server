<template>
    <!--组件列表-->
    <div class="widget-list">
        <span style="color: #6366f1; position: relative;">组件中心</span>
        <div  v-for="widget in list"
              :key="widget.type"
              class="widget"
              draggable="true"
              @mousedown="(e) => $emit('onWidgetMouseDown',e, widget)"
        >
            {{widget.label}}
        </div>
        <div class="widget-actions">
            <button class="action-btn" @click="$emit('clear')">清空</button>
            <button class="action-btn" @click="$emit('back')">返回</button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "widget-list",
        //变量传参
        //逻辑通过触发自定义事件实现
        props:{
            list: Array,
        },
    }
</script>

<style  scoped>
    .widget-list{
        width: 220px;
        background: linear-gradient(135deg, #f8fafc 60%, #e0e7ff 100%);
        padding-top: 60px;
        border-radius: 18px;
        box-shadow: 0 0 24px 4px #a5b4fc80, 0 0 0 2px #e0e7ff inset;
        border: 2px solid #a5b4fc;
        margin: 12px 0 12px 12px;
        position: relative;
        overflow: hidden;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .widget-list > span {
        color: #6366f1;
        font-size: 1.2em;
        letter-spacing: 2px;
        font-family: 'Orbitron', 'Segoe UI', 'Arial', sans-serif;
        text-shadow: 0 0 8px #a5b4fc88;
        margin-bottom: 18px;
        display: block;
        text-align: center;
    }
    .widget{
        width: 84%;
        height: 80px;
        outline: 2px solid #a5b4fc80;
        outline-offset: 2px;
        font-size: 32px;
        text-align: center;
        line-height: 80px;
        display: block;
        margin-left: 8%;
        margin-top: 24px;
        color: #3730a3;
        border-radius: 14px;
        background: linear-gradient(120deg, #e0e7ff 0%, #f8fafc 100%);
        box-shadow: 0 0 16px 2px #a5b4fc33 inset;
        cursor: pointer;
        transition: box-shadow 0.3s, background 0.3s, color 0.3s, transform 0.2s;
        position: relative;
        z-index: 1;
    }
    .widget:hover {
        color: #6366f1;
        background: linear-gradient(120deg, #e0e7ff 0%, #a5b4fc33 100%);
        box-shadow: 0 0 32px 8px #a5b4fcbb, 0 0 0 4px #e0e7ff inset;
        transform: scale(1.05) rotate(-1deg);
        z-index: 2;
    }
    .widget-actions {
        display: flex;
        justify-content: center;
        gap: 16px;
        margin: 32px 0 18px 0;
    }
    .action-btn {
        background: linear-gradient(90deg, #a5b4fc 0%, #f8fafc 100%);
        color: #3730a3;
        border: none;
        border-radius: 10px;
        padding: 8px 22px;
        font-size: 1em;
        font-family: 'Orbitron', 'Segoe UI', 'Arial', sans-serif;
        font-weight: bold;
        box-shadow: 0 0 8px 2px #a5b4fc55;
        cursor: pointer;
        transition: background 0.2s, color 0.2s, box-shadow 0.2s;
    }
    .action-btn:hover {
        background: #6366f1;
        color: #fff;
        box-shadow: 0 0 16px 4px #a5b4fcbb;
    }
</style>