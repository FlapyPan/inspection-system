<script setup>
import { salaryFields } from '../assets/fields'
import DefaultView2 from '../components/DefaultView2.vue'
</script>

<template>
  <default-view2 :fields="salaryFields" name="工资" url="/salary" />
</template>

<style scoped>
</style>
