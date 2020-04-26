import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Notebooks from '@/components/notebooks/Notebooks'
import Login from '@/components/Login'
import SignUp from '@/components/SignUp'
import Admin from '@/components/Admin'
import NotFound from '@/components/NotFound'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
      {
        path: '/',
        name: 'Home',
        component: Home
      },
      {
        path: '/notebooks',
        name: 'Notebooks',
        component: Notebooks
      },
      {
        path: '/login',
        name: 'Login',
        component: Login
      },
      {
        path: '/signup',
        name: 'SignUp',
        component: SignUp
      },
      {
        path: '/admin',
        name: 'Admin',
        component: Admin
      },
      { 
        path: '/404', 
        name: '404', 
        component: NotFound, 
      }, { 
        path: '*', 
        redirect: '/404' 
      }
    ]
})