<template>
	<div>
		<v-app-bar :color="$root.themeColor" dark app dense>
			<v-app-bar-nav-icon
				@click.stop="toggleNavigationBar"
			></v-app-bar-nav-icon>

			<v-text-field
				hide-details
				dense
				solo-inverted
				append-icon="mdi-magnify"
				label="Search"
			>
			</v-text-field>

			<v-spacer></v-spacer>
			<v-dialog v-model="dialog" width="500">
				<template v-slot:activator="{ on, attrs }">
					<v-btn icon large v-bind="attrs" v-on="on">
						<v-icon medium>rate_review</v-icon>
					</v-btn>
				</template>

				<v-card>
					<v-card-title class="text-h5 grey lighten-2">
						Privacy Policy
					</v-card-title>

					<v-card-text>
						Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
						eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
						ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
						aliquip ex ea commodo consequat. Duis aute irure dolor in
						reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
						pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
						culpa qui officia deserunt mollit anim id est laborum.
					</v-card-text>

					<v-divider></v-divider>

					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn color="primary" text @click="dialog = false">
							I accept
						</v-btn>
					</v-card-actions>
				</v-card>
			</v-dialog>

			<v-btn icon large href="https://github.com/fatihunlu" :ripple="false">
				<v-icon medium>mdi-github</v-icon>
			</v-btn>
			<v-menu
				offset-y
				class="toolbar-menu-item"
				origin="center center"
				:nudge-bottom="10"
				transition="scale-transition"
			>
				<template v-slot:activator="{ on, attrs }">
					<v-btn icon large flat v-bind="attrs" v-on="on" @click="messages = 0">
						<v-badge
							color="green"
							:value="messages"
							:content="messages"
							overlap
						>
							<span
								slot="badge"
								v-if="notifications.filter(x => x.isActive).length > 0"
							>
								{{ notifications.filter(x => x.isActive).length }}
							</span>
							<v-icon>notifications</v-icon>
						</v-badge>
					</v-btn>
				</template>
				<v-card>
					<v-list>
						<v-list-item>
							<v-list-item-avatar>
								<img
									src="https://cdn.vuetifyjs.com/images/john.jpg"
									alt="John"
								/>
							</v-list-item-avatar>

							<v-list-item-content>
								<v-list-item-title>John Leider</v-list-item-title>
								<v-list-item-subtitle
									>You have {{ notifications.length }} new
									notification(s)</v-list-item-subtitle
								>
							</v-list-item-content>

							<v-list-item-action>
								<v-btn :class="fav ? 'red--text' : ''" icon @click="fav = !fav">
									<v-icon>mdi-heart</v-icon>
								</v-btn>
							</v-list-item-action>
						</v-list-item>
					</v-list>

					<v-divider></v-divider>

					<v-list>
						<v-list-item v-for="(item, index) in notifications" :key="index">
							<v-list-item-icon>
								<v-icon v-text="item.icon"></v-icon>
							</v-list-item-icon>
							<v-list-tile-content>
								<v-list-tile-sub-title
									v-html="item.title"
									@click="item.onClick"
								></v-list-tile-sub-title>
							</v-list-tile-content>
							<v-spacer></v-spacer>
							<v-list-tile-action class="caption">
								{{ item.actionAt }}
							</v-list-tile-action>
						</v-list-item>
					</v-list>

					<v-card-actions>
						<v-spacer></v-spacer>

						<v-btn text @click="menu = false">Cancel</v-btn>
						<v-btn color="primary" text @click="menu = false">Save</v-btn>
					</v-card-actions>
				</v-card>
			</v-menu>

			<v-menu
				offset-y
				class="toolbar-menu-item"
				origin="center center"
				:nudge-bottom="10"
				transition="scale-transition"
			>
				<v-card>
					<v-list>
						<v-list-item>
							<v-list-item-avatar>
								<img
									src="https://cdn.vuetifyjs.com/images/john.jpg"
									alt="John"
								/>
							</v-list-item-avatar>

							<v-list-item-content>
								<v-list-item-title>John Leider</v-list-item-title>
								<v-list-item-subtitle
									>You have {{ notifications.length }} new
									notification(s)</v-list-item-subtitle
								>
							</v-list-item-content>

							<v-list-item-action>
								<v-btn :class="fav ? 'red--text' : ''" icon @click="fav = !fav">
									<v-icon>mdi-heart</v-icon>
								</v-btn>
							</v-list-item-action>
						</v-list-item>
					</v-list>

					<v-divider></v-divider>

					<v-list>
						<v-list-item v-for="(item, index) in notifications" :key="index">
							<v-list-item-icon>
								<v-icon v-text="item.icon"></v-icon>
							</v-list-item-icon>
							<v-list-tile-content>
								<v-list-tile-sub-title
									v-html="item.title"
									@click="item.onClick"
								></v-list-tile-sub-title>
							</v-list-tile-content>
							<v-spacer></v-spacer>
							<v-list-tile-action class="caption">
								{{ item.actionAt }}
							</v-list-tile-action>
						</v-list-item>
					</v-list>

					<v-card-actions>
						<v-spacer></v-spacer>

						<v-btn text @click="menu = false">Cancel</v-btn>
						<v-btn color="primary" text @click="menu = false">Save</v-btn>
					</v-card-actions>
				</v-card>
			</v-menu>
			<v-menu
				offset-y
				class="toolbar-menu-item"
				origin="center center"
				:nudge-bottom="10"
			>
				<template v-slot:activator="{ on, attrs }">
					<v-btn icon large flat v-bind="attrs" v-on="on">
						<v-avatar size="30px">
							<img
								src="https://avataaars.io/?avatarStyle=Circle&topType=ShortHairShortFlat&accessoriesType=Sunglasses&hairColor=Black&facialHairType=Blank&clotheType=CollarSweater&clotheColor=Black&eyeType=Default&eyebrowType=Default&mouthType=Smile&skinColor=Light"
							/>
						</v-avatar>
					</v-btn>
				</template>
				<v-card>
					<v-list>
						<v-list-item
							v-for="(item, index) in items"
							:key="index"
							@click="item.click"
						>
							<v-list-item-icon>
								<v-icon v-text="item.icon"></v-icon>
							</v-list-item-icon>
							<v-list-tile-content>
								<v-list-tile-title>{{ item.title }}</v-list-tile-title>
							</v-list-tile-content>
						</v-list-item>
					</v-list>
				</v-card>
			</v-menu>
		</v-app-bar>
	</div>
