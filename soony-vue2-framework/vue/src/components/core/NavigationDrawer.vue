<template>
	<v-sheet class="overflow-hidden" style="position: relative">
		<v-navigation-drawer v-model="toggle" fixed app>
			<v-list-item style="background-color: #272727">
				<v-list-item-avatar style="height: 30px">
					<router-link to="/">
						<v-img src="../../static/logo.png" width="36px"></v-img>
					</router-link>
				</v-list-item-avatar>
				<v-list-item-content>
					<v-list-item-title
						><router-link to="/" style="text-decoration: none"
							><font color="white">Vue Admin Template2</font></router-link
						>
					</v-list-item-title>
				</v-list-item-content>
			</v-list-item>

			<v-list>
				<v-list-item>
					<v-list-item-icon>
						<v-icon>dashboard</v-icon>
					</v-list-item-icon>

					<v-list-item-title
						><router-link to="/" style="text-decoration: none; color: black"
							>Dashboard</router-link
						></v-list-item-title
					>
				</v-list-item>
				<v-list-item>
					<v-list-item-icon>
						<v-icon>edit</v-icon>
					</v-list-item-icon>

					<v-list-item-title
						><router-link to="/tag" style="text-decoration: none; color: black"
							>Study</router-link
						></v-list-item-title
					>
				</v-list-item>
				<v-list-item>
					<v-list-item-icon>
						<v-icon>grading</v-icon>
					</v-list-item-icon>

					<v-list-item-title
						><router-link to="/list" style="text-decoration: none; color: black"
							>Board</router-link
						></v-list-item-title
					>
				</v-list-item>
				<v-list-item>
					<v-list-item-icon>
						<v-icon>person</v-icon>
					</v-list-item-icon>

					<v-list-item-title
						><router-link
							to="/recruitList"
							style="text-decoration: none; color: black"
							>Recruit</router-link
						></v-list-item-title
					>
				</v-list-item>
				<v-list-item>
					<v-list-item-icon>
						<v-icon>calendar_today</v-icon>
					</v-list-item-icon>
					<v-list-item-title
						><router-link
							to="/calendar"
							style="text-decoration: none; color: black"
							>Calendar</router-link
						></v-list-item-title
					>
				</v-list-item>
				<v-list-item>
					<v-list-item-icon>
						<v-icon>mail</v-icon>
					</v-list-item-icon>
					<v-list-item-title
						><router-link
							to="/mailbox"
							style="text-decoration: none; color: black"
							>Mailbox</router-link
						></v-list-item-title
					>
				</v-list-item>

				<v-list-group :value="false" prepend-icon="pageview">
					<template v-slot:activator>
						<v-list-item-title>Widgets</v-list-item-title>
					</template>
					<v-list-item v-for="item in widgets" :key="item.title" link>
						<v-list-item-icon>
							<v-icon>{{ item.icon }}</v-icon>
						</v-list-item-icon>
						<v-list-item-content>
							<v-list-item-title
								><router-link
									:to="item.link"
									style="text-decoration: none; color: black"
									>{{ item.title }}</router-link
								></v-list-item-title
							>
						</v-list-item-content>
					</v-list-item>
				</v-list-group>

				<v-list-group :value="false" prepend-icon="select_all">
					<template v-slot:activator>
						<v-list-item-title>Overlays</v-list-item-title>
					</template>
					<v-list-item link>
						<v-list-item-icon>
							<v-icon>event_note</v-icon>
						</v-list-item-icon>
						<v-list-item-content>
							<v-list-item-title
								><router-link
									to="/snackbar"
									style="text-decoration: none; color: black"
									>Snackbar</router-link
								></v-list-item-title
							>
						</v-list-item-content>
					</v-list-item>
				</v-list-group>

				<v-list-group :value="false" prepend-icon="fingerprint">
					<template v-slot:activator>
						<v-list-item-title>Authorization</v-list-item-title>
					</template>
					<v-list-item link>
						<v-list-item-icon>
							<v-icon>cancel</v-icon>
						</v-list-item-icon>
						<v-list-item-content>
							<v-list-item-title
								@click="
									$router.push({ name: 'Error', params: { errorCode: '403' } })
								"
								>403</v-list-item-title
							>
						</v-list-item-content>
					</v-list-item>

					<v-list-item link>
						<v-list-item-icon>
							<v-icon>cancel</v-icon>
						</v-list-item-icon>
						<v-list-item-content>
							<v-list-item-title
								@click="
									$router.push({ name: 'Error', params: { errorCode: '404' } })
								"
								>404</v-list-item-title
							>
						</v-list-item-content>
					</v-list-item>

					<v-list-item link>
						<v-list-item-icon>
							<v-icon>cancel</v-icon>
						</v-list-item-icon>
						<v-list-item-content>
							<v-list-item-title @click="$router.push({ name: 'Login' })"
								>Login</v-list-item-title
							>
						</v-list-item-content>
					</v-list-item>
				</v-list-group>

				<v-list-group :value="false" prepend-icon="mdi-account-circle">
					<template v-slot:activator>
						<v-list-item-title>Users</v-list-item-title>
					</template>

					<v-list-group :value="true" no-action sub-group>
						<template v-slot:activator>
							<v-list-item-content>
								<v-list-item-title>Admin</v-list-item-title>
							</v-list-item-content>
						</template>

						<v-list-item v-for="([title, icon], i) in admins" :key="i" link>
							<v-list-item-title v-text="title"></v-list-item-title>

							<v-list-item-icon>
								<v-icon v-text="icon"></v-icon>
							</v-list-item-icon>
						</v-list-item>
					</v-list-group>

					<v-list-group no-action sub-group>
						<template v-slot:activator>
							<v-list-item-content>
								<v-list-item-title>Actions</v-list-item-title>
							</v-list-item-content>
						</template>

						<v-list-item v-for="([title, icon], i) in cruds" :key="i" link>
							<v-list-item-title v-text="title"></v-list-item-title>

							<v-list-item-icon>
								<v-icon v-text="icon"></v-icon>
							</v-list-item-icon>
						</v-list-item>
					</v-list-group>
				</v-list-group>
			</v-list>
		</v-navigation-drawer>
	</v-sheet>
