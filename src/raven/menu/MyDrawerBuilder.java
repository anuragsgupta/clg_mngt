package raven.menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import raven.application.LoginUser;
import raven.drawer.component.DrawerPanel;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.header.SimpleHeaderStyle;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.drawer.component.menu.SimpleMenuStyle;
import raven.drawer.component.menu.data.Item;
import raven.drawer.component.menu.data.MenuItem;
import raven.forms.DashboardForm;
import raven.forms.other.FormAdmission;
import raven.forms.other.FormAdmissionTeacher;
import raven.forms.other.FormEditAdmission;
import raven.forms.other.FormMark;
import raven.forms.other.FormMySalary;
import raven.forms.other.FormRead;
import raven.forms.other.FormReadTeacher;
import raven.forms.other.FormSalary;
import raven.forms.other.FormStdMmnt;
import raven.forms.other.FormTeacherSalary;
import raven.forms.other.LockForm;
import raven.forms.other.SubjectForm;
import raven.swing.AvatarIcon;

/**
 *
 * @author Raven
 */
public class MyDrawerBuilder extends SimpleDrawerBuilder {

    private final ThemesChange themesChange;
    private String branch;
    private String role;

    public MyDrawerBuilder(String branch, String role) {
        themesChange = new ThemesChange();
        this.branch = branch;
        this.role = role;
        System.err.println(branch + " drawer " + role);
    }

