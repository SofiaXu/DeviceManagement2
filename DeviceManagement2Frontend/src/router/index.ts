import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'
import SignIn from '@/views/SignIn.vue'
import UserList from '@/views/UserList.vue'
import Hello from '@/views/Hello.vue'
import DeviceBaseInformationList from '@/views/DeviceBaseInformationList.vue'
import DeviceBatchList from '@/views/DeviceBatchList.vue'
import DeviceMaintainList from '@/views/DeviceMaintainList.vue'
import DeviceList from '@/views/DeviceList.vue'
import DeviceProviderList from '@/views/DeviceProviderList.vue'
import DeviceStatusList from '@/views/DeviceStatusList.vue'
import DeviceTypeList from '@/views/DeviceTypeList.vue'
import EmployeeList from '@/views/EmployeeList.vue'
import LocationList from '@/views/LocationList.vue'
import MaintainerList from '@/views/MaintainerList.vue'
import UselessDeviceList from '@/views/UselessDeviceList.vue'
import UserTypeList from '@/views/UserTypeList.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    meta: {
      requireAuth: true
    },
    children: [{
      path: '/',
      name: 'Hello',
      component: Hello,
      meta: {
        requireAuth: true
      },
    }, {
      path: "/DeviceBaseInformationList",
      name: 'DeviceBaseInformationList',
      component: DeviceBaseInformationList,
      meta: { requireAuth: true },
    }, {
      path: "/DeviceBatchList", name: 'DeviceBatchList',
      component: DeviceBatchList, meta: { requireAuth: true },
    }, { path: "/DeviceMaintainList", name: 'DeviceMaintainList', component: DeviceMaintainList, meta: { requireAuth: true }, }, { path: "/DeviceList", name: 'DeviceList', component: DeviceList, meta: { requireAuth: true }, }, { path: "/DeviceProviderList", name: 'DeviceProviderList', component: DeviceProviderList, meta: { requireAuth: true }, }, { path: "/DeviceStatusList", name: 'DeviceStatusList', component: DeviceStatusList, meta: { requireAuth: true }, }, { path: "/DeviceTypeList", name: 'DeviceTypeList', component: DeviceTypeList, meta: { requireAuth: true }, }, { path: "/EmployeeList", name: 'EmployeeList', component: EmployeeList, meta: { requireAuth: true }, }, { path: "/LocationList", name: 'LocationList', component: LocationList, meta: { requireAuth: true }, }, { path: "/MaintainerList", name: 'MaintainerList', component: MaintainerList, meta: { requireAuth: true }, }, { path: "/UselessDeviceList", name: 'UselessDeviceList', component: UselessDeviceList, meta: { requireAuth: true }, }, { path: "/UserList", name: 'UserList', component: UserList, meta: { requireAuth: true }, }, { path: "/UserTypeList", name: 'UserTypeList', component: UserTypeList, meta: { requireAuth: true }, },
    ]
  },
  {
    path: "/Login",
    name: "SignIn",
    component: SignIn
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
