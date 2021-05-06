import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/home',
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('../views/Home.vue')
  },
  {
    path: '/movies',
    name: 'Movies',
    component: () => import('../views/Movies.vue')
  },
  {
    path: '/cinema',
    name: 'Cinema',
    component: () => import('../views/Cinema.vue')
  },
  {
    path: '/profile',
    name: 'Profile',
    component: () => import('../views/Account.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/booking/:id',
    name: 'Booking',
    component: () => import('../views/Booking.vue')
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('../views/Admin.vue')
  },
]

const router = new VueRouter({
  routes
})

export default router
