<script setup>
import { bookFields } from '../assets/fields'
import DefaultView from '../components/DefaultView.vue'
</script>

<template>
  <default-view :fields="bookFields" url="/booksWork" name="书籍著作" />
</template>

<style scoped>
</style>
