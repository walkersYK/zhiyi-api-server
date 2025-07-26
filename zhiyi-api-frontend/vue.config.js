const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
    lintOnSave: false,/*关闭语法检查*/
  devServer: {
    port: 8081
  },
  chainWebpack: config =>{
    config.plugin('html')
        .tap(args => {
          args[0].title = "智易快搭";
          return args;
        })
  },
})