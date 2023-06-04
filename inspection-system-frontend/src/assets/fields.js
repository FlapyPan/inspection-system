export const readOnlyRole = ['readonly']
export const roles = ['staff', 'auditor', 'admin']
export const rolesValue = ['职工', '审核员', '系统管理员']
export const approvalStatusValue = ['未审核', '通过', '未通过']

export const attendanceFields = [
  {name: 'id', description: '考勤ID', type: 'number', roles: readOnlyRole},
  {name: 'checkinTime', description: '打卡时间', type: 'datetime', roles: [...roles]},
  {name: 'createTime', sortable: true, description: '日期', type: 'datetime', roles: [...roles]},
  {name: 'isAskForLeave', description: '是否请假', type: 'boolean', roles: [...roles]},
  {name: 'askForLeaveReason', description: '请假原因', type: 'text', roles: [...roles]},
  {name: 'other', description: '其他备注', type: 'text', roles: [...roles]},
  {name: 'employeeId', description: '职工号', type: 'number', roles: [...roles]},
]

export const bookFields = [
  {name: 'id', description: '书籍ID', type: 'number', roles: readOnlyRole},
  {name: 'name', description: '书籍名称', type: 'text', roles: [...roles]},
  {name: 'type', description: '书籍类别', type: 'text', roles: [...roles]},
  {name: 'approvalStatus', description: '审核状态', type: 'enum', values: ['0', '1', '2'], roles: [...roles]},
  {name: 'score', sortable: true, description: '书籍分数', type: 'number', roles: [roles[1], roles[2]]},
  {name: 'isbn', description: '书籍ISBN号', type: 'text', roles: [...roles]},
  {name: 'intellectualProperty', description: '知识产权', type: 'text', roles: [...roles]},
  {name: 'researchDept', description: '教研室', type: 'text', roles: [...roles]},
  {name: 'firstDiscipline', description: '一级学科', type: 'text', roles: [...roles]},
  {name: 'language', description: '语种', type: 'text', roles: [...roles]},
  {name: 'publisherLevel', description: '出版社级别', type: 'text', roles: [...roles]},
  {name: 'publisherUnit', description: '出版单位', type: 'text', roles: [...roles]},
  {name: 'publishTime', sortable: true, description: '出版时间', type: 'datetime', roles: [...roles]},
  {name: 'disciplineCategory', description: '学科门类', type: 'text', roles: [...roles]},
  {name: 'projectSource', description: '项目来源', type: 'text', roles: [...roles]},
  {name: 'schoolSignature', description: '学校署名', type: 'text', roles: [...roles]},
  {name: 'publishLocation', description: '出版地', type: 'text', roles: [...roles]},
  {name: 'participantsCounts', description: '参编人数', type: 'number', roles: [...roles]},
  {name: 'totalWords', description: '总字数', type: 'number', roles: [...roles]},
  {name: 'firstAuthorName', description: '第一作者名', type: 'text', roles: [...roles]},
  {name: 'firstAuthorEmployeeId', description: '第一作者职工号', type: 'number', roles: [...roles]},
  {name: 'firstAuthorType', description: '第一作者类型', type: 'text', roles: [...roles]},
  {name: 'employeeId', description: '职工号', type: 'number', roles: [...roles]},
]

