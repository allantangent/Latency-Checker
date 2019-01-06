
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.*;

public class gui
{
    private BorderLayout layout;
    private JButton b1;
    private JLabel l1, title;
    private JFrame f;
    
    public gui()
    {
        initStuff();
        Container content = f.getContentPane();
        content.setLayout( layout );
        content.add( b1, BorderLayout.PAGE_END );
        content.add( l1, BorderLayout.CENTER );
        content.add( title, BorderLayout.PAGE_START );
        
        title.setFont( new Font( "Courier New", Font.BOLD, 16 ) );
        
        //frame settings
        f.setTitle( "Allan's Latency Checker" );
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setPreferredSize( new Dimension( 420, 420 ) );
        f.setVisible( true );
        f.pack();
        f.setLocationRelativeTo( null );
    }
    
    private void initStuff()
    {
        layout = new BorderLayout();
        f = new JFrame();
        b1 = new JButton( "check" );
        l1 = new JLabel();
        l1.setVerticalAlignment( JLabel.CENTER );
        l1.setHorizontalAlignment( JLabel.CENTER );
        title = new JLabel( "Ping Checker" );
        title.setVerticalAlignment( JLabel.CENTER );
        title.setHorizontalAlignment( JLabel.CENTER );
        
        b1.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent ae ) {
                try {
                    Process p = Runtime.getRuntime().exec( "ping 104.160.131.3" );
                    BufferedReader inputStream = new BufferedReader(
                                    new InputStreamReader( p.getInputStream() ) );

                    String s = "";
                    String output = "";
                    // reading output stream of the command
                    while ( ( s = inputStream.readLine() ) != null) {
                        output += s + "<br>";
                        l1.setText( "<html>" + output + "</html>" );
                    }

                } catch ( Exception e ) {
                    l1.setText( "Internet busted. Check connection" );
                }
            }
        });
    }
}