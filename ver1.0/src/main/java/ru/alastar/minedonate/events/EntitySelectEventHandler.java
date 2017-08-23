package ru.alastar.minedonate.events;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import ru.alastar.minedonate.MineDonate;
import ru.alastar.minedonate.network.manage.packets.ManageResponsePacket.ResponseCode;
import ru.alastar.minedonate.network.manage.packets.ManageResponsePacket.ResponseStatus;
import ru.alastar.minedonate.network.manage.packets.ManageResponsePacket.ResponseType;
import ru.alastar.minedonate.rtnl.Account;
import ru.alastar.minedonate.rtnl.ModNetwork;

public class EntitySelectEventHandler {

 	@SideOnly ( Side . SERVER )
    @SubscribeEvent ( priority = EventPriority . NORMAL )
    public void onEvent ( EntityInteractEvent e ) {
       
    	if ( e . entityPlayer instanceof EntityPlayerMP ) {
         
    		Account acc = MineDonate . getAccountFromCache ( e . entityPlayer . getDisplayName ( ) . toLowerCase ( ) ) ;
    		
    		if ( acc != null ) {
    			
    			if ( acc . ms . mobSelect ) {
    				
    				if ( ! ( e . target instanceof EntityLivingBase ) ) {
                        
                		e . entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Error add entity! EntityLivingBase check error!"));
                		ModNetwork . sendToManageResponsePacket ( ( EntityPlayerMP ) e . entityPlayer, ResponseType . ENTITY, ResponseCode . SELECT, ResponseStatus . ERROR_ENTITY_CHECK_LIVINGBASE ) ;
                        
                	} else {
                	
	                	try {
	                		
	                		Class . forName ( e . target . getClass ( ) . getName ( ) ) . getDeclaredConstructor ( net . minecraft . world . World . class ) . newInstance ( e . entityPlayer . getEntityWorld ( ) ) ;
	
	                	} catch ( Exception ex ) {
	                		
	                		ModNetwork . sendToManageResponsePacket ( ( EntityPlayerMP ) e . entityPlayer, ResponseType . ENTITY, ResponseCode . SELECT, ResponseStatus . ERROR_ENTITY_CHECK_INIT ) ;
	                	
	                		e . setCanceled ( true ) ;
	                        acc . ms . mobSelect = false ;
	
	                		return;
	                		
	                	} 
	                	
	                	acc . ms . currentMob = e . target ;

	                	ModNetwork . sendToManageResponsePacket ( ( EntityPlayerMP ) e . entityPlayer, ResponseType . ENTITY, ResponseCode . SELECT, ResponseStatus . OK ) ;

	            		ModNetwork . sendToMobSelectPacket ( ( EntityPlayerMP ) e . entityPlayer, 1 ) ;

                	}
    				
            		e . setCanceled ( true ) ;
                    acc . ms . mobSelect = false ;
    				
                	ModNetwork . sendToManageResponsePacket ( ( EntityPlayerMP ) e . entityPlayer, ResponseType . ENTITY, ResponseCode . ADD, ResponseStatus . ENTITY_SELECT_STOP ) ;

    			}
	
    		}
        	
        }
    	
    }
	    

}
