<script setup>
import { Finished } from '@element-plus/icons-vue'
import { useUserStore } from '../stores/user'
import { computed, onMounted, reactive, ref } from 'vue'
import { use } from 'echarts/core'
import VChart from 'vue-echarts'
import { CanvasRenderer } from 'echarts/renderers'
import { PieChart } from 'echarts/charts'
import { TitleComponent, TooltipComponent } from 'echarts/components'
import router from '../router'
import { request } from '../api/request'
import { ElMessage, ElMessageBox } from 'element-plus'
import { roles } from '../assets/fields'

const props = defineProps({})

const userStore = useUserStore()

const attendanceStatus = ref([])
const fetchingAS = ref(false)
const fetchAttendanceStatus = () => {
  fetchingAS.value = true
  request({
    url: `/attendance/employee/${userStore.user.id}`,
    method: 'get',
  }).then(({data}) => {
    attendanceStatus.value = data || []
  }).catch(({msg}) => ElMessage.error(msg))
    .finally(() => fetchingAS.value = false)
}
onMounted(() => {
  if (userStore.hasAnyRoles([roles[0]])) {
    fetchAttendanceStatus()
  }
})

const isAttendanceCheck = computed(() => {
  for (const status of attendanceStatus.value) {
    const d = new Date(status.checkinTime)
    const now = new Date()
    if (d.setHours(0, 0, 0, 0) === now.setHours(0, 0, 0, 0)) {
      return true
    }
  }
  return false
})

const addAttendance = () => {
  fetchingAS.value = true
  const now = new Date()
  request({
    url: `/attendance`,
    method: 'put',
    data: {
      checkinTime: now.toLocaleString(),
      isAskForLeave: false,
      askForLeaveReason: null,
      other: null,
      employeeId: userStore.user.id,
    },
  }).then(() => {
    ElMessage.success('打卡成功')
  }).then(fetchAttendanceStatus).catch(({msg}) => ElMessage.error(msg))
    .finally(() => fetchingAS.value = false)
}

use([
  CanvasRenderer,
  PieChart,
  TitleComponent,
  TooltipComponent,
])

const kpiData = ref(null)
const option = reactive({
  title: {
    text: '本月科研成果、项目KPI',
    left: 'center',
  },
  tooltip: {
    trigger: 'item',
    formatter: '{b} : {c} ({d}%)',
  },
  series: [
    {
      name: '本月科研成果、项目KPI',
      type: 'pie',
      data: [],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)',
        },
      },
    },
  ],
})

const parseKpiStructs = ({kpiStructs: structs, kpi}) => {
  console.log()
  if (!structs) return [
    {value: kpi, name: '考勤'},
  ]
  for (let i = 0; i < structs.length; i++) {
    const name = structs[i].name
    if (name === 'thesis') structs[i].name = '论文'
    if (name === 'book') structs[i].name = '书籍著作'
    if (name === 'patent') structs[i].name = '专利'
    if (name === 'softwareWork') structs[i].name = '软件著作'
    if (name === 'contracts') structs[i].name = '合同项目'
  }
  return structs
}


const fetchingKPI = ref(false)
const fetchKPIData = () => {
  fetchingKPI.value = true
  request({
    url: `/api/user/kpi/${userStore.user.id}`,
    method: 'get',
  }).then(({data}) => {
    kpiData.value = data
    if (data.kpi) {
      option.series[0].data = parseKpiStructs(data)
    } else {
      option.series[0].data = []
    }
  }).then(fetchAttendanceStatus).catch(({msg}) => ElMessage.error(msg))
    .finally(() => fetchingKPI.value = false)
}
onMounted(() => {
  if (userStore.hasAnyRoles([roles[0]])) {
    fetchKPIData()
  }
})

const noticeData = ref([])
const fetchingNotice = ref(false)
const fetchNotice = () => {
  fetchingNotice.value = true
  request({
    url: `/broadcast`,
    method: 'get',
  }).then(({data}) => {
    noticeData.value = data || []
  }).catch(({msg}) => ElMessage.error(msg))
    .finally(() => fetchingNotice.value = false)
}
onMounted(() => {
  fetchNotice()
})
const openNotice = ({title, content}) => {
  ElMessageBox.alert(content, title, {
    confirmButtonText: '关闭',
    showClose: false,
  }).catch(() => {})
}

