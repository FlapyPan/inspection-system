<script setup>
import { attendanceFields } from '../assets/fields'
import { computed, onMounted, reactive, ref } from 'vue'
import { useUserStore } from '../stores/user'
import { request } from '../api/request'
import { ElMessage } from 'element-plus'

const props = reactive({
  fields: attendanceFields,
  url: '/attendance',
  name: '考勤',
  data: [],
})

const userStore = useUserStore()

const search = ref('')
const tableData = computed(() => props.data.filter((d) => {
  const v = search.value.trim().toLowerCase()
  if (v === '') return true
  for (const field of props.fields) {
    if (d[field.name]
      && d[field.name].toLowerCase
      && d[field.name].toLowerCase().includes(v)) {
      return true
    }
  }
  return false
}))

const edit = ref(false)

const fetchingTable = ref(true)
const fetchTableData = (searchKey) => {
  fetchingTable.value = true
  request({
    url: `${props.url}/employee/${userStore.user.id}`,
    method: 'get',
  }).then(({data}) => {
    props.data = data || []
  }).catch(({msg}) => ElMessage.error(msg))
    .finally(() => fetchingTable.value = false)
}
onMounted(() => fetchTableData())

</script>

<template>
  <div>
    <el-card>
      <template #header>
        <div style="display: flex;align-items: center;justify-content: space-between">
          <el-text style="font-weight: bold">{{ props.name }}</el-text>
          <div style="flex: 1"></div>
          <el-input style="width: 200px" v-model="search" size="small" placeholder="输入关键字搜索" />
        </div>
      </template>
      <div v-loading="fetchingTable">
        <el-table :data="tableData" :stripe="true" border style="width: 100%">
          <template v-for="f in props.fields">
            <el-table-column :label="f.description" :prop="f.name" v-if="f.name!=='employeeId'&&f.name!=='id'" />
          </template>
        </el-table>
      </div>

    </el-card>
  </div>
</template>

<style scoped>
</style>
