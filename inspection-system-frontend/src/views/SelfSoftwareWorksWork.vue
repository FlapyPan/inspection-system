<script setup>
import { softwareWorkFields } from '../assets/fields'
import DefaultView from '../components/DefaultView.vue'
</script>

<template>
  <default-view :fields="softwareWorkFields" url="/softwareWorksWork" name="软件著作" />
</template>

<style scoped>
</style>
