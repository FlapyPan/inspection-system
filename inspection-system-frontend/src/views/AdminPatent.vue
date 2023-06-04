<script setup>
import { patentFields } from '../assets/fields'
import AuditorView from '../components/AuditorView.vue'
</script>

<template>
  <auditor-view :fields="patentFields" name="专利" url="/patent" />
</template>

<style scoped>
</style>
