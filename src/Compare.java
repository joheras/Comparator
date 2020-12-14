import java.awt.EventQueue;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import org.jdesktop.swingx.JXImageView;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Compare {

	private JFrame frame;
	private JXImageView imageView1;
	private JXImageView imageView2;
	private int position = 0;
	private int eligeA = 0;
	private int eligeB = 0;
	private int eligeIguales = 0;
	private ArrayList<String> eleccionesA = new ArrayList<>();
	private ArrayList<String> eleccionesB = new ArrayList<>();
	private ArrayList<String> eleccionesIguales = new ArrayList<>();
	private List<String> imagesA = searchFilesA();
	private List<String> imagesB = searchFilesB();

	private String izquierda = "";
	private String derecha = "";
	private String currentImage = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compare window = new Compare();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Compare() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblculEstaMejor = new JLabel("¿Cuál esta mejor?");

		imageView1 = new JXImageView();

		imageView2 = new JXImageView();

		JButton btnIguales = new JButton("Iguales");
		btnIguales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					eventoIguales();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnNewButton = new JButton("↑");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					eventoIzquierda();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnNewButton_1 = new JButton("↑");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					eventoDerecha();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblculEstaMejor, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 165,
												Short.MAX_VALUE)
										.addComponent(imageView1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 165,
												Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnIguales, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addGroup(
										groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 157,
														Short.MAX_VALUE)
												.addComponent(imageView2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
														157, Short.MAX_VALUE))))
				.addGap(18)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(6).addComponent(lblculEstaMejor)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addGap(12)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(imageView1, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
										.addComponent(imageView2, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnNewButton_1).addComponent(btnNewButton)))
						.addGroup(groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnIguales)))
				.addContainerGap()));
		frame.getContentPane().setLayout(groupLayout);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		try {
			showImages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void eventoIguales() throws IOException {
		eligeIguales++;
		eleccionesIguales.add(currentImage);
		position++;
		showImages();
	}

	private void eventoIzquierda() throws IOException {
		if (izquierda == "A") {
			eligeA++;
			eleccionesA.add(currentImage);
		} else {
			eligeB++;
			eleccionesB.add(currentImage);
		}
		position++;
		showImages();
	}

	private void eventoDerecha() throws IOException {
		if (derecha == "A") {
			eligeA++;
			eleccionesA.add(currentImage);
		} else {
			eligeB++;
			eleccionesB.add(currentImage);
		}
		position++;
		showImages();
	}

	private void showImages() throws IOException {
		if (position < imagesA.size()) {
			Random rand = new Random();
			currentImage = imagesA.get(position);
			if (rand.nextFloat() < 0.5) {
				imageView1.setImage(new File(imagesA.get(position)));
				imageView1.setScale(0.8);
				imageView2.setImage(new File(imagesB.get(position)));
				imageView2.setScale(0.8);
				izquierda = "A";
				derecha = "B";
			} else {
				imageView1.setImage(new File(imagesB.get(position)));
				imageView1.setScale(0.8);
				imageView2.setImage(new File(imagesA.get(position)));
				imageView2.setScale(0.8);
				izquierda = "B";
				derecha = "A";
			}
		} else {
			File myObj = new File(System.getProperty("user.dir") + "/resultado.txt");
			myObj.createNewFile();
			FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/resultado.txt");
			
			myWriter.write("A: " + eligeA + "\n");
			myWriter.write("B: " + eligeB+ "\n");
			myWriter.write("Iguales: " + eligeIguales+ "\n");
			myWriter.write("Ficheros A elegidos: "+ eleccionesA.toString()+ "\n");
			myWriter.write("Ficheros B elegidos: "+ eleccionesB.toString()+ "\n");
			myWriter.write("Ficheros iguales: "+ eleccionesIguales.toString()+ "\n");
			myWriter.close();
			System.exit(0);
		}
	}

	private static List<String> searchFilesA() {

		List<String> result = new ArrayList<String>();

		// We ask the user for a directory with nd2 images.

		// We store the list of tiff files in the result list.
		File folder = new File(System.getProperty("user.dir") + "/A");

		search(".*jpg", folder, result);

		Collections.sort(result);
		return result;

	}

	private static List<String> searchFilesB() {

		List<String> result = new ArrayList<String>();

		// We ask the user for a directory with nd2 images.

		// We store the list of tiff files in the result list.
		File folder = new File(System.getProperty("user.dir") + "/B");

		search(".*jpg", folder, result);

		Collections.sort(result);
		return result;

	}

	public static void search(final String pattern, final File folder, List<String> result) {
		for (final File f : folder.listFiles()) {

			if (f.isDirectory()) {
				search(pattern, f, result);
			}

			if (f.isFile()) {
				if (f.getName().matches(pattern) && !f.getName().contains("pred")) {
					result.add(f.getAbsolutePath());
				}
			}

		}
	}

}
