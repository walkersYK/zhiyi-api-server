<template>
    <div>
        <h2>嵌入百度页面</h2>
        <iframe :src="baiduUrl" width="100%" height="600px" frameborder="0"></iframe>
    </div>
</template>

<script>
    export default {
        name: "sample",
        data() {
            return {
                baiduUrl: 'http://127.0.0.1:5000/', // 百度的 URL
            };
        },
    }
</script>

<style scoped>

</style>