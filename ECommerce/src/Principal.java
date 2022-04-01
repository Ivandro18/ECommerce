import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Principal {
	public static ECommerce comercio;
	private static final String NOMEdoARQUIVO = "ECommerce.txt";
	private static String eMail = "";
	private static String nome = "";
	private static String senha = "";
	private static String telefone = "";
	private static int tipo = 0;
	private static int menu = 0;
	private static int nunCat = 0;
	private static int nunProd = 0;
	private static boolean continua = false;
	private static Scanner entrada = new Scanner(System.in);
	private static final String ADM = "a"; 
	
	private static final int CLIENTE = 1;
	private static final int VENDEDOR = 2;
	
	public static void main(String[] args) {
		
		importaDados();		
		System.out.println(comercio.listarCategorias());
		
		System.out.println(comercio.listarUsuario());	
		
		do {
			
			
			
			switch (menu) {
				case 0: System.out.println( "Digite a opção:"
											+ "\n 1. Login"
											+ "\n 2. Cadastrar"
											+ "\n 3. Recuperar senha"
											+ "\n 99. Voltar"); //Menu inicial
						menu = Integer.parseInt(entrada.nextLine());
						continua = true;
					break;
					
				case 1: System.out.println("Digite seu E-mail");														//Login
						eMail = entrada.nextLine();
						
						System.out.println("\nDigite sua senha");
						senha = entrada.nextLine();
						
						if (eMail.equals(ADM) && senha.equals(ADM)) {											//Verifica se é admim
							System.out.println("Logado como administrador\n");
							System.out.println("O que você deseja: "
												+ "\n 1. Adicionar categoria"
												+ "\n 2. Editar categoria "
												+ "\n 3. Remover Categoria"
												+ "\n 99. Sair");
							menu = Integer.parseInt(entrada.nextLine());
							
							do {
							
								switch(menu) {																			// Se for Admim
								case 0:	System.out.println(comercio.listarCategorias());  //Menu
										System.out.println("Digite a opção:"
													+ "\n 1. Adicionar categoria"
													+ "\n 2. Editar categoria "
													+ "\n 3. Remover Categoria"
													+ "\n 99. Voltar");
										menu = Integer.parseInt(entrada.nextLine());
										break;
										
								case 1: System.out.println("Digite o nome da categoria: ");
										comercio.adicionaCategoria(entrada.nextLine());										
										menu = 0;
										exportaDados();
										break;
								
								case 2:	System.out.println("Digite o nome da categoria a ser editado:\n");
										System.out.println(comercio.listarCategorias() + "\n");
										
										menu = Integer.parseInt(entrada.nextLine());
										System.out.println("Digite o novo nome para a categoria");
										String novoNome = entrada.nextLine();
										comercio.editaCategoria(menu, novoNome);
									break;	
										
								case 3: System.out.println("Qual categoria você deseja remover?: ");
										System.out.println(comercio.listarCategorias());
										int categ = Integer.parseInt(entrada.nextLine());
										comercio.removeCategoria(categ);
										menu = 0;
										exportaDados();
										break;
										
								default: continua = false;	
										menu = 0;
								}
								
							}while (continua == true);
							continua = true;
							
						}else {																				// se não for admim
							tipo = comercio.login(eMail, senha);
							menu = 0;
							
							if (tipo == CLIENTE) {									//se for cliente
								comercio.setUsuario(eMail);
								
								do {
									System.out.println("Logado como Cliente\n");
									switch (menu) {
									
									case 0:	System.out.println(comercio.listarCategorias());
											System.out.println("\nDigite o numero da categoria ou:"
															 + "\n 77. Meu carrinho"
															 + "\n 88. Meus pedidos"															
															 + "\n 99. Sair");
											nunCat = Integer.parseInt(entrada.nextLine());
											if (nunCat == 99) {
												continua = false; 
											} else if (nunCat < 50){
												menu = 1;
												continua = true;
											} else menu = nunCat;
										break;
										
									case 1:	System.out.println(comercio.listarProdutosCategoria(nunCat));
											System.out.println("\nDigite o numero do produto ou:"
															 + "\n 99: Voltar");
											nunProd = Integer.parseInt(entrada.nextLine());
											if (nunProd == 99) {
												menu = 1;
												} else {							
												System.out.println("Produto\n");
												System.out.println("Produto\n" + comercio.exibeProduto(nunCat,nunProd));
												System.out.println("\nDgite a opção:"
																+ "\n 66: Adicionar ao carrinho"
																+ "\n 77. Meu carrinho"
																+ "\n 88. Meus pedidos"	
																+ "\n 99: Voltar");
												menu = Integer.parseInt(entrada.nextLine());
												if (menu == 66) {
													menu = 2; 
												}else {
													menu = 0;}												
												}
												
											
									case 2:	System.out.println("\nDigite a quantidade");
									
											int qtdEstoque = comercio.getqtdEstoque(nunCat, nunProd);
											int qtdProduto;
											do {
												qtdProduto = Integer.parseInt(entrada.nextLine());
												if (qtdEstoque < qtdProduto) System.out.println("Quantidade escolhida é maior do que em estoque, escolha outra quantidade.");
												
											} while (qtdEstoque < qtdProduto);
											
											
											comercio.addNoCarrinho(nunCat, nunProd, qtdProduto);
											exportaDados();
											menu = 77;											
										break;
										
									case 77 : System.out.println(comercio.getCarrinho());   		// Meu carrinho
											  System.out.println("\nDgite a opção:"
													 		+ "\n 77. Confirmar compra"
															+ "\n 88. Meus pedidos"	
															+ "\n 99: Voltar");
											  menu = Integer.parseInt(entrada.nextLine());
											  if (menu == 99) {
													menu = 0; 
												}  else if (menu == 77) {
													comercio.confirmaCompra();
													exportaDados();
													menu = 88;
												}
										break;
									
									case 88 : System.out.println("Meus pedidos"
																+ comercio.getMeusPedidos()
																+ "\n\n 99: Voltar");
											  menu = Integer.parseInt(entrada.nextLine());
											  if (menu == 99) menu = 0;
										break;
										
									default:continua = false;
									
									}
									}while(continua == true);
								continua = true;
									
								}else if (tipo == VENDEDOR){							// se for vendedor
									System.out.println("Logado como Vendedor\n");  
									comercio.setUsuario(eMail);
									
									do {
										switch (menu) {
										
										case 0:	System.out.println("Digite a opção:"
																+ "\n 1. Meus Anúncios"
																+ "\n 2. Adicionar anúncio"
																+ "\n 3. Minhas vendas"
																+ "\n 99. Sair");
												menu = Integer.parseInt(entrada.nextLine());
												
											break;
											
										case 1:	System.out.println(comercio.meusAnuncios());
												menu = 0;
												System.out.println("Digite a opção:"
																+ "\n 1. Apagar anúncios"
																+ "\n 2. Adicionar anúncio"
																+ "\n 3. Editar anúncio"
																+ "\n 4. Minhas vendas"
																+ "\n 99. Sair");
												menu = Integer.parseInt(entrada.nextLine());
												if (menu == 1) {
													System.out.println("Digite o anuncio que será deletado:"
																	+ "\n 99. voltar");
													menu = Integer.parseInt(entrada.nextLine());
													if (menu == 99) {
														menu = 1;
													} else {
														comercio.removeProduto(menu);
														exportaDados();
													}
												}
												break;
										
												
												
										case 2:	System.out.println(comercio.listarCategorias());   // adiciona produto
												System.out.println("Escolha a categoria:");
												nunCat = Integer.parseInt(entrada.nextLine());
												
												System.out.println("Digite o nome:");
												String nomeProduto = entrada.nextLine();
												
												System.out.println("Digite o preço:");
												double precoProduto = Double.parseDouble(entrada.nextLine());
												
												System.out.println("Digite a quantidade:");
												int qtdProduto = Integer.parseInt(entrada.nextLine());
												
												System.out.println("Digite uma descrição para o produto:");
												String descriProduto = entrada.nextLine();
												
												comercio.adicionaProduto(nunCat, nomeProduto, precoProduto, qtdProduto,descriProduto);
												exportaDados();
												menu = 1;
												break;
											
										case 3: menu = 0;															////Editar produto
												do {
													switch (menu) {
													
													case 0: System.out.println("Digite o anuncio a ser editado:\n");
															System.out.println(comercio.meusAnuncios());
															
															menu = Integer.parseInt(entrada.nextLine());
															System.out.println("Anúncio escolhido:");
															System.out.println(comercio.meuAnuncio(menu));
															
															System.out.println("Digite o que será editado:");										
															System.out.println("\n  1: Mudar nome"
																			+ "\n  2: Mudar preço"
																			+ "\n  3: Mudar quantidade"
																			+ "\n  4: Mudar descrição"
																			+ "\n 99: Voltar");
															menu = Integer.parseInt(entrada.nextLine());
														
														break;
													
													case 1: System.out.println("Digite o novo nome:");  //mudar nome
															String novoNome = entrada.nextLine();
															comercio.editarNomeProduto(novoNome);
															menu = 0;
														break;
														
													case 2: System.out.println("Digite o novo preço:");
															double novoPreco = Double.parseDouble(entrada.nextLine());// Mudar preço
															comercio.editarprecoProduto(novoPreco);
															menu = 0;
														break;
														
													case 3:	System.out.println("Digite a nova quantidade:");  // Mudar quantidade
															int novaQtd = Integer.parseInt(entrada.nextLine());
															comercio.editarQtdProduto(novaQtd);
															menu = 0;
														break;
														
													case 4:	System.out.println("Digite nova descrição:"); // mudar descrição
															String novaDescricao = entrada.nextLine();
															comercio.editarDescriProduto(novaDescricao);
															menu = 0;
														break;
														
													
													}
												}while(menu != 99);
												menu = 0;
												exportaDados();
												break;
												
												
										case 4: System.out.println("\nMinhas vendas\n"
																+ comercio.getMinhasVendas(eMail));
										
												menu = Integer.parseInt(entrada.nextLine());
												if (menu == 99) menu = 0;
											break;
											
										default:continua = false;
										
										}
										}while(continua == true);
									continua = true;
									
								}else {
									System.out.println("Usuário ou senha incorreta");
								}
								
								
							
						}
						menu = 0;
					break;
					
				case 2: System.out.println("\nDigite seu E-mail");                     				// Cadastro novo usuário
						eMail = entrada.nextLine();
						
						if (comercio.verificaEmail(eMail)) {
							System.out.println("\nEmail já cadastrado, recupere sua senha");
							menu = 0;
						} else {
							System.out.println("\nDigite seu nome");
							nome = entrada.nextLine();
							
							System.out.println("\nDigite uma senha");
							senha = entrada.nextLine();		
							
							System.out.println("\nDigite seu telefone");
							telefone = entrada.nextLine();		
							
							System.out.println("\nDigite o tipo do cadastro"
											+ "\n 1. Cliente"
											+ "\n 2. Vendedor");
							tipo = Integer.parseInt(entrada.nextLine()) ;
							
							if (tipo == 1) {
								comercio.adicionaCliente(eMail,nome, senha, telefone);
							} else {							
								comercio.adicionaVendedor(eMail,nome, senha, telefone);
							}
							
							exportaDados();
						}
						menu = 0;
				
					break;
					
				default: continua = false;
			}
			
			
		}while (continua == true);
		
		exportaDados();
		
		System.out.println("Fim");
	}
		
	
	
	private static ObjectInputStream input;
	
	public static void importaDados() {
		//abre o arquivo
		if (Files.exists(Paths.get(NOMEdoARQUIVO))){
			try {
				input = new ObjectInputStream(Files.newInputStream(Paths.get(NOMEdoARQUIVO)));
				}			
			
			catch (IOException ioException) {
				System.err.println("Erro ao abrir araquivo");
				System.exit(1);
				}
			
			//Ler o arquivo
			try {
				comercio = (ECommerce) input.readObject();
			}
			
			catch(EOFException endOfFileException){
				System.out.printf("%No more records%n");
				
			}
			
			catch(ClassNotFoundException classNotFoundException) {
				System.err.println("Tipo do objeto errado");
			}
			
			catch (IOException ioExcepcion) {
				System.err.println("Erro ao ler arquivo");
				comercio = new ECommerce("Informática");
			}
			
			//fecha o arquivo
			try {
				if (input != null) 
					input.close();
			}
			catch(IOException ioExcepcion) {
				System.err.println("Erro ao fechar arquivo");
			}
			
			
		}
		else {
			System.out.println("Arquivo não existe");
			comercio = new ECommerce("Informática");
		}
	}
	
	private static ObjectOutputStream output;
	
	//salva os dados em arquivo sereliazado
	public static void exportaDados() {
		//abre o arquivo
		try {
			output = new ObjectOutputStream(Files.newOutputStream(Paths.get(NOMEdoARQUIVO)));
		}
		catch (IOException ioException) {
			System.err.println("Erro ao abrir araquivo");
			System.exit(1);
		}
		try {
			//serializa o objeto em um arquivo
			output.writeObject(comercio);
		}
		catch (IOException ioExcepcion) {
			System.err.println("Erro ao gravar arquivo");
		}
		
		//fecha o arquivo
		try {
			if (output != null) 
				output.close();
		}
		catch(IOException ioExcepcion) {
			System.err.println("Erro ao fechar arquivo");
		}
	}
}
