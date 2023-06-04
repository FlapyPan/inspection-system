<script setup>
import { ElMessageBox } from 'element-plus'

const props = defineProps({
  data: {
    type: Object,
    default() {return {}},
  },
  fields: {
    type: Array,
    default() {return []},
  },
})
const emits = defineEmits(['submit', 'cancel'])

const submitError = () => {
  emits('submit', {...props.data, approvalStatus: '2', score: 0})
}
const submitOK = () => {
  ElMessageBox.prompt('请输入分数', '审核', {
    confirmButtonText: '提交',
    cancelButtonText: '取消',
  }).then(({value}) => {
    emits('submit', {...props.data, approvalStatus: '1', score: value})
  }).catch(() => {})
}
</script>

<template>
  <div>
    <el-descriptions :column="1" border>
      <el-descriptions-item v-for="(field,i) in fields">
        <template #label>
          <div>{{ field.description }}</div>
        </template>
        {{ data[field.name] }}
      </el-descriptions-item>
    </el-descriptions>
    <div class="btn">
      <el-button @click="emits('cancel')">取消</el-button>
      <el-button @click="submitError()" type="danger">未通过</el-button>
      <el-button @click="submitOK()" type="success">通过</el-button>
    </div>
  </div>
</template>

<style scoped>
.btn {
  margin-top: 10px;
  display: flex;
  align-items: center;
  justify-content: end;
}
</style>
