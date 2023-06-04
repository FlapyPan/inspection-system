<script setup>
import { attendanceFields } from '../assets/fields'
import DefaultView2 from '../components/DefaultView2.vue'
</script>

<template>
  <default-view2 :fields="attendanceFields" name="考勤管理" url="/attendance" />
</template>

<style scoped>
</style>
