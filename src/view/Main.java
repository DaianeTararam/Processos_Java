// @author Daiane Tararam

package view;

import controller.RedesController;
import javax.swing.JOptionPane;

public class Main {
	public static void main (String[] args) {
		RedesController controller = new RedesController();
		
		int opcao = 0;
		while (opcao != 9) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma opção: "+
																"\n1 - IP:\n2 - PING:\n9 - SAIR"));
			switch(opcao) {
				case 1:
					controller.ip();
					break;
				case 2:
					controller.ping();
					break;
				case 9:
					JOptionPane.showMessageDialog(null,"Programa finalizado com sucesso!");
					break;
				default:
					JOptionPane.showMessageDialog(null,"Erro! Repita o procedimento!");
					break;
			}
		}
	}
}
