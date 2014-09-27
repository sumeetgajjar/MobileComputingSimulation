package MCProj;

import java.awt.Color;
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
        private JButton allocateFreq;
        
	private static int xc=100;
	private static int yc=100;
	private static int position=0;
	private static boolean cellflag=false;
	
	private static ArrayList<Cell> cells;
	static boolean a[][];
        static boolean frequency;
        static boolean cellFreq[];
        static Graph y;
        
        public Contents(){
		super();
		cells=new ArrayList<Cell>();
                y=new Graph();
                cellFreq=new boolean[7];
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
                allocateFreq=new JButton("ALLOCATE FREQ");
                
		addcell.addActionListener(new ActionListener() {
			 
                public void actionPerformed(ActionEvent e)
                {
                    cellflag=true;
                    repaint();
                }});
                
                allocateFreq.addActionListener(new ActionListener() {
                    
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    int counter=0;
                    for (int i = 0; i < cellFreq.length; i++) {
                        if(cellFreq[i])
                            counter++;
                        else
                            break;
                    }
                    freqReuse(counter);                    
                }});
                
		add(adduser);
		add(addcell);
                add(allocateFreq);
	}
	
        void freqReuse(int counter)
        {
            y=new Graph();
            y.countCells=counter;
            y.graphcolor(0);
            
            if(!y.solnFound)
                System.out.println("No solution exists");
            else
            {
                frequency=true;
                repaint();
            }
        }
        static void selectColour(Graphics g,int i)
        {
            int col=Graph.x[i];
            switch(col)
            {
                case 0:
                    g.setColor(Color.red);
                    break;
                case 1:
                    g.setColor(Color.green);
                    break;
                case 2:
                    g.setColor(Color.yellow);
                    break;
            }
        }
        @Override
	protected void paintComponent(Graphics g){
		if(cellflag)
                {
                    drawCell(g);
                    cellflag=false;
                }
                if(frequency)
                {
                    g.clearRect(0, 0, getWidth(), getHeight());
                    
                    for (int i = 0; i < 7; i++)
                    {
                        if(cellFreq[i])
                        {
                            Cell temp=cells.get(i);
                            xc=temp.centerx;
                            yc=temp.centery;
                            Polygon p = new Polygon();

                            //six vertices of Polygon for the given ith position
                            for (int j = 0; j < 6; j++)
                            {
                              p.addPoint((int) (xc + Cell.radius * Math.cos(j * 2 * Math.PI / 6)),
                                  (int) (yc + Cell.radius * Math.sin(j * 2 * Math.PI / 6)));
                            }

                            selectColour(g,i);
                            g.fillPolygon(p);
                        }
                        else
                            break;
                    }
                    
                    frequency=false;
                }
	}
	
	private void drawCell(Graphics g){

            g.clearRect(0, 0, getWidth(), 40);
            
            if(position==7)
                position=0;
            
            Cell temp=cells.get(position);
            xc=temp.centerx;
            yc=temp.centery;
            Polygon p = new Polygon();
            
            //six vertices of Polygon for the given position
            for (int i = 0; i < 6; i++)
            {
              p.addPoint((int) (xc + Cell.radius * Math.cos(i * 2 * Math.PI / 6)),
                  (int) (yc + Cell.radius * Math.sin(i * 2 * Math.PI / 6)));
            }
            g.drawPolygon(p);
            cellFreq[position]=true;
            position++;
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
