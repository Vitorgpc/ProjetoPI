package service;

import java.io.IOException;
import java.util.ArrayList;

import dao.PedidoDAO;
import model.ItemPedido;
import model.Pedido;

public class PedidoService {
	PedidoDAO dao = new PedidoDAO();

	public int criar(Pedido pedido) {
		return dao.criar(pedido);
	}

	public void atualizar(Pedido pedido) {
		dao.atualizar(pedido);
	}
	
	public void atualizarItem(ItemPedido itemPedido) {
		dao.atualizarItem(itemPedido);
	}

	public void excluir(int idPedido) {
		dao.excluir(idPedido);
	}
	
	public void excluirItem(int idPedido, int idProduto) {
		dao.excluirItem(idPedido, idProduto);
	}

	public Pedido carregar(int idPedido) {
		return dao.carregar(idPedido);
	}
	
	public ItemPedido carregarItem(int idPedido) throws IOException {
		return dao.carregarItem(idPedido);
	}

	public ArrayList<Pedido> listarPedidos() {
		return dao.listarPedidos();
	}

	public ArrayList<Pedido> listarPedidos(String chave) {
		return dao.listarPedidos(chave);
	}
	
	public Pedido listarPedidosCarrinho(int idCliente) {
		return dao.listarPedidosCarrinho(idCliente);
	}
	
	public ArrayList<ItemPedido> ListarItensPedido(int idPedido) {
		return dao.ListarItensPedido(idPedido);
	}
	
	public void inserirCarrinho(int idPedido, int idProduto){
		dao.inserirCarrinho(idPedido, idProduto);
	}
}