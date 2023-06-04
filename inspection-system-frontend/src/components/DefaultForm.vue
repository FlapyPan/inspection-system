<script setup>
import { ref } from 'vue'
import { useUserStore } from '../stores/user'
import { approvalStatusValue, roles, rolesValue } from '../assets/fields'

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

const userStore = useUserStore()

const form = ref(props.data)
const rules = {}
props.fields.filter((f) => !f.required)
  .forEach(({name, type, description}) => {
    rules[name] = {
      type,
      message: `请输入正确的${description}`,
      trigger: 'change',
    }
  })

const onSubmit = () => {
  emits('submit', {...form.value})
}

const hiddenField = ['id', 'score', 'createTime', 'employeeId', 'approvalStatus', 'publishDate', 'publisherId']
const isHiddenField = (field) => {
  return hiddenField.includes(field.name)
}
</script>

<template>
  <el-form :model="form" :rules="rules" label-width="140px">
    <template v-for="(field,i) in fields">
      <el-form-item
        v-if="!isHiddenField(field)"
        :key="i"
        :label="field.description">
        <template v-if="field.type==='enum'">
          <template v-if="field.name==='approvalStatus'">
            <el-radio-group v-model="form[field.name]" :disabled="!userStore.hasAnyRoles(field.roles)">
              <el-radio v-for="v in field.values" :label="approvalStatusValue[v]" />
            </el-radio-group>
          </template>
          <template v-else-if="field.name==='roles'">
            <el-checkbox-group v-model="form[field.name]" :disabled="!userStore.hasAnyRoles(field.roles)">
              <el-checkbox v-for="(v,i) in roles" :label="v">{{ rolesValue[i] }}</el-checkbox>
            </el-checkbox-group>
          </template>
          <template v-else>
            <el-radio-group v-model="form[field.name]" :disabled="!userStore.hasAnyRoles(field.roles)">
              <el-radio v-for="v in field.values" :label="v" />
            </el-radio-group>
          </template>
        </template>
        <template v-else-if="field.type==='date'">
          <el-date-picker
            type="date" format="YYYY/MM/DD" value-format="YYYY/MM/DD"
            v-model="form[field.name]" :disabled="!userStore.hasAnyRoles(field.roles)"
            :placeholder="`选择${field.description}日期`"
          />
        </template>
        <template v-else-if="field.type==='datetime'">
          <el-date-picker
            type="datetime" format="YYYY/MM/DD hh:mm:ss" value-format="YYYY/MM/DD hh:mm:ss"
            v-model="form[field.name]" :disabled="!userStore.hasAnyRoles(field.roles)"
            :placeholder="`选择${field.description}时间`"
          />
        </template>
        <template v-else-if="field.type==='number'">
          <el-input :type="field.type" v-model.number="form[field.name]"
                    :disabled="!userStore.hasAnyRoles(field.roles)" />
        </template>
        <template v-else>
          <el-input :type="field.type" v-model.trim="form[field.name]"
                    :disabled="!userStore.hasAnyRoles(field.roles)" />
        </template>
      </el-form-item>
    </template>
    <el-form-item>
      <el-button type="primary" @click="onSubmit()">保存</el-button>
      <el-button @click="emits('cancel')">取消</el-button>
    </el-form-item>
  </el-form>
</template>

<style scoped>
</style>