export const contractFields = [
  {name: 'id', description: '合同ID', type: 'number', roles: readOnlyRole},
  {name: 'name', description: '名称', type: 'text', roles: [...roles]},
  {name: 'intellectualPropertyType', description: '知识产权类型', type: 'text', roles: [...roles]},
  {name: 'approvalStatus', description: '审核状态', type: 'enum', values: ['0', '1', '2'], roles: [roles[2]]},
  {name: 'score', sortable: true, description: '分数', type: 'number', roles: [roles[1], roles[2]]},
  {name: 'undertakingUnit', description: '承接单位', type: 'text', roles: [...roles]},
  {name: 'contractCode', description: '合同编码', type: 'text', roles: [...roles]},
  {name: 'contactPerson', description: '负责人姓名', type: 'text', roles: [...roles]},
  {name: 'personTitle', description: '负责人职称', type: 'text', roles: [...roles]},
  {name: 'personType', description: '负责人类型', type: 'text', roles: [...roles]},
  {name: 'personPhone', description: '负责人电话', type: 'text', roles: [...roles]},
  {name: 'buyerNature', description: '买方性质', type: 'text', roles: [...roles]},
  {name: 'buyerCategory', description: '买方类别', type: 'text', roles: [...roles]},
  {name: 'sellerName', description: '卖方名称', type: 'text', roles: [...roles]},
  {name: 'buyerRegion', description: '买方地区', type: 'text', roles: [...roles]},
  {name: 'planCategory', description: '计划类别', type: 'text', roles: [...roles]},
  {name: 'department', description: '所属部门', type: 'text', roles: [...roles]},
  {name: 'undertakingDepartment', description: '承担部门', type: 'text', roles: [...roles]},
  {name: 'contractSubcategory', description: '合同子类别', type: 'text', roles: [...roles]},
  {name: 'financialNumber', description: '财务编号', type: 'text', roles: [...roles]},
  {name: 'socialObjective', description: '社会目标', type: 'text', roles: [...roles]},
  {name: 'createTime', sortable: true, description: '创建时间', type: 'date', roles: readOnlyRole},
  {name: 'employeeId', description: '职工号', type: 'number', roles: [...roles]},
]

export const evaluationFields = [
  {name: 'id', description: '唯一标识', type: 'number', roles: readOnlyRole},
  {name: 'score', sortable: true, description: '自评分数', type: 'number', roles: [roles[1], roles[2]]},
  {name: 'content', description: '自评内容', type: 'text', roles: [...roles]},
  {name: 'month', description: '自评月份', type: 'number', roles: [...roles]},
  {name: 'employeeId', description: '职工号', type: 'number', roles: readOnlyRole},
  {name: 'year', description: '年份', type: 'number', roles: [...roles]},
]

export const noticeFields = [
  {name: 'id', description: '公告ID', type: 'number', roles: readOnlyRole},
  {name: 'title', description: '标题', type: 'text', roles: [...roles]},
  {name: 'content', description: '内容', type: 'text', roles: [...roles]},
  {name: 'publishDate', sortable: true, description: '发布日期', type: 'date', roles: [...roles]},
  {name: 'publisherId', description: '发布者ID', type: 'number', roles: [...roles]},
]

export const patentFields = [
  {name: 'id', description: '专利id', type: 'number', roles: readOnlyRole},
  {name: 'name', description: '专利名称', type: 'text', roles: [...roles]},
  {name: 'type', description: '专利类型', type: 'text', roles: [...roles]},
  {name: 'approvalStatus', description: '审核状态', type: 'enum', values: ['0', '1', '2'], roles: [...roles]},
  {name: 'applicationNumber', description: '申请号', type: 'text', roles: [...roles]},
  {name: 'patentee', description: '专利权人', type: 'text', roles: [...roles]},
  {name: 'agency', description: '代理机构', type: 'text', roles: [...roles]},
  {name: 'patentScope', description: '专利范围', type: 'text', roles: [...roles]},
  {name: 'schoolSignature', description: '学校署名', type: 'text', roles: [...roles]},
  {name: 'applicationDate', sortable: true, description: '申请日期', type: 'date', roles: [...roles]},
  {name: 'firstInventor', description: '第一发明人', type: 'text', roles: [...roles]},
  {name: 'firstInventorEmployeeId', description: '第一发明人职工id', type: 'number', roles: [...roles]},
  {name: 'firstInventorType', description: '第一发明人类型', type: 'text', roles: [...roles]},
  {name: 'employeeId', description: '职工号', type: 'number', roles: readOnlyRole},
]

export const salaryFields = [
  {name: 'id', description: '薪资id', type: 'number', roles: readOnlyRole},
  {name: 'basicSalary', description: '基础薪资', type: 'number', roles: [...roles]},
  {name: 'kpi', description: 'KPI', type: 'text', roles: [...roles]},
  {name: 'kpiCalculatesSalary', description: 'KPI核算工资', type: 'number', roles: [...roles]},
  {name: 'otherAllowance', description: '其他补贴', type: 'number', roles: [...roles]},
  {name: 'remark', description: '备注', type: 'text', roles: [...roles]},
  {name: 'createTime', sortable: true, description: '创建时间', type: 'date', roles: [...roles]},
  {name: 'employeeId', description: '职工号', type: 'number', roles: [...roles]},
]

