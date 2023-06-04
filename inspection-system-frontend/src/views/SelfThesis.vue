<script setup>
import { thesisFields } from '../assets/fields'
import DefaultView from '../components/DefaultView.vue'
</script>

<template>
  <default-view :fields="thesisFields" url="/thesis" name="论文" />
</template>

<style scoped>
</style>
