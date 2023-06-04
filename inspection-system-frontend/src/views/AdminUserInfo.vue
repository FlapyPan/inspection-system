<script setup>
import { userFields } from '../assets/fields'
import DefaultView2 from '../components/DefaultView2.vue'
</script>

<template>
  <default-view2 :fields="userFields" name="用户" url="/api/user" />
</template>

<style scoped>
</style>
