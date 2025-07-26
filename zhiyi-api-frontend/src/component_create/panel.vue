<template>
    <div id="panel">
        <!--组件列表-->
        <Widget_List
                :list="widgetList"
                @onWidgetMouseDown="onWidgetMouseDown"
        />

        <!--操作面板-->
        <div class="panel-o"
             @dragover="e => e.preventDefault()"
             @drop="onDrop"
        >
            <img src="@/assets/net_draw.jpg" alt="Background Image" class="background-img">
            <dragger
                 v-for="item in list"
                 :key="item.id"
                 class="box"
                 :style="{ transform: `translate(${item.x}px, ${item.y}px)` }"
                 :w="item.w"
                 :h="item.h"
                 :isActive="item.focused"
                 @resizestop="record"
                 @dragstop="record"
                 @clicked="onFocus(item)"
            >
                新盒子{{item.label}}
                <Component
                        class="inner-widget"
                        :is="item.component"
                        :value="item.value"
                        :styles="item.styles"
                />
            </dragger>
            <code_v style="position: absolute; right: 0; bottom: 0"/>
        </div>
        <style-sider
                :current="current"
                :form="currentForm"
                @change="onStyleChange"
        />
    </div>
</template>

<script>
    /*其他小工具*/
    import code_v from '@/component_create/component-other/code_v'
      // 组件列表
    import Widget_List from '@/component_create/widget-list';
    /*静态配置*/
    import * as CONFIG from '@/assets/js/config';
    /*组件*/
    import sample from '@/component_create/conview/sample';
    import CustomText from '@/component_create/custom-text';
    import BarChart from '@/component_create/bar';
    import ContentInfo from '@/component_create/conview/Content-view';
    import StyleSider from '@/component_create/style-layout'

    let currentId = 0;
    let widgetX = 0;
    let widgetY = 0;
    let currentWidget = null;
    const recordList = [];

    export default {
        name: "panel",
        components: {
            Widget_List,
            BarChart,
            StyleSider,
            ContentInfo,
            code_v,
            CustomText,
            sample
        },
        data(){
          return{
              list:[],//json数据
              widgetList: CONFIG.WIDGET_LIST,//列表
          }
        },
        //右侧style数据获取
        computed: {
            current () {
                return this.list.find(item => item.focused);
            },
            currentForm () {
                if (!this.current) {
                    return [];
                }
                return CONFIG.WIDGET_LIST.find(item => this.current.type === item.type).styleForm;
            },
        },
        methods:{
            // 让当前项获取焦点 其他项失去焦点
            onFocus (currentItem) {
                console.log(1);
                this.list = this.list.map(item => {
                    item.focused = item.id === currentItem.id;
                    return item;
                });
            },
            //记录操作
            record(){
                recordList.push(this.list.concat());
            },
            //撤回
            withdraw(){
                const idx = recordList.length -2;
                if(idx <0){
                    return;
                }
                this.list = recordList[idx];
                recordList.pop();
            },
            //放置
            onDrop (e){
                let x = e.offsetX;
                let y = e.offsetY;
                // 新增面板项
                const newItem = {
                    id: currentId++,
                    //
                    x,
                    y,
                    ...currentWidget.default, // 解构,生成默认的宽高数据 w, h, value
                    label: currentWidget.label,
                    component: currentWidget.component, // 新增的组件名
                    value: currentWidget.default.value,//触发数据
                    type: currentWidget.type, // 新增组件的类型
                    styles: currentWidget.styles, // 新增组件的类型
                };
                this.list.push(newItem);
                this.onFocus(newItem);
                this.record();
            },
            // 当图层样式改变时
            onStyleChange (id, newStyles) {
                this.list = this.list.map((item) => {
                    if (item.id === id) {
                        item.style = newStyles;
                    }
                    return item;
                });
            },
            //鼠标落下事件
            onWidgetMouseDown(e, widget) {
                widgetX = e.offsetX;
                widgetY = e.offsetY;
                currentWidget = widget;
            },
        },
        mounted() {
            //键盘事件
            document.addEventListener('keyup', e =>{
                if(e.ctrlKey && e.key === 'z'){
                    this.withdraw();
                }
            })
        }
    }
</script>

<style>
    body{
        margin: 0;
        padding: 0;
        font-family: 'Segoe UI', 'Arial', sans-serif;
        background: #f7f8fa;
    }
    #panel{
        display: flex;
        width: 100vw;
        height: 100vh;
        background: #f7f8fa;
        box-shadow: none;
        border-radius: 0;
        overflow: hidden;
        transition: all 0.3s;
    }
    .panel-o{
        margin: 16px 16px;
        flex:1;
        background: #fff;
        position: relative;
        border: 1px solid #e0e0e0;
        border-radius: 12px;
        box-shadow: 0 2px 12px 0 #e0e0e033;
        overflow: hidden;
        min-width: 0;
        transition: box-shadow 0.3s;
    }
    .panel-o:hover {
        box-shadow: 0 4px 24px 0 #b0b0b033;
    }
    .background-img {
        opacity: 0.08;
        width: 100%;
        height: 100%;
        z-index: -1;
        position: absolute;
        top: 0;
        left: 0;
        filter: blur(2px) brightness(0.9);
        pointer-events: none;
    }
    .box{
        outline: 1px solid #b0b0b0;
        border-radius: 8px;
        position: absolute;
        box-shadow: 0 2px 8px 0 #e0e0e033;
        background: #f9f9fb;
        transition: box-shadow 0.3s, outline 0.3s, transform 0.2s;
    }
    .box:hover, .box:focus, .box[isActive] {
        outline: 2px solid #2681ff;
        box-shadow: 0 4px 16px 0 #2681ff22;
        z-index: 2;
        transform: scale(1.03);
    }
    .inner-widget{
        width: 100%;
        height: 100%;
        border-radius: 6px;
        background: #fff;
        box-shadow: none;
        transition: background 0.3s;
    }
    .inner-widget:hover {
        background: #f2f6fa;
    }
    @media (max-width: 900px) {
        #panel {
            flex-direction: column;
        }
        .panel-o {
            margin: 8px 0;
            border-radius: 0;
        }
    }
    @media (max-width: 600px) {
        #panel {
            flex-direction: column;
        }
        .panel-o {
            margin: 0;
            border-radius: 0;
            min-width: 0;
        }
    }
</style>