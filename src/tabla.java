import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.util.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class tabla {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tabla window = new tabla();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public tabla() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws CsvException 
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
					.addGap(12))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
					.addGap(22))
		);
		
		table = new JTable();
		initTable(table);
		scrollPane.setViewportView(table);
		frame.getContentPane().setLayout(groupLayout);
		
		
	}
	
	private void initTable(JTable jTable) {
        String inputFileName = "estudiantes.csv";
        File inputFile;
        String firstRow;
        Vector<Vector<String>> vectorVectorStringsData = new Vector<Vector<String>>();
        Vector<String> vectorStrings = new Vector<String>();
        Vector<String> vectorColumnIdentifiers = new Vector<String>();
        String[] columnIdentifiers;
        DefaultTableModel model = new DefaultTableModel();

        
        inputFile = new File(inputFileName);
        try (FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr)) 
        {
            firstRow = br.readLine().trim();
            if (firstRow != null) {
                // headers:
                columnIdentifiers = firstRow.split(",");

                vectorColumnIdentifiers = new Vector<String>();
                for (int j =0; j < columnIdentifiers.length; j++) {
                    vectorColumnIdentifiers.add(columnIdentifiers[j]);
                }
            }
            // rows
            Object[] tableLines = br.lines().toArray();
            // data rows
            for (int i = 0; i < tableLines.length; i++) {
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split(",");
                vectorStrings = new Vector<String>();
                for (int j =0; j < dataRow.length; j++) {
                    vectorStrings.add(dataRow[j]);
                }
                vectorVectorStringsData.add(vectorStrings);
            }
            
            fr.close();
        }
        catch (IOException ioe) {
            System.out.println("error: " + ioe.getMessage());
        }

        model.setDataVector(vectorVectorStringsData, vectorColumnIdentifiers);
        jTable.setModel(model);
	}
}
