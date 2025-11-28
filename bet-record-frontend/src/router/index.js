import { createRouter, createWebHistory } from 'vue-router'
import Register from '../views/Register.vue'

const routes = [
  {
    path: '/',
    redirect: '/auth/register'
  },
  {
    path: '/auth/register',
    component: Register
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
