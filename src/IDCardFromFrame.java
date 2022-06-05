import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class IDCardFromFrame
{
    String fileName = null;
    byte []photo_image = null;
    static JFrame mainFrame;
    static JPanel IDCardPanel, collegeNameLblPanel, studentDetailsPanel, inputPanel;
    JButton logOutBtn, printPBtn, clearBtn;
    //Ye Labels ID card me he jo  entered details ko display karta he
    JLabel collegeNameLbl, studentIDLbl, studentNameLbl, classLbl, branchLbl, mobileLbl, clgLogoLbl, studentPhotoLbl;
    //Ye Labels bhi ID card pe jo upper ke labels ko represent karte he
    JLabel SID, SName, Class, branch, address, mobile;
    JTextArea addressLblTxt;
    public IDCardFromFrame()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        //IDCard input panel with its fields
        JLabel id, name, class_, branchInput, mobile_no, collegeName, selectPhoto, collegeLogo, addressInputLbl;
        JTextField idTxt, nameTxt, classTxt, branchTxt, mobile_noTxt, collegeNameTxt, photoNameTxt;
        JTextArea addressInputTxt;
        JPanel inputPanel;
        JButton photo, generateIDBtn, printIDBtn;
        inputPanel = new JPanel();
        inputPanel.setBounds(10,10,480, 660);
        inputPanel.setBackground(new Color(123, 144, 149, 100));
        inputPanel.setLayout(new GridBagLayout());
        id = new JLabel("Student ID:");
        id.setFont(new Font("sans serif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 10);
        inputPanel.add(id, gbc);
        idTxt = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(idTxt, gbc);
        name = new JLabel("Name:");
        name.setFont(new Font("sans serif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        inputPanel.add(name, gbc);
        nameTxt = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(nameTxt, gbc);
        class_ = new JLabel("Class");
        class_.setFont(new Font("sans serif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 10);
        inputPanel.add(class_, gbc);
        classTxt = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(classTxt, gbc);
        branchInput = new JLabel("Branch:");
        branchInput.setFont(new Font("sans serif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 10);
        inputPanel.add(branchInput, gbc);
        branchTxt = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(branchTxt, gbc);
        mobile_no = new JLabel("Mobile Number:");
        mobile_no.setFont(new Font("sans serif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 10, 10);
        inputPanel.add(mobile_no, gbc);
        mobile_noTxt = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        inputPanel.add(mobile_noTxt, gbc);
        collegeName = new JLabel("College Name");
        collegeName.setFont(new Font("sans serif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 10, 10);
        inputPanel.add(collegeName, gbc);
        collegeNameTxt = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        inputPanel.add(collegeNameTxt, gbc);
        selectPhoto = new JLabel("Add Student Photo:");
        selectPhoto.setFont(new Font("sans serif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 10 , 10);
        inputPanel.add(selectPhoto, gbc);
        photo = new JButton("Add");
        photo.setFont(new Font("sans serif", Font.BOLD, 16));
        gbc.gridx = 2;
        gbc.gridy = 6;
        inputPanel.add(photo, gbc);
        photoNameTxt = new JTextField(20);
        photoNameTxt.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 6;
        inputPanel.add(photoNameTxt, gbc);
        photo.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(mainFrame);
            File file = fileChooser.getSelectedFile();
            fileName = file.getAbsolutePath();
            try
            {
                File image = new File(fileName);
                FileInputStream fis = new FileInputStream(image);
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                byte []buffer = new byte[1024];
                for(int readno; (readno = fis.read(buffer)) != -1;)
                {
                    byteArray.write(buffer, 0, readno);
                }
                photo_image = byteArray.toByteArray();
                photoNameTxt.setText(fileName);
            }
            catch (Exception exception)
            {
                JOptionPane.showMessageDialog(mainFrame, "Invalid File!");
            }
        });
        addressInputLbl = new JLabel("Address");
        addressInputLbl.setFont(new Font("sans serif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(0,0,5,5);
        inputPanel.add(addressInputLbl, gbc);
        addressInputTxt = new JTextArea(3, 15);
        addressInputTxt.setLineWrap(true);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.insets = new Insets(0, -65, 5, 5);
        inputPanel.add(addressInputTxt, gbc);
        logOutBtn = new JButton("<- Log out");
        logOutBtn.setFont(new Font("sans serif", Font.BOLD, 16));
        logOutBtn.addActionListener(e -> {
            mainFrame.dispose();
            new LoginRegWindow();
        });
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.insets = new Insets(100, 5, 0,0);
        inputPanel.add(logOutBtn, gbc);
        generateIDBtn = new JButton("Generate ID Card");
        generateIDBtn.setFont(new Font("sans serif", Font.BOLD, 16));
        generateIDBtn.addActionListener(e ->
        {
            collegeNameLbl.setText(collegeNameTxt.getText().toUpperCase());
            studentIDLbl.setText(idTxt.getText());
            studentNameLbl.setText(nameTxt.getText().toUpperCase());
            classLbl.setText(classTxt.getText().toUpperCase());
            branchLbl.setText(branchTxt.getText().toUpperCase());
            mobileLbl.setText(mobile_noTxt.getText());
            addressLblTxt.setText(addressInputTxt.getText().toUpperCase());
            studentPhotoLbl.setIcon(resize(fileName));
        });
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.insets = new Insets(0, 0, 0, 10);
        inputPanel.add(generateIDBtn, gbc);
        printIDBtn = new JButton("Print ID Card");
        printIDBtn.setFont(new Font("sans serif", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.insets = new Insets(0, 0, 0, 10);
        inputPanel.add(printIDBtn, gbc);
        printIDBtn.addActionListener(e -> print(IDCardPanel));
        clearBtn = new JButton("Clear");
        clearBtn.setFont(new Font("sans serif", Font.BOLD, 16));
        gbc.gridx = 2;
        gbc.gridy = 8;
        inputPanel.add(clearBtn, gbc);
        clearBtn.addActionListener(e ->
        {
            collegeNameLbl.setText("");
            studentIDLbl.setText("");
            studentNameLbl.setText("");
            classLbl.setText("");
            branchLbl.setText("");
            mobileLbl.setText("");
            addressLblTxt.setText("");
            studentPhotoLbl.setIcon(resize("C:\\Users\\croma\\Pictures\\Saved Pictures\\Login.png"));
            idTxt.setText("");
            nameTxt.setText("");
            classTxt.setText("");
            branchTxt.setText("");
            mobile_noTxt.setText("");
            collegeNameTxt.setText("");
            photoNameTxt.setText("");
            addressInputTxt.setText("");
        });
        SID = new JLabel("Student ID:");
        SID.setFont(new Font("Georgia", Font.BOLD, 14));
        SName = new JLabel("Name:");
        SName.setFont(new Font("Georgia", Font.BOLD, 14));
        Class = new JLabel("Class:");
        Class.setFont(new Font("Georgia", Font.BOLD, 14));
        IDCardPanel = new JPanel();
        studentDetailsPanel = new JPanel();
        studentDetailsPanel.setLayout(new GridBagLayout());
        studentDetailsPanel.setBounds(150, 70, 340, 220);
        studentDetailsPanel.setBackground(new Color(203, 243, 249));
        IDCardPanel.add(studentDetailsPanel);
        IDCardPanel.setLayout(null);
        collegeNameLblPanel = new JPanel();
        collegeNameLblPanel.setLayout(new GridBagLayout());
        collegeNameLblPanel.setBounds(10, 10, 480, 50);
        collegeNameLblPanel.setBackground(new Color(42, 82, 190, 150));
        IDCardPanel.add(collegeNameLblPanel);
        collegeNameLbl = new JLabel();
        collegeNameLbl.setFont(new Font("Georgia", Font.BOLD, 18));
        collegeNameLbl.setForeground(new Color(255, 255, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 10);
        collegeNameLblPanel.add(collegeNameLbl, gbc);
        studentIDLbl = new JLabel();
        studentIDLbl.setFont(new Font("romans", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5,0);
        studentDetailsPanel.add(SID, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,5, 5, 0);
        studentDetailsPanel.add(studentIDLbl, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        studentDetailsPanel.add(SName, gbc);
        studentNameLbl = new JLabel();
        studentNameLbl.setFont(new Font("Georgia", Font.BOLD, 12));
        gbc.gridx = 1;
        gbc.gridy = 1;
        studentDetailsPanel.add(studentNameLbl, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        studentDetailsPanel.add(Class, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 5, 0);
        studentDetailsPanel.add(Class, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 5, 0);
        classLbl = new JLabel();
        classLbl.setFont(new Font("Georgia", Font.BOLD, 14));
        studentDetailsPanel.add(classLbl, gbc);
        branch = new JLabel("Branch:");
        branch.setFont(new Font("Georgia", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 0, 5, 0);
        studentDetailsPanel.add(branch, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 0, 5, 0);
        branchLbl = new JLabel();
        branchLbl.setFont(new Font("Georgia", Font.BOLD, 14));
        studentDetailsPanel.add(branchLbl, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        studentDetailsPanel.add(branchLbl, gbc);
        mobileLbl = new JLabel();
        mobileLbl.setFont(new Font("romans", Font.BOLD, 14));
        mobile = new JLabel("Mobile Number:");
        mobile.setFont(new Font("Georgia", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        studentDetailsPanel.add(mobile, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        studentDetailsPanel.add(mobileLbl, gbc);
        address = new JLabel("Address:");
        address.setFont(new Font("Georgia", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 5, 5);
        studentDetailsPanel.add(address, gbc);
        addressLblTxt = new JTextArea(3, 19);
        addressLblTxt.setEditable(false);
        addressLblTxt.setBackground(studentDetailsPanel.getBackground());
        addressLblTxt.setFont(new Font("Georgia", Font.PLAIN, 12));
        addressLblTxt.setLineWrap(true);
        gbc.gridx = 1;
        gbc.gridy = 5;
        studentDetailsPanel.add(addressLblTxt, gbc);
        studentPhotoLbl = new JLabel();
        studentPhotoLbl.setBounds(12, 70, 128, 150);
        studentPhotoLbl.setIcon(resize("C:\\Users\\croma\\Pictures\\Saved Pictures\\Login.png"));
        IDCardPanel.add(studentPhotoLbl);
        mainFrame = new JFrame("ID Card Form Filling Frame");
        mainFrame.setSize(1028, 720);
        IDCardPanel.setBounds(500, 10, 500, 300);
        IDCardPanel.setBackground(new Color(130, 208, 241));
        mainFrame.setLayout(null);
        mainFrame.add(IDCardPanel);
        mainFrame.add(inputPanel);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public ImageIcon resize(String imgPath)
    {
        ImageIcon icon = null;
        try
        {
            icon = new ImageIcon(imgPath);
        }catch (Exception e){JOptionPane.showMessageDialog(mainFrame, "Invalid Image path!");};
        Image myImage = icon.getImage();
        Image scaledImage = myImage.getScaledInstance(studentPhotoLbl.getWidth(), studentPhotoLbl.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    public void print(JPanel panel)
    {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setJobName("Test Print");
        printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException
            {
                if(pageIndex > 0)
                {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D graphics2D = (Graphics2D) graphics;
                graphics2D.translate(pageFormat.getImageableX() * 2, pageFormat.getImageableY() * 2);
                graphics2D.scale(0.8, 0.8);
                panel.print(graphics2D);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean returningresult = printerJob.printDialog();
        if(returningresult)
        {
            try
            {
                printerJob.print();
            }catch (PrinterException pe)
            {
                JOptionPane.showMessageDialog(IDCardPanel, "Print Error: " +  pe.getMessage());
            }
            finally
            {
                printerJob.cancel();
            }
        }
    }
}
