import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Main {
			
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Started");
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	Simulator x=new Simulator();
            	Contents content=new Contents();
            	
            	JPanel app=new JPanel();
            	app.setLayout(new BoxLayout(app, BoxLayout.Y_AXIS));
            	app.add(content);
            	
            	x.add(app);
            	x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		x.setSize(400,400);
            	x.setExtendedState(JFrame.MAXIMIZED_BOTH);
        		x.setVisible(true);
            }
        });	
				
		System.out.println("Done");
	}

}
