// Call the dataTables jQuery plugin
$(document).ready(function() {

	cargarUsuarios();
	$('#usuarios').DataTable();
	actualizarNombreCompletoUsuario();
});

function actualizarNombreCompletoUsuario(){
	document.getElementById('txtNombreCompleto').outerHTML = localStorage.email
}

function getHeaders() {
	return {
		'Accept': 'application/json',
		'Content-Type': 'application/json',
		'Authorization': localStorage.token
	
	};
}

// carga a los usuarios en la tabla de usuarios.html
async function cargarUsuarios() {
	const request = await fetch('usuarios', {
		method: 'GET',
		headers: getHeaders()
	});
	const usuarios = await request.json();

	let listadoHtml = '';
	for (let usuario of usuarios) {
		let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

		let usuarioHtml = '<tr><td>' + usuario.id + '</td><td>' + usuario.name + ' ' + usuario.lastName + '</td><td>'
			+ usuario.email + '</td><td>'
			+ usuario.telephone + '</td><td>' + botonEliminar + '</td></tr>'
		listadoHtml += usuarioHtml;
	}

	document.querySelector('#usuarios tbody').outerHTML = listadoHtml
}



//Elimina el usuario dado
async function eliminarUsuario(id) {

	if (!confirm('¿Desea eliminar este usuario?')) {
		return;
	}

	await fetch('usuarios/' + id, {
		method: 'DELETE',
		headers: getHeaders()

	});
	location.reload();
}