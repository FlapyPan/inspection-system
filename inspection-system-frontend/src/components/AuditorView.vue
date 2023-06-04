<script setup>
import { onMounted, ref } from 'vue'
import { request } from '../api/request'
import { ElMessage } from 'element-plus'
import { useUserStore } from '../stores/user'
import AuditorForm from '../components/AuditorForm.vue'
import AuditorTable from '../components/AuditorTable.vue'
import DefaultForm from '../components/DefaultForm.vue'

const props = defineProps({
  fields: {type: Array},
  url: {type: String},
  name: {type: String},
})

const userStore = useUserStore()
const tableData = ref([])

const form = ref({})
const edit = ref(false)
const auditor = ref(false)

const fetchingTable = ref(true)
const fetchTableData = (searchKey) => {
  fetchingTable.value = true
  request({
    url: `${props.url}`,
    method: 'get',
  }).then(({data}) => {
    tableData.value = data || []
  }).catch(({msg}) => ElMessage.error(msg))
    .finally(() => fetchingTable.value = false)
}
onMounted(() => fetchTableData())

const handleAdd = () => {
  const row = {}
  props.fields.forEach((f) => row[f.name] = '')
  row['id'] = null
  handleEdit(row)
}

const handleEdit = (row) => {
  form.value = row
  edit.value = true
}

const handleAuditor = (row) => {
  form.value = row
  auditor.value = true
}

const handleDelete = (row) => {
  request({
    url: `${props.url}/${row.id}`,
    method: 'delete',
  }).then(() => {
    ElMessage.info('删除成功')
    fetchTableData()
  }).catch(({msg}) => ElMessage.error(msg))
}

const onSubmit = (data) => {
  if (data['id']) {
    request({
      url: `${props.url}/${data['id']}`,
      method: 'post',
      data,
    }).then(() => {
      ElMessage.info('修改成功')
      fetchTableData()
      edit.value = false
      auditor.value = false
    }).catch(({msg}) => ElMessage.error(msg))
  } else {
    data['approvalStatus'] = '0'
    request({
      url: `${props.url}`,
      method: 'put',
      data,
    }).then(() => {
      ElMessage.info('添加成功')
      fetchTableData()
      edit.value = false
      auditor.value = false
    }).catch(({msg}) => ElMessage.error(msg))
  }
}

</script>

<template>
  <div>
    <el-dialog
      v-model="edit"
      title="修改信息"
      destroy-on-close
      center
      :show-close="false"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <div>
        <default-form :fields="fields" :data="form" @submit="onSubmit" @cancel="edit=false" />
      </div>
    </el-dialog>
    <el-dialog
      v-model="auditor"
      title="审核"
      destroy-on-close
      center
      :show-close="false"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <div>
        <auditor-form :fields="fields" :data="form" @submit="onSubmit" @cancel="auditor=false" />
      </div>
    </el-dialog>
    <el-card>
      <template #header>
        <div style="display: flex;align-items: center;justify-content: space-between">
          <el-text style="font-weight: bold">{{ props.name }}审核</el-text>
        </div>
      </template>
      <div v-loading="fetchingTable">
        <auditor-table :data="tableData" :fields="fields" @add="handleAdd" @edit="handleEdit"
                       @auditor="handleAuditor" @delete="handleDelete" />
      </div>

    </el-card>
  </div>
</template>

<style scoped>
</style>