const showCalendar = ref(false)
const date = new Date()
const lastDay = date.getDate()
const checkData = computed(() => {
  const data = []
  for (let i = 1; i <= lastDay; i++) {
    date.setDate(i)
    let b = false
    for (const status of attendanceStatus.value) {
      const d = new Date(status.checkinTime)
      if (d.setHours(0, 0, 0, 0) === date.setHours(0, 0, 0, 0)) {
        b = true
        break
      }
    }
    data.push(b)
  }
  return data
})
</script>

<template>
  <div>
    <el-dialog
      v-model="showCalendar"
      title="本月考勤情况"
      center
      :show-close="true"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <div>
        <el-descriptions :column="3" border>
          <el-descriptions-item v-for="(b,i) in checkData">
            <template #label>
              <div>{{ date.getMonth() + 1 }}月{{ i + 1 }}日</div>
            </template>
            {{ b ? '✔️' : '' }}
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </el-dialog>

    <div style="display: flex;margin-bottom: 10px">
      <el-card style="flex: 2;margin-right: 10px">
        <template #header>
          <div style="display: flex;align-items: center;justify-content: space-between">
            <el-text style="font-weight: bold">基本信息</el-text>
            <el-button text type="primary" size="small" @click="router.push('/self-info')">查看详细信息</el-button>
          </div>
        </template>
        <div style="display: flex;align-items: center;justify-content: space-between">
          <div style="display: flex;flex-direction: column">
            <el-text size="small" style="margin-bottom: 1em" type="info">姓名</el-text>
            <el-text size="large">{{ userStore.user.name || '无' }}</el-text>
          </div>
          <div style="display: flex;flex-direction: column">
            <el-text size="small" style="margin-bottom: 1em" type="info">部门</el-text>
            <el-text size="large">{{ userStore.user.department || '无' }}</el-text>
          </div>
          <div style="display: flex;flex-direction: column">
            <el-text size="small" style="margin-bottom: 1em" type="info">职位</el-text>
            <el-text size="large">{{ userStore.user.position || '无' }}</el-text>
          </div>
        </div>
      </el-card>
      <el-card class="broadcast-card" style="flex: 1">
        <template #header>
          <el-text style="font-weight: bold">每日考勤</el-text>
        </template>
        <div v-loading="fetchingAS" style="display: flex;flex-direction: column">
          <div style="margin-bottom: 1em">
            <el-text type="success" v-if="isAttendanceCheck">
              <el-icon>
                <Finished />
              </el-icon>
              今天的考勤已打
            </el-text>
            <div v-else>
              <el-button type="primary" @click="addAttendance()">
                打考勤
              </el-button>
            </div>
          </div>
          <el-button @click="showCalendar=true" size="small">查看本月考勤</el-button>
        </div>
      </el-card>
    </div>

    <div style="display: flex">
      <div style="flex: 2;margin-right: 10px;">
        <el-card>
          <div style="display: flex;align-items: center;flex-wrap: wrap">
            <el-text type="success" style="margin-right: 1em;font-size: 1.5rem">{{ kpiData?.kpi || 0 }}</el-text>
          </div>
          <div v-if="kpiData?.kpi" style="height: 500px;width: 100%">
            <v-chart :option="option" autoresize />
          </div>
          <el-empty v-else />
        </el-card>
      </div>
      <el-card class="broadcast-card" style="flex: 1">
        <template #header>
          <el-row justify="space-between" align="middle">
            <el-text style="font-weight: bold">通知公告</el-text>
          </el-row>
        </template>
        <div class="broadcast-card-body">
          <template v-for="n in noticeData">
            <div class="broadcast-title">
              <el-button text @click="openNotice(n)">
                <el-text style="max-width: 120px" truncated>{{ n.title }}</el-text>
              </el-button>
              <el-text type="info">{{ n.publishDate }}</el-text>
            </div>
          </template>
        </div>
      </el-card>
    </div>

  </div>
</template>

<style scoped>
.broadcast-card {
  margin: 0 auto;
}

.broadcast-card-body {
  display: flex;
  flex-direction: column;
  align-items: stretch;
}

.broadcast-title {
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-wrap: wrap;
}

</style>
