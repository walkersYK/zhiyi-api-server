<template>
    <e-charts
            :option = "option"
            autoresize
    />
</template>

<script>
    export default {
        props:{
            value: Array,
        },
        computed: {//计算属性
            option(){
                return{
                    xAxis: {
                        type :"category",
                        data: this.value.map(item => item.name),
                    },
                    yAxis: {
                        type: 'value',
                    },
                    series: [
                        {
                            data: this.value.map(item => item.value),
                            type: 'bar'

                        },
                    ]
                }
            }
        }
    }
</script>

<style scoped>

</style>