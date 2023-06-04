<script setup>
import { softwareWorkFields } from '../assets/fields'
import AuditorView from '../components/AuditorView.vue'
</script>

<template>
  <auditor-view :fields="softwareWorkFields" name="软件著作" url="/softwareWorksWork" />
</template>

<style scoped>
</style>
