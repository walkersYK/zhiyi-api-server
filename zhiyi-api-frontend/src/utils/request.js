import axios from 'axios'
axios.defaults.withCredentials = true; // 允许跨域请求携带凭证（cookies)


// 创建可一个新的axios对象
const request = axios.create({
    baseURL: 'http://localhost:9090',   // 后端的接口地址  ip:port
    timeout: 30000
})

// request 拦截器 统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    let user = JSON.parse(localStorage.getItem("honey-user")||'{}')//获取保存的信息
    console.log(user)
    config.headers['token'] = user.token  // 设置请求头
    return config
}, error => {
    console.error('request error: ' + error) // for debug
    return Promise.reject(error)
});
//
// // 响应拦截器
// // 可以在接口响应后统一处理结果
// request.interceptors.response.use(
//     response => {
//         let res = response.data;
//         // 兼容服务端返回的字符串数据
//         if (typeof res === 'string') {
//             res = res ? JSON.parse(res) : res
//         }
//         if(res.code === '401'){
//             router.push('/login')//如果没有token数据就跳转到登录页
//         }
//         return res;
//     },
//     error => {
//         console.error('response error: ' + error) // for debug
//         return Promise.reject(error)
//     }
// )


export default request