    @Override
    public Component getFooter() {
        return themesChange;
    }

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        AvatarIcon icon = new AvatarIcon(getClass().getResource("/raven/resources/image/profile.png"), 60, 60, 999);
        icon.setBorder(2);
        return new SimpleHeaderData()
                .setIcon(icon)
                .setTitle(LoginUser.getUsername())
                .setDescription(LoginUser.getId() + ' ' + LoginUser.getBranch())
                .setHeaderStyle(new SimpleHeaderStyle() {

                    @Override
                    public void styleTitle(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:#FAFAFA");
                    }

                    @Override
                    public void styleDescription(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:#E1E1E1");
                    }
                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData();
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {

        MenuItem items[] = new MenuItem[]{
            new Item.Label("MAIN"), // index = 0
            new Item("Dashboard", "dashboard.svg"), // index 1
            new Item.Label("Administrator"), //index = 2
            new Item("Admission", "email.svg") //index = 3
            .subMenu("New Admission") // index 3 subindex 0
            .subMenu("New Teacher") // index 3 subindex 1
            .subMenu("Edit Admission")// index 3 subindex 2
            .subMenu("List Admission")// index 3 subindex 4
            .subMenu("Logout"),
            new Item.Label("Functions"),
            new Item(LoginUser.getBranch(), "page.svg") // sub index = 4
            .subMenu("List Teacher") //1
            .subMenu("List Students")
            .subMenu("Students Fee")
            .subMenu("ADD Subject")
            .subMenu("Marks")
            .subMenu("My Salary"),
            new Item.Label("FINANCE"),
            new Item("FINANCE", "ui.svg") // index = 3
            .subMenu("Dashboard")
            .subMenu("Pay Salary")
            .subMenu("List Salary")
            .subMenu("Edit Salary"),
            new Item("Forms", "forms.svg")
            .subMenu("Basic Elements")
            .subMenu("Advanced Elements")
            .subMenu("SEditors")
            .subMenu("Wizard")
//            new Item.Label("OTHER"),
//            new Item("Charts", "chart.svg") // index = 3
//            .subMenu("Apex")
//            .subMenu("Flot")
//            .subMenu("Sparkline"),
//            new Item("Icons", "icon.svg") // index  = 4
//            .subMenu("Feather Icons")
//            .subMenu("Flag Icons")
//            .subMenu("Mdi Icons"),
//            new Item("Special Pages", "page.svg") // index = 5
//            .subMenu("Blank page")
//            .subMenu("Faq")
//            .subMenu("Invoice")
//            .subMenu("Profile")
//            .subMenu("Pricing")
//            .subMenu("Timeline")

        };

        SimpleMenuOption simpleMenuOption = new SimpleMenuOption() {
            @Override
            public Icon buildMenuIcon(String path, float scale) {
                FlatSVGIcon icon = new FlatSVGIcon(path, scale);
                FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
                colorFilter.add(Color.decode("#969696"), Color.decode("#FAFAFA"), Color.decode("#969696"));
                icon.setColorFilter(colorFilter);
                return icon;
            }
        };

        simpleMenuOption.setMenuStyle(new SimpleMenuStyle() {
            @Override
            public void styleMenuItem(JButton menu, int[] index) {
                menu.putClientProperty(FlatClientProperties.STYLE, ""
                        + "[light]foreground:#FAFAFA;"
                        + "arc:10");
            }

            @Override
            public void styleMenu(JComponent component) {
                component.putClientProperty(FlatClientProperties.STYLE, ""
                        + "background:$Drawer.background");
            }

            @Override
            public void styleLabel(JLabel label) {
                label.putClientProperty(FlatClientProperties.STYLE, ""
                        + "[light]foreground:darken(#FAFAFA,15%);"
                        + "[dark]foreground:darken($Label.foreground,30%)");
            }
        });
        simpleMenuOption.addMenuEvent(new MenuEvent() {
            @Override
            public void selected(MenuAction action, int[] index) {

                try {
                    System.err.println("======================================================");
                    System.err.println("index len = " + index.length + "index[0] = " + index[0] + "index[1] " + index[1]);

                    for (int i = 0; i < index.length; i++) {
                        System.err.println(index.length + " " + i + " " + index[i]);
                    }
                } catch (Exception e) {
                }

                switch (index.length) {

                    case 1:
                        System.err.println("Dashform");
                        if (index[0] == 0) {
                            FormManager.showForm(new DashboardForm());
                        }

                        break;

                    case 2:
                        System.err.println("Admission");
//                        if (index.length ==1) {
                        switch (index[0]) {
                            case 1:
                                //index[0] == 1 = admission
                                if (index[1] == 0) {        // new admission
                                    if (LoginUser.getRole().equals("PERMANENT")) {
                                        FormManager.showForm(new FormAdmission());
                                    } else {
                                        FormManager.showForm(new LockForm());
                                    }
                                } else if (index[1] == 1) { // new teacher
                                    if (LoginUser.getRole().equals("PERMANENT")) {
                                        FormManager.showForm(new FormAdmissionTeacher());
                                    } else {
//                                    JOptionPane.ERROR_MESSAGE();
                                        FormManager.showForm(new LockForm());
                                    }
                                } else if (index[1] == 2) { // edit admission
                                    FormManager.showForm(new FormEditAdmission());
                                } else if (index[1] == 3) { // list Teacher
                                    if (LoginUser.getRole().equals("PERMANENT")) {
                                        FormManager.showForm(new FormRead(branch, role));
                                    } else {
                                        FormManager.showForm(new LockForm());
                                    }
                                } else if (index[1] == 4) { // Logout

                                    FormManager.showForm(new LockForm());

                                }

                                // Functions
                                break;
                            case 2:
                                ////index[0] == 2 =
                                if (index[1] == 0) {        // List Teachers
                                    FormManager.showForm(new FormReadTeacher());
                                } else if (index[1] == 1) { // List Students

                                    FormManager.showForm(new FormRead());

                                } else if (index[1] == 2) { // Student fee

                                    FormManager.showForm(new FormStdMmnt(""));

                                } else if (index[1] == 3) { // ADD SUBJECT

                                    FormManager.showForm(new SubjectForm(""));

                                } else if (index[1] == 4) { // MARKS

                                    FormManager.showForm(new FormMark());

                                } else if (index[1] == 5) { // My salary

                                    FormManager.showForm(new FormMySalary(""));

                                }

                                break;

                            case 3:                                 // finance

                                ////index[0] == 2 =
                                if (index[1] == 0) {        // Dashboard
                                    FormManager.showForm(new DashboardForm());
                                } else if (index[1] == 1) { // Logout

//                                FormManager.showForm(new FormRead(branch, role));
                                    FormManager.showForm(new FormTeacherSalary("Teacher Salary"));

                                } else if (index[1] == 2) { // Logout
                                    FormManager.showForm(new FormSalary(branch, role));
//                                FormManager.showForm(new FormSalary(branch, role));
                                } else if (index[1] == 3) { // Logout

                                    FormManager.showForm(new LockForm());

                                }
                                break;
                            default:
                                break;
                        }

                        break;

                    default:
                        break;
                }
            }
        });
        simpleMenuOption.setMenus(items)
                .setBaseIconPath("raven/resources/menu")
                .setIconScale(0.45f);
        return simpleMenuOption;
    }

    @Override
    public void build(DrawerPanel drawerPanel) {
        drawerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Drawer.background");
    }

    @Override
    public int getDrawerWidth() {
        return 270;
    }
}
