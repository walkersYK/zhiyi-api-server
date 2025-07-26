<template>
    <div v-if="current" class="sider">
        <div>{{ this.current.label }}</div>
        <el-form
                label-width="120px"
                :model="styles"
        >
            <el-form-item
                    v-for="formItem in form"
                    :key="formItem.key"
                    :label="formItem.label"
            >
                <component
                        v-model="styles[formItem.key]"
                        :is="formItem.component"
                />
            </el-form-item>
            <el-button @click="$emit('change', current.id, styles)">保存</el-button>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "style-layout",
        props: {
            current: {
                type: Object,
                default () {
                    return null;
                },
            },
            form: {
                type: Array,
                default () {
                    return [];
                },
            },
        },
        watch: {
            current (newVal) {
                if (newVal) {
                    this.styles = newVal.styles;
                }
            },
        },
        updated() {
            console.log(this.current)
        },
        data() {
            return {
                styles: {},
            };
        },
    }
</script>

<style scoped>
 .sider{
     width: 240px;
     background: linear-gradient(135deg, #232526 60%, #1b3964 100%);
     border-radius: 18px;
     box-shadow: 0 0 24px 4px #00fff7a0, 0 0 0 2px #1a1a2e inset;
     border: 2px solid #00fff7;
     margin: 12px 12px 12px 0;
     padding: 32px 18px 24px 18px;
     color: #eaeaea;
     font-family: 'Orbitron', 'Segoe UI', 'Arial', sans-serif;
     min-height: 320px;
     position: relative;
 }
 .sider > div:first-child {
     color: #00fff7;
     font-size: 1.2em;
     letter-spacing: 2px;
     text-shadow: 0 0 8px #00fff7cc;
     margin-bottom: 18px;
     text-align: center;
 }
 .el-form {
     background: transparent;
 }
 .el-form-item {
     margin-bottom: 18px;
 }
 .el-button {
     background: linear-gradient(90deg, #00fff7 0%, #232526 100%);
     color: #232526;
     border-radius: 12px;
     border: none;
     font-family: 'Orbitron', 'Segoe UI', 'Arial', sans-serif;
     font-weight: bold;
     box-shadow: 0 0 12px 2px #00fff7a0;
     transition: background 0.3s, color 0.3s, box-shadow 0.3s, transform 0.2s;
 }
 .el-button:hover {
     background: linear-gradient(90deg, #232526 0%, #00fff7 100%);
     color: #00fff7;
     box-shadow: 0 0 24px 6px #00fff7cc;
     transform: scale(1.05);
 }
</style>