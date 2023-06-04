<script setup>
import { useUserStore } from '../stores/user'
import { computed, ref } from 'vue'

const props = defineProps({
  data: {
    type: Array,
    default() {return []},
  },
  fields: {
    type: Array,
    default() {return []},
  },
})
const emits = defineEmits(['add', 'edit', 'delete', 'auditor'])

const userStore = useUserStore()

const search = ref('')
const tableData = computed(() => props.data.filter((d) => {
  const v = search.value.trim().toLowerCase()
  if (v === '') return true
  for (const field of props.fields) {
    if (d[field.name]
      && d[field.name].toLowerCase
      && d[field.name].toLowerCase().includes(v)) {
      return true
    }
  }
  return false
}))

const handleAdd = () => {
  emits('add')
}

const handleEdit = (row) => {
  emits('edit', {...row})
}

const handleAuditor = (row) => {
  emits('auditor', {...row})
}

const handleDelete = (row) => {
  emits('delete', row)
}
</script>

<template>
  <div>
    <el-table :data="tableData" :stripe="true" border style="width: 100%">
      <template v-for="f in fields">
        <template v-if="f.name!=='id'&&userStore.hasAnyRoles(f.roles)">
          <template v-if="f.name==='approvalStatus'">
            <el-table-column :label="f.description" :prop="f.name" :width="90" align="center">
              <template #default="scope">
                <el-tag disable-transitions v-if="scope.row['approvalStatus']+'' === '0'">待审核</el-tag>
                <el-tag disable-transitions v-else-if="scope.row['approvalStatus']+'' === '1'" type="success">通过</el-tag>
                <el-tag disable-transitions v-else-if="scope.row['approvalStatus']+'' === '2'" type="danger">未通过
                </el-tag>
                <el-tag disable-transitions v-else type="info">未知</el-tag>
              </template>
            </el-table-column>
          </template>
          <template v-else-if="f.type==='date'">
            <el-table-column :label="f.description" :prop="f.name" :sortable="f.sortable">
              <template #default="scope">
                {{ new Date(scope.row[f.name]).toLocaleDateString() }}
              </template>
            </el-table-column>
          </template>
          <template v-else-if="f.type==='datetime'">
            <el-table-column :label="f.description" :prop="f.name" :sortable="f.sortable">
              <template #default="scope">
                {{ new Date(scope.row[f.name]).toLocaleTimeString() }}
              </template>
            </el-table-column>
          </template>
          <template v-else>
            <el-table-column :label="f.description" :prop="f.name" :sortable="f.sortable" />
          </template>
        </template>
      </template>
      <el-table-column label="操作" align="center" fixed="right" :width="200">
        <template #header="scope">
          <el-input v-model="search" size="small" placeholder="输入关键字搜索" />
        </template>
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">
            编辑
          </el-button>
          <el-button type="primary" size="small" @click="handleAuditor(scope.row)">
            审核
          </el-button>
          <el-popconfirm title="确认删除？" @confirm="handleDelete(scope.row)">
            <template #reference>
              <el-button size="small" type="danger">
                删除
              </el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style scoped>
</style>
