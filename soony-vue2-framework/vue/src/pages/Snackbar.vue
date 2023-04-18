<template>
	<v-container fluid grid-list-xl>
		<div id="app">
			<v-app id="inspire">
				<v-container fluid>
					<v-checkbox
						v-model="checkbox1"
						style="border: 1px solid black"
						color="red"
						:label="`Checkbox 1: ${checkbox1.toString()}`"
					></v-checkbox>
					<v-checkbox
						:input-value="checkbox1"
						:label="`Checkbox 1 (sync): ${checkbox1.toString()}`"
					></v-checkbox>
					<input type="checkbox" v-model="tickBoxChecked" />
					<input
						class="form-check-input"
						style="width: 30px; height: 30px"
						type="checkbox"
						value=""
						id="flexCheckDefault"
					/>
					<label
						class="form-check-label"
						style="font-size: 20pt"
						for="flexCheckDefault"
					>
						&nbsp;&nbsp;Default checkbox&nbsp;
					</label>
					<input
						class="form-check-input"
						style="width: 30px; height: 30px"
						type="checkbox"
						value=""
						id="flexCheckChecked"
						checked
					/>
					<label
						class="form-check-label"
						style="font-size: 20pt"
						for="flexCheckChecked"
					>
						&nbsp;&nbsp;Checked checkbox </label
					><br /><br />
					<input
						class="form-check-input"
						style="width: 30px; height: 30px"
						type="radio"
						name="flexRadioDefault"
						id="flexRadioDefault1"
					/>
					<label
						class="form-check-label"
						style="font-size: 20pt"
						for="flexRadioDefault1"
					>
						Default radio </label
					><br />
					<input
						class="form-check-input"
						type="radio"
						style="width: 30px; height: 30px"
						name="flexRadioDefault"
						id="flexRadioDefault2"
						checked
					/>
					<label
						class="form-check-label"
						style="font-size: 20pt"
						for="flexRadioDefault2"
					>
						Default checked radio
					</label>
				</v-container>
			</v-app>
		</div>
	</v-container>
</template>

<script>
export default {
	data() {
		return {
			showSnackbar: false,
			locationVertical: 'top',
			locationHorizontal: null,
			mode: '',
			timeout: 2000,
			content: "I'm cool snackbar..",
			color: 'undefined',
			checkbox1: true,
			ex4: [
				'red',
				'indigo',
				'orange',
				'primary',
				'secondary',
				'success',
				'info',
				'warning',
				'error',
				'red darken-3',
				'indigo darken-3',
				'orange darken-3',
			],
		}
	},
}
</script>

<style></style>
