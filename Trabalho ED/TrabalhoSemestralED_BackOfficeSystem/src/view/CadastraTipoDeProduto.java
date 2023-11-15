package view;



import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
@SuppressWarnings("all")
public class CadastraTipoDeProduto extends JFrame {


	private JPanel contentPane_1;
	private JPanel contentPane_1_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraTipoDeProduto frame = new CadastraTipoDeProduto();
					frame.setVisible(true);
					  frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastraTipoDeProduto() {

		getContentPane().setLayout(null);

		contentPane_1 = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		contentPane_1_1 = new JPanel();
		contentPane_1_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1_1.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane_1_1);
		contentPane_1_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CADASTRO DE TIPO DE PRODUTO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(147, 32, 364, 28);
		contentPane_1_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CÓDIGO IDENTIFICADOR :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(24, 91, 256, 44);
		contentPane_1_1.add(lblNewLabel_1);
		
		
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		comboBoxModel.addElement("Bens de Consumo");
		comboBoxModel.addElement("Produtos para o Consumidor");
		JComboBox<String> comboBox = new JComboBox<>(comboBoxModel);
		comboBox.setBounds(354, 181, 208, 20);
		contentPane_1_1.add(comboBox);
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("SELECIONE O TIPO DO PRODUTO :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(24, 170, 320, 38);
		contentPane_1_1.add(lblNewLabel_2);
		
	    
		textField =  createNumericTextField();
		textField.setBounds(278, 102, 129, 22);
		contentPane_1_1.add(textField);
		textField.setColumns(10);
		textField.setToolTipText("DIGITE O TIPO DO PRODUTO");
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(360, 248, 167, 20);
		contentPane_1_1.add(textField_1);
		textField_1.setColumns(10);
		textField.setToolTipText("DIGITE O NUMERO DO CODIGO IDENTIFICADOR ");
		
		JLabel lblNewLabel_3 = new JLabel("ESPECIFIQUE O TIPO DO PRODUTO:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(24, 245, 351, 23);
		contentPane_1_1.add(lblNewLabel_3);
		
		
		
		JButton btnVoltar = new JButton("Voltar");
	        btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        btnVoltar.setBounds(430, 285, 110, 30);
	        contentPane_1_1.add(btnVoltar);
	        
	        JButton btnCadastrar = new JButton("Cadastrar");
	        btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        btnCadastrar.setBounds(550, 285, 110, 30);
	        contentPane_1_1.add(btnCadastrar);

			
		
		
		
	

        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância da tela inicial (classe ED) e a torna visível
            	TelaInicial telaInicial = new TelaInicial();
                telaInicial.setVisible(true);

                // Fecha o frame atual
                dispose();
            }
        });
     }

private JTextField createNumericTextField() {
    JTextField textField = new JTextField();
   
    textField.setDocument(new NumericDocument());
    return textField;
}

private class NumericDocument extends PlainDocument {
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        // Verifica se a string contém apenas dígitos
        if (str != null && str.matches("\\d+")) {
            super.insertString(offs, str, a);
        }
    }
}
public class AlphaNumericTextField extends JTextField {
    public AlphaNumericTextField() {
        setDocument(new AlphaNumericDocument());
    }

    
	private class AlphaNumericDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            // Verifica se a string contém apenas letras
            if (str != null && str.matches("[a-zA-Z]+")) {
                super.insertString(offs, str, a);
            }
        }
    }
}
}
