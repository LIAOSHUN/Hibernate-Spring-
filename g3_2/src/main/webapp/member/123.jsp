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
			<label for="memAccount" class="form-label">�b��:<span
				class="text-danger"><font color=red><b>*</b></font></span></label> <input style="text-transform: none;"
				v-model="memAccount" type="text" id="memAccount"
				class="form-control" placeholder="��J�b��" key="username">
		</div>
		<div class="form-group mb-3">
			<div class="row align-items-center">
				<label class="form-label col" for="memPassword">�K�X:<span
					class="text-danger"><font color=red><b>*</b></font></span></label>
			</div>
			<input type="password" class="form-control" id="memPassword"
				placeholder="��J�K�X" v-model="memPassword1"
				style="text-transform: none;" key="upassword">
		</div>

		<!-- Checkbox -->
		<div class="form-check mb-3">
			<input class="form-check-input" type="checkbox" value="remember1"
				id="remember1" v-model="isChecked1" /> <label
				class="form-check-label" for="form2Example3"> �O��b�� </label>
		</div>
		<div class="form-group text-center">
			<button type="button" class="btn btn-primary w-100" id="btn1"
				@click="checkAccount" key="b1">�n�J</button>
		</div>
</body>
</html>