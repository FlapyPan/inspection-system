<script setup>
import { salaryFields } from '../assets/fields'
import { onMounted, reactive, ref } from 'vue'
import { useUserStore } from '../stores/user'
import { request } from '../api/request'
import { ElMessage } from 'element-plus'

const props = reactive({
  fields: salaryFields,
  url: '/salary',
  name: '工资',
})

const userStore = useUserStore()

const fetchedData = ref({})
const fetching = ref(true)
const fetchData = (searchKey) => {
  fetching.value = true
  request({
    url: `${props.url}/employee/${userStore.user.id}`,
    method: 'get',
  }).then(({data}) => {
    fetchedData.value = data || {}
  }).catch(({msg}) => ElMessage.error(msg))
    .finally(() => fetching.value = false)
}
onMounted(() => fetchData())

</script>

<template>
  <div>
    <el-card>
      <el-descriptions :column="2" border>
        <template v-for="(field,i) in props.fields">
          <el-descriptions-item v-if="field.name!=='id'">
            <template #label>
              <div>{{ field.description }}</div>
            </template>
            {{ fetchedData[field.name] }}
          </el-descriptions-item>
        </template>
      </el-descriptions>
    </el-card>
  </div>
</template>

<style scoped>
</style>
