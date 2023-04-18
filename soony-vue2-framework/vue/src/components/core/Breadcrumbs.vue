<template>
	<div>
		<v-breadcrumbs :items="items" class="breadcrumbs" divider="/">
		</v-breadcrumbs>
	</div>
</template>

<script>
export default {
	data() {
		return {
			/**
			items: [
		      {
		        text: 'Home',
		        disabled: false,
		        href: 'breadcrumbs_home',
		      },
		      {
		        text: 'Link 1',
		        disabled: false,
		        href: 'breadcrumbs_link_1',
		      },
		      {
		        text: 'Link 2',
		        disabled: true,
		        href: 'breadcrumbs_link_2',
		      },
		    ],
		    **/
			items: [],
		}
	},

	mounted() {
		//alert('AAA')
	},
}
</script>

<style>
.breadcrumbs .link {
	text-decoration: none;
	color: #737373;
	font: bold 24px Sans-Serif;
	letter-spacing: -1px;
	white-space: nowrap;
	display: inline-block;
	position: relative;
}
</style>
