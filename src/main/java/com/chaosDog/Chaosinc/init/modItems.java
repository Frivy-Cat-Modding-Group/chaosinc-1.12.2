package com.chaosDog.Chaosinc.init;

import com.chaosDog.Chaosinc.init.ModItems.Armor;
import com.chaosDog.Chaosinc.init.ModItems.DoorItems;
import com.chaosDog.Chaosinc.init.ModItems.MiscItems;
import com.chaosDog.Chaosinc.init.ModItems.Tools;

public class modItems {
    public static void register(){
        Tools.initAndRegister();
        Armor.initAndRegister();
        DoorItems.initAndRegister();
        MiscItems.initAndRegister();
    }
}
