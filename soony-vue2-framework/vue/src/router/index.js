import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Tag from '../views/Tag.vue'

import Dashboard from '../pages/Dashboard.vue'
import Social from '../pages/Social.vue'
import Media from '../pages/Media.vue'
import Snackbar from '../pages/Snackbar.vue'
import Chart from '../pages/Chart.vue'
import Mailbox from '../pages/Mailbox.vue'
import Calendar from '../pages/Calendar.vue'
import Login from '../pages/core/Login.vue'
import Error from '../pages/core/Error.vue'

Vue.use(VueRouter)

const routes = [
	{
		path: '/',
		name: 'Dashboard',
		component: Dashboard,
		meta: {
			allowAnonymous: false,
		},
	},
	{
		path: '/mailbox',
		name: 'Mailbox',
		component: Mailbox,
	},
	{
		path: '/snackbar',
		name: 'Snackbar',
		component: Snackbar,
	},
	{
		path: '/calendar',
		name: 'Calendar',
		component: Calendar,
	},
	{
		path: '/social',
		name: 'Social',
		component: Social,
	},
	{
		path: '/media',
		name: 'Media',
		component: Media,
	},
	{
		path: '/chart',
		name: 'Chart',
		component: Chart,
	},
	{
		path: '/login',
		name: 'Login',
		component: Login,
		meta: {
			allowAnonymous: true,
		},
	},
	{
		path: '/error',
		name: 'Error',
		component: Error,
		meta: {
			allowAnonymous: true,
		},
	},
	{
		path: '*',
		name: '404',
		component: () => import('@/views/error/404.vue'),
	},
	{
		path: '/home',
		name: 'Home',
		component: Home,
	},
	{
		path: '/tag',
		name: 'Tag',
		component: Tag,
	},
	{
		path: '/list',
		name: 'BoardList',
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () =>
			import(/* webpackChunkName: "about" */ '../views/board/ListView.vue'),
	},
	{
		path: '/write',
		name: 'BoardWrite',
		component: () => import('../views/board/WriteView.vue'),
	},
	{
		path: '/detail',
		name: 'BoardDetail',
		component: () => import('../views/board/DetailView.vue'),
	},
	{
		path: '/edit',
		name: 'BoardEdit',
		component: () => import('../views/board/EditView.vue'),
	},
	// 채용 부분
	{
		path: '/recruitList',
		name: 'RecruitList',
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () =>
			import(/* webpackChunkName: "about" */ '../views/recruit/ListView.vue'),
	},
	{
		path: '/recruit/write',
		name: 'BoardWrite',
		component: () => import('../views/recruit/WriteView.vue'),
	},
	{
		path: '/recruit/detail',
		name: 'BoardDetail',
		component: () => import('../views/recruit/DetailView.vue'),
	},
	{
		path: '/recruit/edit',
		name: 'BoardEdit',
		component: () => import('../views/recruit/EditView.vue'),
	},
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes,
})

export default router
