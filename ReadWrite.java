import java.awt.*; 
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.*; 

public class ReadWrite extends JFrame implements ActionListener{
    JButton save, show;
    JTextField text[];
    JLabel textLabel[];
    JTextArea area;
    JScrollPane scroll;
    Container c;
    String data[][];
    int length = 0;

    public ReadWrite(){
        super("Student Data");
        text = new JTextField[6];
        textLabel = new JLabel[6];
        inti();
    }

    public void inti(){
        c = getContentPane(); 
        c.setLayout(new FlowLayout()); 
        textLabel[0] = new JLabel("Student ID");
        textLabel[1] = new JLabel("Name");
        textLabel[2] = new JLabel("Last Name");
        textLabel[3] = new JLabel("Branch");
        textLabel[4] = new JLabel("Department");
        textLabel[5] = new JLabel("Group");
        text[0] = new JTextField(10);
        text[1] = new JTextField(10);
        text[2] = new JTextField(10);
        text[3] = new JTextField(10);
        text[4] = new JTextField(10);
        text[5] = new JTextField(10);
        c.add(textLabel[0]);
        c.add(text[0]);
        c.add(textLabel[1]);
        c.add(text[1]);
        c.add(textLabel[2]);
        c.add(text[2]);
        c.add(textLabel[3]);
        c.add(text[3]);
        c.add(textLabel[4]);
        c.add(text[4]);
        c.add(textLabel[5]);
        c.add(text[5]);

        save = new JButton("Submit");
        save.addActionListener(this);
        c.add(save);
        show = new JButton("Show");
        show.addActionListener(this);
        c.add(show);
        area = new JTextArea(8,30); 
        area.setEditable(false); 
        scroll = new JScrollPane(area); 
        c.add(scroll);
        setSize(400,300); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setVisible(true); 
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == save){
            dataToArray();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));
                for(int i = 0;i < length;i++){
                    writer.write(data[i][0] + ";" +data[i][1] + ";" + data[i][2] + ";" + data[i][3] + ";" + data[i][4] + ";" + data[i][5] + "\n");
                }
                writer.write(text[0].getText() + ";" + text[1].getText() + ";" + text[2].getText() + ";" + text[3].getText() + ";" + text[4].getText() + ";" + text[5].getText());
                writer.close();
            } catch (Exception e) {
            }
            length = 0;
            text[0].setText("");
            text[1].setText("");
            text[2].setText("");
            text[3].setText("");
            text[4].setText("");
            text[5].setText("");
        }
        if(event.getSource() == show){
            dataToArray();
            String msg = "";
            for(int i = 0;i < length;i++){
                msg += data[i][0] + " " +data[i][1] + " " + data[i][2] + " " + data[i][3] + " " + data[i][4] + " " + data[i][5] + "\n";
            }
            area.setText(msg);
            length = 0;
        }

    }

    public static void main(String[] args) {
        ReadWrite test = new ReadWrite();
    }

    public void dataToArray(){
        data = new String[1000][6];
        int i = 0;
        try {
            File read = new File("data.txt");
            Scanner readFile = new Scanner(read);
            while(readFile.hasNext()){
                String Data = readFile.nextLine();
                String tmp[] = Data.split(";");
                data[i][0] = tmp[0];
                data[i][1] = tmp[1];
                data[i][2] = tmp[2];
                data[i][3] = tmp[3];
                data[i][4] = tmp[4];
                data[i][5] = tmp[5];
                i++;
                length++;
            }
            
        } catch (Exception e) {

        }
    }
}