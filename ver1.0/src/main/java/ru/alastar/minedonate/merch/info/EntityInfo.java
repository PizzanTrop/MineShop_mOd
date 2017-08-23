package ru.alastar.minedonate.merch.info;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import ru.alastar.minedonate.merch.Merch;
import ru.alastar.minedonate.rtnl.Utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by Alastar on 21.07.2017.
 */
public class EntityInfo extends Merch {

    public String classpath;
    public String name;
    public NBTTagCompound entity_data;
    public int limit;
    public EntityLivingBase entity;

    public EntityInfo(int _shopId, int _catId, int _rating, int merch_id, int cost, Blob data, String name, int lim) {

        super(_shopId, _catId, merch_id, _rating);

        this.cost = cost;

        ByteBuf buf = Unpooled.buffer();
        try {
            buf.writeBytes(data.getBinaryStream(), (int) data.length());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        entity_data = ByteBufUtils.readTag(buf);
        this.classpath = entity_data.getString("classpath");
        this.name = name;
        this.limit = lim;

    }

    public EntityInfo(int _shopId, int _catId, int merch_id, int _rating, int cost, Entity entity, String name, int limit) {

        super(_shopId, _catId, merch_id, _rating);

        this.cost = cost;
        this.entity_data = new NBTTagCompound();
        entity.writeToNBT(entity_data);
        this.name = name;
        this.classpath = entity.getClass().getName();
        entity_data.setString("classpath", this.classpath);
        this.limit = limit;
    }

    public EntityInfo() {
        super();
    }

    @Override
    public int getCategory() {
        return 3;
    }

    @Override
    public void read(ByteBuf buf) {
        super.read(buf);
        cost = buf.readInt();
        
        try {
			
        	name = Utils . netReadString ( buf ) ;
	        
	        entity_data = ByteBufUtils.readTag(buf);
	        limit = buf.readInt();
					
        	classpath = Utils . netReadString ( buf ) ;
			
		} catch ( Exception ex ) {
			
			ex . printStackTrace ( ) ;
			
		}
        
        try {
            entity = (EntityLivingBase) Class.forName(classpath).getDeclaredConstructor(net.minecraft.world.World.class).newInstance(Minecraft.getMinecraft().theWorld);
            entity.readFromNBT(entity_data);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        
    }

    @Override
    public void write(ByteBuf buf) {
    	
        super.write(buf);
                
        try {
			
            buf.writeInt(cost);

        	Utils . netWriteString ( buf, name ) ;
	        
	        ByteBufUtils.writeTag(buf, entity_data);
	        buf.writeInt(limit);
		
        	Utils . netWriteString ( buf, classpath ) ;
			
		} catch ( Exception ex ) {
			
			ex . printStackTrace ( ) ;
			
		}
        
    }

    @Override
    public String getBoughtMessage() {
        return "bought entity " + name;
    }


    @Override
    public int getAmountToBuy() {
        return 1;
    }

	@SideOnly(Side.CLIENT)
	@Override
	public String getSearchValue ( ) {
		
		return EnumChatFormatting . getTextWithoutFormattingCodes ( name ) ;
		
	}
	
}
