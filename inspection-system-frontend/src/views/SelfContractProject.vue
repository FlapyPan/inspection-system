<script setup>
import { contractFields } from '../assets/fields'
import DefaultView from '../components/DefaultView.vue'
</script>

<template>
  <default-view :fields="contractFields" url="/project" name="合同项目" />
</template>

<style scoped>
</style>
