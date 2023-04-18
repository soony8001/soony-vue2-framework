<template>
	<v-card class="mx-auto">
		<v-container>
			<v-row dense>
				<v-col v-for="(item, i) in items25" :key="i">
					<v-card :color="item.color" dark height="150px">
						<!-- 코멘트 -->
						<div class="d-flex flex-no-wrap justify-space-between">
							<div>
								<v-card-title
									class="text-h5"
									v-text="item.title"
								></v-card-title>

								<v-card-subtitle v-text="item.artist"></v-card-subtitle>

								<v-card-actions>
									<v-btn
										v-if="item.artist === 'Ellie Goulding'"
										class="ml-2 mt-3"
										fab
										icon
										height="40px"
										right
										width="40px"
									>
										<v-icon>mdi-play</v-icon>
									</v-btn>

									<v-btn v-else class="ml-2 mt-5" outlined rounded small>
										START RADIO
									</v-btn>
								</v-card-actions>
							</div>

							<v-avatar class="ma-3" size="100" tile>
								<v-img :src="item.src"></v-img>
							</v-avatar>
						</div>
					</v-card>
				</v-col>
			</v-row>

			<!-- Columns start at 50% wide on mobile and bump up to 33.3% wide on desktop -->
			<v-row dense>
				<v-col cols="12" md="4">
					<v-card class="pa-2" styloutlined tile>
						<div id="container" style="height: 300px"></div>
					</v-card>
				</v-col>
				<v-col cols="12" md="4">
					<v-card class="pa-2" styloutlined tile>
						<div id="container2" style="height: 300px"></div>
					</v-card>
				</v-col>
				<v-col cols="12" md="4">
					<v-card class="pa-2" styloutlined tile>
						<div id="container3" style="height: 300px"></div>
					</v-card>
				</v-col>
			</v-row>

			<v-row dense>
				<v-col cols="12" md="8">
					<v-card class="pa-2" outlined tile>
						<v-tabs v-model="tabs" fixed-tabs>
							<v-tabs-slider></v-tabs-slider>
							<v-tab href="#mobile-tabs-5-1" class="primary--text">
								<v-icon>mdi-phone</v-icon>
							</v-tab>

							<v-tab href="#mobile-tabs-5-2" class="primary--text">
								<v-icon>mdi-heart</v-icon>
							</v-tab>

							<v-tab href="#mobile-tabs-5-3" class="primary--text">
								<v-icon>mdi-account-box</v-icon>
							</v-tab>
						</v-tabs>
						<v-tabs-items v-model="tabs">
							<v-tab-item :value="'mobile-tabs-5-1'">
								<v-card flat>
									<v-card class="pa-2" outlined tile>
										<v-row>
											<v-col>
												<v-data-table
													class="elevation-1"
													@click:row="onClickRow"
													:headers="headers"
													:items="document"
													:options.sync="options"
													:server-items-length="totalCount"
													:footer-props="footerOptions"
													:loading="loading"
													:mobile-breakpoint="0"
												>
													<template slot="items" slot-scope="props">
														<td>{{ props.item.docNo }}</td>
														<td>{{ props.item.title }}</td>
														<td>{{ props.item.writer }}</td>
														<td>{{ props.item.regDttm }}</td>
														<td>{{ props.item.view }}</td>
														<td>{{ props.item.reply }}</td>
													</template>
												</v-data-table>
											</v-col>
										</v-row>
									</v-card>
								</v-card>
							</v-tab-item>
							<v-tab-item :value="'mobile-tabs-5-2'">
								<v-card flat>
									<v-card class="pa-2" outlined tile>
										<v-row>
											<v-col>
												<v-data-table
													class="elevation-1"
													@click:row="onClickRow"
													:headers="headers"
													:items="document2"
													:options.sync="options"
													:server-items-length="totalCount2"
													:footer-props="footerOptions"
													:loading="loading"
													:mobile-breakpoint="0"
												>
													<template slot="items" slot-scope="props">
														<td>{{ props.item.docNo }}</td>
														<td>{{ props.item.title }}</td>
														<td>{{ props.item.writer }}</td>
														<td>{{ props.item.regDttm }}</td>
														<td>{{ props.item.view }}</td>
														<td>{{ props.item.reply }}</td>
													</template>
												</v-data-table>
											</v-col>
										</v-row>
									</v-card>
								</v-card>
							</v-tab-item>
							<v-tab-item :value="'mobile-tabs-5-3'">
								<v-card flat>
									<v-card-title class="text-h5">
										An even better title
									</v-card-title>
									<v-card-text>
										<p>
											Maecenas ullamcorper, dui et placerat feugiat, eros pede
											varius nisi, condimentum viverra felis nunc et lorem. Sed
											hendrerit. Maecenas malesuada. Vestibulum ullamcorper
											mauris at ligula. Proin faucibus arcu quis ante.
										</p>

										<p class="mb-0">
											Etiam vitae tortor. Curabitur ullamcorper ultricies nisi.
											Sed magna purus, fermentum eu, tincidunt eu, varius ut,
											felis. Aliquam lobortis. Suspendisse potenti.
										</p>
									</v-card-text>
								</v-card>
							</v-tab-item>
						</v-tabs-items>
					</v-card>
				</v-col>
				<v-col cols="12" md="4">
					<v-card class="pa-2" outlined tile>
						<v-card :loading="loading" class="mx-auto" max-width="374">
							<v-img
								height="150"
								src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
							></v-img>

							<v-card-title>Cafe Badilico</v-card-title>

							<v-card-text>
								<div class="my-4 text-subtitle-1">$ • Italian, Cafe</div>

								<div>
									Small plates, salads & sandwiches - an intimate setting with
									12 indoor seats plus patio seating.
								</div>
							</v-card-text>

							<v-divider class="mx-4"></v-divider>

							<v-card-title>Tonight's availability</v-card-title>

							<v-card-text>
								<v-chip-group
									v-model="selection"
									active-class="deep-purple accent-4 white--text"
									column
								>
									<v-chip>5:30PM</v-chip>

									<v-chip>7:30PM</v-chip>

									<v-chip>8:00PM</v-chip>

									<v-chip>9:00PM</v-chip>
								</v-chip-group>
							</v-card-text>

							<v-card-actions>
								<v-btn color="deep-purple lighten-2" text @click="reserve">
									Reserve
								</v-btn>
							</v-card-actions>
						</v-card>
					</v-card>
				</v-col>
			</v-row>

			<v-row dense>
				<v-col cols="12" md="8">
					<v-card class="pa-2" outlined tile>
						.col-12 .col-md-8
						<p />
						MD는 전체화면이라 생각하면 되고 COL은 MD 없을 때 역활 COLS가
						12기준인데 넘어도 MD가 12이므로 전체 화면에서 흐뜨러지지 않음 COLS는
						전체화면이 아니라 화면 작아졌을 때 유지됨<br />
						COLS 없애면 모바일 버전에도 전체 폭 채워짐 없으면 유지됨. 밑에 거
						보면 알 수 있음
					</v-card>
				</v-col>
				<v-col cols="6" md="4">
					<v-card class="pa-2" outlined tile> .col-6 .col-md-4 AAAAAAA </v-card>
				</v-col>
			</v-row>

			<!-- Columns start at 50% wide on mobile and bump up to 33.3% wide on desktop -->
			<v-row dense>
				<v-col v-for="n in 3" :key="n" md="4">
					<v-card class="pa-2" outlined tile>
						.col-6 .col-md-4
						BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBFFFFFFFFFFFFFFFFFFFFFFFFFFFFfF
					</v-card>
				</v-col>
			</v-row>

			<!-- Columns are always 50% wide, on mobile and desktop -->
			<v-row dense>
				<v-col v-for="n in 2" :key="n" cols="6">
					<v-card class="pa-2" outlined tile>
						.col-6
						VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
					</v-card>
				</v-col>
			</v-row>
		</v-container>
	</v-card>
