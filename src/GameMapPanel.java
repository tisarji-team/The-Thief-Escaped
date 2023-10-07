/**
 *
 * @author Tisarji
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class GameMapPanel extends JPanel {

    private GameTable gameTable;
    private JTable mainTable;
    private JTable characterTable;
    private DefaultTableModel mainTableModel;
    private DefaultTableModel characterTableModel;
    private ImageIcon[] images;
	private Character character;

	private int tileSize;

	public GameMapPanel(int rows, int columns)
	{
/* --------------------------- Main Table for map --------------------------- */
		
		mainTableModel = new DefaultTableModel(rows, columns);
		mainTable = new JTable(mainTableModel);

        int newCellWidth = (int)(160 * 0.8);
        int newCellHeight = (int)(115 * 0.8);
        mainTable.setRowHeight(0, newCellHeight);
        mainTable.getColumnModel().getColumn(0).setPreferredWidth(newCellWidth);

        mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainTable.setBorder(new LineBorder(Color.WHITE));
        mainTable.setShowGrid(true);
        mainTable.setBackground(Color.decode("#006400"));
        mainTable.setGridColor(Color.BLACK);
		
		for (int row = 0; row < rows; row++) {
			int newHeight = (int) (115 * 0.8);
			mainTable.setRowHeight(row, newHeight);
		}
		for (int col = 0; col < columns; col++) {
			int newWidth = (int) (160 * 0.8);
			mainTable.getColumnModel().getColumn(col).setPreferredWidth(newWidth);
		}

/* --------------------------- Character Table Model --------------------------- */
		
//		characterTableModel = new DefaultTableModel(1, 6);
//        characterTable = new JTable(characterTableModel);
//	
//		for (int col = 0; col < columns; col++) {
//			int newWidth = (int) (160 * 0.8);
//			characterTable.getColumnModel().getColumn(col).setPreferredWidth(newWidth);
//		}		
		
/* --------------------------- Set Image in Table --------------------------- */

		character = new Character(this);
		character.adam(1, 1);

/* --------------------------- Set Add Table in panel --------------------------- */

        setBorder(BorderFactory.createEmptyBorder(90, 0, 0, 0));
        add(mainTable, BorderLayout.CENTER);
//		add(characterTable, BorderLayout.NORTH);

/* -------------------------------------------------------------------------- */

        mainTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainTable.setBorder(new LineBorder(Color.BLUE));

                new Thread(() -> {
                    try {
                        Thread.sleep(200);
                        mainTable.setBorder(new LineBorder(Color.WHITE));
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });
	}

    public int getTileSize() {
        return tileSize;
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    public void setGameTable(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    public JTable getMainTable() {
        return mainTable;
    }

    public DefaultTableModel getMainTableModel() {
        return mainTableModel;
    }

    public JTable getCharacterTable() {
        return characterTable;
    }

    public DefaultTableModel getCharacterTableModel() {
        return characterTableModel;
    }
}

