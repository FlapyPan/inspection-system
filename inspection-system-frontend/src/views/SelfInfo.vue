<script setup>
import { computed, onMounted, onUnmounted, ref } from 'vue'
import { useUserStore } from '../stores/user'
import UserInfo from '../components/user/UserInfo.vue'
import DefaultForm from '../components/DefaultForm.vue'
import { userFields } from '../assets/fields'
import { request } from '../api/request'
import { ElMessage } from 'element-plus'

const props = defineProps({})

const userStore = useUserStore()

const changePwdForm = ref({
  oldPassword: '',
  newPassword: '',
})
const changePwd = ref(false)
const openChangePwd = () => {
  changePwd.value = true
}
const changingPwd = ref(false)
const onPwdSubmit = () => {
  submitForm.value = true
  request({
    url: `/api/user/${userStore.user.id}/change-password`,
    method: 'post',
    data: changePwdForm.value,
  }).then(() => {
    changePwd.value = false
    changePwdForm.value = {
      oldPassword: '',
      newPassword: '',
    }
    ElMessage.success('修改成功，下次登录时生效')
  }).catch(({msg}) => ElMessage.error(msg))
    .finally(() => changingPwd.value = false)
}

const form = ref({})
const edit = ref(false)
const openEdit = () => {
  form.value = {...userStore.user}
  edit.value = true
}
const submitForm = ref(false)
const onSubmit = (data) => {
  submitForm.value = true
  request({
    url: `/api/user/${userStore.user.id}`,
    method: 'post',
    data,
  }).then(() => {
    userStore.getInfo()
    edit.value = false
    ElMessage.success('保存成功！')
  }).catch(({msg}) => ElMessage.error(msg))
    .finally(() => submitForm.value = false)
}


const fetching = ref(false)
const selectedDate = ref('')
const dateValid = computed(() => {
  if (selectedDate.value.trim() === '') return false
  const sel = new Date(selectedDate.value)
  const now = new Date()
  return sel.getFullYear() <= now.getFullYear() && sel.getMonth() < now.getMonth()
})

const fetchingThisMonth = ref(false)
const evaluationData = ref('')
let fetchedData = ''
const fetchThisMonth = () => {
  fetchingThisMonth.value = true
  request({
    url: `/evaluation/${userStore.user.id}`,
    method: 'get',
  }).then(({data}) => {
    fetchedData = data?.content || ''
    evaluationData.value = fetchedData.trim()
  }).catch(() => ElMessage.error('本月自评获取失败'))
    .finally(() => fetchingThisMonth.value = false)
}
onMounted(fetchThisMonth)
const save = () => {
  const now = new Date()
  request({
    url: `/evaluation/${userStore.user.id}`,
    method: 'post',
    data: {
      score: 0,
      employeeId: userStore.user.id + '',
      content: evaluationData.value,
      year: now.getFullYear(),
      month: now.getMonth() + 1,
    },
  }).then(() => {
    ElMessage.success('保存成功！')
  }).catch(({msg}) => ElMessage.error(msg))
}

const historyData = ref('')
const fetchHistoryData = () => {
  historyData.value = ''
  fetching.value = true
  const sel = new Date(selectedDate.value)
  request({
    url: `/evaluation/${userStore.user.id}/${sel.getFullYear()}/${sel.getMonth() + 1}`,
    method: 'get',
  }).then(({data}) => {
    historyData.value = data?.content?.trim() || '(无信息)'
  }).catch(({msg}) => ElMessage.error(msg))
    .finally(() => fetching.value = false)

}

const dataChanged = ref(false)
let checkChangedId = 0
const checkChanged = () => {
  dataChanged.value = !(evaluationData.value.trim() === fetchedData?.trim())
}
onMounted(() => {
  checkChangedId = setInterval(checkChanged, 500)
})
onUnmounted(() => clearInterval(checkChangedId))
</script>

<template>
  <div>
    <el-dialog
      v-model="changePwd"
      title="修改密码"
      destroy-on-close
      center
      :show-close="false"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <div v-loading="changingPwd">
        <el-form
          ref="formEl"
          label-width="100px"
          :model="changePwdForm"
          style="max-width: 500px"
        >
          <el-form-item label="旧密码" prop="oldPassword">
            <el-input v-model.trim="changePwdForm.oldPassword" type="password" />
          </el-form-item>
          <el-form-item label="新密码" prop="newPassword">
            <el-input v-model.trim="changePwdForm.newPassword" type="password" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onPwdSubmit()">修改密码</el-button>
            <el-button text @click="changePwd=false">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>
    <el-dialog
      v-model=" edit
            "
      title="修改信息"
      destroy-on-close
      center
      :show-close="false"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <div>
        <default-form :fields="userFields" :data="form" @submit="onSubmit" @cancel="edit=false" />
      </div>
    </el-dialog>
    <el-card style="margin-bottom: 10px">
      <user-info :data="userStore.user">
        <div style="display: flex">
          <el-button @click="openChangePwd()">修改密码</el-button>
          <el-button type="primary" @click="openEdit()">修改信息</el-button>
        </div>
      </user-info>
    </el-card>

    <div style="display: flex">
      <el-card style="margin-right: 10px;flex: 1">
        <template #header>
          <div style="display: flex;align-items: center;">
            <el-date-picker
              style="margin-right: 1em"
              v-model="selectedDate"
              type="month"
              format="YYYY-MM"
              value-format="YYYY-MM"
              placeholder="选择一个月份"
            />
            <el-button @click="fetchHistoryData()" :disabled="!dateValid">
              查看历史自评
            </el-button>
          </div>
        </template>
        <div v-loading="fetching">
          {{ historyData }}
        </div>
      </el-card>
      <el-card style="flex: 1">
        <template #header>
          <div style="display: flex;align-items: center;">
            <el-text style="font-weight: bold">编写、修改本月自评</el-text>
          </div>
        </template>
        <div v-loading="fetchingThisMonth">
          <el-input
            v-model.trim="evaluationData"
            :autosize="{ minRows: 10 }"
            type="textarea"
            placeholder="编写本月的自评（3000字以内）"
            maxlength="3000"
            style="margin-bottom: 10px"
          />
          <div style="text-align: right">
            <el-button type="success" v-show="dataChanged" @click="save()">保存</el-button>
          </div>
        </div>
      </el-card>
    </div>


  </div>
</template>

<style scoped>
</style>
