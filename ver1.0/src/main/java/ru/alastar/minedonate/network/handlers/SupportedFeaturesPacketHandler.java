package ru.alastar.minedonate.network.handlers;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ru.alastar.minedonate.MineDonate;
import ru.alastar.minedonate.gui.ShopGUI;
import ru.alastar.minedonate.network.packets.SupportedFeaturesPacket;

/**
 * Created by Alastar on 20.07.2017.
 */
public class SupportedFeaturesPacketHandler implements IMessageHandler<SupportedFeaturesPacket, IMessage> {
    public SupportedFeaturesPacketHandler() {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SupportedFeaturesPacket message, MessageContext ctx) {
    	
        MineDonate.cfg.sellItems = message.items;
        MineDonate.cfg.itemsMoneyType = message.itemsMoneyType;
        MineDonate.cfg.sellPrivelegies = message.privelegies;
        MineDonate.cfg.privelegiesMoneyType = message.privelegiesMoneyType;
        MineDonate.cfg.sellRegions = message.regions;
        MineDonate.cfg.regionMoneyType = message.regionsMoneyType;
        MineDonate.cfg.sellEntities = message.entities;
        MineDonate.cfg.entitiesMoneyType = message.entitiesMoneyType;
        MineDonate.cfg.userShops = message.userShops;

        MineDonate . loadClientMerch ( ) ;
        ShopGUI . instance . defaultCategory = message . firstCatId ;

        return null;
        
    }
    
}