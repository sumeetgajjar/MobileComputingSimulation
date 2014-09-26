import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Contents extends JPanel {
	
	private JButton adduser;
	private JButton addcell;
        
	private static int xc=100;
	private static int yc=100;
	private static int position=0;
	private static boolean cellflag=false;
	
	private static ArrayList<Cell> cells;
	static boolean a[][];
	
        public Contents(){
		super();
		cells=new ArrayList<Cell>();
		initialize();
	}
	
	public void initialize(){
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		System.out.println(width);
		System.out.println(height);
		xc=width/2;
		yc=height/2;
		
		generateCenter();
		
		adduser=new JButton("ADD USER");
		addcell=new JButton("ADD CELL");
		addcell.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
                cellflag=true;
            	repaint();
            }
        });
		add(adduser);
		add(addcell);
	}
	
	protected void paintComponent(Graphics g){
		if(cellflag){
			drawCell(g);
		}
	}
	
	private void drawCell(Graphics g){

            Cell temp=cells.get(position);
            xc=temp.centerx;
            yc=temp.centery;
            Polygon p = new Polygon();
            for (int i = 0; i < 6; i++){
              p.addPoint((int) (xc + Cell.radius * Math.cos(i * 2 * Math.PI / 6)),
                  (int) (yc + Cell.radius * Math.sin(i * 2 * Math.PI / 6)));
            }
            g.drawPolygon(p);
            System.out.println(""+position);        
            position=(position+1)%7;
            cellflag=false;
	}
	
	public void generateCenter(){
            Cell temp=new Cell();
            temp.centerx=xc;
            temp.centery=yc;
            cells.add(temp);
            
            int radius=(int)Math.round(Cell.radius*Math.sqrt(3));
            for (int i = 0; i < 6; i++){
                temp=new Cell();
                temp.centerx=(int) (xc + radius * Math.cos(i * 2 * Math.PI / 6 + Math.PI / 6));
                temp.centery=(int) (yc + radius * Math.sin(i * 2 * Math.PI / 6 + Math.PI / 6));
                cells.add(temp);
            }
            System.out.println(""+cells.size());
	}   
}			
