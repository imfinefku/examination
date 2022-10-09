import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login.vue'
import home from '@/components/home.vue'
import organizeManage from '@/components/departmentManage.vue'
import authority from '@/components/authority.vue'
import menuManage from '@/components/menuManage.vue'
import authorityManage from '@/components/authorityManage.vue'
import userManage from '@/components/userManage.vue'
import roleManage from '@/components/roleManage.vue'
import managerConsole from '@/components/service/managerConsole.vue'
import examinationManage from '@/components/service/examinationManage.vue'
import examinationManage2 from '@/components/service/examinationManage2.vue'
import subjectManage from '@/components/service/subjectManage.vue'
import questionManage from '@/components/service/questionManage.vue'
import testpaperManage from '@/components/service/testpaperManage.vue'
import testpaperCorrection from '@/components/service/testpaperCorrection.vue'
import myExamination from '@/components/service/myExamination.vue'
import examOnline from '@/components/service/examOnline.vue'
import myScore from '@/components/service/myScore.vue'
import testpaperAdd from '@/components/service/testpaperAdd.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/home',
      name: 'home',
      component: home,
      meta: {
        title: "首页"
      },
      children: [
        {
          path: 'authority',
          component: authority,
          meta: {
            title: "权限管理"
          },
          children: [{
            path: 'menuManage',
            component: menuManage,
            meta: {
              title: "菜单管理"
            }
          },{
            path: 'authorityManage',
            component: authorityManage,
            meta: {
              title: "权限管理"
            }
          },{
            path: 'roleManage',
            component: roleManage,
            meta: {
              title: "角色管理"
            }
          },{
            path: 'userManage',
            component: userManage,
            meta: {
              title: "用户管理"
            }
          },{
            path: 'departmentManage',
            component: organizeManage,
            meta: {
              title: "部门管理"
            }
          }]
        },
        {
          path: 'managerConsole',
          component: managerConsole,
          meta: {
            title: "管理台"
          }
        },{
          path: 'examinationManage',
          component: examinationManage,
          meta: {
            title: "考试管理"
          },
          children: [
            {
              path: 'subjectManage',
              component: subjectManage,
              meta: {
                title: "科目管理"
              }
            },
            {
              path: 'questionManage',
              component: questionManage,
              meta: {
                title: "试题管理"
              }
            },
            {
              path: 'testpaperManage',
              component: testpaperManage,
              meta: {
                title: "试卷管理"
              }
            },{
              path: 'testpaperAdd',
              component: testpaperAdd,
              meta: {
                title: "试卷编制"
              }
            },{
              path: 'examinationManage2',
              component: examinationManage2,
              meta: {
                title: "考试管理"
              }
            },
            {
              path: 'testpaperCorrection',
              component: testpaperCorrection,
              meta: {
                title: "试卷批改"
              }
            }
          ]
        },{
          path: 'myExamination',
          component: myExamination,
          meta: {
            title: "我的考试"
          },
          children: [
            {
              path: 'examOnline',
              component: examOnline,
              meta: {
                title: "在线考试"
              }
            },
            {
              path: 'myScore',
              component: myScore,
              meta: {
                title: "我的成绩"
              }
            }
          ]
        }
      ]
    }
  ]
})
