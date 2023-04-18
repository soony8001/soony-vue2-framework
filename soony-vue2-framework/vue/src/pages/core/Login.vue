<template>

	<v-app id="login" class="secondary">
		<v-content>
			<v-container fluid fill-height>
				<v-layout align-center justify-center>
					<v-flex xs12 sm8 md4 lg4>
						<v-card class="elevation-1 pa-3">
							<v-card-text>
								<div class="layout column align-center">
									<img
										src="../../static/logo.png"
										alt="Vue Material Admin"z
										width="180"
										height="180"
									/>
									<h1 class="flex my-4 primary--text">Vue Admin Template</h1>
								</div>
								<v-form>
									<v-text-field
										append-icon="person"
										name="login"
										label="Login"
										type="text"
										v-model="userEmail"
										:error="error"
									/>
									<v-text-field
										:type="hidePassword ? 'password' : 'text'"
										:append-icon="
											hidePassword ? 'visibility_off' : 'visibility'
										"
										name="password"
										label="Password"
										id="password"
										v-model="password"
										:error="error"
										@click:append="hidePassword = !hidePassword"
									/>
								</v-form>
							</v-card-text>
							<v-card-actions>
								<v-spacer></v-spacer>
								<v-btn block color="primary" @click="login" :loading="loading"
									>Login</v-btn
								>
							</v-card-actions>
							<!-- justify-start -->
							<v-card-actions class="justify-end">
								<v-dialog v-model="dialog" scrollable max-width="600px">
									<template v-slot:activator="{ on, attrs }">
										<v-btn class="ma-2" v-bind="attrs" v-on="on">
											회원등록
										</v-btn>
									</template>
									<v-card>
										<v-system-bar
											style="height: 30pt"
											color="indigo darken-2"
											dark
										>
											<v-spacer></v-spacer>

											<!-- <v-icon>mdi-window-minimize</v-icon> -->

											<!--<v-icon>mdi-window-maximize</v-icon> -->

											<v-icon @click="dialog = false">mdi-close</v-icon>
										</v-system-bar>

										<v-card-title>Select Country</v-card-title>
										<v-divider></v-divider>
										<v-card-text style="height: 600px">
											<v-container>
												<v-row>
													<v-col cols="8" sm="8" md="8">
														<v-text-field
															v-model="userID"
															label="로그인ID"
															ref="userID"
															required
														></v-text-field>
													</v-col>
													<v-col cols="4" sm="4" md="4">
														<v-btn class="ma-2" v-on:click="doubleCheckId"> 중복체크 </v-btn>
													</v-col>
													<v-col cols="12" sm="6" md="6">
														<v-text-field
															:type="hidePassword1 ? 'password' : 'text'"
															:append-icon="
																hidePassword1 ? 'visibility_off' : 'visibility'
															"
															name="password"
															label="패스워드"
															id="passwd"
															v-model="passwd"
															ref="passwd"
															:rules="rules"
															:error="error"
															hint="특수문자 포함, 8자리 이상"
															persistent-hint
															required
															@click:append="hidePassword1 = !hidePassword1"
														/>
													</v-col>
													<v-col cols="8" sm="8" md="8">
														<v-text-field
															v-model="birth"
															ref="birth"
															label="생년월일"
															required
														></v-text-field>
													</v-col>
													<v-col cols="12">
														<v-text-field
															label="Email*"
															v-model="email"
															id="email"
															required
														></v-text-field>
													</v-col>
													<v-col cols="12" sm="12" md="12">
														<v-text-field
															v-model="name"
															label="유저명"
															required
														></v-text-field>
													</v-col>
													<v-col cols="12">
														<input
															class="form-check-input"
															style="width: 15px; height: 15px"
															type="radio"
															id="flexRadioDefault1"
															name="flexRadioDefault"
															v-model="flexRadioDefault"
															value="M"
														/>
														<label
															class="form-check-label"
															style="font-size: 15pt"
															for="flexRadioDefault1"
														>
															&nbsp;&nbsp;남&nbsp;&nbsp;
														</label>
														<input
															class="form-check-input"
															type="radio"
															style="width: 15px; height: 15px"
															id="flexRadioDefault2"
															name="flexRadioDefault"
															v-model="flexRadioDefault"
															value="W"
															checked
														/>
														<label
															class="form-check-label"
															style="font-size: 15pt"
															for="flexRadioDefault2"
														>
															&nbsp;&nbsp;여
														</label>
													</v-col>
													<v-col cols="4" sm="4" md="4">
														<v-select
															:items="['010', '011', '080', '080']"
															label="Phone1*"
															v-model="phone1"
															id="phone1"
															ref="phone1"
															required
														></v-select>
													</v-col>
													<v-col cols="8" sm="8" md="8">
														<v-text-field
															label="Phone2*"
															v-model="phone2"
															id="phone2"
															ref="phone2"
															required
														></v-text-field>
													</v-col>

													<v-col cols="8" sm="8" md="8">
														<v-text-field
															label="우편번호*"
															v-model="postNum"
															id="postNum"
															required
														></v-text-field>
													</v-col>
													<v-col cols="4" sm="4" md="4">
														<v-btn class="ma-2" v-on:click="execDaumPostcode()"> 우편번호 </v-btn>
													</v-col>
													<v-col cols="12">
														<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
															<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" @click="foldDaumPostcode()" alt="접기 버튼">
														</div>
														<v-text-field
															label="주소1*"
															v-model="addr1"
															id="addr1"
															required
														></v-text-field>
													</v-col>
													<v-col cols="12">
														<v-text-field
															label="주소2*"
															v-model="addr2"
															id="addr2"
															required
														></v-text-field>
													</v-col>
													<v-col cols="12">
														<v-autocomplete
															:items="[
																'Skiing',
																'Ice hockey',
																'Soccer',
																'Basketball',
																'Hockey',
																'Reading',
																'Writing',
																'Coding',
																'Basejump',
															]"
															label="Interests"
															v-model="multiItems"
															multiple
														></v-autocomplete>
													</v-col>
												</v-row>
											</v-container>
											<small>*indicates required field</small>
										</v-card-text>
										<v-divider></v-divider>
										<v-card-actions>
											<v-spacer></v-spacer>
											<v-btn color="blue darken-1" text @click="dialog = false">
												Close
											</v-btn>
											<v-btn
												color="blue darken-1"
												text
												@click="registUserInfo()"
											>
												Save
											</v-btn>
										</v-card-actions>
									</v-card>
								</v-dialog>
							</v-card-actions>
						</v-card>
					</v-flex>
				</v-layout>
			</v-container>
			<v-snackbar v-model="showResult" :timeout="2000" top>
				{{ result }}
			</v-snackbar>
		</v-content>
	</v-app>
