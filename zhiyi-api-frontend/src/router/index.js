import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'manager',
    component: () => import('../views/Manager.vue'),
    redirect: '/home',
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import('../views/manager/Home.vue'),
        meta: { title: '首页', icon: 'el-icon-house' }
      },
      {
        path: 'api',
        name: 'Api',
        component: () => import('../views/manager/Api.vue'),
        meta: { title: '接口管理', icon: 'el-icon-connection' }
      },
      {
        path: 'draw',
        name: 'Draw',
        component: () => import('../views/manager/draw.vue'),
        meta: { title: '图表生成', icon: 'el-icon-data-analysis' }
      },
      {
        path: 'interface_info/:id',
        name: '接口文档',
        component: () => import('../views/InterfaceInfo.vue'),
        meta: { title: '接口详情', hidden: true }
      }
    ]
  },
  { path: '/panel', name: 'Panel', component: () => import('../component_create/panel.vue') },
  { path: '/test', name: 'test', component: () => import('../component_create/component-other/cramer.vue') },
  { path: '/index', name: 'test', component: () => import('../views/product/index.vue') },
  { path: '/sample', name: 'test', component: () => import('../component_create/conview/sample.vue') },


  /*登录，注册*/
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 路由守卫
// router.beforeEach((to, from, next) => {
//   // to 是到达的路由信息
//   // from 是开源的路由信息
//   // next 是帮助我们跳转路由的函数
//   let adminPaths = ['/user']
//   let user = JSON.parse(localStorage.getItem('honey-user') || '{}')
//
//   if (user.role !== '管理员' && adminPaths.includes(to.path)) {
//     // 如果当前登录的用户不是管理员，然后当前的到达的路径是管理员才有权限访问的路径，那这个时候我就让用户去到一个没有权限的页面，不让他访问实际的页面
//     next('/403')
//   } else {
//     next()
//   }
// })
export default router
