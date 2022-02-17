
// carga a los usuarios en la tabla de usuarios.html
async function registrarUsuario() {
	
	let datos = {};
	
	datos.name = document.getElementById('txtName').value;
	datos.lastName = document.getElementById('txtLastName').value;
	datos.email = document.getElementById('txtEmail').value;
	datos.telephone = document.getElementById('txtTelephone').value;
	datos.password = document.getElementById('txtPassword').value;
	let repeatPassword = document.getElementById('txtRepeatPassword').value;
	
	if(repeatPassword != datos.password ){
		alert('No hay coincidencias en las contraseñas');
		return;
	}
	
	const request = await fetch('usuarios', {
		method: 'POST',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
	body: JSON.stringify(datos)
	});
	
	alert('¡La cuenta fue creada exitosamente!')
	window.location.href='login.html'
}