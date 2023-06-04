<script setup>
import { Check, Document, Finished, House, Money, Star, User } from '@element-plus/icons-vue'
import { useUserStore } from '../stores/user'
import { useRoute } from 'vue-router'
import { roles } from '../assets/fields'

const userStore = useUserStore()
const route = useRoute()
</script>

<template>
  <aside class="aside-nav no-select">
    <el-scrollbar>
      <el-menu class="menu" active-text-color="dodgerblue" router :default-active="route.path">
        <el-menu-item v-if="!userStore.isLogin()" index="/login">
          <el-icon>
            <user />
          </el-icon>
          登录
        </el-menu-item>
        <el-menu-item v-if="userStore.hasAnyRoles([...roles])" index="/">
          <el-icon>
            <house />
          </el-icon>
          主页
        </el-menu-item>
        <el-menu-item v-if="userStore.hasAnyRoles([...roles])" index="/self-info">
          <el-icon>
            <star />
          </el-icon>
          个人信息
        </el-menu-item>
        <el-menu-item v-if="userStore.hasAnyRoles([roles[0]])" index="/self-attendance">
          <el-icon>
            <Check />
          </el-icon>
          考勤信息
        </el-menu-item>
        <el-menu-item v-if="userStore.hasAnyRoles([roles[0]])" index="/self-salary">
          <el-icon>
            <Money />
          </el-icon>
          工资信息
        </el-menu-item>
        <el-sub-menu v-if="userStore.hasAnyRoles([roles[0]])" index="/self">
          <template #title>
            <el-icon>
              <document />
            </el-icon>
            科研成果、项目
          </template>
          <el-menu-item index="/self-thesis">论文管理</el-menu-item>
          <el-menu-item index="/self-book">书籍著作管理</el-menu-item>
          <el-menu-item index="/self-patent">专利管理</el-menu-item>
          <el-menu-item index="/self-software-work">软件著作管理</el-menu-item>
          <el-menu-item index="/self-contract-project">合同项目管理</el-menu-item>
        </el-sub-menu>
        <el-sub-menu v-if="userStore.hasAnyRoles([roles[1], roles[2]])" index="/admin">
          <template #title>
            <el-icon>
              <finished />
            </el-icon>
            审核系统
          </template>
          <el-menu-item index="/admin-thesis">论文审核</el-menu-item>
          <el-menu-item index="/admin-book">书籍著作审核</el-menu-item>
          <el-menu-item index="/admin-patent">专利审核</el-menu-item>
          <el-menu-item index="/admin-software-work">软件著作审核</el-menu-item>
          <el-menu-item index="/admin-contract-project">合同项目审核</el-menu-item>
        </el-sub-menu>
        <el-sub-menu v-if="userStore.hasAnyRoles([roles[1], roles[2]])" index="/manage">
          <template #title>
            <el-icon>
              <user />
            </el-icon>
            管理系统
          </template>
          <el-menu-item index="/broadcast-manage">公告管理</el-menu-item>
          <el-menu-item index="/attendance-manage">考勤管理</el-menu-item>
          <el-menu-item index="/salary-manage">工资管理</el-menu-item>
          <el-menu-item v-if="userStore.hasAnyRoles([roles[2]])" index="/user-manage">用户管理</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-scrollbar>
  </aside>
</template>

<style scoped>
.aside-nav {
  width: 200px;
  position: fixed;
  top: 60px;
  left: 0;
  bottom: 0;
  box-shadow: var(--el-box-shadow-light);
  background-color: white;
  z-index: 1000;
}

.menu {
  border: none;
}
</style>
