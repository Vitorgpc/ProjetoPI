<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="utf-8">
  		<meta name="viewport" content="width=device-width, initial-scale=1">
  		<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/MeusDadosCompraEndereco.css" rel="stylesheet">
</head>
<body>
	<c:import url="header.jsp" />
	<c:import url="Menu.jsp" />
<div class="cadastroletraLogin">
	<div class="container">
	<br>
		<h3 class="page-header" align="center"> <strong></strong></h3>
		<br>
		<form action="controller.do " method="post">

			<h3><strong>Informações do Endereço</strong></h3>
			<div class="row">
				<div class="form-group col-md-12">
				<br>
					<label for="pais">País: </label> 
					<input value="${cliente.pais}" type="text" class="form-control"
						name="pais" id="pais" maxlength="50" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-6">
					<label for="cep">CEP: </label>
					<input value="${cliente.cep}" type="text" class="form-control"
						name="cep" id="cep" maxlength="100" />
				</div>
				<div class="form-group col-md-6">
					<label for="estado">Estado:
					</label> <input value="${cliente.estado}" type="text" class="form-control"
						name="estado" id="estado" maxlength="100" />
				</div>
				</div>
				<div class="row">
				<div class="form-group col-md-6">
					<label for="bairro">Bairro:
					</label> <input value="${cliente.bairro}" type="text" class="form-control"
						name="bairro" id="bairro"  maxlength="100" />
				</div>

				<div class="form-group col-md-6">
					<label for="endereco">Endereço:
					</label> <input value="${cliente.endereco}" type="text"
						class="form-control" name="endereco" id="endereco" 
						maxlength="50" />
				</div>
				</div>
				<div class="row">
				<div class="form-group col-md-6">
					<label for="numero">Número:	
					</label> <input value="${cliente.numero}" type="text" class="form-control"
						name="numero" id="numero"  maxlength="100" />
				</div>
				<div class="form-group col-md-6">
					<label for="complemento">Complemento:
					</label> <input value="${cliente.complemento}" type="text"
						class="form-control" name="complemento" id="complemento" 
						maxlength="100" />
				</div>
			</div>
			<hr />
			<div class="containerLogin">
				<div id="actions" class="row">
					<div class="md-col-12">
						<button type="submit" class="btn btn-primary" name="command" value="AlterarMeusDadosCompra">Alterar</button>
						<a href="controller.do?command=CarregarPedido" class="btn btn-danger"
							role="button" aria-pressed="true">Cancelar</a>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
		<c:import url="footer.jsp"/>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
</body>
</HTML>

