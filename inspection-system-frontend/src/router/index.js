import { createRouter, createWebHashHistory } from 'vue-router'
import { useUserStore } from '../stores/user'
import { roles } from '../assets/fields'

export const routerMap = [
  {
    path: '/',
    component: () => import('../views/HomeView.vue'),
    meta: {roles: [...roles]},
  },
  {
    path: '/login',
    component: () => import('../views/LoginView.vue'),
    children: [],
    meta: {roles: []},
  },
  {
    path: '/self-info',
    component: () => import('../views/SelfInfo.vue'),
    meta: {roles: [...roles]},
  },
  {
    path: '/self-attendance',
    component: () => import('../views/SelfAttendance.vue'),
    meta: {roles: [roles[0]]},
  },
  {
    path: '/self-salary',
    component: () => import('../views/SelfSalary.vue'),
    meta: {roles: [roles[0]]},
  },
  {
    path: '/self-book',
    component: () => import('../views/SelfBooker.vue'),
    meta: {roles: [roles[0]]},
  },
  {
    path: '/self-contract-project',
    component: () => import('../views/SelfContractProject.vue'),
    meta: {roles: [roles[0]]},
  },
  {
    path: '/self-patent',
    component: () => import('../views/SelfPatent.vue'),
    meta: {roles: [roles[0]]},
  },
  {
    path: '/self-software-work',
    component: () => import('../views/SelfSoftwareWorksWork.vue'),
    meta: {roles: [roles[0]]},
  },
  {
    path: '/self-thesis',
    component: () => import('../views/SelfThesis.vue'),
    meta: {roles: [roles[0]]},
  },
  {
    path: '/admin-book',
    component: () => import('../views/AdminBooker.vue'),
    meta: {roles: [roles[1], roles[2]]},
  },
  {
    path: '/admin-contract-project',
    component: () => import('../views/AdminContractProject.vue'),
    meta: {roles: [roles[1], roles[2]]},
  },
  {
    path: '/admin-patent',
    component: () => import('../views/AdminPatent.vue'),
    meta: {roles: [roles[1], roles[2]]},
  },
  {
    path: '/admin-software-work',
    component: () => import('../views/AdminSoftwareWorksWork.vue'),
    meta: {roles: [roles[1], roles[2]]},
  },
  {
    path: '/admin-thesis',
    component: () => import('../views/AdminThesis.vue'),
    meta: {roles: [roles[1], roles[2]]},
  },
  {
    path: '/broadcast-manage',
    component: () => import('../views/AdminBroadcast.vue'),
    meta: {roles: [roles[1], roles[2]]},
  },
  {
    path: '/attendance-manage',
    component: () => import('../views/AdminAttendance.vue'),
    meta: {roles: [roles[1], roles[2]]},
  },
  {
    path: '/salary-manage',
    component: () => import('../views/AdminSalary.vue'),
    meta: {roles: [roles[1], roles[2]]},
  },
  {
    path: '/user-manage',
    component: () => import('../views/AdminUserInfo.vue'),
    meta: {roles: [roles[2]]},
  },
]

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes: [...routerMap, {path: '/:catchAll(.*)', redirect: '/'}],
})

router.beforeEach((to, from, next) => {
  const userStore = useUserStore()
  if (to.path !== '/login' && !userStore.isLogin()) {
    next('/login')
    return
  }
  const roles = to.meta?.roles
  if (userStore.hasAnyRoles(roles)) {
    next()
  }
})

export default router
