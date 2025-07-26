<template>
    <div class="nav-main">
        <canvas id="nav-canvas" class="nav-canvas"></canvas>
        <div class="nav-items">
            <div
                    v-for="nav in navs"
                    :key="nav.id"
                    class="nav-item"
                    :class="{ 'nav-active': activeNav === nav.id }"
                    @click="toggleNav(nav)">
                {{ nav.name }}
            </div>
        </div>
    </div>
</template>

<script>
    import { NavCanvas } from './nav-canvas.js'

    export default {
        name: 'nav-main',
        data() {
            return {
                navs: [
                    { id: 0, key: 'home', name: '我的可视化' },
                    { id: 1, key: 'api', name: '数据源管理' },
                    { id: 2, key: 'draw', name: '数据' },
                ],
                activeNav: 0,
                nc: null,
            }
        },
        methods: {
            toggleNav(nav) {
                if (this.$route.path !== nav) {
                    this.activeNav = nav.id
                    this.nc.toggle(nav.id)
                    this.$emit('change', nav)
                    this.$router.push({path: '/' + nav.key})
                }
            },
            handleResize() {
                if (this.nc) {
                    this.nc.resize()
                }
            }
        },
        mounted() {
            const nav = this.navs.find(m => m.key === this.$route.name)
            this.activeNav = nav ? nav.id : 0

            this.nc = new NavCanvas(
                'nav-canvas',
                '.nav-main .nav-item',
                this.activeNav
            )
            this.nc.toggle(this.activeNav)

            window.addEventListener('resize', this.handleResize)
        },
        beforeDestroy() {
            window.removeEventListener('resize', this.handleResize)
            if (this.nc) {
                this.nc.destroy()
            }
        }
    }
</script>

<style lang="scss" scoped>
    .nav-main {
        position: relative;
        height: 100%;
        width: 100%;

        .nav-canvas {
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100%;
            height: 3px;
            z-index: 1;
        }

        .nav-items {
            display: flex;
            align-items: center;
            height: 100%;
            position: relative;
            z-index: 2;
        }

        .nav-item {
            position: relative;
            padding: 0 24px;
            height: 100%;
            display: flex;
            align-items: center;
            font-size: 16px;
            font-weight: 500;
            color: #64748b;
            cursor: pointer;
            transition: color 0.3s ease;

            &:hover {
                color: #1e293b;
            }

            &.nav-active {
                color: #1a56db;
                font-weight: 600;
            }
        }
    }
</style>