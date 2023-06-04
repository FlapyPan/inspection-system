<script setup>
import { contractFields } from '../assets/fields'
import AuditorView from '../components/AuditorView.vue'
</script>

<template>
  <auditor-view :fields="contractFields" name="专利" url="/project" />
</template>

<style scoped>
</style>