</template>
<script>
import * as echarts from 'echarts'
import { getBoardListMainAPI, getRecruitListMainAPI } from '@/api/index'

export default {
	data: () => ({
		tabs: null,
		text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
		option: [],
		option2: [],
		items25: [
			{
				color: '#1F7087',
				src: 'https://cdn.vuetifyjs.com/images/cards/foster.jpg',
				title: 'SuperModel',
				artist: 'Foster the People',
			},
			{
				color: '#952175',
				src: 'https://cdn.vuetifyjs.com/images/cards/halcyon.png',
				title: 'Halcyon',
				artist: 'Ellie Goulding',
			},
			{
				color: '#1F7087',
				src: 'https://cdn.vuetifyjs.com/images/cards/foster.jpg',
				title: 'SuperModel',
				artist: 'Foster the People',
			},
			{
				color: '#952175',
				src: 'https://cdn.vuetifyjs.com/images/cards/halcyon.png',
				title: 'Halcyon',
				artist: 'Ellie Goulding',
			},
		],

		headers: [
			{ text: '글 번호', align: 'center', value: 'docNo' },
			{ text: '제목', align: 'start', value: 'title' },
			{ text: '작성자', align: ' d-none d-lg-table-cell', value: 'writer' },
			{
				text: '작성일시',
				align: ' d-none d-lg-table-cell',
				value: 'regDttm',
			},
			{
				text: '조회수',
				align: ' d-none d-sm-table-cell d-md-table-cell d-lg-table-cell',
				value: 'view',
			},
			{
				text: '댓글수',
				align: ' d-none d-md-table-cell d-lg-table-cell',
				value: 'reply',
			},
		],
		document: [],
		document2: [],
		options: {
			multiSort: true,
			sortBy: [],
			sortDesc: [],
			page: 1,
			itemsPerPage: 5,
		},
		footerOptions: {
			'items-per-page-options': [5, 10, 25, 50, 100],
			showFirstLastPage: true,
			firstIcon: 'mdi-arrow-collapse-left',
			lastIcon: 'mdi-arrow-collapse-right',
			prevIcon: 'mdi-minus',
			nextIcon: 'mdi-plus',
		},
		totalCount: 0,
		loading: false,
		conditions: [
			{ text: '글 번호', value: 'docNo' },
			{ text: '제목', value: 'title' },
			{ text: '작성자', value: 'writer' },
		],
		schType: '',
		schVal: '',
	}),
	methods: {
		getBoardDataFromAPI(page, itemsPerPage, sort) {
			return getBoardListMainAPI({
				params: {
					schType: this.schType,
					schVal: this.schVal,
					page: page,
					rows: itemsPerPage,
					sort: encodeURIComponent(sort),
				},
			})
				.then(response => {
					return response.data
				})
				.catch(error => {
					console.log(error)
				})
		},
		getRecruitDataFromAPI(page, itemsPerPage, sort) {
			return getRecruitListMainAPI({
				params: {
					schType: this.schType,
					schVal: this.schVal,
					page: page,
					rows: itemsPerPage,
					sort: encodeURIComponent(sort),
				},
			})
				.then(response => {
					return response.data
				})
				.catch(error => {
					console.log(error)
				})
		},
		getBoardList() {
			const vm = this
			this.loading = true
			// eslint-disable-next-line
			return new Promise((resolve, reject) => {
				const { sortBy, sortDesc, page, itemsPerPage } = this.options
				let sort = []

				if (sortBy.length > 0) {
					// eslint-disable-next-lines
					sortBy.forEach((value, index) => {
						sort.push(
							value
								.replace(/[A-Z]/g, function (str) {
									return '_' + str[0]
								})
								.toUpperCase() +
								' ' +
								(sortDesc[index] ? 'desc' : 'asc'),
						)
					})
				} else {
					sort.push('DOC_NO desc')
				}

				let items = this.getBoardDataFromAPI(page, itemsPerPage, sort).then(
					response => {
						items = response.data
						const total = response.total

						setTimeout(() => {
							vm.loading = false
							this.document = items
							this.totalCount = total
						}, 1000)
					},
				)
			})
		},
		getRecruitList() {
			const vm = this
			this.loading = true
			// eslint-disable-next-line
			return new Promise((resolve, reject) => {
				const { sortBy, sortDesc, page, itemsPerPage } = this.options
				let sort = []

				if (sortBy.length > 0) {
					// eslint-disable-next-lines
					sortBy.forEach((value, index) => {
						sort.push(
							value
								.replace(/[A-Z]/g, function (str) {
									return '_' + str[0]
								})
								.toUpperCase() +
								' ' +
								(sortDesc[index] ? 'desc' : 'asc'),
						)
					})
				} else {
					sort.push('DOC_NO desc')
				}

				let items2 = this.getRecruitDataFromAPI(page, itemsPerPage, sort).then(
					response => {
						items2 = response.data
						const total2 = response.total
						setTimeout(() => {
							vm.loading = false
							this.document2 = items2
							this.totalCount = total2
						}, 1000)
					},
				)
			})
		},
		onClickRow(event, data) {
			this.movePage('/detail?docNo=' + data.item.docNo)
		},
	},
	watch: {
		options: {
			handler() {
				this.getBoardList()
			},
			deep: true,
		},
	},
	/**
	beforeCreate() {
		var userId = this.$session.get('userId') 
		if (userId == null || userId == 'undefined') {
			const vm = this
			vm.$router.push({ name: 'Login' })
		}
	},
	**/
	mounted() {
		this.getBoardList()
		this.getRecruitList()
		/**  Chart 1번 **/
		var chartDom = document.getElementById('container')
		var myChart = echarts.init(chartDom)

		if (myChart != null && myChart != '' && myChart != undefined) {
			myChart.dispose() //차트돔이 먼저 생성된 경우 기존 돔을 삭제해준다
		}

		myChart = echarts.init(chartDom)

		var option

		option = {
			title: {
				text: 'Stacked Line',
			},
			xAxis: {
				type: 'category',
				data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
				name: '요일',
				nameLocation: 'middle',
				nameTextStyle: {
					fontSize: 12,
					align: 'bottom',
					verticalAlign: 'top',
					padding: [15, 0, 0, 0],
				},
			},
			yAxis: {
				type: 'value',
				name: '넘버값',
				nameLocation: 'end',
				nameTextStyle: {
					fontSize: 12,
					padding: [0, 0, 0, 0],
				},
			},
			series: [
				{
					data: [150, 230, 224, 218, 135, 147, 260],
					type: 'line',
				},
			],
		}

		option && myChart.setOption(option)

		/**  Chart 2번 **/
		var chartDom2 = document.getElementById('container2')
		var myChart2 = echarts.init(chartDom2)

		if (myChart2 != null && myChart2 != '' && myChart2 != undefined) {
			myChart2.dispose() //차트돔이 먼저 생성된 경우 기존 돔을 삭제해준다
		}

		myChart2 = echarts.init(chartDom2)

		var option2

		option2 = {
			title: {
				text: 'Referer of a Website',
				subtext: 'Fake Data',
				left: 'right',
			},
			tooltip: {
				trigger: 'item',
			},
			legend: {
				orient: 'vertical',
				left: 'left',
			},
			series: [
				{
					name: 'Access From',
					type: 'pie',
					radius: '70%',
					top: '30%',
					data: [
						{ value: 1048, name: 'Search Engine' },
						{ value: 735, name: 'Direct' },
						{ value: 580, name: 'Email' },
						{ value: 484, name: 'Union Ads' },
						{ value: 300, name: 'Video Ads' },
					],
					emphasis: {
						itemStyle: {
							shadowBlur: 10,
							shadowOffsetX: 0,
							shadowColor: 'rgba(0, 0, 0, 0.5)',
						},
					},
				},
			],
		}

		option2 && myChart2.setOption(option2)

		//윈도우 사이즈가 변경될때마다 resize되도록 설정해준다
		window.onresize = function () {}

		/**  Chart 3번 **/
		var chartDom3 = document.getElementById('container3')
		var myChart3 = echarts.init(chartDom3)

		if (myChart3 != null && myChart3 != '' && myChart3 != undefined) {
			myChart3.dispose() //차트돔이 먼저 생성된 경우 기존 돔을 삭제해준다
		}

		myChart3 = echarts.init(chartDom3)

		var option3

		option3 = {
			title: {
				text: 'sales revenue',
			},
			xAxis: {
				type: 'category',
				data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
				name: '요일',
				nameLocation: 'middle',
				nameTextStyle: {
					fontSize: 12,
					align: 'bottom',
					verticalAlign: 'top',
					padding: [15, 0, 0, 0],
				},
			},
			yAxis: {
				type: 'value',
				name: '넘버값',
			},
			series: [
				{
					data: [120, 200, 150, 80, 70, 110, 130],
					type: 'bar',
				},
			],
		}

		option3 && myChart3.setOption(option3)

		//윈도우 사이즈가 변경될때마다 resize되도록 설정해준다
		window.onresize = function () {
			myChart.resize()
			myChart2.resize()
			myChart3.resize()
		}
	},
}
</script>

<style></style>
