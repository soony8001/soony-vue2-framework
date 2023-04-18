import axios from 'axios'

const instance = axios.create({
	// baseURL: 'http://192.168.56.126:8090/',
	baseURL: 'http://localhost:8090/',
})

/**
 * 게시글 목록 조회
 * @param {*} params
 */
function getBoardListAPI(params) {
	return instance.get('/board/list', params)
}

/**
 * 게시글 목록 조회 (메인페이지)
 * @param {*} params
 */
function getBoardListMainAPI(params) {
	return instance.get('/board/mainList', params)
}

/**
 * 게시글 상세 조회
 * @param {*} params
 */
function getBoardDetailAPI(params) {
	return instance.get('/board/detail', params)
}

/**
 * 게시글 작성
 * @param {*} params
 */
function insertBoardAPI(params) {
	return instance.post('/board/insert', null, params)
}

/**
 * 게시글 수정
 * @param {*} params
 */
function updateBoardAPI(params) {
	return instance.post('/board/update', null, params)
}

/**
 * 게시글 삭제
 * @param {*} params
 */
function deleteBoardAPI(params) {
	return instance.post('/board/delete', null, params)
}

/**
 * 댓글 작성
 * @param {*} params
 */
function insertReplyAPI(params) {
	return instance.post('/board/reply/insert', null, params)
}

/**
 * 댓글 조회
 * @param {*} params
 */
function getReplyListAPI(params) {
	return instance.get('/board/reply/list', params)
}

/**
 * 댓글 수정
 * @param {*} params
 */
function updateReplyAPI(params) {
	return instance.post('/board/reply/update', null, params)
}

/**
 * 댓글 삭제
 * @param {*} params
 */
function deleteReplyAPI(params) {
	return instance.post('/board/reply/delete', null, params)
}

/************** 채용  ********************/
/**
 * 게시글 목록 조회 (화면)
 * @param {*} params
 */
function getRecruitListAPI(params) {
	return instance.get('/recruit/recruitList', params)
}

/**
 * 게시글 목록 조회 (메인페이지)
 * @param {*} params
 */
function getRecruitListMainAPI(params) {
	return instance.get('/recruit/mainList', params)
}

/**
 * 게시글 상세 조회
 * @param {*} params
 */
function getRecruitDetailAPI(params) {
	return instance.get('/recruit/detail', params)
}

/**
 * 게시글 작성
 * @param {*} params
 */
function insertRecruitAPI(params) {
	return instance.post('/recruit/insert', null, params)
}

/**
 * 게시글 수정
 * @param {*} params
 */
function updateRecruitAPI(params) {
	return instance.post('/recruit/update', null, params)
}

/**
 * 게시글 삭제
 * @param {*} params
 */
function deleteRecruitAPI(params) {
	return instance.post('/recruit/delete', null, params)
}

/**
 * 댓글 작성
 * @param {*} params
 */
function insertRecruitReplyAPI(params) {
	return instance.post('/recruit/reply/insert', null, params)
}

/**
 * 댓글 조회
 * @param {*} params
 */
function getRecruitReplyListAPI(params) {
	return instance.get('/recruit/reply/list', params)
}

/**
 * 댓글 수정
 * @param {*} params
 */
function updateRecruitReplyAPI(params) {
	return instance.post('/recruit/reply/update', null, params)
}

/**
 * 댓글 삭제
 * @param {*} params
 */
function deleteRecruitReplyAPI(params) {
	return instance.post('/recruit/reply/delete', null, params)
}

export {
	getBoardListAPI,
	getBoardListMainAPI,
	getBoardDetailAPI,
	insertBoardAPI,
	updateBoardAPI,
	deleteBoardAPI,
	insertReplyAPI,
	getReplyListAPI,
	updateReplyAPI,
	deleteReplyAPI,
	getRecruitListMainAPI,
	getRecruitDetailAPI,
	insertRecruitAPI,
	updateRecruitAPI,
	deleteRecruitAPI,
	insertRecruitReplyAPI,
	getRecruitReplyListAPI,
	updateRecruitReplyAPI,
	deleteRecruitReplyAPI,
	getRecruitListAPI,
}
