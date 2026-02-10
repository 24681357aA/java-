package tqlslib;

import arc.Core;
import arc.Events;
import arc.util.Log;
import arc.util.Time;
import mindustry.game.EventType.*;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.Mod;
import mindustry.ui.dialogs.BaseDialog;

public class TQLSlib extends Mod{

    public TQLSlib(){
        Log.info("Loaded TQLSlib constructor.");

        //监听游戏加载事件
        Events.on(ClientLoadEvent.class, e -> {
            //启动时显示对话框
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //模组精灵以模组名称为前缀（此模组在配置中名为 'tqlslib-java-mod'）
                dialog.cont.image(Core.atlas.find("tqlslib-java-mod-frog")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading some tqlslib content.");
    }

}
