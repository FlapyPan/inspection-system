<script setup>

import {
  Calendar, Female, Iphone, Male, Message, OfficeBuilding, Postcard, Tickets, User,
} from '@element-plus/icons-vue'

const props = defineProps({
  data: {type: Object},
})
</script>

<template>
  <el-descriptions
    class="margin-top"
    title="用户信息"
    :column="2"
    border
  >
    <template #extra>
      <slot></slot>
    </template>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <user />
          </el-icon>
          姓名
        </div>
      </template>
      {{ data.name }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <male v-if="data.gender==='男'" />
            <female v-else />
          </el-icon>
          性别
        </div>
      </template>
      {{ data.gender }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <postcard />
          </el-icon>
          身份证号码
        </div>
      </template>
      {{ data.idCard }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <message />
          </el-icon>
          邮箱
        </div>
      </template>
      {{ data.email }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <iphone />
          </el-icon>
          手机号
        </div>
      </template>
      {{ data.phone }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <calendar />
          </el-icon>
          出生日期
        </div>
      </template>
      {{ new Date(data.birthDate).toLocaleDateString() }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <office-building />
          </el-icon>
          部门
        </div>
      </template>
      {{ data.department }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <tickets />
          </el-icon>
          职位
        </div>
      </template>
      {{ data.position }}
    </el-descriptions-item>
  </el-descriptions>
</template>

<style scoped>
.cell-item svg {
  transform: translateY(2px);
}
</style>
