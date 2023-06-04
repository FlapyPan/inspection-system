<script setup>
import { patentFields } from '../assets/fields'
import DefaultView from '../components/DefaultView.vue'
</script>

<template>
  <default-view :fields="patentFields" url="/patent" name="专利" />
</template>

<style scoped>
</style>
