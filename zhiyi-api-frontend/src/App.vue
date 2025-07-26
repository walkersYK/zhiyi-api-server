<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style>
  #app {
    font-family: 'Segoe UI', 'Helvetica Neue', Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #1e293b;
    height: 100vh;
    overflow: hidden;
    background-color: #f8fafc;
  }

  /* 移除可能影响布局的默认样式 */
  body, html {
    margin: 0;
    padding: 0;
    height: 100%;
  }

  /* 优化路由过渡效果 */
  .fade-enter-active,
  .fade-leave-active {
    transition: opacity 0.3s ease;
  }

  .fade-enter-from,
  .fade-leave-to {
    opacity: 0;
  }
</style>