</template>

<script>

import Viewer from '@/components/common/Viewer'
import Tooltip from '@/components/common/Tooltip'
import btnMixins from '@/mixins/btnMixins'
import axios from 'axios'
export default {
	data() {
		return {
			loading: false,
			userEmail: 'soony1008',
			password: '!aaa111222',
			hidePassword: true,
			hidePassword1: true,
			error: false,
			showResult: false,
			result: '',
			dialog: false,
			rules: [
				value => !!value || 'Required.',
				value => (value && value.length >= 8) || '비밀번호8자이상입력해 주세요',
				value => (value && /\d/.test(value)) || '숫자를 포함해 입력해 주세요',
				value => (value && /[A-Za-z]/.test(value)) || '문자를 포함해 입력해 주세요',
				value => (value && /[^A-Za-z0-9]/.test(value)) || '특수문자를 포함해 입력해 주세요',
			],
			userID: '',
			passwd: '',
			email: '',
			birth: '',
			name: '',
			radioValues: '',
			flexRadioDefault1: '',
			Phone1: '',
			Phone2: '',
			postNum: '',
			addr1: '',
			addr2: '',
			multiItems: [],
			snackbar: false,
			text: 'My timeout is set to 2000.',
			timeout: 2000,
		}
	},
	methods: {
		
		foldDaumPostcode() {
			var element_wrap = document.getElementById('wrap');
	        // iframe을 넣은 element를 안보이게 한다.
	        element_wrap.style.display = 'none';
	    },
		
    execDaumPostcode() {
	 // 우편번호 찾기 찾기 화면을 넣을 element
	    var element_wrap = document.getElementById('wrap');
	
	    //function foldDaumPostcode() {
	    //    // iframe을 넣은 element를 안보이게 한다.
	    //    element_wrap.style.display = 'none';
	    //}
    
	  var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop)
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.addr1 = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.addr1 = data.jibunAddress;
          }
 
          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.addr2 += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.addr2 +=
                this.addr2 !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.addr2 !== "") {
              this.addr2 = `(${this.addr2})`;
            }
          } else {
            this.addr2 = "";
          }
          // 우편번호를 입력한다.
         // this.postcode = data.zonecode
          this.postNum = data.zonecode
          
          
        },
        // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
      }).embed(element_wrap);
      // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
    },
		login() {
			
			const vm = this

			if (!vm.userEmail || !vm.password) {
				vm.result = "Email and Password can't be null."
				vm.showResult = true

				return
			}
			
			/**
			if (vm.userEmail === 'admin@yopmail.com' && vm.password === '123456') {
				vm.$session.set('userId', vm.userEmail)
				vm.$router.push({ name: 'Dashboard' })
			} else {
				vm.error = true
				vm.result = 'Email or Password is incorrect.'
				vm.showResult = true
			}
			**/
			
			const formData = new FormData()
			formData.append('userId', this.userEmail)
			formData.append('passwd', this.password)
			
			axios
				.post('http://localhost:8090/board/confirmLoginInfo', formData, {
					withCredentials: false
				})
				.then(response => {
					const result = response.data.result
					console.log(response.data.result)
					if (result == 'success') {
						//this.snackbar = true;

						this.$store.commit('SET_SNACKBAR', {
							show: true,
							msg: 'Login Success',
							color: 'success',
						})
						vm.$session.set('userId', vm.userEmail)
						vm.$router.push({ name: 'Dashboard' })
						
						//this.movePage('/detail?docNo=' + response.data)
					} else {
						alert('로그인에 실패 하였습니다.')
						return
					}
				})
				.catch(error => {
					console.log(error)
				})
			
		},
		doubleCheckId() {
			
			const formData = new FormData()
			formData.append('userId', this.userID)
				axios
					.post('http://localhost:8090/board/selectUserInfo', formData, {
						withCredentials: true,
					})
					.then(response => {
						const total = response.data.total
						const userId = response.data.userId
						if (total > 0) {
							//this.snackbar = true;
							alert('중복된 아이디입니다.')
							this.$refs.userID.focus()
							//this.movePage('/detail?docNo=' + response.data)
						}
					})
					.catch(error => {
						console.log(error)
					})
	    },
		async registUserInfo() {
			var data =
				'userID:' +
				this.userID +
				' ' +
				'passwd:' +
				this.passwd +
				' ' +
				'email:' +
				this.email +
				' ' +
				'name:' +
				this.name +
				' ' +
				'flexRadioDefault:' +
				this.flexRadioDefault +
				' ' +
				'phone1:' +
				this.phone1 +
				' ' +
				'phone2:' +
				this.phone2 +
				' ' +
				'postNum:' +
				this.postNum +
				' ' +
				'addr1:' +
				this.addr1 +
				' ' +
				'addr2:' +
				this.addr2 +
				' ' +
				'multiItems:' +
				this.multiItems +
				' '
			alert(data)
			
			if (this.userID == '' || this.userID == 'undefined') {
				alert('아이디 입력해 주세요.');
				this.$refs.userID.focus()
				return
			}
			
			if (this.passwd == '' || this.passwd == 'undefined') {
				alert('패스워드 입력해 주세요.');
				this.$refs.passwd.focus()
				return
			}
			
			if (this.birth == '' || this.birth == 'undefined' || this.birth.length != 8) {
				alert('생년월일 8자로 입력해 주세요.')
				this.$refs.birth.focus()
				return
			}
			
			if (this.phone1 == '' || this.phone1 == null) {
				alert('폰 번호 입력해 주세요.');
				this.$refs.phone1.focus()
				return
			}
			
			if (this.phone2 == '' || this.phone2 == 'undefined') {
				alert('폰 번호 입력해 주세요.');
				this.$refs.phone2.focus()
				return
			}
			
			if (this.flexRadioDefault == '' || this.flexRadioDefault == 'undefined') {
				alert('성별 입력해 주세요.');
				this.$refs.flexRadioDefault.focus()
				return
			}

			const formData = new FormData()
			formData.append('userId', this.userID)
			formData.append('passwd', this.passwd)
			formData.append('birth', this.birth)
			formData.append('email', this.email)
			formData.append('name', this.name)
			formData.append('flexRadioDefault', this.flexRadioDefault)
			formData.append('phone1', this.phone1)
			formData.append('phone2', this.phone2)
			formData.append('postNum', this.postNum)
			formData.append('addr1', this.addr1)
			formData.append('addr2', this.addr2)
			formData.append('multiItems', this.multiItems)

			let res = await this.confirmDialog(
				'Confirm Write',
				'Do you want to save it?',
			)

			if (res) {
				axios
					.post('http://localhost:8090/board/insertUserInfo', formData, {
						withCredentials: true,
						// 요청
						headers: {
							'Content-Type': 'multipart/form-data',
						},
					})
					.then(response => {
						const total = response.data.row
						const userId = response.data.userId
						console.log(response.data)
						if (response.data.row > 0) {
							//this.snackbar = true;

							this.$store.commit('SET_SNACKBAR', {
								show: true,
								msg: 'Insert Complete',
								color: 'success',
							})
							//this.movePage('/detail?docNo=' + response.data)
						}
					})
					.catch(error => {
						console.log(error)
					})
			}
		},
	},
}
</script>

<style scoped lang="css">
#login {
	height: 50%;
	width: 100%;
	position: absolute;
	top: 0;
	left: 0;
	content: '';
	z-index: 0;
}
</style>
