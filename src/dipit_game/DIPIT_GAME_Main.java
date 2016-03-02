/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dipit_game;

import dipit_game.GameWindowJFrame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import ExpandedGame.ExpandedGame_OnKeyPressedImpl;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;
import org.xeustechnologies.jcl.proxy.CglibProxyProvider;
import org.xeustechnologies.jcl.proxy.ProxyProviderFactory;

/**
 *
 * @author Arch
 */
public class DIPIT_GAME_Main {

    public static final String DIPITPATH = "C:/Users/Arch/Documents/NetBeansProjects/DIPIT_GAME/build/classes";
    public static World_DIPIT TheWorld;
    public static PlayerBase ThePlayer;
    public static GameWindowJFrame GameWindow;
    static int sizex = 4;
    static int sizey = 4;
    public static List<String> ConsoleOuts = new ArrayList<>();

    private static final List<dipit_game.events.PostWorldGenInterface> postWorldGen_listeners = new ArrayList<>();
    private static List<dipit_game.events.OnKeyPressedInterface> OnKeyPressed_Listeners = new ArrayList<>();

    public static void add_postWorldGen_Listener(dipit_game.events.PostWorldGenInterface toAdd) {
        postWorldGen_listeners.add(toAdd);
    }

    public static void add_OnKeyPressed_Listener(dipit_game.events.OnKeyPressedInterface toAdd) {
        OnKeyPressed_Listeners.add(toAdd);
    }

    public static void OnKeyPressed(java.awt.event.KeyEvent evt) {
        OnKeyPressed_Listeners.stream().forEach((hl) -> {
            //System.out.println(("hl(null) : " + (hl == null)));
            hl.OnKeyPressed(evt);
        });
    }

    public static void main(String[] args) {
        URL main = dipit_game.DIPIT_GAME_Main.class.getResource("dipit_game.DIPIT_GAME_Main.class");
        if (!"file".equalsIgnoreCase(main.getProtocol())) {
            throw new IllegalStateException("Main class is not stored in a file.");
        }
        File path = new File(main.getPath());
        System.out.println("Classpath? : " + (path.toString()));
        
        //
        LoadMods();
        TheWorld = new World_DIPIT(sizex, sizey);
        TheWorld.AddSolidTag("W");
        // TODO code application logic here
        for (int y = 0; y < sizey; y++) {
            for (int x = 0; x < sizex; x++) {
                TileBase aNewTile = new TileBase(x, y);
                aNewTile.SetRenderCharacter("+");
                TheWorld.AddTile(aNewTile);
            }
        }
        postWorldGen_listeners.stream().forEach((hl) -> {
            //System.out.println(("hl(null) : " + (hl == null)));
            hl.ModifyWorldGen(TheWorld);
        });
        ThePlayer = new PlayerBase(0, 0, TheWorld);
        GameWindow = new dipit_game.GameWindowJFrame(TheWorld, ThePlayer);
        GameWindow.setVisible(true);
        Iterator<String> ConsoleIterator;
        ConsoleIterator = ConsoleOuts.iterator();
        String output = "";
        while (ConsoleIterator.hasNext()) {
            output = output + "\n" + ConsoleIterator.next();
        }
        GameWindow.ConsoleOut.setText(output);
        Debug_PrintWorldAsString();
    }

    public static void Debug_PrintWorldAsString() {
        String print_line = "";
        for (int y = 0; y < sizey; y++) {
            print_line += "\n";
            for (int x = 0; x < sizey; x++) {
                if (ThePlayer.GetPositionX() == x && ThePlayer.GetPositionY() == y) {
                    print_line += "[" + ThePlayer.GetRenderCharacter() + "]";
                } else {
                    print_line += "[" + TheWorld.GetTile(TheWorld.GetTileAtPosition(x, y)).GetRenderCharacter() + "]";
                }
            }
        }
        System.out.println(print_line);
    }

    private static void LoadMods() {
        String referencedModlistString = "C:\\Users\\Arch\\Documents\\NetBeansProjects\\referenced" + "/modlist.txt";
        String BaseModString = "C:\\Users\\Arch\\Documents\\NetBeansProjects\\referenced";

        ConsoleOuts.add(referencedModlistString);
        List<String> ModsToLoad = new ArrayList<>();
        try {
            FileReader MyReader = new FileReader(referencedModlistString);
            ModsToLoad.addAll(GetModsToLoad(MyReader));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DIPIT_GAME_Main.class.getName()).log(Level.SEVERE, null, ex);
            ConsoleOuts.add(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(DIPIT_GAME_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //ConsoleOuts.addAll(MyDebugOut);
        JarClassLoader jcl = new JarClassLoader();
        jcl.add("ExpandedGame/");
        ProxyProviderFactory.setDefaultProxyProvider(new CglibProxyProvider());

        //Create a factory of castable objects/proxies
        JclObjectFactory factory = JclObjectFactory.getInstance(true);

        //Create and cast object of loaded class
        dipit_game.DipitModBase mi = (dipit_game.DipitModBase) factory.create(jcl, "ExpandedGame.ExpandedMain");
        //Object obj = factory.create(jcl, "ExpandedGame.ExpandedMain");
        for (String str : ModsToLoad) {
            jcl.add(BaseModString + "\\" + str);
            ConsoleOuts.add(BaseModString + "\\" + str);
            //factory.create(jcl, BaseModString + "\\ModLibrary.jar");
            ConsoleOuts.add(">Attempting to ClassLoad [" + str + "] \n Suceeded = " + (LoadMod(BaseModString + "\\" + str)));
        }

    }

    private static List<String> GetModsToLoad(FileReader MyReader) throws IOException {
        String ModPathBuild = "";
        List<String> ModsList = new ArrayList<>();
        char[] readout = new char[1024];
        MyReader.read(readout);

        for (char c : readout) {
            //System.out.println("> " + java.lang.Character.toString(c));
            // System.out.println("=* " + (c == '*') );
            if (c == ';') {
                ConsoleOuts.add("Adding Build >" + ModPathBuild.trim());
                ModsList.add(ModPathBuild.trim());
                ModPathBuild = "";
            } else {
                ModPathBuild = ModPathBuild + java.lang.Character.toString(c);
            }
        }

        return ModsList;
    }

    /**
     * Loads the Mod at Binary String point [BinaryModName]. Throws exceptions,
     * and continues to run the application if the mod is unable to be loaded
     * for whatever reason.
     *
     * @param BinaryModName the Binary String name of the, [ex. MyMod.ModClass]
     * .
     * @return [boolean] true if the mod was loaded successfully, false if it
     * failed for whatever reason.
     */
    private static boolean LoadMod(String BinaryModName) {
        Class<?> ModClass = null;
        Method method = null;
        try {
            Class<?> ModClass_ = ClassLoader.getSystemClassLoader().loadClass(BinaryModName);
            ModClass = ModClass_;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DIPIT_GAME_Main.class.getName()).log(Level.SEVERE, "Inable to find Mod @" + BinaryModName, ex);
        }
        if (ModClass != null) {
            //Get InitMod Method
            try {
                Method method_ = ModClass.getMethod("InitMod");
                method = method_;
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(DIPIT_GAME_Main.class.getName()).log(Level.CONFIG, "Mod @" + BinaryModName + " has not specified a [InitMod] Method!", ex);
            } catch (SecurityException ex) {
                Logger.getLogger(DIPIT_GAME_Main.class.getName()).log(Level.CONFIG, null, ex);
            }
            //Get Init Mod
            try {
                method.invoke(null, null);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(DIPIT_GAME_Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (ModClass != null);
    }
}
