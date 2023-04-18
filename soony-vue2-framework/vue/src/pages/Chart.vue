<template>
	<v-container>
		<v-card class="mx-auto" tile>
			<v-card-text>
				<div id="container" style="height: 400px"></div>
			</v-card-text>
		</v-card>
	</v-container>
</template>

<script>
import * as echarts from 'echarts'

export default {
	name: '',
	data() {
		return {
			option: [],
		}
	},
	mounted() {
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
			},
			yAxis: {
				type: 'value',
			},
			series: [
				{
					data: [150, 230, 224, 218, 135, 147, 260],
					type: 'line',
				},
			],
		}

		option && myChart.setOption(option)

		//윈도우 사이즈가 변경될때마다 resize되도록 설정해준다
		window.onresize = function () {
			myChart.resize()
		}
	},
}
</script>

<style></style>