</template>
<script>
export default {
	props: {
		toggle: {
			type: Boolean,
			required: false,
			default: false,
		},
	},
	data() {
		return {
			drawer: true,
			authors: [
				{ title: '403', icon: 'cancel', link: '/' },
				{ title: '404', icon: 'cancel', link: '/' },
				{ title: '500', icon: 'cancel', link: '/' },
				{ title: 'Calendar', icon: 'calendar_today', link: '/calendar' },
				{ title: 'Calendar', icon: 'calendar_today', link: '/calendar' },
			],
			items: [
				{ title: 'Dashboard', icon: 'dashboard', link: '/' },
				{ title: 'Calendar', icon: 'calendar_today', link: '/calendar' },
				{ title: 'Mailbox', icon: 'mail', link: '/mailbox' },
				{ title: 'Calendar', icon: 'calendar_today', link: '/calendar' },
				{ title: 'Calendar', icon: 'calendar_today', link: '/calendar' },
			],
			widgets: [
				{ title: 'Social', icon: 'group', link: '/social' },
				{ title: 'Charts', icon: 'bar_chart', link: '/chart' },
				{ title: 'Media', icon: 'perm_media', link: '/media' },
			],
			admins: [
				['Management', 'mdi-account-multiple-outline'],
				['Settings', 'mdi-cog-outline'],
			],
			cruds: [
				['Create', 'mdi-plus-outline'],
				['Read', 'mdi-file-outline'],
				['Update', 'mdi-update'],
				['Delete', 'mdi-delete'],
			],
		}
	},
	methods: {
		changeRoute(routeName, selectedIndex) {
			const vm = this

			vm.selectedIndex = selectedIndex

			return vm.$router.push({ name: routeName })
		},
	},
}
</script>

<style>
.toolbar {
	font-weight: bold;
	font-size: 18px;
}

.toolbar .text {
	padding-left: 15px;
	color: white;
	text-decoration: none;
}

.item-title {
	font-size: 17px;
	font-weight: 500;
}
.item-sub-title {
	font-size: 15px;
	font-weight: 500;
}

.active {
	font-weight: bold;
}
</style>
