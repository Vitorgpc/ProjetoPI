package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ItemPedido;
import model.Pedido;

public class PedidoDAO {
	public int criar(Pedido pedido) {
		String sqlInsert = "INSERT INTO pedido(valorTotal, cliente_idCliente, f_pagamento_idPagamento, status) VALUES (?, ?, ?, ?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setDouble(1, pedido.getValorTotal());
			stm.setInt(2, pedido.getCliente_idCliente());
			stm.setInt(3, pedido.getFormaPagamento_idPagamento());
			stm.setString(4, pedido.getStatus());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					pedido.setIdPedido(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedido.getIdPedido();
	}

	public void atualizar(Pedido pedido) {
		String sqlUpdate = "UPDATE pedido SET ValorTotal=?, status = ?, dataPedido = now() WHERE idPedido=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setDouble(1, pedido.getValorTotal());
			stm.setString(2, pedido.getStatus());
			stm.setInt(3, pedido.getIdPedido());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int idPedido) {
		String sqlDelete = "DELETE FROM pedido WHERE idPedido = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, idPedido);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirItem(int idPedido, int idProduto) {
		String sqlDel = "DELETE FROM itempedido WHERE idPedido = ? AND idProduto = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDel);) {
			stm.setInt(1, idPedido);
			stm.setInt(2, idProduto);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Pedido carregar(int idPedido) {
		Pedido pedido = new Pedido();
		pedido.setIdPedido(idPedido);
		String sqlSelect = "SELECT * FROM Pedido WHERE idPedido = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, pedido.getIdPedido());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pedido.setValorTotal(rs.getFloat("valorTotal"));
					pedido.setStatus(rs.getString("status"));
				} else {
					pedido.setIdPedido(-1);
					pedido.setValorTotal(0);
					pedido.setStatus(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pedido;
	}

	public ArrayList<Pedido> listarPedidos() {
		Pedido pedido;
		ArrayList<Pedido> lista = new ArrayList<>();
		String sqlSelect = "SELECT idPedido, ValorTotal FROM Pedido";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					pedido = new Pedido();
					pedido.setIdPedido(rs.getInt("idPedido"));
					pedido.setValorTotal(rs.getFloat("valorTotal"));
					lista.add(pedido);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}

	public ArrayList<Pedido> listarPedidos(String chave) {
		Pedido pedido;
		ArrayList<Pedido> lista = new ArrayList<>();
		String sqlSelect = "SELECT idPedido, ValorTotal FROM pedido where upper(idPedido)like ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, "%" + chave.toUpperCase() + "%");
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					pedido = new Pedido();
					pedido.setIdPedido(rs.getInt("idAdministrador"));
					pedido.setValorTotal(rs.getFloat("valorTotal"));
					lista.add(pedido);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}

	public Pedido listarPedidosCarrinho(int idCliente) {
		Pedido pedido = null;
		String sqlSelect = "SELECT * FROM Pedido WHERE status = 'carrinho' AND cliente_idCliente = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, idCliente);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pedido = new Pedido();
					pedido.setIdPedido(rs.getInt("idPedido"));
					pedido.setValorTotal(rs.getFloat("valorTotal"));
					pedido.setCliente_idCliente(rs.getInt("cliente_idCliente"));
					pedido.setFormaPagamento_idPagamento(rs.getInt("f_Pagamento_idPagamento"));
					pedido.setStatus(rs.getString("status"));
				}else {
					pedido = new Pedido();
					pedido.setCliente_idCliente(idCliente);
					pedido.setStatus("carrinho");
					criar(pedido);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pedido;
	}

	public ArrayList<ItemPedido> ListarItensPedido(int idPedido) {
		ArrayList<ItemPedido> lista = new ArrayList<>();
		String sqlSelect = "SELECT * FROM ItemPedido WHERE idPedido = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, idPedido);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					ItemPedido item = new ItemPedido();
					item.setIdPedido(idPedido);
					item.setIdProduto(rs.getInt("idProduto"));
					item.setQuantidade(rs.getInt("quantidade"));
					lista.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}

	public void inserirCarrinho(int idPedido, int idProduto) {
		String sqlSelect = "SELECT 1 FROM itemPedido WHERE idPedido=? AND idProduto=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, idPedido);
			stm.setInt(2, idProduto);
			try (ResultSet rs = stm.executeQuery();) {
				String query = "insert into itempedido(quantidade, idPedido, idProduto) values (?,?,?)";
				int qtdSoma = 1;
				if (rs.next()) {
					query = "update itempedido set quantidade = quantidade + ? where idPedido = ? and idProduto = ?";
				}
				try (PreparedStatement stm2 = conn.prepareStatement(query);) {
					stm2.setInt(1, qtdSoma);
					stm2.setInt(2, idPedido);
					stm2.setInt(3, idProduto);
					stm2.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
	}
	
	public ItemPedido carregarItem(int idPedido) throws IOException{
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setIdPedido(idPedido);
		String sqlSelect = "SELECT * FROM itempedido WHERE idPedido = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, itemPedido.getIdPedido());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					itemPedido.setIdPedido(rs.getInt("idPedido"));
					itemPedido.setIdProduto(rs.getInt("idProduto"));
					itemPedido.setQuantidade(rs.getInt("quantidade"));
					
				} else {
					itemPedido.setIdProduto(-1);
					itemPedido.setIdProduto(-1);
					itemPedido.setQuantidade(-1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return itemPedido;
	}
	
	public void atualizarItem(ItemPedido itemPedido) {
		String sqlUpdate = "UPDATE itemPedido SET quantidade=? WHERE idProduto=? and idPedido=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, itemPedido.getQuantidade());
			stm.setInt(2, itemPedido.getIdProduto());
			stm.setInt(3, itemPedido.getIdPedido());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}