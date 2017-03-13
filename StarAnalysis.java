//Alex Hernandez
//StarAnalysis.java
//COM 204
//12 May 2014

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class StarAnalysis {
  
  public static void main(String[] args)
  {
    //Run program
    setMenuGUI();
  }

  public static void setMenuGUI()
  {
    //GUI window
    JFrame menu = new JFrame("Star Analysis Program");
    
    //GUI buttons
    JButton create = new JButton("Load Star");
    
    //GUI input fields
    final JTextField temp = new JTextField(20);
    final JTextField size = new JTextField(20);
    final JTextField radius = new JTextField(20);
    
    //GUI labels and icons
    ImageIcon icon = new ImageIcon("star.gif");
    JLabel star = new JLabel(icon);
    JLabel label1 = new JLabel();
    label1.setText("Study a Star");
    label1.setForeground(Color.WHITE);
    JLabel tempL = new JLabel();
    tempL.setText("Enter temperature (in Kelvin): ");
    tempL.setForeground(Color.WHITE);
    JLabel sizeL = new JLabel();
    sizeL.setText("Enter size (in Solar Masses): ");
    sizeL.setForeground(Color.WHITE);
    JLabel radiusL = new JLabel();
    radiusL.setText("Enter radius of star (in Solar Radii): ");
    radiusL.setForeground(Color.WHITE);
    JLabel madeBy = new JLabel();
    madeBy.setText("Made by: Alex Hernandez '17");
    madeBy.setForeground(Color.WHITE);
    
    //When load star button is clicked
    create.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        
        //Gets input from user
        String inputT = update(temp);
        String inputS = update(size);
        String inputR = update(radius);
        
        //Throws errors if input is empty, not of the right type, or is invalid as an input
        if(inputT.equals("") || inputS.equals("") || inputR.equals(""))
        {
          JOptionPane optionPane = new JOptionPane("Some input fields are empty!", JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Empty Field Error!");
          dialog.setVisible(true);
        }
        else if(isNotInt(inputT) == true || isNotDouble(inputS) == true || isNotDouble(inputR) == true)
        {
          JOptionPane optionPane = new JOptionPane("Some input fields are of wrong type! (int, double, double)", JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Wrong Type Error!");
          dialog.setVisible(true);
        }
        else if(Integer.parseInt(inputT) < 2400 || Integer.parseInt(inputT) > 60000)
        {
          JOptionPane optionPane = new JOptionPane("Temperature is off-limit! (Min:2400K Max:60000K)", JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Off Limit Error!");
          dialog.setVisible(true);
        }
        else if(Double.parseDouble(inputS) < 0 || Double.parseDouble(inputS) > 300)
        {
          JOptionPane optionPane = new JOptionPane("Solar mass is off-limit! (Min:>0M Max:300M)", JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Off Limit Error!");
          dialog.setVisible(true);
        }
        else if(Double.parseDouble(inputR) < 0 || Double.parseDouble(inputR) > 1800)
        {
          JOptionPane optionPane = new JOptionPane("Solar radius is off-limit! (Min:>0R Max:1800R)", JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Off Limit Error!");
          dialog.setVisible(true);
        }
        else
        {
          //If it does not find errors it will load the star with its information
          loadStar(Integer.parseInt(inputT), Double.parseDouble(inputS), Double.parseDouble(inputR));
        }
      }
    });
    
    //Creates menu window
    menu.pack();
    menu.setSize(400,400);
    menu.setResizable(false);
    menu.getContentPane().setBackground(Color.BLACK);
    menu.setLocationRelativeTo(null);
    menu.setVisible(true);
    
    //Add labels and icons
    menu.getContentPane().setLayout(null);
    create.setBounds(135,300,120,30);
    menu.getContentPane().add(create);
    label1.setBounds(125,30,200,30);
    label1.setFont(label1.getFont().deriveFont(22.0f));
    menu.add(label1);
    star.setBounds(200,85,200,200);
    menu.add(star);
    madeBy.setBounds(250,350,200,30);
    madeBy.setFont(label1.getFont().deriveFont(10.0f));
    menu.add(madeBy);
    
    //Add input fields with labels
    temp.setBounds(15,110,125,20);
    size.setBounds(15,170,125,20);
    radius.setBounds(15,230,125,20);
    menu.add(temp);
    menu.add(size);
    menu.add(radius);
    tempL.setBounds(15,90,200,20);
    sizeL.setBounds(15,150,200,20);
    radiusL.setBounds(15,210,200,20);
    menu.add(tempL);
    menu.add(sizeL);
    menu.add(radiusL);
    
  }
  
  public static void loadStar(int temp, double size, double radius)
  { 
    //GUI window
    JFrame menu = new JFrame("Star Analysis Program");
    
    //GUI buttons
    JButton compare = new JButton("Compare to Another Star");
    
    //Creates menu window
    menu.pack();
    menu.setSize(600,575);
    menu.setResizable(false);
    menu.getContentPane().setBackground(Color.BLACK);
    menu.setLocationRelativeTo(null);
    menu.setVisible(true);
    
    //Get class and icons for star
    JLabel classL = new JLabel();
    classL.setText("Class " + getClass(temp));
    String sClass = getClass(temp);
    
    //Star Label for type
    menu.getContentPane().setLayout(null);
    classL.setBounds(395,160,120,30);
    classL.setForeground(getTextColor(getClass(temp)));
    classL.setFont(classL.getFont().deriveFont(18.0f));
    menu.add(classL);
    
    //Star icon according to type
    ImageIcon icon = new ImageIcon(getStarGif(getClass(temp),radius));
    JLabel starL = new JLabel(icon);
    starL.setBounds(330,200,200,200);
    menu.add(starL);
    
    //Add Button
    menu.getContentPane().setLayout(null);
    compare.setBounds(200,430,200,30);
    menu.getContentPane().add(compare);
    
    final int temp1 = temp;
    final double size1 = size;
    final double radius1 = radius;
    
    //When compare button is clicked, it transfers the information
    //from 1st star to be compared to 2nd star
    compare.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        loadStar2(temp1, size1, radius1);
      }
    });
    
    JLabel titleL = new JLabel();
    titleL.setText("Star Information");
    titleL.setForeground(Color.WHITE);
    titleL.setFont(titleL.getFont().deriveFont(27.0f));
    titleL.setBounds(180,70,400,30);
    menu.add(titleL);
    JLabel infoL = new JLabel();
    infoL.setText("Stellar Analysis");
    infoL.setForeground(Color.WHITE);
    infoL.setFont(infoL.getFont().deriveFont(16.5f));
    infoL.setBounds(65,150,165,30);
    menu.add(infoL);

    //Equation Labels and Values
    //Luminosity
    JLabel lumL = new JLabel();
    lumL.setText("Luminosity");
    lumL.setForeground(Color.WHITE);
    lumL.setFont(lumL.getFont().deriveFont(14.0f));
    lumL.setBounds(15,200,120,30);
    menu.add(lumL);
    JLabel lumL2 = new JLabel();
    lumL2.setText("(4" + "\u03C0" + "R" + "\u00B2" + "\u03C3" + "T" + "\u2074" + "):");
    lumL2.setForeground(Color.WHITE);
    lumL2.setFont(lumL2.getFont().deriveFont(14.0f));
    lumL2.setBounds(95,200,120,30);
    menu.add(lumL2);
    JLabel lumL3 = new JLabel();
    lumL3.setText(getLuminosity(temp, radius));
    lumL3.setForeground(Color.WHITE);
    lumL3.setFont(lumL3.getFont().deriveFont(14.0f));
    lumL3.setBounds(175,200,120,30);
    menu.add(lumL3);
    
    //Peak Wavelength
    JLabel waveL = new JLabel();
    waveL.setText("Peak Wavelength");
    waveL.setForeground(Color.WHITE);
    waveL.setFont(waveL.getFont().deriveFont(14.0f));
    waveL.setBounds(15,240,150,30);
    menu.add(waveL);
    JLabel waveL2 = new JLabel();
    waveL2.setText("(" + "\u039B" + "max" + "):");
    waveL2.setForeground(Color.WHITE);
    waveL2.setFont(waveL2.getFont().deriveFont(14.0f));
    waveL2.setBounds(145,240,120,30);
    menu.add(waveL2);
    JLabel waveL3 = new JLabel();
    waveL3.setText(getPeak(temp));
    waveL3.setForeground(Color.WHITE);
    waveL3.setFont(waveL3.getFont().deriveFont(14.0f));
    waveL3.setBounds(205,240,120,30);
    menu.add(waveL3);
    
    //Hydrogen Fusion Rate
    JLabel fusL = new JLabel();
    fusL.setText("H Fusion Rate:");
    fusL.setForeground(Color.WHITE);
    fusL.setFont(fusL.getFont().deriveFont(14.0f));
    fusL.setBounds(15,280,150,30);
    menu.add(fusL);
    JLabel fusL2 = new JLabel();
    fusL2.setText(getFusionRate(temp, radius));;
    fusL2.setForeground(Color.WHITE);
    fusL2.setFont(fusL2.getFont().deriveFont(14.0f));
    fusL2.setBounds(125,280,120,30);
    menu.add(fusL2);
    
    //Spectral Characteristics
    JLabel specL = new JLabel();
    specL.setText("Spectral Lines:");
    specL.setForeground(Color.WHITE);
    specL.setFont(specL.getFont().deriveFont(14.0f));
    specL.setBounds(15,320,150,30);
    menu.add(specL);
    JLabel specL2 = new JLabel();
    specL2.setText(getSpectra(sClass, radius));
    specL2.setForeground(Color.WHITE);
    specL2.setFont(specL2.getFont().deriveFont(14.0f));
    specL2.setBounds(130,320,200,30);
    menu.add(specL2);
    
  }
  
  public static void loadStar2(int temp, double size, double radius)
  {
    //GUI window
    JFrame menu = new JFrame("Star 2 Input");
    
    //GUI buttons
    JButton compare = new JButton("Compare Stars");
    
    //GUI input fields
    final JTextField temp2 = new JTextField(20);
    final JTextField size2 = new JTextField(20);
    final JTextField radius2 = new JTextField(20);
    
    //GUI labels and icons
    JLabel label1 = new JLabel();
    label1.setText("Enter Values for Star 2");
    label1.setForeground(Color.WHITE);
    JLabel tempL = new JLabel();
    tempL.setText("Enter temperature (in Kelvin): ");
    tempL.setForeground(Color.WHITE);
    JLabel sizeL = new JLabel();
    sizeL.setText("Enter size (in Solar Masses): ");
    sizeL.setForeground(Color.WHITE);
    JLabel radiusL = new JLabel();
    radiusL.setText("Enter radius of star (in Solar Radii): ");
    radiusL.setForeground(Color.WHITE);
    JLabel madeBy = new JLabel();
    
    final int temp1 = temp;
    final double size1 = size;
    final double radius1 = radius;
    
    //When compare button is clicked
    compare.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        
        //Gets input from user
        String inputT = update(temp2);
        String inputS = update(size2);
        String inputR = update(radius2);
        
        //Throws errors if input is empty, not of the right type, or is invalid as an input
        if(inputT.equals("") || inputS.equals("") || inputR.equals(""))
        {
          JOptionPane optionPane = new JOptionPane("Some input fields are empty!", JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Empty Field Error!");
          dialog.setVisible(true);
        }
        else if(isNotInt(inputT) == true || isNotDouble(inputS) == true || isNotDouble(inputR) == true)
        {
          JOptionPane optionPane = new JOptionPane("Some input fields are of wrong type! (int, double, double)", JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Wrong Type Error!");
          dialog.setVisible(true);
        }
        else if(Integer.parseInt(inputT) < 2400 || Integer.parseInt(inputT) > 60000)
        {
          JOptionPane optionPane = new JOptionPane("Temperature is off-limit! (Min:2400K Max:60000K)", JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Off Limit Error!");
          dialog.setVisible(true);
        }
        else if(Double.parseDouble(inputS) < 0 || Double.parseDouble(inputS) > 300)
        {
          JOptionPane optionPane = new JOptionPane("Solar mass is off-limit! (Min:>0M Max:300M)", JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Off Limit Error!");
          dialog.setVisible(true);
        }
        else if(Double.parseDouble(inputR) < 0 || Double.parseDouble(inputR) > 1800)
        {
          JOptionPane optionPane = new JOptionPane("Solar radius is off-limit! (Min:>0R Max:1800R)", JOptionPane.ERROR_MESSAGE);    
          JDialog dialog = optionPane.createDialog("Off Limit Error!");
          dialog.setVisible(true);
        }
        else
        {
          //If it does not find errors it will compare the 1st and 2nd star
          compareStars
            (temp1, size1, radius1, Integer.parseInt(inputT), Double.parseDouble(inputS), Double.parseDouble(inputR));
        }
      }
    });
    
    //Creates menu window
    menu.pack();
    menu.setSize(400,400);
    menu.setResizable(false);
    menu.getContentPane().setBackground(Color.BLACK);
    menu.setLocationRelativeTo(null);
    menu.setVisible(true);
    
    //Add labels and icons
    menu.getContentPane().setLayout(null);
    compare.setBounds(105,300,200,30);
    menu.getContentPane().add(compare);
    label1.setBounds(90,30,300,30);
    label1.setFont(label1.getFont().deriveFont(22.0f));
    menu.add(label1);
    madeBy.setBounds(250,350,200,30);
    madeBy.setFont(label1.getFont().deriveFont(10.0f));
    menu.add(madeBy);
    
    //Add input fields with labels
    temp2.setBounds(15,110,125,20);
    size2.setBounds(15,170,125,20);
    radius2.setBounds(15,230,125,20);
    menu.add(temp2);
    menu.add(size2);
    menu.add(radius2);
    tempL.setBounds(15,90,200,20);
    sizeL.setBounds(15,150,200,20);
    radiusL.setBounds(15,210,200,20);
    menu.add(tempL);
    menu.add(sizeL);
    menu.add(radiusL);
    
  }
  
  public static void compareStars(int temp, double size, double radius, int temp2, double size2, double radius2)
  {
    //GUI window
    JFrame menu = new JFrame("Star Comparison");
    menu.getContentPane().setLayout(null);
    
    //Creates menu window
    menu.pack();
    menu.setSize(600,530);
    menu.setResizable(false);
    menu.getContentPane().setBackground(Color.BLACK);
    menu.setLocationRelativeTo(null);
    menu.setVisible(true);
    
    //GUI labels and icons
    JLabel label1 = new JLabel();
    label1.setText("Comparitive Analysis of Both Stars");
    label1.setForeground(Color.WHITE);
    label1.setFont(label1.getFont().deriveFont(23.0f));
    label1.setBounds(100,70,450,30);
    menu.add(label1);

    //Equation Labels and Values
    //Luminosity Comparison
    JLabel lumL = new JLabel();
    lumL.setText("Luminosity Comparison");
    lumL.setForeground(Color.WHITE);
    lumL.setFont(lumL.getFont().deriveFont(14.0f));
    lumL.setBounds(15,120,200,30);
    menu.add(lumL);
    JLabel lumL2 = new JLabel();
    lumL2.setText("(" + "L" + "\u2081" + "/" + "L" + "\u2082" + "):");
    lumL2.setForeground(Color.WHITE);
    lumL2.setFont(lumL2.getFont().deriveFont(14.0f));
    lumL2.setBounds(185,120,120,30);
    menu.add(lumL2);
    JLabel lumL3 = new JLabel();
    double lumC = (4 * Math.pow(radius,2) * Math.pow(temp,4)) / (4 * Math.pow(radius2,2) * Math.pow(temp2,4));
    lumL3.setText(String.format("%.3g%n", lumC) + "L" + "\u2082");
    lumL3.setForeground(Color.WHITE);
    lumL3.setFont(lumL3.getFont().deriveFont(14.0f));
    lumL3.setBounds(243,120,300,30);
    menu.add(lumL3);
    
    //Radius Comparison
    JLabel radL = new JLabel();
    radL.setText("Radius Comparison");
    radL.setForeground(Color.WHITE);
    radL.setFont(radL.getFont().deriveFont(14.0f));
    radL.setBounds(15,160,200,30);
    menu.add(radL);
    JLabel radL2 = new JLabel();
    radL2.setText("(" + "R" + "\u2081" + "/" + "R" + "\u2082" + "):");
    radL2.setForeground(Color.WHITE);
    radL2.setFont(radL2.getFont().deriveFont(14.0f));
    radL2.setBounds(155,160,120,30);
    menu.add(radL2);
    JLabel radL3 = new JLabel();
    double radC = radius / radius2;
    radL3.setText(String.format("%.3g%n", radC) + "R" + "\u2082");
    radL3.setForeground(Color.WHITE);
    radL3.setFont(radL3.getFont().deriveFont(14.0f));
    radL3.setBounds(213,160,300,30);
    menu.add(radL3);
    
    //Mass Comparison
    JLabel massL = new JLabel();
    massL.setText("Mass Comparison");
    massL.setForeground(Color.WHITE);
    massL.setFont(massL.getFont().deriveFont(14.0f));
    massL.setBounds(15,200,200,30);
    menu.add(massL);
    JLabel massL2 = new JLabel();
    massL2.setText("(" + "M" + "\u2081" + "/" + "M" + "\u2082" + "):");
    massL2.setForeground(Color.WHITE);
    massL2.setFont(massL2.getFont().deriveFont(14.0f));
    massL2.setBounds(145,200,120,30);
    menu.add(massL2);
    JLabel massL3 = new JLabel();
    double massC = size / size2;
    massL3.setText(String.format("%.1g%n", massC) + "M" + "\u2082");
    massL3.setForeground(Color.WHITE);
    massL3.setFont(lumL3.getFont().deriveFont(14.0f));
    massL3.setBounds(213,200,300,30);
    menu.add(massL3);
    
    //Star 1 Information
    JLabel star1L = new JLabel();
    star1L.setText("-Star 1-");
    star1L.setForeground(Color.WHITE);
    star1L.setFont(star1L.getFont().deriveFont(14.0f));
    star1L.setBounds(80,265,200,30);
    menu.add(star1L);
    JLabel star1L2 = new JLabel();
    star1L2.setText("Class:");
    star1L2.setForeground(Color.WHITE);
    star1L2.setFont(star1L2.getFont().deriveFont(14.0f));
    star1L2.setBounds(20,295,200,30);
    menu.add(star1L2);
    JLabel star1L3 = new JLabel();
    star1L3.setText("Temperature:");
    star1L3.setForeground(Color.WHITE);
    star1L3.setFont(star1L3.getFont().deriveFont(14.0f));
    star1L3.setBounds(20,325,200,30);
    menu.add(star1L3);
    JLabel star1L4 = new JLabel();
    star1L4.setText("Radius:");
    star1L4.setForeground(Color.WHITE);
    star1L4.setFont(star1L4.getFont().deriveFont(14.0f));
    star1L4.setBounds(20,355,200,30);
    menu.add(star1L4);
    JLabel star1L5 = new JLabel();
    star1L5.setText("Size:");
    star1L5.setForeground(Color.WHITE);
    star1L5.setFont(star1L5.getFont().deriveFont(14.0f));
    star1L5.setBounds(20,385,200,30);
    menu.add(star1L5);
    JLabel star1L6 = new JLabel();
    star1L6.setText("Wavelength:");
    star1L6.setForeground(Color.WHITE);
    star1L6.setFont(star1L6.getFont().deriveFont(14.0f));
    star1L6.setBounds(20,415,200,30);
    menu.add(star1L6);
    
    //Values for Information on Star 1
    JLabel star1L22 = new JLabel();
    star1L22.setText(getClass(temp));
    star1L22.setForeground(getTextColor(getClass(temp)));
    star1L22.setFont(star1L22.getFont().deriveFont(14.0f));
    star1L22.setBounds(65,295,200,30);
    menu.add(star1L22);
    JLabel star1L32 = new JLabel();
    star1L32.setText(temp + "K");
    star1L32.setForeground(Color.WHITE);
    star1L32.setFont(star1L32.getFont().deriveFont(14.0f));
    star1L32.setBounds(120,325,200,30);
    menu.add(star1L32);
    JLabel star1L42 = new JLabel();
    star1L42.setText(radius + "R" + "\u2299");
    star1L42.setForeground(Color.WHITE);
    star1L42.setFont(star1L42.getFont().deriveFont(14.0f));
    star1L42.setBounds(75,355,200,30);
    menu.add(star1L42);
    JLabel star1L52 = new JLabel();
    star1L52.setText(size + "M" + "\u2299");
    star1L52.setForeground(Color.WHITE);
    star1L52.setFont(star1L52.getFont().deriveFont(14.0f));
    star1L52.setBounds(55,385,200,30);
    menu.add(star1L52);
    JLabel star1L62 = new JLabel();
    star1L62.setText(getPeak(temp));
    star1L62.setForeground(Color.WHITE);
    star1L62.setFont(star1L62.getFont().deriveFont(14.0f));
    star1L62.setBounds(113,415,200,30);
    menu.add(star1L62);
    
    //Star 2 Information
    JLabel star2L = new JLabel();
    star2L.setText("-Star 2-");
    star2L.setForeground(Color.WHITE);
    star2L.setFont(star2L.getFont().deriveFont(14.0f));
    star2L.setBounds(440,265,200,30);
    menu.add(star2L);
    JLabel star2L2 = new JLabel();
    star2L2.setText("Class:");
    star2L2.setForeground(Color.WHITE);
    star2L2.setFont(star2L2.getFont().deriveFont(14.0f));
    star2L2.setBounds(380,295,200,30);
    menu.add(star2L2);
    JLabel star2L3 = new JLabel();
    star2L3.setText("Temperature:");
    star2L3.setForeground(Color.WHITE);
    star2L3.setFont(star2L3.getFont().deriveFont(14.0f));
    star2L3.setBounds(380,325,200,30);
    menu.add(star2L3);
    JLabel star2L4 = new JLabel();
    star2L4.setText("Radius:");
    star2L4.setForeground(Color.WHITE);
    star2L4.setFont(star2L4.getFont().deriveFont(14.0f));
    star2L4.setBounds(380,355,200,30);
    menu.add(star2L4);
    JLabel star2L5 = new JLabel();
    star2L5.setText("Size:");
    star2L5.setForeground(Color.WHITE);
    star2L5.setFont(star2L5.getFont().deriveFont(14.0f));
    star2L5.setBounds(380,385,200,30);
    menu.add(star2L5);  
    JLabel star2L6 = new JLabel();
    star2L6.setText("Wavelength:");
    star2L6.setForeground(Color.WHITE);
    star2L6.setFont(star2L6.getFont().deriveFont(14.0f));
    star2L6.setBounds(380,415,200,30);
    menu.add(star2L6);  
    
    //Values for Information on Star 2
    JLabel star2L22 = new JLabel();
    star2L22.setText(getClass(temp2));
    star2L22.setForeground(getTextColor(getClass(temp2)));
    star2L22.setFont(star2L22.getFont().deriveFont(14.0f));
    star2L22.setBounds(425,295,200,30);
    menu.add(star2L22);
    JLabel star2L32 = new JLabel();
    star2L32.setText(temp2 + "K");
    star2L32.setForeground(Color.WHITE);
    star2L32.setFont(star2L32.getFont().deriveFont(14.0f));
    star2L32.setBounds(480,325,200,30);
    menu.add(star2L32);
    JLabel star2L42 = new JLabel();
    star2L42.setText(radius2 + "R" + "\u2299");
    star2L42.setForeground(Color.WHITE);
    star2L42.setFont(star2L42.getFont().deriveFont(14.0f));
    star2L42.setBounds(435,355,200,30);
    menu.add(star2L42);
    JLabel star2L52 = new JLabel();
    star2L52.setText(size2 + "M" + "\u2299");
    star2L52.setForeground(Color.WHITE);
    star2L52.setFont(star1L52.getFont().deriveFont(14.0f));
    star2L52.setBounds(415,385,200,30);
    menu.add(star2L52);
    JLabel star2L62 = new JLabel();
    star2L62.setText(getPeak(temp2));
    star2L62.setForeground(Color.WHITE);
    star2L62.setFont(star2L62.getFont().deriveFont(14.0f));
    star2L62.setBounds(473,415,200,30);
    menu.add(star2L62);
  }
  
  public static String getLuminosity(int temp, double radius)
  {
    //Constant for equation and conversion from solar radii to meters
    //Uses Stefan-Boltzmann Constant with solar radius represented in meters
    double SB_CONSTANT = Math.pow(5.670400,-8);
    double realR = radius * (Math.pow(6.955,8));
    
    //Formatting to scientific notation and representing answer as W (Watts)
    NumberFormat notation = new DecimalFormat("0.##E0 W");
    return notation.format((4 * Math.PI * Math.pow(realR,2) * SB_CONSTANT * Math.pow(temp,4) * Math.pow(10,3)));
  }
  
  public static String getPeak(int temp)
  {
    //Constant divided by temperature of star in kelvin
    //*Is converted to nanometers
    NumberFormat notation = new DecimalFormat("000 nm");
    return notation.format((0.2898/temp)*10000000); 
  }
  
  public static String getFusionRate(int temp, double radius)
  {
    //Stefan-Boltzmann Constant and solar radius represented in meters
    double SB_CONSTANT = Math.pow(5.670400,-8);
    double realR = radius * (Math.pow(6.955,8));
    
    //Formatting to scientific notation and representing answer as kg of Hydrogen fused per second (kg/s)
    //Uses the luminosity equation for star (Using temperature of blackbody)
    NumberFormat notation = new DecimalFormat("0.##E0 kg of H/s");
    return notation.format
      (4 * Math.PI * Math.pow(realR,2) * SB_CONSTANT * Math.pow(temp,4) * Math.pow(10,3) / 630000000000000L);
  }
  
  public static String getSpectra(String sClass, double radius)
  {
    //Gets general type of spectra for a star depending on its stellar classification
    if(sClass == "O")
    {
      return "Ionized He";
    }
    else if(sClass == "B")
    {
      return "He, H";
    }
    else if(sClass == "A")
    {
      return "Strong H, Ionized Metals";
    }
    else if(sClass == "F")
    {
      return "H, Fe, Ionized Ca";
    }
    else if(sClass == "G")
    {
      return "Neutral/Ionized Metals";
    }
    else if(sClass == "K")
    {
      return "Neutral Metals, Na";
    }
    else
    {
      return "TiO, Strong Na";
    } 
  }
  
  public static String getClass(int temp)
  {
    //Gets stellar classification of star based on its temperature value
    if(temp > 30000)
    {
      return "O";
    }
    else if(temp > 9999)
    {
      return "B";
    }
    else if(temp > 7499)
    {
      return "A";
    }
    else if(temp > 5999)
    {
      return "F";
    }
    else if(temp > 5199)
    {
      return "G";
    }
    else if(temp > 3699)
    {
      return "K";
    }
    else
    {
      return "M";
    }
  }
  
  public static String getStarGif(String sClass, double radius)
  {
    //Gets icon for star based on its stellar classification
    if(sClass == "O")
    {
      return "classO.gif";
    }
    else if(sClass == "B")
    {
      return "classB.gif";
    }
    else if(sClass == "A")
    {
      return "classA.gif";
    }
    else if(sClass == "F")
    {
      return "classF.gif";
    }
    else if(sClass == "G")
    {
      return "classG.gif";
    }
    else if(sClass == "K")
    {
      return "classK.gif";
    }
    else
    {
      return "classM.gif";
    }
  }
  
  public static Color getTextColor(String sClass)
  {
    //Gets text color representing the class based on stellar classification of star
    if(sClass == "O")
    {
      return new Color(30, 144, 255);
    }
    else if(sClass == "B")
    {
      return new Color(135, 206, 235);
    }
    else if(sClass == "A")
    {
      return new Color(255, 255, 255);
    }
    else if(sClass == "F")
    {
      return new Color(255, 255, 224);
    }
    else if(sClass == "G")
    {
      return new Color(255, 255, 0);
    }
    else if(sClass == "K")
    {
      return new Color(255, 165, 0);
    }
    else
    {
      return new Color(255, 0, 0);
    }
  }
  
  public static String update(JTextField field)
  {
    //Updates input of text field if changed
    return field.getText();
  }
  
  public static Boolean isNotInt(String input)
  {
    //Checks if input is not an integer
    try {
      Integer.parseInt(input);
      return false;
    }
    catch (NumberFormatException e) {
      return true;
    }
  }
  
  public static Boolean isNotDouble(String input)
  {
    //Checks if input is not a double
    try {
      Double.parseDouble(input);
      return false;
    }
    catch (NumberFormatException e) {
      return true;
    }
  }
}