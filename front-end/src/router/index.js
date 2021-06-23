import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import MainQuiz from '../views/MainQuiz.vue'
import Submit from '../views/Submit.vue'
const history = createWebHistory();

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/round1/:quizCode',
    name: 'MainQuiz',
    component: MainQuiz,
    props: true,
    
  },
  {
    path: '/submit/',
    name: 'Submit',
    component: Submit

  }
]

const router = createRouter({
  history,
  routes
})

export default router