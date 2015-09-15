package eatr;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel {
	protected JButton startButton;
    protected JButton stopButton;
    

    protected JPanel panel;

    protected runEnv thread;
    protected JPanel dPanel;

    public ButtonPanel() {
        this.thread = null;
        createControl();
    }

    protected void createControl() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (thread != null) {
                    thread.stopRunning();
                }
                //thread = new runEnv(dPanel);
                thread.start();
            }
        });

        panel.add(startButton);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (thread != null) {
                    thread.stopRunning();
                    thread = null;
                }
            }
        });

        panel.add(stopButton);

        //setButtonSizes(startButton, stopButton);
    }
//
//    protected void setButtonSizes(JButton ... buttons) {
//        Dimension preferredSize = new Dimension();
//        for (JButton button : buttons) {
//            Dimension d = button.getPreferredSize();
//            preferredSize = setLarger(preferredSize, d);
//        }
//        for (JButton button : buttons) {
//            button.setPreferredSize(preferredSize);
//        }
//    }
//
//    protected Dimension setLarger(Dimension a, Dimension b) {
//        Dimension d = new Dimension();
//        d.height = Math.max(a.height, b.height);
//        d.width = Math.max(a.width, b.width);
//        return d;
//    }
//
//    public void setTrafficSignalPanel(TrafficSignalPanel tsPanel) {
//        this.tsPanel = tsPanel;
//    }
//
//    public JPanel getPanel() {
//        return panel;
//    }


}