export const softwareWorkFields = [
  {name: 'id', description: '唯一标识', type: 'number', roles: readOnlyRole},
  {name: 'name', description: '著作名称', type: 'text', roles: [...roles]},
  {name: 'type', description: '版权类型', type: 'text', roles: [...roles]},
  {name: 'approvalStatus', description: '审核状态', type: 'enum', values: ['0', '1', '2'], roles: [...roles]},
  {name: 'score', sortable: true, description: '评分', type: 'number', roles: [roles[1], roles[2]]},
  {name: 'registrationNumber', description: '登记号', type: 'text', roles: [...roles]},
  {name: 'affiliatedUnit', description: '所属单位', type: 'text', roles: [...roles]},
  {name: 'primaryDiscipline', description: '一级学科', type: 'text', roles: [...roles]},
  {name: 'publicationDate', sortable: true, description: '出版日期', type: 'date', roles: [...roles]},
  {name: 'authorCount', description: '作者人数', type: 'number', roles: [...roles]},
  {name: 'firstAuthorName', description: '第一作者名', type: 'text', roles: [...roles]},
  {name: 'firstAuthorEmployeeId', description: '第一作者职工号', type: 'text', roles: [...roles]},
  {name: 'firstAuthorType', description: '第一作者类型', type: 'text', roles: [...roles]},
  {name: 'employeeId', description: '职工号', type: 'number', roles: [...roles]},
]

export const thesisFields = [
  {name: 'id', description: '唯一标识', type: 'number', roles: readOnlyRole},
  {name: 'topic', description: '题目', type: 'text', roles: [...roles]},
  {name: 'type', description: '类型', type: 'text', roles: [...roles]},
  {name: 'approvalStatus', description: '审核状态', type: 'text', roles: [...roles]},
  {name: 'score', sortable: true, description: '分数', type: 'number', roles: [roles[1], roles[2]]},
  {name: 'department', description: '所属单位', type: 'text', roles: [...roles]},
  {name: 'publishTime', sortable: true, description: '发表时间', type: 'date', roles: [...roles]},
  {name: 'disciplineCategory', description: '学科门类', type: 'text', roles: [...roles]},
  {name: 'firstLevelDiscipline', description: '一级学科', type: 'text', roles: [...roles]},
  {name: 'attachmentAddress', description: '附件地址', type: 'text', roles: [...roles]},
  {name: 'journalType', description: '期刊类型', type: 'text', roles: [...roles]},
  {name: 'participantNumber', description: '参编人数', type: 'number', roles: [...roles]},
  {name: 'totalNumber', description: '总人数', type: 'number', roles: [...roles]},
  {name: 'journalNumber', description: '卷期号', type: 'number', roles: [...roles]},
  {name: 'journalName', description: '期刊名称', type: 'text', roles: [...roles]},
  {name: 'firstAuthor', description: '第一作者', type: 'text', roles: [...roles]},
  {name: 'firstAuthorEmployeeId', description: '第一作者职工号', type: 'number', roles: [...roles]},
  {name: 'firstAuthorType', description: '第一作者类型', type: 'text', roles: [...roles]},
  {name: 'employeeId', description: '职工号', type: 'number', roles: [...roles]},
]

export const userFields = [
  {name: 'id', description: '用户id', type: 'number', roles: readOnlyRole},
  {name: 'name', description: '姓名', type: 'text', roles: [roles[2]]},
  {name: 'roles', description: '角色', type: 'enum', roles: [roles[2]]},
  // {name: 'password', description: '密码', type: 'text', roles: [roles[2]]},
  {name: 'gender', description: '性别', type: 'enum', values: ['男', '女'], roles: [roles[2]]},
  {name: 'idCard', description: '身份证', type: 'text', roles: [roles[2]]},
  {name: 'email', description: '邮箱', type: 'email', roles: [...roles]},
  {name: 'phone', description: '手机号', type: 'number', roles: [...roles]},
  {name: 'birthDate', description: '出生日期', type: 'date', roles: [roles[2]]},
  {name: 'position', description: '职位', type: 'text', roles: [...roles]},
  {name: 'department', description: '部门', type: 'text', roles: [...roles]},
]
