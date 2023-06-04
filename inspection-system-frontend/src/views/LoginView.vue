<script setup>
import CaptchaMini from '../assets/captcha-mini'
import { nextTick, onMounted, reactive, ref } from 'vue'
import { useUserStore } from '../stores/user'
import router from '../router'
import { ElMessage } from 'element-plus'
import { request } from '../api/request'

const props = defineProps({})

const processing = ref(false)
const formEl = ref(null)
const captchaValue = ref('')
const loginForm = reactive({
  phone: '',
  password: '',
  captcha: '',
})
const geneCaptcha = () => {
  const c = new CaptchaMini({
    length: 4,
    fontSize: 80,
  })
  c.draw(document.querySelector('#captcha'), (code) => {
    captchaValue.value = code.toLowerCase()
  })
}
onMounted(geneCaptcha)
const loginFormRules = reactive({
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
  captcha: [
    {
      required: true,
      message: '请输入验证码',
      trigger: 'blur',
    },
  ],
})

const userStore = useUserStore()
const onSubmit = () => {
  if (captchaValue.value !== loginForm.captcha.toLowerCase()) {
    ElMessage.error('验证码错误')
    return
  }
  formEl.value.validate().then((valid) => {
    if (valid) {
      processing.value = true
      userStore
        .login(loginForm.phone, loginForm.password)
        .then(() => {
          router.push('/')
        })
        .catch(({msg}) => ElMessage.error(msg))
        .finally(() => {
          processing.value = false
        })
    }
  }).catch(() => {})
}

const showForgetCard = ref(false)
const forgetFormEl = ref(null)
const forgetForm = reactive({
  name: '',
  phone: '',
  idCard: '',
  newPassword: '',
  captcha: '',
})
const forgetFormRules = reactive({
  name: [
    {
      required: true,
      message: '请输入真实姓名',
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
  idCard: [
    {
      required: true,
      message: '请输入身份证号码',
      trigger: 'blur',
    },
  ],
  newPassword: [
    {
      required: true,
      message: '请输入密码',
      trigger: 'blur',
    },
  ],
  captcha: [
    {
      required: true,
      message: '请输入验证码',
      trigger: 'blur',
    },
  ],
})
const onForgetSubmit = () => {
  if (captchaValue.value !== forgetForm.captcha.toLowerCase()) {
    ElMessage.error('验证码错误')
    return
  }
  forgetFormEl.value.validate().then((valid) => {
    if (valid) {
      processing.value = true
      request({
        url: `/api/auth/forget-password`,
        method: 'post',
        data: forgetForm,
      }).then(() => {
        ElMessage.success('密码重置成功，正在跳转登录页面')
        forgetForm.name = ''
        forgetForm.phone = ''
        forgetForm.idCard = ''
        forgetForm.newPassword = ''
        forgetForm.captcha = ''
        toLogin()
      }).catch(({msg}) => ElMessage.error(msg))
        .finally(() => processing.value = false)
    }
  }).catch(() => {})
}

const toForget = () => {
  showForgetCard.value = true
  nextTick(geneCaptcha)
}
const toLogin = () => {
  showForgetCard.value = false
  nextTick(geneCaptcha)
}
</script>

<template>
  <div v-loading="processing" style="max-width: 50%;margin: 0 auto;">
    <el-card v-if="!showForgetCard">
      <template #header>
        <div>
          <el-text size="large">登录系统</el-text>
        </div>
      </template>
      <el-form
        ref="formEl"
        label-width="100px"
        :model="loginForm"
        :rules="loginFormRules"
        style="max-width: 500px"
      >
        <el-form-item label="手机号" prop="phone">
          <el-input v-model.trim="loginForm.phone" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model.trim="loginForm.password" type="password" />
        </el-form-item>
        <el-form-item label="验证码" prop="captcha">
          <el-col :span="17">
            <el-input v-model.trim="loginForm.captcha" />
          </el-col>
          <el-col :span="1"></el-col>
          <el-col :span="6" style="display: flex;align-items: center">
            <canvas id="captcha" style="width: 100%;height: 32px;cursor: pointer"></canvas>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit()">登录系统</el-button>
          <el-button text @click="toForget()">忘记密码？</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card v-if="showForgetCard">
      <template #header>
        <div>
          <el-text size="large">找回密码</el-text>
        </div>
      </template>
      <el-form
        ref="forgetFormEl"
        label-width="100px"
        :model="forgetForm"
        :rules="forgetFormRules"
        style="max-width: 500px"
      >
        <el-form-item label="姓名" prop="name">
          <el-input v-model.trim="forgetForm.name" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model.trim="forgetForm.phone" />
        </el-form-item>
        <el-form-item label="身份证号码" prop="idCard">
          <el-input v-model.trim="forgetForm.idCard" />
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input v-model.trim="forgetForm.newPassword" type="password" />
        </el-form-item>
        <el-form-item label="验证码" prop="captcha">
          <el-col :span="17">
            <el-input v-model.trim="forgetForm.captcha" />
          </el-col>
          <el-col :span="1"></el-col>
          <el-col :span="6" style="display: flex;align-items: center">
            <canvas id="captcha" style="width: 100%;height: 32px;cursor: pointer"></canvas>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onForgetSubmit()">重置密码</el-button>
          <el-button text @click="toLogin()">返回登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>


  </div>
</template>

<style scoped>
</style>
