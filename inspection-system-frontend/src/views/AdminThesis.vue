<script setup>
import { thesisFields } from '../assets/fields'
import AuditorView from '../components/AuditorView.vue'
</script>

<template>
  <auditor-view :fields="thesisFields" name="论文" url="/thesis" />
</template>

<style scoped>
</style>
