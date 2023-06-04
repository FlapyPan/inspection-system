<script setup>
import { reactive, ref } from 'vue'
import { Picture as IconPicture } from '@element-plus/icons-vue'
import { useUserStore } from '../stores/user'
import { request } from '../api/request'
import { ElMessage } from 'element-plus'

const props = defineProps({})

const processing = ref(false)
const formEl = ref(null)
const form = reactive({
  name: '',
  phone: '',
  password: '',
  captcha: '',
})
const formRules = reactive({
  name: [
    {
      required: true,
      message: '请输入姓名',
      trigger: 'blur',
    },
  ],
  phone: [
    {
      required: true,
      message: '请输入手机号',
      trigger: 'blur',
    },
  ],
  password: [
    {
      required: true,
      message: '请输入密码',
      trigger: 'blur',
    },
  ],
  rePassword: [
    {
      required: true,
      message: '请再次输入密码',
      trigger: 'blur',
    },
  ],
})

const userStore = useUserStore()
const onSubmit = () => {
  formEl.value.validate().then((valid) => {
    if (valid) {
      processing.value = true
      request({
        url: `/api/auth/register`,
        method: 'post',
        data: {name: form.name, phone: form.phone, password: form.password},
      }).then(() => {
      }).catch(({msg}) => ElMessage.error(msg))
        .finally(() => {
          processing.value = false
        })
    }
  }).catch(() => {})
}

</script>

<template>
  <div v-loading="processing" style="max-width: 50%;margin: 0 auto;">
    <el-card>
      <template #header>
        <div>
          <el-text size="large">注册账号</el-text>
        </div>
      </template>
      <el-form
        ref="formEl"
        label-width="100px"
        :model="form"
        :rules="formRules"
        style="max-width: 500px"
      >
        <el-form-item label="姓名" prop="name">
          <el-input v-model.trim="form.name" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model.number="form.phone" type="phone" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model.trim="form.password" type="password" />
        </el-form-item>
        <el-form-item label="验证码" prop="captcha">
          <el-col :span="17">
            <el-input v-model.trim="form.captcha" />
          </el-col>
          <el-col :span="1"></el-col>
          <el-col :span="6" style="display: flex;align-items: center">
            <el-image style="width: 100%;height: 32px;cursor: pointer" fit="contain" src="null">
              <template #error>
                <div class="no-select" style="font-size: 12px;cursor: pointer">
                  <el-icon>
                    <icon-picture />
                  </el-icon>
                  点击刷新
                </div>
              </template>
            </el-image>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit()">注册</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped>
</style>
