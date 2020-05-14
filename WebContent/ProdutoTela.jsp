<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Darkcell! - Explosão de Preços Baixo</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/ProdutoTela.css" rel="stylesheet">
</head>
<body>
	<c:import url="header.jsp" />
	<c:import url="Menu.jsp" />
	<div id="main" class="container">
		<div id="main" class="containerProduto">
			<div class="TopoProduto">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="TelaInicial.jsp">Pagina Inicial</a></li>
						<li class="breadcrumb-item"><a href="controller.do?command=ListarColecao&v=1">Coleções</a></li>
						<li class="breadcrumb-item active" aria-current="page">${produto.nome}</li>
					</ol>
				</nav>
			</div>
			<div class="DivImg">
				<img class="imgProduto"
					src="data:image/jpg;base64,${produto.base64Image}">
			</div>

			<div class="col-md-3 col-lg-6 col-sm-12 col-xs-12">
				<h2 class="TituloProduto" align="left">
					<strong>${produto.nome}</strong>
				</h2>
				<p class="valor">R$ ${produto.valor}</p>
				<div class="divCapacid">
					<h5>Capacidade:</h5>
					<select>
						<option value="${produto.capacidade}">${produto.capacidade}</option>
					</select>
				</div>
				<div class="divCor">
					<h5>Cores:</h5>
					<select>
						<option value="${produto.cor}">${produto.cor}</option>
					</select>
					<div class="btm">
						<a type="button" class="btn btn-danger btn-lg"
							href="controller.do?command=AdicionarCarrinho&idProduto=${produto.idProduto}">ADICIONAR
							AO CARRINHO</a>
					</div>
				</div>
				<img width="350px" src="./imagens/ImagemMercado.jpg">
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<c:import url="footer.jsp" />
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>