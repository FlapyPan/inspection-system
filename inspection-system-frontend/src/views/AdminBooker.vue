<script setup>
import { bookFields } from '../assets/fields'
import AuditorView from '../components/AuditorView.vue'
</script>

<template>
  <auditor-view :fields="bookFields" name="书籍著作" url="/booksWork" />
</template>

<style scoped>
</style>