</template>
<script>
export default {
	methods: {
		toggleNavigationBar() {
			const vm = this
			var toggleNavigationBar = this.$session.get('toggleNavigationBar')
			if (toggleNavigationBar != null && toggleNavigationBar == true) {
				this.$session.set('toggleNavigationBar', false)
			} else {
				this.$session.set('toggleNavigationBar', true)
			}

			vm.$emit('toggleNavigationBar')
		},
		setUpSettings() {
			const vm = this

			if (
				vm.userEmail === null ||
				vm.password === null ||
				vm.passwordConfirm === null
			) {
				vm.result = "Email and Password can't be null."
				vm.showResult = true

				return
			}

			if (vm.password !== vm.passwordConfirm) {
				vm.error = true
				vm.result = 'Passwords does not match the confirm password.'
				vm.showResult = true

				return
			}

			vm.$root.userEmail = vm.userEmail
			vm.$root.userPassword = vm.password

			vm.result = 'Email and password changed succesfully.'
			vm.showResult = true

			vm.dialogSettings = false
		},
	},
	data() {
		return {
			dialog: false,
			items: [
				{
					icon: 'account_circle',
					href: '#',
					title: 'Profile',
					//click: e => {},
				},
				{
					icon: 'settings',
					href: '#',
					title: 'Settings',
					click: () => {
						const vm = this

						vm.dialogSettings = true
					},
				},
				{
					icon: 'exit_to_app',
					href: '#',
					title: 'Log Out',
					click: () => {
						const vm = this
						vm.$session.remove('userId')
						vm.$router.push({ name: 'Login' })
					},
				},
			],
			notifications: [
				{
					title: 'New mail from Adam Joe',
					color: 'light-blue',
					icon: 'email',
					actionAt: '12 min ago',
					isActive: true,
					onClick: () => {
						const vm = this

						vm.$router.push({ name: 'Mailbox' })
					},
				},
				{
					title: 'Scheculed meeting',
					color: 'red',
					icon: 'calendar_today',
					actionAt: '46 min ago',
					isActive: true,
					onClick: () => {
						const vm = this

						vm.$router.push({ name: 'Calendar' })
					},
				},
				{
					title: 'New mail from Github',
					color: 'light-blue',
					icon: 'email',
					isActive: true,
					timeLabel: '2 hour ago',
					onClick: () => {
						const vm = this

						vm.$router.push({ name: 'Mailbox' })
					},
				},
			],
			fav: true,
			menu: false,
			message: false,
			hints: true,
			messages: 8,
		}
	},
}
</script>
