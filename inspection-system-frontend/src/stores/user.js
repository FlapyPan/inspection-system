import { ref } from 'vue'
import { defineStore } from 'pinia'
import { formRequest, request } from '../api/request'
import { ElMessage, ElMessageBox } from 'element-plus'
import router from '../router'

const TOKEN_STORAGE_KEY = 'token'

export const useUserStore = defineStore('user', () => {
  const user = ref(null)

  const token = ref(localStorage.getItem(TOKEN_STORAGE_KEY))
  const setToken = (v) => {
    if (!!v) {
      localStorage.setItem(TOKEN_STORAGE_KEY, v)
      token.value = v
    }
  }
  const getInfo = () => {
    return request({
      url: `/api/user/userInfo`,
      method: 'get',
    }).then(({data}) => {
      if (data?.roles?.length === 0) {
        return Promise.reject({
          code: 403,
          msg: '此账号无权使用系统，请联系管理员',
          data: null,
        })
      }
      user.value = data
    })
  }
  if (token.value) {
    ElMessage.info('登录中...')
    getInfo().then(() => router.push('/'),
    ).catch(() => {})
  }
  const login = (phone, password) => {
    return formRequest({
      url: `/api/auth/login`,
      data: {phone, password},
    }).then(({data}) => {
      if (password === '123456') ElMessageBox.alert('您使用的是默认密码，请尽快修改以保证安全', '提示')
      setToken(data?.access_token)
    }).then(getInfo)
  }
  const logout = () => {
    localStorage.removeItem(TOKEN_STORAGE_KEY)
    location.reload()
  }
  const isLogin = () => user.value?.roles?.length > 0
  const hasAnyRoles = (roles = []) => {
    if (roles.length === 0) return true
    const userRoles = user.value?.roles || []
    return userRoles.some((has) => roles.some((role) => has === role))
  }

  return {
    user, token,
    login, getInfo,
    logout,
    isLogin,
    hasAnyRoles,
  }
})
