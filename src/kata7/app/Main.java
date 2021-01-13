package kata7.app;

import kata7.control.Command;
import kata7.control.DownCommand;
import kata7.control.LeftCommand;
import kata7.control.RightCommand;
import kata7.control.UpCommand;
import kata7.model.Block;
import kata7.view.BlockDisplay;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import kata7.control.BlockPresenter;

public class Main extends JFrame {
    public static void main(String[] args) {
	    new Main().exectute();
    }
    private BlockDisplay blockDisplay;
    private Map<String, Command> commands = new HashMap<>();

    public Main(){
        this.setTitle("Block shifter");
        this.setSize(700,762);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
        
        Block block = new Block(4,4);
        BlockPresenter blockPresenter = new BlockPresenter(block, blockDisplay);
        this.commands.put("left",new LeftCommand(block));
        this.commands.put("right",new RightCommand(block));
        this.commands.put("up",new UpCommand(block));
        this.commands.put("down",new DownCommand(block));
    }
    
    private void exectute() {
        this.setVisible(true);
    }
    
    private JPanel blockPanel() {
        BlockPanel blockpanel = new BlockPanel(Block.MAX);
        this.blockDisplay = blockpanel;
        return blockpanel;
    }

    private JMenuBar toolbar() {
        JMenuBar menubar = new JMenuBar();
        menubar.add(button("left"));
        menubar.add(button("up"));
        menubar.add(button("down"));
        menubar.add(button("right"));
        return menubar;
    }

    private JButton button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(name).execute();
            }
        });
        return button;
    }
    
}