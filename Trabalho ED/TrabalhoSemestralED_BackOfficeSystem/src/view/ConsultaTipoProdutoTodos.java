package view;

import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;



import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ConsultaTipoProdutoTodos extends BaseFrame {

	private JPanel contentPane;
	private JTextField textFieldTipoProdutoTodos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaTipoProdutoTodos frame = new ConsultaTipoProdutoTodos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultaTipoProdutoTodos() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomeTipoProdutoTodos = new JLabel("NOME DO TIPO DO PRODUTO :");
		lblNomeTipoProdutoTodos.setToolTipText("NOME DO TIPO DO PRODUTO ");
		lblNomeTipoProdutoTodos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNomeTipoProdutoTodos.setBounds(42, 88, 295, 35);
		contentPane.add(lblNomeTipoProdutoTodos);
		
		AlphaNumericTextField textFieldTipoProdutoTodos = new AlphaNumericTextField();
		textFieldTipoProdutoTodos.setToolTipText("DIGITE O NOME DO TIPO DO PRODUTO");
		textFieldTipoProdutoTodos.setBounds(334, 96, 201, 25);
		contentPane.add(textFieldTipoProdutoTodos);
		textFieldTipoProdutoTodos.setColumns(10);
		
		JLabel lblTipoProdutoTodos = new JLabel("CONSULTA DE TODOS OS PRODUTOS DE UM TIPO ");
		lblTipoProdutoTodos.setToolTipText("CONSULTA DE TODOS OS PRODUTOS DE UM TIPO ");
		lblTipoProdutoTodos.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTipoProdutoTodos.setBounds(76, 27, 515, 25);
		contentPane.add(lblTipoProdutoTodos);
		
		JLabel lblResultadoTipoProdutoTodos = new JLabel("OS PRODUTOS SÃO :");
		lblResultadoTipoProdutoTodos.setToolTipText("OS PRODUTOS SÃO ");
		lblResultadoTipoProdutoTodos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblResultadoTipoProdutoTodos.setBounds(42, 161, 191, 25);
		contentPane.add(lblResultadoTipoProdutoTodos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("RESULTADO DA SUA CONSULTA DE TODOS OS PRODUTIOS DESTE TIPO");
		scrollPane.setBounds(243, 161, 310, 108);
		contentPane.add(scrollPane);
		
		JButton btnVoltarTipoProdutoTodos= new JButton("Voltar");
		btnVoltarTipoProdutoTodos.setToolTipText("VOLTAR");
		btnVoltarTipoProdutoTodos.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVoltarTipoProdutoTodos.setBounds(425, 285, 110, 30);
		
		contentPane.add(btnVoltarTipoProdutoTodos);

		JButton btnConsultarTipoProdutoTodos = new JButton("Consultar");
		btnConsultarTipoProdutoTodos.setToolTipText("CONSULTAR");
		btnConsultarTipoProdutoTodos.setBackground(new Color(0, 204, 255));
		btnConsultarTipoProdutoTodos.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConsultarTipoProdutoTodos.setBounds(550, 285, 114, 30);
		contentPane.add(btnConsultarTipoProdutoTodos);
		
		JTextArea textAreaResultadoConsultaTipoProdutoTodos = new JTextArea();
		textAreaResultadoConsultaTipoProdutoTodos.setEnabled(false);
		textAreaResultadoConsultaTipoProdutoTodos.setToolTipText("RESULTADO DA SUA CONSULTA DE TODOS OS PRODUTIOS DESTE TIPO");
		textAreaResultadoConsultaTipoProdutoTodos.setBounds(243, 161, 308, 106);
		contentPane.add(textAreaResultadoConsultaTipoProdutoTodos);

	
	
		btnConsultarTipoProdutoTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Consulta Realizado com Sucesso", "Sucesso!", JOptionPane.PLAIN_MESSAGE);
			
		}
			
		});

		btnVoltarTipoProdutoTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cria uma instância da tela inicial (classe ED) e a torna visível
				TipoProdutoEspecificoouTodos telaInicial = new TipoProdutoEspecificoouTodos();
				telaInicial.setVisible(true);

				// Fecha o frame atual
				dispose();
			}
		});
	}
	public class AlphaNumericTextField extends JTextField {
	    public AlphaNumericTextField() {
	        setDocument(new AlphaNumericDocument());
	    }

	    private class AlphaNumericDocument extends PlainDocument {
	        @Override
	        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	            // Verifica se a string contém apenas letras ou espaços
	            if (str != null && str.matches("[a-zA-Z ]+")) {
	                super.insertString(offs, str, a);
	            }
	        }
	    }

	}
	}
