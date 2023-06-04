<script setup>
import { RouterView } from 'vue-router'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import AsideNav from '@/components/AsideNav.vue'
import MainHeader from '@/components/MainHeader.vue'
</script>

<template>
  <div class="bg">
    <el-config-provider :locale="zhCn">
      <MainHeader></MainHeader>
      <AsideNav></AsideNav>
      <ElScrollbar>
        <main>
          <router-view v-slot="{ Component }">
            <transition name="el-fade-in" mode="out-in">
              <component :is="Component" />
            </transition>
          </router-view>
        </main>
      </ElScrollbar>
    </el-config-provider>
  </div>
</template>

<style scoped>
.bg {
  height: 100vh;
  width: 100vw;
  overflow: hidden;
  background-color: rgb(242, 242, 242);
}

main {
  padding: 75px 15px 15px 215px;
  min-width: 760px;
}
</style>
