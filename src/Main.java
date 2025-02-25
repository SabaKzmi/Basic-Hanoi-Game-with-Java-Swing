import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static JLabel disk1 = new JLabel();
    public static JLabel disk2 = new JLabel();
    public static JLabel disk3 = new JLabel();
    public static JLabel disk4 = new JLabel();
    public static JLabel disk5 = new JLabel();

    public static void main(String[] args) {

        Rods a = new Rods(1, 2, 3, 4, 5);
        Rods b = new Rods(0, 0, 0, 0, 0);
        Rods c = new Rods(0, 0, 0, 0, 0);

        //graphics

        //rules
        JOptionPane.showMessageDialog(null, "Move the tower to rode C.\nOnly one disk can be moved at a time.\nEach move consists of taking the upper disk from one of the stacks and placing it on top of another stack.\nNo larger disk may be placed on top of a smaller disk.", "Rules", JOptionPane.PLAIN_MESSAGE);

        // Frame
        JFrame frame = new JFrame();
        frame.setSize(910, 610);
        frame.setTitle("Hanoi Tower");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(43, 45, 43));
        frame.setLayout(null);
        frame.setVisible(true);
        // A B C rod texts
        {
        JLabel A = new JLabel("A");
        A.setBounds(153, 390, 80, 80);
        A.setForeground(Color.BLACK);
        A.setFont(new Font("Serif", Font.PLAIN, 30));
        frame.add(A);

        JLabel B = new JLabel("B");
        B.setBounds(440, 390, 80, 80);
        B.setForeground(Color.BLACK);
        B.setFont(new Font("Serif", Font.PLAIN, 30));
        frame.add(B);

        JLabel C = new JLabel("C");
        C.setBounds(747, 390, 80, 80);
        C.setForeground(Color.BLACK);
        C.setFont(new Font("Serif", Font.PLAIN, 30));
        frame.add(C);
        }
        //Image icons
        {
            ImageIcon disk1Icon = new ImageIcon("disk1.png");
            disk1.setIcon(disk1Icon);
            disk1.setBounds(15, 325, 300, 100);
            frame.add(disk1);
            ImageIcon disk2Icon = new ImageIcon("disk2.png");
            disk2.setIcon(disk2Icon);
            disk2.setBounds(15, 275, 300, 100);
            frame.add(disk2);
            ImageIcon disk3Icon = new ImageIcon("disk3.png");
            disk3.setIcon(disk3Icon);
            disk3.setBounds(15, 220, 300, 100);
            frame.add(disk3);
            ImageIcon disk4Icon = new ImageIcon("disk4.png");
            disk4.setIcon(disk4Icon);
            disk4.setBounds(15, 170, 300, 100);
            frame.add(disk4);
            ImageIcon disk5Icon = new ImageIcon("disk5.png");
            disk5.setIcon(disk5Icon);
            disk5.setBounds(15, 120, 300, 100);
            frame.add(disk5);
            ImageIcon rodsIcon = new ImageIcon("rods.png");
            JLabel rods = new JLabel(rodsIcon);
            rods.setBounds(0, 0, 900, 600);
            frame.add(rods);
        }
        //text
        {
        JLabel text = new JLabel("From rod                to rod");
        text.setBounds(200, 490, 600, 50);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Serif", Font.PLAIN, 30));
        frame.add(text);}
        //text field From
        JTextField fromField = new JTextField();
        fromField.setBounds(330,505,80,30);
        fromField.setBackground(Color.GRAY);
        frame.add(fromField);
        //text field To
        JTextField toField = new JTextField();
        toField.setBounds(520,505,80,30);
        toField.setBackground(Color.GRAY);
        frame.add(toField);
        //go button
        {
        JButton goButton = new JButton("GO");
        goButton.setBounds(650, 500, 100, 40);
        goButton.setBackground(new Color(154, 180, 138));
        goButton.setForeground(Color.BLACK);
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromTxt = fromField.getText();
                Rods fromRod = new Rods(0,0,0,0,0);
                switch (fromTxt.toUpperCase()){
                    case "A" : fromRod = a; break;
                    case "B" : fromRod = b; break;
                    case "C" : fromRod = c; break;
                }
                String toTxt = toField.getText();
                Rods toRod = new Rods(0,0,0,0,0);

                boolean noError = true;
                //invalid rod name error
                if (!(fromTxt.equalsIgnoreCase("a") || fromTxt.equalsIgnoreCase("b") || fromTxt.equalsIgnoreCase("c"))
                    || !(toTxt.equalsIgnoreCase("a") || toTxt.equalsIgnoreCase("b") || toTxt.equalsIgnoreCase("c"))){
                    JOptionPane.showMessageDialog(null,"Invalid rod names", "Error", JOptionPane.ERROR_MESSAGE);
                    noError = false;
                }

                //from-rod and to-rod
                if (fromTxt.equalsIgnoreCase(toTxt)){
                    JOptionPane.showMessageDialog(null,"Rod names cant be the same", "Error", JOptionPane.ERROR_MESSAGE);
                    noError = false;
                }

                switch (toTxt.toUpperCase()){
                    case "A" : toRod = a; break;
                    case "B" : toRod = b; break;
                    case "C" : toRod = c; break;
                }

                int fromSlot = fromRod.findTopSlot();
                int movingDisk = 0;
                switch (fromSlot){
                    case 0: JOptionPane.showMessageDialog(null,"The from-rod is empty", "error", JOptionPane.ERROR_MESSAGE); break;
                    case 1: movingDisk = fromRod.getSlot1(); break;
                    case 2: movingDisk = fromRod.getSlot2(); break;
                    case 3: movingDisk = fromRod.getSlot3(); break;
                    case 4: movingDisk = fromRod.getSlot4(); break;
                    case 5: movingDisk = fromRod.getSlot5(); break;
                }
                int toSlot = toRod.findTopSlot() + 1;

                boolean executed = false;
                if (noError){
                    executed = moveDisk(fromRod, toRod);}
                //move disk graphics
                if (executed && noError){
                    switch (movingDisk){
                        case 5:
                            if (toSlot == 1 && toTxt.equalsIgnoreCase("A")){
                                disk5.setBounds(15, 325, 300, 100);
                            }
                            else if (toSlot == 1 && toTxt.equalsIgnoreCase("B")){
                                disk5.setBounds(300, 325, 300, 100);
                            }
                            else if (toSlot == 1 && toTxt.equalsIgnoreCase("C")){
                                disk5.setBounds(605, 325, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("A")){
                                disk5.setBounds(15, 275, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("B")){
                                disk5.setBounds(300, 275, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("C")){
                                disk5.setBounds(605, 275, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("A")){
                                disk5.setBounds(15, 220, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("B")){
                                disk5.setBounds(300, 220, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("C")){
                                disk5.setBounds(605, 220, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("A")){
                                disk5.setBounds(15, 170, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("B")){
                                disk5.setBounds(300, 170, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("C")){
                                disk5.setBounds(605, 170, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("A")){
                                disk5.setBounds(15, 120, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("B")){
                                disk5.setBounds(300, 120, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("C")){
                                disk5.setBounds(605, 120, 300, 100);
                            } break;
                        case 4:
                            if (toSlot == 1 && toTxt.equalsIgnoreCase("A")){
                                disk4.setBounds(15, 325, 300, 100);
                            }
                            else if (toSlot == 1 && toTxt.equalsIgnoreCase("B")){
                                disk4.setBounds(300, 325, 300, 100);
                            }
                            else if (toSlot == 1 && toTxt.equalsIgnoreCase("C")){
                                disk4.setBounds(605, 325, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("A")){
                                disk4.setBounds(15, 275, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("B")){
                                disk4.setBounds(300, 275, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("C")){
                                disk4.setBounds(605, 275, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("A")){
                                disk4.setBounds(15, 220, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("B")){
                                disk4.setBounds(300, 220, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("C")){
                                disk4.setBounds(605, 220, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("A")){
                                disk4.setBounds(15, 170, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("B")){
                                disk4.setBounds(300, 170, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("C")){
                                disk4.setBounds(605, 170, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("A")){
                                disk4.setBounds(15, 120, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("B")){
                                disk4.setBounds(300, 120, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("C")){
                                disk4.setBounds(605, 120, 300, 100);
                            } break;
                        case 3:
                            if (toSlot == 1 && toTxt.equalsIgnoreCase("A")){
                                disk3.setBounds(15, 325, 300, 100);
                            }
                            else if (toSlot == 1 && toTxt.equalsIgnoreCase("B")){
                                disk3.setBounds(300, 325, 300, 100);
                            }
                            else if (toSlot == 1 && toTxt.equalsIgnoreCase("C")){
                                disk3.setBounds(605, 325, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("A")){
                                disk3.setBounds(15, 275, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("B")){
                                disk3.setBounds(300, 275, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("C")){
                                disk3.setBounds(605, 275, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("A")){
                                disk3.setBounds(15, 220, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("B")){
                                disk3.setBounds(300, 220, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("C")){
                                disk3.setBounds(605, 220, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("A")){
                                disk3.setBounds(15, 170, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("B")){
                                disk3.setBounds(300, 170, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("C")){
                                disk3.setBounds(605, 170, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("A")){
                                disk3.setBounds(15, 120, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("B")){
                                disk3.setBounds(300, 120, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("C")){
                                disk3.setBounds(605, 120, 300, 100);
                            } break;
                        case 2:
                            if (toSlot == 1 && toTxt.equalsIgnoreCase("A")){
                                disk2.setBounds(15, 325, 300, 100);
                            }
                            else if (toSlot == 1 && toTxt.equalsIgnoreCase("B")){
                                disk2.setBounds(300, 325, 300, 100);
                            }
                            else if (toSlot == 1 && toTxt.equalsIgnoreCase("C")){
                                disk2.setBounds(605, 325, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("A")){
                                disk2.setBounds(15, 275, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("B")){
                                disk2.setBounds(300, 275, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("C")){
                                disk2.setBounds(605, 275, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("A")){
                                disk2.setBounds(15, 220, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("B")){
                                disk2.setBounds(300, 220, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("C")){
                                disk2.setBounds(605, 220, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("A")){
                                disk2.setBounds(15, 170, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("B")){
                                disk2.setBounds(300, 170, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("C")){
                                disk2.setBounds(605, 170, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("A")){
                                disk2.setBounds(15, 120, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("B")){
                                disk2.setBounds(300, 120, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("C")){
                                disk2.setBounds(605, 120, 300, 100);
                            } break;
                        case 1:
                            if (toSlot == 1 && toTxt.equalsIgnoreCase("A")){
                                disk1.setBounds(15, 325, 300, 100);
                            }
                            else if (toSlot == 1 && toTxt.equalsIgnoreCase("B")){
                                disk1.setBounds(300, 325, 300, 100);
                            }
                            else if (toSlot == 1 && toTxt.equalsIgnoreCase("C")){
                                disk1.setBounds(605, 325, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("A")){
                                disk1.setBounds(15, 275, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("B")){
                                disk1.setBounds(300, 275, 300, 100);
                            }
                            else if (toSlot == 2 && toTxt.equalsIgnoreCase("C")){
                                disk1.setBounds(605, 275, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("A")){
                                disk1.setBounds(15, 220, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("B")){
                                disk1.setBounds(300, 220, 300, 100);
                            }
                            else if (toSlot == 3 && toTxt.equalsIgnoreCase("C")){
                                disk1.setBounds(605, 220, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("A")){
                                disk1.setBounds(15, 170, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("B")){
                                disk1.setBounds(300, 170, 300, 100);
                            }
                            else if (toSlot == 4 && toTxt.equalsIgnoreCase("C")){
                                disk1.setBounds(605, 170, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("A")){
                                disk1.setBounds(15, 120, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("B")){
                                disk1.setBounds(300, 120, 300, 100);
                            }
                            else if (toSlot == 5 && toTxt.equalsIgnoreCase("C")){
                                disk1.setBounds(605, 120, 300, 100);
                            } break;
                    }
                }

                if (c.getSlot1() == 1 && c.getSlot2() == 2 && c.getSlot3() == 3 && c.getSlot4() == 4 && c.getSlot5() == 5) {
                    JOptionPane.showMessageDialog(null,"Yay! you won!", "Congratulations", JOptionPane.PLAIN_MESSAGE);
                    resetA(a);
                    resetZero(b);
                    resetZero(c);
                }
            }
        });
        frame.add(goButton);
        }

    }

    public static boolean moveDisk(Rods from, Rods to){
        int Disk = 0; //size of the disk that is being moved
        int toDisk = 0; //size of the top disk on the toRod

        boolean executed = true;

        // finding disk size number
        // sets the disk size as 0 if the rod is empty
        switch (from.findTopSlot()){
            case 0:
                Disk = 0;
                executed = false; break;
            case 1:
                Disk = from.getSlot1(); break;
            case 2:
                Disk = from.getSlot2(); break;
            case 3:
                Disk = from.getSlot3(); break;
            case 4:
                Disk = from.getSlot4(); break;
            case 5:
                Disk = from.getSlot5(); break;
        }

        //finding to top disk number
        switch (to.findTopSlot()){
            case 0:
                toDisk = 0; break;
            case 1:
                toDisk = to.getSlot1(); break;
            case 2:
                toDisk = to.getSlot2(); break;
            case 3:
                toDisk = to.getSlot3(); break;
            case 4:
                toDisk = to.getSlot4(); break;
            case 5:
                toDisk = to.getSlot5(); break;
        }

        if (Disk > toDisk){

            //place the disk in its new slot
            switch (to.findTopSlot() + 1){
                case 1:
                    to.setSlot1(Disk); break;
                case 2:
                    to.setSlot2(Disk); break;
                case 3:
                    to.setSlot3(Disk); break;
                case 4:
                    to.setSlot4(Disk); break;
                case 5:
                    to.setSlot5(Disk); break;
            }

            //remove the disk from its previous slot
            switch (from.findTopSlot()){
                case 1:
                    from.setSlot1(0); break;
                case 2:
                    from.setSlot2(0); break;
                case 3:
                    from.setSlot3(0); break;
                case 4:
                    from.setSlot4(0); break;
                case 5:
                    from.setSlot5(0); break;
            }
        }
        else if (Disk < toDisk){
            JOptionPane.showMessageDialog(null,"You can't put a bigger disk on an smaller one", "Error", JOptionPane.ERROR_MESSAGE);
            executed = false;
        }
        return executed;
    }

    //reset zero methods
    public static void resetZero(Rods b){
        b.setSlot1(0);
        b.setSlot2(0);
        b.setSlot3(0);
        b.setSlot4(0);
        b.setSlot5(0);
    }
    public static void resetA(Rods a){
        a.setSlot1(1);
        a.setSlot2(2);
        a.setSlot3(3);
        a.setSlot4(4);
        a.setSlot5(5);
    }

}
