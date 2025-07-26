<template>
    <link rel="stylesheet" href="http://at.alicdu.com/t/c/font_4096975_i8f4jjygw.css">
    <div class="toolbar">
        <div class="button">
            <ul class="icons">
                <li><i class="iconfont icon-message"></i></li>
                <li><i class="iaconfont icon-cart"> </i></li>
                <li><i class = iconfont icon-money></i></li>
                <li><i class="iconfont icon-star"></i></li>
                <li><i class=" iconfont icon-setting"></i></li>
            </ul>
        </div>
    </div>
</template>

<script>
    export default {
        name: "toolerbar"
    }
</script>

<style scoped>
    *{
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
    /*居中*/
    app{
        display: flex;
        justify-content: center;
        align-items: center;
        flex-wrap: wrap;
        min-height: 100vh;
    }

    .button{
        width: 70px;
        height: 70px;
        border-radius: 50%;
        background: #3ab09e;
        position: relative;
    }
    /*伪元素加号*/
    .button::after{
        content: "+";
        position: absolute;
        inset: -6.5px;
        font-size: 3.5em;
    }
    .button{
        color: #fff;
        text-align: center;
    }
    .icons{
        display: flex;
        justify-content: space-evenly;
        align-items: center;
        width: 0%;
        height: 36px;
        list-style: none;
        padding: 16px 10px 10px 50px;
        background: #fff;
        box-shadow: 1px 1px 1px 1px #dcdcdc;
        border-radius: 2em;
    }
    .icons li i {
        color: #3ab09e;
        font-size: 1.5em;!important;
    }
/*    字体图标在容器内左边*/
    .icons li:nth-child(1){
        margin-left: 50px    ;
    }
</style>