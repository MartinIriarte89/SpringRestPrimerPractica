
// carga a los usuarios en la tabla de usuarios.html
async function iniciarSesion() {
	
	let datos = {};
	
	datos.email = document.getElementById('txtEmail').value;
	datos.password = document.getElementById('txtPassword').value;
		
	const request = await fetch('login', {
		method: 'POST',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
	body: JSON.stringify(datos)
	});
	//const response = await request.json();	
}