package ru.alastar.minedonate.network.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import ru.alastar.minedonate.MineDonate;
import ru.alastar.minedonate.merch.Merch;

/**
 * Created by Alastar on 23.07.2017.
 */
public class MerchInfoPacket  implements IMessage {

    public MerchInfoPacket() {

    }

    public Merch info;
    public int shopId = 0;
    public int m_category = 0;

    public MerchInfoPacket(Merch info) {
    	
        this.info = info;
        this.m_category = info.getCategory();
        this.shopId = info.getShopId();
        
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(shopId);
        buf.writeInt(m_category);
        info.write(buf);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	shopId = buf.readInt();
        m_category = buf.readInt();
        info =  MineDonate.shops.get(shopId).cats[m_category].constructMerch();
        info . setShopId ( shopId ) ;
        info.read(buf);
    }
}