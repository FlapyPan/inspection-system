<script setup>
import { useUserStore } from '../stores/user'

const userStore = useUserStore()
</script>

<template>
  <header class="no-select">
    <el-button color="transparent" size="large">高校职工考核系统</el-button>
    <div class="spacer"></div>
    <div v-if="userStore.isLogin()" class="username">
      <span>{{ userStore.user.name }}</span> ，你好！
    </div>
    <el-button @click="userStore.logout()" v-if="userStore.isLogin()" type="danger" size="small">退出登录</el-button>
  </header>
</template>

<style scoped>
header {
  height: 60px;
  width: 100%;
  position: fixed;
  top: 0;
  z-index: 1000;
  background-color: rgba(56, 58, 66, 0.95);
  box-shadow: var(--el-box-shadow-lighter);
  display: flex;
  align-items: center;
  padding: 0 20px;
  vertical-align: center;
}

.username {
  font-size: 13px;
  color: white;
  padding: 0 1em;
}

.username span {
  text-decoration: underline;
  text-underline-offset: 2px;
}

.spacer {
  flex-grow: 1;
  flex-shrink: 1;
}
</style>
