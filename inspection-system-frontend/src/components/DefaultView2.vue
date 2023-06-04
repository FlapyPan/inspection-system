<script setup>
import { onMounted, ref } from 'vue'
import DefaultTable from '../components/DefaultTable.vue'
import DefaultForm from '../components/DefaultForm.vue'
import { request } from '../api/request'
import { ElMessage } from 'element-plus'
import { useUserStore } from '../stores/user'

const props = defineProps({
  fields: {type: Array},
  url: {type: String},
  name: {type: String},
})

const userStore = useUserStore()

const tableData = ref([])

const form = ref({})
const edit = ref(false)

const fetchingTable = ref(true)
const fetchTableData = (searchKey) => {
  fetchingTable.value = true
  request({
    url: `${props.url}`,
    method: 'get',
  }).then(({data}) => {
    console.log(data)
    tableData.value = data || []
  }).catch(({msg}) => ElMessage.error(msg))
    .finally(() => fetchingTable.value = false)
}
onMounted(() => fetchTableData())

const handleAdd = () => {
  const row = {}
  props.fields.forEach((f) => row[f.name] = '')
  row['roles'] = []
  row['id'] = null
  handleEdit(row)
}

const handleEdit = (row) => {
  form.value = row
  edit.value = true
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
  data['employeeId'] = userStore.user.id
  if (data['id']) {
    request({
      url: `${props.url}/${data['id']}`,
      method: 'post',
      data,
    }).then(() => {
      ElMessage.success('修改成功')
      fetchTableData()
      edit.value = false
    }).catch(({msg}) => ElMessage.error(msg))
  } else {
    data['approvalStatus'] = '0'
    data['publisherId'] = userStore.user.id
    request({
      url: `${props.url}`,
      method: 'put',
      data,
    }).then(() => {
      if (props.name === '用户') {
        ElMessage.success('添加成功，新用户密码默认为：123456')
      } else
        ElMessage.success('添加成功')
      fetchTableData()
      edit.value = false
    }).catch(({msg}) => ElMessage.error(msg))
  }
}

</script>

<template>
  <div>
    <el-dialog
      v-model="edit"
      :title="form.id===null?'添加信息':'修改信息'"
      destroy-on-close
      center
      :show-close="false"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <div>
        <default-form :fields="props.fields" :data="form" @submit="onSubmit" @cancel="edit=false" />
      </div>
    </el-dialog>
    <el-card>
      <template #header>
        <div style="display: flex;align-items: center;justify-content: space-between">
          <el-text style="font-weight: bold">{{ props.name }}</el-text>
        </div>
      </template>
      <div v-loading="fetchingTable">
        <default-table :data="tableData" :fields="props.fields" @add="handleAdd" @edit="handleEdit"
                       @delete="handleDelete" />
      </div>

    </el-card>
  </div>
</template>

<style scoped>
</style>
