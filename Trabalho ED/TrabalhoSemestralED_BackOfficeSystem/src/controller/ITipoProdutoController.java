package controller;

import java.io.IOException;

import model.ListaEncadeada;
import model_main.TipoProduto;

public interface ITipoProdutoController {
	
	public void verificarBaseDadosTipoProduto (ListaEncadeada<TipoProduto> lista)
			throws IOException, Exception;
	public void cadastrarTipoProduto (ListaEncadeada<TipoProduto> lista, String tipo, String descricao)
			throws Exception; 
	public void consultarTipoProduto (ListaEncadeada<TipoProduto> lista, String tipo)
			throws Exception;
	public void excluirTipoProduto (ListaEncadeada<TipoProduto> lista, String tipo)
			throws Exception;
	public void mostrarTodosTiposProduto (ListaEncadeada<TipoProduto> lista)
			throws Exception;
	
}
