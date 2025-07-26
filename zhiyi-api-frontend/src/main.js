import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import VueDragResize from 'vue-drag-resize';
import 'echarts';
import ECharts from 'vue-echarts'
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/css/globle.css'
import request from "@/utils/request";
import axios from 'axios'
// 导入全局生效
axios.defaults.withCredentials = true;
Vue.config.productionTip = false
Vue.use(ElementUI,{size: 'small'});
// eslint-disable-next-line vue/multi-word-component-names
Vue.component('Dragger' ,VueDragResize);
Vue.component('ECharts', ECharts)
Vue.prototype.$request=request//$ 是在 Vue 所有实例中都可用的 property 的一个简单约定。这样做会避免和已被定义的数据、方法、计算属性产生冲突。
// 全局引入request,方便this访问，没括号

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
