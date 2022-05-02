package up.techninjas.jogo;

import up.techninjas.pecas.Damas;
import up.techninjas.pecas.Pecas;
import up.techninjas.tabuleiro.Tabuleiro;

public class Checks {

	private boolean existe, destino;
	Tabuleiro tab = new Tabuleiro();
	int index, indexC;

	public boolean checkPecaEscolhida(int vez, int i, int j) {

		if (tab.checarTab(i, j) != "x") {
			if (vez == 0 && tab.checarTab(i, j) == "A") {
				existe = true;
			} else if (vez == 1 && tab.checarTab(i, j) == "B") {
				existe = true;
			} else {
				System.out.println("Esta peca nao e sua");
				existe = false;
			}
		} else {
			existe = false;
			System.out.println("Peca inexistente");
		}
		return existe;
	}

	public boolean checkDestino(int vez, int linhaP, int colunaP, int linhaJ, int colunaJ) {
		if (linhaJ == linhaP || colunaJ == colunaP) {
			destino = false;
			System.out.println("Voce so pode jogar na diagonal");
		} else if (linhaJ > 7 || linhaJ < 0 || colunaJ > 7 || colunaJ < 0) {
			destino = false;
			System.out.println("Informe um destino com linhas e colunas entre 1 e 8");
		} else if ((linhaP - linhaJ == 1 || linhaP - linhaJ == -1)
				&& (colunaP - colunaJ == 1 || colunaP - colunaJ == -1)) {
			// ^^^^ Checa a distancia de 1 casa
			// Checagem de comer pecas e jogada destino do jogador1 'A'
			if (vez == 0 && tab.checarTab(linhaJ, colunaJ) == "B") {
				if (linhaJ < linhaP && colunaJ > colunaP) {
					// CIMA-DIREITA
					if (linhaJ - 1 < 0 || colunaJ + 1 > 7) {
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ - 1, colunaJ + 1) == "x") {
							index = checkIndex(linhaP, colunaP, "A");
							indexC = checkIndexComido(linhaJ, colunaJ);
							tab.setTabJogada(linhaJ - 1, colunaJ + 1, index, vez);
							tab.setTabX(linhaJ, colunaJ);
							removerPeca(indexC);
							tab.setTabX(linhaP, colunaP);
							Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);
							destino = true;
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				} else if (linhaJ < linhaP && colunaJ < colunaP) {
					// CIMA-ESQUERDA
					if (linhaJ - 1 < 0 || colunaJ - 1 < 0) {
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ - 1, colunaJ - 1) == "x") {
							index = checkIndex(linhaP, colunaP, "A");
							indexC = checkIndexComido(linhaJ, colunaJ);
							tab.setTabJogada(linhaJ - 1, colunaJ - 1, index, vez);
							tab.setTabX(linhaJ, colunaJ);
							removerPeca(indexC);
							tab.setTabX(linhaP, colunaP);
							Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);
							destino = true;
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				} else if (linhaJ > linhaP && colunaJ < colunaP) {
					// BAIXO-ESQUERDA
					if (linhaJ + 1 > 7 || colunaJ - 1 < 0) {
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ + 1, colunaJ - 1) == "x") {
							index = checkIndex(linhaP, colunaP, "A");
							indexC = checkIndexComido(linhaJ, colunaJ);
							tab.setTabJogada(linhaJ + 1, colunaJ - 1, index, vez);
							tab.setTabX(linhaJ, colunaJ);
							removerPeca(indexC);
							tab.setTabX(linhaP, colunaP);
							Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);
							destino = true;
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				} else if (linhaJ > linhaP && colunaJ > colunaP) {
					// BAIXO-DIREITA
					if (linhaJ + 1 > 7 || colunaJ + 1 > 7) {
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ + 1, colunaJ + 1) == "x") {
							index = checkIndex(linhaP, colunaP, "A");
							indexC = checkIndexComido(linhaJ, colunaJ);
							tab.setTabJogada(linhaJ + 1, colunaJ + 1, index, vez);
							tab.setTabX(linhaJ, colunaJ);
							removerPeca(indexC);
							tab.setTabX(linhaP, colunaP);
							Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);
							destino = true;
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				}
			} else if (vez == 0 && tab.checarTab(linhaJ, colunaJ) == "A") {
				System.out.println("Voce nao pode comer a propria peca!");
				destino = false;
			} else if (vez == 0 && tab.checarTab(linhaJ, colunaJ) == "x") {
				if(linhaJ > linhaP) {
					destino = false;
					System.out.println("Voce nao pode andar para tras!");
				}else {
					index = checkIndex(linhaP, colunaP, "A");
					tab.setTabJogada(linhaJ, colunaJ, index, vez);
					tab.setTabX(linhaP, colunaP);
					destino = true;
				}
			}
			// Checagem de comer pecas e jogada destino do jogador2 'B'
			if (vez == 1 && tab.checarTab(linhaJ, colunaJ) == "A") {
				if (linhaJ < linhaP && colunaJ > colunaP) {
					// CIMA-DIREITA
					if (linhaJ - 1 < 0 || colunaJ + 1 > 7) {
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ - 1, colunaJ + 1) == "x") {
							index = checkIndex(linhaP, colunaP, "B");
							indexC = checkIndexComido(linhaJ, colunaJ);
							tab.setTabJogada(linhaJ - 1, colunaJ + 1, index, vez);
							tab.setTabX(linhaJ, colunaJ);
							removerPeca(indexC);
							tab.setTabX(linhaP, colunaP);
							Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
							destino = true;
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				} else if (linhaJ < linhaP && colunaJ < colunaP) {
					// CIMA-ESQUERDA
					if (linhaJ - 1 < 0 || colunaJ - 1 < 0) {
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ - 1, colunaJ - 1) == "x") {
							index = checkIndex(linhaP, colunaP, "B");
							indexC = checkIndexComido(linhaJ, colunaJ);
							tab.setTabJogada(linhaJ - 1, colunaJ - 1, index, vez);
							tab.setTabX(linhaJ, colunaJ);
							removerPeca(indexC);
							tab.setTabX(linhaP, colunaP);
							Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
							destino = true;
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				} else if (linhaJ > linhaP && colunaJ < colunaP) {
					// BAIXO-ESQUERDA
					if (linhaJ + 1 > 7 || colunaJ - 1 < 0) {
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ + 1, colunaJ - 1) == "x") {
							index = checkIndex(linhaP, colunaP, "B");
							indexC = checkIndexComido(linhaJ, colunaJ);
							tab.setTabJogada(linhaJ + 1, colunaJ - 1, index, vez);
							tab.setTabX(linhaJ, colunaJ);
							removerPeca(indexC);
							tab.setTabX(linhaP, colunaP);
							Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
							destino = true;
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				} else if (linhaJ > linhaP && colunaJ > colunaP) {
					// BAIXO-DIREITA
					if (linhaJ + 1 > 7 || colunaJ + 1 > 7) {
						destino = false;
						System.out.println("Voce nao pode comer essa peca pois nao ha espaco para a sua!");
					} else {
						if (tab.checarTab(linhaJ + 1, colunaJ + 1) == "x") {
							index = checkIndex(linhaP, colunaP, "B");
							indexC = checkIndexComido(linhaJ, colunaJ);
							tab.setTabJogada(linhaJ + 1, colunaJ + 1, index, vez);
							tab.setTabX(linhaJ, colunaJ);
							removerPeca(indexC);
							tab.setTabX(linhaP, colunaP);
							Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
							destino = true;
						} else {
							System.out.println("Apenas damas podem comer + de uma peca por jogada");
							destino = false;
						}
					}
				}
			} else if (vez == 1 && tab.checarTab(linhaJ, colunaJ) == "B") {
				System.out.println("Voce nao pode comer a propria peca!");
				destino = false;
			} else if (vez == 1 && tab.checarTab(linhaJ, colunaJ) == "x") {
				if(linhaJ < linhaP) {
					destino = false;
					System.out.println("Voce nao pode andar para tras!");
				}else {
					index = checkIndex(linhaP, colunaP, "B");
					tab.setTabJogada(linhaJ, colunaJ, index, vez);
					tab.setTabX(linhaP, colunaP);
					destino = true;
				}
			}
		} else {
			destino = false;
			System.out.println("Voce so pode andar 1 casa");
		}
		return destino;
	}

	public boolean checkDestinoDama(int vez, int linhaP, int colunaP, int linhaJ, int colunaJ) {
		boolean diagOk = false;
		int tipoDiag = 0;
		// 1 - CIMA ESQUERDA
		// 2 - BAIXO DIREITA
		// 3 - BAIXO ESQUERDA
		// 4 - CIMA DIREITA
		if (linhaJ > 7 || linhaJ < 0 || colunaJ > 7 || colunaJ < 0) {
			destino = false;
			System.out.println("Informe um destino com linhas e colunas entre 1 e 8");
			// Checa o tipo de diagonal de destino e se esta dentro dela
			// CIMA ESQUERDA
		} else if (linhaJ < linhaP && colunaJ < colunaP) {
			for (int i = linhaP, j = colunaP; i >= 0 && j >= 0; i--, j--) {
				if (i == linhaJ && j == colunaJ) {
					diagOk = true;
					destino = true;
					tipoDiag = 1;
					break;
				} else {
					diagOk = false;
					destino = false;
				}
			}
			if (!diagOk) {
				System.out.println("Voce so pode andar dentro da diagonal!");
			}
			// BAIXO DIREITA
		} else if (linhaJ > linhaP && colunaJ > colunaP) {
			for (int i = linhaP, j = colunaP; i < 8 && j < 8; i++, j++) {
				if (i == linhaJ && j == colunaJ) {
					diagOk = true;
					destino = true;
					tipoDiag = 2;
					break;
				} else {
					diagOk = false;
					destino = false;
				}
			}
			if (!diagOk) {
				System.out.println("Voce so pode andar dentro da diagonal!");
			}
			// BAIXO ESQUERDA
		} else if (linhaJ > linhaP && colunaJ < colunaP) {
			for (int i = linhaP, j = colunaP; i < 8 && j >= 0; i++, j--) {
				if (i == linhaJ && j == colunaJ) {
					diagOk = true;
					destino = true;
					tipoDiag = 3;
					break;
				} else {
					diagOk = false;
					destino = false;
				}
			}
			if (!diagOk) {
				System.out.println("Voce so pode andar dentro da diagonal!");
			}
			// CIMA DIREITA
		} else if (linhaJ < linhaP && colunaJ > colunaP) {
			for (int i = linhaP, j = colunaP; i >= 0 && j < 8; i--, j++) {
				if (i == linhaJ && j == colunaJ) {
					diagOk = true;
					destino = true;
					tipoDiag = 4;
					break;
				} else {
					diagOk = false;
					destino = false;
				}
			}
			if (!diagOk) {
				System.out.println("Voce so pode andar dentro da diagonal!");
			}
		}
		// Checa o destino para comer e andar com a peca apos ter validado a diagonal
		if (diagOk && destino) {
			index = -1;
			if (vez == 0) {
				if (tab.checarTab(linhaJ, colunaJ) == "x") {
					switch (tipoDiag) {
					case 1: {
						for (int i = linhaP - 1, j = colunaP - 1; i >= linhaJ && j >= colunaJ; i--, j--) {
							if(tab.checarTab(i , j) == "A") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP - 1, j = colunaP - 1; i >= linhaJ && j >= colunaJ; i--, j--) {
								if(tab.checarTab(i, j) == "B") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "A");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					case 2: {
						for (int i = linhaP + 1, j = colunaP + 1; i <= linhaJ && j <= colunaJ; i++, j++) {
							if(tab.checarTab(i, j) == "A") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP + 1, j = colunaP + 1; i <= linhaJ && j <= colunaJ; i++, j++) {
								if(tab.checarTab(i, j) == "B") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "A");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					case 3: {
						for (int i = linhaP + 1, j = colunaP - 1; i <= linhaJ && j >= colunaJ; i++, j--) {
							if(tab.checarTab(i, j) == "A") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP + 1, j = colunaP - 1; i <= linhaJ && j >= colunaJ; i++, j--) {
								if(tab.checarTab(i, j) == "B") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "A");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					case 4: {
						for (int i = linhaP - 1, j = colunaP + 1; i >= linhaJ && j <= colunaJ; i--, j++) {
							if(tab.checarTab(i, j) == "A") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP - 1, j = colunaP + 1; i >= linhaJ && j <= colunaJ; i--, j++) {
								if(tab.checarTab(i, j) == "B") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog1.setPontos(Dama.jog1.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "A");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					default:
						System.out.println("Alguma merda deu");
						break;
					}
				}else {
					destino = false;
					System.out.println("Jogue em uma casa vazia!");
				}
			} else if(vez == 1){
				if (tab.checarTab(linhaJ, colunaJ) == "x") {
					switch (tipoDiag) {
					case 1: {
						for (int i = linhaP - 1, j = colunaP - 1; i >= linhaJ && j >= colunaJ; i--, j--) {
							if(tab.checarTab(i, j) == "B") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP - 1, j = colunaP - 1; i >= linhaJ && j >= colunaJ; i--, j--) {
								if(tab.checarTab(i, j) == "A") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "B");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					case 2: {
						for (int i = linhaP + 1, j = colunaP + 1; i <= linhaJ && j <= colunaJ; i++, j++) {
							if(tab.checarTab(i, j) == "B") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP + 1, j = colunaP + 1; i <= linhaJ && j <= colunaJ; i++, j++) {
								if(tab.checarTab(i, j) == "A") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "B");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					case 3: {
						for (int i = linhaP + 1, j = colunaP - 1; i <= linhaJ && j >= colunaJ; i++, j--) {
							if(tab.checarTab(i, j) == "B") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP + 1, j = colunaP - 1; i <= linhaJ && j >= colunaJ; i++, j--) {
								if(tab.checarTab(i, j) == "A") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "B");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					case 4: {
						for (int i = linhaP - 1, j = colunaP + 1; i >= linhaJ && j <= colunaJ; i--, j++) {
							if(tab.checarTab(i, j) == "B") {
								destino = false;
								System.out.println("Tem uma peca sua no meio");
								break;
							}
						}
						if(destino) {
							for (int i = linhaP - 1, j = colunaP + 1; i >= linhaJ && j <= colunaJ; i--, j++) {
								if(tab.checarTab(i, j) == "A") {
									indexC = checkIndexComido(i, j);
									removerPeca(indexC);
									Dama.jog2.setPontos(Dama.jog2.getPontos() + 1);
									tab.setTabX(i, j);
								}
							}
							index = checkIndex(linhaP, colunaP, "B");
							//System.out.println(index);
							tab.setTabJogada(linhaJ, colunaJ, index, vez);
							tab.setTabX(linhaP, colunaP);
						}
						break;
					}
					default:
						System.out.println("Alguma merda deu");
						break;
					}
				}else {
					destino = false;
					System.out.println("Jogue em uma casa vazia!");
				}
			}
		}

		return destino;
	}

	// Verificando se a peça virou uma dama e entao setando
	public void checkDama(int vez, boolean correto) {
		boolean troca = false;
		int i = 0, j = 0;
		if (correto) {
			if (vez == 0) {
				for (Pecas peca : Tabuleiro.peca) {
					if (peca.getJogador() == 0 && peca.getLinha() == 0) {
						index = peca.getIndex();
						i = peca.getLinha();
						j = peca.getColuna();
						troca = true;
					}
				}
				if (troca) {
					Tabuleiro.peca.remove(index);
					Pecas dama = new Damas(0, 0, i, j, "A", true);
					Tabuleiro.peca.add(dama);
					tab.setIndex();
				}
			} else {
				for (Pecas peca : Tabuleiro.peca) {
					if (peca.getJogador() == 1 && peca.getLinha() == 7) {
						index = peca.getIndex();
						i = peca.getLinha();
						j = peca.getColuna();
						troca = true;
					}
				}
				if (troca) {
					Tabuleiro.peca.remove(index);
					Pecas dama = new Damas(0, 1, i, j, "B", true);
					Tabuleiro.peca.add(dama);
					tab.setIndex();
				}
			}
		}
	}

	public boolean checkPontos() {
		boolean gameover;

		if (Dama.jog1.getPontos() == 12) {
			gameover = true;
		} else if (Dama.jog2.getPontos() == 12) {
			gameover = true;
		} else if(Dama.jog1.getPontos() == 11 && Dama.jog2.getPontos() == 11){
			gameover = true;
		}else {
			gameover = false;
		}
		return gameover;
	}

	public int checkVitoria(boolean gameover) {
		int vencedor = 2;
		if (gameover) {
			if (Dama.jog1.getPontos() == 12) {
				vencedor = 0;
			} else if (Dama.jog2.getPontos() == 12) {
				vencedor = 1;
			} else if(Dama.jog1.getPontos() == 11 && Dama.jog2.getPontos() == 11) {
				vencedor = 2;
			}
		} else {
			vencedor = 3;
		}
		return vencedor;
	}

	public int checkIndex(int linha, int coluna, String jogador) {
		for (Pecas peca : Tabuleiro.peca) {
			if (peca.getPeca().equals(jogador) && peca.getLinha() == linha && peca.getColuna() == coluna) {
				index = peca.getIndex();
			}else if(peca instanceof Damas) {
				if(((Damas)peca).getPeca().equals(jogador) && ((Damas)peca).getLinha() == linha && ((Damas)peca).getColuna() == coluna) {
					index = ((Damas)peca).getIndex();
				}
			}
		}
		return index;
	}

	public int checkIndexComido(int linha, int coluna) {
		for (Pecas peca : Tabuleiro.peca) {
			if (peca.getLinha() == linha && peca.getColuna() == coluna) {
				indexC = peca.getIndex();
			}
		}
		return indexC;
	}

	public void removerPeca(int index) {
		Tabuleiro.peca.remove(index);
		tab.setIndex();
	}
}