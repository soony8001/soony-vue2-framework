<template>
	<div>
		<template v-if="!$route.meta.allowAnonymous">
			<v-app>
				<!-- <v-app-bar app></v-app-bar> -->
				<toolbar @toggleNavigationBar="showCheck" />
				<navigation :toggle="drawer" />
				<!--<AppHeader></AppHeader> -->
				<v-main>
					<router-view></router-view>
					<page-footer></page-footer>
				</v-main>
			</v-app>
		</template>
		<template v-else>
			<transition>
				<keep-alive>
					<router-view></router-view>
				</keep-alive>
			</transition>
		</template>
		<Snackbar></Snackbar>
	</div>
</template>

<script>
import AppHeader from '@/components/core/Toolbar'
import AppFooter from '@/components/core/Footer'
import Snackbar from '@/components/common/Snackbar'

export default {
	name: 'App',
	components: {
		//AppHeader,
		Snackbar,
		//AppFooter,
	},
	data: () => ({
		drawer: true,
		//drawer:this.$session.get('toggleNavigationBar'),
		icons: ['mdi-facebook', 'mdi-twitter', 'mdi-linkedin', 'mdi-instagram'],
	}),
	beforeCreate() {
		var userId = this.$session.get('userId')
		if (userId == null || userId == 'undefined') {
			const vm = this
			vm.$router.push({ name: 'Login' })
		}
	},
	methods: {
		showCheck() {
			var naviSessionData = this.$session.get('toggleNavigationBar')
			if (naviSessionData) {
				naviSessionData = false
			} else {
				naviSessionData = true
			}
			this.drawer = naviSessionData
		},
	},
}
</script>
<style>
.v-btn:hover:before {
	color: transparent !important;
}
</style>
