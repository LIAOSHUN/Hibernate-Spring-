<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<div class="card-body">
		<div class="form-group mb-3">
			<label for="memAccount" class="form-label">帳號:<span
				class="text-danger"><font color=red><b>*</b></font></span></label> <input style="text-transform: none;"
				v-model="memAccount" type="text" id="memAccount"
				class="form-control" placeholder="輸入帳號" key="username">
		</div>
		<div class="form-group mb-3">
			<div class="row align-items-center">
				<label class="form-label col" for="memPassword">密碼:<span
					class="text-danger"><font color=red><b>*</b></font></span></label>
			</div>
			<input type="password" class="form-control" id="memPassword"
				placeholder="輸入密碼" v-model="memPassword1"
				style="text-transform: none;" key="upassword">
		</div>

		<!-- Checkbox -->
		<div class="form-check mb-3">
			<input class="form-check-input" type="checkbox" value="remember1"
				id="remember1" v-model="isChecked1" /> <label
				class="form-check-label" for="form2Example3"> 記住帳號 </label>
		</div>
		<div class="form-group text-center">
			<button type="button" class="btn btn-primary w-100" id="btn1"
				@click="checkAccount" key="b1">登入</button>
		</div>
</body>
</html>