package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import model.ListaEncadeada;
import model_main.TipoProduto;

public class TipoProdutoController implements ITipoProdutoController {
	
	//Define caminho
	File diretorio = new File("C:\\TrabalhoED");
	//Define arquivo
	File arquivo = new File(diretorio, "Cadastro_TipoProduto.csv");

	
	public TipoProdutoController() {
		super();
	}
	
	//*******************************************************************************************************************************************************
	@Override
	//Metodo utilizado para verificar existencia de base de dados e criar uma lista encadeada, se nao existir, deve criar um arquivo csv com os dados padroes
	//*******************************************************************************************************************************************************
	public void verificarBaseDadosTipoProduto(ListaEncadeada<TipoProduto> lista) throws Exception  {
		//Verifica se o diretorio existe, se nao existir, cria
		verificarDiretorio();
		//Verifico se o arquivo existe, se nao existir, cria
		verificarArquivo();
		//Preencher a lista de tipo de produto com os dados do cadastro csv
		lista = preencherListaTipoProduto(lista);
		//Verifica se lista foi preenchida com informacoes de cadastro
		if(lista.isEmpty()) {
			//Se lista nao foi preenchida, o sistema encerra
			JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado e o sistema ira encerrar");
			System.exit(1);
		}	//fim if
	}	//fim metodo
	
	
	//***********************************************************************************************************************************************
	//Metodo que verifica se o diretorio ja existe no computador, caso nao exista ele cria um novo, chamada no metodo verificarBaseDadosTipoProduto()
	//***********************************************************************************************************************************************
	private void verificarDiretorio() {
		//Verifica se diretorio existe
		if (!diretorio.exists() || !diretorio.isDirectory()) {
			diretorio.mkdirs(); // Cria o diretorio
		}	//fim if
	}	//fim metodo
	
	
	//***********************************************************************************************************************************************
	//Metodo que verifica se o arquivo CSV de tipo de produto ja existe no computador, caso nao exista ele cria um novo, chamada no metodo verificarBaseDadosTipoProduto()
	//***********************************************************************************************************************************************
	private void verificarArquivo() throws IOException {
		// Verifica se arquivo existe
		if(!arquivo.exists() || !arquivo.isFile()) {
			//Gera os tipos de produto e suas descricoes originais
			String conteudo = gerarDadosOriginais();
			//abre o arquivo e define operacao(write ou append)
			FileWriter fileWriter = new FileWriter(arquivo);
			//escreve o conteudo no arquivo
			PrintWriter print = new PrintWriter(fileWriter);
			//abre o arquivo e escreve o conteudo
			print.write(conteudo);
			//finaliza a escrita
			print.flush();
			//fecha o arquivo
			print.close();
			//fecha o arquivo
			fileWriter.close();
		}	//fim if
	}	//fim metodo
	
	
	//******************************************************************************************************************
	//Metodo que gera os tipos de produto e suas descricoes originais, chamada no metodo verificarBaseDadosTipoProduto()
	//******************************************************************************************************************
	private String gerarDadosOriginais() {
		//vetor com tipos de produtos originais
		String[] tipo = {
			    			"PRODUTOS AO CONSUMIDOR", "PRODUTOS INDUSTRIAIS", "BENS DE CONVENIENCIA", "BENS DE IMPULSO",
			    			"BENS DE EMERGENCIA", "BENS DE COMPRA COMPARADA", "BENS DE ESPECIALIDADE", "BENS PERECIVEIS",
			    			"BENS DURAVEIS", "BENS NAO-DURAVEIS", "BENS DE CAPITAL", "PARTES E MATERIAIS", "ABASTECIMENTO E SERVICOS",
			    			"COMMODITIES", "PRODUTOS INTERMEDIARIOS"
						};
		//vetor com a descricao dos tipos de produtos originais
		String[] descricao = {
			    "USADOS POR USUARIOS-FINAIS", "USADOS NA PRODUCAO DE OUTROS BENS", "ADQUIRIDOS FREQUENTEMENTE E COM UM ESFORCO MINIMO",
			    "COMPRA POR ESTIMULOS SENSORIAIS IMEDIATOS", "BENS NECESSARIOS IMEDIATAMENTE", "ALGUMA COMPARACAO COM OUTROS BENS COMO CARROS E TVs. "
			    + "OU SEJA, SAO PRODUTOS QUE EXIGEM UM ALTO ESFORCO DO CONSUMIDOR PARA COMPARAR OS REQUISITOS E FAZER UMA ESCOLHA QUE ATENDA AS SUAS NECESSIDADES",
			    "COMPARACAO EXTENSIVA COM OUTROS BENS DE UMA LONGA BUSCA POR INFORMACOES", "BENS QUE SE DETERIORARAO RAPIDAMENTE MESMO SEM USO",
			    "BENS QUE SOBREVIVEM A OCASIOES DE MULTILO USO", "BENS QUE SERAO CONSUMIDOS EM UMA UNICA OPORTUNIDADE", "INSTALACOES, EQUIPAMENTOS E CONSTRUCOES",
			    "BENS QUE SAO AGREGADOS A UM PRODUTO FINAL", "BENS QUE FACILITAM A PRODUCAO", "BENS INDIFERENCIAVEIS", "RESULTA DA FABRICACAO DE OUTRO PRODUTO"
			};
		//Cria um buffer para armazenar o conteudo
		StringBuffer buffer = new StringBuffer();
		//Itera sobre os vetores para criar linhas no formato csv (tipo;descricao) e adiciona ao buffer
		for(int id = 0; id < 15; id++) {
			buffer.append(id + ";" + tipo[id] + ";" + descricao[id] + "\r\n");
		}	//fim for
		//Converte conteudo do buffer para string e retorna ao metodo verificarBaseDadosTipoProduto()
		return buffer.toString();
	}	//fim metodo
	
	
	//******************************************************************************************************************************
	//Metodo que preenche a lista de tipo de produto com os dados do cadastro csv, chamada no metodo verificarBaseDadosTipoProduto()
	//******************************************************************************************************************************
	private ListaEncadeada<TipoProduto> preencherListaTipoProduto(ListaEncadeada<TipoProduto> lista) throws Exception {
		//Verificar se o arquivo existe
		if(arquivo.exists() && arquivo.isFile()) {
			//abre arquivo
			FileInputStream fluxo = new FileInputStream(arquivo);
			//le arquivo
			InputStreamReader leitor = new InputStreamReader(fluxo);
			//converte arquivo
			BufferedReader buffer = new BufferedReader(leitor);
			//coloca o conteudo no buffer
			String linha = buffer.readLine();
			while(linha != null) {	//procura EOF(end of file)
				//separa o conteudo da linha pelo ponto e virgula e coloca em um vetor
				String[] conteudo = linha.split(";");
				//Cria instancia do Tipo Produto
				TipoProduto tipoProduto = new TipoProduto(Integer.parseInt(conteudo[0]), conteudo[1], conteudo[2]);
				//Adiciona a lista
				lista.addLast(tipoProduto);
				//le proxima linha
				linha = buffer.readLine();
			}
			//fecha arquivo
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {	//Caso arquivo nao seja encontrado
			//Informa usuario que nao foi possivel encontrar base de dados do sistema
			JOptionPane.showMessageDialog(null, "Nao foi possivel encontrar a base de dados do sistema");
		}	//fim else
		//Retorna lista ao metodo verificarBaseDadosTipoProduto()
		return lista;
	}	//fim metodo

	
	//********************************************************************************************************************
	@Override
	//Metodo utilizado para cadastrar novo tipo de produto no sistema
	//********************************************************************************************************************
	public void cadastrarTipoProduto(ListaEncadeada<TipoProduto> lista, String novoTipo, String novaDescricao) throws Exception  {
		novoTipo = normalizarTipoProduto(novoTipo);
		novaDescricao = normalizarTipoProduto(novaDescricao);
		boolean tipoProdutoExiste = verificarTipoProduto(lista, novoTipo);
		if(!tipoProdutoExiste) {
			//Cria uma instancia de tipo de produto
			TipoProduto tipoProduto = new TipoProduto();
			//Normaliza os dados inseridos pelos usuarios, para nao dar erro no cadastro csv
			tipoProduto.tipo = novoTipo;
			tipoProduto.descricao = novaDescricao;
			tipoProduto.id = gerarId(lista);
			//Adicona novo tipo de produto a lista e ao cadastro csv
			lista.add(tipoProduto, tipoProduto.id);
			//Informa usuario que novo tipo de produto foi adicionado com sucesso ao sistema
			JOptionPane.showMessageDialog(null, tipoProduto.tipo + " foi adicionado como tipo de produto.");
		} else {
			//Informa usuario que o tipo de produto inserido ja existe
			JOptionPane.showMessageDialog(null, "Nao foi possivel realizar o cadastro pois o tipo de produto " + novoTipo + " ja existe na base de dados.");
		}
		//Chamada de metodo para salvar as informacoes no CSV
		salvarTipoProdutoCSV(lista);
	}	//fim metodo
	

	//********************************************************************************************************************
	//Metodo utilizado para verificar se o tipo de produto que esta sendo cadastrado ja nao existe na base de dados
	//********************************************************************************************************************
	private boolean verificarTipoProduto(ListaEncadeada<TipoProduto> lista, String novoTipo) throws Exception {
		//Percorre a lista de tipo de produto 
		int tamanho = lista.size();
		for(int i = 0; i < tamanho; i++) {
			//Cria uma instancia e pega o tipo de produto da lista
			TipoProduto tipoProduto = lista.getValue(i);
			//Verifica se e o mesmo tipo de produto
			if(tipoProduto.tipo.equals(novoTipo)) {
				//Produto existe
				return true;
			}	//fim if
		}	//fim for
		return false;
	}
	
	
	//***********************************************************************
	//Metodo utilizado para gerar um novo id para o tipo de produto
	//***********************************************************************
	private int gerarId(ListaEncadeada<TipoProduto> lista) throws Exception {
		int contador = 0;
		int tamanho = lista.size();
		for(int i = 0; i < tamanho; i++) {
			TipoProduto tipoProduto = lista.getValue(i);
			if(contador != tipoProduto.id) {
				return contador;
			}
			contador++;
		}
		return contador;
	}
	
	
	//******************************************************************************************************************
	@Override
	//Metodo utilizado para consultar um tipo de produto especifico, exibindo-se sua descricao
	//******************************************************************************************************************
	public void consultarTipoProduto(ListaEncadeada<TipoProduto> lista, String tipoProdutoProcurado) throws Exception  {
		//flag que verifica se o tipo de produto procurado foi encontrado
		boolean tipoProdutoExiste = false;
		//Normaliza os dados inseridos pelos usuarios
		tipoProdutoProcurado = normalizarTipoProduto(tipoProdutoProcurado);
		//Percorre a lista em busca do tipo de produto procurado 
		int tamanho = lista.size();
		for(int i = 0; i < tamanho; i++) {
			//Cria uma instancia e pega o tipo de produto da lista
			TipoProduto tipoProduto = lista.getValue(i);
			//Verifica se e o mesmo tipo de produto procurado
			if(tipoProduto.tipo.equals(tipoProdutoProcurado)) {
				//Produto encontrado
				tipoProdutoExiste = true;
				//Informa usuario sobre este tipo de produto
				JOptionPane.showMessageDialog(null, tipoProduto.toString());
				break;
			}	//fim if
		}	//fim for
		//Caso nao exista este tipo de produto
		if (!tipoProdutoExiste) {
			//Informa usuario que o produto procurado nao foi encontrado
		    JOptionPane.showMessageDialog(null, "Este tipo de produto não foi encontrado.");
		}	//fim if
	}	//fim metodo

	
	//****************************************************************************************************************************
	@Override
	//Metodo utilizado para excluir uma categoria de produtos
	//****************************************************************************************************************************
	public void excluirTipoProduto(ListaEncadeada<TipoProduto> lista, String tipoProdutoProcurado) throws IOException, Exception {
		//flag que verifica se o tipo de produto procurado foi encontrado
		boolean tipoProdutoExiste = false;
		//Normaliza os dados inseridos pelos usuarios
		tipoProdutoProcurado = normalizarTipoProduto(tipoProdutoProcurado);
		//Percorre a lista em busca do tipo de produto procurado 
		int tamanho = lista.size();
		for(int i = 0; i < tamanho; i++) {
			//Cria uma instancia e pega o tipo de produto da lista
			TipoProduto tipoProduto = lista.getValue(i);
			//Verifica se e o mesmo tipo de produto procurado
			if(tipoProduto.tipo.equals(tipoProdutoProcurado)) {
				//Produto encontrado
				tipoProdutoExiste = true;
				//Informa usuario a remocao deste tipo de produto
				JOptionPane.showMessageDialog(null, tipoProdutoProcurado + " foi removido do sistema.");
				//Remove tipo de produto do sistema
				lista.remove(i);
				break;
			}	//fim if
		}	//fim for
		//Caso nao exista este tipo de produto
		if(!tipoProdutoExiste) {
			//Informa usuario que o produto procurado nao foi encontrado
			JOptionPane.showMessageDialog(null, "Este tipo de produto nao foi encontrado.");
		}	//fim if
		//Chamada de metodo para salvar as informacoes no CSV
		salvarTipoProdutoCSV(lista);
	}	//fim metodo
	
	
	//*******************************************************************************************************************
	//Metodo que normaliza os dados inseridos pelos usuarios, para nao dar erro no cadastro csv, chamado nos metodos CRUD
	//*******************************************************************************************************************
	private String normalizarTipoProduto(String texto) {
		//Se o texto estiver vazio
		if (texto == null) {
            return null;
        }	//fim if
        // Normaliza para decompor os acentos
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        // Usa uma expressao regular para remover os caracteres acentuados
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        // Retorna string normalizada aos metodos
        String textoSemAcento = pattern.matcher(textoNormalizado).replaceAll("");
        //Converte o texto para maisculo e retorna normalizado aos metodos
        return textoSemAcento.toUpperCase();
	}	//fim if   
	
	
	//*****************************************************************************************************
	@Override
	//Metodo utilizado para exibir ao usuario os tipos de produtos existentes no sistema
	//*****************************************************************************************************
	public void mostrarTodosTiposProduto(ListaEncadeada<TipoProduto> lista) throws IOException, Exception {
		//Percorre a lista em busca do tipo de produto procurado 
		int tamanho = lista.size();
		for(int i = 0; i < tamanho; i++) {
			//Cria uma instancia e pega o tipo de produto da lista
			TipoProduto tipoProduto = lista.getValue(i);
			//Informa ao usuario o tipo de produto e sua descricao
			JOptionPane.showMessageDialog(null, tipoProduto.toString());
		}	//fim for
	}	//fim metodo
	
	
	//*************************************************************************************************
	//Metodo responsavel por salvar as informacoes geradas e alteradas pelo usuario em arquivo CSV
	//*************************************************************************************************
	private void salvarTipoProdutoCSV(ListaEncadeada<TipoProduto> lista) throws IOException, Exception {
		//Verifico se diretorio existe
		if(diretorio.exists() && diretorio.isDirectory()) {
			//Cria um buffer para armazenar o conteudo
			StringBuffer buffer = new StringBuffer();
			//Itera sobre a lista de tipo de produtos
			int tamanho = lista.size();
			for(int i = 0; i < tamanho; i++) {
				//Cria uma instancia e pega o tipo de produto da lista
				TipoProduto tipoProduto = lista.getValue(i);
				//Cria linhas no formato csv (tipo;descricao) e adiciona ao buffer
				buffer.append(tipoProduto.id + ";" + tipoProduto.tipo + ";" + tipoProduto.descricao + "\r\n");
			}	//fim for
			//Converte conteudo do buffer para string
			String conteudo = buffer.toString();
			//abre o arquivo e define operacao(write ou append)
			FileWriter fileWriter = new FileWriter(arquivo, false);
			//escreve o conteudo no arquivo
			PrintWriter print = new PrintWriter(fileWriter);
			//abre o arquivo e escreve o conteudo
			print.write(conteudo);
			//finaliza a escrita
			print.flush();
			//fecha o arquivo
			print.close();
			//fecha o arquivo
			fileWriter.close();
		} else {
			throw new IOException("Diretorio Invalido");
		}	//fim else
	}	//fim metodo                                              
		
}
