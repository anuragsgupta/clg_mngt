package raven.application;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.UIManager;
import raven.forms.other.DemoForm;
import raven.menu.FormManager;
import raven.popup.GlassPanePopup;

/**
 *
 * @author Raven
 */
public class Application extends JFrame {

    private static Application app;
    String userName, branch, role;

    public void setApp(Application app) {
        this.app = app;
    }

    public Application(HashMap<String, String> map) {
        this.userName = map.get("name");
        this.branch = map.get("branch");
        this.role = map.get("role");
        System.err.println(this.branch + " Application" + this.role);

        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("raven.themes");
//        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 15));
        FlatMacLightLaf.setup();
        init();

    }
    String[] adjectives = {
        "Sparkling",
        "Awesome",
        "Fabulous",
        "Super",
        "Dynamic",
        "Star",
        "Radiant",
        "Vibrant",
        "Sensational"
    };

    // Step 2: Create a Random object
    private void init() {
//        setUndecorated(true);
//        setBackground(new Color(0, 0, 0, 0));
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        Random random = new Random();

        // Step 3: Generate a random index within the range of the array
        int randomIndex = random.nextInt(adjectives.length);

        // Step 4: Select the adjective using the random index
        String selectedAdjective = adjectives[randomIndex];

        // Output the selected adjective
        System.out.println("Randomly selected adjective: " + selectedAdjective);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1400, 760));
        setLocationRelativeTo(null);
        setFont(new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));

        setResizable(true);
//        setAlwaysOnTop(false);
        if (userName.toLowerCase().startsWith("shailja")) {
            userName = userName + " ;)";
        }
        setName("GPC Management " + "         Welcome  Back " + selectedAdjective + " " + userName);
        setTitle("GPC Management " + "        Welcome  Back " + selectedAdjective + " " + userName);

//        setTitle("GPC Management "+userName);
//        setContentPane(new Background());
        // applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        GlassPanePopup.install(this);
        FormManager.setData(branch, role);
        FormManager.install(this);
        FormManager.showForm(new DemoForm("Welcome To GPC MANAGEMENT"));
        // applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    public void appDispose() {
        dispose();
    }

    public static void logout() {
        FlatAnimatedLafChange.showSnapshot();
        app.dispose();
        new LoginForm().setVisible(true);
//        appDispose();
//        app.dispose();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }
//
//    public static void main(String[] args) {
//        FlatRobotoFont.install();
//        FlatLaf.registerCustomDefaultsSource("raven.themes");
//        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
//        FlatMacLightLaf.setup();
////        EventQueue.invokeLater(() -> new Application().setVisible(true));
//        java.awt.EventQueue.invokeLater(() -> {
//            app = new Application();
////            MainLoginForm form = new MainLoginForm(app);
//
//            //  app.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//            app.setVisible(true);
//
//        });
//    }
}
