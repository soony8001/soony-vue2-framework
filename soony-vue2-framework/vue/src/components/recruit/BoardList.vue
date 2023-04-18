<template>
	<v-container>
		<v-card elevation="10" outlined width="100%" class="mx-auto">
			<v-card-title>
				<div class="d-block d-sm-none d-md-none d-lg-none">Recruit <br /></div>
				<div class="d-none d-sm-block d-md-none">Recruit d-sm-block <br /></div>
				<div class="d-none d-md-block d-lg-none">Recruit d-md-block <br /></div>
				<div class="d-none d-lg-block">Recruit d-lg-block <br /></div>
			</v-card-title>
			<v-card-text>
				<v-row>
					<v-col align-self="end" cols="12" md="2">
						<v-select
							v-model="schType"
							label="검색조건"
							:items="conditions"
						></v-select>
					</v-col>
					<v-col cols="12" md="8">
						<v-text-field
							v-model="schVal"
							label="검색어"
							single-line
							@keypress.enter.prevent="getBoardList"
						></v-text-field>
					</v-col>
					<v-col align-self="center">
						<b-button variant="primary">Button</b-button>
						<Button
							@click="getBoardList"
							color="blue-grey darken-1"
							rounded
							small
							block
							iconName="mdi-magnify"
							btnName="Search"
						></Button>
					</v-col>
				</v-row>
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
			</v-card-text>
		</v-card>
		<v-fab-transition>
			<Button
				@click="movePage('/recruit/write')"
				color="blue-grey darken-1"
				fab
				right
				bottom
				fixed
				iconName="mdi-pencil"
			></Button>
		</v-fab-transition>
	</v-container>
</template>

<script>
import { getRecruitListAPI } from '@/api/index'

export default {
	data() {
		return {
			//  d-none d-sm-table-cell d-md-table-cell d-lg-table-cell로 화면 킈기에 따라 볼 수 있음 https://getbootstrap.kr/docs/5.0/utilities/display/
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
		}
	},
	mounted() {
		this.getBoardList()
		// 모바일 유무
		//alert("this.isMobile() = " + this.isMobile())
	},
	watch: {
		options: {
			handler() {
				this.getBoardList()
			},
			deep: true,
		},
	},
	methods: {
		getBoardDataFromAPI(page, itemsPerPage, sort) {
			return getRecruitListAPI({
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
					// eslint-disable-next-line
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
		onClickRow(event, data) {
			this.movePage('/recruit/detail?docNo=' + data.item.docNo)
		},
		isMobile() {
			return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(
				navigator.userAgent,
			)
		},
	},
}
</script>
