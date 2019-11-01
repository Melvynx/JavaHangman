package pendu;

public class Pendu {
    public static void main(String[] args) {
        //Placement de la ToolBar dans le menu Apple
        try {
            System.setProperty( "com.apple.mrj.application.apple.menu.about.name", "Ted" );
            System.setProperty( "com.apple.macos.useScreenMenuBar", "true" );
            System.setProperty( "apple.laf.useScreenMenuBar", "true" ); // for older versions of Java
        } catch ( SecurityException e ) {/*Nothing*/}
        //Lancement de la page principal
        Window win = new Window();
        win.setPage(0);
    }
}
