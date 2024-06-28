package net.starfal.kvouchers.MenuManager;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.Time;


public class PlayerMenuUtility {

    private Player owner;

    public String getCreateTitle() {
        return createTitle;
    }

    public void setCreateTitle(String createTitle) {
        this.createTitle = createTitle;
    }

    public String getCreateDesc() {
        return createDesc;
    }

    public void setCreateDesc(String createDesc) {
        this.createDesc = createDesc;
    }

    public ItemStack getCreateIcon() {
        return createIcon;
    }

    public void setCreateIcon(ItemStack createIcon) {
        this.createIcon = createIcon;
    }



    private String createTitle;
    private String createDesc;
    private ItemStack createIcon;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    private String createTime;

    public int getCreateUses() {
        return createUses;
    }

    public void setCreateUses(int createUses) {
        this.createUses = createUses;
    }

    private int createUses;
    private String createCode;
    private String createActionName;
    private String createAction;

    public String getCreateCode() {
        return createCode;
    }

    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }

    public String getCreateActionName() {
        return createActionName;
    }

    public void setCreateActionName(String createActionName) {
        this.createActionName = createActionName;
    }

    public String getCreateAction() {
        return createAction;
    }

    public void setCreateAction(String createAction) {
        this.createAction = createAction;
    }

    public PlayerMenuUtility(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }

}
